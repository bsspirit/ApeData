package org.ape.data.core.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.ape.data.core.util.FileUtil;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * @title sftp客户端
 * @description
 * @usage
 * @copyright Copyright 2012 Sunshine Insurance Group . All rights reserved.
 * @company Sunshine Insurance Group.
 * @author yushuanghai
 * @version $Id: FtpClient.java,v 1.3 2013-1-15 9:56:13 $
 * @create 2013-1-15 9:56:13
 */
@Service
public class SFtpClient implements FileTransfer {

    private ChannelSftp connect(String host, int port, String username, String password) throws JSchException {
        ChannelSftp sftp = null;
        JSch jsch = new JSch();
        jsch.getSession(username, host, port);
        Session sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
        return sftp;
    }

    /**
     * 删除
     * 
     * @param directory
     *            目录
     * @param deleteFile
     *            文件
     * @param sftp
     * @throws SftpException
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) throws SftpException {
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    /**
     * 显示文件
     * 
     * @param directory
     *            目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public List<?> listFiles(String directory, ChannelSftp sftp) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * Upload File
     * 
     * @param conf
     *            传输配置信息
     * @param srcFile
     *            源文件
     * @param targetFile
     *            目标目录
     * @throws IOException
     * 
     * @author yushuanghai,Conan_Z
     */
    @Override
    public void upload(FileTransferConf conf, String srcFile, String targetFolder) throws IOException {
        ChannelSftp sftp = null;
        try {
            sftp = connect(conf.getIp(), conf.getPort(), conf.getUsername(), conf.getPassowrd());
            String fileExt = FileUtil.getFileExt(srcFile);
            if (!fileExt.contains("txt") && !fileExt.contains("csv")) {
                throw new IOException("SFTP Client upload  not supply file type Error!");
            }
            sftp.put(new FileInputStream(new File(srcFile)), targetFolder + FileUtil.getFileName(srcFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("SFTP Client upload Error!", e);
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            if (sftp != null && sftp.isConnected()) {
                try {
                    sftp.getSession().disconnect();
                    sftp.quit();
                    sftp.disconnect();
                } catch (JSchException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Download File
     * 
     * @param conf
     *            传输配置信息
     * @param srcFile
     *            源文件
     * @param targetFile
     *            目标文件
     * @throws IOException
     * 
     * @author yushuanghai,Conan_Z
     */
    @Override
    public void download(FileTransferConf conf, String srcFile, String targetFolder) throws IOException {
        ChannelSftp sftp = null;
        try {
            sftp = connect(conf.getIp(), conf.getPort(), conf.getUsername(), conf.getPassowrd());
            String fileExt = FileUtil.getFileExt(srcFile);
            if (!FileUtil.isASCIIFile(fileExt)) {
                throw new IOException("SFTP Client download  not supply file type Error!");
            }
            sftp.get(srcFile, new FileOutputStream(new File(targetFolder + FileUtil.getFileName(srcFile))));

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("SFTP Client download Error!", e);
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            if (sftp != null && sftp.isConnected()) {
                try {
                    sftp.getSession().disconnect();
                } catch (JSchException e) {
                    e.printStackTrace();
                }
                sftp.quit();
                sftp.disconnect();
            }
        }
    }

    // private static void download(String directory, String downloadFile,
    // String saveDir, String targetFileName, ChannelSftp sftp) throws
    // SftpException, FileNotFoundException {
    // sftp.cd(directory);
    // File file = new File(saveDir + targetFileName);
    // sftp.get(downloadFile, new FileOutputStream(file));
    // }

    // public static void download(String ip,String user,String password,String
    // srcDir,String srcFile,
    // String targetDir,String targetFileName,String fileExt) throws Exception {
    // ChannelSftp sftp = null;
    // try {
    // sftp = connect(ip,port,user,password);
    // if(!fileExt.contains("txt")&&!fileExt.contains("csv")){
    // throw new Exception("SFTP Client download  not supply file type Error!");
    // }
    // download(srcDir,srcFile,targetDir,targetFileName,sftp);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new Exception("SFTP Client download Error!", e);
    // } finally {
    // if (sftp != null && sftp.isConnected()) {
    // sftp.getSession().disconnect();
    // sftp.quit();
    // sftp.disconnect();
    // }
    // }
    // }

}
package org.ape.data.core.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.ape.data.core.util.FileUtil;
import org.springframework.stereotype.Service;

/**
 * 
 * @title ftp 客户端
 * @description
 * @usage
 * @copyright Copyright 2012 Sunshine Insurance Group . All rights reserved.
 * @company Sunshine Insurance Group.
 * @author yushuanghai
 * @version $Id: FtpClient.java,v 1.3 2013-1-15 9:56:13 $
 * @create 2013-1-15 9:56:13
 */
@Service
public class FtpClient implements FileTransfer {

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
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        try {
            ftpClient.connect(conf.getIp(), conf.getPort());
            ftpClient.login(conf.getUsername(), conf.getPassowrd());
            File src = new File(srcFile);
            fis = new FileInputStream(src);
            ftpClient.changeWorkingDirectory(targetFolder);
            ftpClient.setBufferSize(conf.getBufferSize());
            ftpClient.setControlEncoding(conf.getEncoding());
            String fileExt = FileUtil.getFileExt(srcFile);

            if (FileUtil.isASCIIFile(fileExt)) {
                ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
            } else {
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            }

            ftpClient.storeFile(targetFolder + FileUtil.getFileName(srcFile), fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("FTP Client upload Error!", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("FTP Client close Error!", e);
            }
        }
    }

    // public static void upload(String ip, String user, String password, String
    // srcFile, String targetDir, String targetFile, String fileExt) throws
    // Exception {
    // FTPClient ftpClient = new FTPClient();
    // FileInputStream fis = null;
    // try {
    // ftpClient.connect(ip, port);
    // ftpClient.login(user, password);
    // File src = new File(srcFile);
    // fis = new FileInputStream(src);
    // ftpClient.changeWorkingDirectory(targetDir);
    // ftpClient.setBufferSize(1024);
    // ftpClient.setControlEncoding("GBK");
    // if (fileExt.contains("txt") || fileExt.contains("csv")) {
    // ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
    // } else {
    // ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
    // }
    // ftpClient.storeFile(targetFile, fis);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new Exception("FTP Client upload Error!", e);
    // } finally {
    // IOUtils.closeQuietly(fis);
    // try {
    // ftpClient.disconnect();
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new Exception("FTP Client close Error!", e);
    // }
    // }
    // }

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
        FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null;
        try {
            ftpClient.connect(conf.getIp(), conf.getPort());
            ftpClient.login(conf.getUsername(), conf.getPassowrd());
            fos = new FileOutputStream(targetFolder + FileUtil.getFileName(srcFile));
            ftpClient.setBufferSize(conf.getBufferSize());
            String fileExt = FileUtil.getFileExt(srcFile);

            if (FileUtil.isASCIIFile(fileExt)) {
                ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
            } else {
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            }

            ftpClient.retrieveFile(srcFile, fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("FTP Client download Error!", e);
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("FTP Client close Error!", e);
            }
        }
    }
    // public static void main(String[] args){
    // String filePath =
    // "D:\\workspace\\java\\tianji-recommmendation\\core\\src\\main\\java\\com\\tianji\\r\\core\\job\\job.xml";
    // // String filePath =
    // "D:/workspace/java/tianji-recommmendation/core/src/main/java/com/tianji/r/core/job/job.xml";
    // filePath.replaceAll("\\\\$", File.separator);
    // filePath.replaceAll("/$", File.separator);
    // String filename =
    // filePath.substring(filePath.lastIndexOf(File.separator)+1);
    // System.out.println(filename);
    //
    // String fileext = filePath.substring(filePath.lastIndexOf(".")+1);
    // System.out.println(fileext);
    // }

    // public static void download(String ip,String user,String password,String
    // srcFile,
    // String targetFile,String fileExt) throws Exception {
    // FTPClient ftpClient = new FTPClient();
    // FileOutputStream fos = null;
    //
    // try {
    // ftpClient.connect(ip,port);
    // ftpClient.login(user,password);
    // fos = new FileOutputStream(targetFile);
    // ftpClient.setBufferSize(1024);
    // if(fileExt.contains("txt")||fileExt.contains("csv")){
    // ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
    // }else{
    // ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
    // }
    // ftpClient.retrieveFile(srcFile, fos);
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new Exception("FTP Client download Error!", e);
    // } finally {
    // IOUtils.closeQuietly(fos);
    // try {
    // ftpClient.disconnect();
    // } catch (IOException e) {
    // e.printStackTrace();
    // throw new Exception("FTP Client close Error!", e);
    // }
    // }
    // }

}
package org.ape.data.core.util;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
/**
 * 
 * @title 		sftp协议的文件传输
 * @description	
 * @usage		
 * @copyright	Copyright 2012  Sunshine Insurance Group . All rights reserved.
 * @company		Sunshine Insurance Group.
 * @author		yushuanghai
 * @version		$Id: FtpClient.java,v 1.3 2013-1-15 上午9:56:13  $
 * @create		2013-1-15 上午9:56:13
 */
public class SFtpClient{ 

	private final static int port = 22;
	
	
	private static ChannelSftp connect(String host, int port, String username,
			String password) throws JSchException {
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
	* 上传文件
	* @param directory 上传的目录
	* @param uploadFile 要上传的文件
	* @param sftp
	 * @throws SftpException 
	 * @throws FileNotFoundException 
	*/
	private static void upload(String directory,String fileName, String uploadFile, ChannelSftp sftp) throws FileNotFoundException, SftpException {
	    sftp.cd(directory);
	    File file=new File(uploadFile);
	    sftp.put(new FileInputStream(file), fileName);
	}

	/**
	* 下载文件
	* @param directory 下载目录
	* @param downloadFile 下载的文件
	* @param saveFile 存在本地的路径
	* @param sftp
	 * @throws SftpException 
	 * @throws FileNotFoundException 
	*/
	private  static void download(String directory, String downloadFile,String saveDir,String targetFileName, ChannelSftp sftp) throws SftpException, FileNotFoundException {
	    sftp.cd(directory);
	    File file=new File(saveDir+targetFileName);
	    sftp.get(downloadFile, new FileOutputStream(file));
	}

	/**
	* 删除文件
	* @param directory 要删除文件所在目录
	* @param deleteFile 要删除的文件
	* @param sftp
	 * @throws SftpException 
	*/
	public  static void delete(String directory, String deleteFile, ChannelSftp sftp) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}

	/**
	* 列出目录下的文件
	* @param directory 要列出的目录
	* @param sftp
	* @return
	* @throws SftpException
	*/
	public static List listFiles(String directory, ChannelSftp sftp) throws SftpException{
		return sftp.ls(directory);
	}
    /**
     *  
     * 文件上传
     * @param ip 主机ip
     * @param user 用户名
     * @param password 密码
     * @param srcFile 源文件
     * @param targetDir 目标目录
     * @param targetFile 目标文件名
     * @param fileExt 源文件扩展名
     * @throws Exception
     */
    public static void upload(String ip,String user,String password,String srcFile,String targetDir,
    		String targetFile,String fileExt) throws Exception { 
    	ChannelSftp sftp = null;
       try { 
    	  sftp =  connect(ip,port,user,password);
    	 if(!fileExt.contains("txt")&&!fileExt.contains("csv")){
    		 throw new Exception("SFTP Client upload  not supply file type Error！"); 
    	 }
    	 upload(targetDir,targetFile,srcFile,sftp);
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	        throw new Exception("SFTP Client upload Error！", e); 
	    } finally { 
	    	 if (sftp != null && sftp.isConnected()) { 
    		     sftp.getSession().disconnect(); 
    		     sftp.quit(); 
    		     sftp.disconnect(); 
    		   } 
	    }  
    } 

    /**
     * 
     * 文件下载
     * @param ip 主机ip
     * @param user 用户名
     * @param password 密码
     * @param srcFile 源文件
     * @param targetFile 目标文件
     * @param fileExt 源文件扩展名
     * @throws Exception
     */
    public static void download(String ip,String user,String password,String srcDir,String srcFile,
    		String targetDir,String targetFileName,String fileExt) throws Exception { 
    	ChannelSftp sftp = null;
        try { 
     	  sftp =  connect(ip,port,user,password);
     	 if(!fileExt.contains("txt")&&!fileExt.contains("csv")){
     		 throw new Exception("SFTP Client download  not supply file type Error！"); 
     	 }
     	 download(srcDir,srcFile,targetDir,targetFileName,sftp);
 	    } catch (IOException e) { 
 	        e.printStackTrace(); 
 	        throw new Exception("SFTP Client download Error！", e); 
 	    } finally { 
 	    	 if (sftp != null && sftp.isConnected()) { 
     		     sftp.getSession().disconnect(); 
     		     sftp.quit(); 
     		     sftp.disconnect(); 
     		   } 
 	    }  
    } 
} 
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
 * @title 		sftpЭ����ļ�����
 * @description	
 * @usage		
 * @copyright	Copyright 2012  Sunshine Insurance Group . All rights reserved.
 * @company		Sunshine Insurance Group.
 * @author		yushuanghai
 * @version		$Id: FtpClient.java,v 1.3 2013-1-15 ����9:56:13  $
 * @create		2013-1-15 ����9:56:13
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
	* �ϴ��ļ�
	* @param directory �ϴ���Ŀ¼
	* @param uploadFile Ҫ�ϴ����ļ�
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
	* �����ļ�
	* @param directory ����Ŀ¼
	* @param downloadFile ���ص��ļ�
	* @param saveFile ���ڱ��ص�·��
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
	* ɾ���ļ�
	* @param directory Ҫɾ���ļ�����Ŀ¼
	* @param deleteFile Ҫɾ�����ļ�
	* @param sftp
	 * @throws SftpException 
	*/
	public  static void delete(String directory, String deleteFile, ChannelSftp sftp) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}

	/**
	* �г�Ŀ¼�µ��ļ�
	* @param directory Ҫ�г���Ŀ¼
	* @param sftp
	* @return
	* @throws SftpException
	*/
	public static List listFiles(String directory, ChannelSftp sftp) throws SftpException{
		return sftp.ls(directory);
	}
    /**
     *  
     * �ļ��ϴ�
     * @param ip ����ip
     * @param user �û���
     * @param password ����
     * @param srcFile Դ�ļ�
     * @param targetDir Ŀ��Ŀ¼
     * @param targetFile Ŀ���ļ���
     * @param fileExt Դ�ļ���չ��
     * @throws Exception
     */
    public static void upload(String ip,String user,String password,String srcFile,String targetDir,
    		String targetFile,String fileExt) throws Exception { 
    	ChannelSftp sftp = null;
       try { 
    	  sftp =  connect(ip,port,user,password);
    	 if(!fileExt.contains("txt")&&!fileExt.contains("csv")){
    		 throw new Exception("SFTP Client upload  not supply file type Error��"); 
    	 }
    	 upload(targetDir,targetFile,srcFile,sftp);
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	        throw new Exception("SFTP Client upload Error��", e); 
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
     * �ļ�����
     * @param ip ����ip
     * @param user �û���
     * @param password ����
     * @param srcFile Դ�ļ�
     * @param targetFile Ŀ���ļ�
     * @param fileExt Դ�ļ���չ��
     * @throws Exception
     */
    public static void download(String ip,String user,String password,String srcDir,String srcFile,
    		String targetDir,String targetFileName,String fileExt) throws Exception { 
    	ChannelSftp sftp = null;
        try { 
     	  sftp =  connect(ip,port,user,password);
     	 if(!fileExt.contains("txt")&&!fileExt.contains("csv")){
     		 throw new Exception("SFTP Client download  not supply file type Error��"); 
     	 }
     	 download(srcDir,srcFile,targetDir,targetFileName,sftp);
 	    } catch (IOException e) { 
 	        e.printStackTrace(); 
 	        throw new Exception("SFTP Client download Error��", e); 
 	    } finally { 
 	    	 if (sftp != null && sftp.isConnected()) { 
     		     sftp.getSession().disconnect(); 
     		     sftp.quit(); 
     		     sftp.disconnect(); 
     		   } 
 	    }  
    } 
} 
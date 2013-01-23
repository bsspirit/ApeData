package org.ape.data.core.util;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
/**
 * 
 * @title 		ftp 客户端
 * @description	
 * @usage		
 * @copyright	Copyright 2012  Sunshine Insurance Group . All rights reserved.
 * @company		Sunshine Insurance Group.
 * @author		yushuanghai
 * @version		$Id: FtpClient.java,v 1.3 2013-1-15  9:56:13  $
 * @create		2013-1-15  9:56:13
 */
public class FtpClient{ 

	private final static int port = 20;
    /**
     *  
     * 上传文件
     * @param ip  ip
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
        FTPClient ftpClient = new FTPClient(); 
        FileInputStream fis = null; 
        try { 
            ftpClient.connect(ip,port); 
            ftpClient.login(user,password); 
            File src = new File(srcFile); 
            fis = new FileInputStream(src); 
            ftpClient.changeWorkingDirectory(targetDir); 
            ftpClient.setBufferSize(1024); 
            ftpClient.setControlEncoding("GBK"); 
            if(fileExt.contains("txt")||fileExt.contains("csv")){
            	ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE); 
            }else{
            	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            }
            ftpClient.storeFile(targetFile, fis); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new Exception("FTP Client upload Error!", e); 
        } finally { 
            IOUtils.closeQuietly(fis); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new Exception("FTP Client close Error!", e); 
            } 
        } 
    } 

    /**
     * 
     * 下载文件
     * @param ip  ip
     * @param user  用户名
     * @param password 密码
     * @param srcFile 源文件
     * @param targetFile 目标文件
     * @param fileExt 源文件扩展名
     * @throws Exception
     */
    public static void download(String ip,String user,String password,String srcFile,
    		String targetFile,String fileExt) throws Exception { 
        FTPClient ftpClient = new FTPClient(); 
        FileOutputStream fos = null; 

        try { 
            ftpClient.connect(ip,port); 
            ftpClient.login(user,password); 
            fos = new FileOutputStream(targetFile); 
            ftpClient.setBufferSize(1024); 
            if(fileExt.contains("txt")||fileExt.contains("csv")){
            	ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE); 
            }else{
            	ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            }
            ftpClient.retrieveFile(srcFile, fos); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new Exception("FTP Client download Error!", e); 
        } finally { 
            IOUtils.closeQuietly(fos); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new Exception("FTP Client close Error!", e); 
            } 
        } 
    } 
} 
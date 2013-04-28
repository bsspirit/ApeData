package org.ape.data.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;



public class TestUtil{
	
	public static void main(String[] args) {
		 FTPClient ftpClient = new FTPClient(); 
	        FileInputStream fis = null; 
	        try { 
	            ftpClient.connect("127.0.0.1",2221); 
	            ftpClient.login("yushh",""); 
	            File src = new File("D:\\ryxtest.xls"); 
	            fis = new FileInputStream(src); 
	            ftpClient.changeWorkingDirectory("D:\\tmp\\"); 
	            ftpClient.setBufferSize(1024); 
	            ftpClient.setControlEncoding("GBK"); 
	            ftpClient.storeFile("ryxtest2.xls", fis); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } finally { 
	            IOUtils.closeQuietly(fis); 
	            try { 
	                ftpClient.disconnect(); 
	            } catch (IOException e) { 
	                e.printStackTrace(); 
	            } 
	        } 
	}
}

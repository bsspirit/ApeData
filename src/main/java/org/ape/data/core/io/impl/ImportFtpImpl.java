package org.ape.data.core.io.impl;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.httpclient.util.DateUtil;
import org.ape.data.core.io.Import;
import org.ape.data.core.storage.HdfsBaseDao;
import org.ape.data.core.util.FtpClient;
import org.ape.data.core.util.SFtpClient;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 
 * @title 		FTP 导入实现类
 * @description	
 * @usage		
 * @copyright	Copyright 2012  Sunshine Insurance Group . All rights reserved.
 * @company		Sunshine Insurance Group.
 * @author		yushuanghai
 * @version		$Id: exportFtpImpl.java,v 1.3 2013-1-22  11:17:47  $
 * @create		2013-1-22 11:17:47
 */
@Repository
public class ImportFtpImpl implements Import {

	
	@Resource
	private HdfsBaseDao hdfsBaseDao;
	
	@Override
	public void importFromRemoteByFtp(String ip,String userName,String password,
			String src,String hdfsFile,String srcFileExt) throws Exception {
		FtpClient ftp = new FtpClient();
		String tmp = "C:\\tmp\\"+ip+"-"+userName+"-"+DateUtil.formatDate(new Date(), "yyyyMMddHHmmSS")+".txt";
		File f = new File(tmp);
		if(f.exists()){
			f.delete();
		}
		ftp.download(ip, userName, password, src, tmp, srcFileExt);
		hdfsBaseDao.copyFile(tmp, hdfsFile);
		f.delete();
		
	}

	@Override
	public void split() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processStreaming() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeMeta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importFromRemoteBySFtp(String ip, String userName,
			String password, String srcDir,String srcFile, String hdfsFile, String srcFileExt)
			throws Exception {
		SFtpClient sftp = new SFtpClient();
		String targetDir = "C:\\tmp\\";
		String targetFileName =ip+"-"+userName+"-"+DateUtil.formatDate(new Date(), "yyyyMMddHHmmSS")+".txt";
		File f = new File(targetDir+targetFileName);
		if(f.exists()){
			f.delete();
		}
		sftp.download(ip, userName, password, srcDir, srcFile,
				"C:\\tmp\\", targetFileName, srcFileExt);
		hdfsBaseDao.copyFile(targetDir+targetFileName, hdfsFile);
		f.delete();
		
	}

}

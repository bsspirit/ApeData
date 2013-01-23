package org.ape.data.core.io.impl;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.log4j.Logger;
import org.ape.data.core.io.Import;
import org.ape.data.core.storage.HdfsBaseDao;
import org.ape.data.core.transfer.FtpClient;
import org.ape.data.core.transfer.SFtpClient;
import org.springframework.stereotype.Repository;

/**
 * 
 * @title FTP 导入实现类
 * @author yushuanghai
 * @version $Id: exportFtpImpl.java,v 1.3 2013-1-22 11:17:47 $
 * @create 2013-1-22 11:17:47
 */
@Repository
public class ImportFtpImpl implements Import {

    private static final Logger log = Logger.getLogger(ImportFtpImpl.class);

    @Resource
    private HdfsBaseDao hdfsBaseDao;

    @Override
    public void importFromRemoteByFtp(String ip, String userName, String password, String src, String hdfsFile, String srcFileExt) throws Exception {
        FtpClient ftp = new FtpClient();
        String tmp = "C:\\tmp\\" + ip + "-" + userName + "-" + DateUtil.formatDate(new Date(), "yyyyMMddHHmmSS") + ".txt";
        File f = new File(tmp);
        if (f.exists()) {
            f.delete();
        }

        // TODO CONAN
        // ftp.download(ip, userName, password, src, tmp, srcFileExt);
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
    public void importFromRemoteBySFtp(String ip, String userName, String password, String srcDir, String srcFile, String hdfsFile, String srcFileExt) throws Exception {
        SFtpClient sftp = new SFtpClient();
        String targetDir = "C:\\tmp\\";
        String targetFileName = ip + "-" + userName + "-" + DateUtil.formatDate(new Date(), "yyyyMMddHHmmSS") + ".txt";
        File f = new File(targetDir + targetFileName);
        if (f.exists()) {
            f.delete();
        }

        // TODO CONAN
        // sftp.download(ip, userName, password, srcDir, srcFile, "C:\\tmp\\",
        // targetFileName, srcFileExt);
        hdfsBaseDao.copyFile(targetDir + targetFileName, hdfsFile);
        f.delete();

    }

}

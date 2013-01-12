package org.ape.data.core.storage;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.data.hadoop.fs.FileSystemFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class HdfsBaseDao {

    private static final Log log = LogFactory.getLog(HdfsBaseDao.class);
    @Resource
    private     	FileSystemFactoryBean fsFactory;
    
    public void mkdir(String folder) throws Exception {
        Path path = new Path(folder);
        FileSystem fs = fsFactory.getObject();
        if (!fs.exists(path)) {
            fs.mkdirs(path);
            log.info("Created: " + folder+" successed!");
        }	
        fs.close();
    }

    public void rmr(String folder) throws Exception {
        Path path = new Path(folder);
        FileSystem fs = fsFactory.getObject();
        fs.deleteOnExit(path);
        log.info("Deleted: " + folder+" successed!");
        fs.close();
    }

    public void ls(String folder) throws Exception {
        Path path = new Path(folder);
        FileSystem fs = fsFactory.getObject();
        FileStatus[] list = fs.listStatus(path);
        log.info("ls: " + folder);
        for (FileStatus f : list) {
            log.info("name: "+f.getPath()+", folder: "+f.isDir()+", size: "+f.getLen());
        }
        fs.close();
    }

    public void createFile(String file, String content) throws Exception {
        FileSystem fs = fsFactory.getObject();
        byte[] buff = content.getBytes();
        FSDataOutputStream os = null;
        try {
            os = fs.create(new Path(file));
            os.write(buff, 0, buff.length);
            log.info("Created: " + file + "successed!");
        } finally {
            if (os != null)
                os.close();
        }
        fs.close();
    }

    public void copyFile(String local, String remote) throws Exception {
        FileSystem fs = fsFactory.getObject();
        fs.copyFromLocalFile(new Path(local), new Path(remote));
        log.info("copyed from: " + local + " to " + remote);
        fs.close();
    }

    public void upload(String local, String remote) throws Exception {
        FileSystem fs = fsFactory.getObject();
        fs.copyFromLocalFile(new Path(local), new Path(remote));
        log.info("uploaded: from " + local + " to " + remote);
        fs.close();
    }

    public void download(String remote, String local) throws Exception {
        Path path = new Path(remote);
        FileSystem fs = fsFactory.getObject();
        fs.copyToLocalFile(path, new Path(local));
        log.info("downloaded: from " + remote + " to " + local);
        fs.close();
    }

    public List<String> location(String uri) throws Exception {
     	List<String> result = new ArrayList<String>();
      	FileSystem fs = fsFactory.getObject();
    	    FileStatus f = fs.getFileStatus(new Path(uri));
        BlockLocation[] list = fs.getFileBlockLocations(f, 0, f.getLen());
        log.info("File Location: " + uri);
        for (BlockLocation bl : list) {
        String[] hosts = bl.getHosts();
        for (String host : hosts) {
	        	if(!result.contains(host)){
	        		result.add(host);
		        	log.info("host:" + host);
	        	}
         }
        }
        fs.close();
        return result;
    }
}
package org.ape.data.core.io;

/**
 * 
 * @author Conan_Z
 * @data 2012-12-19
 */
public interface Import {
    
    void split();
    void processStreaming();
    void storeMeta();
    void storeInfo();
	void importFromRemoteByFtp(String ip, String userName, String password,
			String src, String hdfsFile, String srcFileExt) throws Exception;
	void importFromRemoteBySFtp(String ip, String userName, String password,
			String srcDir, String srcFile, String hdfsFile, String srcFileExt)
			throws Exception;
    
}

package org.ape.data.core.transfer;

import java.io.IOException;

/**
 * File Transfer Interface
 * 
 * @author Conan_Z
 * @data 2013-1-23
 */
public interface FileTransfer {

    /**
     * Download File
     * 
     * @param conf
     * @param srcFile
     * @param targetFolder
     * @throws IOException
     */
    public void download(FileTransferConf conf, String srcFile, String targetFolder) throws IOException;

    /**
     * Upload File
     * 
     * @param conf
     * @param srcFile
     * @param targetFolder
     * @throws IOException
     */
    public void upload(FileTransferConf conf, String srcFile, String targetFolder) throws IOException;

}

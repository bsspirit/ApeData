package org.ape.data.core.util;

/**
 * 
 * @author Conan_Z
 * @data 2012-12-19
 */
public interface Import {
    
    void importFromRemoteByFtp();
    void split();
    void processStreaming();
    void storeMeta();
    void storeInfo();
    
}

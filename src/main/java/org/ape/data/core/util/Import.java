package org.ape.data.core.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.ape.data.core.io.model.MetaInfo;

/**
 * 
 * @author Conan_Z
 * @data 2012-12-19
 */
public interface Import {
    
    void importFromRemoteByFtp();
    void split();
    void processStreaming();
    MetaInfo storeMeta(String userName,String projName,String tableName,String url,String type) throws IOException;
    void storeInfo(String url,MetaInfo infol) throws IOException;
    
}

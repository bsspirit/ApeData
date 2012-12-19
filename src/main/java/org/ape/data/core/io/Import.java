package org.ape.data.core.io;

/**
 * 
 * @author Conan_Z
 * @data 2012-12-19
 */
public interface Import {
    
    void scale();
    void split();
    void processStreaming();
    void storeMeta();
    void storeInfo();
    
}

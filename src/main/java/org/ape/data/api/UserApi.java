package org.ape.data.api;

/**
 * 
 * @author Conan_Z
 * @data 2012-12-19
 */
public interface UserApi {

    void download(int id, int uid);
    void upload(int uid, Object obj);
    void status(int uid);

}

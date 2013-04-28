package org.ape.data.client;

import org.ape.data.core.io.model.UserMetaInfo;

/**
 * 
 * @author Conan_Z
 * @data 2012-12-19
 */
public interface UserApi {

	UserMetaInfo importCsv(UserMetaInfo umi);
    UserMetaInfo getUserMetaInfo(String userName,String projectName,String tableName);

}

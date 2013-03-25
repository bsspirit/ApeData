package org.ape.data.core.io.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.ape.data.base.BaseTestCase;
import org.ape.data.core.io.model.Column;
import org.ape.data.core.io.model.MetaInfo;
import org.ape.data.core.storage.MetaStore;
import org.ape.data.core.storage.MySqlBaseDao;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class CSVImporterTest extends BaseTestCase{
	
	
	@Resource
	private CSVImporter csvImporter;
	
	@Resource
	private MetaStore metaStore;


	@Test
	@Rollback(value=false)
	public void testStoreInfo() throws IOException {
		String sql  = "create table t_col(xuhao VARCHAR(50),gongsi VARCHAR(50),diqu VARCHAR(50),shuzi1 INT,shuzi2 INT,shuzi3 INT,shuzi4 INT,riqi TIMESTAMP,flag CHAR,url VARCHAR(5000))";
		MetaInfo mi = csvImporter.storeMeta("yushh", "test",sql, "user.csv", "hadoop");
		csvImporter.storeInfo("user.csv", mi);
	}
//	@Test
//	@Rollback(value=false)
//	public void testGetStoreInfo() throws IOException {
//		MetaInfo mi = metaStore.getMetaInfo("yushh", "test","t_col");
//	}

}

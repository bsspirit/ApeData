package org.ape.data.core.io.impl;


import java.io.IOException;

import javax.annotation.Resource;

import org.ape.data.base.BaseTestCase;
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


//	@Test
//	@Rollback(value=false)
//	public void testStoreInfo() throws IOException {
//		MetaInfo mi = csvImporter.storeMeta("yushh", "test","t_col", "user.csv", "hadoop");
//		csvImporter.storeInfo("user.csv", mi);
//	}
//	@Test
//	@Rollback(value=false)
//	public void testGetStoreInfo() throws IOException {
//		MetaInfo mi = metaStore.getMetaInfo("yushh", "test","t_col");
//	}


}

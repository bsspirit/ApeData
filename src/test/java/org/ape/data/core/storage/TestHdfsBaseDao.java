package org.ape.data.core.storage;

import java.io.IOException;

import javax.annotation.Resource;

import org.ape.data.base.BaseTestCase;
import org.ape.data.core.storage.MySqlBaseDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


public class TestHdfsBaseDao extends BaseTestCase{
	
	@Resource
	private HdfsBaseDao hdfsBaseDao;
	
//	@Test
//	@Rollback(value=false)
//	public void testFs() throws Exception{
//		hdfsBaseDao.ls("/");
//	}
	
	
//	@Test
//	@Rollback(value=false)
//	public void testRmr() throws Exception{
//		hdfsBaseDao.rmr("/user/yushh/test");
//	}
	
	
//	@Test
//	@Rollback(value=false)
//	public void testMkdir() throws Exception{
//		hdfsBaseDao.mkdir("/user/yushh/test");
//	}
	
	
	@Test
	@Rollback(value=false)
	public void testMkdir() throws Exception{
		hdfsBaseDao.mkdir("/user/yushh/test");
	}
	
	

}

package org.ape.data.core.storage;

import javax.annotation.Resource;

import org.ape.data.base.BaseTestCase;
import org.ape.data.core.storage.MySqlBaseDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


public class TestMySqlBaseDao extends BaseTestCase{
	
	@Resource
	private MySqlBaseDao mySqlBaseDao;
	
	@Test
	@Rollback(value=false)
	public void testInsert(){
		mySqlBaseDao.insert("insert into t values(3,'yushh2')");
	}

}

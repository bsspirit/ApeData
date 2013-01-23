package org.ape.data.core.io.impl;

import javax.annotation.Resource;


import org.ape.data.base.BaseTestCase;
import org.ape.data.core.io.Import;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;


public class ImportFtpImplTest extends BaseTestCase{
	
	@Resource
	private Import importFtpImpl;

	@Test
	@Rollback(value=false)
	public void testImportFromRemoteByFtp() throws Exception{
		importFtpImpl.importFromRemoteBySFtp("42.121.108.236", 
				"test", "testape", "/home/test/","test.txt", "/user/yushh/test.txt", ".txt");
	}
	
}

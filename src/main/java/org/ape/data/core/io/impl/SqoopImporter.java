package org.ape.data.core.io.impl;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.ape.data.core.io.model.Cell;
import org.ape.data.core.io.model.Column;
import org.ape.data.core.io.model.TableMetaInfo;
import org.ape.data.core.io.model.Row;
import org.ape.data.core.storage.MetaStore;
import org.ape.data.core.storage.MySqlBaseDao;
import org.ape.data.core.util.Import;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;

@Service
public class SqoopImporter implements Import{
	
	@Resource
	private MySqlBaseDao dao;
	
	@Resource
	private MetaStore metaStore;

	@Override
	public void importFromRemoteByFtp() {
		
	}

	@Override
	public void split() {
		
		
	}

	@Override
	public void processStreaming() {
		
	}
	
	

	@Override
	public void storeInfo(String url,TableMetaInfo info) throws IOException {
		//从关系型数据库导入sqoop中
//		sqoop import --connect jdbc:mysql://mysqlserver_IP/databaseName --table testtable -m 1 
	}

	@Override
	public TableMetaInfo storeMeta(String userName, String projName,
			String tableName, String url, String type) throws IOException {
		return null;
	}
	

}

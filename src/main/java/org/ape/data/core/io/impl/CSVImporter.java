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
import org.ape.data.core.io.model.MetaInfo;
import org.ape.data.core.io.model.Row;
import org.ape.data.core.storage.MetaStore;
import org.ape.data.core.storage.MySqlBaseDao;
import org.ape.data.core.util.Import;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;

@Service
public class CSVImporter implements Import{
	
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
	public  MetaInfo storeMeta(String userName,String projName,String sql,String url,String type) throws IOException {
		MetaInfo mi = new MetaInfo();
		sql =sql.toUpperCase();
		String tableName =sql.substring(sql.indexOf("TABLE")+5, sql.indexOf("(")).trim();
		mi.setTableName("t_"+userName+"_"+projName+"_"+tableName);
		
		List<Column> cols = new ArrayList<Column>();
		String s = sql.substring(sql.indexOf("(")+1, sql.lastIndexOf(")"));
		String[] colt = s.split(",");
		for(int j=0;j<colt.length;j++){
			String[] col = colt[j].split("\\s+");
			Column c = new Column();
			c.setId(j+1);
			c.setColumnName(col[0]);
			c.setColumnType(col[1]);
			cols.add(c);
		}
		mi.setColumns(cols);
		metaStore.storeMetaData(userName, projName,tableName, mi, type);
		return mi;
	}

	@Override
	public void storeInfo(String url,MetaInfo info) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(url));
        String [] nextLine;
        StringBuffer sql = new StringBuffer("insert into "+info.getTableName()+"(");
        Map map = new HashMap();
        for (Column c:info.getColumns()){
        	map.put(c.getId(), c);
	   	}
        for(int i=1;i<=info.getColumns().size();i++){
        	sql.append(((Column)(map.get(i))).getColumnName()+",");
        }
        sql = new StringBuffer(sql.substring(0, sql.length()-1)+") values(");
        for(int i=0;i<info.getColumns().size();i++){
        	sql.append("?,");
        }
        String sqlFinal = sql.substring(0, sql.length()-1)+")";
        List list = new ArrayList<Row>();
        int i=0;
       
        while ((nextLine = reader.readNext()) != null) {
        	if(i==0){
        		i++;
        		continue;//忽略首行
        	}
        	Row r = new Row();
        	r.setOrder(i);
        	List<Cell> cells = new ArrayList<Cell>(info.getColumns().size());
        	for(int b=0;b<nextLine.length;b++){
        		Cell c = new Cell();
        		c.setContent(nextLine[b]);
        		c.setColumn((Column)map.get(b+1));
        		cells.add(c);
        	}
        	r.setCells(cells);
        	list.add(r);
        	i++;
        }
		dao.batchUpdateLinkset(sqlFinal, list);
	}
	

}

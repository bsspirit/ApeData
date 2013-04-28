package org.ape.data.core.storage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ape.data.core.io.model.Column;
import org.ape.data.core.io.model.TableMetaInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetaStore {
	
	@Resource
	private MySqlBaseDao dao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public  void storeMetaData(String userName,String projName,String tableName,TableMetaInfo mi,String type){
		createUserTable(mi);
		StringBuffer sb = new StringBuffer("insert into META_SCHEMA(USERNAME,PROJECT,TABLENAME,COLOM,TYPE) values('"+userName+"','"+projName+"','"+tableName+"','");
	   	for (Column c:mi.getColumns()){ 
	   		   sb.append(c.getId()+" "+c.getColumnName()+" "+c.getColumnType()+",");
	   	} 
	   	String sql = sb.substring(0, sb.length()-1)+"','"+type+"')";
		dao.update(sql);
	}
	

    public  void createUserTable(TableMetaInfo mi){
    	StringBuffer sb = new StringBuffer("create table "+mi.getTableName()+"( id bigint auto_increment not null primary key,");
    	for (Column c:mi.getColumns()){ 
	   		   sb.append(c.getColumnName()+" "+c.getColumnType()+",");
	   	}   
	    String sql = sb.substring(0, sb.length()-1)+")";
	    dao.update(sql);
	}
    
    public TableMetaInfo getMetaInfo(String userName,String projName,String tableName){
    	Map map =  dao.getObject("select * from META_SCHEMA where USERNAME='"+userName+"' and PROJECT='"+projName+"' and TABLENAME='"+tableName+"'");
    	TableMetaInfo mi = new TableMetaInfo();
//    	{ID=26, USERNAME=yushh, PROJECT=test, TABLENAME=t_col, TYPE=hadoop, 
//    			COLOM=1 xuhao VARCHAR(50),2 gongsi VARCHAR(50),3 diqu VARCHAR(50),
//    			4 shuzi1 INT,5 shuzi2 INT,6 shuzi3 INT,7 shuzi4 INT,8 riqi TIMESTAMP,
//    			9 flag CHAR,10 url VARCHAR(5000), CREATETIME=2013-03-06 17:07:25.0}
    	mi.setTableName(map.get("USERNAME")+"_"+map.get("PROJECT")+"_"+map.get("TABLENAME"));
    	List<Column> columns = new ArrayList<Column>();
    	String col = (String) map.get("COLOM");
    	String[] cols = col.split(",");
    	for(int i=0;i<cols.length;i++){
    		Column c = new Column();
    		String[] colss = cols[i].split(" ");
        		c.setId(Integer.valueOf(colss[0]));
        		c.setColumnName(colss[1]);
        		c.setColumnType(colss[2]);
        		columns.add(c);
    	}
    	mi.setColumns(columns);
    	mi.setCreateTime((Timestamp) map.get("CREATETIME"));
		return mi;
    }

}

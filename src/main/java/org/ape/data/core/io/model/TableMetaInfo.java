package org.ape.data.core.io.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @title 		表元信息
 * @description	
 * @usage		
 * @author		yushuanghai
 */
public class TableMetaInfo {
	
	private String tableName;
	private List<Column> columns;
	private Date createTime;
	private String sql;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
}

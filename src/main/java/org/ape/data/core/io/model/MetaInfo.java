package org.ape.data.core.io.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @title 		元信息
 * @description	
 * @usage		
 * @author		yushuanghai
 * @version		$Id: MetaInfo.java,v 1.3 2013-3-5 上午11:07:29  $
 * @create		2013-3-5 上午11:07:29
 */
public class MetaInfo {
	
	private String tableName;
	private List<Column> columns;
	private Date createTime;
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
	
	
}

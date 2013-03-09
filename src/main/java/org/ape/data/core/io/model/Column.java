package org.ape.data.core.io.model;

/**
 * 
 * @title 		
 * @description	标示元数据中列
 * @usage		
 * @author		yushuanghai
 * @version		$Id: Column.java,v 1.3 2013-3-6 下午3:23:24  $
 * @create		2013-3-6 下午3:23:24
 */
public class Column {
	
	private int id;
	
	private String columnName;
	
	private String columnType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
	

}

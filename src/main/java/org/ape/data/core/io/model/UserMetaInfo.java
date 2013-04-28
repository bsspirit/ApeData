package org.ape.data.core.io.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @title 		用户元信息
 * @description	
 * @usage		
 * @author		yushuanghai
 */
public class UserMetaInfo implements Serializable{

	
	private static final long serialVersionUID = -2969997705452739552L;
	
	private String userName;
	
	private String proName;
	
	private List<CsvMetaInfo> csvInfo;
	
	private List<TableMetaInfo> tableInfo;
	
	private List<OperationMetaInfo> operationInfo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public List<CsvMetaInfo> getCsvInfo() {
		return csvInfo;
	}

	public void setCsvInfo(List<CsvMetaInfo> csvInfo) {
		this.csvInfo = csvInfo;
	}

	public List<TableMetaInfo> getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(List<TableMetaInfo> tableInfo) {
		this.tableInfo = tableInfo;
	}

	public List<OperationMetaInfo> getOperationInfo() {
		return operationInfo;
	}

	public void setOperationInfo(List<OperationMetaInfo> operationInfo) {
		this.operationInfo = operationInfo;
	}
	
	
	
	

}

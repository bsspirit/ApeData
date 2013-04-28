package org.ape.data.core.io.model;

import java.io.Serializable;

/**
 * 
 * @title 		操作元信息
 * @description	
 * @usage		
 * @author		yushuanghai
 */
public class OperationMetaInfo implements Serializable{

	
	private static final long serialVersionUID = -2153641294164343747L;
	
	private String importType;
	
	private String loadType;
	
	private String dealType;

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getLoadType() {
		return loadType;
	}

	public void setLoadType(String loadType) {
		this.loadType = loadType;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
	
	

}

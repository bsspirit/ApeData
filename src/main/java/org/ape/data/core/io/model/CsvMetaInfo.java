package org.ape.data.core.io.model;

import java.io.Serializable;

import org.ape.data.core.io.ImportFileType;

/**
 * 
 * @title 		
 * @description	
 * @usage		
 * @author		yushuanghai
 */
public class CsvMetaInfo implements Serializable,ImportFileType{
	
	private static final long serialVersionUID = -3543644746405706897L;

	private String name;
	
	private boolean isLocal;
	
	private String localUrl;
	
	private String remoteUrl;
	
	private Long size;
	
	private Long downLoadSize;
	
	private boolean isLoadFinish;
	
	private Long splitSize;
	
	private Long split;
	
	private boolean isSplit;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}


	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public boolean isLoadFinish() {
		return isLoadFinish;
	}

	public void setLoadFinish(boolean isLoadFinish) {
		this.isLoadFinish = isLoadFinish;
	}

	public Long getDownLoadSize() {
		return downLoadSize;
	}

	public void setDownLoadSize(Long downLoadSize) {
		this.downLoadSize = downLoadSize;
	}

	public Long getSplitSize() {
		return splitSize;
	}

	public void setSplitSize(Long splitSize) {
		this.splitSize = splitSize;
	}

	public Long getSplit() {
		return split;
	}

	public void setSplit(Long split) {
		this.split = split;
	}

	public boolean isSplit() {
		return isSplit;
	}

	public void setSplit(boolean isSplit) {
		this.isSplit = isSplit;
	}
	
	
	

}

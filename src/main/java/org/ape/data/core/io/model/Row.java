package org.ape.data.core.io.model;

import java.util.List;

/**
 * 
 * @title 		行信息
 * @description	
 * @usage		
 * @author		yushuanghai
 * @version		$Id: MetaInfo.java,v 1.3 2013-3-5 上午11:07:29  $
 * @create		2013-3-5 上午11:07:29
 */
public class Row {
	
	private int order;
	private List<Cell> cells;
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public List<Cell> getCells() {
		return cells;
	}
	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
	

}

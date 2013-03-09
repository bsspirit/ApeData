package org.ape.data.core.io.model;
/**
 * 
 * @title 		
 * @description	
 * @usage		基本行
 * @author		yushuanghai
 * @version		$Id: Cell.java,v 1.3 2013-3-6 下午3:56:02  $
 * @create		2013-3-6 下午3:56:02
 */
public class Cell {
	
	private Column column;
	private Object content;
	
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	

	
}

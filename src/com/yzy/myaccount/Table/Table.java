package com.yzy.myaccount.Table;

import java.util.List;

/**
 * @author YZY
 *
 */
public class Table {

	//标题数组
	public String[] titleStrings;
	//类容列表数组
	public List<String[]> contents;
	//单元格类型数组
	public int[] type;
	//单元格宽度数组
	public int[] cellWidth;
	//行高度数组(索引0标题，1内容列表)
	public int[] rowHeight;
	//行大小数组(索引0标题，1内容列表)
	public int[] rowTextSize;
	//行文字颜色数组(索引0标题，1内容列表)
	public int rowTextColor;
	//表格资源数组(索引0标题背景，1内容列表背景，2表格背景)
	/**
	 * 
	 */
	public int[] tableResource;
	/**
	 * 表格对象无参构造
	 */
	public Table()
	{
		super();
	}
	/**
	 * 
	 * @param titleStrings
	 * @param contents
	 * @param type
	 * @param cellWidth
	 * @param rowHeight
	 * @param rowTextSize
	 * @param rowTextColor
	 * @param tableResource
	 */
	public Table(String[] titleStrings,List<String[]> contents, int[] type, int[] cellWidth,
			int[] rowHeight, int[] rowTextSize, int rowTextColor,
			int[] tableResource) {
		super();
		this.titleStrings = titleStrings;
		this.contents=contents;
		this.type = type;
		this.cellWidth = cellWidth;
		this.rowHeight = rowHeight;
		this.rowTextSize = rowTextSize;
		this.rowTextColor = rowTextColor;
		this.tableResource = tableResource;
	}
	
}

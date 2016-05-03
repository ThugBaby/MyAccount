package com.yzy.myaccount.Table;

import javax.swing.text.TableView.TableCell;

import android.R.integer;

public class TableRow {

	//行的所有列
	public TableCell[] cells;
	//是否为标题
	public boolean isTitle;
	//行高
	public int height;
	//该行单元格的背景
	public int backgroundResource;
	//字体大小
	public int textSize;
	//字体颜色
	public int textColor;
	/**
	 * 表格一行的构造
	 * @param cells 行的所有列
	 * @param height 行高
	 * @param isTitle 是否是标题
	 * @param textSize 字体大小
	 * @param textColor 字体颜色
	 * @param backgroundResource 该行单元格的背景
	 */
	public TableRow(TableCell[] cells,int height,boolean isTitle,int textSize,int textColor,int backgroundResource)
	{
		this.cells=cells;
		this.isTitle=isTitle;
		this.height=height;
		this.textColor=textColor;
		this.textSize=textSize;
		this.backgroundResource=backgroundResource;
	}
	/**
	 * 行中的单元格数 
	 */
	public int getCellSize()
	{
		return cells.length;
		
	}
	/**
	 * 根据列索引返回列的值
	 * @param index
	 * @return
	 */
	public TableCell getCellValue(int index)
	{
		if(index>=cells.length)
		{
			return null;
		}
		return cells[index];
	}
	
}


package com.yzy.myaccount.Table;




import javax.swing.text.TableView;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;



public class TableItemView extends LinearLayout {

	private Context mContext;
	//该行单元格数量
	private int cellCount;
	//改行单元格文本数组
	private TextView[] rowcell;
	/**
	 * 创建一行的View
	 * @param context Context
	 * @param tableRow 行数据
	 */
	public TableItemView(Context context, TableRow tableRow)
	{
		super(context);
		mContext=context;
		//水平排列
		this.setOrientation(LinearLayout.HORIZONTAL);
		//初始化行中列的数量
		cellCount=tableRow.getCellSize();
		//初始化改行单元格文本View数组
		rowcell=new TextView[cellCount];
		//将单元格逐个添加到行
		LinearLayout.LayoutParams layoutParams=null;
		for(int i=0;i<cellCount;i++)
		{
			TableView.TableCell tableCell=tableRow.getCellValue(i);
			//按照单元格指定的大小设置空间
			layoutParams=new LinearLayout.LayoutParams(layoutParams.FILL_PARENT,tableRow.height);
			//if(tableCell.==CellType.STRING)
			{
				
				
			}
		}
		
	}
}

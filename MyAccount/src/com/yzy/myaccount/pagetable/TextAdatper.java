package com.yzy.myaccount.pagetable;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class TextAdatper extends BaseAdapter {
	private List<String> fields;
	private Context context;
	private int textcolor;
	private float textSize;
	private int textStyle;
	int myGalleryBackGroud;
	
	
	public TextAdatper(Context context, List<String> fields,
			int textcolor, float textSize, int textStyle) {
		this.context = context;
		this.fields = fields;
		this.textcolor = textcolor;
		this.textSize = textSize;
		this.textStyle = textStyle;
		
	}
	
	public TextAdatper(Context context, List<String> fields) {
//		new TextAdatper(context, fields, Color.WHITE, 16.0f, Typeface.ITALIC);
		this.context = context;
		this.fields = fields;
		this.textcolor = Color.BLACK;
		this.textSize = 20.0f;
		this.textStyle = Typeface.NORMAL;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fields.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return fields.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//字段名称
		TextView tv_field = new TextView(context);
		tv_field.setText(fields.get(position));
		tv_field.setTextSize(textSize);
		tv_field.setTextColor(textcolor);
		tv_field.setTypeface(null, textStyle);
		tv_field.setSingleLine();
		return tv_field;
	}

}

package com.yzy.myaccount.fragment;
import com.yzy.myaccount.R;
import com.yzy.myaccount.activity.GestureEditActivity;
import com.yzy.myaccount.dao.Tb_pwdDao;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;
/**
 * 
 * @author wuwenjie
 * @description 今日
 */
public class SetFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_set, null);
		View v=view.findViewById(R.id.tvpwd);
		final Tb_pwdDao pwdDao=new Tb_pwdDao(getActivity());
		v.setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
//				final SharedPreferences sp=getActivity().getSharedPreferences("password",getActivity().MODE_PRIVATE);
//				 String pwdString=sp.getString("pwd","");
				//这里使用数据库中的密码
				String pwdString=null;
				
				if(pwdDao.find()!=null)
				{
					pwdString=pwdDao.find().getPassword();
				}
				if(pwdString==null||pwdString.equals(""))
				{
					Intent intent =new Intent(getActivity(),GestureEditActivity.class);
					startActivity(intent);
				}
				else {
					//创建对话框
					  AlertDialog alertDialog=new AlertDialog.Builder(getActivity()).create();
					  alertDialog.setTitle("提示");
					  alertDialog.setMessage("确定要取消密码吗？");
					  alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
//							 Editor editor=sp.edit();
//							editor.clear();
//							editor.commit();
//							dialog.dismiss();
							pwdDao.delete();
							Toast.makeText(getActivity(),"清除成功！",Toast.LENGTH_SHORT).show();;
						}
					} );
					  alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						} );
					alertDialog.show();
					
				}
			}
		});
		return view;
	}
	
	
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}

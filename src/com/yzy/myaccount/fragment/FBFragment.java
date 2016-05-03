package com.yzy.myaccount.fragment;

import javax.mail.MessagingException;

import com.yzy.myaccount.R;
import com.yzy.myaccount.util.MailUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FBFragment extends Fragment {
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
		View view = inflater.inflate(R.layout.frag_fb, null);
		final TextView tv1=(TextView)view.findViewById(R.id.opinion);
		final TextView tv2=(TextView)view.findViewById(R.id.contact);
		Button bt=(Button)view.findViewById(R.id.send);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					MailUtil.snedMail("意见反馈",(String)(tv1.getText()+"\n联系方式:"+tv2.getText()));
					Toast.makeText(getActivity(), "发送ok",
	                        Toast.LENGTH_LONG).show();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getActivity(), "发送失败",
	                        Toast.LENGTH_LONG).show();
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

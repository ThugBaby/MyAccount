package com.yzy.myaccount.fragment;

import com.yzy.myaccount.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyHelpFragment extends Fragment {
	private WebView myWebView;
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
		View view = inflater.inflate(R.layout.frag_help, null);
		
		myWebView=(WebView)view.findViewById(R.id.Toweb1);
		 myWebView.loadUrl("http://yzy.hgfree.kuxier.club/apk/help.html");  
	     //加载本地中的html  
	     //myWebView.loadUrl("file:///android_asset/www/test2.html");  
	     //加上下面这段代码可以使网页中的链接不以浏览器的方式打开  
	     myWebView.setWebViewClient(new WebViewClient());  
	     //得到webview设置  
	     WebSettings webSettings = myWebView.getSettings();    
	     //允许使用javascript  
	     webSettings.setJavaScriptEnabled(true);  
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

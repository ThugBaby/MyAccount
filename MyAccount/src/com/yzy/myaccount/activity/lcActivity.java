package com.yzy.myaccount.activity;

import com.yzy.myaccount.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class lcActivity extends Activity {
    private WebView myWebView ;
    private ImageView imageView;
 public void onCreate(Bundle savedInstanceState)
 {
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.webview);
//	 ActionBar bar = getActionBar(); // 获取ActionBar的对象，从这个方法也可知action
//		// bar是activity的一个属性
//		bar.setDisplayHomeAsUpEnabled(true);// 显示返回的箭头，并可通过onOptionsItemSelected()进行监听，其资源ID为android.R.id.home
	  //获取webview控件  
     myWebView = (WebView) findViewById(R.id.Toweb);  
     imageView=(ImageView)findViewById(R.id.topback);
       imageView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
     //加载服务器上的页面  
     myWebView.loadUrl("http://m.hexun.com");//http://ftp520855.host510.zhujiwu.cn/  
     //加载本地中的html  
     //myWebView.loadUrl("file:///android_asset/www/test2.html");  
     //加上下面这段代码可以使网页中的链接不以浏览器的方式打开  
     myWebView.setWebViewClient(new WebViewClient());  
     //得到webview设置  
     WebSettings webSettings = myWebView.getSettings();  
   //设置加载进来的页面自适应手机屏幕 
     webSettings.setUseWideViewPort(true); 
     webSettings.setLoadWithOverviewMode(true); 
//    第一个方法设置webview推荐使用的窗口，设置为true。第二个方法是设置webview加载的页面的模式，也设置为true。
     //允许使用javascript  
     webSettings.setJavaScriptEnabled(true);  
     //将WebAppInterface于javascript绑定  
     //myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");         
 }     
 @Override  
 public boolean onKeyDown(int keyCode, KeyEvent event) {  
     // Check if the key event was the Back button and if there's history  
     if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {  
         myWebView.goBack();  
         return true;  
     }  
     return super.onKeyDown(keyCode, event);  
 }  
}

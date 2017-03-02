package com.example.judge1;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MyRatings extends Activity {
	
	WebView wv;
	int judge_id=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.myratings);
		this.wv=(WebView) this.findViewById(R.id.webView1);
		
		String ipadd=Sessions.getIpAddress();
		String url="http:"+ipadd+"/hackaton/getmyratings.php?judge_id="+judge_id;
	
		this.wv.loadUrl(url);
		
	}
		
}

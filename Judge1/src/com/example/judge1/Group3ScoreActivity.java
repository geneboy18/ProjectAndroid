package com.example.judge1;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Group3ScoreActivity  extends Activity implements  OnClickListener {

	EditText txtC1,txtC2,txtC3;
	Button btnSend;
	int judge_id=1;
	int group_id=3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.group3_score);
		
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		this.txtC1=(EditText) this.findViewById(R.id.editText1);
		this.txtC2=(EditText) this.findViewById(R.id.editText2);
		this.txtC3=(EditText) this.findViewById(R.id.editText3);
		
		this.btnSend=(Button) this.findViewById(R.id.button1);
		
		this.btnSend.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		String ipadd=Sessions.getIpAddress();
		
		String c1=this.txtC1.getText().toString();
		String c2=this.txtC2.getText().toString();
		String c3=this.txtC3.getText().toString();
		
		if(Integer.parseInt(c1)<0 || Integer.parseInt(c1)>40){
			txtC1.setError("0 to 40 Only");
			txtC1.requestFocus();
		}
		else	if(Integer.parseInt(c2)<0 || Integer.parseInt(c2)>35){
			txtC2.setError("0 to 35 Only");
			txtC2.requestFocus();
		}
		else 	if(Integer.parseInt(c3)<0 || Integer.parseInt(c3)>25){
			txtC3.setError("0 to 25 Only");
			txtC3.requestFocus();
		}
		else {
			try {
				URL url=new URL("http://"+ipadd+"/hackaton/acceptscore.php?group_id="+group_id+"&judge_id="+judge_id+"&c1="+c1+"&c2="+c2+"&c3="+c3);					
				HttpURLConnection conn=(HttpURLConnection) url.openConnection();
				InputStream is=conn.getInputStream();
				int c=0;
				StringBuffer sb=new StringBuffer();
				while((c=is.read())!=-1){
					sb.append((char)c);
				}
				is.close();
				conn.disconnect();
				Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
}

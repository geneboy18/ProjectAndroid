package com.example.myfinalquestions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ViewStudent extends Activity implements OnClickListener {

	TextView txtQ, txtA, txtB, txtC, txtD, txtQs;
	RadioGroup rdoG;
	Button btnSubmit, btnCancel;
	String idno, quesNo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stundentinfo);
		
		this.txtQ = (TextView) this.findViewById(R.id.textView1);
		this.txtA = (TextView) this.findViewById(R.id.textView3);
		this.txtB = (TextView) this.findViewById(R.id.textView7);
		this.txtC = (TextView) this.findViewById(R.id.textView5);
		this.txtD = (TextView) this.findViewById(R.id.textView9);
		this.txtQs = (TextView) this.findViewById(R.id.textView10);
		this.rdoG = (RadioGroup) this.findViewById(R.id.radioGroup1);
		
		Bundle b = this.getIntent().getExtras();
		idno = b.getString("idno");
		quesNo = b.getString("idquestion");
		String question = b.getString("question");
		String a = b.getString("a");
		String be = b.getString("b");
		String ce = b.getString("c");
		String de = b.getString("d");
		
		this.txtQs.setText("Question "+quesNo);
		this.txtQ.setText(question);
		this.txtA.setText(a);
		this.txtB.setText(be);
		this.txtC.setText(ce);
		this.txtD.setText(de);
		
		this.btnSubmit = (Button) this.findViewById(R.id.button2);
		this.btnSubmit.setOnClickListener(this);
		this.btnCancel = (Button) this.findViewById(R.id.button1);
		this.btnCancel.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int selectedSex = this.rdoG.getCheckedRadioButtonId();
		RadioButton selectedButton = (RadioButton) this.findViewById(selectedSex);
		
		
		String answer = selectedButton.getText().toString();
		
		switch(v.getId()){
			case R.id.button2: 
			URL url;
			try {
				url = new URL("http://172.19.130.31/android/sendanswer.php?idno="+ idno +"&question_no="+ quesNo +"&answer="+ answer);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				InputStream is = conn.getInputStream();
				
				int c=0;
				StringBuffer sb = new StringBuffer();
				while((c=is.read())!=-1){
					sb.append((char)c);	
				}
				is.close();
				conn.disconnect();
				
				Toast.makeText(this, idno.toString() + "\n" + quesNo.toString() + "\n" + answer.toString(), Toast.LENGTH_SHORT).show();
				//Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				Intent intnt = new Intent();
					intnt.putExtra("ans", answer);
					intnt.putExtra("no", quesNo);
					this.setResult(Activity.RESULT_OK, intnt);
			
			case R.id.button1: this.finish();
				break;
		}
		
	}
	
}

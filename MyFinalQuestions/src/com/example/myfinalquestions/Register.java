package com.example.myfinalquestions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.myfinalquestions.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {
	
	EditText idno, famname, givname, email, pass;
	Button btnSubmit, btnCan;
	String idNo, fname, gname, em, passs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);
        
       
        this.idno = (EditText) this.findViewById(R.id.editText2);
        this.famname = (EditText) this.findViewById(R.id.editText1);
        this.givname = (EditText) this.findViewById(R.id.editText3);
        this.email = (EditText) this.findViewById(R.id.editText4);
        this.pass = (EditText) this.findViewById(R.id.editText5);
        
        this.btnSubmit = (Button) this.findViewById(R.id.button1);
        this.btnSubmit.setOnClickListener(this);
        this.btnCan = (Button) this.findViewById(R.id.button2);
        this.btnCan.setOnClickListener(this);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			String idNo = this.idno.getText().toString();
			String fname = this.famname.getText().toString();
			String gname = this.givname.getText().toString();
			String em = this.email.getText().toString();
			String passs = this.pass.getText().toString();
			
			try {
				URL url = new URL("http://172.19.130.31/android/addStudent.php?idno="+ idNo +"&familyname="+ fname + "&givenname=" + gname + "&email=" + em + "&password=" + passs);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				int c = 0;
				StringBuffer s = new StringBuffer();
				InputStream is = conn.getInputStream();
				while((c=is.read())!=-1){
					s.append((char)c);
				}
				is.close();
				conn.disconnect();
				
				if(s.toString().equals("Fill all fields")){
					Toast.makeText(this, s.toString(), Toast.LENGTH_LONG).show();
					break;
				}
					
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.pass.setText("");
			this.email.setText("");
			this.givname.setText("");
			this.famname.setText("");
			this.idno.setText("");
			/*String user = this.user.getText().toString();
			String pass = this.pass.getText().toString();
			String cpass = this.cpass.getText().toString();
			
			if(pass.equals(cpass)){
				try {
					URL url = new URL("http://10.0.2.2/hey/add.php?user="+ user +"&password="+ pass);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					int c = 0;
					StringBuffer s = new StringBuffer();
					InputStream is = conn.getInputStream();
					while((c=is.read())!=-1){
						s.append((char)c);
					}
					is.close();
					conn.disconnect();
					
					
						
					if(s.toString().equals("You Have Registered!")){
						Toast.makeText(this, s.toString(), Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(this, "Username Already Exist!", Toast.LENGTH_SHORT).show();
						break;
					}
	
	
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent intnt = new Intent();
				 intnt.putExtra("user", user);
				 intnt.putExtra("pass", pass);
				 
				 this.setResult(Activity.RESULT_OK, intnt);
			}else{
				Toast.makeText(this, "Password Doesn't Match!", Toast.LENGTH_SHORT).show();
				break;
			}*/
			Intent intnt = new Intent();
			 intnt.putExtra("idno", idNo);
			 this.setResult(Activity.RESULT_OK, intnt);
			 
		case R.id.button2: this.finish();
							break;
		}
	}
}

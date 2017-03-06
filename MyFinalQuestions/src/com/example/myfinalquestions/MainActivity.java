package com.example.myfinalquestions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.myfinalquestions.R;
import com.example.myfinalquestions.ViewStudent;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	EditText txtUsername, pwdPassword;
	Button btnSubmit, btnReg;
	String user, pass;
	String no, fname, gname, em, passs, sched, scre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.txtUsername = (EditText) this.findViewById(R.id.editText1);
        this.pwdPassword = (EditText) this.findViewById(R.id.editText2);
        
        this.btnSubmit = (Button) this.findViewById(R.id.button1);
        this.btnSubmit.setOnClickListener(this);

        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
    }

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
			
		case R.id.button1:	
			String user = this.txtUsername.getText().toString();
			String pass = this.pwdPassword.getText().toString();
			
			try {
				//URL url = new URL("http://10.0.2.2/Android/log.php?user="+ user +"&password="+ pass );
				URL url = new URL("http://172.19.130.31/android/login.php?idno="+ user +"&password="+ pass );
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				int c = 0;
				StringBuffer s = new StringBuffer();
				InputStream is = conn.getInputStream();
				while((c=is.read())!=-1){
					s.append((char)c);
				}
				is.close();
				conn.disconnect();
				
				//Toast.makeText(this, s.toString(), Toast.LENGTH_SHORT).show();
				
				if(!s.toString().equals("")){
					//Intent intnt = new Intent(this, ListofStudents.class);
					//this.startActivityForResult(intnt, 1);
					JSONObject json =  new JSONObject(s.toString());
					JSONObject arrayjson = json.getJSONObject("student");
					//for(int i=0;i<arrayjson.length();i++){
						//JSONArray item = arrayjson.getJSONArray(i);
						//JSONObject j = arrayjson.getJSONObject();
						 no = arrayjson.getString("idno");
						 fname = arrayjson.getString("familyName");
						 gname = arrayjson.getString("givenName");
						 em = arrayjson.getString("email");
						 passs = arrayjson.getString("password");
						 sched = arrayjson.getString("schedule");
						 scre = arrayjson.getString("score");
					//}
					
					//Toast.makeText(this, no.toString(), Toast.LENGTH_SHORT).show();
					//this.txtUsername.setText("");
					//this.pwdPassword.setText("");
					Intent intnt = new Intent(this, ListofStudents.class);
					intnt.putExtra("idno", no);
					/*intnt.putExtra("fname", fname);
					intnt.putExtra("gname", gname);
					intnt.putExtra("email", em);
					intnt.putExtra("pass", passs);
					intnt.putExtra("sched", sched);
					intnt.putExtra("score", scre);*/
					this.startActivityForResult(intnt, 1);
					this.pwdPassword.setText("");

				}else{
					Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
					this.txtUsername.setText("");
					this.pwdPassword.setText("");
				}
					
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==2){
				Bundle b = data.getExtras();
				user = b.getString("idno");
				
				this.txtUsername.setText(user);
			}
		
		}
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intnt = new Intent(this, Register.class);
		   this.startActivityForResult(intnt, 2);
		return super.onOptionsItemSelected(item);
	}


}//end of class

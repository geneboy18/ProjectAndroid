package com.example.judge1;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	
	EditText txtIpAddress;
	Button btnSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.txtIpAddress=(EditText) this.findViewById(R.id.editText1);
        this.btnSet=(Button) this.findViewById(R.id.button1);
        this.btnSet.setOnClickListener(this);
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String ipadd=this.txtIpAddress.getText().toString();
		if(!ipadd.equals("")){
			Sessions.setIpAdd(ipadd);
			Intent intent=new Intent(this,SelectGroupActivity.class);
			this.startActivity(intent);
		}
		else {
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
				builder.setTitle("Error");
				builder.setMessage("Please Set Server IPAddress");
				builder.setNeutralButton("Okey",null);
			AlertDialog dialog=builder.create();
				dialog.show();
			
		}
	}
    
}

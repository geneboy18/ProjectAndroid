package com.example.person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSms extends Activity implements OnClickListener {

	Button btnSend,btnCancel;
	EditText etTo,etMsg;
	String name,number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.sendsms);
        
        this.btnSend=(Button) this.findViewById(R.id.button1);
        this.btnCancel=(Button) this.findViewById(R.id.button2);
        this.etTo=(EditText) this.findViewById(R.id.editText1);
        this.etMsg=(EditText) this.findViewById(R.id.editText2);
        
        this.btnSend.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);
        
        Bundle b=getIntent().getExtras();
        name=b.getString("name");
        number=b.getString("number");
        etTo.setText(name+"("+number+")");
        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id=arg0.getId();
		switch(id){
			case R.id.button1:
				String msg=etMsg.getText().toString();
			    try {
				    SmsManager smsManager = SmsManager.getDefault();
				    smsManager.sendTextMessage(number, null, msg, null, null);
				    Toast.makeText(getApplicationContext(), "SMS sent to "+name+".", Toast.LENGTH_LONG).show();
			    }
			    catch (Exception e) {
			    	Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
			    	
    				Intent sendSms = new Intent(this,SendSms.class);
    				sendSms.putExtra("name", name);
    				sendSms.putExtra("number", number);
    				startActivity(sendSms);
    				
    				e.printStackTrace();
			    }     
			case R.id.button2:
				this.finish();
		}
	}
}

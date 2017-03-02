package com.example.judge1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectGroupActivity extends Activity implements OnClickListener {
	
	Button btnGroup1;
	Button btnGroup2;	
	Button btnGroup3;
	Button btnGroup4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.select_group);
		
		this.btnGroup1=(Button) this.findViewById(R.id.button1);
		this.btnGroup2=(Button) this.findViewById(R.id.button2);
		this.btnGroup3=(Button) this.findViewById(R.id.button3);
		this.btnGroup4=(Button) this.findViewById(R.id.button4);
		
		this.btnGroup1.setOnClickListener(this);
		this.btnGroup2.setOnClickListener(this);
		this.btnGroup3.setOnClickListener(this);
		this.btnGroup4.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id=arg0.getId();
		Intent intent=null;
		switch(id){
		case R.id.button1: 
			intent=new Intent(this,Group1ScoreActivity.class);
			this.startActivity(intent);
			break;
		case R.id.button2: 
			intent=new Intent(this,Group2ScoreActivity.class);
			this.startActivity(intent);
			break;
		case R.id.button3: 
			intent=new Intent(this,Group3ScoreActivity.class);
			this.startActivity(intent);
			break;
		case R.id.button4:
			intent=new Intent(this,MyRatings.class);
			this.startActivity(intent);
		}
	}

	
	
	
}

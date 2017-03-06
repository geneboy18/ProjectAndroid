package com.example.person;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdatePerson extends Activity implements OnClickListener {

	ImageView ivImg;
	Button btnSave,btnCancel;
	EditText etName,etNumber;
	Uri uriImg;
	Bitmap bmpImg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);
        
        this.ivImg=(ImageView) this.findViewById(R.id.imageView1);
        this.btnSave=(Button) this.findViewById(R.id.button1);
        this.btnCancel=(Button) this.findViewById(R.id.button2);
        this.etName=(EditText) this.findViewById(R.id.editText1);
        this.etNumber=(EditText) this.findViewById(R.id.editText2);
        
        this.ivImg.setOnClickListener(this);        
        this.btnSave.setOnClickListener(this);        
        this.btnCancel.setOnClickListener(this);
        
        //
        Bundle bundle=getIntent().getExtras();
		String name=bundle.getString("name");
		String number=bundle.getString("number");
		byte[] b = bundle.getByteArray("image");
		Bitmap image = BitmapFactory.decodeByteArray(b, 0, b.length);
		
		etName.setText(name);
		etNumber.setText(number);
		ivImg.setImageBitmap(image);
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
			case R.id.imageView1:
				Intent intent=new Intent(this,ImagePick.class);
				this.startActivityForResult(intent, 100);
			break;
			case R.id.button1:

				String name=etName.getText().toString();
				String number=etNumber.getText().toString();
				
				if(name.isEmpty()||number.isEmpty())
					Toast.makeText(getApplicationContext(), "Some Fields are required.", Toast.LENGTH_SHORT).show();
				else{
					// for Bitmap Image
					if(bmpImg==null)
						bmpImg = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					bmpImg.compress(Bitmap.CompressFormat.PNG, 100, baos);
					byte[] b = baos.toByteArray();

					Intent data=new Intent();
					data.putExtra("name",name);
					data.putExtra("number",number);
					data.putExtra("image",b);
					
					this.setResult(Activity.RESULT_OK, data);
					this.finish();
				}
			break;
			case R.id.button2:
				this.finish();
			break;
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==100){
				Bundle extras = data.getExtras();
				byte[] b = extras.getByteArray("image");
				bmpImg = BitmapFactory.decodeByteArray(b, 0, b.length);
				ivImg.setImageBitmap(bmpImg);
			}
		}
	}
}

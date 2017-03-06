package com.example.person;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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

public class ImagePick extends Activity implements OnClickListener {
	
	Button btnCamera,btnGallery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.image_pick);
        
        this.btnCamera=(Button) this.findViewById(R.id.button1);
        this.btnGallery=(Button) this.findViewById(R.id.button2);
        
        this.btnCamera.setOnClickListener(this);        
        this.btnGallery.setOnClickListener(this);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent=null;
		int id=arg0.getId();
		switch(id){
			case R.id.button1:
				intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				this.startActivityForResult(intent, 100);
			break;
			case R.id.button2:
				intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				this.startActivityForResult(intent, 200);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==Activity.RESULT_OK){
			Bitmap bmp=null;
			if(requestCode==100){
				bmp = (Bitmap) data.getExtras().get("data");
			}
			else if(requestCode==200){
				Uri uriImg=data.getData();
				try {
					InputStream is=getContentResolver().openInputStream(uriImg);
					bmp = BitmapFactory.decodeStream(is);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			if(requestCode==100||requestCode==200){
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
				byte[] b = baos.toByteArray();
				
			    data.putExtra("image", b);
			    this.setResult(Activity.RESULT_OK, data);
			    this.finish();
			}
		}
	}
	

}

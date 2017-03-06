package com.example.person;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView lv;
	EditText etSearch;
	
	ArrayList<Person> person=new ArrayList<Person>();
	ArrayList<Person> list=new ArrayList<Person>();
	PersonAdapter adapter;
	
	AdapterView.AdapterContextMenuInfo info;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        this.lv=(ListView) this.findViewById(R.id.listView1);
        this.etSearch=(EditText) this.findViewById(R.id.editText1);
    
        this.adapter=new PersonAdapter(this,list);
        this.lv.setAdapter(adapter);
        registerForContextMenu(lv);
   
        this.etSearch.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				onTextChangedByListView();
			}
		});
    }

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		if (v.getId()==R.id.listView1) {
			info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			menu.setHeaderTitle(list.get(info.position).getName());
			String[] menuItems = getResources().getStringArray(R.array.menu);
			for (int i = 0; i<menuItems.length; i++) 
				menu.add(Menu.NONE, i, i, menuItems[i]);
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

    @Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	if(info!=null){
    		Person c=person.get(info.position);
    		Intent intent=null;
        	int id = item.getItemId();
    		switch(id){
    			case 0:
    				intent = new Intent(this,SendSms.class);
    				intent.putExtra("name", c.getName());
    				intent.putExtra("number", c.getNumber());
    				startActivity(intent);
    			break;
    			case 1:
    				Toast.makeText(this, "Start Calling : "+c.getName(), Toast.LENGTH_LONG).show();
    				intent = new Intent(Intent.ACTION_CALL);
    				intent.setData(Uri.parse("tel:"+(String)c.getNumber()));
    				startActivity(intent);
    			break;
    			case 2:
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					c.getImg().compress(Bitmap.CompressFormat.PNG, 100, baos);
					byte[] b = baos.toByteArray();
					
    				intent = new Intent(this,UpdatePerson.class);
					intent.putExtra("image",b);
    				intent.putExtra("number", c.getNumber());
    				intent.putExtra("name", c.getName());
    				
    				this.startActivityForResult(intent, 2);
    			break;
    			case 3:
    				AlertDialog.Builder builder=new AlertDialog.Builder(this);
    				builder.setTitle(c.getName()+"("+c.getNumber()+")");
    				builder.setMessage("Are you sure to Delete this Contact?");
    				builder.setNegativeButton("Delete", new OnClickListener(){
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							person.remove(info.position);
		    				Toast.makeText(getApplicationContext(), "Succefully Deleted.", Toast.LENGTH_LONG).show();
		    				onTextChangedByListView();
						}
					});
    				builder.setPositiveButton("Cancel",null);
    				
    				AlertDialog dialog=builder.create();
    				dialog.show();
    			break;
    		}
    	}
		return super.onContextItemSelected(item);
	}
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id=item.getItemId();
		switch(id){
			case R.id.add:
				Intent intent=new Intent(this,AddPerson.class);
				this.startActivityForResult(intent, 1);
			break;
		
		}
		return super.onOptionsItemSelected(item);
	}
   
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==1 || requestCode==2){
				Bundle bundle=data.getExtras();
				
				String name=bundle.getString("name");
				String number=bundle.getString("number");
				
				byte[] b = bundle.getByteArray("image");
				Bitmap bmpImg = BitmapFactory.decodeByteArray(b, 0, b.length);
				
				if(requestCode==1)
					person.add(new Person(bmpImg,name,number));
				else if(requestCode==2)
					person.set(info.position,new Person(bmpImg,name,number));
				
				list.clear();
				for(int i=0;i<person.size();i++)
					list.add(person.get(i));

				onTextChangedByListView();
			}
		}
	
	}
	
	public void onTextChangedByListView(){
		String str=etSearch.getText().toString().toLowerCase();
		list.clear();
		if(!str.isEmpty()){
			Pattern p=Pattern.compile(str);
			for(int i=0;i<person.size();i++){
				Matcher mName=p.matcher(person.get(i).getName());
				Matcher mNumber=p.matcher(person.get(i).getNumber());
				if(mName.find()||mNumber.find())
					list.add(person.get(i));
			}
		}else{
			for(int i=0;i<person.size();i++)
				list.add(person.get(i));
		}
		adapter.notifyDataSetChanged();
	}
}

package com.example.person;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {

	Context context;
	ArrayList<Person> list;
	LayoutInflater inflater; 
	
	public PersonAdapter(Context context, ArrayList<Person> list) {
		super();
		this.context = context;
		this.list = list;
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ContactHandler handler=null;
		if(arg1==null){
			arg1=inflater.inflate(R.layout.itemlayout, null);
			handler=new ContactHandler();
			handler.ivImg=(ImageView) arg1.findViewById(R.id.imageView1);
			handler.txtName=(TextView) arg1.findViewById(R.id.textView1);
			handler.txtNumber=(TextView) arg1.findViewById(R.id.textView2);
			arg1.setTag(handler);
		}else
			handler=(ContactHandler)arg1.getTag();
		
		handler.txtName.setText(list.get(arg0).getName());
		handler.txtNumber.setText(list.get(arg0).getNumber());
		handler.ivImg.setImageBitmap(list.get(arg0).getImg());
				
		return arg1;
	}
	
	static class ContactHandler{
		TextView txtName,txtNumber;
		ImageView ivImg;
	}
}

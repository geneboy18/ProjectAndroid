package com.example.myfinalquestions;

import java.util.ArrayList;

import com.example.myfinalquestions.R;
import com.example.myfinalquestions.Student;
import com.example.myfinalquestions.ItemAdapter.StudentHandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
	
	Context context;
	ArrayList<Student> list;
	LayoutInflater inflater;
	
	
	
	public ItemAdapter(Context context, ArrayList<Student> list) {
		super();
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
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
		StudentHandler holder = null;
		if(arg1 == null){
			arg1 = inflater.inflate(R.layout.itemlayout, null);
			holder = new StudentHandler();
			//holder.idno = (TextView) arg1.findViewById(R.id.textView2);
			holder.name = (TextView) arg1.findViewById(R.id.textView1);
			//holder.course = (TextView) arg1.findViewById(R.id.textView3);
			arg1.setTag(holder);
		}else{
			holder = (StudentHandler) arg1.getTag();
		}
	
				//holder.name.setText("Student" + (arg0+1));
			//holder.idno.setText(list.get(arg0).getIdno());
			holder.name.setText("Question " + list.get(arg0).getQues_id());
			//holder.course.setText(list.get(arg0).getCourse());
		
		
		return arg1;
	}
	
	static class StudentHandler{
		TextView idno, name, course, yrLevel;
	}

}
package com.example.myfinalquestions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.myfinalquestions.ItemAdapter;
import com.example.myfinalquestions.R;
import com.example.myfinalquestions.Student;
import com.example.myfinalquestions.ViewStudent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListofStudents extends Activity implements OnItemClickListener {
	ListView lv;
	Button btnS;
	ArrayList<Student> list = new ArrayList<Student>();
	ItemAdapter adapter;
	AlertDialog.Builder builder;
	String idno, answ, no;
	String ans[];
	String num[];
	int max =2;
	//String idno, familyname, givenname, course, yr;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofstudents);
        
        this.lv = (ListView) this.findViewById(R.id.listView1);

        adapter = new ItemAdapter(this, list);
        this.lv.setAdapter(adapter);
        this.ans = new String[lv.getChildCount()+10];
        this.num = new String[max];
        
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        try {
        	//URL url =  new URL("http://172.19.131.99/android/showstudent.php");
        	URL url =  new URL("http://172.19.130.31/android/getquestions.php");
        	//URL url =  new URL("http://10.0.2.2/hey/db.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			
			int c=0;
			StringBuffer sb = new StringBuffer();
			while((c=is.read())!=-1){
				sb.append((char)c);	
			}
			is.close();
			conn.disconnect();
			
			//Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
			
			/*JSONObject json = new JSONObject(sb.toString());
			JSONArray arrayjson = json.getJSONArray("student");
			for(int i=0;i<arrayjson.length();i++){
				JSONObject item = arrayjson.getJSONObject(i);
				String idno = item.getString("idno");
				String familyname = item.getString("familyName");
				String givenname = item.getString("givenName");
				String course = item.getString("course");
				String yr = item.getString("yrLevel");
				list.add(new Student(idno, familyname, givenname, course, yr));*/
			
			/*JSONObject json = new JSONObject(sb.toString());
			JSONArray arrayjson = json.getJSONArray("student");
			for(int i=0;i<arrayjson.length();i++){
				JSONObject item = arrayjson.getJSONObject(i);
				String id = item.getString("ques_id");
				String question = item.getString("question");
				String a = item.getString("a");
				String b = item.getString("b");
				String ce = item.getString("c");
				String d = item.getString("d");
				list.add(new Student(id, question, a, b, ce, d));*/
			Bundle bnd = this.getIntent().getExtras();
			 idno = bnd.getString("idno");
			
			
			
			JSONObject json = new JSONObject(sb.toString());
			JSONArray arrayjson = json.getJSONArray("question");
			for(int i=0;i<arrayjson.length();i++){
				JSONObject item = arrayjson.getJSONObject(i);
				String no = item.getString("question_no");
				String question = item.getString("question");
				String a = item.getString("opt1");
				String b = item.getString("opt2");
				String ce = item.getString("opt3");
				String d = item.getString("opt4");
				list.add(new Student(no, question, a, b, ce, d));
			}
			adapter.notifyDataSetChanged();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.lv.setOnItemClickListener(this);
        
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		/*TextView idno = new TextView(this);
		TextView name = new TextView(this);
		TextView course = new TextView(this);
		LinearLayout layout = new LinearLayout(this);
		
		
		layout.setOrientation(LinearLayout.VERTICAL);
			builder = new AlertDialog.Builder(this);
				builder.setTitle("Student" + (arg2+1));
				
				idno.setText(list.get(arg2).getIdno());
				idno.setGravity(Gravity.CENTER_HORIZONTAL);
				name.setText(list.get(arg2).getFamilyName() + ", " + list.get(arg2).getGivenName());
				name.setGravity(Gravity.CENTER_HORIZONTAL);
				course.setText(list.get(arg2).getCourse() + " - " + list.get(arg2).getYrLevel());
				course.setGravity(Gravity.CENTER_HORIZONTAL);
				
				layout.addView(idno);
				layout.addView(name);
				layout.addView(course);
				
		builder.setView(layout);
		builder.setNeutralButton("OKAY",null);
		AlertDialog dialog = builder.create();
			dialog.show();*/
		
		for (int i = 0; i < lv.getChildCount(); i++) {
		    if(arg2 == i){
		       this.lv.getChildAt(i).setBackgroundColor(Color.LTGRAY);
		       this.lv.getChildAt(i).setClickable(true);
		    }
		 }
		
		//Toast.makeText(this, idno.toString() + "\n" + list.get(arg2).getQues_id() , Toast.LENGTH_SHORT).show();
		
			Intent intnt = new Intent(this, ViewStudent.class);	
				intnt.putExtra("idno", idno);
				intnt.putExtra("idquestion", list.get(arg2).getQues_id());
				intnt.putExtra("question", list.get(arg2).getQuestion());
				intnt.putExtra("a", list.get(arg2).getA());
				intnt.putExtra("b", list.get(arg2).getB());
				intnt.putExtra("c", list.get(arg2).getC());
				intnt.putExtra("d", list.get(arg2).getD());
			this.startActivityForResult(intnt, 0);
			
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.register, menu);
        return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		this.finish();
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==0){
				Bundle b = data.getExtras();
					 no = b.getString("no");
					answ = b.getString("ans");
				
			}
		
		}
	}

}

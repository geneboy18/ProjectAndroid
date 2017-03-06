package com.example.myfinalquestions;

public class Student {
	
	//private String idno, familyName, givenName, course, yrLevel;
	private String ques_id, question, a, b, c, d;

	public Student(String ques_id, String question, String a, String b,
			String c, String d) {
		super();
		this.ques_id = ques_id;
		this.question = question;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public String getQues_id() {
		return ques_id;
	}

	public void setQues_id(String ques_id) {
		this.ques_id = ques_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	
	
	
}


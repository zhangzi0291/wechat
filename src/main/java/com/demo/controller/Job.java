package com.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Job {
	public void gettime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
	}
}

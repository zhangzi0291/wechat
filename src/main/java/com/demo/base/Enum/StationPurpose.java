package com.demo.base.Enum;

public enum StationPurpose {
	成人("ADULT"),学生("STUDENT");
	StationPurpose(){
		
	}
	private String type;
	private StationPurpose(String s){
		type=s;
	}
}

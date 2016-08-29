package com.demo.entity;

public class FutureWeather {
	private String date;
	private String high;
	private String low;
	private String fengli;
	private String fengxiang;
	private String type;
	private Double highNum;
	private Double lowNum;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getFengli() {
		return fengli;
	}
	public void setFengli(String fengli) {
		this.fengli = fengli;
	}
	public String getFengxiang() {
		return fengxiang;
	}
	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getHighNum() {
		return highNum;
	}
	public void setHighNum(String high) {
		this.highNum = getNum(high);
	}
	public Double getLowNum() {
		return lowNum;
	}
	public void setLowNum(String low) {
		this.lowNum = getNum(low);
	}
	@Override
	public String toString() {
		return "FutureWeather [date=" + date + ", high=" + high + ", low=" + low + ", fengli=" + fengli + ", fengxiang="
				+ fengxiang + ", type=" + type + ", highNum=" + highNum + ", lowNum=" + lowNum + "]";
	}
	public Double getNum(String num){
		String subNum="";
		byte[] b=num.getBytes();
		for (int i = 0; i < b.length; i++) {
			if ( b[i]>= 0x30 && b[i] <= 0x39) {
				byte[] ba={b[i]};
				subNum += new String(ba);
			}
		}
		return Double.valueOf(subNum);
		
	}
	
}

package com.test;

import com.demo.util.HttpUtil;

public class BaiduTest {
	public static void main(String[] args) {
//		for(int i=0;i<5;i++){
		BaiduEntity b=new BaiduEntity();
			b.setLatitude("32.158444");
			b.setLongitude("118.700701");
			b.setCoordType("1");
			b.setGeotableId("147858");
			b.setBore("-1");
			b.setTitl("-1");
			b.setNetType("4G");
			b.setTitle("东南大学成贤学院桃园");
			b.setCellId("gxDNDXCXXYtaoyuan12dongMBOD");
			b.setCellName("gx东南大学成贤学院桃园12栋MBOD");
			System.out.println(b);
			System.out.println(create(b));
//		}
//		System.out.println(delete(b));
	}
	public static String create(BaiduEntity b){
		return HttpUtil.sendPost("http://api.map.baidu.com/geodata/v3/poi/create", b.toString());
	}
	public static String delete(BaiduEntity b){
		return HttpUtil.sendPost("http://api.map.baidu.com/geodata/v3/poi/delete", b.toString());
	}
}

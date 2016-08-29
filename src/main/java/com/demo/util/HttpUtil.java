package com.demo.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.entity.WechatMessage;

public class HttpUtil {
	
	/**
	 * 
	 * 发送GET请求
	 * @param url 目标url
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 返回String字符串
	 */
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
	/**
	 * 
	 * 发送POST请求
	 * @param url 目标url
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 返回String字符串
	 */
	public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
	public static Document sendTextXML(WechatMessage message){
		Document document=DocumentHelper.createDocument();
		Element root=document.addElement("xml");
		Element ToUserName = root.addElement("ToUserName");
		Element FromUserName = root.addElement("FromUserName");
		Element CreateTime = root.addElement("CreateTime");
		Element MsgType = root.addElement("MsgType");
		Element Content = root.addElement("Content");
		Element MsgId = root.addElement("MsgId");
		String tousername=message.getFromUserName();
		String fromusername=message.getToUserName();
		ToUserName.addCDATA(tousername);
		FromUserName.addCDATA(fromusername);
		CreateTime.addCDATA((message.getCreateTime().getTime()/1000)+"");
		MsgType.addCDATA(message.getMsgType());
		Content.addCDATA(message.getContent());
		MsgId.addCDATA(message.getMsgId());
		return document;
	}
	
	public static String getRobotTolk(String message){
		String url="http://www.tuling123.com/openapi/api";
		String param="key=3bd5fe84edb65d3179caa29837f54d5d&info="+message;		
		return sendPost(url, param);
	}
	public static String getToDayWeather(String city){
		JSONObject json=JSONObject.parseObject(getWeather(city));
		JSONObject today=(JSONObject) json.get("data");
		StringBuffer sb=new StringBuffer();
		sb.append("现在温度："+today.getString("wendu")+"\n");
		
		JSONObject json2=JSONObject.parseObject(getWeather(city));
		JSONObject today2=(JSONObject) json.get("data");
		JSONArray forecast2 = today2.getJSONArray("forecast");
		JSONObject j=(JSONObject) forecast2.get(0);
		
		sb.append("天气："+j.getString("type")+"\n");
		if(today.getString("aqi")!=null&&!"".equals(today.getString("aqi"))){
			sb.append("空气指数："+today.getString("aqi")+"\n");
		}
		sb.append("感冒指数："+today.getString("ganmao"));
		return sb.toString();
	}
	public  static String getFutureWeather(String city){
		JSONObject json=JSONObject.parseObject(getWeather(city));
		JSONObject today=(JSONObject) json.get("data");
		JSONArray forecast = today.getJSONArray("forecast");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<forecast.size()-1;i++){
			JSONObject j=(JSONObject) forecast.get(i);
			sb.append("天气预报  "+j.getString("date")+"："+"\n");
			sb.append(j.getString("high")+" "+j.getString("low")+"\n");
			sb.append("天气："+j.getString("type")+"	风向："+j.getString("fengxiang")+"	风力等级："+j.getString("fengli")+"\n");
		}
		return sb.toString();
	}
	
	public  static String  getWeather(String city) {
		String url="http://wthrcdn.etouch.cn/weather_mini";
		String parem="city="+city;
		HttpGet httpGet = new HttpGet(url +"?"+parem);  
		HttpClient httpClient = new DefaultHttpClient();
		String weatherJSON = "";
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);  	
			weatherJSON = getJsonStringFromGZIP(httpResponse);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return weatherJSON;
	}

    private static String getJsonStringFromGZIP(HttpResponse response) {  
        String jsonString = null;  
        try {  
            InputStream is = response.getEntity().getContent();  
            BufferedInputStream bis = new BufferedInputStream(is);  
            bis.mark(2);  
            // 取前两个字节  
            byte[] header = new byte[2];  
            int result = bis.read(header);  
            // reset输入流到开始位置  
            bis.reset();  
            // 判断是否是GZIP格式  
            int headerData = getShort(header);  
            if (result != -1 && headerData == 0x1f8b) {  
                is = new GZIPInputStream(bis);  
            } else {  
                is = bis;  
            }  
            InputStreamReader reader = new InputStreamReader(is, "utf-8");  
            char[] data = new char[100];  
            int readSize;  
            StringBuffer sb = new StringBuffer();  
            while ((readSize = reader.read(data)) > 0) {  
                sb.append(data, 0, readSize);  
            }  
            jsonString = sb.toString();  
            bis.close();  
            reader.close();  
        } catch (Exception e) {  
            e.printStackTrace();
        }  
        return jsonString;  
    }  
  
    private static int getShort(byte[] data) {  
        return (int) ((data[0] << 8) | data[1] & 0xFF);  
    }  
    public static void main(String[] args) {
//    	String url="http://int.dpool.sina.com.cn/iplookup/iplookup.php";
//		String parem="format=json&ip=222.45.42.120";
//		HttpGet httpGet = new HttpGet(url +"?"+parem);  
//		HttpClient httpClient = new DefaultHttpClient();
//		String weatherJSON = "";
//		try {
//			HttpResponse httpResponse = httpClient.execute(httpGet);  	
//			weatherJSON = getJsonStringFromGZIP(httpResponse);
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    	String weatherJSON=sendPost("http://int.dpool.sina.com.cn/iplookup/iplookup.php", "format=json&ip=222.45.42.120");
		System.out.println(weatherJSON);
	}
}

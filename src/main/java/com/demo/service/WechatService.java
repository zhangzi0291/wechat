package com.demo.service;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.demo.dao.AccessToken.AccessTokenDao;
import com.demo.entity.AccessToken.AccessToken;
import com.demo.util.GlobalConstants;
import com.demo.util.PropertiesConfig;

@Service("wechatService")
public class WechatService {

	@Resource
	AccessTokenDao atdao;
	
	public void updateAccessToken( Map<String, Object> map){
		atdao.updateAccessToken(map);
	}
	public Map<String, Object> getAccessToken(){
		return atdao.getAccessToken();
	}
	public Boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { PropertiesConfig.getProperties(GlobalConstants.WECHAT_TOKEN), timestamp, nonce };  
        // 将token、timestamp、nonce三个参数进行字典序排序  
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        try {
			if(signature.toUpperCase().equals(sha1(content.toString()))){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return false;
	}

	private String sha1(String str) throws Exception{
	    MessageDigest md = MessageDigest.getInstance("SHA-1");
	    md.update(str.getBytes("UTF-8"));
	    byte[] result = md.digest();
	    
	    StringBuffer sb = new StringBuffer();
	 
	    for (byte b : result) {
	        int i = b & 0xff;
	        if (i < 0xf) {
	            sb.append(0);
	        }
	        sb.append(Integer.toHexString(i));
	    }
	    return sb.toString().toUpperCase();
	}
}

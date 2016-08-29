package com.demo.dao.AccessToken;


import java.util.Map;

public interface AccessTokenDao {
	public Integer updateAccessToken(Map<String, Object> map);
	public Map<String, Object> getAccessToken();
}
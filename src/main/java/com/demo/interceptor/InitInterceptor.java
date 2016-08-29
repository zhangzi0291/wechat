package com.demo.interceptor;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.Error.ErrorCode;
import com.demo.service.WechatService;
import com.demo.util.DateUtil;
import com.demo.util.GlobalConstants;
import com.demo.util.HttpUtil;
import com.demo.util.PropertiesConfig;

public class InitInterceptor extends HandlerInterceptorAdapter {

	private Logger log = LoggerFactory.getLogger(InitInterceptor.class);
	@Resource
	WechatService wcservice;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		Date date = (Date) wcservice.getAccessToken().get("expires_in");
		if (date.before(new Date())) {
			String param = "grant_type=client_credential&appid=" + PropertiesConfig.getProperties("wechat.AppID")
					+ "&secret=" + PropertiesConfig.getProperties("wechat.AppSecret");
			String result = HttpUtil.sendPost(PropertiesConfig.getProperties("URL"), param);
			log.info("获取新的Access_Token");
			if (result.indexOf("errcode") == -1) {
				JSONObject json = JSONObject.parseObject(result);
				json.put("expires_in", DateUtil.getStrByDate(
						DateUtil.addSecond(new Date(), (Integer) json.get("expires_in")), "yyyy-MM-dd HH:mm:ss"));
				wcservice.updateAccessToken(json);
				GlobalConstants.ACCESSTOKEN=(String) json.get("access_token");
				log.info("保存新的Access_Token："+GlobalConstants.ACCESSTOKEN);
				return true;
			} else {
				ErrorCode error = JSONObject.parseObject(result, ErrorCode.class);
				log.info("错误代码：" + error.getErrcode() + "---------错误信息" + error.getErrmsg());
			}
		}
		return true;
	}
}

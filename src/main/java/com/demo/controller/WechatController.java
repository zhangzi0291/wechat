package com.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.DigitalSignature;
import com.demo.entity.TrainInfo;
import com.demo.entity.WechatMessage;
import com.demo.service.StationService;
import com.demo.service.WechatService;
import com.demo.util.DateUtil;
import com.demo.util.GlobalConstants;
import com.demo.util.HttpUtil;
import com.demo.util.PropertiesConfig;

@Controller
public class WechatController {

	private Logger log = LoggerFactory.getLogger(WechatController.class);  
	@Resource
	private WechatService service;
	@Resource
	private StationService stationService;
	
	@RequestMapping(value="check",method=RequestMethod.GET)
	@ResponseBody
	public String check(HttpServletRequest request,HttpServletResponse response,DigitalSignature dp) throws UnsupportedEncodingException{
		if(dp!=null){
			if(service.checkSignature(dp.getSignature(), dp.getTimestamp(), dp.getNonce())){
				log.info("微信效验成功");
				return dp.getEchostr();
			}else{
				log.info("微信效验失败");
				return null;
			}
		}
		
		return null;
	}
	/**
	 * 
	  * 天气预报                                         
	  *@param request
	  *@param response
	  *@param xml
	  *@return
	  *@throws DocumentException
	  *@throws IOException 
	  *@date 2016年8月8日 上午9:18:21
	  *@author zxn
	 */
	@RequestMapping(value="check",method=RequestMethod.POST)
	@ResponseBody
	public String test(HttpServletRequest request,HttpServletResponse response,String xml) throws DocumentException, IOException{
		InputStream is=request.getInputStream();
		SAXReader reader = new SAXReader();
		Document document =reader.read(is);;
		Element root = document.getRootElement();
		List<Element> childElements = root.elements();
		WechatMessage message=new WechatMessage();
		for (Element e : childElements) {
			switch (e.getName()) {
			case "ToUserName":
				message.setToUserName(e.getTextTrim());
				break;
			case "FromUserName":
				message.setFromUserName(e.getTextTrim());
				break;
			case "CreateTime":
				Date date=new Date(Long.parseLong(e.getText())*1000);
				message.setCreateTime(date);
				break;
			case "MsgType":
				message.setMsgType(e.getTextTrim());
				break;
			case "Content":
				message.setContent(e.getTextTrim());
				break;
			case "MsgId":
				message.setMsgId(e.getTextTrim());
				break;
			default:
				break;
			}
		}
		log.info("文本消息："+message.getContent()+"     类型："+message.getMsgType()+"     时间："+DateUtil.getStrByDate(message.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		switch (message.getMsgType()) {
		case "text":
			message=textMessage(message);
			break;

		default:
			message.setContent("你说什么？风太大我听不见");
			break;
		}
		
		
		log.info("返回的文本："+message.getContent());
		return HttpUtil.sendTextXML(message).asXML();			
	}
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String aaa(HttpServletResponse response) throws ClientProtocolException, IOException{
		StringBuffer sb=new StringBuffer();
		sb.append(HttpUtil.getToDayWeather("南京"));
		sb.append("\n");
		sb.append(HttpUtil.getFutureWeather("南京"));
		return sb.toString();
	}
	
	/**
	 * 
	  * 文本回复
	  *@param message
	  *@return 
	  *@date 2016年8月5日 下午4:35:09
	  *@author zxn
	 */
	public WechatMessage textMessage(WechatMessage message){
		String content=message.getContent().trim();
		if(content!=null&&!"".equals(content)){
			if(content.startsWith("天气")){
				log.info("获取天气信息");
				message.setContent(HttpUtil.getToDayWeather(content.substring(2).trim()));
			}else if(content.startsWith("预报")){
				log.info("获取预报信息");
				message.setContent("看预报<a href='http://106.186.18.49/wechat/web/futureweather?area="+URLEncoder.encode(content.substring(2).trim())+"'>点这里</a>");
			}else if(content.startsWith("余票")){
				log.info("12306火车余票查询");
				try {
					String [] s=content.substring(2).trim().split("\\+");
					message.setContent("火车余票<a href='http://106.186.18.49/wechat/web/traintable?date="
					+s[0]+"&startStation="+URLEncoder.encode(s[1])+"&endStation="+URLEncoder.encode(s[2])+"'>点这里</a>");
				} catch (Exception e) {
					e.printStackTrace();
					message.setContent("参数不对，需要  余票yyyyMMdd+出发地+到达地  的格式");
				}
			}else{
				log.info("图灵机器人回复");
				message.setContent(robotTolk(message.getContent()));	
			}
		}else{
			log.info("消息为空字符串");
			message.setContent("你说什么？风太大我听不见");
		}
		return message;
	}
	
	public String robotTolk(String text){
		String message=HttpUtil.getRobotTolk(text);
		JSONObject json=JSONObject.parseObject(message);
		log.info("code:"+json.getString("code"));
		log.info("info:"+json.getString("text"));
		if(json.getString("code").equals("100000")){
			return json.getString("text");			
		}else{
			return "图灵机器人错误";
		}
	}
	/**
	 * 
	  * 余票查询
	  *@param args
	  *@return
	  *@throws Exception 
	  *@date 2016年8月8日 上午9:17:50
	  *@author zxn
	 */
	public String trainCheck(String[] args)throws Exception{
		if(args.length<3){
			throw new Exception("参数不足");
		}
		
		String url = "https://kyfw.12306.cn/otn/lcxxcx/query";
		
		String queryDate=DateUtil.getStrByStr(args[0],"yyyyMMdd", "yyyy-MM-dd");
		String from_station=stationService.getStationCnName(args[1]).getStationCode();
		String to_station=stationService.getStationCnName(args[2]).getStationCode();
		String purpose_codes="ADULT";
		
		String param = "purpose_codes="+purpose_codes+"&queryDate="+queryDate+"&from_station="+from_station+"&to_station="+to_station;
		System.setProperty("javax.net.ssl.trustStore", PropertiesConfig.getProperties(GlobalConstants.SSL_FILE));
		String json=HttpUtil.sendGet(url, param);
		JSONObject jsonObject=JSONObject.parseObject(json);
		JSONObject data= (JSONObject) jsonObject.get("data");
		List<Object> dataArray=data.getJSONArray("datas");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<dataArray.size();i++){
			 JSONObject jsonob= (JSONObject) dataArray.get(i);
			 TrainInfo trainInfo = (TrainInfo) JSONObject.parseObject(jsonob.toJSONString(),TrainInfo.class);
			 if(isTrainTicketEmpty(trainInfo.getGg_num())||isTrainTicketEmpty(trainInfo.getGr_num())||
				 isTrainTicketEmpty(trainInfo.getQt_num())|| isTrainTicketEmpty(trainInfo.getRw_num())||
				 isTrainTicketEmpty(trainInfo.getRz_num())|| isTrainTicketEmpty(trainInfo.getTz_num())||
				 isTrainTicketEmpty(trainInfo.getWz_num())|| isTrainTicketEmpty(trainInfo.getYb_num())||
				 isTrainTicketEmpty(trainInfo.getYw_num())|| isTrainTicketEmpty(trainInfo.getYz_num())||
				 isTrainTicketEmpty(trainInfo.getZe_num())|| isTrainTicketEmpty(trainInfo.getZy_num())||
			 	isTrainTicketEmpty(trainInfo.getSwz_num())){
				 sb.append("车次："+trainInfo.getTrain_no())
				 .append(" 出发时间："+trainInfo.getStart_time())
				 .append(" 到达站："+trainInfo.getEnd_station_name()+"\n")
				 .append("余票 :");
				 if(isTrainTicketEmpty(trainInfo.getGr_num())){
					 sb.append("高级软卧"+trainInfo.getGr_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getQt_num())){
					 sb.append("其他"+trainInfo.getQt_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getRw_num())){
					 sb.append("软卧"+trainInfo.getRw_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getRz_num())){
					 sb.append("软座"+trainInfo.getRz_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getTz_num())){
					 sb.append("特等座"+trainInfo.getTz_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getWz_num())){
					 sb.append("无座"+trainInfo.getWz_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getYw_num())){
					 sb.append("硬卧"+trainInfo.getYw_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getYz_num())){
					 sb.append("硬座"+trainInfo.getYz_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getZe_num())){
					 sb.append("二等座"+trainInfo.getZe_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getZy_num())){
					 sb.append("一等座"+trainInfo.getZy_num()+"张");
				 }
				 if(isTrainTicketEmpty(trainInfo.getSwz_num())){
					 sb.append("商务座"+trainInfo.getSwz_num()+"张");
				 }
			 }
		}
		return sb.toString();
	}
	private boolean isTrainTicketEmpty(String s){
		if(s!=null&&!"".equals(s)&&!"--".equals(s)&&!"无".equals(s)){
			return true;
		}
		return false;
	}
}

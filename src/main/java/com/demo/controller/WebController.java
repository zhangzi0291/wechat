package com.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.entity.FutureWeather;
import com.demo.service.StationService;
import com.demo.util.DateUtil;
import com.demo.util.GlobalConstants;
import com.demo.util.HttpUtil;
import com.demo.util.PropertiesConfig;

@Controller
@RequestMapping("web")
public class WebController {

	@Resource
	private StationService stationService;

	@RequestMapping("futureweather")
	public String getFutureWeather(Map<String, Object> map, String area) {
		map.put("area", area);
		return "weather/weather";
	}

	@RequestMapping("futureweather.json")
	@ResponseBody
	public List<FutureWeather> getFutureWeatherJson(String area) {
		final String weatherjson = HttpUtil.getWeather(area);
		JSONObject json = JSONObject.parseObject(weatherjson);
		JSONObject today = (JSONObject) json.get("data");
		JSONArray forecast = today.getJSONArray("forecast");
		List<FutureWeather> list = new ArrayList<>();
		for (Object ob : forecast) {
			FutureWeather future = JSONObject.parseObject(ob.toString(), FutureWeather.class);
			future.setHighNum(future.getHigh());
			future.setLowNum(future.getLow());
			list.add(future);
		}
		return list;
	}

	@RequestMapping("traintable")
	public String getTrainTable(Map<String, Object> map, String date, String startStation, String endStation) {
		map.put("date", date);
		map.put("startStation", startStation);
		map.put("endStation", endStation);
		return "traintable/train";
	}

	@RequestMapping("traintable.json")
	@ResponseBody
	public List<Object> getTrainTableJson(Map<String, Object> map, String date, String startStation, String endStation) throws Exception {
		if (date == null || startStation == null || endStation == null || date.length() == 0
				|| startStation.length() == 0 || endStation.length() == 0) {
			throw new Exception("参数不足");
		}

		final String  URL = "https://kyfw.12306.cn/otn/lcxxcx/query";

		String queryDate = DateUtil.getStrByStr(date, "yyyyMMdd", "yyyy-MM-dd");
		String from_station = stationService.getStationCnName(startStation).getStationCode();
		String to_station = stationService.getStationCnName(endStation).getStationCode();
		String purpose_codes = "ADULT";

		String param = "purpose_codes=" + purpose_codes + "&queryDate=" + queryDate + "&from_station=" + from_station
				+ "&to_station=" + to_station;
		System.out.println(param);
		System.setProperty("javax.net.ssl.trustStore", PropertiesConfig.getProperties(GlobalConstants.SSL_FILE));
		String json = HttpUtil.sendGet(URL, param);
		System.out.println(json);
		JSONObject jsonObject = JSONObject.parseObject(json);
		JSONObject data = (JSONObject) jsonObject.get("data");
		List<Object> dataArray = data.getJSONArray("datas");
		return dataArray;
	}

}

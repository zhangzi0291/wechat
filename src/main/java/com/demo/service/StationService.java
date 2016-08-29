package com.demo.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.station.StationDao;
import com.demo.entity.station.Station;

@Service("stationService")
public class StationService {
	@Resource
	private StationDao dao;
	
	public Station getStationCnName(String stationCode){
		return dao.findStationCode(stationCode);
	}
}

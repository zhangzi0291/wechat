package com.demo.dao.station;


import java.util.Map;

import com.demo.entity.station.Station;

public interface StationDao {
	public Integer insertStation(Map<String, Object> map);
	public Station findStationCode(String stationCode);
}
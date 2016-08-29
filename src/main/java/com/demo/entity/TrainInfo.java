package com.demo.entity;

public class TrainInfo {
	private String train_no;//车辆编号
	private String station_train_code;//车次 "G7571",
	private String start_station_telecode;//始发站名 "HOH",
	private String start_station_name;// 始发站名中文 "淮南东",
	private String end_station_telecode;// 终点站名 "VRH",
	private String end_station_name;//终点站名中文 "温州南",
	private String from_station_telecode;// 出发站名 "NKH",
	private String from_station_name;// 出发站名中文 "南京南",
	private String to_station_telecode; // 到达站名 "AOH",
	private String to_station_name; // 到达站名中文 "上海虹桥",
	private String start_time; //出发时间 "14; //05",
	private String arrive_time; //到达时间  "16; //10",
	private String day_difference; // "0",
	private String train_class_name; // "", 列车类型
	private String lishi; // 经历的时间 （小时）"02; //05",
	private String canWebBuy; //可否网络购买 "Y",
	private String lishiValue; // "125",
	private String yp_info; // "O014450000M022950000O014453010",
	private String control_train_day; // "20300303",
	private String start_train_date; // 开车日期 "20160805",
	private String seat_feature; // "O3M3W3",
	private String yp_ex; // "O0M0O0",
	private String train_seat_feature; // "3",
	private String seat_types; // "OMO",
	private String location_code; // "H2",
	private String from_station_no; // "04",
	private String to_station_no; // "11",
	private String control_day; // 59,
	private String sale_time; // "0800",
	private String is_support_card; // "1",
	private String note; // "",
	private String controlled_train_flag; // "0",
	private String controlled_train_message; // "正常车次，不受控",
	private String gg_num; // "--",
	private String gr_num; // "--",高级软卧
	private String qt_num; // "--",其他
	private String rw_num; // "--",软卧
	private String rz_num; // "--",软座
	private String tz_num; // "--",特等
	private String wz_num; // "10",无座
	private String yb_num; // "--",
	private String yw_num; // "--",硬卧
	private String yz_num; // "--",硬座
	private String ze_num; // "无",二等
	private String zy_num; // "无",一等
	private String swz_num; // "--"商务
	public String getTrain_no() {
		return train_no;
	}
	public void setTrain_no(String train_no) {
		this.train_no = train_no;
	}
	public String getStation_train_code() {
		return station_train_code;
	}
	public void setStation_train_code(String station_train_code) {
		this.station_train_code = station_train_code;
	}
	public String getStart_station_telecode() {
		return start_station_telecode;
	}
	public void setStart_station_telecode(String start_station_telecode) {
		this.start_station_telecode = start_station_telecode;
	}
	public String getStart_station_name() {
		return start_station_name;
	}
	public void setStart_station_name(String start_station_name) {
		this.start_station_name = start_station_name;
	}
	public String getEnd_station_telecode() {
		return end_station_telecode;
	}
	public void setEnd_station_telecode(String end_station_telecode) {
		this.end_station_telecode = end_station_telecode;
	}
	public String getEnd_station_name() {
		return end_station_name;
	}
	public void setEnd_station_name(String end_station_name) {
		this.end_station_name = end_station_name;
	}
	public String getFrom_station_telecode() {
		return from_station_telecode;
	}
	public void setFrom_station_telecode(String from_station_telecode) {
		this.from_station_telecode = from_station_telecode;
	}
	public String getFrom_station_name() {
		return from_station_name;
	}
	public void setFrom_station_name(String from_station_name) {
		this.from_station_name = from_station_name;
	}
	public String getTo_station_telecode() {
		return to_station_telecode;
	}
	public void setTo_station_telecode(String to_station_telecode) {
		this.to_station_telecode = to_station_telecode;
	}
	public String getTo_station_name() {
		return to_station_name;
	}
	public void setTo_station_name(String to_station_name) {
		this.to_station_name = to_station_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getArrive_time() {
		return arrive_time;
	}
	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}
	public String getDay_difference() {
		return day_difference;
	}
	public void setDay_difference(String day_difference) {
		this.day_difference = day_difference;
	}
	public String getTrain_class_name() {
		return train_class_name;
	}
	public void setTrain_class_name(String train_class_name) {
		this.train_class_name = train_class_name;
	}
	public String getLishi() {
		return lishi;
	}
	public void setLishi(String lishi) {
		this.lishi = lishi;
	}
	public String getCanWebBuy() {
		return canWebBuy;
	}
	public void setCanWebBuy(String canWebBuy) {
		this.canWebBuy = canWebBuy;
	}
	public String getLishiValue() {
		return lishiValue;
	}
	public void setLishiValue(String lishiValue) {
		this.lishiValue = lishiValue;
	}
	public String getYp_info() {
		return yp_info;
	}
	public void setYp_info(String yp_info) {
		this.yp_info = yp_info;
	}
	public String getControl_train_day() {
		return control_train_day;
	}
	public void setControl_train_day(String control_train_day) {
		this.control_train_day = control_train_day;
	}
	public String getStart_train_date() {
		return start_train_date;
	}
	public void setStart_train_date(String start_train_date) {
		this.start_train_date = start_train_date;
	}
	public String getSeat_feature() {
		return seat_feature;
	}
	public void setSeat_feature(String seat_feature) {
		this.seat_feature = seat_feature;
	}
	public String getYp_ex() {
		return yp_ex;
	}
	public void setYp_ex(String yp_ex) {
		this.yp_ex = yp_ex;
	}
	public String getTrain_seat_feature() {
		return train_seat_feature;
	}
	public void setTrain_seat_feature(String train_seat_feature) {
		this.train_seat_feature = train_seat_feature;
	}
	public String getSeat_types() {
		return seat_types;
	}
	public void setSeat_types(String seat_types) {
		this.seat_types = seat_types;
	}
	public String getLocation_code() {
		return location_code;
	}
	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}
	public String getFrom_station_no() {
		return from_station_no;
	}
	public void setFrom_station_no(String from_station_no) {
		this.from_station_no = from_station_no;
	}
	public String getTo_station_no() {
		return to_station_no;
	}
	public void setTo_station_no(String to_station_no) {
		this.to_station_no = to_station_no;
	}
	public String getControl_day() {
		return control_day;
	}
	public void setControl_day(String control_day) {
		this.control_day = control_day;
	}
	public String getSale_time() {
		return sale_time;
	}
	public void setSale_time(String sale_time) {
		this.sale_time = sale_time;
	}
	public String getIs_support_card() {
		return is_support_card;
	}
	public void setIs_support_card(String is_support_card) {
		this.is_support_card = is_support_card;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getControlled_train_flag() {
		return controlled_train_flag;
	}
	public void setControlled_train_flag(String controlled_train_flag) {
		this.controlled_train_flag = controlled_train_flag;
	}
	public String getControlled_train_message() {
		return controlled_train_message;
	}
	public void setControlled_train_message(String controlled_train_message) {
		this.controlled_train_message = controlled_train_message;
	}
	public String getGg_num() {
		return gg_num;
	}
	public void setGg_num(String gg_num) {
		this.gg_num = gg_num;
	}
	public String getGr_num() {
		return gr_num;
	}
	public void setGr_num(String gr_num) {
		this.gr_num = gr_num;
	}
	public String getQt_num() {
		return qt_num;
	}
	public void setQt_num(String qt_num) {
		this.qt_num = qt_num;
	}
	public String getRw_num() {
		return rw_num;
	}
	public void setRw_num(String rw_num) {
		this.rw_num = rw_num;
	}
	public String getRz_num() {
		return rz_num;
	}
	public void setRz_num(String rz_num) {
		this.rz_num = rz_num;
	}
	public String getTz_num() {
		return tz_num;
	}
	public void setTz_num(String tz_num) {
		this.tz_num = tz_num;
	}
	public String getWz_num() {
		return wz_num;
	}
	public void setWz_num(String wz_num) {
		this.wz_num = wz_num;
	}
	public String getYb_num() {
		return yb_num;
	}
	public void setYb_num(String yb_num) {
		this.yb_num = yb_num;
	}
	public String getYw_num() {
		return yw_num;
	}
	public void setYw_num(String yw_num) {
		this.yw_num = yw_num;
	}
	public String getYz_num() {
		return yz_num;
	}
	public void setYz_num(String yz_num) {
		this.yz_num = yz_num;
	}
	public String getZe_num() {
		return ze_num;
	}
	public void setZe_num(String ze_num) {
		this.ze_num = ze_num;
	}
	public String getZy_num() {
		return zy_num;
	}
	public void setZy_num(String zy_num) {
		this.zy_num = zy_num;
	}
	public String getSwz_num() {
		return swz_num;
	}
	public void setSwz_num(String swz_num) {
		this.swz_num = swz_num;
	}
	
}

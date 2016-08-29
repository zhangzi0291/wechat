package com.test;

public class BaiduEntity {
	private String title;
	private String address;
	private String tags;
	private String latitude;
	private String longitude;
	private String coordType;
	private String geotableId;
	private String ak="WUsOfxw3ldMtLfhSYgwoIuBeXV0gMhrv";
	private String cellName;
	private String titl;
	private String bore;
	private String cellId;
	private String netType;
	
	@Override
	public String toString() {
		return "title=" + title + "&address=" + address + "&tags=" + tags + "&latitude=" + latitude
				+ "&longitude=" + longitude + "&coord_type=" + coordType + "&geotable_id=" + geotableId + "&ak=" + ak
				+ "&cell_name=" + cellName + "&titl=" + titl + "&bore=" + bore + "&cell_id=" + cellId + "&net_type="
				+ netType ;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCoordType() {
		return coordType;
	}
	public void setCoordType(String coordType) {
		this.coordType = coordType;
	}
	public String getGeotableId() {
		return geotableId;
	}
	public void setGeotableId(String geotableId) {
		this.geotableId = geotableId;
	}
	public String getAk() {
		return ak;
	}
	public void setAk(String ak) {
		this.ak = ak;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getBore() {
		return bore;
	}
	public void setBore(String bore) {
		this.bore = bore;
	}
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}

}

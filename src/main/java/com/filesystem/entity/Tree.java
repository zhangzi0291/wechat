package com.filesystem.entity;

import java.io.Serializable;

public class Tree implements Serializable{

	private static final long serialVersionUID = 8174562583598288276L;
	private Integer id;
	private Integer pid;
	private String dictKey;
	private String dictCn;
	private String dictValue;
	private Integer sort;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getDictKey() {
		return dictKey;
	}
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	public String getDictCn() {
		return dictCn;
	}
	public void setDictCn(String dictCn) {
		this.dictCn = dictCn;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	@Override
	public String toString() {
		return "Tree [id=" + id + ", pid=" + pid + ", dictKey=" + dictKey + ", dictCn=" + dictCn + ", dictValue="
				+ dictValue + ", sort=" + sort + ", status=" + status + "]";
	}



	
}

package com.demo.base;

/**
 * 分页记录类
 * @author liulang
 * @version Current version:&nbsp;&nbsp;V1.0<br>
 * <hr style="width:80%; float:left;">
 * <table style="width:100%;">
 * <tr><td width="20%" align="left">Operator</td><td width="30%" align="left">Time</td><td width="50%" align="left">Describe</td></tr>
 * <tr><td>liulang</td><td>2014年3月4日 上午12:14:25</td><td>create&nbsp;V1.0</td></tr>
 * </table>
 * @since   JDK 1.6
 */
public class Page {
	// 分页查询开始记录位置
	private int begin;
	// 分页查看下结束位置
	private int end;
	// 每页显示记录数
	private int pageSize;
	// 当前页码
	private int currentPage;
	
	private String orderName;
	
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	private String sord;

	public int getPageSize() {
		return pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public Page(int currentPage, int pageSize) {
		if (currentPage == 0){
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
		this.pageSize = pageSize;
		this.begin = (currentPage - 1) * pageSize;
		this.end = currentPage * pageSize;
	}

	public int getBegin() {
		this.begin = (currentPage - 1) * pageSize;
		return begin;
	}

	public int getEnd() {
		this.end = currentPage * pageSize;
		return end;
	}

	public void setBeginEnd(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage == 0){
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
	}

	public Page() {}
}

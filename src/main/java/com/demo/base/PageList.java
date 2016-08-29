package com.demo.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 分页查询结果类
 * @author liulang
 * @version Current version:&nbsp;&nbsp;V1.0<br>
 * <hr style="width:80%; float:left;">
 * <table style="width:100%;">
 * <tr><td width="20%" align="left">Operator</td><td width="30%" align="left">Time</td><td width="50%" align="left">Describe</td></tr>
 * <tr><td>liulang</td><td>2014年6月15日 下午2:56:28</td><td>create&nbsp;V1.0</td></tr>
 * </table>
 * @since   JDK 1.6
 */
public class PageList<E> implements Serializable {

	private static final long serialVersionUID = -3838017138856732724L;
	
	/** 表格数据 */
	private Collection<E> rows;
	/** 总记录数 */
	private int total;
	/** 总页数 */
	private int totalPage;
	
	private int pageSize;
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/** 页脚数据 */
	private Collection<?> footer;
	
	public PageList(Collection<E> rows, int total, Collection<?> footer) {
		this.rows = null == rows ? new ArrayList<E>(0) : rows;
		this.total = total;
		this.footer = null == footer ? new ArrayList<Object>(0) : footer;
	}
	
	public PageList(Collection<E> rows, int total) {
		this.rows = null == rows ? new ArrayList<E>(0) : rows;
		this.total = total;
	}
	
	public PageList(Collection<E> rows) {
		if(null != rows) {
			this.rows = rows;
			this.total = rows.size();
		}else {
			this.rows = new ArrayList<E>(0);
			this.total = 0;
		}
	}

	public Collection<E> getRows() {
		return rows;
	}

	public void setRows(Collection<E> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Collection<?> getFooter() {
		return footer;
	}

	public void setFooter(Collection<?> footer) {
		this.footer = footer;
	}
	
	public int getTotalPage() {
		if ( total == 0)
		{
			return 0;
		}
		
		if (0 == total%pageSize)
		{
			totalPage = total/pageSize;
		}else{
			totalPage = total/pageSize + 1;
		}
		
		
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public static void main(String[] args){
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(2);
		PageList<Integer> pagelist = new PageList<Integer>(list);
		pagelist.setTotal(1);
		//pagelist.setCurrentPage(currentPage);
		System.out.println(pagelist.getTotalPage());
	}
}

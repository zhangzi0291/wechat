package com.gecco.demo.test;

import java.util.List;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl = "http://gundam-exceed.main.jp/contents2.html", pipelines = { "consolePipeline" })
public class IndexGecco implements HtmlBean {
	
	@Request
	HttpRequest request;
	
	@Href(click=true)
	@HtmlField(cssPath="body > div > div > div.contents > h3 > table > tbody > tr > td.width5 > a")
	private List<String> pageList;
	
	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public List<String> getPageList() {
		return pageList;
	}

	public void setPageList(List<String> pageList) {
		this.pageList = pageList;
	}

	public static void main(String[] args) {
		HttpGetRequest start = new HttpGetRequest("http://gundam-exceed.main.jp/contents2.html");
		start.setCharset("GBK");
		GeccoEngine.create()
		.classpath("com.gecco.demo.test")
		.thread(100)
		.interval(2000)
		.start(start)
		.run();
	}
}

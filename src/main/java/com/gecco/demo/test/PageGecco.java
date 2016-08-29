package com.gecco.demo.test;

import java.util.List;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.pipeline.ConsolePipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

//http://www.meizitu.com/a/5402.html
@Gecco(matchUrl = "http://www.meizitu.com/tag/{code}.html", pipelines = { "consolePipeline" })
public class PageGecco implements HtmlBean{
	
	@RequestParameter
	private String Code;
	
	@Request
	HttpRequest request;
	
	@Href(click=true)
	@HtmlField(cssPath="#maincontent > div.inWrap > ul > li > div > div > a")
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
		HttpGetRequest start = new HttpGetRequest("http://www.meizitu.com/a/5402.html");
		start.setCharset("GBK");
		GeccoEngine.create()
		.classpath("com.gecco.demo.test")
		.thread(100)
		.interval(2000)
		.start(start)
		.run();
	}
}

package com.gecco.demo.test;

import java.util.List;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Downloader;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.Html;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.pipeline.ConsolePipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

//http://www.meizitu.com/a/5402.html
@Gecco(matchUrl = "http://gundam-exceed.main.jp/cggallery{Code}.html", pipelines = { "consolePipeline","myPipeline" })
public class MyGecco implements HtmlBean{
	
	@RequestParameter
	private String Code;
	
//	@Image(value="src",download="d:\\meizitu\\")
//	@HtmlField(cssPath="#picture > p > img")
//	@HtmlField(cssPath="#picture > p > img:nth-child(1)")
//	private List<String> pics;
	@Href
	@HtmlField(cssPath="body > div.base > div > div.cgworks > a:nth-child(even)")
	private List<String> download;
	public List<String> getDownload() {
		return download;
	}
	public void setDownload(List<String> download) {
		this.download = download;
	}
	public static void main(String[] args) {
		HttpGetRequest start = new HttpGetRequest("http://gundam-exceed.main.jp/cggallery1.html");
		start.setCharset("GBK");
		GeccoEngine.create()
		.classpath("com.gecco.demo.test")
		.thread(100)
		.interval(2000)
		.start(start)
		.run();
	}
}

package com.gecco.demo.test;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.SpiderBean;

@PipelineName("myPipeline")
@Service
public class MyPipeline implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		System.out.println("我的管子   "+JSON.toJSONString(bean));
		
	}
	
}

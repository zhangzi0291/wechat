package com.filesystem.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("file")
public class FileSysController {

	private Logger log = LoggerFactory.getLogger(FileSysController.class);
	
	@RequestMapping("/index")
	public String index(){
		return "file";
	}
	
	@RequestMapping("/fileList")
	@ResponseBody
	public List<Map<String, String> > fileList(String path){
		log.info(path);
		List<Map<String, String> > list=new ArrayList<>();
		File f=new File(path);
		if(!f.isDirectory()){
			log.info("不是文件夹");
			Map<String, String> map=new HashMap<>();
			map.put("name", f.getName());
			map.put("path", f.getPath());
			map.put("type", "file");
			list.add(map);
			return list;
		}
		for(String fileName:f.list()){
			System.out.println(fileName);
			Map<String, String> map=new HashMap<>();
			map.put("name", fileName);
			map.put("path", path+"/"+fileName);
			File file=new File(path+"/"+fileName); 
			if(file.isDirectory()){
				map.put("type", "dir");
			}else{
				map.put("type", "file");
			}
			list.add(map);
		}
		return list;
	}
}

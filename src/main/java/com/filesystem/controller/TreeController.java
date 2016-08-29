package com.filesystem.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.filesystem.entity.Tree;
import com.filesystem.service.TreeService;

@Controller
@RequestMapping("tree")
public class TreeController {

	private Logger log = LoggerFactory.getLogger(TreeController.class);
	@Resource
	TreeService service;
	
	@RequestMapping("/index")
	public String index(){
		return "tree";
	}
	
	@RequestMapping("/treeList")
	@ResponseBody
	public List<Tree> fileList(Map<String, Object> map,String pid){
		map.put("pid", pid);
		List<Tree> list=service.findTreeList(map);
		return list;
	}
	@RequestMapping("/addDict")
	@ResponseBody
	public String addDict(Map<String, Object> map,Tree tree){
		map.put("info", tree);
		try {
			if(service.insertTree(map)>0){
				return "新增成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "新增失败";
		}
		return "新增失败";
	}
	@RequestMapping("/editDict")
	@ResponseBody
	public String editDict(Map<String, Object> map,Tree tree){
		map.put("info", tree);
		try {
			if(service.editDict(map)>0){
				return "编辑成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "编辑失败";
		}
		return "编辑失败";
	}
	@RequestMapping("/deleteDict")
	@ResponseBody
	public String deleteDict(Map<String, Object> map,Tree tree){
		map.put("info", tree);
		try {
			if(service.deleteDict(map)>0){
				return "删除成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败";
		}
		return "删除失败";
	}
	@RequestMapping("/detailDict")
	@ResponseBody
	public Tree detailDict(Map<String, Object> map, Tree tree) {
		map.put("info", tree);		
		return service.detailDict(map);
	}
}	

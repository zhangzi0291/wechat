package com.filesystem.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.filesystem.dao.TreeDao;
import com.filesystem.entity.Tree;

@Service("treeService")
public class TreeService {

	@Resource
	TreeDao dao;

	public List<Tree> findTreeList(Map<String, Object> map) {
		return dao.findTreeList(map);
	}
	public Integer insertTree(Map<String, Object> map)throws Exception{
		return dao.insertTree(map);
	}
	public Integer editDict(Map<String, Object> map)throws Exception{
		return dao.editDict(map);
	}
	public Integer deleteDict(Map<String, Object> map)throws Exception{
		return dao.deleteDict(map);
	}
	public Tree detailDict(Map<String, Object> map){
		return dao.detailDict(map);
	}
	
}

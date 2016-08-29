package com.filesystem.dao;

import java.util.List;
import java.util.Map;

import com.filesystem.entity.Tree;

public interface TreeDao {
	public List<Tree> findTreeList(Map<String, Object> map);
	public Integer insertTree(Map<String, Object> map);
	public Integer deleteDict(Map<String, Object> map);
	public Integer editDict(Map<String, Object> map);
	public Tree detailDict(Map<String, Object> map);
}

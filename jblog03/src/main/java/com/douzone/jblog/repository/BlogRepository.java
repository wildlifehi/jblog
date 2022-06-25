package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public boolean insert(BlogVo vo) {
		
		return sqlSession.insert("blog.insert", vo) == 1;
	}

	public List<BlogVo> findAllByID(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		return sqlSession.selectList("blog.findAllByID", map);
	}

}

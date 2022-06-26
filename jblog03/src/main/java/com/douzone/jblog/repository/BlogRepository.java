package com.douzone.jblog.repository;

import java.util.HashMap;
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

	
	//블로그 리스트 전체를 뽑는 내용으로 아마 안쓸 것 
//	public List<BlogVo> findAllByID(String id) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("id", id);
//		
//		return sqlSession.selectList("blog.findAllByID", map);
//	}

	public BlogVo findByID(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		return sqlSession.selectOne("blog.findByID", map);
	}


	public boolean update(BlogVo vo) {
		
		return sqlSession.update("blog.Update", vo) == 1;
	}

}

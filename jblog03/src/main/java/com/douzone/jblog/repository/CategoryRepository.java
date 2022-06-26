package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(CategoryVo vo) {
		
		return sqlSession.insert("category.insert", vo) == 1;
	}

	public List<CategoryVo> findAllById(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		return sqlSession.selectList("category.findAllById", map);
	}

	public boolean categoryInsert(CategoryVo vo) {

		return sqlSession.insert("category.categoryInsert", vo) == 1;
	}

	public CategoryVo findCategoryByName(String name) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		
		return sqlSession.selectOne("category.findCategoryByName", map);
	}


	//휘민 도움
	public List<CategoryVo> findListbyId(String blogId) {
		return sqlSession.selectList("category.select", blogId);
	}
	

}

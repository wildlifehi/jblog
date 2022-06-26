package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> findAllByNo(Long categoryNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", categoryNo);
		
		return sqlSession.selectList("post.findAllByNo", map);
	}

	public PostVo findByNo(Long postNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", postNo);
		
		return sqlSession.selectOne("post.findByNo", map);
	}

	public boolean insert(PostVo vo) {
		return sqlSession.insert("post.insert", vo) == 1;
	}

}

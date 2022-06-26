package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getPostlistByCategoryNo(Long categoryNo) {
		
		return postRepository.findAllByNo(categoryNo);
	}

	public PostVo getPostContentsByPostNo(Long postNo) {

		return postRepository.findByNo(postNo);
	}

	public void postInsert(PostVo vo) {
		
		postRepository.insert(vo);
	}

}

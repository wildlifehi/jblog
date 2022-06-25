package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public void join(UserVo vo) {
		
		BlogVo blogvo = new BlogVo();
		blogvo.setId(vo.getId());
		blogvo.setTitle(vo.getName()+"님의 블로그 입니다.");
		blogvo.setLogo("default url");
		
		blogRepository.insert(blogvo);
	}

	public List<BlogVo> getBlogListById(String id) {
		
		return blogRepository.findAllByID(id);
	}

}

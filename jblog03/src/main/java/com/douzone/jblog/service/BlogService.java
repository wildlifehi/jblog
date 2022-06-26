package com.douzone.jblog.service;

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
		blogvo.setLogo("/assets/images/spring-logo.jpg");
		
		blogRepository.insert(blogvo);
	}
	
	//블로그 전체 내용을 뽑는 내용으로 아마 안쓸 것
//	public List<BlogVo> getBlogListById(String id) {
//		
//		return blogRepository.findAllByID(id);
//	}

	public BlogVo getBlogVoById(String id) {
		
		return blogRepository.findByID(id);
	}

	public void setBlogUpdate(BlogVo vo) {
		
		blogRepository.update(vo);
	}

}

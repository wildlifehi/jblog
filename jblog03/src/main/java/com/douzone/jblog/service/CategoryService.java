package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public void join(UserVo vo) {
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(vo.getId());
		
		categoryRepository.insert(categoryVo);
		
	}
}

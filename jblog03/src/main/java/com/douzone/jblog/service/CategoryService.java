package com.douzone.jblog.service;

import java.util.List;

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
		categoryVo.setName(vo.getId()+"의 새 카테고리");
		
		categoryRepository.insert(categoryVo);
		
	}

	public List<CategoryVo> getCategoryById(String id) {
		
		return categoryRepository.findAllById(id);
	}

	public void categoryInsert(CategoryVo categoryVo) {
		
		categoryRepository.categoryInsert(categoryVo);
	}

	public CategoryVo getCategoryByCategoryName(String name) {
		
		return categoryRepository.findCategoryByName(name);
	}
	
	
	//휘민
	public List<CategoryVo> getCategoryList(String blogId) {
		return categoryRepository.findListbyId(blogId);
	}
	
	
}

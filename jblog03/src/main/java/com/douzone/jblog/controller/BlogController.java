package com.douzone.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

//@ResponseBody

//@RequestMapping("/{id:(?!assets.*)}") // 중복되는거 맨 위로 올리고
@Controller
@RequestMapping("/blog")
public class BlogController {

	
	
	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/{id}")
	public String index(@PathVariable("id") String id) {
		
		//블로그 메인으로 들어가는 순간
		//해당 id에 대한 카테고리와 블로그를 리스트로 가져와야할 것.
		
		List<BlogVo> bloglist = blogService.getBlogListById(id);
		
		System.out.println(bloglist);
		
		List<CategoryVo> categorylist = categoryService.getCategoryById(id);
		
		System.out.println(categorylist);
		
		return "blog/main";
	}
	
	
	@RequestMapping("/admin")
	public String admin() {
		
		return "blog/admin/basic";
	}
	
	@RequestMapping("/category")
	public String category() {
		
		return "blog/admin/category";
	}
	
	@RequestMapping("/write")
	public String write() {
		
		return "blog/admin/write";
	}
	
//	{id} {category} {post}
//	@ResponseBody
//	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"}) 
//	public String index(
//	@PathVariable("id") String id, 
//	@PathVariable("pathNo1") Optional<Long> pathNo1,
//	@PathVariable("pathNo2") Optional<Long> pathNo2) {
//		
//	Long categoryNo = 0L;
//	Long postNo = 0L;
//	if(pathNo1.isPresent()) {
//		categoryNo = pathNo1.get();
//	} else if(pathNo1.isPresent()) {
//		
//		}
//	
//	return "blog/main";
//		categoryService.getCategories(id, categoryNo);
//	
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	// 여기서 어드민까지 전부 처리할 것.
//	// 컨트롤러는 더 이상 만들지 말고 여기서 다 처리하도록
//	
//	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"}) 
//	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"}) 
//	//들어오는 방법은 3가지이므로 3가지 멀티매핑으로 설정
//	public String index(
//			@PathVariable("id") String id, 
//			@PathVariable("pathNo1") Optional<Long> pathNo1,
//			@PathVariable("pathNo1") Optional<Long> pathNo2) {
//				
//			Long categoryNo = 0L;
//			Long postNo = 0L;
//			if(pathNo1.isPresent()) {
//				categoryNo = pathNo1.get();
//			} else if(pathNo1.isPresent()) {
//				
//			}
//			
//			categoryService.getCategories(id, categoryNo);
//			
//	}
//
//	@RequestMapping("/{id}")
//	public String adminBasic(@PathVariable("id") String id) {
//		
//	}
//	
//	
	
}

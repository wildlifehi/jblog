package com.douzone.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RequestMapping("/{id:(?!assets.*)}") // 중복되는거 맨 위로 올리고
@Controller
@RequestMapping("/blog")
public class BlogController {

	@RequestMapping("")
	public String main() {
		
		return "blog/main";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	// 여기서 어드민까지 전부 처리할 것.
//	// 컨트롤러는 더 이상 만들지 말고 여기서 다 처리하도록
//	
//	//@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"}) 
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

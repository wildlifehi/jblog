package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	
	List<BlogVo> bloglist;
	List<CategoryVo> categorylist;
	List<PostVo> postlist;
	PostVo postVo;
	
	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<String> pathNo1,
		@PathVariable("pathNo2") Optional<String> pathNo2, Model model) {
		
		
		Long categoryNo = 0L ;
		Long postNo = 0L ;
		
		if(pathNo2.isPresent()) {
			categoryNo = Long.parseLong(pathNo1.get());
			postNo = Long.parseLong(pathNo2.get());
			System.out.println("포스트랑 같이 올라옴");
		} else if(pathNo1.isPresent()) {
			categoryNo = Long.parseLong(pathNo1.get());
			System.out.println("이것만 실행될텐데?");
		}
		
		//Integer.parseInt(String.valueOf(categoryNo))
		

		//블로그 리스트는 쓸모없는데 우선은 그냥 박아두었다.
		bloglist = blogService.getBlogListById(id);
		//블로그 title은 필요합니다.
		
		
		//id를 통해 카테고리 리스트 받아오는 이친구는 카테고리 리스트 띄어주는 용도
		categorylist = categoryService.getCategoryById(id);
		//System.out.println(categorylist);
	
		//categoryNo 통해 포스트 리스트 받아오는 이친구는 포스트 리스트 띄어주는 용도
		postlist = postService.getPostlistByCategoryNo(categoryNo);
		//System.out.println(postlist);
		
		//이친구는 포스트 번호 받아서 해당번호에 해당하는 글 가져오는 용도.
		if (postNo != 0L) {
			postVo = postService.getPostContentsByPostNo(postNo);
			System.out.println(postVo);
		} 
		
		System.out.println(postVo);
		
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("postlist", postlist);
		model.addAttribute("postVo", postVo);

		
		return "blog/main";
	}
	
	
	//************블로그 수정 관련 핸들러 모음**************//
	@RequestMapping("/admin")
	public String admin() {
		
		//아마 여기서 블로그 리스트 쓰게될 것.
		return "blog/admin/basic";
	}
	
	
	//************카테고리 관련 핸들러 모음**************//
	//추후 그냥 누르면 겟 방식, 내용 저장시 포스트 방식으로 분기해줄 것 
	@RequestMapping("/category")
	public String category() {
		
		return "blog/admin/category";
		
	}
	
	@RequestMapping("/categoryInsert")
	public String categoryInsert(@PathVariable("id") String id, CategoryVo categoryVo, Model model) {
		
	//카테고리명 중복 안되게 코드 수정필요!!!!!!!!!
		categoryVo.setBlogId(id);
		categoryService.categoryInsert(categoryVo);
		
		return "redirect:/"+id;
	}
	
	
	//************포스트 관련 핸들러 모음**************//
	//추후 그냥 누르면 겟 방식, 내용 저장시 포스트 방식으로 분기해줄 것 
	@RequestMapping("/write")
	public String write(Model model) {
		
		model.addAttribute("categorylist", categorylist);
		return "blog/admin/write";
	}
	
	
	@RequestMapping("/postInsert")
	public String postInsert(@PathVariable("id") String id, @RequestParam("category") String category, PostVo postVo, Model model) {
		
		if ("default".equals(category)) {
			
			return "redirect:/blog/write";

		} else {
			
			CategoryVo categoryVo = categoryService.getCategoryByCategoryName(category);
			
			postVo.setCategoryNo(categoryVo.getNo());
			System.out.println(postVo);
			
			postService.postInsert(postVo);
			System.out.println("포스트 내용 추가 완료했어요");
			
			return "redirect:/"+id;
		}
		
	}
	
	
}
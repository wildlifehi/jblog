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
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		
		
		Long categoryNo = 0L ;
		Long postNo = 0L ;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		//Integer.parseInt(String.valueOf(categoryNo))
		

		//블로그 리스트는 쓸모없는데 우선은 그냥 박아두었다.
		bloglist = blogService.getBlogListById(id);
		//블로그 title은 필요합니다.
		
		//System.out.println(bloglist);
		
		
		
		//이친구는 카테고리 리스트 띄어주는 용도
		categorylist = categoryService.getCategoryById(id);
		//System.out.println(categorylist);
	
		//이친구는 포스트 리스트 띄어주는 용도
		postlist = postService.getPostlistByCategoryNo(categoryNo);
		//System.out.println(postlist);
		
		//이친구는 포스트 번호 받아서 해당번호에 해당하는 글 가져오는 용도.
		postVo = postService.getPostContentsByPostNo(postNo);
		//System.out.println(postVo);
		
		
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("postlist", postlist);
		model.addAttribute("postVo", postVo);

		
		return "blog/main";
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/admin")
	public String admin() {
		
		//아마 여기서 블로그 리스트 쓰게될 것.
		return "blog/admin/basic";
	}
	
	@RequestMapping("/category")
	public String category() {
		
		return "blog/admin/category";
	}
	
	
	@RequestMapping("/categoryInsert")
	public String categoryInsert(@PathVariable("id") String id, CategoryVo categoryVo, Model model) {
		
		
	//카테고리명 중복안되게 반드시 체크해줄것.
		
		categoryVo.setBlogId(id);
		categoryService.categoryInsert(categoryVo);
		
		return "redirect:/"+id;
	}
	
	
	@RequestMapping("/write")
	public String write(Model model) {
		
		model.addAttribute("categorylist", categorylist);
		return "blog/admin/write";
	}
	
	//@PathVariable("category") String category
	
	@RequestMapping("/postInsert")
	public String postInsert(@PathVariable("id") String id, @RequestParam("category") String category, PostVo postVo, Model model) {
		
		
		
		if ("default".equals(category)) {
			
			return "redirect:/blog/write";

		} else {
			
			System.out.println(category);
			
			CategoryVo categoryVo = categoryService.getCategoryByCategoryName(category);
			
			System.out.println(categoryVo);
			
			postVo.setCategoryNo(categoryVo.getNo());
			System.out.println(postVo);
			
			//postService.postInsert(postVo);
			System.out.println("내용 추가 완료했어요");
			
			return "redirect:/"+id;
		}
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@ResponseBody
//	@RequestMapping({"", "/{}", "/{pathNo1}/{pathNo2}"})
//	public String index(
//		@PathVariable("id") String id,
//		@PathVariable("pathNo1") Optional<Long> pathNo1,
//		@PathVariable("pathNo2") Optional<Long> pathNo2) {
//		
//		Long categoryNo = 0L;
//		Long postNo = 0L;
//		
//		if(pathNo2.isPresent()) {
//			categoryNo = pathNo1.get();
//			postNo = pathNo2.get();
//		} else if(pathNo1.isPresent()) {
//			categoryNo = pathNo1.get();
//		}
//		
//		return "BlogController.index(" + id + ", " + categoryNo + ", " + postNo + ")";
//	}
}
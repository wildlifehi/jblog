package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	BlogVo blogVo;
	List<CategoryVo> categorylist;
	List<PostVo> postlist;
	PostVo postVo;
	
	
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

		} else if(pathNo1.isPresent()) {
			categoryNo = Long.parseLong(pathNo1.get());
		}
		
		//Integer.parseInt(String.valueOf(categoryNo))
		

		//블로그 리스트는 아마 당장은 쓸일업을 것이다.
		//bloglist = blogService.getBlogListById(id);
		
		blogVo = blogService.getBlogVoById(id);
		//System.out.println(blogVo);
		
		//id를 통해 카테고리 리스트 받아오는 이친구는 카테고리 리스트 띄어주는 용도
		categorylist = categoryService.getCategoryById(id);
		//System.out.println(categorylist);
	
		//categoryNo 통해 포스트 리스트 받아오는 이친구는 포스트 리스트 띄어주는 용도
		postlist = postService.getPostlistByCategoryNo(categoryNo);
		//System.out.println(postlist);
		
		//이친구는 포스트 번호 받아서 해당번호에 해당하는 글 가져오는 용도.
		//없으면 널 값을 보내준다.
		if (postNo != 0L) {
			postVo = postService.getPostContentsByPostNo(postNo);
		} else {
			postVo = null;
		}
		
		

		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("postlist", postlist);
		model.addAttribute("postVo", postVo);
		
		return "blog/main";
	}
	
	
	//************블로그 수정 관련 핸들러 모음**************//
	@RequestMapping("/admin")
	public String admin(Model model) {
		
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/basic";
	}
	

	//업뎃 내용으로 아직 이미지는 업로드되지 않는 상태이다.
	@RequestMapping("/blogUpdate")
	public String blogUpdate(
				@PathVariable("id") String id,
				@RequestParam("title") String title,
				@RequestParam("logoFile") MultipartFile logoFile ) {
		
		String url = fileUploadService.restoreImage(logoFile);
		
		blogVo = blogService.getBlogVoById(id);
		//여기서 blogVo 내용을 수정해서 업데이트 해줄 것.
		blogVo.setTitle(title);
		
		//여기는 블로그 이미지 url을 넣어준다.
		blogVo.setLogo(url);
		
		blogService.setBlogUpdate(blogVo);
		
		return "redirect:/"+id;
	}

	
	
	
	//************카테고리 관련 핸들러 모음**************//
	//추후 그냥 누르면 겟 방식, 내용 저장시 포스트 방식으로 분기해줄 것 
	@RequestMapping("/category")
	public String category(@PathVariable("id") String id, Model model) {
		
		
		//카테고리 정보의 블로그 id는 안쓰므로 해당 위치에 post수를 넣어 보내주기
		//여기에 쓰인 categorylist는 전역변수와 다른 내용을 담고있다!!
		categorylist = categoryService.getCategoryPostById(id);
	

		model.addAttribute("categorylist", categorylist);
		//기본 블로그 정보
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/category";
		
	}
	
	@RequestMapping("/categoryInsert")
	public String categoryInsert(@PathVariable("id") String id, CategoryVo categoryVo, Model model) {
		
		//카테고리명 중복 안되게 코드 수정필요!!!!!!!!!
		//중복처리 안해주면 나중에 분명히 뻑납니다.
		categoryVo.setBlogId(id);
		categoryService.categoryInsert(categoryVo);
		
		return "redirect:/"+id;
	}
	
	
	//************포스트 관련 핸들러 모음**************//
	//추후 그냥 누르면 겟 방식, 내용 저장시 포스트 방식으로 분기해줄 것 
	@RequestMapping("/write")
	public String write(Model model) {
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categorylist", categorylist);
		return "blog/admin/write";
	}
	
	
	@RequestMapping("/postInsert")
	public String postInsert(@PathVariable("id") String id, @RequestParam("category") String category, PostVo postVo, Model model) {
		
		if ("default".equals(category)) {
			
			return "redirect:/"+id+"/write";

		} else {
			
			CategoryVo categoryVo = categoryService.getCategoryByCategoryName(category);
			
			postVo.setCategoryNo(categoryVo.getNo());
			
			postService.postInsert(postVo);
			System.out.println("포스트 내용 추가 완료했어요");
			
			return "redirect:/"+id;
		}
		
	}
	
	
}
package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		
		return "user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo, BindingResult result, Model model) {
		
		userService.join(userVo);
		return "/user/joinsuccess";
	}

//	@RequestMapping("/joinsuccess")
//	public String joinSuccess() {
//		return "user/joinsuccess";
//	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "user/login";
	}
	
	@RequestMapping(value="/auth")
	public void auth() {
	}

	


}


package com.douzone.jblog.service;

import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.UserVo;

@Service
public class CategoryService {

	public void join(UserVo vo) {
		userRepository.insert(vo);
		
	}
}

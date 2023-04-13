package com.jjh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	@RequestMapping("/user/home/main")
	@ResponseBody
	public String showMain() {
		return "Hi~";
	}
	
	@RequestMapping("/user/home/main2")
	@ResponseBody
	public String showMain2() {
		return "반가워~";
	}
	
	@RequestMapping("/user/home/main3")
	@ResponseBody
	public String showMain3() {
		return "잘가~";
	}
	
	private int count;
	public void UserHomeController() {
		
	}
	@RequestMapping("/user/home/main4")
	@ResponseBody
	public int showMain4() {
		return count++;
		
	}
}







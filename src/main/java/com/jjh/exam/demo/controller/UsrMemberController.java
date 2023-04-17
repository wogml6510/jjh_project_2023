package com.jjh.exam.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjh.exam.demo.service.MemberService;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId,String liginPw,String name, String nickName ,String phone, String email,Date regDate, Date updateDate) {
		memberService.join(loginId, liginPw, name, nickName, phone, email, regDate, updateDate);
				
		
		return "doJoin!!!!!!!";
	}
	
	
}







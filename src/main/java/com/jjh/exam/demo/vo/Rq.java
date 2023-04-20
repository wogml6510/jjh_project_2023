package com.jjh.exam.demo.vo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

// HttpSession을 Controller에서 중복사용됨 
// -> Request조금도 쓰기 쉽게 하는것(중복코드)
public class Rq {
	@Getter
	private boolean isLogined;	
	@Getter
	private int loginedMemberId;
	
	public Rq(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if( httpSession.getAttribute("loginedMemberId") != null ) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
	}
	
	
}

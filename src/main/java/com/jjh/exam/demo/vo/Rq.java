package com.jjh.exam.demo.vo;

import java.io.IOException;

import com.jjh.exam.demo.utill.Ut;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

// HttpSession을 Controller에서 중복사용됨 
// -> Request조금도 쓰기 쉽게 하는것(중복코드)
public class Rq {
	@Getter
	private boolean isLogined;	
	@Getter
	private int loginedMemberId;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		// 로그인 유저 확인
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
	
	public void prinHhistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		
		// 비어있는지 확인, 후 뒤로 돌아가기
		print("<script>");
		
		if(!Ut.empty(msg)) {
			println("alert('"+msg+"');");
		}
		println("history.back()");
		
		print("</script>");
	}
	
	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void println(String str) {
		print(str+"\n");
	}
	
}

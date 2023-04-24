package com.jjh.exam.demo.vo;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.jjh.exam.demo.service.MemberService;
import com.jjh.exam.demo.utill.Ut;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

// HttpSession을 Controller에서 중복사용됨 
// -> Request조금도 쓰기 쉽게 하는것(중복코드)
// Session관리
//자동주입이되는 // 객체가 하나만 존재하는게 아니라 각각의 객체별로 존재해야한다
@Component 																
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) 	
public class Rq {
	@Getter
	private boolean isLogined;	
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
		
		this.session = req.getSession();
		
		// 로그인 유저 확인
		HttpSession httpSession = req.getSession();
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if( session.getAttribute("loginedMemberId") != null ) {
			isLogined = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMember(loginedMemberId);
		}
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", memberService);	// Rq를 편하게 쓰기위해서 변경해줌
	}
	
	public void prinHhistoryBackJs(String msg) {	
		resp.setContentType("text/html; charset=UTF-8");
		print(Ut.jsHistoryBack(msg));
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

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
	
	public String historyBackJsOneview(String msg){
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "common/js";
	}

	public String jsHistoryBack(String msg) {
		return Ut.jsHistoryBack(msg);
	}
	
	public String jsReplace(String msg, String uri) {
		return Ut.jsReplace(msg, uri);
	}
	
}

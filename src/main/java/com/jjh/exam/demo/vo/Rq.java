package com.jjh.exam.demo.vo;

import java.io.IOException;
import java.util.Map;

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
//자동주입이되는 // 객체가 하나만 존재하는게 아니라 각각의 객체별로 존재해야한다, Rq객체생성을 spring에게 맡김
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
	private Map<String, String> paramMap;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
		
		paramMap = Ut.getParamMap(req);  // ??
		
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
		
		this.req.setAttribute("rq", this);	// Rq를 편하게 쓰기위해서 변경해줌
	}
	
	public boolean isNotLogined() {
		return !isLogined;
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
	
	public String getCurrentUri() {
		String currentUri = req.getRequestURI();	// /use/article
		String queryString = req.getQueryString();
		
		if(queryString != null && queryString.length() > 0){
			currentUri += "?" + queryString;
		}
		return currentUri;
	}
	
	public String getEncodedCurrentUri() {
		return Ut.getUriEncoded(getCurrentUri());
	}

	// 이 메서드는 Rq객체가 자연스럽게 생성되도록 유도하는 역할을 한다.
	// 지우면X, 편의를 위해 BeforActionInterceptor에서 꼭 호출 해줘야 한다.
	public void initOnBeforActionInterceptor() {
	}

	public void printReplaceJs(String msg, String uri) {
		resp.setContentType("text/html; charset=UTF-8");
		print(Ut.jsReplace(msg, uri));
	}

	public String getLoginUri() {
		return "../member/login?afterLoginUri=" + getAfterLoginUri();
	}

	public String getAfterLoginUri() {
		String requestUri = req.getRequestURI();
		// 주소줄 가져와서 비교( 로그인이 필요없는 페이지의 주소줄은 return해줌)
		// [ 로그인 후 돌아가면 안되는 페이지 URL ]
		switch(requestUri) {
		case "/usr/member/login":
		case "/usr/member/join":
		case "/usr/member/findLoginId":
		case "/usr/member/findLoginPw":
			return Ut.getUriEncoded(Ut.getStrAttr(paramMap, "afterLoginUri", ""));
		}
		return getEncodedCurrentUri();
	}
	
	public String getLogoutUri() {
		return "../member/doLogout?afterLogoutUri=" + getAfterLogoutUri();
	}
	public String getAfterLogoutUri() {
		String requestUri = req.getRequestURI();
//		switch(requestUri) {
//		case "/usr/article/write":
//			return Ut.getUriEncoded(Ut.getStrAttr(paramMap, "afterLogioutUri", ""));
//		}
		return getEncodedCurrentUri();
	}
	
	public String getArticleDetailUriFromArticleList(Article article) {
		return "../article/detail?id=" + article.getId() + "&listUri=" + getEncodedCurrentUri();
	}
	
}




package com.jjh.exam.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jjh.exam.demo.service.MemberService;
import com.jjh.exam.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
												
@Component									// spring안에 배당되어있는 interceptor
public class BeforeActionInterceptor implements HandlerInterceptor {
	// Interceptor는 컨트롤러에 대한 요청 처리 전/후에 추가로 작업을 수행하며, 
	// 인터셉터는 DispatcherServlet과 컨트롤러 사이에 위치해 요청에 대한 처리를 가로채고 가공
	
	// BeforeActionInterceptor : Action실행되기 전에 먼저실행됨, preHandle이 먼저 실행됨
	// 즉 Rq를 Controller에 만들어주지 않아도 됨, 전달이 된다
	// preHandle() : 컨트롤러 실행 전에 실행되며, 
	// 컨트롤러가 실행되기 전에 인터셉터에서 처리할 작업을 구현할 수 있음. 
	// 이 메서드에서 false를 반환하면 요청을 처리하는 과정이 중지 됨.
	
	private Rq rq;
	public BeforeActionInterceptor(Rq rq) {
		this.rq = rq;
	}
	@Autowired
	private MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		rq.initOnBeforActionInterceptor();
		// 이제는 Rq 객체가 자동으로 만들어지기 때문에 필요 없음.
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}

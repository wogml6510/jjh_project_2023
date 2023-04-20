package com.jjh.exam.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jjh.exam.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
												
@Component									// spring안에 배당되어있는 interceptor
public class NeedLoginInterceptor implements HandlerInterceptor {
	// BeforeActionInterceptor : Action실행되기 전에 먼저실행됨, preHandle이 먼저 실행됨
	// 즉 Rq를 Controller에 만들어주지 않아도 됨, 전달이 된다
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		
		System.out.println("로그인 필요!!");

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}

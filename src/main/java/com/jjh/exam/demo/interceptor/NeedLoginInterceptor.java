package com.jjh.exam.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jjh.exam.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
												
@Component								
public class NeedLoginInterceptor implements HandlerInterceptor {
	// NeedLoginInterceptor : 필터역할, 먼저 실행됨으로서 로그인 여부 확인
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		// Rq에서 로그인 여부를 물어봄
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(!rq.isLogined()) {
			
			rq.prinHhistoryBackJs("로그인 후 이용해주세요.");
			return false;
		}
		
		System.out.println("로그인 필요!!");

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}

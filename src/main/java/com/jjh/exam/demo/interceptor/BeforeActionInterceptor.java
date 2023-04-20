package com.jjh.exam.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
												
@Component									// spring안에 배당되어있는 interceptor
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		System.out.println("실행가능?");

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}

package com.jjh.exam.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jjh.exam.demo.interceptor.BeforeActionInterceptor;

@Configuration //
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	// BeforeActionInterceptor불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;

	// InterceptorRegistry?
	// addPathPatterns 이 이후에 들어오는 모든 인터셉터 작동해, excluedPathPatterns 이거 제외하고.
	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override	// addInterceptors: 프로그램 실행되고 딱 한번만 실행.
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
	}
}

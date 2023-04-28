package com.jjh.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jjh.exam.demo.interceptor.BeforeActionInterceptor;
import com.jjh.exam.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	// BeforeActionInterceptor불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;

	// BeforeActionInterceptor불러오기
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;

	// InterceptorRegistry?
	// addPathPatterns 이 이후에 들어오는 모든 인터셉터 작동해, excluedPathPatterns 이거 제외하고.
	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override // addInterceptors: 프로그램 실행되고 딱 한번만 실행.
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration ir;
		ir = registry.addInterceptor(beforeActionInterceptor);
		ir.addPathPatterns("/**");
		ir.excludePathPatterns("/error");
		ir.excludePathPatterns("/resource/**");
		// 로그인여부확인
		ir = registry.addInterceptor(needLoginInterceptor);
		ir.addPathPatterns("/usr/member/myPage");
		ir.addPathPatterns("/usr/member/checkPassword");
		ir.addPathPatterns("/usr/member/doCheckPassword");
		ir.addPathPatterns("/usr/member/modify");
		ir.addPathPatterns("/usr/member/doModify");
		ir.addPathPatterns("/usr/member/doDelete");
		
		ir.addPathPatterns("/usr/reply/write");
		ir.addPathPatterns("/usr/reply/doWrite");
		ir.addPathPatterns("/usr/reply/modify");
		ir.addPathPatterns("/usr/reply/doModify");
		ir.addPathPatterns("/usr/reply/doDelete");

		ir.addPathPatterns("/usr/article/write");
		ir.addPathPatterns("/usr/article/doWrite");
		ir.addPathPatterns("/usr/article/modify");
		ir.addPathPatterns("/usr/article/doModify");
		ir.addPathPatterns("/usr/article/doDelete");

		ir.addPathPatterns("/usr/reactionPoint/doGoodReaction");
		ir.addPathPatterns("/usr/reactionPoint/doBadReaction");
		ir.addPathPatterns("/usr/reactionPoint/doCancelGoodReaction");
		ir.addPathPatterns("/usr/reactionPoint/doCancelDadReaction");
		
		// before실행후 need실행
	}
}

package com.jjh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjh.exam.demo.service.ArticleService;
import com.jjh.exam.demo.utill.Ut;
import com.jjh.exam.demo.vo.Article;
import com.jjh.exam.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	// 액션 메서드 시작
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if( httpSession.getAttribute("loginedMemberId") != null ) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		if(isLogined == false) {
			return ResultData.from("F-A","로그인 후 이용해주세요.");			
		}
		
		// title, body 입력하지 않았을때(공백,여백) 메세지
		if(Ut.empty(title) ) {
			return ResultData.from("F-1","title(을)를 입력해주세요.");
		}
		if(Ut.empty(body) ) {
			return ResultData.from("F-2","body(을)를 입력해주세요.");
		}
		ResultData<Integer> writeArDataRd = articleService.writeArticle(loginedMemberId, title, body);
		
		int id= writeArDataRd.getData1();
		
		Article article = articleService.getArticle(id);
		
		return ResultData.from(writeArDataRd.getResultCode(), writeArDataRd.getMsg(), "article", article);
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "게시물 리스트 입니다.", "articles", articles);
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<?> getArticle(int id) {
		Article article = articleService.getArticle(id);
		
		if ( article == null ) {
			//					
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), "article", article);
	}
	
	// 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if( httpSession.getAttribute("loginedMemberId") != null ) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		if(isLogined == false) {
			return ResultData.from("F-A","로그인 후 이용해주세요.");			
		}
		
		Article article = articleService.getArticle(id);
		
		if( article.getMemberId() != loginedMemberId ) {
			return ResultData.from("F-2", "본인글만 삭제 가능합니다.");
		}
		
		if ( article == null ) {
			return ResultData.from("F-1",Ut.f("%d번 게시물이 존재하지 않습니다.", id ));
		}
		
		articleService.deleteArticle(id);
		
		return ResultData.from("S-1",Ut.f("%d번 게시물을 삭제하였습니다.", id ), "id", id );
	}
	
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpSession httpSession,int id, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		// 로그인체크
		if( httpSession.getAttribute("loginedMemberId") != null ) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		if(isLogined == false) {
			return ResultData.from("F-A","로그인 후 이용해주세요.");			
		}
		
		Article article = articleService.getArticle(id);
		
		//
		if ( article == null ) {
			return ResultData.from("F-1",Ut.f("%d번 게시물이 존재하지 않습니다.", id ));
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(loginedMemberId, article);
		
		if( actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}
		
		
		articleService.modifyArticle(id, title, body);
		
		return articleService.modifyArticle(id, title, body);
	}
	// 액션 메서드 끝

}
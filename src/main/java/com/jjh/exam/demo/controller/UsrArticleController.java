package com.jjh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjh.exam.demo.service.ArticleService;
import com.jjh.exam.demo.utill.Ut;
import com.jjh.exam.demo.vo.Article;
import com.jjh.exam.demo.vo.ResultData;
import com.jjh.exam.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	// 액션 메서드 시작
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody           // 클라이언트가 요청을 했을때만 받겠다.
	public String doWrite(HttpServletRequest req, String title, String body, String replaceUri) {
		// Rq rq = new Rq(req); ㄱ Rq객체를 한번만 만들면됨
		Rq rq = (Rq)req.getAttribute("rq");
		
	
		// title, body 입력하지 않았을때(공백,여백) 메세지
		if(Ut.empty(title) ) {
			return rq.jsHistoryBack("title(을)를 입력해주세요.");
		}
		if(Ut.empty(body) ) {
			return rq.jsHistoryBack("body(을)를 입력해주세요.");
		}
		ResultData<Integer> writeArDataRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		
		int id= writeArDataRd.getData1();
		
		if(Ut.empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", id);
		}
		
		return rq.jsReplace(Ut.f("%d번 글이 생성되었습니다.", id), body);
	}

	
	@RequestMapping("/usr/article/write")
	public String showWrite() {
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req,  Model model) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId());
		model.addAttribute("articles",articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		model.addAttribute("article",article);
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<?> getForPrintArticle(HttpServletRequest req, int id) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if ( article == null ) {
			//					
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), "article", article);
	}
	
	// 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(req.getContentLength(), id);
		
		if ( article == null ) {
			ResultData.from("F-1",Ut.f("%d번 게시물이 존재하지 않습니다.", id ));
		}
		if( article.getMemberId() != rq.getLoginedMemberId() ) {
			return rq.jsHistoryBack("권한이 없습니다");
		}
		
		articleService.deleteArticle(id);
		
		return rq.jsReplace(Ut.f("%d번 게시물을 삭제하였습니다.", id ), "../article/list" );
	}
	
	
	@RequestMapping("/usr/article/modify")
	public String Showmodify(HttpServletRequest req,Model model, int id, String title, String body) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if ( article == null ) {
			return rq.historyBackJsOneview(Ut.f("%d번 게시물이 존재하지 않습니다.", id ));
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
		
		if( actorCanModifyRd.isFail()) {
			return rq.historyBackJsOneview(actorCanModifyRd.getMsg());
		}
		
		model.addAttribute("article",article);
		
		return "usr/article/modify";
	}
	

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req,int id, String title, String body) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		//
		if ( article == null ) {
			return rq.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id ));
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
		
		if( actorCanModifyRd.isFail()) {
			return rq.historyBackJsOneview(actorCanModifyRd.getMsg());
		}
		
		articleService.modifyArticle(id, title, body);
		
		return rq.jsReplace(Ut.f("%d번 글이 수정되었습니다.", id), Ut.f("../article/detail?id=%d", id));
	}
	// 액션 메서드 끝

}
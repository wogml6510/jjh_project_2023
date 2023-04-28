package com.jjh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjh.exam.demo.service.ReplyService;
import com.jjh.exam.demo.utill.Ut;
import com.jjh.exam.demo.vo.Reply;
import com.jjh.exam.demo.vo.ResultData;
import com.jjh.exam.demo.vo.Rq;

@Controller
public class UsrReplyController {
	private ReplyService replyService;
	private Rq rq;
	
	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	String doWrite(String relTypeCode, int relId, String body, String replaceUri) {
		if(Ut.empty(relTypeCode)) {
			return rq.jsHistoryBack("replceUri(을)를 입력해주세요.");
		}
		if(Ut.empty(relId)) {
			return rq.jsHistoryBack("relId(을)를 입력해주세요.");
		}
		if(Ut.empty(body)) {
			return rq.jsHistoryBack("replaceUri(을)를 입력해주세요.");
		}
		
		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);
		
		int id = writeReplyRd.getData1();
		
		if(Ut.empty(replaceUri)) {
			switch(relTypeCode) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", relId);
				break;
			}
		}
		
		return rq.jsReplace(writeReplyRd.getMsg(), replaceUri);
	}
	
	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	String doDelete(int id, String replaceUri) {
		if(Ut.empty(id)) {
			return rq.jsHistoryBack("replceUri(을)를 입력해주세요.");
		}
		
		Reply reply = replyService.getForPrintPeply(rq.getLoginedMemberId(), id);
		
		if(reply==null) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글이 존재하지 않습니다.", id));
		}
		
		if(reply.isExtra_actorCanDelete() == false) {
			return rq.jsHistoryBack(Ut.f("%d번 댓글을 삭제할 권한이 없습니다.", id));
		}
		
		ResultData deleteReplyRd = replyService.deleteReply(id);
		
		if(Ut.empty(replaceUri)) {
			switch(reply.getRelTypeCode()) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
				break;
			}
		}
		
		return rq.jsReplace(deleteReplyRd.getMsg(), replaceUri);
	}
	
}

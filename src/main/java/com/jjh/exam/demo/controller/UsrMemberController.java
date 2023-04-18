package com.jjh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjh.exam.demo.service.MemberService;
import com.jjh.exam.demo.utill.Ut;
import com.jjh.exam.demo.vo.Member;
import com.jjh.exam.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	// 인스턴스 변수 시작
	@Autowired
	private MemberService memberService;

	// 인스턴스 변수 끝

	// 생성자
	public UsrMemberController() {

	}
	// 서비스 메서드 시작

	@RequestMapping("/usr/member/getMember")
	@ResponseBody
	private Object getMember(int id) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원정보가 존재하지 않습니다.";
		}
		return Member;
	}

	// 서비스 메서드 끝

	// 액션 메서드 시작
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		if (Ut.empty(loginId)) {
			return ResultData.from("F-3", "loginId(을)를 입력해주세요.");
		}
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-4", "loginPw(을)를 입력해주세요.");
		}
		if (Ut.empty(name)) {
			return ResultData.from("F-5", "name(을)를 입력해주세요.");
		}
		if (Ut.empty(nickname)) {
			return ResultData.from("F-6", "nickname(을)를 입력해주세요.");
		}
		if (Ut.empty(cellphoneNo)) {
			return ResultData.from("F-7", "cellphoneNo(을)를 입력해주세요.");
		}
		if (Ut.empty(email)) {
			return ResultData.from("F-7", "email(을)를 입력해주세요.");
		}
		
		// S-1
		// 회원가입이 완료되었습니다.
		// 7(몇번회원인지)
		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail()) {
			return joinRd;
		}

		Member Member = memberService.getMember((int) joinRd.getData1());

		return ResultData.newData(joinRd, Member);
	   
	}

	@RequestMapping("/usr/member/getMembers")
	@ResponseBody
	public List<Member> getMembers() {
		return memberService.getMembers();
	}

	@RequestMapping("/usr/member/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원이 존재하지 않습니다.";
		}

		memberService.deleteMember(id);
		return id + "번 회원정보가 삭제되었습니다.";
	}

	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		Member Member = memberService.getMember(id);
		if (Member == null) {
			return id + "번 회원이 존재하지 않습니다.";
		}

		memberService.modifyMember(id, loginId, loginPw, name, nickname, cellphoneNo, email);
		return id + "번 회원정보가 수정되었습니다.";
	}
}
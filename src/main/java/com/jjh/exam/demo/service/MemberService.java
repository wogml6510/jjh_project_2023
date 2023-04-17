package com.jjh.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jjh.exam.demo.repository.MemberRepository;
import com.jjh.exam.demo.vo.Member;

@Service
public class MemberService {
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Member getMember(int id) {
		return memberRepository.getMember(id);
	}

	public List<Member> getMembers() {
		return memberRepository.getMembers();
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {

		// 로그인아이디 중복체크
		Member oldMember = getMemberByIdLoginId(loginId);
		if (oldMember != null) {
			return -1;
		}

		// 별명,폰번호,이메일 중복체크
		oldMember = getMemberByNickNameNPhoneNEmail(nickname, cellphoneNo, email);
		if (oldMember != null) {
			return -2;
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		return memberRepository.getLastInsertId();
	}

	// 아이디 중복체크
	private Member getMemberByIdLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	// 중복체크
	private Member getMemberByNickNameNPhoneNEmail(String nickname, String cellphoneNo, String email) {
		return memberRepository.getMemberByNickNameNPhoneNEmail(nickname, cellphoneNo, email);
	}

	public void deleteMember(int id) {
		memberRepository.deleteMember(id);
	}

	public void modifyMember(int id, String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		memberRepository.modifyMember(id, loginId, loginPw, name, nickname, cellphoneNo, email);
	}
}
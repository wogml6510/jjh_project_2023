package com.jjh.exam.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jjh.exam.demo.repository.MemberRepository;
import com.jjh.exam.demo.vo.MemberVO;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public MemberVO getMember(String loginId) {
		return memberRepository.getMember(loginId);
	}
	
	public List<MemberVO> getMemberList(){
		return memberRepository.getMemberList();
	}
	public int insertMember(String loginId,String liginPw,String name, String nickName ,String phone, String email,Date regDate, Date updateDate) {
		memberRepository.insertMember(loginId, liginPw, name, nickName, phone, email, regDate, updateDate);
		return memberRepository.getLastInsertId();
	}

	public void join(String loginId, String liginPw, String name, String nickName, String phone, String email,
			Date regDate, Date updateDate) {
		
	}

}

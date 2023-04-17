package com.jjh.exam.demo.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jjh.exam.demo.vo.MemberVO;

public interface MemberRepository {

	public MemberVO getMember(@Param("id") String id);

	public List<MemberVO> getMemberList();
	
	public void insertMember(@Param("loginId") String loginId,@Param("loginPw")String liginPw
							,@Param("name")String name, @Param("nickname")String nickName
							,@Param("phone")String phone, @Param("email")String email
							,@Param("regDate")Date regDate, @Param("updateDate")Date updateDate);
	
	public int getLastInsertId();
}

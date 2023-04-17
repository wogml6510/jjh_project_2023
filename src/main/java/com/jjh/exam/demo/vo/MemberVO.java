package com.jjh.exam.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	private int id;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String phone;
	private String email;
	
	private Date regDate = new Date();
	private Date updateDate = new Date();
	
	
	
}

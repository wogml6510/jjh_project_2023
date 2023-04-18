package com.jjh.exam.demo.vo;

import lombok.Getter;

public class ResultData {
	// S-1, S-2.. : 성공
	// F-1 : 실패
	@Getter
	private String resultCode;	// 결과 값
	@Getter
	private String msg; 		// 오류 메세지 
	@Getter
	private Object data1; 		// 비고란?

	private ResultData() {

	}

	public static ResultData from(String resultCode, String msg) {

		return from(resultCode, msg, null);
	}

	//
	public static ResultData from(String resultCode, String msg, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 = data1;

		return rd;
	}

	// 코드가 성공했는지 여부
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	// 실패여부
	public boolean isFail() {
		return isSuccess() == false;
	}

}

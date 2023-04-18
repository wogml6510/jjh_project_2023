package com.jjh.exam.demo.vo;

import lombok.Getter;

public class ResultData<DT> {
	// S-1, S-2.. : 성공
	// F-1 : 실패
	@Getter
	private String resultCode;	// 결과 값
	@Getter
	private String msg; 		// 오류 메세지 
	@Getter
	private DT data1; 		// 비고란?

	private ResultData() {

	}
	// 실패
	public static ResultData from(String resultCode, String msg) {
		return from(resultCode, msg, null);
	}

	// 성공
	public static <DT> ResultData<DT> from(String resultCode, String msg, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 = data1;

		return rd;
	}

	public static <DT> ResultData<DT> newData(ResultData joinRd, DT newData) {
		return from(joinRd.getResultCode(), joinRd.getMsg(), newData);
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

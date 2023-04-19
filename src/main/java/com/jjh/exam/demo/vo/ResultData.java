package com.jjh.exam.demo.vo;

import lombok.Getter;
// 범용보고서 이력서 양식?
public class ResultData<DT> {
	// S-1, S-2.. : 성공
	// F-1 : 실패
	@Getter
	private String resultCode;	// 결과 값
	@Getter
	private String msg; 		// 오류 메세지 
	@Getter
	private String data1Name;
	@Getter
	private DT data1; 			// 데이터
	

	private ResultData() {

	}
	// 실패  가지고온 데이터 토스만 함
	public static ResultData from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}

	// 성공
	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name ,DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;

		return rd;
	}
	// 가지고온 데이터 토스
	public static <DT> ResultData<DT> newData(ResultData oldRd, String data1Name, DT data1) {
		return from(oldRd.getResultCode(), oldRd.getMsg(), data1Name, data1);
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

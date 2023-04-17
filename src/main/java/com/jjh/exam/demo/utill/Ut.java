package com.jjh.exam.demo.utill;

public class Ut {
	
	public static boolean empty(Object obj) {
		// null체크
		if(obj == null) {
			return true;
		}
		// 이 객체가 String객체 체크
		if( obj instanceof String == false) {
			return true;
		}
		
		String str = (String) obj;
		
		return str.trim().length() == 0;
	}
	
}

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
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}

	public static String jsHistoryBack(String msg) {
		return Ut.f("""
				<script>
				const msg = '$s'.trim();
				if(msg.length > 0){
					alert(msg);
				}
				history.back();
				</script>				
				""",msg);
	}
	// 메세지 보여주고 나서 이동
	public static String jsReplace(String msg, String uri) {
		if ( msg == null){
			msg ="";
		}
		if ( uri == null){
			uri ="";
		}
		return Ut.f("""
				<script>
				const msg = '%s'.trim();
				if(msg.length > 0){
					alert(msg);
				}
				location.replace('%s');
				</script>				
				""", msg, uri);
	}
}

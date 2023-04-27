package com.jjh.exam.demo.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;	
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	private int hitCount;
	private int goodReactionPoint;
	private int badReactionPoint;
	
	//private int extra_sumReactionPoint;
	
	private String extra_writerName;
	private boolean extra_actorCanDelete;
	private boolean extra_actorCanModify;
	
	public String getRegDateForPrint() {
		return regDate.substring(2,16);
	}
	
	public String getUpdateDateForPrint() {
		return updateDate.substring(2,16);
	}
	
	public String forPrintintType1RegDate() {
		return regDate.substring(2, 16).replace(" ", "<br>");
	}

	public String forPrintintType1UpdateDate() {
		return updateDate.substring(2, 16).replace(" ", "<br>");
	}
	
	public String getForPrintBody() {
		return body.replaceAll("\n", "<br>");
	}
}


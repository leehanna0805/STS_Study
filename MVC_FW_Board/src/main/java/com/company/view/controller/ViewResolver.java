package com.company.view.controller;

public class ViewResolver {
	//필드 선언
	public String prefix; //접두사
	public String suffix; //접미사
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	//사용자 정의 메소드
	//ex)포워딩 할때 ==> view로 "getBoardList"넘어옴. 
	//				prefix: "./" 
	//				suffix: ".jsp" 
	//	 return "./getBoardList.jsp"
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
}

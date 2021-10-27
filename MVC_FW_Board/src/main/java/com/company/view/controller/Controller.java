package com.company.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	/*
	 * 구체적인 Controller 클래스들을 구현하기에 앞서 
	 * 모든 컨트롤러를 같은 타입으로 관리하기위한 인터페이스를 만들어야 함.
	 */
	
	//추상메소드. 모든 핸들러 클래스는 이 메소드를 재정의해서 사용한다.
	String handleRequest(HttpServletRequest request, HttpServletResponse response);
}

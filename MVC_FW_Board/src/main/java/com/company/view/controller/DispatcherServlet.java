package com.company.view.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//객체 참조변수 선언
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	//수동으로 메소드 오버라이드 시키는 방법: i 입력후 Ctrl+space
	//init()은 개발자가 아닌 서블릿컨테이너가 자동으로 호출함.
	@Override
	public void init() throws ServletException { 
		
		//멤버변수 초기화
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		
		//접두사, 첩미사 설정
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");	
	}
	
    public DispatcherServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	//사용자 정의 메소드. 응답처리 
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//1. 클라이언트의 요청 path 정보 추출
		String uri = request.getRequestURI();
		int lastposition = uri.lastIndexOf("/");
		String filePath = uri.substring(lastposition); // "/login.do" 얻어옴
		
		//2. Handler Mapping 을 통해서 filePath에 해당하는  Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(filePath); //해당컨트롤러를 new 로 return해줌
		
		//3. 리턴된 Ctonroller를 실행한다.
		String viewName = ctrl.handleRequest(request, response); //login 컨트롤러의 handleRequest()호출
	}
}

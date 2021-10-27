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
	@Override
	public void init() throws ServletException { //개발자가 아닌 서블릿컨테이너가 자동으로 호출함.
		//멤버변수 초기화
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
	}
	
    public DispatcherServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

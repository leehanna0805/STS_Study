package com.company.view.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.Model2_Board.board.BoardDAO;
import com.company.Model2_Board.board.BoardDO;
import com.company.Model2_Board.user.UserDAO;
import com.company.Model2_Board.user.UserDO;

import java.util.List;
/**
 * doGet(get방식), doPost(post방식) 부분, 사용자정의 메소드 process 개발자가 코딩해주면됨.
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DispatcherServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response); //사용자 정의 메소드 호출
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //폼에서 넘어온 한글 안깨지게
		process(request, response); //사용자 정의 메소드 호출
	}
	
	//사용자 정의 메소드 구현. 클라이언트의 요청에 응답하는 메소드 !!
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//1. 클라이언트의 요청 path 정보를 추출한다. ex)"/login.do" 만 가져옴
		String uri = request.getRequestURI();
		String filePath = uri.substring(uri.lastIndexOf("/"));
		
		/*
		 * 2. 클라이언트의 요청 filePath 에 따라 적절히 분기 처리한다. 
		 */
		//로그인 인증처리 (login.jsp에서 데이터 넘어옴)
		if(filePath.equals("/login.do")) {
			//Model1의 login_proc.jsp내용이 여기로!
			System.out.println("로그인 처리됨!");
			
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			
			UserDO userDO = new UserDO();
			userDO.setId(id);
			userDO.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserDO user = userDAO.getUser(userDO); //헷갈린다. 공부행
			
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("IdKey", id);
				//System.out.println("로그인 성공");
				response.sendRedirect("getBoardList.do"); //.do로 응답 보내라!
			}else {
				//System.out.println("로그인 실패");
				response.sendRedirect("login.jsp"); //여긴 jsp로 보내라. 
			}
			
		//getBoardList.do (게시글 목록 보기)
		}else if(filePath.equals("/getBoardList.do")) {
			String searchField = "";
			String searchText = "";
			
			if(request.getParameter("searchCondition") != "" && 
					request.getParameter("searchCondition") != "") {
				searchField = request.getParameter("searchCondition");
				searchText = request.getParameter("searchCondition");
			}
			
			BoardDAO boardDAO = new BoardDAO();
			List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
		}
	}
}










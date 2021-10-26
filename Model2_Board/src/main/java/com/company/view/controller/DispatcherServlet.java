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
 * doGet(get���), doPost(post���) �κ�, ��������� �޼ҵ� process �����ڰ� �ڵ����ָ��.
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DispatcherServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response); //����� ���� �޼ҵ� ȣ��
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //������ �Ѿ�� �ѱ� �ȱ�����
		process(request, response); //����� ���� �޼ҵ� ȣ��
	}
	
	//����� ���� �޼ҵ� ����. Ŭ���̾�Ʈ�� ��û�� �����ϴ� �޼ҵ� !!
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//1. Ŭ���̾�Ʈ�� ��û path ������ �����Ѵ�. ex)"/login.do" �� ������
		String uri = request.getRequestURI();
		String filePath = uri.substring(uri.lastIndexOf("/"));
		
		/*
		 * 2. Ŭ���̾�Ʈ�� ��û filePath �� ���� ������ �б� ó���Ѵ�. 
		 */
		//�α��� ����ó�� (login.jsp���� ������ �Ѿ��)
		if(filePath.equals("/login.do")) {
			//Model1�� login_proc.jsp������ �����!
			System.out.println("�α��� ó����!");
			
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			
			UserDO userDO = new UserDO();
			userDO.setId(id);
			userDO.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserDO user = userDAO.getUser(userDO); //�򰥸���. ������
			
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("IdKey", id);
				//System.out.println("�α��� ����");
				response.sendRedirect("getBoardList.do"); //.do�� ���� ������!
			}else {
				//System.out.println("�α��� ����");
				response.sendRedirect("login.jsp"); //���� jsp�� ������. 
			}
			
		//getBoardList.do (�Խñ� ��� ����)
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










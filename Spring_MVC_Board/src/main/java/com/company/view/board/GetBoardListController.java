package com.company.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.company.Spring_MVC_Board.board.BoardDAO;
import com.company.Spring_MVC_Board.board.BoardDO;

public class GetBoardListController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("��ü�Խñ� ��� ���� ó��");
		
		String searchField = "";
		String searchText = "";
		
		if(request.getParameter("searchCondition") != ""
				&& request.getParameter("searchKeyword") != "") {
			searchField = request.getParameter("searchCondition");
			searchText = request.getParameter("searchKeyword");
		}
		BoardDO boardDO = new BoardDO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
		
		//ModelAndView ��ü ����
		ModelAndView mav = new ModelAndView();
		
		//�� ���� ���(���ǿ� ����ϴ� ���� �ƴ� mav�� ���)
		mav.addObject("boardList", boardList);
		
		//������
		mav.setViewName("getBoardList");
		return mav;
	}

}

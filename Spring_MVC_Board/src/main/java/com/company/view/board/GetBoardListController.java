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
		System.out.println("전체게시글 목록 보기 처리");
		
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
		
		//ModelAndView 객체 생성
		ModelAndView mav = new ModelAndView();
		
		//모델 정보 등록(세션에 등록하는 것이 아닌 mav에 등록)
		mav.addObject("boardList", boardList);
		
		//포워딩
		mav.setViewName("getBoardList");
		return mav;
	}

}

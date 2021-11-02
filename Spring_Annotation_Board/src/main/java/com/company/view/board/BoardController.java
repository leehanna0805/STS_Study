package com.company.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.annotation.board.BoardDAO;
import com.company.annotation.board.BoardDO;

@Controller
public class BoardController { //통합 컨트롤러
	//전체 게시글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardDO boardDO, BoardDAO boardDAO,
						Model model, String searchField, String searchText) { //필요한거 매개변수로 받기만 하면 스프링컨테이너가 주입시켜줌!
		model.addAttribute("boardList", boardDAO.getBoardList(searchField, searchText)); //결과를 등록시킨 후
		return "getBoardList.jsp"; //이 페이지로 가서 결과 뿌려주거라
	}
	
}

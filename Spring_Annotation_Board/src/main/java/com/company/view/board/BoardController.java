package com.company.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.annotation.board.BoardDAO;
import com.company.annotation.board.BoardDO;

/*
 * 커맨드 (command) 객체란?
 * 클라이언트가 보내주는 파라미터가 자동으로 담겨서 반환되는 객체.
 * 자동 객체 변환이라고도 하며, MVC 디자인 패턴에서 가장 큰 핵심기술 중에 하나이다.
 * 
 * 스프링에서는 @RequestMapping을 이용하여 HandlerMapping 설정을 대체한다.
 */

@Controller
public class BoardController { //통합 컨트롤러
	//전체 게시글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardDO boardDO, BoardDAO boardDAO,
						Model model, String searchCondition, String searchKeyword) { //필요한거 매개변수로 받기만 하면 스프링컨테이너가 주입시켜줌!
		model.addAttribute("boardList", boardDAO.getBoardList(searchCondition, searchKeyword)); //결과를 등록시킨 후
		return "getBoardList.jsp"; //이 페이지로 가서 결과 뿌려주거라
	}
	
	//게시글 상세보기
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardDO boardDO, BoardDAO boardDAO, Model model) {
		model.addAttribute("board", boardDAO.getBoard(boardDO));
		return "getBoard.jsp";
	}
	
	/*
	 * DML 작업시엔 BoardDO, BoardDAO 만 커맨드 객체로 받으면 된다! 
	 */
	//게시글 등록
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardDO boardDO, BoardDAO boardDAO) {
		boardDAO.insertBoard(boardDO);
		return "getBoardList.do"; //변경된 table에 반영된걸 다시 뿌려주라! 그러니까 .do
	}
	//게시글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardDO boardDO, BoardDAO boardDAO) {
		boardDAO.deleteBoard(boardDO);
		return "getBoardList.do";
	}
	
	//게시글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardDO boardDO, BoardDAO boardDAO) {
		boardDAO.updateBoard(boardDO);
		return "getBoardList.do";
	}
}

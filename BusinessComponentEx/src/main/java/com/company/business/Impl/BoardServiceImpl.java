package com.company.business.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.business.board.BoardDO;
import com.company.business.board.BoardService;
/*
 * @Service: 클래스 선언부 위에 붙이면 이 클래스는 비즈니스 로직을 처리하는 클래스임을
 * 			 스프링 컨테이너에 알려줌.
 */
@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	/*
	 * @Autowired: 멤버변수 위에 붙이면 이 객체를 자동으로 injection함.
	 */
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardDO> getBoardList(BoardDO boardDO) {
		return boardDAO.getBoardList(boardDO);
	}

	@Override
	public BoardDO getBoard(BoardDO boardDO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBoard(BoardDO boardDO) {
		boardDAO.insertBoard(boardDO);
	}

	@Override
	public void updateBoard(BoardDO boardDO) {
		
	}

	@Override
	public void deleteBoard(BoardDO boardDO) {
		// TODO Auto-generated method stub
		
	}
	
}

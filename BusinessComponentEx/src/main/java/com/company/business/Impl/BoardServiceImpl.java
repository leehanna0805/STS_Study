package com.company.business.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.business.board.BoardDO;
import com.company.business.board.BoardService;
import com.company.business.common.LogAdvice;
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
	@Autowired
	private LogAdvice log; //AOP실습용
	//생성자
		public BoardServiceImpl() {
			// TODO Auto-generated constructor stub
			log = new LogAdvice();
		}
	
	@Override
	public List<BoardDO> getBoardList(BoardDO boardDO) {
		//log.printLog(); 
		//AOP실습용. 이렇게 메소드 하나하나에 넣지말고 공통으로 넣으려면?
		
		
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

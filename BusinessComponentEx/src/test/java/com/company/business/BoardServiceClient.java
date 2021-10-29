package com.company.business;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.company.business.board.BoardDO;
import com.company.business.board.BoardService;

/*
 * 실행 클래스 (test)
 */
public class BoardServiceClient {

	public static void main(String[] args) {
		//1. Spring Container 구동
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		//인터페이스. BoardServiceImpl에서 boardService 가져오기
		BoardService boardService = (BoardService)container.getBean("boardService");
	
		//2. 게시글 등록 테스트
		BoardDO boardDO = new BoardDO();
		boardDO.setTitle("스프링 어노테이션 개발");
		boardDO.setWriter("가비");
		boardDO.setContent("우리가 제일 잘했어 그럼 된!거!야!");
		
		boardService.insertBoard(boardDO);
		
		//3. 게시글 전체 목록 보기를 콘솔창에서 확인하기
		List<BoardDO> boardList = boardService.getBoardList(boardDO);
		for(BoardDO board : boardList) {
			//override해놓은 toString메소드 호출
			System.out.println("---> "+board.toString());
		}
		
		//4. Spring Container 종료
		container.close();
	}

}

package com.company.business.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.company.business.board.BoardDO;
import com.company.business.common.JDBCUtil;

import org.springframework.stereotype.Repository;

/*
 * @Repository: 클래스 선언부 위에 붙이면 이 클래스는 DB연동을 처리하는 클래스임을 
 * 				스프링 컨테이너에 알려줌.
 * @Component로 줘도 되는데, 개발자에게 이 클래스는 db쓰는걸 알려주려면 @Repository붙이는게 가독성 좋다.
 */

@Repository
public class BoardDAO {
	private Connection conn=null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; //select문 결과값
	
	public List<BoardDO> getBoardList(BoardDO boardDO){
		System.out.println("BoardDAO 의 getBoardList 호출");
		
		List<BoardDO> boardList = new ArrayList<BoardDO>();
		
		try {
			conn = JDBCUtil.getConnection();
			String SELECT_BOARD = "select * from board order by seq desc";
		
			pstmt = conn.prepareStatement(SELECT_BOARD);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDO board = new BoardDO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add(board);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//select니까 3개짜리
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList;
	}
	
	public void insertBoard(BoardDO boardDO) {
		System.out.println("BoardDAO 의 insertBoard 호출");
		
		try {
			conn = JDBCUtil.getConnection();
			String INSERT_BOARD = 
					"insert into board(seq, title, writer, content) values ((select nvl(max(seq),0)+1 from board),?,?,?)";
			pstmt = conn.prepareStatement(INSERT_BOARD);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getContent());
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
}

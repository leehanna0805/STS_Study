package com.company.Model2_Board.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.Model2_Board.common.JDBCUtil;

public class BoardDAO {
	// DB관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 전체 게시글 목록 조회 method
	// searchField = '제목'으로 검색할껀지, '작성자'로 검색할껀지. searchText=검색 텍스트
	public List<BoardDO> getBoardList(String searchField, String searchText){ 
		System.out.println("===> getBoardList() 기능 처리됨!");
		
		List<BoardDO> boardList = new ArrayList<BoardDO>(); //가변 배열 객체 생성
		
		try {
			conn = JDBCUtil.getConnection();
			
			// 게시물 검색 시 '제목' or '작성자'로 검색조건 제시하는 SQL만들기
			// 검색 조건 없으면 전체를 최신순 정렬해서 보여줌. 검색 조건 있으면 조건맞게 정렬해서 보여줌.
			String where="";
			
			if(searchField != null && searchText != null){  //넘어온게 있다면
				where = "where " + searchField + " like '%" + searchText + "%'";
			
			}
			
			String Condition_SQL = "select * from board " + where + " order by seq desc;";
			
			pstmt = conn.prepareStatement(Condition_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //게시판 글들 여러개니까 while문으로 돌려
				BoardDO board = new BoardDO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add(board);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,pstmt,conn);
		}
		return boardList;	
	}
	//=====================================================================================================================
	
	//게시글 상세보기
	public BoardDO getBoard(BoardDO boardDO) {
		System.out.println("===> getBoard() 처리됨!");
		
		BoardDO board = null;
		
		try {
			conn=JDBCUtil.getConnection();
			
			// 해당 게시글의 조회수(cnt) +1
			String UPDATE_CNT = "update board set cnt=cnt+1 where seq=?"; //물음표값은 boardDO객체가 가지고있음. 얻어오자
			pstmt = conn.prepareStatement(UPDATE_CNT);
			pstmt.setInt(1, boardDO.getSeq()); //첫번째 물음표에 넣어라
			pstmt.executeUpdate(); //DML작업에선 executeUpdate();
			
			// 게시글을 가져오자
			String BOARD_GET = "select * from board where seq=?";
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, boardDO.getSeq());
			rs = pstmt.executeQuery(); //select결과를 rs로 받기
			
			if(rs.next()) {
				board = new BoardDO();
				board.setSeq(rs.getInt("SEQ")); //db에서 데이터 가져와서 초기화
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	}
	//====================================================================================================
	
	//게시글 수정 처리 메소드
	public void updateBoard(BoardDO boardDO) {
		System.out.println("===> updateBoard() 처리됨!");
	
		try {
			conn = JDBCUtil.getConnection();
			
			String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getContent());
			pstmt.setInt(3, boardDO.getSeq());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	//======================================================================================================
	
	//게시글 삭제 처리 메소드
	public void deleteBoard(BoardDO boardDO) {
		System.out.println("===> deleteBoarD() 처리됨!");
		
		try {
			conn = JDBCUtil.getConnection();
			
			String BOARD_DELETE="delete from board where seq=?";
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, boardDO.getSeq());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	//======================================================================================================
	
		//게시글 추가 처리 메소드
		public void insertBoard(BoardDO boardDO) {
			System.out.println("===> insertBoarD() 처리됨!");
			
			try {
				conn = JDBCUtil.getConnection();
				
				String BOARD_INSERT="insert into board(seq, title, writer, content) "
						+ "values((select nvl(max(seq),0)+1 from board),?,?,?)"; //nvl함수 이용해서 게시글 번호 생성
				pstmt = conn.prepareStatement(BOARD_INSERT);
				pstmt.setString(1, boardDO.getTitle());
				pstmt.setString(2, boardDO.getWriter());
				pstmt.setString(3, boardDO.getContent());
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(pstmt, conn);
			}
		}
}

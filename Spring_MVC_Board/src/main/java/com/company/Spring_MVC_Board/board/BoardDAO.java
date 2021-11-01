package com.company.Spring_MVC_Board.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.Spring_MVC_Board.common.JDBCUtil;

public class BoardDAO {
	// DB���� ���� ����
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// ��ü �Խñ� ��� ��ȸ method
	// searchField = '����'���� �˻��Ҳ���, '�ۼ���'�� �˻��Ҳ���. searchText=�˻� �ؽ�Ʈ
	public List<BoardDO> getBoardList(String searchField, String searchText){ 
		System.out.println("===> getBoardList() ��� ó����!");
		
		List<BoardDO> boardList = new ArrayList<BoardDO>(); //���� �迭 ��ü ����
		
		try {
			conn = JDBCUtil.getConnection();
			
			// �Խù� �˻� �� '����' or '�ۼ���'�� �˻����� �����ϴ� SQL�����
			// �˻� ���� ������ ��ü�� �ֽż� �����ؼ� ������. �˻� ���� ������ ���Ǹ°� �����ؼ� ������.
			String where="";
			
			if(searchField != null && searchText != null){  //�Ѿ�°� �ִٸ�
				where = "where " + searchField + " like '%" + searchText + "%'";
			
			}
			
			String Condition_SQL = "select * from board " + where + " order by seq desc;";
			
			pstmt = conn.prepareStatement(Condition_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //�Խ��� �۵� �������ϱ� while������ ����
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
	
	//�Խñ� �󼼺���
	public BoardDO getBoard(BoardDO boardDO) {
		System.out.println("===> getBoard() ó����!");
		
		BoardDO board = null;
		
		try {
			conn=JDBCUtil.getConnection();
			
			// �ش� �Խñ��� ��ȸ��(cnt) +1
			String UPDATE_CNT = "update board set cnt=cnt+1 where seq=?"; //����ǥ���� boardDO��ü�� ����������. ������
			pstmt = conn.prepareStatement(UPDATE_CNT);
			pstmt.setInt(1, boardDO.getSeq()); //ù��° ����ǥ�� �־��
			pstmt.executeUpdate(); //DML�۾����� executeUpdate();
			
			// �Խñ��� ��������
			String BOARD_GET = "select * from board where seq=?";
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, boardDO.getSeq());
			rs = pstmt.executeQuery(); //select����� rs�� �ޱ�
			
			if(rs.next()) {
				board = new BoardDO();
				board.setSeq(rs.getInt("SEQ")); //db���� ������ �����ͼ� �ʱ�ȭ
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
	
	//�Խñ� ���� ó�� �޼ҵ�
	public void updateBoard(BoardDO boardDO) {
		System.out.println("===> updateBoard() ó����!");
	
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
	
	//�Խñ� ���� ó�� �޼ҵ�
	public void deleteBoard(BoardDO boardDO) {
		System.out.println("===> deleteBoarD() ó����!");
		
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
	
		//�Խñ� �߰� ó�� �޼ҵ�
		public void insertBoard(BoardDO boardDO) {
			System.out.println("===> insertBoarD() ó����!");
			
			try {
				conn = JDBCUtil.getConnection();
				
				String BOARD_INSERT="insert into board(seq, title, writer, content) "
						+ "values((select nvl(max(seq),0)+1 from board),?,?,?)"; //nvl�Լ� �̿��ؼ� �Խñ� ��ȣ ����
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
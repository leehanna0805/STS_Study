package com.company.Model2_Board.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.company.Model2_Board.common.JDBCUtil;

public class UserDAO {
	//DB 관련 변수 선언
		private Connection			conn = null;
		private PreparedStatement	pstmt = null;
		private ResultSet			rs = null;
		
		//SQL 명령어 (prepared statement방식)
		private final String USER_GET = "select id, password from users where id=? and password=?";
		
		//로그인 user 조회(select) 메소드 구현
		public UserDO getUser(UserDO userObj) {	//login_proc.jsp에서 사용
			UserDO user = null;
			
			try {
				System.out.println("===> JDBC로 getUser() 기능 처리됨!");
				conn = JDBCUtil.getConnection(); //연결 객체 가져옴
				
				pstmt = conn.prepareStatement(USER_GET); //파라미터로 prepared statement주기
				pstmt.setString(1, userObj.getId()); //1번째 물음표값 설정(넘어온 객체의 id)
				pstmt.setString(2, userObj.getPassword()); //2번째 물음표값 설정(넘어온 객체의 pw)
				
				rs = pstmt.executeQuery(); //select는 executeQuery로 실행
				if(rs.next()) { //인증처리가 됐다면 객체를 참조하고 있을것.
					user = new UserDO();
					user.setId(rs.getString("ID")); //db의 회원 id값으로 초기화해라
					user.setPassword(rs.getString("PASSWORD"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//자원해제
				JDBCUtil.close(rs, pstmt, conn);
			}
			return user;
		}
}

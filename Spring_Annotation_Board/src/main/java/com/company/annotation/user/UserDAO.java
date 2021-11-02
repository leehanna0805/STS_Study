package com.company.annotation.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.company.annotation.common.JDBCUtil;
import com.company.annotation.common.PasswordEncryptUtil;

public class UserDAO {
	
		private Connection			conn = null;
		private PreparedStatement	pstmt = null;
		private ResultSet			rs = null;
		
		
		private final String USER_GET = "select * from users where id=? and password=?";
		
		 // [추가] 회원가입시 패스워드를 암호화시킨 데이터를 저장할 참조변수 선언
		String pwEncrypt;
		private final String USERS_INSERT = "insert into users values(?,?,?,?,?)"; //회원가입 SQL
		
		public void insertUser(UserDO userDO){
			try {
				conn = JDBCUtil.getConnection();
				pstmt = conn.prepareStatement(USERS_INSERT);
				pstmt.setString(1, userDO.getId());
				pstmt.setString(2, userDO.getPassword());
				
				//넘어온 패스워드를 암호화 시켜서 세번쨰 물음표 값으로 지정한다.
				String plainText = userDO.getPassword();
				pwEncrypt = PasswordEncryptUtil.encryptSHA256(plainText);
				pstmt.setString(3, pwEncrypt);
				
				pstmt.setString(4, userDO.getName());
				pstmt.setString(5, userDO.getRole());
				
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(pstmt, conn);
			}
		}
		
		
		public UserDO getUser(UserDO userObj) {	
			UserDO user = null;
			
			try {
				System.out.println("===> USER DAO의 get user");
				conn = JDBCUtil.getConnection(); 
				
				pstmt = conn.prepareStatement(USER_GET); 
				pstmt.setString(1, userObj.getId()); 
				pstmt.setString(2, userObj.getPassword()); 
				
				rs = pstmt.executeQuery(); 
				if(rs.next()) {
					user = new UserDO();
					user.setId(rs.getString("ID")); 
					user.setPassword(rs.getString("PASSWORD"));
					user.setName(rs.getString("NAME"));
					user.setRole(rs.getString("ROLE"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(rs, pstmt, conn);
			}
			return user;
		}
}

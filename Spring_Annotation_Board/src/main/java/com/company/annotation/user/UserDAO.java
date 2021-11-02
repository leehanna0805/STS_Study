package com.company.annotation.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.company.annotation.common.JDBCUtil;

public class UserDAO {
	//DB ���� ���� ����
		private Connection			conn = null;
		private PreparedStatement	pstmt = null;
		private ResultSet			rs = null;
		
		//SQL ��ɾ� (prepared statement���)
		private final String USER_GET = "select id, password from users where id=? and password=?";
		
		//�α��� user ��ȸ(select) �
		public UserDO getUser(UserDO userObj) {	//login_proc.jsp���� ���
			UserDO user = null;
			
			try {
				System.out.println("===> JDBC�� getUser() ��� ó����!");
				conn = JDBCUtil.getConnection(); //���� ��ü ������
				
				pstmt = conn.prepareStatement(USER_GET); //�Ķ���ͷ� prepared statement�ֱ�
				pstmt.setString(1, userObj.getId()); //1��° ����ǥ�� ����(�Ѿ�� ��ü�� id)
				pstmt.setString(2, userObj.getPassword()); //2��° ����ǥ�� ����(�Ѿ�� ��ü�� pw)
				
				rs = pstmt.executeQuery(); //select�� executeQuery�� ����
				if(rs.next()) { //����ó���� �ƴٸ� ��ü�� �����ϰ� ������.
					user = new UserDO();
					user.setId(rs.getString("ID")); //db�� ȸ�� id������ �ʱ�ȭ�ض�
					user.setPassword(rs.getString("PASSWORD"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//�ڿ�����
				JDBCUtil.close(rs, pstmt, conn);
			}
			return user;
		}
}

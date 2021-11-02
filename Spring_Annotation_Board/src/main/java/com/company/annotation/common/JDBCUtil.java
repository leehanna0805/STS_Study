package com.company.annotation.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	//h2 db ����
	static final String driver = "org.h2.Driver";
	//���߿� �����Ҷ� oracle�� �ٲ㼭 �����ϸ� ��
	static final String url = "jdbc:h2:tcp://localhost/~/test"; 
	
	public static Connection getConnection() throws Exception{	//UserDAO���� ���
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "sa", ""); //url, id, pw
			return con;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	// METHOD OVERLOADING
	//DML�۾�(insert,update,delete) ����� ȣ��Ǵ� �ڿ����� �޼ҵ�
	public static void close(PreparedStatement pstmt, Connection conn) {
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close(); //pstmt �ڿ�����
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close(); //conn �ڿ�����
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//select �۾� ����� ȣ��Ǵ� �ڿ����� �޼ҵ�
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				if(!rs.isClosed()) rs.close(); //rs �ڿ�����
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close(); //pstmt �ڿ�����
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close(); //conn �ڿ�����
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
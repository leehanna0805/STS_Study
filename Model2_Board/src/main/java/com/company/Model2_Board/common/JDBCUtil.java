package com.company.Model2_Board.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	//h2 db 연동
	static final String driver = "org.h2.Driver";
	//나중에 서비스할때 oracle로 바꿔서 배포하면 댐
	static final String url = "jdbc:h2:tcp://localhost/~/test"; 
	
	public static Connection getConnection() throws Exception{	//UserDAO에서 사용
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
	//DML작업(insert,update,delete) 종료시 호출되는 자원해제 메소드
	public static void close(PreparedStatement pstmt, Connection conn) {
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close(); //pstmt 자원해제
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close(); //conn 자원해제
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//select 작업 종료시 호출되는 자원해제 메소드
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				if(!rs.isClosed()) rs.close(); //rs 자원해제
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close(); //pstmt 자원해제
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close(); //conn 자원해제
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
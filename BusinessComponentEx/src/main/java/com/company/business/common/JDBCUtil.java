package com.company.business.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	static final String driver = "org.h2.Driver";
	static final String url = "jdbc:h2:tcp://localhost/~/test"; 
	
	public static Connection getConnection() throws Exception{
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
	public static void close(PreparedStatement pstmt, Connection conn) {
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				if(!rs.isClosed()) rs.close(); 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close(); 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close(); 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
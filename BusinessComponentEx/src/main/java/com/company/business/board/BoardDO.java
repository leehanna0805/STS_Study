package com.company.business.board;
/*
 * 개발자가 구현하는 모든 클래스는 최상위 클래스인 Object클래스로부터 상속받는다.
 * Object클래스의 모든 메소드를 BoardDO 자식클래스에서 @Override 할수있다능
 */
import java.sql.Date;

public class BoardDO {
	private int seq;
	private String title;
	private String writer;
	private Date regdate;
	private int cnt;
	
	//getter setter
	public int getSeq() {return seq;}
	public void setSeq(int seq) {this.seq = seq;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getWriter() {return writer;}
	public void setWriter(String writer) {this.writer = writer;}
	public Date getRegdate() {return regdate;}
	public void setRegdate(Date regdate) {this.regdate = regdate;}
	public int getCnt() {return cnt;}
	public void setCnt(int cnt) {this.cnt = cnt;}
	
	//Object클래스 메소드 오버라이드 하기
	@Override
	public String toString() {
		return "BoardDO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", regdate=" + regdate + ", cnt="
				+ cnt + "]";
	}
	
	
}

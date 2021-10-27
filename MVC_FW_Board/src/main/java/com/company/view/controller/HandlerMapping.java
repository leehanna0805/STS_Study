package com.company.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.company.view.board.DeleteBoardController;
import com.company.view.board.GetBoardController;
import com.company.view.board.GetBoardListController;
import com.company.view.board.InsertBoardController;
import com.company.view.board.UpdateBoardController;
import com.company.view.user.LoginController;
import com.company.view.user.LogoutController;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	//생성자
	public HandlerMapping() {
		//hashmap 객체생성. key는 string, 값은 controller
		mappings = new HashMap<String, Controller>();
		
		//action을 key로 PUT
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());
	}
	
	//사용자 정의 메소드 구현
	public Controller getController(String path) {
		return mappings.get(path);
	}
}

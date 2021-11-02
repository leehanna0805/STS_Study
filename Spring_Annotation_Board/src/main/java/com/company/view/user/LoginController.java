
package com.company.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.annotation.user.UserDAO;
import com.company.annotation.user.UserDO;

@Controller
public class LoginController {	//Controller 클래스는 POJO 클래스로 구현한다!
	
	/*
	 * @RequestMapping: 요청(login.do)에 대해 어떤 Controller, 어떤 메소드가 처리할지를 맵핑하기 위한 어노테이션
	 * 					요청 URL 을 어떤 메소드가 처리할지 결정해줘야 한다.
	 */
	@RequestMapping("/login.do")
	public String login(UserDO userDO, UserDAO userDAO, HttpSession session) { //객체 생성은 스프링이 해줌. 바로 쓰면 돼.
		UserDO user = userDAO.getUser(userDO);
		
		if(user!= null) {
			System.out.println("로그인 성공");
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		}else {
			System.out.println("로그인 실패");
			return "login.jsp";
		}
	}
	
}



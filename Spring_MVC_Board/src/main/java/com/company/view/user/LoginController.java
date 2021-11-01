package com.company.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.company.Spring_MVC_Board.user.UserDAO;
import com.company.Spring_MVC_Board.user.UserDO;

/*
 * MVC패턴에선 Controller 인터페이스를 만들어서 implements했었는데
 * 스프링에선 Controller 인터페이스를 지원해줌. 구현할 필요 X
 */
public class LoginController implements Controller{

	/* 리턴 타입이 ModelAndView */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 처리");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserDO userDO = new UserDO();
		userDO.setId(id);
		userDO.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserDO user = userDAO.getUser(userDO);
		
		//ModelAndView 객체생성
		ModelAndView mav = new ModelAndView();
		
		if(user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("IdKey",id);
			
			System.out.println("로그인 인증 성공");
			//포워딩
			mav.setViewName("redirect:getBoardList.do");
			
		}else {
			System.out.println("로그인 실패");
			//포워딩
			mav.setViewName("redirect:login.jsp");
		}
		return mav;
		
	}

}

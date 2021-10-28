package com.company.couplingtest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// 디자인 패턴 적용 방법! (BeanFactory.java 사용)
		//BeanFactory factory = new BeanFactory();
		//TV tv = (TV)factory.getBean(args[0]);
		
		// 스프링 프레임워크 이용하는 방법!!
		//1. 스프링 컨테이너를 구동한다(환경설정 xml파일 가져와서)
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. 스프링 컨테이너로부터 필요한 객체를 요청(Lookup)한다. (xml파일에서 id tv로 설정해놓음)
		TV tv = (TV)factory.getBean("lg");
		
		tv.powerOn();
		tv.volOff();
		tv.powerOff();
	
		//3. 스프링 컨테이너를 종료한다.
		factory.close();
	}

}

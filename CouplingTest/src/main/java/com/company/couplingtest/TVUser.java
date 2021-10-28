package com.company.couplingtest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// ������ ���� ���� ���! (BeanFactory.java ���)
		//BeanFactory factory = new BeanFactory();
		//TV tv = (TV)factory.getBean(args[0]);
		
		// ������ �����ӿ�ũ �̿��ϴ� ���!!
		//1. ������ �����̳ʸ� �����Ѵ�(ȯ�漳�� xml���� �����ͼ�)
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. ������ �����̳ʷκ��� �ʿ��� ��ü�� ��û(Lookup)�Ѵ�. (xml���Ͽ��� id tv�� �����س���)
		TV tv = (TV)factory.getBean("lg");
		
		tv.powerOn();
		tv.volOff();
		tv.powerOff();
	
		//3. ������ �����̳ʸ� �����Ѵ�.
		factory.close();
	}

}

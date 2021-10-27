package com.company.view.controller;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	//생성자
	public HandlerMapping() {
		//hashmap 객체생성. key는 string, 값은 controller
		mappings = new HashMap<String, Controller>();
		
		
	}
}

package com.company.couplingtest;

public class SamsungTV implements TV{
	
	public SamsungTV() {
		System.out.println("삼성 티비 객체 생성");
	}
	
	public void powerOn() {
		System.out.println("SamsungTV 전원 키기");
	}
	public void powerOff() {
		System.out.println("SamsungTV 전원 끄기");
	}
	public void volUp() {
		System.out.println("SamsungTV 볼륨 올리기");
	}
	public void volOff() {
		System.out.println("SamsungTV 볼륨 내리기");
	}
}

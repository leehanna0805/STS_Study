package com.company.couplingtest;

/*
 * 삼성 티비와 엘지 티비는 메소드 시그니처가 서로 다르게 구현되어 있다.
 */

public class LgTV implements TV{
	public void powerOn() {
		System.out.println("lgTV 전원 키기");
	}
	public void powerOff() {
		System.out.println("lgTV 전원 끄기");
	}
	public void volUp() {
		System.out.println("lgTV 볼륨 올리기");
	}
	public void volOff() {
		System.out.println("lgTV 볼륨 내리기");
	}
}

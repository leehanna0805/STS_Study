package com.company.couplingtest;

/*
 * �Ｚ Ƽ��� ���� Ƽ��� �޼ҵ� �ñ״�ó�� ���� �ٸ��� �����Ǿ� �ִ�.
 */

public class LgTV implements TV{
	public LgTV() {
		System.out.println("���� Ƽ�� ��ü ����");
	}
	
	public void powerOn() {
		System.out.println("lgTV ���� Ű��");
	}
	public void powerOff() {
		System.out.println("lgTV ���� ����");
	}
	public void volUp() {
		System.out.println("lgTV ���� �ø���");
	}
	public void volOff() {
		System.out.println("lgTV ���� ������");
	}
}

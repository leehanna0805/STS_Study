package com.company.couplingtest;

public class SamsungTV implements TV{
	
	public SamsungTV() {
		System.out.println("�Ｚ Ƽ�� ��ü ����");
	}
	
	public void powerOn() {
		System.out.println("SamsungTV ���� Ű��");
	}
	public void powerOff() {
		System.out.println("SamsungTV ���� ����");
	}
	public void volUp() {
		System.out.println("SamsungTV ���� �ø���");
	}
	public void volOff() {
		System.out.println("SamsungTV ���� ������");
	}
}

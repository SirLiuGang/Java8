package com.zhbr.lg.j.newinterface;

/**
 * �ӿ��е�Ĭ�Ϸ����뾲̬����
 * 
 */
public interface MyInterface {

	default String getName() {
		return "myInterface";
	}
	
	public static void show() {
		System.out.println("MyFunction�ӿ��еľ�̬����");
	}
}

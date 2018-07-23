package com.zhbr.lg.j.newinterface;

/**
 * 接口中的默认方法与静态方法
 * 
 */
public interface MyInterface {

	default String getName() {
		return "myInterface";
	}
	
	public static void show() {
		System.out.println("MyFunction接口中的静态方法");
	}
}

package com.zhbr.lg.j.newinterface;

public class SubClass /*extends MyClass*/ implements MyInterface, MyInterface2 {

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

}

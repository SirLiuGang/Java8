package com.zhbr.lg.j.newinterface;

/**
 * 接口中的默认方法
 *	接口默认方法的“类优先”原则
 *		若一个即可中定义了一个默认方法，而另外一个父类或接口中又定义了一个同名的方法时
 *		1.选择父类中的方法。如果一个父类提供了具体的实现，那么接口中具有相同名称和参数的默认方法会被忽略
 *		2.接口冲突。如果一个父接口提供一个默认的方法，而另一个接口也提供了一个具有相同名称和参数列表的方法（
 *					不管方法是否是默认方法），那么必须覆盖该方法来解决冲突
 */
public class Main_DefaultInterfaceTest {

	public static void main(String[] args) {
		SubClass sc = new SubClass();
		System.out.println(sc.getName());
		
		MyInterface.show();		// 调用接口中的静态方法
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

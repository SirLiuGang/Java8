package com.zhbr.lg.b.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符“->”，称之为箭头操作符或者Lambda操作符
 * 								箭头操作符将Lambda分为了两部分：
 * 									左侧：Lambda表达式的参数列表
 * 									右侧：Lambda表达式中所需执行的功能，即Lambda体
 * 
 * 	语法格式一：无参数，无返回值：								() -> System.out.println("Hello");
 * 	语法格式二：有一个参数，无返回值：							(e) -> System.out.println(e);
 * 	语法格式三：若只有一个参数，小括号可以省略不写				e -> System.out.println(e);
 * 	语法格式四：有两个以上参数，有返回值，Lambda体中有多条语句	(x, y) -> {
 *																System.out.println("Lambda expression，x = " + x + ", y = " + y);
 *																return Integer.compare(x, y);
 *															};
 * 	语法格式五：若只有一条语句，大括号和return可省略不写		(x, y) ->  Integer.compare(x, y);
 * 	语法格式六：Lambda表达式的参数列表数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”
 * 
 * 	左右遇一括号省
 * 	左侧推断类型省
 * 
 * 二、Lambda表达式需要“函数式接口”的支持
 * 	函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用@FunctionaInterface修饰，可以检查是否是函数式接口
 * 				（详见MyFilter.java）
 * 
 */
public class Main_LambdaTest {

	/**
	 * 语法格式一：无参数，无返回值：					() -> System.out.println("Hello");
	 */
	@Test
	public void test1() {
		/*final */int num = 0;	// jdk1.7以前必须是final，1.8默认自动增加final
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("Anonymous Inner Class 无参数，无返回值" + num);
			}
		};
		r.run();
		
		System.out.println("----------");
		
		Runnable r_new = () -> System.out.println("Lambda expression 无参数，无返回值" + num);
		r_new.run();
	}
	
	/**
	 * 语法格式二：有一个参数，无返回值：							(e) -> System.out.println(e);
	 */
	@Test
	public void test2() {
		Consumer<String> con = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println("Anonymous Inner Class " + t);
			}
		};
		con.accept("有一个参数，无返回值");
		
		System.out.println("-------------");
		
		Consumer<String> con_new = (e) -> System.out.println("Lambda expression " + e);
		con_new.accept("有一个参数，无返回值");
	}
	
	/**
	 * 语法格式三：若只有一个参数，小括号可以省略不写				e -> System.out.println(e);
	 */
	@Test
	public void test3() {
		Consumer<String> con_new = e -> System.out.println("Lambda expression " + e);
		con_new.accept("只有一个参数，小括号可以省略不写");
	}
	
	/**
	 * 语法格式四：有两个以上参数，有返回值，Lambda体中有多条语句
	 * (x, y) -> {
	 *				System.out.println("Lambda expression，x = " + x + ", y = " + y);
	 *				return Integer.compare(x, y);
	 * };
	 */
	@Test
	public void test4() {
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				System.out.println("有两个以上参数，有返回值，Lambda体中有多条语句");
				System.out.println("Anonymous Inner Class，x = " + x + ", y = " + y);
				return Integer.compare(x, y);
			}
		};
		System.out.println(com.compare(10, 20));
		
		System.out.println("------------");
		
		Comparator<Integer> com_new = (x, y) -> {
			System.out.println("有两个以上参数，有返回值，Lambda体中有多条语句");
			System.out.println("Lambda expression，x = " + x + ", y = " + y);
			return Integer.compare(x, y);
		};
		System.out.println(com_new.compare(10, 20));
	}
	
	/**
	 * 语法格式五：若只有一条语句，大括号和return可省略不写		(x, y) ->  Integer.compare(x, y);
	 */
	@Test
	public void test5() {
		System.out.println("若只有一条语句，大括号和return可省略不写");
		Comparator<Integer> com_new = (x, y) ->  Integer.compare(x, y);
		System.out.println(com_new.compare(10, 20));
	}
	
	/**
	 * 语法格式六：Lambda表达式的参数列表数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”
	 */
	@Test
	public void test6() {
		System.out.println("Lambda表达式的参数列表数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”");
		Comparator<Integer> com_new = (Integer x, Integer y) ->  Integer.compare(x, y);
		System.out.println(com_new.compare(10, 20));
		
		
		String[] strs = {"aaa", "bbb", "ccc"};
		List<String> list = new ArrayList<>();	// 1.7以后可以不写后边的类型
		show(new HashMap<>());					// 1.7以后可以不写后边的类型
	}
	
	private void show(Map<String, Integer> map) {
		
	}
	
	// 需求：对一个数进行运算
	@Test
	public void test7() {
		System.out.println(operation(100, (e) -> e * e));
		System.out.println(operation(100, (e) -> e + 200));
	}
	
	private Integer operation(Integer num, MyFun my) {
		return my.getValue(num);
	}
	
	
	
	
	
	
	
	
}

package com.zhbr.lg.e.lambda;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;

/**
 * 方法引用和构造器引用<br/>
 * 
 * 一、方法引用：<br/>若Lambda体重的内容已有方法已经实现了，我们可以使用“方法引用”
 * 			（可以理解为方法引用是Lambda表达式的另一种表现形式）
 * 	主要有三种语法格式：
 * 		1.对象::实例方法名
 * 		2.类::静态方法名
 * 		3.类::实例方法名
 * 		区别：2，3的区别： 若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： 类名::实例方法名
 * 	<br/>前提条件：<br/>
 * 		1.方法引用所引用的方法的<b>参数列表</b>必须要和函数式接口中抽象方法的参数列表相同（完全一致）
 * 		2.方法引用所引用的方法的<b>返回值</b>必须要和函数式接口中抽象方法的返回值相同（完全一致）
 * 	<br/>
 * 
 * 二、构造器引用：<br/>
 * 		格式：ClassName::new;
 * 		注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 * 	<br/>
 * 
 * 三、数组引用：<br/>
 * 		格式：Type::new;
 * 
 * 
 * 
 */
public class Main_LambdaTest {

	/**
	 * 方法引用 -- 对象::实例方法名
	 */
	@Test
	public void test1() {
		// 原有方法的调用
		Consumer<String> con = (x) -> System.out.println(x);
		con.accept("1111");
		
		// 方法引用的方式进行调用
		Consumer<String> con1 = System.out::println;
		con1.accept("2222");
	}
	
	/**
	 * 方法引用 -- 对象::实例方法名	练习
	 */
	@Test
	public void test1_1() {
		Employee emp = new Employee();
		Supplier<String> sup = () -> emp.getName();
		String str = sup.get();
		System.out.println(str);
		
		Supplier<Integer> sup1 = emp::getAge;
		Integer str1 = sup1.get();
		System.out.println(str1);
	}
	
	
	/**
	 * 方法引用 -- 类::静态方法名
	 */
	@SuppressWarnings("unused")
	@Test
	public void test2() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		
		Comparator<Integer> com1 = Integer::compare;
	}
	
	/**
	 * 方法引用 -- 类::实例方法名
	 */
	@Test
	public void test4() {
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		
		BiPredicate<String, String> bp2 = String::equals;
	}
	
	/**
	 * 构造器引用 -- 无参构造器
	 * ClassName::new;
	 */
	@Test
	public  void test5() {
		Supplier<Employee> sup = () -> new Employee();
		
		Supplier<Employee> sup1 = Employee::new;
		Employee emp = sup1.get();
		System.out.println(emp);
	}
	
	/**
	 * 构造器引用 -- 有参构造器（保持一致）
	 * ClassName::new;
	 */
	@Test
	public  void test6() {
		Function<String, Employee> fun = (x) -> new Employee(x);
		
		Function<String, Employee> fun1 = Employee::new;
		Employee emp = fun1.apply("aaa");
		System.out.println(emp);
		
		BiFunction<String, Integer, Employee> bifun = (x, y) -> new Employee(x, y);
		
		BiFunction<String, Integer, Employee> bifun1 = Employee::new;
		Employee emp1 = bifun1.apply("张三", 18);
		System.out.println(emp1);
		
	}
	
	/**
	 * 数组引用：
	 * Type::new;
	 */
	@Test
	public void test7() {
		Function<Integer, String[]> fun = (x) -> new String[x];
		String[] strs = fun.apply(10);
		System.out.println(strs.length);
		
		Function<Integer, String[]> fun1 = String[]::new;
		String[] strs1 = fun.apply(20);
		System.out.println(strs1.length);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

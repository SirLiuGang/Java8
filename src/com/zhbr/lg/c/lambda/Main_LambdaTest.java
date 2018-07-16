package com.zhbr.lg.c.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;

public class Main_LambdaTest {

	/**
	 * 初始化员工列表
	 */
	List<Employee> EmployeeList = Arrays.asList(
			new Employee("赵六", 48, 4444.444),
			new Employee("张三", 18, 1111.111),
			new Employee("王五", 38, 3333.333),
			new Employee("田七", 58, 5555.555),
			new Employee("李四", 28, 2222.222)
	);
	
	/**
	 * 调用Conllections.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按姓名比），
	 * 使用Lambda作为参数传递
	 */
	@Test
	public void test1() {
		Collections.sort(EmployeeList, (e1, e2) -> {
			if(e1.getAge() == e2.getAge()) {
				return e1.getName().compareTo(e2.getName());
			} else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		
		for(Employee emp : EmployeeList) {
			System.out.println(emp);
		}
	}
	
	/**
	 * ①声明函数式接口，接口中声明抽象方法，public String getValue(String str)
	 * ②声明类TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值
	 * ③再将一个字符串的第2个和第4个索引位置进行截取子串
	 */
	@Test
	public void test2() {
		String str1 = strHandler("\t\t\t HelloWorld   ", (e) -> e.trim());
		System.out.println("去空格：" + str1);
		
		String str2 = strHandler(str1, (e) -> e.toUpperCase());
		System.out.println("转大写：" + str2);
		
		String str3 = strHandler(str2, (e) -> e.substring(2, 5));
		System.out.println("截取：" + str3);
	}
	
	/**
	 * 对字符串进行处理
	 */
	private String strHandler(String str, MyFun_2 fun) {
		return fun.getValue(str);
	} 
	
	/**
	 * ①声明一个带两个泛型的函数式接口，泛型类型为<T, R> T为参数，R为返回值
	 * ②接口中声明对应抽象方法
	 * ③在TestLambda类中声明方法，使用接口作为参数，计算两个long型参数的和
	 * ④再计算两个long型参数的乘积
	 */
	@Test
	public void test3() {
		operator(100L, 200L, (x, y) -> x + y);
		operator(100L, 200L, (x, y) -> x * y);
	}
	
	private void operator(Long l1, Long l2, MyFun_3<Long, Long> fun) {
		System.out.println(fun.getValue(l1, l2));
	} 
	
}
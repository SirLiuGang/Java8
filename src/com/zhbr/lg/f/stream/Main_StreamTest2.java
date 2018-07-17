package com.zhbr.lg.f.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;

/**
 * Stream
 * 2.中间操作
 * 
 * 筛选与切片：
 *  	filter——接收Lambda，从流中排除某些元素
 *  	limit——截断流，使其元素不超过给定数量
 *  	skip(n)——跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
 *  	distinct——筛选，通过流所生成的hashCode() 和 equals()
 *  
 *  映射：
 *  	map——接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
 *  	flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流都连接成一个流
 *  排序：
 *  	sorted()——自然排序(Comparable)
 *  	sorted(Comparator com)——定制排序(Comparator)
 * 
 * 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理
 * 而在终止操作时一次性全部处理，称为“惰性求值”
 * 
 * 内部迭代：迭代操作由Stream API完成
 * 外部迭代：iterator
 */
public class Main_StreamTest2 {
	
	/**
	 * 初始化员工列表
	 */
	List<Employee> EmployeeList = Arrays.asList(
			new Employee("张三", 18, 1111.111),
			new Employee("李四", 28, 2222.222),
			new Employee("王五", 38, 3333.333),
			new Employee("赵六", 48, 4444.444),
			new Employee("田七", 58, 5555.555),
			new Employee("田七", 58, 5555.555),
			new Employee("田七", 58, 5555.555),
			new Employee("啊啊", 58, 5555.555)
	);
	
	/**
	 *  filter——接收Lambda，从流中排除某些元素
	 */
	@Test
	// 内部迭代：迭代操作由Stream API完成
	public void test1() {
		// 中间操作不会有任何结果
		Stream<Employee> stream = EmployeeList.stream()
					.filter((e) -> {
						System.out.println("Stream API 中间操作");
						return e.getAge() > 35;
					});
		
		// 终止操作：一次性执行全部内容，即“惰性求值”
		stream.forEach(System.out::println);
	}
	
	/**
	 * 外部迭代：iterator
	 */
	@Test
	public void test1_1() {
		Iterator<Employee> it = EmployeeList.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	/**
	 * limit——截断流，使其元素不超过给定数量
	 */
	@Test
	public void test2() {
		EmployeeList.stream().filter((e) -> {
									System.out.println("短路");
									return e.getSalary() > 3000;
								})
							.limit(2)
							.forEach(System.out::println);
	}
	
	/**
	 * skip(n)——跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
	 */
	@Test
	public void test3() {
		EmployeeList.stream().filter((e) -> {
									System.out.println("短路");
									return e.getSalary() > 3000;
								})
							.skip(2)		// 跳过前两个
							.forEach(System.out::println);
	}
	
	/**
	 * distinct——筛选，通过流所生成的hashCode() 和 equals()
	 */
	@Test
	public void test4() {
		// 需要重写Employee中的 hashCode() 和 equals() 方法
		EmployeeList.stream()
					.filter((e) -> e.getSalary() > 3000)
					.skip(2)
					.distinct()
					.forEach(System.out::println);
	}

	List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
	
	/**
	 * map——接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
	 */
	@Test
	public void test5() {
		list.stream()
			.map((str) -> str.toUpperCase())
			.forEach(System.out::println);
		
		EmployeeList.stream()
					.map(Employee::getName)
					.forEach(System.out::println);
	}
	
	/**
	 * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流都连接成一个流
	 */
	@Test
	public void test6() {
		// {a,a,a}, {b,b,b}
		Stream<Stream<Character>> stream = list.stream()
												.map(Main_StreamTest2::filterCharacter);
		stream.forEach((sm) -> {
			sm.forEach(System.out::println);
		});
		
		System.out.println("--------------------------------");
		
		// 使用flatMap 扁平化
		// {a,a,a,b,b,b}
		Stream<Character> sm = list.stream()
										.flatMap(Main_StreamTest2::filterCharacter);
		sm.forEach(System.out::println);
	}
	
	public static Stream<Character> filterCharacter(String str) {
		List<Character> list = new ArrayList<>();
		
		for(Character ch : str.toCharArray()) {
			list.add(ch);
		}
		
		return list.stream();
	}
	
	/**
	 * sorted()——自然排序
	 */
	@Test
	public void test7() {
		list.stream()
			.sorted()
			.forEach(System.out::println);
	}
	
	/**
	 * sorted(Comparator com)——定制排序(Comparator)
	 */
	@Test
	public void test8() {
		EmployeeList.stream()
					.sorted((e1, e2) -> {
						if(e1.getAge() == e2.getAge()) {
							return e1.getName().compareTo(e2.getName());
						} else {
							return e1.getAge() - e2.getAge();
						}
					})
					.forEach(System.out::println);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.zhbr.lg.g.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;

/**
 * Stream API 练习
 */
public class Main_StreamTest4 {
	
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
	 * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
	 * 给定[1,2,3,4,5]，应该返回[1,4,9,16,25]
	 */
	@Test
	public void test1() {
		Integer[] nums = new Integer[]{1,2,3,4,5};
		Arrays.stream(nums)
				.map((x) -> x * x)
				.forEach(System.out::println);
	}
	
	/**
	 * 2.使用map和reduce方法数一数流中有多少个Employee
	 */
	@Test
	public void test2() {
		Optional<Integer> count = EmployeeList.stream()
									.map((e) -> 1)
									.reduce(Integer::sum);
	}
	
	
	
}

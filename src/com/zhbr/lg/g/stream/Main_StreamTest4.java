package com.zhbr.lg.g.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;

/**
 * Stream API ��ϰ
 */
public class Main_StreamTest4 {
	
	/**
	 * ��ʼ��Ա���б�
	 */
	List<Employee> EmployeeList = Arrays.asList(
			new Employee("����", 18, 1111.111),
			new Employee("����", 28, 2222.222),
			new Employee("����", 38, 3333.333),
			new Employee("����", 48, 4444.444),
			new Employee("����", 58, 5555.555),
			new Employee("����", 58, 5555.555),
			new Employee("����", 58, 5555.555),
			new Employee("����", 58, 5555.555)
	);
	
	/**
	 * ����һ�������б���η���һ����ÿ������ƽ�����ɵ��б��أ�
	 * ����[1,2,3,4,5]��Ӧ�÷���[1,4,9,16,25]
	 */
	@Test
	public void test1() {
		Integer[] nums = new Integer[]{1,2,3,4,5};
		Arrays.stream(nums)
				.map((x) -> x * x)
				.forEach(System.out::println);
	}
	
	/**
	 * 2.ʹ��map��reduce������һ�������ж��ٸ�Employee
	 */
	@Test
	public void test2() {
		Optional<Integer> count = EmployeeList.stream()
									.map((e) -> 1)
									.reduce(Integer::sum);
	}
	
	
	
}

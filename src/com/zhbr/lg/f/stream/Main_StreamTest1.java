package com.zhbr.lg.f.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;

/**
 * Stream������3������
 * 1.����Stream
 * 
 * 2.�м����
 * 
 * 3.��ֹ�������ն˲�����
 */
public class Main_StreamTest1 {
	
	/**
	 * 1.����Stream
	 */
	@Test
	public void test1() {
		// 1.����ͨ�� Collection ϵ�м����ṩ�� stream() �� parallelStream()
		List<String> list = new ArrayList();
		Stream<String> stream1 = list.stream();
		
		// 2.ͨ�� Arrays �еľ�̬���� stream() ��ȡ������
		Employee[] emps = new Employee[10];
		Stream<Employee> stream2 = Arrays.stream(emps);
		
		// 3.ͨ�� Stream ���еľ�̬���� of()
		Stream<String> stream3 = Stream.of("aa", "bb", "cc");
		
		// 4.����������
		// ����
		// 												�м����
		Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
		// ��ֹ����
		stream4.limit(10).forEach(System.out::println);
		
		// ����
		Stream.generate(() -> Math.random())		// �м����
					.limit(5)					// ��ֹ����
					.forEach(System.out::println);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

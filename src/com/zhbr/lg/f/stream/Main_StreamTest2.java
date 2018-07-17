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
 * 2.�м����
 * 
 * ɸѡ����Ƭ��
 *  	filter��������Lambda���������ų�ĳЩԪ��
 *  	limit�����ض�����ʹ��Ԫ�ز�������������
 *  	skip(n)��������Ԫ�أ�����һ���ӵ���ǰn��Ԫ�ص�����������Ԫ�ز���n�����򷵻�һ����������limit(n)����
 *  	distinct����ɸѡ��ͨ���������ɵ�hashCode() �� equals()
 *  
 *  ӳ�䣺
 *  	map��������Lambda����Ԫ��ת����������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ��
 *  	flatMap��������һ��������Ϊ�����������е�ÿ��ֵ��������һ������Ȼ������е��������ӳ�һ����
 *  ����
 *  	sorted()������Ȼ����(Comparable)
 *  	sorted(Comparator com)������������(Comparator)
 * 
 * ����м�����������������γ�һ����ˮ�ߣ�������ˮ���ϴ�����ֹ�����������м��������ִ���κεĴ���
 * ������ֹ����ʱһ����ȫ��������Ϊ��������ֵ��
 * 
 * �ڲ�����������������Stream API���
 * �ⲿ������iterator
 */
public class Main_StreamTest2 {
	
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
	 *  filter��������Lambda���������ų�ĳЩԪ��
	 */
	@Test
	// �ڲ�����������������Stream API���
	public void test1() {
		// �м�����������κν��
		Stream<Employee> stream = EmployeeList.stream()
					.filter((e) -> {
						System.out.println("Stream API �м����");
						return e.getAge() > 35;
					});
		
		// ��ֹ������һ����ִ��ȫ�����ݣ�����������ֵ��
		stream.forEach(System.out::println);
	}
	
	/**
	 * �ⲿ������iterator
	 */
	@Test
	public void test1_1() {
		Iterator<Employee> it = EmployeeList.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	/**
	 * limit�����ض�����ʹ��Ԫ�ز�������������
	 */
	@Test
	public void test2() {
		EmployeeList.stream().filter((e) -> {
									System.out.println("��·");
									return e.getSalary() > 3000;
								})
							.limit(2)
							.forEach(System.out::println);
	}
	
	/**
	 * skip(n)��������Ԫ�أ�����һ���ӵ���ǰn��Ԫ�ص�����������Ԫ�ز���n�����򷵻�һ����������limit(n)����
	 */
	@Test
	public void test3() {
		EmployeeList.stream().filter((e) -> {
									System.out.println("��·");
									return e.getSalary() > 3000;
								})
							.skip(2)		// ����ǰ����
							.forEach(System.out::println);
	}
	
	/**
	 * distinct����ɸѡ��ͨ���������ɵ�hashCode() �� equals()
	 */
	@Test
	public void test4() {
		// ��Ҫ��дEmployee�е� hashCode() �� equals() ����
		EmployeeList.stream()
					.filter((e) -> e.getSalary() > 3000)
					.skip(2)
					.distinct()
					.forEach(System.out::println);
	}

	List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
	
	/**
	 * map��������Lambda����Ԫ��ת����������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ��
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
	 * flatMap��������һ��������Ϊ�����������е�ÿ��ֵ��������һ������Ȼ������е��������ӳ�һ����
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
		
		// ʹ��flatMap ��ƽ��
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
	 * sorted()������Ȼ����
	 */
	@Test
	public void test7() {
		list.stream()
			.sorted()
			.forEach(System.out::println);
	}
	
	/**
	 * sorted(Comparator com)������������(Comparator)
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

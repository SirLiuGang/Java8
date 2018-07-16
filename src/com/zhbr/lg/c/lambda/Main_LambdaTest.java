package com.zhbr.lg.c.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;

public class Main_LambdaTest {

	/**
	 * ��ʼ��Ա���б�
	 */
	List<Employee> EmployeeList = Arrays.asList(
			new Employee("����", 48, 4444.444),
			new Employee("����", 18, 1111.111),
			new Employee("����", 38, 3333.333),
			new Employee("����", 58, 5555.555),
			new Employee("����", 28, 2222.222)
	);
	
	/**
	 * ����Conllections.sort()������ͨ����������Ƚ�����Employee���Ȱ�����ȣ�������ͬ�������ȣ���
	 * ʹ��Lambda��Ϊ��������
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
	 * ����������ʽ�ӿڣ��ӿ����������󷽷���public String getValue(String str)
	 * ��������TestLambda�����б�д����ʹ�ýӿ���Ϊ��������һ���ַ���ת���ɴ�д������Ϊ�����ķ���ֵ
	 * ���ٽ�һ���ַ����ĵ�2���͵�4������λ�ý��н�ȡ�Ӵ�
	 */
	@Test
	public void test2() {
		String str1 = strHandler("\t\t\t HelloWorld   ", (e) -> e.trim());
		System.out.println("ȥ�ո�" + str1);
		
		String str2 = strHandler(str1, (e) -> e.toUpperCase());
		System.out.println("ת��д��" + str2);
		
		String str3 = strHandler(str2, (e) -> e.substring(2, 5));
		System.out.println("��ȡ��" + str3);
	}
	
	/**
	 * ���ַ������д���
	 */
	private String strHandler(String str, MyFun_2 fun) {
		return fun.getValue(str);
	} 
	
	/**
	 * ������һ�����������͵ĺ���ʽ�ӿڣ���������Ϊ<T, R> TΪ������RΪ����ֵ
	 * �ڽӿ���������Ӧ���󷽷�
	 * ����TestLambda��������������ʹ�ýӿ���Ϊ��������������long�Ͳ����ĺ�
	 * ���ټ�������long�Ͳ����ĳ˻�
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
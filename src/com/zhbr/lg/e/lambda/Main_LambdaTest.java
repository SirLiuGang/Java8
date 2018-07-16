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
 * �������ú͹���������<br/>
 * 
 * һ���������ã�<br/>��Lambda���ص��������з����Ѿ�ʵ���ˣ����ǿ���ʹ�á��������á�
 * 			���������Ϊ����������Lambda���ʽ����һ�ֱ�����ʽ��
 * 	��Ҫ�������﷨��ʽ��
 * 		1.����::ʵ��������
 * 		2.��::��̬������
 * 		3.��::ʵ��������
 * 		����2��3������ ��Lambda �Ĳ����б�ĵ�һ����������ʵ�������ĵ����ߣ��ڶ�������(���޲�)��ʵ�������Ĳ���ʱ����ʽ�� ����::ʵ��������
 * 	<br/>ǰ��������<br/>
 * 		1.�������������õķ�����<b>�����б�</b>����Ҫ�ͺ���ʽ�ӿ��г��󷽷��Ĳ����б���ͬ����ȫһ�£�
 * 		2.�������������õķ�����<b>����ֵ</b>����Ҫ�ͺ���ʽ�ӿ��г��󷽷��ķ���ֵ��ͬ����ȫһ�£�
 * 	<br/>
 * 
 * �������������ã�<br/>
 * 		��ʽ��ClassName::new;
 * 		ע�⣺��Ҫ���õĹ������Ĳ����б�Ҫ�뺯��ʽ�ӿ��г��󷽷��Ĳ����б���һ��
 * 	<br/>
 * 
 * �����������ã�<br/>
 * 		��ʽ��Type::new;
 * 
 * 
 * 
 */
public class Main_LambdaTest {

	/**
	 * �������� -- ����::ʵ��������
	 */
	@Test
	public void test1() {
		// ԭ�з����ĵ���
		Consumer<String> con = (x) -> System.out.println(x);
		con.accept("1111");
		
		// �������õķ�ʽ���е���
		Consumer<String> con1 = System.out::println;
		con1.accept("2222");
	}
	
	/**
	 * �������� -- ����::ʵ��������	��ϰ
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
	 * �������� -- ��::��̬������
	 */
	@SuppressWarnings("unused")
	@Test
	public void test2() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		
		Comparator<Integer> com1 = Integer::compare;
	}
	
	/**
	 * �������� -- ��::ʵ��������
	 */
	@Test
	public void test4() {
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		
		BiPredicate<String, String> bp2 = String::equals;
	}
	
	/**
	 * ���������� -- �޲ι�����
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
	 * ���������� -- �вι�����������һ�£�
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
		Employee emp1 = bifun1.apply("����", 18);
		System.out.println(emp1);
		
	}
	
	/**
	 * �������ã�
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

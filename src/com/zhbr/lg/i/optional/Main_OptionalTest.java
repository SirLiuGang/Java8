package com.zhbr.lg.i.optional;

import java.util.Optional;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;
import com.zhbr.lg.a.lambda.Employee.Status;

/**
 * Optional<T> �ࣨjava.util.Optional����һ�������࣬����һ��ֵ���ڻ򲻴��ڣ�ԭ����
 * 	null��ʾһ��ֵ�����ڣ�����Optional���Ը��õı�����������ҿ��Ա����ָ���쳣
 *
 *	���÷�����
 *	Optional.of(T t)������һ��Optionalʵ��
 *	Optional.empty()������һ���յ�Optionalʵ��
 *	Optional.ofNullable(T t)����t��Ϊnull������Optionalʵ�������򴴽���ʵ��
 *	isPresent()���ж��Ƿ����ֵ
 *	orElse(T t)��������ö������ֵ�����ظ�ֵ�����򷵻�t
 *	orElseGet(Supplier s)��������ö������ֵ�����ظ�ֵ�����򷵻�sȡ����ֵ
 *	map(Function f)�������ֵ���䴦�������ش�����Optional�����򷵻�Optional.empty()
 *	flatMap(Function mapper)����map���ƣ�Ҫ�󷵻�ֵ������Optional
 */
public class Main_OptionalTest {

	/**
	 * Optional.of(T t)������һ��Optionalʵ��
	 */
	@Test
	public void test1() {
		Optional<Employee> op = Optional.of(new Employee());
//		Optional<Employee> op = Optional.of(null);
		
		Employee emp = op.get();
		System.out.println(emp);
		
	}
	
	/**
	 * Optional.empty()������һ���յ�Optionalʵ��
	 */
	@Test
	public void test2() {
		Optional<Employee> op = Optional.empty();
		
		Employee emp = op.get();
		System.out.println(emp);
	}
	
	/**
	 * Optional.ofNullable(T t)����t��Ϊnull������Optionalʵ�������򴴽���ʵ��
	 */
	@Test
	public void test3() {
		Optional<Employee> op = Optional.ofNullable(new Employee());
//		Optional<Employee> op = Optional.ofNullable(null);
		
		System.out.println(op.get());
	}
	
	/**
	 * isPresent()���ж��Ƿ����ֵ
	 */
	@Test
	public void test4() {
		Optional<Employee> op = Optional.ofNullable(new Employee());
		
		if(op.isPresent()) {
			System.out.println(op.get());
		}
		
		System.out.println("-----------");
		
		Optional<Employee> op1 = Optional.ofNullable(null);
		
		if(op1.isPresent()) {
			System.out.println(op1.get());
		}
	}
	
	/**
	 * orElse(T t)��������ö������ֵ�����ظ�ֵ�����򷵻�t
	 */
	@Test
	public void test5() {
		Optional<Employee> op = Optional.ofNullable(null);
		
		Employee emp = op.orElse(new Employee("����", 18, 888.88, Status.BUSY));
		
		System.out.println(emp);
	}
	
	/**
	 * orElseGet(Supplier s)��������ö������ֵ�����ظ�ֵ�����򷵻�sȡ����ֵ
	 */
	@Test
	public void test6() {
		Optional<Employee> op = Optional.ofNullable(null);
		
		Employee emp = op.orElseGet(() -> new Employee());
		
		System.out.println(emp);
	}
	
	/**
	 * map(Function f)�������ֵ���䴦�������ش�����Optional�����򷵻�Optional.empty()
	 */
	@Test
	public void test7() {
		Optional<Employee> op = Optional.ofNullable(new Employee("����", 18, 888.88, Status.BUSY));
		
		Optional<String> str = op.map((e) -> e.getName());
		
		System.out.println(str.get());
	}
	
	/**
	 * flatMap(Function mapper)����map���ƣ�Ҫ�󷵻�ֵ������Optional
	 */
	@Test
	public void test8() {
		Optional<Employee> op = Optional.ofNullable(new Employee("����", 18, 888.88, Status.BUSY));
		
		Optional<String> str = op.flatMap((e) -> Optional.of(e.getName()));
		
		System.out.println(str.get());
	}
	
	/**
	 * ���⣺��ȡһ����������Ů�������
	 */
	@Test
	public void test() {
//		Man man = new Man();
//		String str = getGodnessName(man);
//		System.out.println(str);
		Optional<NewMan> op = Optional.ofNullable(null);
		String str2 = getGodnessName2(op);
		System.out.println(str2);
	}
	
	/**
	 * ��ȡ����Ů�������
	 */
	private String getGodnessName(Man man) {
		return man.getGodness().getName();	// ���ڿ�ָ���쳣
	}
	
	/**
	 * ��ȡ����Ů�������2
	 */
	private String getGodnessName2(Optional<NewMan> man) {
		return man.orElse(new NewMan())
					.getGodness()
					.orElse(new Godness("Ĭ������"))
					.getName();
	}
}

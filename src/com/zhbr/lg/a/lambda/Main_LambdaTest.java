package com.zhbr.lg.a.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

/**
 * ����ʹ��ԭ�������������������ʵ�֣�Ȼ��ʹ�����ģʽ�����Ż������ʹ��Lambda���ʽ����ʵ��
 * @author Administrator
 *
 */
public class Main_LambdaTest {

	/**
	 * ʹ��ԭ����������ʵ��Comparator�ıȽϷ���
	 */
	@Test
	public void test1() {
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		TreeSet<Integer> tree = new TreeSet<>(com);
	}
	
	/**
	 * ʹ��Lambda���ʽʵ��Comparator�ıȽϷ���
	 */
	@Test
	public void test2() {
		Comparator<Integer> com = (x, y) -> Integer.compare(y, x);
		System.out.println(com.compare(1, 2));
	}
	
	/**
	 * ��ʼ��Ա���б�
	 */
	List<Employee> EmployeeList = Arrays.asList(
			new Employee("����", 18, 1111.111),
			new Employee("����", 28, 2222.222),
			new Employee("����", 38, 3333.333),
			new Employee("����", 48, 4444.444),
			new Employee("����", 58, 5555.555)
	);
	
	/**
	 * �����ʵ�ַ�ʽ��ͨ�����ò�ͬ������ʵ�ֲ�ͬ�Ĺ���
	 */
	@Test
	public void test3() {
		List<Employee> list = getEmployeeByAge(EmployeeList);
		
		for(Employee emp : list) {
			System.out.println(emp);
		}
		
		System.out.println("------------------");
		
		List<Employee> list1 = getEmployeeBySalary(EmployeeList);
		
		for(Employee emp : list1) {
			System.out.println(emp);
		}
	}
	
	/**
	 * ����Ա��������й���
	 */
	public List<Employee> getEmployeeByAge(List<Employee> list) {
		List<Employee> newList = new ArrayList<Employee>();
		
		for(Employee emp : list) {
			if(emp.getAge() > 38) {
				newList.add(emp);
			}
		}
		
		return newList;
	}
	
	/**
	 * ����Ա�����ʽ��й���
	 */
	public List<Employee> getEmployeeBySalary(List<Employee> list) {
		List<Employee> newList = new ArrayList<Employee>();
		
		for(Employee emp : list) {
			if(emp.getSalary() > 3333) {
				newList.add(emp);
			}
		}
		
		return newList;
	}
	
	
	// �Ż�һ������ģʽ
	/**
	 * �Ե��ò�ͬ����ʵ�ֽ����Ż���
	 * ʹ�ò���ģʽ������һ���ӿڣ�Ȼ���ò�ͬ�Ľӿ�ʵ�ָýӿڣ������˵����ӽӿ�
	 */
	@Test
	public void test4() {
		List<Employee> list = getFilter(EmployeeList, new getIEmployeeByAge());
		
		for(Employee emp : list) {
			System.out.println(emp);
		}
		
		System.out.println("--------------------");
		
		List<Employee> list1 = getFilter(EmployeeList, new getIEmployeeBySalary());
		
		for(Employee emp : list1) {
			System.out.println(emp);
		}
	}
	
	/**
	 * ʹ�ýӿڵķ�ʽ����ʵ�ֹ�������
	 * @param list		Ա���б�
	 * @param filter	���˵Ľӿ�
	 * @return
	 */
	public List<Employee> getFilter(List<Employee> list, MyFilter<Employee> filter) {
		List<Employee> newList = new ArrayList<Employee>();
		
		for(Employee emp : list) {
			if(filter.filter(emp)) {
				newList.add(emp);
			}
		}
		
		return newList;
	}
	
	
	// �Ż���ʽ���������ڲ���
	/**
	 * ʹ�������ڲ���ʵ�֣�����һ���ӿڣ�Ȼ���ò�ͬ�������ڲ���ʵ�ָýӿ��еķ�������Ҫ�Լ�����ʵ��
	 */
	@Test
	public void test5() {
		List<Employee> list = getFilter(EmployeeList, new MyFilter<Employee>() {
			@Override
			public boolean filter(Employee t) {
				return t.getAge() < 38;
			}
		});
		
		for(Employee emp : list) {
			System.out.println(emp);
		}
	}
	
	
	// �Ż���ʽ����Lambda���ʽ
	/**
	 * ʹ��Lambda���ʽ���������ڲ�������Ż������ٴ�������
	 */
	@Test
	public void test6() {
		List<Employee> list = getFilter(EmployeeList, (e) -> e.getAge() < 38);
		list.forEach(System.out::println);
	}
	
	
	// �Ż���ʽ�ģ�Stream API
	/**
	 * ʹ��Stream API��������������ݿ�һ����������
	 */
	@Test
	public void test7() {
		EmployeeList.stream()
					.filter((e) -> e.getAge() < 38 && e.getSalary() < 5000)
					.forEach(System.out::println);
		
		System.out.println("---------------");
		
		EmployeeList.stream()
					.map(Employee::getName)
					.forEach(System.out::println);
					
	}
	
	
	
}

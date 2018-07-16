package com.zhbr.lg.a.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

/**
 * 首先使用原有匿名函数对需求进行实现，然后使用设计模式进行优化，最后使用Lambda表达式进行实现
 * @author Administrator
 *
 */
public class Main_LambdaTest {

	/**
	 * 使用原有匿名函数实现Comparator的比较方法
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
	 * 使用Lambda表达式实现Comparator的比较方法
	 */
	@Test
	public void test2() {
		Comparator<Integer> com = (x, y) -> Integer.compare(y, x);
		System.out.println(com.compare(1, 2));
	}
	
	/**
	 * 初始化员工列表
	 */
	List<Employee> EmployeeList = Arrays.asList(
			new Employee("张三", 18, 1111.111),
			new Employee("李四", 28, 2222.222),
			new Employee("王五", 38, 3333.333),
			new Employee("赵六", 48, 4444.444),
			new Employee("田七", 58, 5555.555)
	);
	
	/**
	 * 最初的实现方式，通过调用不同方法来实现不同的功能
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
	 * 根据员工年龄进行过滤
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
	 * 根据员工工资进行过滤
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
	
	
	// 优化一：策略模式
	/**
	 * 对调用不同方法实现进行优化。
	 * 使用策略模式：定义一个接口，然后用不同的接口实现该接口，供别人调用子接口
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
	 * 使用接口的方式进行实现过滤条件
	 * @param list		员工列表
	 * @param filter	过滤的接口
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
	
	
	// 优化方式二：匿名内部类
	/**
	 * 使用匿名内部类实现：定义一个接口，然后用不同的匿名内部类实现该接口中的方法，需要自己重新实现
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
	
	
	// 优化方式三：Lambda表达式
	/**
	 * 使用Lambda表达式：对匿名内部类进行优化，减少代码行数
	 */
	@Test
	public void test6() {
		List<Employee> list = getFilter(EmployeeList, (e) -> e.getAge() < 38);
		list.forEach(System.out::println);
	}
	
	
	// 优化方式四：Stream API
	/**
	 * 使用Stream API，可以像操作数据库一样操作数据
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

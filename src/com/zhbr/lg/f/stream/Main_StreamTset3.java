package com.zhbr.lg.f.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;
import com.zhbr.lg.a.lambda.Employee.Status;

/**
 * 3.终止操作
 * 	查找与匹配
 * 		allMatch——检查是否匹配所有元素
 * 		anyMatch——检查是否至少匹配一个元素
 * 		noneMatch——检查是否没有匹配所有元素
 * 		findFirst——返回第一个元素
 * 		findAny——返回当前流中的任意元素
 * 		count——返回流中元素的总个数
 * 		max——返回流中最大值
 * 		min——返回流中最小值
 * 
 *  规约
 * 		--可以将流中的元素反复结合起来，得到一个值
 * 		reduce(T indentity, BinaryOperator)
 * 		reduce(BinaryOperator)
 * 
 * 	收集
 * 		--将流转化为其他形式。接收一个Collection接口的实现，用于给Stream中元素做汇总的方法
 * 		collect
 */
public class Main_StreamTset3 {

	/**
	 * 初始化员工列表
	 */
	List<Employee> EmployeeList = Arrays.asList(
			new Employee("张三", 18, 1111.111, Status.FREE),
			new Employee("李四", 28, 2222.222, Status.BUSY),
			new Employee("王五", 38, 3333.333, Status.VOCATION),
			new Employee("赵六", 48, 5555.555, Status.FREE),
			new Employee("田七", 58, 4444.444, Status.BUSY)
	);
	
	/**
	 * allMatch——检查是否匹配所有元素
	 */
	@Test
	public void test1() {
		// 是否匹配所有的状态都为BUSY
		boolean b1 = EmployeeList.stream()
					.allMatch((e) -> e.getStatus().equals((Status.BUSY)));
		System.out.println(b1);
	}
	
	/**
	 * anyMatch——检查是否至少匹配一个元素
	 */
	@Test
	public void test2() {
		// 是否匹配至少一个为BUSY
		boolean b1 = EmployeeList.stream()
					.anyMatch((e) -> e.getStatus().equals((Status.BUSY)));
		System.out.println(b1);
	}
	
	/**
	 * noneMatch——检查是否没有匹配所有元素
	 */
	@Test
	public void test3() {
		// 是否没有匹配BUSY
		boolean b1 = EmployeeList.stream()
					.noneMatch((e) -> e.getStatus().equals((Status.BUSY)));
		System.out.println(b1);
	}
	
	/**
	 * findFirst——返回第一个元素
	 */
	@Test
	public void test4() {
		// 进行排序后返回第一个元素
		Optional<Employee> op = EmployeeList.stream()
					.sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
					.findFirst();
		System.out.println(op.get());
	}
	
	/**
	 * findAny——返回当前流中的任意元素
	 */
	@Test
	public void test5() {
		// 取出任意空闲状态的员工
		// 并行流，多个线程同时去找，谁先找到先输出谁
		Optional<Employee> op = EmployeeList.parallelStream()
					.filter((e) -> e.getStatus().equals(Status.FREE))
					.findAny();
		System.out.println(op.get());
	}
	
	/**
	 * count——返回流中元素的总个数
	 */
	@Test
	public void test6() {
		// 返回总数
		Long count = EmployeeList.stream()
				.count();
		System.out.println(count);
	}
	
	/**
	 * max——返回流中最大值
	 */
	@Test
	public void test7() {
		// 工资的最大值
		Optional<Employee> op = EmployeeList.stream()
				.max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(op.get());
	}
	
	/**
	 * min——返回流中最小值
	 */
	@Test
	public void test8() {
		// 最小的工资
		Optional<Double> op = EmployeeList.stream()
				.map(Employee::getSalary)
				.min(Double::compare);
		System.out.println(op.get());
		
		Optional<Double> op1 = EmployeeList.stream()
				.max((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
				.map(Employee::getSalary);
		System.out.println(op1.get());
	}
	
	/**
	 * 规约
	 * 		--可以将流中的元素反复结合起来，得到一个值
	 * 		reduce(T indentity, BinaryOperator)
	 * 		reduce(BinaryOperator)
	 */
	@Test
	public void test9() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Integer sum = list.stream()
				// 首先将0作为x，然后加1，再把1作为x，加2
			.reduce(0, (x, y) -> x + y);
		System.out.println(sum);
		
		// 所有员工的工资之和
		Optional<Double> op = EmployeeList.stream()
										.map(Employee::getSalary)
										.reduce(Double::sum);
		System.out.println(op.get());
	}
	
	
	/**
	 * 收集
	 * 		--将流转化为其他形式。接收一个Collection接口的实现，用于给Stream中元素做汇总的方法
	 * 		collect
	 */
	@Test
	public void test10() {
		// 对结果的一个收集
		// 所有员工的名字放到List集合中
		List<String> list = EmployeeList.stream()
								.map(Employee::getName)
								.collect(Collectors.toList());
		list.forEach(System.out::println);
		
		System.out.println("-------------------");
		
		// 所有员工的名字放到Set集合中
		Set<String> set = EmployeeList.stream()
								.map(Employee::getName)
								.collect(Collectors.toSet());
		set.forEach(System.out::println);
		
		System.out.println("-------------------");
		
		// 所有员工的名字放到Set集合中
		HashSet<String> hs = EmployeeList.stream()
								.map(Employee::getName)
								.collect(Collectors.toCollection(HashSet::new));
		hs.forEach(System.out::println);
		
		// 总人数
		Long count = EmployeeList.stream()
								.collect(Collectors.counting());
		System.out.println(count);
		
		// 工资平均值
		Double ave = EmployeeList.stream()
						.collect(Collectors.averagingDouble(Employee::getSalary));;
		System.out.println(ave);
		
		// 工资平均值
		Double ssum = EmployeeList.stream()
						.collect(Collectors.summingDouble(Employee::getSalary));;
		System.out.println(ssum);
		
		// 工资最大值
		Optional<Employee> max = EmployeeList.stream()
						.collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(),  e2.getSalary())));
		System.out.println(max.get());
		
		// 工资最小值
		Optional<Double> min = EmployeeList.stream()
						.map(Employee::getSalary)
						.collect(Collectors.minBy(Double::compare));
		System.out.println(min.get());
		
		// 分组
		// 按照状态进行分组
		Map<Status, List<Employee>> map = EmployeeList.stream()
						.collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(map);
		
		// 多级分组
		// 按照状态进行分组，状态分完按照年龄段分
		Map<Status, Map<String, List<Employee>>> map1 = EmployeeList.stream()
						.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
																		if(((Employee) e).getAge() <= 35) {
																			return "青年";
																		} else if(((Employee) e).getAge() <= 50){
																			return "中年";
																		} else {
																			return "老年";
																		}
																	})));
		System.out.println(map1);
		
		// 分区/分片
		Map<Boolean, List<Employee>> map2 = EmployeeList.stream()
						.collect(Collectors.partitioningBy((e) -> e.getSalary() > 5000));
		System.out.println(map2);
		
		// 另一种取平均数或求和的方法
		DoubleSummaryStatistics dss = EmployeeList.stream()
						.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(dss.getSum());
		System.out.println(dss.getAverage());
		System.out.println(dss.getMax());
		
		// 
		String str = EmployeeList.stream()
							.map(Employee::getName)
							.collect(Collectors.joining(",", "===", "==="));
		System.out.println(str);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

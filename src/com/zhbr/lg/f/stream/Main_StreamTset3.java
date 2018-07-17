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
 * 3.��ֹ����
 * 	������ƥ��
 * 		allMatch��������Ƿ�ƥ������Ԫ��
 * 		anyMatch��������Ƿ�����ƥ��һ��Ԫ��
 * 		noneMatch��������Ƿ�û��ƥ������Ԫ��
 * 		findFirst�������ص�һ��Ԫ��
 * 		findAny�������ص�ǰ���е�����Ԫ��
 * 		count������������Ԫ�ص��ܸ���
 * 		max���������������ֵ
 * 		min��������������Сֵ
 * 
 *  ��Լ
 * 		--���Խ����е�Ԫ�ط�������������õ�һ��ֵ
 * 		reduce(T indentity, BinaryOperator)
 * 		reduce(BinaryOperator)
 * 
 * 	�ռ�
 * 		--����ת��Ϊ������ʽ������һ��Collection�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
 * 		collect
 */
public class Main_StreamTset3 {

	/**
	 * ��ʼ��Ա���б�
	 */
	List<Employee> EmployeeList = Arrays.asList(
			new Employee("����", 18, 1111.111, Status.FREE),
			new Employee("����", 28, 2222.222, Status.BUSY),
			new Employee("����", 38, 3333.333, Status.VOCATION),
			new Employee("����", 48, 5555.555, Status.FREE),
			new Employee("����", 58, 4444.444, Status.BUSY)
	);
	
	/**
	 * allMatch��������Ƿ�ƥ������Ԫ��
	 */
	@Test
	public void test1() {
		// �Ƿ�ƥ�����е�״̬��ΪBUSY
		boolean b1 = EmployeeList.stream()
					.allMatch((e) -> e.getStatus().equals((Status.BUSY)));
		System.out.println(b1);
	}
	
	/**
	 * anyMatch��������Ƿ�����ƥ��һ��Ԫ��
	 */
	@Test
	public void test2() {
		// �Ƿ�ƥ������һ��ΪBUSY
		boolean b1 = EmployeeList.stream()
					.anyMatch((e) -> e.getStatus().equals((Status.BUSY)));
		System.out.println(b1);
	}
	
	/**
	 * noneMatch��������Ƿ�û��ƥ������Ԫ��
	 */
	@Test
	public void test3() {
		// �Ƿ�û��ƥ��BUSY
		boolean b1 = EmployeeList.stream()
					.noneMatch((e) -> e.getStatus().equals((Status.BUSY)));
		System.out.println(b1);
	}
	
	/**
	 * findFirst�������ص�һ��Ԫ��
	 */
	@Test
	public void test4() {
		// ��������󷵻ص�һ��Ԫ��
		Optional<Employee> op = EmployeeList.stream()
					.sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
					.findFirst();
		System.out.println(op.get());
	}
	
	/**
	 * findAny�������ص�ǰ���е�����Ԫ��
	 */
	@Test
	public void test5() {
		// ȡ���������״̬��Ա��
		// ������������߳�ͬʱȥ�ң�˭���ҵ������˭
		Optional<Employee> op = EmployeeList.parallelStream()
					.filter((e) -> e.getStatus().equals(Status.FREE))
					.findAny();
		System.out.println(op.get());
	}
	
	/**
	 * count������������Ԫ�ص��ܸ���
	 */
	@Test
	public void test6() {
		// ��������
		Long count = EmployeeList.stream()
				.count();
		System.out.println(count);
	}
	
	/**
	 * max���������������ֵ
	 */
	@Test
	public void test7() {
		// ���ʵ����ֵ
		Optional<Employee> op = EmployeeList.stream()
				.max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(op.get());
	}
	
	/**
	 * min��������������Сֵ
	 */
	@Test
	public void test8() {
		// ��С�Ĺ���
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
	 * ��Լ
	 * 		--���Խ����е�Ԫ�ط�������������õ�һ��ֵ
	 * 		reduce(T indentity, BinaryOperator)
	 * 		reduce(BinaryOperator)
	 */
	@Test
	public void test9() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Integer sum = list.stream()
				// ���Ƚ�0��Ϊx��Ȼ���1���ٰ�1��Ϊx����2
			.reduce(0, (x, y) -> x + y);
		System.out.println(sum);
		
		// ����Ա���Ĺ���֮��
		Optional<Double> op = EmployeeList.stream()
										.map(Employee::getSalary)
										.reduce(Double::sum);
		System.out.println(op.get());
	}
	
	
	/**
	 * �ռ�
	 * 		--����ת��Ϊ������ʽ������һ��Collection�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
	 * 		collect
	 */
	@Test
	public void test10() {
		// �Խ����һ���ռ�
		// ����Ա�������ַŵ�List������
		List<String> list = EmployeeList.stream()
								.map(Employee::getName)
								.collect(Collectors.toList());
		list.forEach(System.out::println);
		
		System.out.println("-------------------");
		
		// ����Ա�������ַŵ�Set������
		Set<String> set = EmployeeList.stream()
								.map(Employee::getName)
								.collect(Collectors.toSet());
		set.forEach(System.out::println);
		
		System.out.println("-------------------");
		
		// ����Ա�������ַŵ�Set������
		HashSet<String> hs = EmployeeList.stream()
								.map(Employee::getName)
								.collect(Collectors.toCollection(HashSet::new));
		hs.forEach(System.out::println);
		
		// ������
		Long count = EmployeeList.stream()
								.collect(Collectors.counting());
		System.out.println(count);
		
		// ����ƽ��ֵ
		Double ave = EmployeeList.stream()
						.collect(Collectors.averagingDouble(Employee::getSalary));;
		System.out.println(ave);
		
		// ����ƽ��ֵ
		Double ssum = EmployeeList.stream()
						.collect(Collectors.summingDouble(Employee::getSalary));;
		System.out.println(ssum);
		
		// �������ֵ
		Optional<Employee> max = EmployeeList.stream()
						.collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(),  e2.getSalary())));
		System.out.println(max.get());
		
		// ������Сֵ
		Optional<Double> min = EmployeeList.stream()
						.map(Employee::getSalary)
						.collect(Collectors.minBy(Double::compare));
		System.out.println(min.get());
		
		// ����
		// ����״̬���з���
		Map<Status, List<Employee>> map = EmployeeList.stream()
						.collect(Collectors.groupingBy(Employee::getStatus));
		System.out.println(map);
		
		// �༶����
		// ����״̬���з��飬״̬���갴������η�
		Map<Status, Map<String, List<Employee>>> map1 = EmployeeList.stream()
						.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
																		if(((Employee) e).getAge() <= 35) {
																			return "����";
																		} else if(((Employee) e).getAge() <= 50){
																			return "����";
																		} else {
																			return "����";
																		}
																	})));
		System.out.println(map1);
		
		// ����/��Ƭ
		Map<Boolean, List<Employee>> map2 = EmployeeList.stream()
						.collect(Collectors.partitioningBy((e) -> e.getSalary() > 5000));
		System.out.println(map2);
		
		// ��һ��ȡƽ��������͵ķ���
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

package com.zhbr.lg.i.optional;

import java.util.Optional;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

import com.zhbr.lg.a.lambda.Employee;
import com.zhbr.lg.a.lambda.Employee.Status;

/**
 * Optional<T> 类（java.util.Optional）是一个容器类，代表一个值存在或不存在，原来用
 * 	null表示一个值不存在，现在Optional可以更好的表达这个概念。并且可以避免空指针异常
 *
 *	常用方法：
 *	Optional.of(T t)：创建一个Optional实例
 *	Optional.empty()：创建一个空的Optional实例
 *	Optional.ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
 *	isPresent()：判断是否包含值
 *	orElse(T t)：如果调用对象包含值，返回该值，否则返回t
 *	orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回s取到的值
 *	map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
 *	flatMap(Function mapper)：与map类似，要求返回值必须是Optional
 */
public class Main_OptionalTest {

	/**
	 * Optional.of(T t)：创建一个Optional实例
	 */
	@Test
	public void test1() {
		Optional<Employee> op = Optional.of(new Employee());
//		Optional<Employee> op = Optional.of(null);
		
		Employee emp = op.get();
		System.out.println(emp);
		
	}
	
	/**
	 * Optional.empty()：创建一个空的Optional实例
	 */
	@Test
	public void test2() {
		Optional<Employee> op = Optional.empty();
		
		Employee emp = op.get();
		System.out.println(emp);
	}
	
	/**
	 * Optional.ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
	 */
	@Test
	public void test3() {
		Optional<Employee> op = Optional.ofNullable(new Employee());
//		Optional<Employee> op = Optional.ofNullable(null);
		
		System.out.println(op.get());
	}
	
	/**
	 * isPresent()：判断是否包含值
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
	 * orElse(T t)：如果调用对象包含值，返回该值，否则返回t
	 */
	@Test
	public void test5() {
		Optional<Employee> op = Optional.ofNullable(null);
		
		Employee emp = op.orElse(new Employee("张三", 18, 888.88, Status.BUSY));
		
		System.out.println(emp);
	}
	
	/**
	 * orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回s取到的值
	 */
	@Test
	public void test6() {
		Optional<Employee> op = Optional.ofNullable(null);
		
		Employee emp = op.orElseGet(() -> new Employee());
		
		System.out.println(emp);
	}
	
	/**
	 * map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
	 */
	@Test
	public void test7() {
		Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 888.88, Status.BUSY));
		
		Optional<String> str = op.map((e) -> e.getName());
		
		System.out.println(str.get());
	}
	
	/**
	 * flatMap(Function mapper)：与map类似，要求返回值必须是Optional
	 */
	@Test
	public void test8() {
		Optional<Employee> op = Optional.ofNullable(new Employee("张三", 18, 888.88, Status.BUSY));
		
		Optional<String> str = op.flatMap((e) -> Optional.of(e.getName()));
		
		System.out.println(str.get());
	}
	
	/**
	 * 例题：获取一个男人心中女神的名字
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
	 * 获取男人女神的名字
	 */
	private String getGodnessName(Man man) {
		return man.getGodness().getName();	// 存在空指针异常
	}
	
	/**
	 * 获取男人女神的名字2
	 */
	private String getGodnessName2(Optional<NewMan> man) {
		return man.orElse(new NewMan())
					.getGodness()
					.orElse(new Godness("默认名称"))
					.getName();
	}
}

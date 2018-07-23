package com.zhbr.lg.d.interface4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * java8中内置的四大核心函数式接口
 * 
 * Consumer<T> ： 消费型接口
 * 			void accept(T t);
 * 
 * Supplier<T> ： 供给型接口
 * 			T get();
 * 
 * Function<T, R> ： 函数型接口
 * 			R apply(T t);
 * 
 * Predicate<T> ： 断言型接口
 * 			boolean test(T t);
 */
public class Main_LambdaTest {

	/**
	 * Consumer<T> ： 消费型接口
	 * 		void accept(T t);
	 */
	@Test
	public void test1() {
		happy(10000, (e) -> System.out.println("消费型接口：" + e));
	}
	
	/**
	 * 进行消费
	 */
	private void happy(double money, Consumer<Double> con) {
		con.accept(money);
	}
	
	/**
	 * Supplier<T> ： 供给型接口
	 * 		T get();
	 */
	@Test
	public void test2() {
		List<Integer> list = getNumberList(10, () -> (int)(Math.random() * 100));
		
		for(Integer num : list) {
			System.out.println(num);
		}
	}
	
	/**
	 * 产生一些整数并放入集合中
	 */
	public List<Integer> getNumberList(int num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < num; i++) {
			list.add(sup.get());
		}
		
		return list;
	}
	
	/**
	 * Function<T, R> ： 函数型接口
	 * 		R apply(T t);
	 */
	@Test
	public void test3() {
		String str1 = strHandler("\t\t\t HelloWorld   ", (e) -> e.trim());
		System.out.println(str1);
		
		String str2 = strHandler(str1, (e) -> e.substring(2, 5));
		System.out.println(str2);
		
		String str3 = strHandler(str1, (e) -> e.toUpperCase());
		System.out.println(str3);
	}
	
	/**
	 * 用于处理字符串
	 */
	private String strHandler(String str, Function<String, String> fun) {
		return fun.apply(str);
	}

	/**
	 * Predicate<T> ： 断言型接口
	 * 		boolean test(T t);
	 */
	@Test
	public void test4() {
		List<String> list = Arrays.asList("Hello", "World", "a", "aa", "aaa");
		List<String> strList = filterStr(list, (e) -> e.length() > 3);
		
		for(String str : strList) {
			System.out.println(str);
		}
	}
	
	/**
	 * 将满足条件的字符串放入集合中
	 */
	private List<String> filterStr(List<String> list, Predicate<String> pre) {
		List<String> strList = new ArrayList<>();
		
		for(String str : list) {
			if(pre.test(str)) {
				strList.add(str);
			}
		}
		
		return strList;
		
	}
	
	/**
	 * 扩展子接口练习
	 */
	@Test
	public void test5(){
		BiFunction<String, String, String> a = (x,y) -> {
			System.out.println(x+y);
			return x+y;
		};
		a.apply("hello", "world");
	}
	
}

package com.zhbr.lg.b.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * һ��Lambda ���ʽ�Ļ����﷨��Java8��������һ���µĲ�������->������֮Ϊ��ͷ����������Lambda������
 * 								��ͷ��������Lambda��Ϊ�������֣�
 * 									��ࣺLambda���ʽ�Ĳ����б�
 * 									�ҲࣺLambda���ʽ������ִ�еĹ��ܣ���Lambda��
 * 
 * 	�﷨��ʽһ���޲������޷���ֵ��								() -> System.out.println("Hello");
 * 	�﷨��ʽ������һ���������޷���ֵ��							(e) -> System.out.println(e);
 * 	�﷨��ʽ������ֻ��һ��������С���ſ���ʡ�Բ�д				e -> System.out.println(e);
 * 	�﷨��ʽ�ģ����������ϲ������з���ֵ��Lambda�����ж������	(x, y) -> {
 *																System.out.println("Lambda expression��x = " + x + ", y = " + y);
 *																return Integer.compare(x, y);
 *															};
 * 	�﷨��ʽ�壺��ֻ��һ����䣬�����ź�return��ʡ�Բ�д		(x, y) ->  Integer.compare(x, y);
 * 	�﷨��ʽ����Lambda���ʽ�Ĳ����б��������Ϳ���ʡ�Բ�д����ΪJVM����������ͨ���������ƶϳ��������ͣ����������ƶϡ�
 * 
 * 	������һ����ʡ
 * 	����ƶ�����ʡ
 * 
 * ����Lambda���ʽ��Ҫ������ʽ�ӿڡ���֧��
 * 	����ʽ�ӿڣ��ӿ���ֻ��һ�����󷽷��Ľӿڣ���Ϊ����ʽ�ӿڡ�����ʹ��@FunctionaInterface���Σ����Լ���Ƿ��Ǻ���ʽ�ӿ�
 * 				�����MyFilter.java��
 * 
 */
public class Main_LambdaTest {

	/**
	 * �﷨��ʽһ���޲������޷���ֵ��					() -> System.out.println("Hello");
	 */
	@Test
	public void test1() {
		/*final */int num = 0;	// jdk1.7��ǰ������final��1.8Ĭ���Զ�����final
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("Anonymous Inner Class �޲������޷���ֵ" + num);
			}
		};
		r.run();
		
		System.out.println("----------");
		
		Runnable r_new = () -> System.out.println("Lambda expression �޲������޷���ֵ" + num);
		r_new.run();
	}
	
	/**
	 * �﷨��ʽ������һ���������޷���ֵ��							(e) -> System.out.println(e);
	 */
	@Test
	public void test2() {
		Consumer<String> con = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println("Anonymous Inner Class " + t);
			}
		};
		con.accept("��һ���������޷���ֵ");
		
		System.out.println("-------------");
		
		Consumer<String> con_new = (e) -> System.out.println("Lambda expression " + e);
		con_new.accept("��һ���������޷���ֵ");
	}
	
	/**
	 * �﷨��ʽ������ֻ��һ��������С���ſ���ʡ�Բ�д				e -> System.out.println(e);
	 */
	@Test
	public void test3() {
		Consumer<String> con_new = e -> System.out.println("Lambda expression " + e);
		con_new.accept("ֻ��һ��������С���ſ���ʡ�Բ�д");
	}
	
	/**
	 * �﷨��ʽ�ģ����������ϲ������з���ֵ��Lambda�����ж������
	 * (x, y) -> {
	 *				System.out.println("Lambda expression��x = " + x + ", y = " + y);
	 *				return Integer.compare(x, y);
	 * };
	 */
	@Test
	public void test4() {
		Comparator<Integer> com = new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				System.out.println("���������ϲ������з���ֵ��Lambda�����ж������");
				System.out.println("Anonymous Inner Class��x = " + x + ", y = " + y);
				return Integer.compare(x, y);
			}
		};
		System.out.println(com.compare(10, 20));
		
		System.out.println("------------");
		
		Comparator<Integer> com_new = (x, y) -> {
			System.out.println("���������ϲ������з���ֵ��Lambda�����ж������");
			System.out.println("Lambda expression��x = " + x + ", y = " + y);
			return Integer.compare(x, y);
		};
		System.out.println(com_new.compare(10, 20));
	}
	
	/**
	 * �﷨��ʽ�壺��ֻ��һ����䣬�����ź�return��ʡ�Բ�д		(x, y) ->  Integer.compare(x, y);
	 */
	@Test
	public void test5() {
		System.out.println("��ֻ��һ����䣬�����ź�return��ʡ�Բ�д");
		Comparator<Integer> com_new = (x, y) ->  Integer.compare(x, y);
		System.out.println(com_new.compare(10, 20));
	}
	
	/**
	 * �﷨��ʽ����Lambda���ʽ�Ĳ����б��������Ϳ���ʡ�Բ�д����ΪJVM����������ͨ���������ƶϳ��������ͣ����������ƶϡ�
	 */
	@Test
	public void test6() {
		System.out.println("Lambda���ʽ�Ĳ����б��������Ϳ���ʡ�Բ�д����ΪJVM����������ͨ���������ƶϳ��������ͣ����������ƶϡ�");
		Comparator<Integer> com_new = (Integer x, Integer y) ->  Integer.compare(x, y);
		System.out.println(com_new.compare(10, 20));
		
		
		String[] strs = {"aaa", "bbb", "ccc"};
		List<String> list = new ArrayList<>();	// 1.7�Ժ���Բ�д��ߵ�����
		show(new HashMap<>());					// 1.7�Ժ���Բ�д��ߵ�����
	}
	
	private void show(Map<String, Integer> map) {
		
	}
	
	// ���󣺶�һ������������
	@Test
	public void test7() {
		System.out.println(operation(100, (e) -> e * e));
		System.out.println(operation(100, (e) -> e + 200));
	}
	
	private Integer operation(Integer num, MyFun my) {
		return my.getValue(num);
	}
	
	
	
	
	
	
	
	
}

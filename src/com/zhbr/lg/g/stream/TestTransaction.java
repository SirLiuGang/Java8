package com.zhbr.lg.g.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class TestTransaction {
List<Transaction> transactions = null;
	
	@Before
	public void before(){
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950)
		);
	}
	
	/**
	 * 1. �ҳ�2011�귢�������н��ף� �������׶����򣨴ӵ͵��ߣ�
	 */
	@Test
	public void test1(){
		transactions.stream()
					.filter((e) -> e.getYear() == 2011)
					.sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
					.forEach(System.out::println);
	}
	
	/**
	 * 2. ����Ա������Щ��ͬ�ĳ��й�������
	 */
	@Test
	public void test2(){
		transactions.stream()
					.map((e) -> e.getTrader().getCity())
					.distinct()
					.forEach(System.out::println);
	}
	
	/**
	 * 3. �����������Խ��ŵĽ���Ա��������������
	 */
	@Test
	public void test3(){
		transactions.stream()
					.filter((e) -> "Cambridge".equals(e.getTrader().getCity()))
					.map(Transaction::getTrader)
					.sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
					.distinct()
					.forEach(System.out::println);
	}
	
	/**
	 * 4. �������н���Ա�������ַ���������ĸ˳������
	 */
	@Test
	public void test4(){
		transactions.stream()
					.map(Transaction::getTrader)
					.map(Trader::getName)
					.distinct()
					.sorted((e1, e2) -> e1.compareTo(e2))
					.forEach(System.out::println);
		
		System.out.println("--------------");
		
		String str = transactions.stream()
								.map((e) -> e.getTrader().getName())
								.sorted()
								.reduce("", String::concat);
		System.out.println(str);
		
		System.out.println("--------------");
		
		transactions.stream()
					.map((e) -> e.getTrader().getName())
					.flatMap(TestTransaction::filterCharacter)
					.sorted((e1, e2) -> e1.compareToIgnoreCase(e2))
					.forEach(System.out::print);
	}
	
	public static Stream<String> filterCharacter(String str) {
		List<String> list = new ArrayList<>();
		
		for(Character ch :str.toCharArray()) {
			list.add(ch.toString());
		}
		
		return list.stream();
	}
	
	
	/**
	 * 5. ��û�н���Ա�������������ģ�
	 */
	@Test
	public void test5(){
		boolean b1 = transactions.stream()
							.anyMatch((e) -> "Milan".equals(e.getTrader().getCity()));
		System.out.println(b1);
	}
	
	
	/**
	 * 6. ��ӡ�����ڽ��ŵĽ���Ա�����н��׶�
	 */
	@Test
	public void test6(){
		Optional<Integer> sum = transactions.stream()
									.filter((e) -> "Cambridge".equals(e.getTrader().getCity()))
									.map((e) -> e.getValue())
									.reduce(Integer::sum);
		System.out.println(sum.get());
	}
	
	
	/**
	 * 7. ���н����У���ߵĽ��׶��Ƕ���
	 */
	@Test
	public void test7(){
		Optional<Integer> sum = transactions.stream()
						.map((e) -> e.getValue())
						.max(Integer::compare);
		System.out.println(sum.get());
	}
	
	/**
	 * 8. �ҵ����׶���С�Ľ���
	 */
	@Test
	public void test8(){
		Optional<Transaction> sum = transactions.stream()
										.min((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
		System.out.println(sum.get());
	}
}

package com.zhbr.lg.h.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class Main_ForkJoinTest {

	/**
	 * ForkJoin框架
	 */
	@Test
	public void test1() {
		Instant start = Instant.now();
		
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0, 10000000000L);
		
		Long sum = pool.invoke(task);
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		System.out.println(Duration.between(start, end).toMillis());
	}
	
	/**
	 * 普通for循环
	 */
	@Test
	public void test2() {
		Instant start = Instant.now();
		
		long sum = 0L;
		
		for(long i = 0; i <= 10000000000L; i++) {
			sum += i;
		}
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		System.out.println(Duration.between(start, end).toMillis());
	}
	
	/**
	 * java8并行流
	 */
	@Test
	public void test3() {
		Instant start = Instant.now();
		
		Long sum = LongStream.rangeClosed(0, 10000000000L)
							.parallel()
							.reduce(0, Long::sum);
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		System.out.println(Duration.between(start, end).toMillis());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

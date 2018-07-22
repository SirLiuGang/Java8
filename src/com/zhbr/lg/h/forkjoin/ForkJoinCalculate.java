package com.zhbr.lg.h.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 并行流与顺序流
 * 	并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流
 * 	java8中将并行进行了优化，我们可以很容易的对数据进行并行操作
 * 	Stream API可以声明性地通过parallel()与sequential()在并行流与顺序流之间进行切换
 * 
 * Fork/Join框架
 * 	就是在必要的情况下，将一个大任务，进行拆分(fork)成若干个小任务(拆到不可再拆时)，再将一个个的小任务运算的结果进行join汇总
 * 	采用“工作窃取”模式（work-stealing）
 * 	当执行新的任务时他可以将其拆分分成更小的任务执行，并将小任务加到线程队列中，然后再从一个随机线程的队列汇总偷一个并把它放在自己的队列中
 * 		优势：
 * 				相对于一般的线程实现，fork/join框架的优势体现在对其中包含的任务的处理方式上，在一般的线程池汇总，如果
 * 			一个线程正在执行的任务由于某些原因无法继续运行，那么该线程会处于等待状态，而在fork/join框架中，如果某个
 * 			子问题由于等待另外一个子问题的完成而无法继续运行，那么处理该子问题的线程会自动寻找其他尚未运行的子问题来
 * 			执行。这种方式减少了线程的等待时间，提高了性能。
 * 
 * 
 */
public class ForkJoinCalculate extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1094121773343108572L;

	private long start;
	private long end;
	
	private static final long THRESHOLD = 10000;
	
	public ForkJoinCalculate() {
	}
	public ForkJoinCalculate(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	// 类似于递归
	@Override
	protected Long compute() {
		long length = end - start;
		
		if(length < THRESHOLD) {
			long sum = 0L;
			
			for(long i = start; i <= end; i++) {
				sum += i;
			}
			
			return sum;
			
		} else {
			long middle = (start + end) / 2;
			ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
			left.fork();		// 拆分子任务，同时压入线程队列
			
			ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
			right.fork();		// 拆分子任务，同时压入线程队列
			
			return left.join() + right.join();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

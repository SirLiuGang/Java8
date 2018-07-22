package com.zhbr.lg.h.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * ��������˳����
 * 	���������ǰ�һ�����ݷֳɶ�����ݿ飬���ò�ͬ���̷ֱ߳���ÿ�����ݿ����
 * 	java8�н����н������Ż������ǿ��Ժ����׵Ķ����ݽ��в��в���
 * 	Stream API���������Ե�ͨ��parallel()��sequential()�ڲ�������˳����֮������л�
 * 
 * Fork/Join���
 * 	�����ڱ�Ҫ������£���һ�������񣬽��в��(fork)�����ɸ�С����(�𵽲����ٲ�ʱ)���ٽ�һ������С��������Ľ������join����
 * 	���á�������ȡ��ģʽ��work-stealing��
 * 	��ִ���µ�����ʱ�����Խ����ֳַɸ�С������ִ�У�����С����ӵ��̶߳����У�Ȼ���ٴ�һ������̵߳Ķ��л���͵һ�������������Լ��Ķ�����
 * 		���ƣ�
 * 				�����һ����߳�ʵ�֣�fork/join��ܵ����������ڶ����а���������Ĵ���ʽ�ϣ���һ����̳߳ػ��ܣ����
 * 			һ���߳�����ִ�е���������ĳЩԭ���޷��������У���ô���̻߳ᴦ�ڵȴ�״̬������fork/join����У����ĳ��
 * 			���������ڵȴ�����һ�����������ɶ��޷��������У���ô�������������̻߳��Զ�Ѱ��������δ���е���������
 * 			ִ�С����ַ�ʽ�������̵߳ĵȴ�ʱ�䣬��������ܡ�
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
	
	// �����ڵݹ�
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
			left.fork();		// ���������ͬʱѹ���̶߳���
			
			ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
			right.fork();		// ���������ͬʱѹ���̶߳���
			
			return left.join() + right.join();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

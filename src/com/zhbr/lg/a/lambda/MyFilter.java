package com.zhbr.lg.a.lambda;

/**
 * ����ʽ�ӿ�ע��
 */
@FunctionalInterface
public interface MyFilter<T> {
	public boolean filter(T t);
}

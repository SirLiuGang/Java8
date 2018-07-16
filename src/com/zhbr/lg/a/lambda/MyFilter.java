package com.zhbr.lg.a.lambda;

/**
 * 函数式接口注解
 */
@FunctionalInterface
public interface MyFilter<T> {
	public boolean filter(T t);
}

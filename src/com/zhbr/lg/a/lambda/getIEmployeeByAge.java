package com.zhbr.lg.a.lambda;

public class getIEmployeeByAge implements MyFilter<Employee> {

	@Override
	public boolean filter(Employee t) {
		return t.getAge() >= 35;
	}

}

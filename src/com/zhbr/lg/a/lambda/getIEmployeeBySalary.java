package com.zhbr.lg.a.lambda;

public class getIEmployeeBySalary implements MyFilter<Employee> {

	@Override
	public boolean filter(Employee t) {
		return t.getSalary() > 4444;
	}

}

package com.zhbr.lg.i.optional;

public class Godness {

	private String name;

	public Godness() {
		super();
	}

	public Godness(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Godness [name=" + name + "]";
	}
	
}

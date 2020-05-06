package com.test.diEx01;

public class MyGetSum {
	public GetSum getsum;
	private int a;
	private int b;
	
	public MyGetSum() {
	}
	
	public void sum() {
		getsum.sum(a,b);
	}

	public void setGetsum(GetSum getsum) {
		this.getsum = getsum;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}
}

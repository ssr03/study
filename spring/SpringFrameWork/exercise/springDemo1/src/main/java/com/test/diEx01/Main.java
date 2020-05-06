package com.test.diEx01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// MyGetSum 내용 불러오기
//		MyGetSum myGetSum = new MyGetSum();
//		myGetSum.setGetsum(new GetSum());
//		
//		myGetSum.setA(10);
//		myGetSum.setB(100);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:getsum.xml");
		MyGetSum myGetSum = ctx.getBean("myGetSum", MyGetSum.class);
		
		myGetSum.sum();
	}
}

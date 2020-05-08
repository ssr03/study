package com.test.diEx05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CarTester {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:car.xml");
		Car car =ctx.getBean("car", Car.class);
		car.drive();
	}
}

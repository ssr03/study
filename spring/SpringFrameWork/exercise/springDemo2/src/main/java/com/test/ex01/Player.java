package com.test.ex01;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Player implements InitializingBean, DisposableBean{
	private String name;
	private int age;
	
	public Player() {}
	
	public Player(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("빈의 소멸시 처리할 명령");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("빈의 생성시 처리할 명령");	
	}
	
}

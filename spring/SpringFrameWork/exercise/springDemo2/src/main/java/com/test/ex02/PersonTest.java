package com.test.ex02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PersonTest {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:person.xml");
		
		//scope="singleton"(default)
		Person person1 = ctx.getBean("person",Person.class);
		System.out.println("이름: " + person1.getName());
		System.out.println("나이: " + person1.getAge());
		
		System.out.println("---------------------------");
		
		Person person2 = ctx.getBean("person",Person.class);
		System.out.println("이름: " + person2.getName());
		System.out.println("나이: " + person2.getAge());
		
		System.out.println("---------------------------");
		if(person1.equals(person2))System.out.println("person1 == person2");
		else System.out.println("person1 != person2");
		
		System.out.println("\n======================================\n");
		
		//scope="prototype"
		Person personP1 = ctx.getBean("personP",Person.class);
		System.out.println("이름: " + personP1.getName());
		System.out.println("나이: " + personP1.getAge());
		
		System.out.println("---------------------------");
		
		Person personP2 = ctx.getBean("personP",Person.class);
		System.out.println("이름: " + personP2.getName());
		System.out.println("나이: " + personP2.getAge());
		
		System.out.println("---------------------------");
		if(personP1.equals(personP2))System.out.println("personP1 == personP2");
		else System.out.println("personP1 != person2");
		
	}
}

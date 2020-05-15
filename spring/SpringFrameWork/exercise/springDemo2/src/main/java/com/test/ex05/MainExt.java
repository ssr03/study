package com.test.ex05;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExt {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:ext.xml");
		
		ExternalFileEx ext = ctx.getBean("externalFileEx", ExternalFileEx.class);
		
		System.out.println("envId: " + ext.getId());
		System.out.println("envPwd: " + ext.getPwd());
		System.out.println("extId: " + ext.getExtId());
		System.out.println("extPwd: " + ext.getExtPwd());
		ctx.close();
		
	}

}

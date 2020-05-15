package com.test.ex06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.ex05.ExternalFileEx;

public class MainExt2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ExtConfig.class);
		ExternalFileEx ext=ctx.getBean("extConf", ExternalFileEx.class); // ExtConfig 클래스의 extConfig 메서드
		
		System.out.println("envId: " + ext.getId());
		System.out.println("envPwd: " + ext.getPwd());
		System.out.println("extId: " + ext.getExtId());
		System.out.println("extPwd: " + ext.getExtPwd());
		
		ctx.close();
	}
}

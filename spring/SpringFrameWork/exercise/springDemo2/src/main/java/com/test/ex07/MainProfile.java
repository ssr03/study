package com.test.ex07;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainProfile {
	public static void main(String[] args) {
		String profile = null;
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		if(str.equals("dev")) {
			profile = "dev";
		}else if(str.equals("service")) {
			profile = "service";
		}
		sc.close();
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles(profile); //설정파일을 결정
		ctx.load("dev.xml", "service.xml");
		
		ProfileEx prof = ctx.getBean("profileEx", ProfileEx.class);
		System.out.println("ip: " + prof.getIp());
		System.out.println("port: " + prof.getPort());
		
		ctx.close();
		
	}
}

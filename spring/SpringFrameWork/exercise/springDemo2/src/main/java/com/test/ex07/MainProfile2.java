package com.test.ex07;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainProfile2 {

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
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setActiveProfiles(profile); //설정파일을 결정
		ctx.register(DevConfig.class, SvcConfig.class);
		ctx.refresh();
		
		ProfileEx prof = ctx.getBean("profileEx", ProfileEx.class);
		System.out.println("ip: " + prof.getIp());
		System.out.println("port: " + prof.getPort());
		
		ctx.close();

	}

}

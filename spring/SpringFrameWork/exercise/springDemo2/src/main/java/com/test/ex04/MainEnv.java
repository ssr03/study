package com.test.ex04;
import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class MainEnv {
	
	public static void main(String[] args) throws IOException {
		//context만들기
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		//Environment객체 얻어오기
		ConfigurableEnvironment env = ctx.getEnvironment();
		//propertySource라는 객체를 얻어오기
		MutablePropertySources propertySources = env.getPropertySources();//외부파일로 부터 property얻어옴
		
		//외부파일에 있는 설정된 값을 propertySource에 사용가능
		propertySources.addLast(new ResourcePropertySource("classpath:env.properties")); 
		
		//env.properties 파일에서 가져온 정보를 출력
		System.out.println(env.getProperty("env.id"));
		System.out.println(env.getProperty("env.pwd"));
		
		GenericXmlApplicationContext gCtx = (GenericXmlApplicationContext)ctx;
		gCtx.load("classpath:env.xml");
		gCtx.refresh();
		
		EnvironmentEx envEx = gCtx.getBean("env", EnvironmentEx.class);
		System.out.println("env id: " + envEx.getId());
		System.out.println("env pwd: " + envEx.getPwd());
		
		gCtx.close();
		ctx.close();
		
	}//main
}

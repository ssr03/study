package com.test.diEx04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainMyBatis {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:myBatis.xml");
		MyBatisService service = ctx.getBean("service", MyBatisService.class);
		
		service.myBatisTest();
		
		ctx.close();
	}

}

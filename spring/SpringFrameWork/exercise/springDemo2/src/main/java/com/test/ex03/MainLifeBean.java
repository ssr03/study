package com.test.ex03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainLifeBean {

	public static void main(String[] args) {
	
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:lifeBean.xml");
		LifeBean bean = ctx.getBean("lifeBean", LifeBeanImpl.class);
		
		bean.lifeMethod();
		
		ctx.close();
	}

}

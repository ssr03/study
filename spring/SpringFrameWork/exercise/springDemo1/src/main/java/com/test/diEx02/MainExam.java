package com.test.diEx02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExam {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:examDao.xml");
		ExamDao examDao = ctx.getBean("examDao", ExamDao.class);
		
		examDao.outputMsg();
	}
	
}

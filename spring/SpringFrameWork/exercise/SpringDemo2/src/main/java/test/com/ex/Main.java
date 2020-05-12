package test.com.ex;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:baseBall.xml");
		
		//초기화
		ctx.refresh();
		
		//종료
		ctx.close();
	}
}

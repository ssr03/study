package com.test.ex06;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.test.ex05.ExternalFileEx;

@Configuration
public class ExtConfig {

	@Value("${env.id}")
	private String envId;
	@Value("${env.pwd}")
	private String envPwd;
	@Value("${ext.id}")
	private String extId;
	@Value("${ext.pwd}")
	private String extPwd;
	
	//컨테이너에서 호출해서 사용하는 메소드
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		//프로퍼티 파일의 위치값을 저장하는 객체
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("env.properties");
		locations[1] = new ClassPathResource("externals.properties");
		configurer.setLocations(locations);
		
		return configurer;
	}//properties()
	
	@Bean
	public ExternalFileEx extConf() {
		ExternalFileEx ext = new ExternalFileEx();
		ext.setId(envId);
		ext.setPwd(envPwd);
		ext.setExtId(extId);
		ext.setExtPwd(extPwd);
		
		return ext;
		
	}//extConfig()
}

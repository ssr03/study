package com.test.ex07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("service")
public class SvcConfig {
	@Bean
	public ProfileEx profileEx() {
		ProfileEx prof = new ProfileEx();
		prof.setIp("210.177.226.15");
		prof.setPort("80");
		return prof;
	}
}

package com.test.diEx09;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:baseBall4.xml")
public class ConfigApp {

	@Bean
	public Player player1() {
		ArrayList<String> position = new ArrayList<String>();
		position.add("4번 타자");
		position.add("3루수");
		
		Player player = new Player("박병호", 28, position);
		player.setHeight(187);
		player.setWeight(80);
		
		System.out.println(player.getName());
		return player;
	}
	
}

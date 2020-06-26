package com.gdut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan("com.gdut.mapper")
public class TurnTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurnTableApplication.class, args);
	}

}

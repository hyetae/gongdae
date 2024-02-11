package com.jy.gongdae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

@SpringBootApplication
public class GongdaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GongdaeApplication.class, args);
	}

}

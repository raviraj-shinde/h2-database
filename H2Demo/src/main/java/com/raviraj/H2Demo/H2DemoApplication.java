package com.raviraj.H2Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class H2DemoApplication {

	public static void main(String[] args) {
	 	ConfigurableApplicationContext context = SpringApplication.run(H2DemoApplication.class, args);
		System.out.println("context :::::: " + context);

		Object dataSource = context.getBean("dataSource");
		System.out.println("dataSource Bean ::::: " + dataSource );

	}

}

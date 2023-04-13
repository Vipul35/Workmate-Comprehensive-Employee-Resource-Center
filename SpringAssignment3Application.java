package com.example.SpringAssignment3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages="com.example")
public class SpringAssignment3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAssignment3Application.class, args);
	}

}

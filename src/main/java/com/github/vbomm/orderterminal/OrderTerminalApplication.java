package com.github.vbomm.orderterminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.vbomm.orderterminal")
@EnableAutoConfiguration
public class OrderTerminalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderTerminalApplication.class, args);
	}

}

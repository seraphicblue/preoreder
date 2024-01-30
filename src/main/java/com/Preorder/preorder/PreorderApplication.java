package com.preorder.preorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PreorderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreorderApplication.class, args);
	}

}

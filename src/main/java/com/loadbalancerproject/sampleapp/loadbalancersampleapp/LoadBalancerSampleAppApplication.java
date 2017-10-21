package com.loadbalancerproject.sampleapp.loadbalancersampleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class LoadBalancerSampleAppApplication {

	@RequestMapping("/")
	@ResponseBody
	public String main() {
		return "Hello";
	}


	public static void main(String[] args) {
		SpringApplication.run(LoadBalancerSampleAppApplication.class, args);
	}
}

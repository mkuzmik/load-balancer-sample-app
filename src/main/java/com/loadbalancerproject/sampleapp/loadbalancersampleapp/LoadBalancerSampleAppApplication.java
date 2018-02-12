package com.loadbalancerproject.sampleapp.loadbalancersampleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class LoadBalancerSampleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadBalancerSampleAppApplication.class, args);
	}
}

package com.loadbalancerproject.sampleapp.loadbalancersampleapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {PersistenceConfig.class})
@ComponentScan(basePackages = {"com.loadbalancerproject.sampleapp.loadbalancersampleapp"})
public class AppConfig {
}

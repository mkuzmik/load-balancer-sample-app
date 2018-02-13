package com.loadbalancerproject.sampleapp.loadbalancersampleapp.config;


import com.loadbalancerproject.loadbalancer.*;
import com.loadbalancerproject.loadbalancer.config.DatabaseConfiguration;
import com.loadbalancerproject.loadbalancer.config.LoadBalancerDataSource;
import com.loadbalancerproject.loadbalancer.loadbalancing.EqualDistributionStrategy;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.StudentDAO;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.StudentRepository;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    //Simple DataSource is created because of Spring restrictions
    @Bean
    public DataSource primaryDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://192.168.99.100:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public DatabaseConfiguration prepareDatabaseConfiguration(){
        DatabaseConfiguration config = new DatabaseConfiguration.ConfigurationBuilder()
                .dataSources(createLoadBalancerDataSourceList())
                .strategy(new EqualDistributionStrategy())
                .build();
        return config;
    }

    @Bean
    public LoadBalancer getLoadBalancer(){

        LoadBalancer loadBalancer = new LoadBalancerImpl(prepareDatabaseConfiguration());
        return loadBalancer;
    }

    @Bean
    public StudentDAO getStudentDAO(LoadBalancer loadBalancer) {
        return new StudentRepository(loadBalancer);
    }


    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private List<LoadBalancerDataSource> createLoadBalancerDataSourceList() {
        List<LoadBalancerDataSource> list = new LinkedList<>();
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5432/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5433/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5434/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5435/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5436/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5437/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5438/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5439/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5440/postgres")
                .build());
        list.add(new LoadBalancerDataSource.DataSourceBuilder()
                .url("jdbc:postgresql://192.168.99.100:5441/postgres")
                .build());
        return list;
    }
}

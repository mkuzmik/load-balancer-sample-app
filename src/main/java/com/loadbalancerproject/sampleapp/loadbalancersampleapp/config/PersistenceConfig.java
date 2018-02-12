package com.loadbalancerproject.sampleapp.loadbalancersampleapp.config;


import com.loadbalancerproject.loadbalancer.*;
import com.loadbalancerproject.loadbalancer.config.DBConfig;
import com.loadbalancerproject.loadbalancer.exception.DataSourceParametersException;
import com.loadbalancerproject.loadbalancer.factory.DataSourceFactory;
import com.loadbalancerproject.loadbalancer.prototype.DataSourcePrototype;
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


    private DataSourcePrototype prototype = new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5432/postgres")
            .user("postgres")
            .pass("")
            .build();

    private DataSourcePrototype prototype1= new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5433/postgres")
            .user("postgres")
            .pass("")
            .build();

    private DataSourcePrototype prototype2= new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5434/postgres")
            .user("postgres")
            .pass("")
            .build();

    private DataSourcePrototype prototype3= new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5435/postgres")
            .user("postgres")
            .pass("")
            .build();
    private DataSourcePrototype prototype4 = new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5436/postgres")
            .user("postgres")
            .pass("")
            .build();
    private DataSourcePrototype prototype5 = new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5437/postgres")
            .user("postgres")
            .pass("")
            .build();
    private DataSourcePrototype prototype6 = new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5438/postgres")
            .user("postgres")
            .pass("")
            .build();
    private DataSourcePrototype prototype7 = new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5439/postgres")
            .user("postgres")
            .pass("")
            .build();
    private DataSourcePrototype prototype8 = new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5440/postgres")
            .user("postgres")
            .pass("")
            .build();
    private DataSourcePrototype prototype9 = new DataSourcePrototype.DataSourceBuilder()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://192.168.99.100:5441/postgres")
            .user("postgres")
            .pass("")
            .build();

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
    public DBConfig dataSourcesList(){
        List<DataSource> list = new LinkedList<>();
        try {
            list.add(DataSourceFactory.createDataSource(prototype));
            list.add(DataSourceFactory.createDataSource(prototype1));
            list.add(DataSourceFactory.createDataSource(prototype2));
            list.add(DataSourceFactory.createDataSource(prototype3));
            list.add(DataSourceFactory.createDataSource(prototype4));
            list.add(DataSourceFactory.createDataSource(prototype4));
            list.add(DataSourceFactory.createDataSource(prototype5));
            list.add(DataSourceFactory.createDataSource(prototype6));
            list.add(DataSourceFactory.createDataSource(prototype7));
            list.add(DataSourceFactory.createDataSource(prototype8));
            list.add(DataSourceFactory.createDataSource(prototype9));
        }
        catch (DataSourceParametersException e){
            e.printStackTrace();
        }
        DBConfig config = new DBConfig.DBConfigBuilder()
                                   .dataSourceList(list)
                                    .build();
        return config;
    }

    @Bean
    public LoadBalancer getLoadBalancer(){

        LoadBalancer loadBalancer = new LoadBalancerImpl(dataSourcesList());
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


}

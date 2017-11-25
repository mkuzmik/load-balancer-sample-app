package com.loadbalancerproject.sampleapp.loadbalancersampleapp;

import com.loadbalancerproject.sampleapp.loadbalancersampleapp.config.AppConfig;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.config.PersistenceConfig;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.StudentRepository;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.util.StudentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class StudentRepositoryIntegrationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    StudentRepository studentRepository;

    StudentGenerator studentGenerator;

    @BeforeClass
    public void setUp() throws Exception {
        studentGenerator = new StudentGenerator();
    }

    @Test
    public void should() throws Exception {
        IntStream.range(0,500).forEach(
                (num) -> studentRepository.save(studentGenerator.nextStudent()));
    }
}

package com.loadbalancerproject.sampleapp.loadbalancersampleapp;

import com.loadbalancerproject.sampleapp.loadbalancersampleapp.config.AppConfig;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.config.PersistenceConfig;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.StudentRepository;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.util.StudentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
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
        clearDb();
    }

    @Test
    public void shouldInsertThenGetManyRecords() throws Exception {

        int numberOfInserts = 500;

        Collection<Student> expectedStudents = studentGenerator.generateStudents(numberOfInserts);

        saveStudentsAsynchronously(expectedStudents);

        assertThatRepositoryContains(expectedStudents);
    }

    @Test
    public void manyGetAllRequestsShould() throws Exception {

        int numberOfInserts = 5000;
        int numberOfGetAllRequests = 500;

        Collection<Student> expectedStudents = studentGenerator.generateStudents(numberOfInserts);

        saveStudentsAsynchronously(expectedStudents);

        long diff = System.currentTimeMillis();

        IntStream.range(0, numberOfGetAllRequests).parallel()
                .forEach(i ->
                    assertThatRepositoryContains(expectedStudents)
                );

        System.out.println("Exec time: " + (System.currentTimeMillis() - diff));
    }

    private void saveStudentsAsynchronously(Collection<Student> students) {
        students.parallelStream()
                .forEach(student -> studentRepository.save(student));
    }

    private void assertThatRepositoryContains(Collection<Student> students) {
        Collection<Student> actualStudents = studentRepository.getAll();

        assertThat(actualStudents).containsOnly(students.stream().toArray(Student[]::new));
    }

    private void clearDb() {
        studentRepository.getAll().forEach(student -> studentRepository.delete(student));
    }
}

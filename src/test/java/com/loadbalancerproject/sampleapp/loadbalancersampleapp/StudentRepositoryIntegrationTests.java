package com.loadbalancerproject.sampleapp.loadbalancersampleapp;

import com.loadbalancerproject.sampleapp.loadbalancersampleapp.config.AppConfig;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.config.PersistenceConfig;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.StudentDAO;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.StudentRepository;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.util.StudentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentRepositoryIntegrationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    StudentDAO studentRepository;

    StudentGenerator studentGenerator;

    @BeforeClass
    public void setUp() throws Exception {
        studentGenerator = new StudentGenerator();
        clearDb();
    }

    @Test
    public void shouldInsertThenGetManyRecords() throws Exception {

        // given
        int numberOfInserts = 100;
        Collection<Student> expectedStudents = studentGenerator.generateStudents(numberOfInserts);

        // when
        saveStudentsAsynchronously(expectedStudents);

        // then
        assertThatRepositoryContains(expectedStudents);
    }

    @Test
    public void shouldGetSameResultWhenManyGetAllRequests() throws Exception {

        // given
        int numberOfInserts = 100;
        int numberOfGets = 100;
        Collection<Student> expectedStudents = studentGenerator.generateStudents(numberOfInserts);

        // when
        saveStudentsAsynchronously(expectedStudents);

        // then
        IntStream.range(0, numberOfGets).parallel().forEach(x ->
                assertThatRepositoryContains(expectedStudents));
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

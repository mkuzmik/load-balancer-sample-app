package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student;

import com.loadbalancerproject.loadbalancer.LoadBalancer;
import com.loadbalancerproject.loadbalancer.ReadOnlyQueryCreator;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import java.util.List;

@Repository

public class StudentRepository {


    private LoadBalancer loadBalancer;


    @Autowired
    public StudentRepository(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public void save(Student student) {
        loadBalancer.save(student,Student.class);
    }

    public void delete(Student student) {
        loadBalancer.delete(student, Student.class);
    }

    public List<Student> getAll() {
        ReadOnlyQueryCreator readonlyExecutor = loadBalancer.getReadonlyExecutor();
        List result = readonlyExecutor.createReadonlyQuery("from Student").getResultList();

        readonlyExecutor.close();

        return (List<Student>) result;
    }
}

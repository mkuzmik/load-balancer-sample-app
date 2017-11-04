package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student;

import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Student student) {
        entityManager.persist(student);
    }

    public void delete(Student student) {
        Student merged = entityManager.merge(student);
        entityManager.remove(merged);
    }

    public List<Student> getAll() {
        return (List<Student>) entityManager.createQuery("from Student").getResultList();
    }
}

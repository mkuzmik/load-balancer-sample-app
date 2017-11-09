package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student;

import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import java.util.List;

@Repository
public class StudentRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private void setup() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    private void commitAndClose() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void save(Student student) {
        setup();
        entityManager.persist(student);
        commitAndClose();
    }

    public void delete(Student student) {
        setup();
        Student merged = entityManager.merge(student);
        entityManager.remove(merged);
        commitAndClose();
    }

    public List<Student> getAll() {
        entityManager = entityManagerFactory.createEntityManager();
        return (List<Student>) entityManager.createQuery("from Student").getResultList();
    }
}

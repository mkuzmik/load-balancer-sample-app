package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

    public void save(Student student) {
        localSessionFactoryBean.getObject().getCurrentSession().save(student);
    }
//
//    public void deleteByEmail(String email) {
//
//    }
//
//    public List<Student> getAll() {
//
//    }
}

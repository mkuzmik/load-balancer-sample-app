package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student;

import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> getAll();
    void save(Student student);
    void update(Student student);
    void delete(Student student);
}

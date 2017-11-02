package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model;

import javax.persistence.*;

@Entity
@Table
public class Student {

    @Id
    String email;

    String name;

    String surname;

    public Student(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public Student() {
    }

    public static Student from(StudentInput studentInput) {
        return new Student(studentInput.getEmail(),
                            studentInput.getName(),
                            studentInput.getSurname());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

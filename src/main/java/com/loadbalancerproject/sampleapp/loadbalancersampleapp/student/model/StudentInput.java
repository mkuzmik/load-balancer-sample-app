package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model;

import org.hibernate.validator.constraints.NotBlank;

public class StudentInput {

    @NotBlank
    String email;

    @NotBlank
    String name;

    @NotBlank
    String surname;

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

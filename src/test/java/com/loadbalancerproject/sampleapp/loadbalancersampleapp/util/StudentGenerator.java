package com.loadbalancerproject.sampleapp.loadbalancersampleapp.util;

import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;

import java.util.Random;

public class StudentGenerator {

    private static String[] NAMES = new String[] {
            "Janusz", "Andrzej", "Kamil", "Norbert", "Mikołaj", "Mariusz", "Nikodem"
    };

    private static String[] SURNAMES = new String[] {
            "Janusz", "Andrzej", "Kamil", "Norbert", "Mikołaj", "Mariusz", "Nikodem"
    };

    private static String[] EMAIL_SUFFIX = new String[] {
            "wp.pl", "gmail.com", "mail.dot", "onet.pl", "interia.pl", "whatever.org"
    };

    private Random random = new Random();

    public Student nextStudent() {
        String name = randomName();
        String surname = randomSurname();
        String email = randomEmail(name, surname);

        return new Student(email, name, surname);
    }

    private String randomName() {
        return NAMES[random.nextInt(NAMES.length)];
    }

    private String randomSurname() {
        return SURNAMES[random.nextInt(SURNAMES.length)];
    }

    private String randomEmail(String name, String surname) {

        String namePart = name.substring(0, random.nextInt(name.length() - 1)).toLowerCase();
        String surnamePart = name.substring(0, random.nextInt(surname.length() - 1)).toLowerCase();
        String numPart = Integer.toString(random.nextInt(100));
        String suffix = EMAIL_SUFFIX[random.nextInt(EMAIL_SUFFIX.length)];

        return namePart + surnamePart + numPart + "@" + suffix;
    }
}

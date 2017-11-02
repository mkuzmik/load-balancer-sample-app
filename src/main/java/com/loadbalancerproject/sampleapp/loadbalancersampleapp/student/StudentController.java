package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student;

import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.StudentInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void create(@RequestBody @Valid StudentInput studentInput) {
        studentRepository.save(Student.from(studentInput));
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getAll() {
        return studentRepository.getAll();
    }
}

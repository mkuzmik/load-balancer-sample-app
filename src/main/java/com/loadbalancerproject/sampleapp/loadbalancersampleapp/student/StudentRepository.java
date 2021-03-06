package com.loadbalancerproject.sampleapp.loadbalancersampleapp.student;

import com.loadbalancerproject.loadbalancer.LoadBalancer;
import com.loadbalancerproject.loadbalancer.readonlyqueryexecutor.SelectQuery;
import com.loadbalancerproject.sampleapp.loadbalancersampleapp.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@SuppressWarnings("unchecked")
public class StudentRepository implements StudentDAO {

    private LoadBalancer loadBalancer;

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

        SelectQuery selectQuery = loadBalancer.getSelectQuery();

        CriteriaQuery<Student> criteriaQuery = selectQuery.getCriteriaQuery(Student.class);

        Root<Student> from = criteriaQuery.from(Student.class);

        List<Student> result =  selectQuery.getTypedQuery(criteriaQuery.select(from)).getResultList();

        selectQuery.close();

        return result;
    }

    public void update(Student student) {
        loadBalancer.update(student, Student.class);
    }
}

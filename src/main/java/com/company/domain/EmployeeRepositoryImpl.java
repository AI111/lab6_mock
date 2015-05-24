package com.company.domain;

import com.company.application.ApplicationService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sasha on 5/14/15.
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    static final Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);

    @Autowired
    SessionFactory sessionFactory;

    public EmployeeRepositoryImpl() {
    }


    public EmployeeRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    @Transactional
    public void createEmployee(Employee employee) {
        logger.trace("createEmployee begin with Employ = {}",employee);
        sessionFactory.getCurrentSession().save(employee);
        logger.trace("createEmployee end");

    }
    @Transactional
    public void createEmployee(List<Employee> employee) {
        logger.trace("createEmployee begin with Employs = {}",employee);
        for(Employee e:employee)sessionFactory.getCurrentSession().save(e);
        logger.trace("createEmployee end");
    }
    @Transactional
    public Employee getEmployee(long id) {
        logger.trace("getEmployee begin with id = {}",id);
        logger.trace("getEmployee end");
        return (Employee)  sessionFactory.getCurrentSession().get(Employee.class,id);

    }

    @Transactional
    public void editEmployee(Employee updatedEntity) {
        logger.trace("editEmployee() begin wihh updatedEntity ={}",updatedEntity);
        sessionFactory.getCurrentSession().update(updatedEntity);
        logger.trace("editEmployee() end");
    }

    @Transactional
    public void removeEmployee(Employee updatedEntity) {
        logger.trace("getAllEmployees() begin with updatedEntity = {}",updatedEntity);
        sessionFactory.getCurrentSession().delete(updatedEntity);
        logger.trace("getAllEmployees() end");
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        logger.trace("getAllEmployees() begin ");
        logger.trace("getAllEmployees() end");
        return sessionFactory.getCurrentSession().createQuery( "FROM Employee" ).list();
    }
}

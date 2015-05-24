package com.company.domain;

import java.util.Collections;
import java.util.List;

/**
 * Created by sasha on 4/28/15.
 */
public interface EmployeeRepository {
    void createEmployee(Employee employee);
    void createEmployee(List<Employee> employee);
    Employee getEmployee(long id);
    void editEmployee(Employee updatedEntity);
    void removeEmployee(Employee updatedEntity);
    List<Employee> getAllEmployees();


}

package com.angie.basic.services;

import com.angie.basic.domain.Employee;
import com.angie.basic.domain.InvalidEmployeeException;
import com.angie.basic.domain.NotFoundEmployeeException;

public interface EmployeeService {

    Employee create(Employee employee) throws InvalidEmployeeException;

    Employee update(Employee employee) throws NotFoundEmployeeException, InvalidEmployeeException;

    Employee delete(Long id) throws NotFoundEmployeeException;

    Employee find(Long id) throws NotFoundEmployeeException;

}

package com.angie.basic.repositories;

import com.angie.basic.domain.Employee;
import com.angie.basic.domain.InvalidEmployeeException;
import com.angie.basic.domain.NotFoundEmployeeException;

public interface EmployeeRepository {

    Employee create(Employee employee) throws InvalidEmployeeException;

    Employee update(Employee employee) throws InvalidEmployeeException;

    Employee delete(Employee employee) throws NotFoundEmployeeException;

    Employee find(Long id) throws NotFoundEmployeeException;

}

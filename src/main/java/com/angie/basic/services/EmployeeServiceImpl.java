package com.angie.basic.services;

import com.angie.basic.domain.Employee;
import com.angie.basic.domain.InvalidEmployeeException;
import com.angie.basic.domain.NotFoundEmployeeException;
import com.angie.basic.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    final
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) throws InvalidEmployeeException {
        try {
            return employeeRepository.create(employee);
        } catch (Exception e) {
            throw new InvalidEmployeeException();
        }
    }

    @Override
    public Employee update(Employee employee) throws NotFoundEmployeeException, InvalidEmployeeException {
        employeeRepository.find(employee.getId());
        return employeeRepository.update(employee);
    }

    @Override
    public Employee delete(Long id) throws NotFoundEmployeeException {
        Employee employee = employeeRepository.find(id);
        return employeeRepository.delete(employee);
    }

    @Override
    public Employee find(Long id) throws NotFoundEmployeeException {
        return employeeRepository.find(id);
    }
}

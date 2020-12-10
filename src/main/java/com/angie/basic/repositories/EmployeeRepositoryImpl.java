package com.angie.basic.repositories;

import com.angie.basic.domain.Employee;
import com.angie.basic.domain.InvalidEmployeeException;
import com.angie.basic.domain.NotFoundEmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JpaEmployeeRepository jpaEmployeeRepository;

    @Autowired
    public EmployeeRepositoryImpl(JpaEmployeeRepository jpaEmployeeRepository) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }

    @Override
    public Employee create(Employee employee) throws InvalidEmployeeException {
        try {
            return toDomain(jpaEmployeeRepository.save(toEntity(employee)));
        }catch (IllegalArgumentException e) {
            throw new InvalidEmployeeException();
        }
    }

    @Override
    public Employee update(Employee employee) throws InvalidEmployeeException {
        try {
            return toDomain(jpaEmployeeRepository.save(toEntity(employee)));
        }catch (IllegalArgumentException e) {
            throw new InvalidEmployeeException();
        }
    }

    @Override
    public Employee delete(Employee employee) throws NotFoundEmployeeException {
        try {
            jpaEmployeeRepository.delete(toEntity(employee));
            return employee;
        } catch (IllegalArgumentException e) {
            throw new NotFoundEmployeeException();
        }
    }

    @Override
    public Employee find(Long id) throws NotFoundEmployeeException {
        Optional<EmployeeEntity> employeeEntity = jpaEmployeeRepository.findById(id);
        if (employeeEntity.isPresent()) {
            return toDomain(employeeEntity.get());
        }
        throw new NotFoundEmployeeException();
    }

    private EmployeeEntity toEntity(Employee employee) {
        return new EmployeeEntity(employee.getId(), employee.getName(), employee.getLastName(),
                employee.getAdmissionDate(), employee.getBaseSalary(), employee.getFinishDate());
    }

    private Employee toDomain(EmployeeEntity employeeEntity) {
        return new Employee(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getLastName(),
                employeeEntity.getAdmissionDate(), employeeEntity.getBaseSalary(), employeeEntity.getFinishDate());
    }
}

package com.angie.basic.repositories;

import org.springframework.data.repository.CrudRepository;

public interface JpaEmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
}

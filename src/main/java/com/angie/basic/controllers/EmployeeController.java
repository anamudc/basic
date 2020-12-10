package com.angie.basic.controllers;

import com.angie.basic.domain.Employee;
import com.angie.basic.domain.InvalidEmployeeException;
import com.angie.basic.domain.NotFoundEmployeeException;
import com.angie.basic.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/employees")
    public EmployeeRequest create (@RequestBody EmployeeRequest employeeRequest) throws InvalidEmployeeException {
        return toRequest(employeeService.create(toDomain(employeeRequest)));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/employees/{id}")
    public EmployeeRequest update (@RequestBody EmployeeRequest employeeRequest, @PathVariable Long id) throws NotFoundEmployeeException, InvalidEmployeeException {
        employeeRequest.setId(id);
        return toRequest(employeeService.update(toDomain(employeeRequest)));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/employees/{id}")
    public EmployeeRequest delete (@PathVariable Long id) throws NotFoundEmployeeException {
        return toRequest(employeeService.delete(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/employees/{id}")
    public EmployeeRequest get (@PathVariable Long id) throws NotFoundEmployeeException {
        return toRequest(employeeService.find(id));
    }

    @ExceptionHandler ({Exception.class})
    public ResponseEntity<String> handlerError(Exception e){
        return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private EmployeeRequest toRequest(Employee employee) {
        return new EmployeeRequest(employee.getId(), employee.getName(), employee.getLastName(),
                employee.getAdmissionDate(), employee.getBaseSalary(), employee.getFinishDate());
    }

    private Employee toDomain(EmployeeRequest employeeRequest) {
        return new Employee(employeeRequest.getId(), employeeRequest.getName(), employeeRequest.getLastName(),
                employeeRequest.getAdmissionDate(), employeeRequest.getBaseSalary(), employeeRequest.getFinishDate());
    }
}

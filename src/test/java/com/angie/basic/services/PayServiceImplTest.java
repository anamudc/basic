package com.angie.basic.services;

import com.angie.basic.domain.Employee;
import com.angie.basic.domain.NotFoundEmployeeException;
import com.angie.basic.domain.PayInformation;
import com.angie.basic.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

class PayServiceImplTest {

    PayServiceImpl payServiceImp = new PayServiceImpl(null);
    EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
    PayService payService = new PayServiceImpl(employeeRepository);

    @Test
    void getPayByMonth() throws NotFoundEmployeeException {
        Long employeeId = 1L;
        Mockito.when(employeeRepository.find(Mockito.any())).thenReturn(getEmployeePedro());
        Assertions.assertEquals(600000L, payService.getPayByMonth(new PayInformation(employeeId,9,2018)));
    }

    @Test
    void getDaysToPay() throws NotFoundEmployeeException {
        Long employeeId = 1L;
        Assertions.assertEquals(15L, payServiceImp.getDaysToPay(new PayInformation(employeeId, 9, 2018), getEmployeePedro()));
        Assertions.assertEquals(30L, payServiceImp.getDaysToPay(new PayInformation(employeeId, 10, 2018), getEmployeePedro()));
    }


    Employee getEmployeePedro () {
        LocalDate admissionDate = LocalDate.of(2018, 9, 16);
        return new Employee(1L, "Pedro", "Pérez", admissionDate, 1200000L, null);
    }

    Employee getEmployeeJuan () {
        LocalDate admissionDate = LocalDate.of(2018, 7, 13);
        LocalDate finishDate = LocalDate.of(2019, 11, 25);
        return new Employee(2L, "Juan", "González", admissionDate, 1000000L, finishDate);
    }

    Employee getEmployeePablo () {
        LocalDate admissionDate = LocalDate.of(2019, 4, 4);
        LocalDate finishDate = LocalDate.of(2019, 4, 23);
        return new Employee(3L, "Pablo", "Robles", admissionDate, 2100000L, finishDate);
    }

}
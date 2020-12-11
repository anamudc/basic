package com.angie.basic.services;

import com.angie.basic.domain.Employee;
import com.angie.basic.domain.NotFoundEmployeeException;
import com.angie.basic.domain.PayInformation;
import com.angie.basic.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
public class PayServiceImpl implements PayService {

    final EmployeeRepository employeeRepository;

    @Autowired
    public PayServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long getPayByMonth(PayInformation payInformation) throws NotFoundEmployeeException {

        Employee emp = employeeRepository.find(payInformation.getEmployeeId());
        Long days = getDaysToPay(payInformation, emp);

        return emp.getBaseSalary() * days / 30L;
    }

    public Long getDaysToPay(PayInformation payInformation, Employee emp) throws NotFoundEmployeeException {
        LocalDate initDate = LocalDate.of(payInformation.getYear(), payInformation.getMonth(), 1);
        int lastDayOfMonth = initDate.lengthOfMonth();
        LocalDate finishDate = LocalDate.of(payInformation.getYear(), payInformation.getMonth(), lastDayOfMonth);

        if (emp.getAdmissionDate().isAfter(initDate)) {
            initDate = emp.getAdmissionDate();
        }
        if (!Objects.isNull(emp.getFinishDate()) && emp.getFinishDate().isBefore(finishDate)) {
            finishDate = emp.getFinishDate();
        }
        if (lastDayOfMonth > 30){
            finishDate = finishDate.minusDays(1);
        }
        if (lastDayOfMonth < 30){
            finishDate = finishDate.plusDays(1);
        }

        long days = DAYS.between(initDate, finishDate) + 1L;
        if (days < 0L) {
            return 0L;
        }
        if (days > 30) {
            days = 30L;
        }
        return days;
    }
}

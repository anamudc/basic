package com.angie.basic.services;

import com.angie.basic.domain.NotFoundEmployeeException;
import com.angie.basic.domain.PayInformation;

import java.time.LocalDate;

public interface PayService {

    Long getPayByMonth(PayInformation payInformation) throws NotFoundEmployeeException;


}

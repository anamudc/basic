package com.angie.basic.domain;

import com.angie.basic.repositories.EmployeeEntity;

public class NotFoundEmployeeException extends Exception {

    @Override
    public String getMessage() {
        return "No fue posible encontrar el empleado";
    }
}

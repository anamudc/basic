package com.angie.basic.domain;

public class InvalidEmployeeException extends Exception {

    @Override
    public String getMessage() {
        return "El empleado no tiene la información completa";
    }
}

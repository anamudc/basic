package com.angie.basic.controllers;

public class PayResponse {

    Long amountToPay;

    public PayResponse(Long amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Long getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Long amountToPay) {
        this.amountToPay = amountToPay;
    }
}

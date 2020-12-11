package com.angie.basic.controllers;

import com.angie.basic.domain.InvalidEmployeeException;
import com.angie.basic.domain.NotFoundEmployeeException;
import com.angie.basic.domain.PayInformation;
import com.angie.basic.services.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PayController {

    final PayService payService;

    @Autowired
    public PayController(PayService payService) {
        this.payService = payService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/employees/pay")
    public PayResponse getPayByMonth (@RequestBody PayRequest payRequest) throws NotFoundEmployeeException {
        return new PayResponse(payService.getPayByMonth(toDomain(payRequest)));
    }

    private PayInformation toDomain (PayRequest payRequest) {
        return new PayInformation(payRequest.employeeId, payRequest.month, payRequest.year);
    }


    @ExceptionHandler ({Exception.class})
    public ResponseEntity<String> handlerError(Exception e){
        if (NotFoundEmployeeException.class.equals(e.getClass())
                || InvalidEmployeeException.class.equals(e.getClass())){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

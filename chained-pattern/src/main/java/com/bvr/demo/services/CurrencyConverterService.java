package com.bvr.demo.services;


import com.bvr.demo.models.PaymentContext;
import com.bvr.demo.services.FeeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CurrencyConverterService {
    
    @Autowired
    private FeeCalculatorService feeCalculatorService;

    public PaymentContext convertCurrency(PaymentContext context) {
        if("USD".equalsIgnoreCase(context.getCurrency())){

            context.setAmount(context.getAmount() * 82); // 1 USD = 82 INR
            context.setCurrency("EUR");
            context.getAuditlogs().add("Service B - Converted USD to EUR at 82 rate");

         }
         return feeCalculatorService.calculate(context);
    }
}


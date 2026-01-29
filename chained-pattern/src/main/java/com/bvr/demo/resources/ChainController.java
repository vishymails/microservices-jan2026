package com.bvr.demo.resources;

import com.bvr.demo.services.PaymentGatewayService;
import com.bvr.demo.models.PaymentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/process")
public class ChainController {

	public ChainController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired 
	private PaymentGatewayService gatewayService;

	@PostMapping("/payment")
	public PaymentContext process(@RequestParam Double amount, @RequestParam String currency) {
		return gatewayService.initiate(amount, currency);
	}

}


//http://localhost:8080/api/v1/process/payment?amount=1000&currency=USD
package com.game.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.GameService.PaymentService;
import com.game.model.payment.PayUResponse;
import com.game.model.payment.PaymentAudit;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/createpayment")
	public Map<String ,Object>  createPayment(@Valid @RequestBody PaymentAudit paymentAudit) {
		
		Map<String ,Object> outputMap = paymentService.createPayment(paymentAudit);
		return outputMap;
		
	}
	
	@PostMapping("/getpaymentbyid")
	public Map<String ,Object>  getPayment(@RequestBody String transId ) {
		
		return paymentService.getPaymentById(transId);
		
		
	}
	
	@PostMapping("/updatepaymentfailure")
	public Map<String ,Object>  updatePaymentFailure(@Valid @RequestBody PayUResponse response) {		
		Map<String ,Object> outputMap = paymentService.handlePaymentFailure(response);
		return outputMap;
		
	}
	
	@PostMapping("/updatepaymentsuccess")
	public Map<String ,Object>  updatePaymentSuccess(@Valid @RequestBody PayUResponse response) {		
		Map<String ,Object> outputMap = paymentService.handlePaymentSuccess(response);
		return outputMap;		
	}
	
}

	
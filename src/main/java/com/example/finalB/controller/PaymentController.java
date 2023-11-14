package com.example.finalB.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalB.domain.Member;
import com.example.finalB.service.MileageService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@RestController
	public class PaymentController {

	    @Value("${iamport.key}")
	    private String restApiKey;
	    @Value("${iamport.secret}")
	    private String restApiSecret;

	    private IamportClient iamportClient;
	    
	    @Autowired
	    private MileageService mileageService;
	   
	    @PostConstruct
	    public void init() {
	        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
	    }

	    @PostMapping("/verifyIamport/{imp_uid}")
	    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid) throws IamportResponseException, IOException {
	    	IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
	    	
	        return payment;
	    }
	}


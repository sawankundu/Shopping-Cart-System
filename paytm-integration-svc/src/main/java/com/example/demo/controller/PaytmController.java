package com.example.demo.controller;

import java.net.URL;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.AppConfig;
import com.paytm.pg.merchant.PaytmChecksum;

@RestController
@RequestMapping("/payment")
public class PaytmController {
	
	Random random = new Random();
	
	@PostMapping("/start")
	public Map<String,Object> startPayment(@RequestBody Map<String,Object> data ){
		
		int orderId = random.nextInt(179739719);
		
		JSONObject paytmParams = new JSONObject();
		
		
		//body information
		JSONObject body = new JSONObject();
		body.put("requestType", "Payment");
		body.put("mid", AppConfig.MID);
		body.put("websiteName", AppConfig.WEBSITE);
		body.put("orderId", orderId);
		body.put("callbackUrl", "https://localhost:8081/payment-success");
		
		JSONObject txnAmount = new JSONObject();
		txnAmount.put("value", data.get("amount"));
		txnAmount.put("currency", "INR");
		
		JSONObject userInfo = new JSONObject();
		userInfo.put("custId", "CUST_001");
		body.put("txnAmount", txnAmount);
		body.put("userInfo", userInfo);
		
		String responseDate="";
		ResponseEntity<Map> responseEntity = null;
		
		try {
			String checkSum = PaytmChecksum.generateSignature(body.toString(), AppConfig.MKEY);
			
			JSONObject head = new JSONObject();
			head.put("signature", checkSum);

			paytmParams.put("body", body);
			paytmParams.put("head", head);
			
			String postData = paytmParams.toString();
			
			/* for Staging */
			URL url = new URL("https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?mid="+AppConfig.MID+"&orderId="+orderId+"");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Map<String, Object>> httpentity= new HttpEntity<Map<String,Object>>(paytmParams.toMap(),headers);
			
			//calling api
			RestTemplate restTemplate = new RestTemplate();
			responseEntity= restTemplate.postForEntity(url.toString(), httpentity, Map.class);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Map body1= responseEntity.getBody();
		body1.put("orderId", orderId);
		body.put("amount",txnAmount.get("value") );
		return body1; 
	}
}

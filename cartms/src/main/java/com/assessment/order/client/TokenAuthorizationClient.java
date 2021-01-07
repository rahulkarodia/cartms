package com.assessment.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "user-authentication", fallback = TokenAuthorizationFallback.class)
public interface TokenAuthorizationClient {
	@RequestMapping(method = RequestMethod.GET, value = "/validateToken")
	boolean validateToken(@RequestHeader("Authorization") String token);
	
}



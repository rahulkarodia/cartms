package com.assessment.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assessment.order.model.Product;


@FeignClient(name = "products", fallback = ProductValidationFallback.class)
public interface ProductValidationClient {
	@RequestMapping(method = RequestMethod.GET, path="/validateProduct/{productId}")
	ResponseEntity<Product> validateProduct(@PathVariable int productId);
	@RequestMapping(method = RequestMethod.GET, path ="/default")
	String test();
}



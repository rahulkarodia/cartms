package com.assessment.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.order.model.Cart;
import com.assessment.order.service.AuditService;
import com.assessment.order.service.CartService;

@RestController
@RefreshScope
public class OrderController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	AuditService auditService;
	
	
	@GetMapping(path = "/default")
	public String defaultMessage() {
		return "Welcome to Cart service";
	}

	@GetMapping(path = "/getCart", produces = "application/json")
	public ResponseEntity<List<Cart>> getAllCarts() {
		return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/addCart/{productId}", produces = "application/json")
	public ResponseEntity<String> addcart(@PathVariable int productId) {
		return new ResponseEntity<>(cartService.addProduct(productId), HttpStatus.OK);
	}
	
	@GetMapping(path = "/deleteCart/{productId}", produces = "application/json")
	public ResponseEntity<String> deleteCart(@PathVariable int productId) {
		return new ResponseEntity<>(cartService.deleteProduct(productId), HttpStatus.OK);
	}
	
	@GetMapping(path = "/clearCart", produces = "application/json")
	public ResponseEntity<String> clearCart() {
		return new ResponseEntity<>(cartService.clearCart(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/checkoutCart", produces = "application/json")
	public ResponseEntity<String> checkoutCart() {
		List<Cart> cart = cartService.getAllCarts();
		String msg = cartService.clearCart();
		auditService.publishAuditEvent( new ResponseEntity<List<Cart>>(cart, HttpStatus.OK) );
		
		return new ResponseEntity<>( msg, HttpStatus.OK );
	}

	@GetMapping(path = "/test")
	public String test() {
		return cartService.test();
	}
}

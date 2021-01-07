package com.assessment.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.assessment.order.model.Cart;


@Component
public interface CartDAO extends JpaRepository<Cart, Integer> {

}

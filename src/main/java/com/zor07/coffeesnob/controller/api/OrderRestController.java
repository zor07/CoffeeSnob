package com.zor07.coffeesnob.controller.api;

import com.zor07.coffeesnob.dto.OrderForm;
import com.zor07.coffeesnob.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {


    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity saveOrder(@RequestBody OrderForm orderForm) {
        orderService.saveOrder(orderForm);
        return ResponseEntity.ok().build();
    }

}

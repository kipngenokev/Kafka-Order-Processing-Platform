package com.kipngeno.order_service.Orders;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final AtomicLong orderIdGenerator = new AtomicLong(1000);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createOrder(
            @Valid @RequestBody CreateOrderRequest request) {
        long orderId = orderIdGenerator.incrementAndGet();
        return Map.of(
                "orderId", orderId,
                "customerId", request.customerId(),
                "productId", request.productId(),
                "quantity", request.quantity(),
                "status", "RECEIVED"

        );


    }
}

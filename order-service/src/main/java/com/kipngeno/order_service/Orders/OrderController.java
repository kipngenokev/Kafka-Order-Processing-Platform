package com.kipngeno.order_service.Orders;

import com.kipngeno.order_service.kafka.OrderEventProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final AtomicLong orderIdGenerator = new AtomicLong(1000);

    private final OrderEventProducer orderEventProducer;

    public OrderController(OrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createOrder(
            @Valid @RequestBody CreateOrderRequest request) {
        long orderId = orderIdGenerator.incrementAndGet();

        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.customerId(),
                request.productId(),
                request.quantity(),
                Instant.now()
        );

        orderEventProducer.publishOrder(event);

        return Map.of(
                "orderId", orderId,
                "customerId", request.customerId(),
                "productId", request.productId(),
                "quantity", request.quantity(),
                "status", "RECEIVED"

        );


    }
}

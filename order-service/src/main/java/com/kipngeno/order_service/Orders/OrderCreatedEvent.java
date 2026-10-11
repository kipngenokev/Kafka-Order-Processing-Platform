package com.kipngeno.order_service.Orders;

import java.time.Instant;

public record OrderCreatedEvent(
        Long orderId,
        Long customerId,
        Long productId,
        Integer quantity,
        Instant createdAt
) {
}

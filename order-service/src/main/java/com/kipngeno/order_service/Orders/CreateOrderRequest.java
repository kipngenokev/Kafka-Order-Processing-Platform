package com.kipngeno.order_service.Orders;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest (
        @NotNull Long customerId,
        @NotNull Long productId,
        @NotNull @Min(1) Integer quantity
){
}

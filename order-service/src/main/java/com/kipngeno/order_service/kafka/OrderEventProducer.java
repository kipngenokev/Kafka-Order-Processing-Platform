package com.kipngeno.order_service.kafka;

import com.kipngeno.order_service.Orders.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OrderEventProducer {
    private final String TOPIC = "orders_created";

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrder(OrderCreatedEvent event) {
        try{
           kafkaTemplate.send(
                   TOPIC,
                   event.orderId().toString(),
                   event

           ).get(5, TimeUnit.SECONDS);
        } catch(Exception exception) {
            throw new IllegalStateException(
                    "Failed to publish order-created event",
                    exception
            );
        }
    }


}

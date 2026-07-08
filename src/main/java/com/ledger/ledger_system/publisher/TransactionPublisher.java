package com.ledger.ledger_system.publisher;

import com.ledger.ledger_system.config.RabbitMQConfig;
import com.ledger.ledger_system.events.TransactionEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionPublisher {

    private final RabbitTemplate rabbitTemplate;

    public TransactionPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(TransactionEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );

        System.out.println("Published Transaction Event : "
                + event.getReferenceNumber());
    }
}
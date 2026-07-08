package com.ledger.ledger_system.consumer;

import com.ledger.ledger_system.config.RabbitMQConfig;
import com.ledger.ledger_system.events.TransactionEvent;
import com.ledger.ledger_system.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
    public void receive(TransactionEvent event) {

        notificationService.saveNotification(event);

        System.out.println("Notification stored for "
                + event.getReferenceNumber());
    }
}
package com.ledger.ledger_system.consumer;

import com.ledger.ledger_system.config.RabbitMQConfig;
import com.ledger.ledger_system.events.TransactionEvent;
import com.ledger.ledger_system.service.AnalyticsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsConsumer {

    private final AnalyticsService analyticsService;

    public AnalyticsConsumer(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @RabbitListener(queues = RabbitMQConfig.ANALYTICS_QUEUE)
    public void receive(TransactionEvent event) {

        analyticsService.saveAnalytics(event);

        System.out.println("Analytics stored for "
                + event.getReferenceNumber());
    }
}
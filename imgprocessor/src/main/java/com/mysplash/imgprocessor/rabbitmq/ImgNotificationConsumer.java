package com.mysplash.imgprocessor.rabbitmq;


import com.mysplash.clients.ImageNotification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ImgNotificationConsumer {

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(ImageNotification notification) {
        log.info("Consumed {} from queue", notification);

    }
}

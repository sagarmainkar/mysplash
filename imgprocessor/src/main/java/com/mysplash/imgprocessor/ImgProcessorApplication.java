package com.mysplash.imgprocessor;


import com.mysplash.imgprocessor.rabbitmq.ImgNotificationConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.mysplash.imgprocessor",
        "com.mysplash.amqp"
})
@AllArgsConstructor
public class ImgProcessorApplication {

    private ImgNotificationConfig notificationConfig;

    public static void main(String[] args) {
        SpringApplication.run(ImgProcessorApplication.class,args);
    }


}

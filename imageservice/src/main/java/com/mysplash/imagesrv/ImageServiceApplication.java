package com.mysplash.imagesrv;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
        "com.mysplash.imagesrv",
        "com.mysplash.amqp"
})
@Slf4j
@EnableFeignClients(basePackages = "com.mysplash.amqp")
public class ImageServiceApplication {


    public static void main(String[] args) {

        SpringApplication.run(ImageServiceApplication.class,args);


    }






}

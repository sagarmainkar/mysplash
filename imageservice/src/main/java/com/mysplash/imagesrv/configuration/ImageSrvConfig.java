package com.mysplash.imagesrv.configuration;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Getter
@Setter
public class ImageSrvConfig {

    @Value("${azure.connection.string}")
    private String connection_string;

    @Value("${azure.container.name}")
    private String container_name;


}

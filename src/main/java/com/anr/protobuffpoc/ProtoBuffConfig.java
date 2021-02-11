package com.anr.protobuffpoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class ProtoBuffConfig {

    @Bean
    public ProtobufHttpMessageConverter protobufJsonFormatHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }


    @Bean
    public RestTemplate restTemplate() {
        ProtobufHttpMessageConverter httpMessageConverter = new ProtobufHttpMessageConverter();
        ProtobufJsonFormatHttpMessageConverter protobufJsonFormatHttpMessageConverter = new ProtobufJsonFormatHttpMessageConverter();
        return new RestTemplate(Arrays.asList(httpMessageConverter, protobufJsonFormatHttpMessageConverter));
    }
}

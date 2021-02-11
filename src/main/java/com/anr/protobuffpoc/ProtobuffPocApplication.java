package com.anr.protobuffpoc;

import com.anr.protobuffpoc.entity.StudentEntity;
import com.anr.protobuffpoc.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Configuration
public class ProtobuffPocApplication {

    @Autowired
    private StudentRepo studentRepo;

    public static void main(String[] args) {
        SpringApplication.run(ProtobuffPocApplication.class, args);
    }


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

    /**
     * adding default records while application start, hence H2 DB will no records.
     */
    @PostConstruct
    public void init() {
        studentRepo.saveAll(
                List.of(new StudentEntity(1, "aswarth", "bhupathi"),
                        new StudentEntity(2, "sharath", "k"),
                        new StudentEntity(3, "Prakash", "p")));
    }
}

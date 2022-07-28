package kz.zhelezyaka.spring5webfluxrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class Spring5WebfluxRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring5WebfluxRestApplication.class, args);
    }

}

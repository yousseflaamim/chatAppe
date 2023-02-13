package com.example.duckradio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class DuckRadioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuckRadioApplication.class, args);
    }

}

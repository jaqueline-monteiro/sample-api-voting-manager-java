package br.com.sample.api.voting.manager.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.com.sample" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

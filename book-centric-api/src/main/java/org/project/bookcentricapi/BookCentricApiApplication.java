package org.project.bookcentricapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookCentricApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCentricApiApplication.class, args);
    }

}

package com.fabiankevin.dev.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor
@Log4j2
@EnableScheduling
public class ProducerApplication {

    private final KafkaTemplate<String, Student> kafkaTemplate;
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }


    @Scheduled(fixedDelay = 20000)
    public void produce(){
        Random random = new Random();
        int randomInt = random.nextInt(10000);
        final Student student = Student.builder()
                .birthDate(LocalDate.now().minusDays(randomInt))
                .name(String.format("Android_%s", randomInt))
                .virgin(randomInt > 500)
                .build();

        kafkaTemplate.send("students", student);
    }


}

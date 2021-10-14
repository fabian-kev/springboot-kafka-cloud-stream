package com.fabiankevin.dev.reactiveconsumer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class ReactiveConsumerApplication {
    private final KafkaService kafkaService;
    public static void main(String[] args) {
        SpringApplication.run(ReactiveConsumerApplication.class, args);
    }



    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> virginStudents(){
        return kafkaService.getEventPublisher()
                .map(consumerRecord -> ServerSentEvent.builder(consumerRecord.data()).build().toString());
    }
}

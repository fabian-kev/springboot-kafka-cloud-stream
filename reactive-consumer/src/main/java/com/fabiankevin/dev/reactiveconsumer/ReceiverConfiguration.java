package com.fabiankevin.dev.reactiveconsumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReceiverConfiguration {

    @Bean
    KafkaReceiver kafkaReceiver() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put( ConsumerConfig.GROUP_ID_CONFIG, "G1");
        ReceiverOptions<Integer, String> receiverOptions =
                ReceiverOptions.<Integer, String>create(configProps)
                        .subscription(Collections.singleton("virgin.students"));
        return KafkaReceiver.create(receiverOptions);
    }
}

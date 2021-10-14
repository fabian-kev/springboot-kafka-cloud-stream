package com.fabiankevin.dev.consumer;

import com.fabiankevin.dev.producer.Student;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KafkaConsumerConfiguration {

    @Bean
    public Consumer<KStream<String, Student>> studentConsumer(){
        return stringStudentKStream -> stringStudentKStream.foreach((k, student) -> {
            System.out.println("********");
            System.out.println("Name: "+student.getName());
            System.out.println("Age: "+student.getAge());
            System.out.println("Virgin: "+student.isVirgin());
            System.out.println("BithDate: "+student.getBirthDate());
            System.out.println("********");
            System.out.println("\n\n");

        });

    }
}

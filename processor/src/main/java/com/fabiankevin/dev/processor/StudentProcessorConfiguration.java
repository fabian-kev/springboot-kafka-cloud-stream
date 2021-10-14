package com.fabiankevin.dev.processor;

import com.fabiankevin.dev.producer.Student;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class StudentProcessorConfiguration {

    @Bean
    public Function<KStream<String, Student>, KStream<String, Student>> studentProcessor(){
        return kstream -> kstream.filter((key, student) -> {
            if(student.isVirgin()){
                System.out.println(String.format("%s -> %s is virgin at age of %s",key, student.getName(), student.getAge()));
            } else {
                System.out.println("Not virgin anymore "+student.getName());
            }
            return student.isVirgin();
        });
    }
}

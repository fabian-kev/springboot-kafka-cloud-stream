package com.fabiankevin.dev.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private LocalDate birthDate;
    private boolean virgin;

    public int getAge(){
        return Period.between(LocalDate.now(),  birthDate).getYears();
    }
}

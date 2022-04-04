package com.idigital.epam.energy.service.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Person {
    private Long cardNumber;
    private String fullName;
}

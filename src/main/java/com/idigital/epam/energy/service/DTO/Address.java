package com.idigital.epam.energy.service.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Address {
    private String homeNumber;
    private String street;
    private String district;

}

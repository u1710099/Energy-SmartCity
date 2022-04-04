package com.idigital.epam.energy.service.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Parents {
    private Person mother;
    private Person father;
}

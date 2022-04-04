package com.idigital.epam.energy.service.DTO;


import com.idigital.epam.energy.entity.Address;
import com.idigital.epam.energy.enums.BuildingType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class HomeDto {
    private Long homeCode;
    private Address address;

}

package com.idigital.epam.energy.common;

import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.service.DTO.*;

public class CommonTestObjects {
    public static User getUserObject(){
        User u = new User();
        u.setActive(true);
        u.setCardNumber("Card Number");
        u.setPassword("Password");
        return u;
    }


    public static ResponseResident getResponseResident(){
        return ResponseResident
                .builder()
                .result(Resident
                        .builder()
                        .cardNumber(73234L)
                        .birthDate("22/02/2022")
                        .firstName("FirstName")
                        .gender("MALE")
                        .maritalStatus("SINGLE")
                        .photoId("6d632859-49e6-45d9-a4de-41964a0ef46e")
                        .parents(Parents
                                .builder()
                                .father(Person
                                        .builder()
                                        .fullName("Parent Full")
                                        .cardNumber(74774747L)
                                        .build())
                                .mother(Person
                                        .builder()
                                        .fullName("Mother Full")
                                        .cardNumber(7447L)
                                        .build())
                                .build())
                        .lastName("Last")
                        .active(true)
                        .address(Address
                                .builder()
                                .district("district")
                                .build())
                        .build())
                .success(true)
                .build();

    }

    public static EnergyResponse getEnergyResponse(){
        return EnergyResponse
                .builder()
                .id(90L)
                .buildingType(BuildingType.RESIDENTIAL)
                .energyConsumption(11)
                .cardNumber("1111111")
                .firstName("AB")
                .homeCode(1L)
                .build();
    }

    public static EnergyMeter getEnergyMeter(){
        return EnergyMeter
                .builder()
                .id(90L)
                .energyConsumption(11)
                .previousReading(111)
                .energyConsumption(222)
                .build();
    }

    public static Billing getBilling(){
        return Billing
                .builder()
                .energyConsumption(122)
                .amountEnergyConsumption(33343)
                .sum(332)
                .id(1L)
                .energyMeter(
                        getEnergyMeter()
                ).build();
    }
}

package com.idigital.epam.energy.common;

import com.idigital.epam.energy.entity.User;
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
}

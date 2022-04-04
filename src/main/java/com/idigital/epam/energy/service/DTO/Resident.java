package com.idigital.epam.energy.service.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Resident {
    private Long cardNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthDate;
    private String maritalStatus;
    private String photoId;
    private Address address;
    private Parents parents;
    private Boolean active;

}

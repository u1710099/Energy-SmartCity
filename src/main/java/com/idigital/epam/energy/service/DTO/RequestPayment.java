package com.idigital.epam.energy.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RequestPayment {
    private String title = "Payment for energy";
    private String description = "Please pay in  5days";
    private String serviceName = "ENERGY";
    private Integer amount;
    private String citizenCardNumber;

    public RequestPayment(String title, String description, String serviceName, Integer amount, String citizenCardNumber) {
        this.title = title;
        this.description = description;
        this.serviceName = serviceName;
        this.amount = amount;
        this.citizenCardNumber = citizenCardNumber;
    }

    public RequestPayment(Integer amount, String citizenCardNumber) {
        this.amount = amount;
        this.citizenCardNumber = citizenCardNumber;
    }

}

package com.idigital.epam.energy.service.DTO;

import com.sun.istack.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter

public class PaymentRequestToPaymentService {

    //Name of every service
    @NotNull/*(message = "serviceName can not be null")*/
    private String serviceName = "ENERGY_SYSTEM";
    //Description to every payment
    @NotNull/*(message = "description can not be null")*/
    private String description ;
    //amount of payment
    @NotNull/*(message = "amount can not be null")*/
    private Double amount ;
    //citizen card id which generated by CITY_MENEGMENT
    @NotNull/*(message = "senderCardNumber can not be null")*/
    private Long senderCardNumber;
    //citizen card id which generated by CITY_MENEGMENT
    @NotNull/*(message = "receiverCardNumber can not be null")*/
    private Long receiverCardNumber;
    //Redirect after payment comoplition
    @NotNull/*(message = "redirectUrl can not be null")*/
    private String redirectUrl;

}

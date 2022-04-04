package com.idigital.epam.energy.controller;

import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.entity.EnergyMeter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingControllerTest {


    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final String URL = "http://localhost:8080/api/billing";


    @Test
    public void TestBilling(){

        Billing billing = new Billing();
        billing.setEnergyConsumption(UUID.randomUUID().variant());
        billing.setAmountEnergyConsumption(UUID.randomUUID().variant());
        billing.setEnergyMeter(new EnergyMeter());
        billing.setSum(UUID.randomUUID().variant());
        Billing created = REST_TEMPLATE.postForObject(URL + "/save", billing, Billing.class);

        verify(billing, created);

        Billing byId = REST_TEMPLATE.getForObject(URL + "/getOne/" + created.getId(), Billing.class);
        verify(created, byId);



        List<?> billings = REST_TEMPLATE.getForObject(URL + "/getAllBillings", List.class);
        assertThat(billings).isNotEmpty();
    }

    private void verify(Billing billing, Billing created) {
        assertThat(billing).isNotNull().isInstanceOf(Billing.class);
        assertThat(created.getId()).isNotNull();
        assertThat(created.getSum()).isEqualTo(billing.getSum());
        assertThat(created.getAmountEnergyConsumption()).isEqualTo(billing.getAmountEnergyConsumption());
    }
}

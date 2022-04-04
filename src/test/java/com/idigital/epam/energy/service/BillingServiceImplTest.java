package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.repository.BillingRepository;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.service.DTO.RequestPayment;
import com.idigital.epam.energy.service.Impl.BillingServiceImpl;
import com.idigital.epam.energy.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillingServiceImplTest {



    @InjectMocks
    BillingServiceImpl billingService;

    @Mock
    BillingRepository billingRepository;

    @Mock
    EnergyMeterRepository energyMeterRepository;

    @Mock
    EnergyMeterService energyMeterService;

    @Mock
    HMACUtilsService hmACUtils;
    @Mock
    UserService userService;

    @Test
    public void getAll(){

        EnergyMeter energyMeter = new EnergyMeter();
        energyMeter.setId(1L);
        energyMeter.setEnergyConsumption(33);
        energyMeter.setPreviousReading(22);

        List<Billing> billings = new ArrayList<>();
        Billing billing = new Billing();
        billing.setEnergyConsumption(112);
        billing.setAmountEnergyConsumption(343);
        billing.setEnergyMeter(energyMeter);
        billing.setSum(444);
        billings.add(billing);

        when(billingRepository.findAll()).thenReturn(billings);

        List<Billing> resp = billingService.getAll();
        assertNotNull(billings);
        assertEquals(billings, resp);

    }
    @Test
    public void findByHomeId() throws Exception {

        Long homeId = 1l;

        EnergyResponse energyResponse = new EnergyResponse();
        energyResponse.setId(1L);
        energyResponse.setCardNumber("1111111");
        energyResponse.setEnergyConsumption(11);
        energyResponse.setFirstName("Ab");
        energyResponse.setBuildingType(BuildingType.RESIDENTIAL);
        energyResponse.setHomeCode(1L);


        EnergyMeter energyMeter = new EnergyMeter();
        energyMeter.setPreviousReading(111);
        energyMeter.setId(1L);
        energyMeter.setEnergyConsumption(222);

        List<Billing>billingList = new ArrayList<>();
        Billing billing = new Billing();
        billing.setId(1L);
        billing.setEnergyMeter(energyMeter);
        billing.setEnergyConsumption(222);
        billing.setAmountEnergyConsumption(3444);
        billing.setSum(45555);
        billingList.add(billing);

        when(energyMeterRepository.getEnergyByHomeID(homeId)).thenReturn(energyResponse);
        when(billingRepository.findByEnergyMeterId(energyResponse.getId())).thenReturn(billingList);

        List<Billing> resp = billingService.findByHomeId(homeId);
        assertNotNull(billingList);
        assertEquals(billingList, resp);
    }

    @Test
    public void create() throws Exception {


        EnergyMeter energyMeter = new EnergyMeter();
        energyMeter.setId(1L);
        energyMeter.setEnergyConsumption(33);
        energyMeter.setPreviousReading(22);

        Billing billingRequest = new Billing();
        billingRequest.setEnergyConsumption(122);
        billingRequest.setEnergyMeter(energyMeter);
        billingRequest.setSum(332);
        billingRequest.setId(1L);
        billingRequest.setAmountEnergyConsumption(33343);


//        User u = new User();
//        u.setActive(true);
//        u.setCardNumber("232");
//
//        when(userService.getCurrentUser()).thenReturn(u);
//        when(u.getCardNumber()).thenReturn("232");
        RequestPayment paymentRequest = new RequestPayment(billingRequest.getSum(), "232");
       // when(hmACUtils.postRequestWithHmac(anyString(), anyString(),  anyString(), anyString(), anyObject())).thenReturn(obj);
        when(billingRepository.save(billingRequest)).thenReturn(billingRequest);

        Billing res = billingService.create(billingRequest);
        assertEquals(billingRequest,res);
    }


}

package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.repository.BillingRepository;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.service.Impl.BillingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
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
        assertNotNull(billings);

    }
    @Test
    public void findByHomeId(){

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
        when(energyMeterRepository.findByHomeId(1L)).thenReturn(energyMeter);
        when(billingRepository.findByEnergyMeterId(1L)).thenReturn(billingList);
        assertNotNull(billing);
    }

    @Test
    public void create(){

    }


}

package com.idigital.epam.energy.service;

import com.idigital.epam.energy.common.CommonTestObjects;
import com.idigital.epam.energy.entity.*;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.repository.BillingRepository;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.service.DTO.RequestPayment;
import com.idigital.epam.energy.service.Impl.BillingServiceImpl;
import com.idigital.epam.energy.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BillingServiceImplTest {



    @InjectMocks
    BillingServiceImpl billingService; // real parameters e.g userId = 12L

    @Mock
    BillingRepository billingRepository; // any() if it userId use anyLong() any()

    @Mock
    EnergyMeterRepository energyMeterRepository;

    @Mock
    EnergyMeterService energyMeterService;

    @Mock
    HMACUtilsService hmACUtils;
    @Mock
    UserService userService;

    @Mock
    BillingHmacUtilsService billingHmacUtilsService;

    @Test
    public void getAll(){
        List<Billing> billings = new ArrayList<>(Collections.singletonList(CommonTestObjects.getBilling()));
        when(billingRepository.findAll()).thenReturn(billings);
        List<Billing> resp = billingService.getAll();
        assertNotNull(billings);
        assertEquals(billings, resp);
    }
    @Test
    public void findByHomeId() throws Exception {
        Long homeId = 1L;
        EnergyResponse energyResponse = CommonTestObjects.getEnergyResponse();
        List<Billing>billingList = new ArrayList<>(Collections.singletonList(CommonTestObjects.getBilling()));
        when(energyMeterRepository.getEnergyByHomeID(anyLong())).thenReturn(energyResponse);
        when(billingRepository.findByEnergyMeterId(anyLong())).thenReturn(billingList);
        List<Billing> resp = billingService.findByHomeId(homeId);
        assertNotNull(billingList);
        assertEquals(billingList, resp);
    }

    @Test
    public void create() throws Exception {
        EnergyMeter energyMeter = CommonTestObjects.getEnergyMeter();
        Billing billingRequest = CommonTestObjects.getBilling();
        User u = new User();
        u.setActive(true);
        u.setCardNumber("232");
        when(userService.getCurrentUser()).thenReturn(u);
        when(billingHmacUtilsService
                .postRequestWithHmac(anyString(),anyString(),anyString(),anyString(),any()))
                .thenReturn("String");
       when(billingRepository.save(billingRequest)).thenReturn(billingRequest);

        Billing res = billingService.create(billingRequest);
        assertEquals(billingRequest,res);
    }

    @Test
    public void createWithNullPostRequest() throws Exception {
        Billing billingRequest = CommonTestObjects.getBilling();
        User u = new User();
        u.setActive(true);
        u.setCardNumber("232");
        when(userService.getCurrentUser()).thenReturn(u);
        Exception ex = assertThrows(Exception.class, () -> billingService.create(billingRequest),"We have some problem with connection");
        Assertions.assertEquals("We have some problem with connection", ex.getMessage());
    }

    @Test
    public void testGetById(){
        Optional<Billing> billing = Optional.of(CommonTestObjects.getBilling());
        when(billingRepository.findById(anyLong())).thenReturn(billing); // mocking
        Billing bill = billingService.getById(10L); // real Inject mock
        assertNotNull(bill);
    }

    @Test
    public void testGetByIdWhereBillingIsNull(){
        Optional<Billing> billing = Optional.empty();
        when(billingRepository.findById(anyLong())).thenReturn(billing); // mocking
        Billing bill = billingService.getById(10L); // real Inject mock
        assertNull(bill);
    }

    @Test
    public void testUpdateBilling() throws Exception {
        when(billingRepository.findById(anyLong()))
                .thenReturn(Optional.of(CommonTestObjects.getBilling()));
        when(billingRepository.save(any())).thenReturn(null);
        Billing bill = billingService.update(CommonTestObjects.getBilling());
        assertNotNull(bill);
    }

    @Test
    public void testUpdateBillingThrowsException() throws Exception {
        when(billingRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        Billing testBill = CommonTestObjects.getBilling();
        Exception ex = assertThrows(Exception.class, () -> billingService.update(testBill),"Billing Not found for Id " + testBill.getId());
        Assertions.assertEquals("Billing Not found for Id " + testBill.getId(), ex.getMessage());
    }

    @Test
    public void testDeleteBilling() throws Exception {
        when(billingRepository.findById(anyLong()))
                .thenReturn(Optional.of(CommonTestObjects.getBilling()));
        doNothing().when(billingRepository).deleteById(anyLong());
        billingService.deleteById(90L);
    }

    @Test
    public void testDeleteBillThrowsException() throws Exception {
        when(billingRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        Billing testBill = CommonTestObjects.getBilling();
        Exception ex = assertThrows(Exception.class, () -> billingService.deleteById(1L),"Billing Not found for Id " + testBill.getId());
        Assertions.assertEquals("Billing Not found for Id " + testBill.getId(), ex.getMessage());
    }


}

package com.idigital.epam.energy.service.Impl;

import com.idigital.epam.energy.entity.Billing;
import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.repository.BillingRepository;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.service.BillingHmacUtilsService;
import com.idigital.epam.energy.service.BillingService;
import com.idigital.epam.energy.payload.BillingRequest;
import com.idigital.epam.energy.service.DTO.RequestPayment;
import com.idigital.epam.energy.service.EnergyMeterService;
import com.idigital.epam.energy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    BillingRepository billingRepository;

    @Autowired
    EnergyMeterRepository energyMeterRepository;

    @Autowired
    EnergyMeterService energyMeterService;

    @Autowired
    HMACUtilsService hmACUtils;
    @Autowired
    UserService userService;

    @Autowired
    BillingHmacUtilsService billingHmacUtilsService;

    @Override
    public List<Billing> getAll() {
        return billingRepository.findAll();
    }



    @Override
    public Billing getById(Long id) {
        Optional<Billing> billing  = billingRepository.findById(id);
        return billing.orElse(null);
    }

    @Override
    public Billing create(Billing billingRequest) throws Exception {
        User user = userService.getCurrentUser();
        String cardNumber = user.getCardNumber();
        RequestPayment paymentRequest = new RequestPayment(billingRequest.getSum(), cardNumber);
        String basePath = "http://54.236.248.165/api/v1";
        String keyId = "ENERGY";
        String action = "payment";
        String secretKey = "energyKey";
        String some = billingHmacUtilsService.postRequestWithHmac(keyId,action,basePath,secretKey,paymentRequest);
        if(some != null){
            return billingRepository.save(billingRequest);
        }else{
            throw new Exception("We have some problem with connection");
        }


    }

    @Override
    public Billing update(Billing billing) throws Exception{
        Optional<Billing> bill = billingRepository.findById(billing.getId());
        if(bill.isPresent()){
            billingRepository.save(billing);
            return billing;
        }else{
            throw new Exception("Billing Not found for Id "+billing.getId());
        }

    }

    @Override
    public void delete(Billing data) {
        // TODO: implement
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Optional<Billing> bill = billingRepository.findById(id);
          if(bill.isPresent()){
              billingRepository.deleteById(id);
          }else{
              throw new Exception("Billing Not found for Id "+id);
          }


    }

    @Override
    public List<Billing> findByHomeId(Long homeId) throws Exception {
        EnergyResponse energyMeter = energyMeterRepository.getEnergyByHomeID(homeId);
        return billingRepository.findByEnergyMeterId(energyMeter.getId());
    }
}

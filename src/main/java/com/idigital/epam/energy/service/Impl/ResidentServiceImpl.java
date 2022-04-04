package com.idigital.epam.energy.service.Impl;

import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    HMACUtilsService hmacUtils;

    @Override
    public String getResident(Long cardNumber) throws Exception {
        return hmacUtils.getRequestWithHmac("ENERGY", "get_resident", "http://citymanagementbackend-env-1.eba-3swwhqnr.us-east-2.elasticbeanstalk.com/api/v1/resident/card/" + cardNumber, "energyKey").getBody().toString();
    }
}

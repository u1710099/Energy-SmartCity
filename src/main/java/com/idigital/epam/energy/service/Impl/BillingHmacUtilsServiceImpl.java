package com.idigital.epam.energy.service.Impl;

import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.service.BillingHmacUtilsService;
import com.idigital.epam.energy.service.DTO.RequestPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingHmacUtilsServiceImpl implements BillingHmacUtilsService {


    @Autowired
    HMACUtilsService hmACUtils;

    @Override
    public String postRequestWithHmac(String keyId, String action, String path, String secretKey, RequestPayment paymentRequest) throws Exception {
        return
                String.valueOf(hmACUtils.postRequestWithHmac(keyId, action, path + "/pay-notification", secretKey, paymentRequest)
                .getBody());
    }
}

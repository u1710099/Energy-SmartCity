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
    public String getHmacRequest(String keyId, String action, String path, String secretKey) throws Exception {
        return String.valueOf(hmacUtils.getRequestWithHmac(keyId, action, path, secretKey).getBody());
    }
}

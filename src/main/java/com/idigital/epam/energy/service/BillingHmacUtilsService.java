package com.idigital.epam.energy.service;

import com.idigital.epam.energy.service.DTO.RequestPayment;

public interface BillingHmacUtilsService {
    String postRequestWithHmac(String keyId, String action, String path, String secretKey, RequestPayment paymentRequest) throws Exception;
}

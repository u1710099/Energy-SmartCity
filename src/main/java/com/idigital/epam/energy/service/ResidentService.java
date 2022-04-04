package com.idigital.epam.energy.service;

public interface ResidentService {
    String getHmacRequest(String keyId, String action, String path, String secretKey) throws Exception;
}

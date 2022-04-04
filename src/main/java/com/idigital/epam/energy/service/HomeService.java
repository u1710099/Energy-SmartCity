package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.service.DTO.HomeResponse;
import com.idigital.epam.energy.service.DTO.Result;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HomeService extends CommonService<Home>{

    Home create() throws Exception;
    Result getHomesByCardNumber(Long cardNum);
    Home createInstitutional() throws Exception;
    List<HomeResponse> getHomesList();

}

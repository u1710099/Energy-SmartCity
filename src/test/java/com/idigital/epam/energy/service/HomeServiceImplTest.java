package com.idigital.epam.energy.service;


import com.idigital.epam.energy.entity.Address;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.repository.UserRepository;
import com.idigital.epam.energy.service.DTO.HomeResponse;
import com.idigital.epam.energy.service.Impl.HomeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeServiceImplTest {


    @InjectMocks
    HomeServiceImpl homeService;

    @Mock
    HomeRepository homeRepository;

    @Mock
    HMACUtilsService hmACUtils;

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    AddressService addressService;

    @Test
    public void getAll() {

        Address address = new Address();
        address.setStreet("any");
        address.setDistrict("GNT");

        User user = new User();
        user.setActive(true);
        user.setId(1l);

        List<Home> homes = new ArrayList<>();
        Home home = new Home();
        home.setId(1l);
        home.setHomeCode(1l);
        home.setBuildingType(BuildingType.RESIDENTIAL);
        home.setAddress(address);
        home.setUser(user);
        homes.add(home);

        when(homeRepository.findAll()).thenReturn(homes);
              assertNotNull(homes);
    }

    @Test
    public void getHomesList(){
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setHomeNumber("1111");
        homeResponse.setHomeCode(1L);
        homeResponse.setStreet("AB");
        homeResponse.setDistrict("Bn");
        homeResponse.setBuildingType(BuildingType.RESIDENTIAL);
        homeResponse.setId(1L);

        List<HomeResponse> homeResponseList = new ArrayList<>();
        homeResponseList.add(homeResponse);
        when(homeRepository.getAllHoMes()).thenReturn(homeResponseList);


    }

    @Test
    public void create() throws Exception {


        Address address = new Address();
        address.setStreet("any");
        address.setDistrict("GNT");

        User user = new User();
        user.setActive(true);
        user.setId(1l);


        Home home = new Home();
        home.setId(1l);
        home.setHomeCode(1l);
        home.setBuildingType(BuildingType.RESIDENTIAL);
        home.setAddress(address);
        home.setUser(user);
//        when(hmACUtils.getRequestWithHmac("ENERGY","get_homes","http://citymanagementbackend-env-1.eba-3swwhqnr.us-east-2.elasticbeanstalk.com/api/v1/request/homesWithOwner","energyKey")).thenReturn(home);

        when(homeService.create()).thenReturn(home);

    }
}

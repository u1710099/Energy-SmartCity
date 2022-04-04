package com.idigital.epam.energy.service;


import com.google.gson.Gson;
import com.idigital.epam.energy.common.CommonTestObjects;
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
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Mock
    ResidentService residentService;

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
        List<Home> h = homeService.getAll();
              assertNotNull(h);
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
        List<HomeResponse> resp = homeService.getHomesList();
        assertNotNull(resp);
    }

    @Test
    public void getById(){
        when(homeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(CommonTestObjects.getHomeTestObject()));
        Home resp = homeService.getById(1L);
        assertNotNull(resp);
    }

    @Test
    public void getByIdWhereHomeIsNull(){
        when(homeRepository.findById(anyLong())).thenReturn(Optional.empty());
        Home resp = homeService.getById(1L);
        assertNull(resp);
    }

    @Test
    public void create() throws Exception {
        Address address = CommonTestObjects.getAddressObject();
        User user = new User();
        user.setActive(true);
        user.setId(1L);
        Home home = CommonTestObjects.getHomeTestObject();
        home.setUser(user);
        when(residentService.getHmacRequest(any(),any(),any(),any())).thenReturn(new Gson().toJson(CommonTestObjects.getResponseTestObject()));
        when(userService.create(any(),any())).thenReturn(user);
        when(addressService.createAddress(any())).thenReturn(address);
        when(homeRepository.save(any())).thenReturn(null);
        Home results =  homeService.create();
        assertNull(results);

    }

    @Test
    public void createHomeWhenUserIsNull() throws Exception {
        Address address = CommonTestObjects.getAddressObject();
        User user = new User();
        user.setActive(true);
        user.setId(1L);
        Home home = CommonTestObjects.getHomeTestObject();
        home.setUser(user);
        when(residentService.getHmacRequest(any(),any(),any(),any())).thenReturn(new Gson().toJson(CommonTestObjects.getResponseTestObject()));
        when(userService.create(any(),any())).thenReturn(null);
        when(addressService.createAddress(any())).thenReturn(address);
        when(homeRepository.save(any())).thenReturn(null);
        Home results =  homeService.create();
        assertNull(results);

    }

    @Test
    public void createInstitutional() throws Exception {
        Address address = CommonTestObjects.getAddressObject();
        User user = new User();
        user.setActive(true);
        user.setId(1L);
        Home home = CommonTestObjects.getHomeTestObject();
        home.setUser(user);
        when(residentService.getHmacRequest(any(),any(),any(),any())).thenReturn(new Gson().toJson(CommonTestObjects.getResponseTestObject()));
        when(userService.create(any(),any())).thenReturn(user);
        when(addressService.createAddress(any())).thenReturn(address);
        when(homeRepository.save(any())).thenReturn(null);
        Home results =  homeService.createInstitutional();
        assertNull(results);
    }

    @Test
    public void updateHome() throws Exception {
        when(homeRepository.findById(any())).thenReturn(Optional.ofNullable(CommonTestObjects.getHomeTestObject()));
        when(homeRepository.save(any())).thenReturn(CommonTestObjects.getHomeTestObject());
        Home resp = homeService.update(CommonTestObjects.getHomeTestObject());
        assertNotNull(resp);
    }

    @Test
    public void updateHomeWithException() {
        Home home = CommonTestObjects.getHomeTestObject();
        assert home != null;
        home.setId(null);
        Exception ex = assertThrows(Exception.class, () -> {
            homeService.update(home);
        },"Id shouldn't be null");
        Assertions.assertEquals("Id shouldn't be null", ex.getMessage());

    }
}

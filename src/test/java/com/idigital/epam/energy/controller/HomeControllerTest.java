package com.idigital.epam.energy.controller;


import com.idigital.epam.energy.entity.Address;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.entity.Role;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.repository.RoleRepository;
import com.idigital.epam.energy.service.AddressService;
import com.idigital.epam.energy.service.DTO.HomeResponse;
import com.idigital.epam.energy.service.HomeService;
import com.idigital.epam.energy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class HomeControllerTest {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final String URL = "http://localhost:8080/api/home";

    @Mock
    HomeService homeService;
    @Mock
    HomeRepository homeRepository;
    @Mock
    UserService userService;
    @InjectMocks
    HomeController homeController;


    @Test
    public void getAll() throws Exception {
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

        when(homeService.getAll()).thenReturn(homes);

        ResponseEntity<List<Home>> res = homeController.getAll();
        assertNotNull(res);

    }

    @Test
    public void getResultByCardNumber(){


        Address address = new Address();
        address.setStreet("any");
        address.setDistrict("GNT");

        User user = new User();
        user.setCardNumber("11111111");
        user.setActive(true);
        user.setId(1l);
        user.setFirstName("Fname");
        user.setLastName("lName");
        user.setRole(null);

        Home home = new Home();
        home.setId(1l);
        home.setHomeCode(1l);
        home.setBuildingType(BuildingType.RESIDENTIAL);
        home.setAddress(address);
        home.setUser(user);

        HomeResponse hresp = new HomeResponse();
        hresp.setCardNumber("hai");
        hresp.setDistrict("GNT");
        hresp.setId(1l);
        hresp.setBuildingType(BuildingType.RESIDENTIAL);
        hresp.setStreet("street");
        hresp.setHomeCode(2l);
        hresp.setHomeNumber("111");
        List<HomeResponse> homes = new ArrayList<>();
        homes.add(hresp);

        String str ="hai";
        User u = Mockito.mock(User.class);
        when(userService.getCurrentUser()).thenReturn(user);
        when(homeRepository.findHomeListByUserId(str)).thenReturn( homes);
        ResponseEntity res = homeController.getResultByCardNumber();
        assertNotNull(res);

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
        when(homeService.getHomesList()).thenReturn(homeResponseList);

        ResponseEntity responseEntity = homeController.getHomesList();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }


    @Test
    public void getAllUsersWithHomesTest(){
        List<?> homes = REST_TEMPLATE.getForObject(URL + "/getAllUsersWithHomes", List.class);
    }
}
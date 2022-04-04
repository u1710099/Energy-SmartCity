package com.idigital.epam.energy.service.Impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idigital.epam.energy.entity.Address;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.hmac.HMACUtilsService;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.repository.UserRepository;
import com.idigital.epam.energy.service.*;
import com.idigital.epam.energy.service.DTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service

public class HomeServiceImpl implements HomeService, CommonService<Home> {

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    HMACUtilsService hmACUtils;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    ResidentService residentService;

    public static final String KEY_ID = "ENERGY";
    public static final String SECRET_KEY = "ENERGY";
    public static final String REQUEST_PATH = "http://citymanagementbackend-env-1.eba-3swwhqnr.us-east-2.elasticbeanstalk.com/api/v1/request/homesWithOwner";

    @Override
    public List<Home> getAll() {
        return homeRepository.findAll();
    }
    @Override
    public List<HomeResponse> getHomesList(){
        return homeRepository.getAllHoMes();
    }

    @Override
    public Home getById(Long id) {
        Optional<Home> home = homeRepository.findById(id);
        return home.orElse(null);
    }



    @Override
    public Home create() throws Exception{
        String aas =  residentService.getHmacRequest(KEY_ID,"get_homes", REQUEST_PATH, SECRET_KEY);
        ObjectMapper mapper = new ObjectMapper();
        Response json = mapper.readValue(aas, Response.class);

        Home home;
        Address address;
        List<Result> homes = json.getResult();
        User user;
        for (Result result: homes) {

            user = userService.create(result.getCardNumber(),String.valueOf(result.getCardNumber()));

            for (HomeDto homeDto: result.getHomes()){
               home = new Home();
               home.setHomeCode(homeDto.getHomeCode());
               home.setBuildingType(BuildingType.RESIDENTIAL);
               address = new Address();
               address.setHomeNumber(homeDto.getAddress().getHomeNumber());
               address.setDistrict(homeDto.getAddress().getDistrict());
               address.setStreet(homeDto.getAddress().getStreet());
               address = addressService.createAddress(address);
               home.setAddress(address);
               if (user!=null){
                   home.setUser(user);
               }else {
                   home.setUser(userService.getCurrentUser());
               }
               homeRepository.save(home);
           }
        }
        return null;
    }

    @Override
    public Home createInstitutional() throws Exception {
        String institution =  residentService.getHmacRequest(KEY_ID,"get_institutional",REQUEST_PATH, SECRET_KEY);
        ObjectMapper mapper = new ObjectMapper();
        Response json = mapper.readValue(institution, Response.class);

        Home home;
        Address address;
        List<Result> homes = json.getResult();
        User user;
        for (Result result: homes) {
//            if (!userRepository.existsById(userRepository.findUserByCardNumber(String.valueOf(result.getCardNumber())).get().getId())){
                user = userService.create(result.getCardNumber(),String.valueOf(result.getCardNumber()));
//            }else {
//                user = userRepository.findUserByCardNumber(String.valueOf(result.getCardNumber())).get();
//            }
            for (HomeDto homeDto: result.getHomes()){
                home = new Home();
                home.setHomeCode(homeDto.getHomeCode());
                home.setBuildingType(BuildingType.INSTITUTIONAL);
                address = new Address();
                address.setHomeNumber(homeDto.getAddress().getHomeNumber());
                address.setDistrict(homeDto.getAddress().getDistrict());
                address.setStreet(homeDto.getAddress().getStreet());
                address = addressService.createAddress(address);
                home.setAddress(address);
                home.setUser(user);
                homeRepository.save(home);
            }
        }
        return null;
    }


    @Override
    public Home update(Home data) throws Exception{
        if(data.getId() != null){
            Home newHome = homeRepository.findById(data.getId()).get();
            newHome.setHomeCode(data.homeCode);
            newHome.setBuildingType(data.getBuildingType());
            newHome.setAddress(data.getAddress());
            newHome.setUser(data.getUser());
            return homeRepository.save(data);
        }
        throw new Exception("Id shouldn't be null");
    }
    @Override
    public void delete(Home data) {
        // TODO : homeRepository.deleteById(data.getId());
    }

    public Result getHomesByCardNumber(Long cardNumber){
        Result result = new Result();
        return result;
    }



    public List<HomeDto> parser(List<Home> homes){
        List<HomeDto> homeDtos = new LinkedList<>();
        HomeDto h;
        for (Home home : homes) {
            h = new HomeDto();
            h.setHomeCode(home.getHomeCode());
            h.setAddress(home.getAddress());
            homeDtos.add(h);
        }
        return homeDtos;
    }
}








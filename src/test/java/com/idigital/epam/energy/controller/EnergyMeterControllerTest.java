package com.idigital.epam.energy.controller;

import com.idigital.epam.energy.entity.Address;
import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.payload.Response;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.service.EnergyMeterService;
import com.idigital.epam.energy.service.HomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnergyMeterControllerTest {

   private static final RestTemplate REST_TEMPLATE = new RestTemplate();
   private static final String URL = "http://localhost:8080/api/energymeter";

   @InjectMocks
   EnergyMeterController energyMeterController;

   @Mock
   EnergyMeterService energyMeterService;

   @Mock
   EnergyMeterRepository energyMeterRepository;

   @Mock
   HomeService homeService;

//   @Test
//
//   public void testEnergy(){
//      EnergyMeter energy = new EnergyMeter();
//
//      energy.setHome(new Home(1L));
//      energy.setPreviousReading(UUID.randomUUID().variant());
//      energy.setEnergyConsumption(UUID.randomUUID().variant());
//
//
//      Response created = REST_TEMPLATE.postForObject(URL + "/save", energy, Response.class);
//      assertThat(created.isSuccess()).isEqualTo(true);
//   }


   @Test
   public void getAll(){


      Address address = new Address();
      address.setStreet("any");
      address.setDistrict("GNT");

      Home home = new Home();
      home.setId(1L);
      home.setAddress(address);

      EnergyResponse energyResponse = new EnergyResponse();
      energyResponse.setEnergyConsumption(11);
      energyResponse.setBuildingType(BuildingType.RESIDENTIAL);
      energyResponse.setFirstName("Spring");
      energyResponse.setId(1L);
      energyResponse.setHomeCode(1L);
      energyResponse.setCardNumber("11111111");

      List<EnergyResponse> energyResponseList = new ArrayList<>();
      energyResponseList.add(energyResponse);
      when(energyMeterService.getAllEnergyMeter()).thenReturn(energyResponseList);

      ResponseEntity responseEntity = energyMeterController.getAll();
      assertNotNull(responseEntity);
      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


   }

   @Test

   public void getByHomeId(){



      Address address = new Address();
      address.setStreet("any");
      address.setDistrict("GNT");

      Home home = new Home();
      home.setId(1L);
      home.setAddress(address);

      EnergyResponse energyResponse = new EnergyResponse();
      energyResponse.setEnergyConsumption(11);
      energyResponse.setBuildingType(BuildingType.RESIDENTIAL);
      energyResponse.setFirstName("Spring");
      energyResponse.setId(1L);
      energyResponse.setHomeCode(1L);
      energyResponse.setCardNumber("11111111");

      ArrayList<EnergyResponse> energyResponses = new ArrayList<EnergyResponse>();
      energyResponses.add(energyResponse);
      when(energyMeterService.getAllEnergyMeter()).thenReturn(energyResponses);

      ResponseEntity responseEntity = energyMeterController.getByHomeId(1L);
      assertNotNull(responseEntity);
      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());




   }

   @Test
   public void createEnergyMeter(){

   }



   }

















































//
//   package com.idigital.epam.energy.controller;
//
//           import com.idigital.epam.energy.entity.EnergyMeter;
//           import com.idigital.epam.energy.entity.Home;
//           import com.idigital.epam.energy.payload.Response;
//           import com.idigital.epam.energy.service.HomeService;
//           import org.junit.Test;
//           import org.springframework.beans.factory.annotation.Autowired;
//           import org.springframework.web.client.RestTemplate;
//
//           import java.util.UUID;
//
//           import static org.assertj.core.api.Assertions.assertThat;
//public class EnergyMeterControllerTest {
//    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
//    private static final String URL = "http://localhost:8080/api/energymeter";
//
//    @Autowired
//    HomeService homeService;
//
//    @Test
//    public void testEnergy(){
//        EnergyMeter energy = new EnergyMeter();
//
//        energy.setHome(new Home());
//        energy.setPreviousReading(UUID.randomUUID().variant());
//        energy.setEnergyConsumption(UUID.randomUUID().variant());
//
//
//        Response created = REST_TEMPLATE.postForObject(URL + "/save", energy, Response.class);
//        assertThat(created.isSuccess()).isEqualTo(true);
//    }
//
//
//}

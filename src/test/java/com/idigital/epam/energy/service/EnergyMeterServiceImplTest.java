package com.idigital.epam.energy.service;

import com.idigital.epam.energy.entity.EnergyMeter;
import com.idigital.epam.energy.entity.Home;
import com.idigital.epam.energy.enums.BuildingType;
import com.idigital.epam.energy.payload.EnergyMeterRequest;
import com.idigital.epam.energy.payload.EnergyResponse;
import com.idigital.epam.energy.repository.EnergyMeterRepository;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.service.Impl.EnergyMeterServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class EnergyMeterServiceImplTest {


    @InjectMocks
    EnergyMeterServiceImpl energyMeterService;

    @Mock
    EnergyMeterRepository energyMeter;

    @Mock
    HomeRepository homeRepository;

    @Test
    public void  getAllEnergyMeter(){

        Home home = new Home();
        home.setId(1l);
        home.setHomeCode(1l);
        home.setBuildingType(BuildingType.RESIDENTIAL);
        homeRepository.save(home);

        EnergyMeter energyMeter1r=new EnergyMeter();
        energyMeter1r.setEnergyConsumption(1111);
        energyMeter1r.setHome(home);
        energyMeter.save(energyMeter1r);

        List<EnergyResponse> energyResponseList = new ArrayList<>();
        EnergyResponse energyResponse = new EnergyResponse();
        energyResponse.setId(1L);
        energyResponse.setCardNumber("1111111");
        energyResponse.setEnergyConsumption(11);
        energyResponse.setFirstName("Ab");
        energyResponse.setBuildingType(BuildingType.RESIDENTIAL);
        energyResponse.setHomeCode(1L);
        energyResponseList.add(energyResponse);
        when(energyMeterService.getAllEnergyMeter()).thenReturn(energyResponseList);
    }

    @Test
    public void getByHomeId(){

        Home home = new Home();
        home.setId(1l);
        home.setHomeCode(1l);
        home.setBuildingType(BuildingType.RESIDENTIAL);

        EnergyMeter energyMeter = new EnergyMeter();
        energyMeter.setPreviousReading(111);
        energyMeter.setHome(home);
        energyMeter.setId(1L);
        energyMeter.setEnergyConsumption(222);

       Mockito.when(energyMeterService.getByHomeId(1L)).thenReturn(energyMeter);

    }

    @Test
    public void create(){

        EnergyMeterRequest energyMeterRequest = new EnergyMeterRequest();
        energyMeterRequest.setEnergyConsumption(13);
        energyMeterRequest.setHomeId(1L);
        energyMeterRequest.setInstitutional("Institutional");
        energyMeterRequest.setResidential("Residential");

        Home home = new Home();
        home.setId(1l);
        home.setHomeCode(1l);
        home.setBuildingType(BuildingType.RESIDENTIAL);
        homeRepository.save(home);
        when(homeRepository.findById(1L)).thenReturn(Optional.of(home));

        EnergyMeter energyMeter1r=new EnergyMeter();
        energyMeter1r.setEnergyConsumption(1111);
        energyMeter1r.setHome(home);
        energyMeter.save(energyMeter1r);



    }

    @Test
    public void EnergyMeterCalculation(){

        Home home = new Home();
        home.setId(1l);
        home.setHomeCode(1l);
        home.setBuildingType(BuildingType.RESIDENTIAL);

        List<EnergyMeter> energyMeterList =  new ArrayList<>();
        EnergyMeter energyMeter = new EnergyMeter();
        energyMeter.setPreviousReading(111);
        energyMeter.setHome(home);
        energyMeter.setId(1L);
        energyMeter.setEnergyConsumption(222);
        energyMeterList.add(energyMeter);

       when(energyMeterService.getAll()).thenReturn(energyMeterList);

    }
}

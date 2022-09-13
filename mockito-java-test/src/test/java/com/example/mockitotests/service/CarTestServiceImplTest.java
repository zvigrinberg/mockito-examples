package com.example.mockitotests.service;

import com.example.mockitotests.dao.CarsDBAccess;
import com.example.mockitotests.dao.CarsDBAccessImpl;
import com.example.mockitotests.model.Car;
import com.example.mockitotests.model.CarBuyingDecision;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarTestServiceImplTest {
    @Mock
    private CarsDBAccessImpl carsDBAccessObject = new CarsDBAccessImpl();
    @InjectMocks
    private CarTestService service = new CarTestServiceImpl();
    private List<Car> listOfCars;
    private ObjectMapper om;

    @BeforeEach
    void setUp() {

        Car car1 = new Car("Audi",2019,"red","126-53-669",150000,true,50000);
        Car car2 = new Car("Audi",2019,"blue","192-73-091",130000,true,75000);
        Car car3 = new Car("Audi",2019,"black","442-14-853",155000,true,60000);
        Car car4 = new Car("Audi",2019,"blue","193-72-061",110000,false,90000);
        Car car5 = new Car("Audi",2019,"blue","937-42-558",140000,false,65000);

        om = new ObjectMapper();
        listOfCars = List.of(car1, car2,car3,car4,car5);
        lenient().doAnswer(invocation ->
                { List cars =(List)invocation.getArgument(0);
                    String result = om.writerWithDefaultPrettyPrinter().writeValueAsString(cars);
                    System.out.println(result);
                    return null;
                })
        .when(carsDBAccessObject).saveCarBuyingDecisionToDB(ArgumentMatchers.anyList());
    }
@Test
    void testCarDetailsInsistingAll()
    {
        when(carsDBAccessObject.getAllCarsAccordingToCriteria("Audi",2019)).thenReturn(listOfCars);
        List<CarBuyingDecision> carDetails = service.getCarDetails("Audi", 2019, 150000, true, "blue", true, 70000, true);
        carDetails.forEach(carBD -> { testCarDetailsInsistingAllExpansion(carBD,carBD.getChosenCar()); } );

    }
    
@Test
    void testCarDetailsInsistingOnlyOne()
    {
        when(carsDBAccessObject.getAllCarsAccordingToCriteria("Audi",2019)).thenReturn(listOfCars);
        List<CarBuyingDecision> carDetails = service.getCarDetails("Audi", 2019, 150000, false, "blue", false, 70000, true);
        carDetails.forEach(carBD -> { testCarDetailsInsistingOnlyOneExpansion(carBD,carBD.getChosenCar()); } );


    }

    private void testCarDetailsInsistingOnlyOneExpansion(CarBuyingDecision carBD, Car chosenCar) {
        switch(chosenCar.getLicenseNumber())
        {
            case "126-53-669":
                assertFalse(carBD.isFoundIdealMatch());
                assertTrue(carBD.isFoundMatch());
                assertTrue(carBD.isMetBudgetCriteria());
                assertTrue(carBD.isMetKilometerCriteria());
                assertFalse(carBD.isMetColorCriteria());
                break;

            case "192-73-091":

            case "193-72-061":
                assertFalse(carBD.isFoundIdealMatch());
                assertFalse(carBD.isFoundMatch());
                assertTrue(carBD.isMetBudgetCriteria());
                assertFalse(carBD.isMetKilometerCriteria());
                assertTrue(carBD.isMetColorCriteria());
                break;

            case "442-14-853":
                assertFalse(carBD.isFoundIdealMatch());
                assertTrue(carBD.isFoundMatch());
                assertFalse(carBD.isMetBudgetCriteria());
                assertTrue(carBD.isMetKilometerCriteria());
                assertFalse(carBD.isMetColorCriteria());
                break;


            case "937-42-558":
                assertTrue(carBD.isFoundIdealMatch());
                assertTrue(carBD.isFoundMatch());
                assertTrue(carBD.isMetBudgetCriteria());
                assertTrue(carBD.isMetKilometerCriteria());
                assertTrue(carBD.isMetColorCriteria());
                break;


        }
    }

    private void testCarDetailsInsistingAllExpansion(CarBuyingDecision carBD, Car chosenCar) {
        switch(chosenCar.getLicenseNumber())
        {
            case "126-53-669":
                assertFalse(carBD.isFoundIdealMatch());
                assertFalse(carBD.isFoundMatch());
                assertTrue(carBD.isMetBudgetCriteria());
                assertTrue(carBD.isMetKilometerCriteria());
                assertFalse(carBD.isMetColorCriteria());
                break;

            case "192-73-091":
            case "193-72-061":
                assertFalse(carBD.isFoundIdealMatch());
                assertFalse(carBD.isFoundMatch());
                assertTrue(carBD.isMetBudgetCriteria());
                assertFalse(carBD.isMetKilometerCriteria());
                assertTrue(carBD.isMetColorCriteria());
                break;

            case "442-14-853":
                assertFalse(carBD.isFoundIdealMatch());
                assertFalse(carBD.isFoundMatch());
                assertFalse(carBD.isMetBudgetCriteria());
                assertTrue(carBD.isMetKilometerCriteria());
                assertFalse(carBD.isMetColorCriteria());
                break;

            case "937-42-558":
                assertTrue(carBD.isFoundIdealMatch());
                assertTrue(carBD.isFoundMatch());
                assertTrue(carBD.isMetBudgetCriteria());
                assertTrue(carBD.isMetKilometerCriteria());
                assertTrue(carBD.isMetColorCriteria());
                break;


        }

    }


}
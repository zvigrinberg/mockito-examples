package com.example.mockitotests.service;

import com.example.mockitotests.dao.CarsDBAccess;
import com.example.mockitotests.model.Car;
import com.example.mockitotests.model.CarBuyingDecision;

import java.util.List;
import java.util.stream.Collectors;

public class TestServiceImpl implements TestService {
    @Override
    public CarBuyingDecision getCarDetails(String company, int yearModel, String type, Integer maxBudget, boolean insistBudget, String preferredColor, boolean insistColor, Integer preferredMaxNumOfKilometers, boolean insistMaxNumOfKilometers) {
        CarsDBAccess carsDBAccess = new CarsDBAccess();
        CarBuyingDecision carBuyingDecision = new CarBuyingDecision();
        List<Car> cars = carsDBAccess.getAllCarsAccordingToCriteria(company, yearModel, type);
        List<Car> carsWithPreferences = cars.stream().filter(Car::isPrivateOwnership).filter(car -> car.getColor().equalsIgnoreCase(preferredColor)).filter(car -> car.getPrice() <= maxBudget).filter(car -> car.getNumOfKilometer() <= preferredMaxNumOfKilometers).collect(Collectors.toList());
        if (carsWithPreferences.size() > 0)
        {
          carBuyingDecision.setFoundMatch(true);
          carBuyingDecision.setFoundIdealMatch(true);
          carBuyingDecision.setChosenCar(carsWithPreferences);
        }

        else
        {
            if(cars.size()==0)
            {
                carBuyingDecision.setFoundMatch(false);
                carBuyingDecision.setFoundIdealMatch(false);
                carBuyingDecision.setChosenCar(List.of());
            }

            else
            {
                if(insistBudget)
                carBuyingDecision.setFoundMatch(true);
                carBuyingDecision.setFoundIdealMatch(false);
            }
        }

        return null;

    }
}

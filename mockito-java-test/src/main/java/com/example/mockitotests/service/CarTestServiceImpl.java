package com.example.mockitotests.service;

import com.example.mockitotests.dao.CarsDBAccess;
import com.example.mockitotests.dao.CarsDBAccessImpl;
import com.example.mockitotests.model.Car;
import com.example.mockitotests.model.CarBuyingDecision;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarTestServiceImpl implements CarTestService {
    private CarsDBAccess carsDBAccess = new CarsDBAccessImpl();
    @Override
    public List<CarBuyingDecision> getCarDetails(String company, int yearModel, Integer maxBudget, boolean insistBudget, String preferredColor, boolean insistColor, Integer preferredMaxNumOfKilometers, boolean insistMaxNumOfKilometers) {
        List<CarBuyingDecision> resultList = new ArrayList<>();

        List<Car> cars = carsDBAccess.getAllCarsAccordingToCriteria(company, yearModel);
        List<Car> carsWithPreferences = cars.stream().filter(car -> car.getColor().equalsIgnoreCase(preferredColor)).filter(car -> car.getPrice() <= maxBudget).filter(car -> car.getNumOfKilometer() <= preferredMaxNumOfKilometers).collect(Collectors.toList());
        Set<Car> carsSet = Set.of();
        if(carsWithPreferences.size() > 0) {
            carsSet = Set.copyOf(carsWithPreferences);
        }
//        carsSet.contains()

        for (Car car : carsWithPreferences) {
            resultList.add(new CarBuyingDecision(true,true,true,true,true,car));
        }
        for(Car car : cars)
        {
           if(!carsSet.contains(car))
           {
              boolean metColorCriteria =  preferredColor.equalsIgnoreCase(car.getColor());
              boolean metKilometerCriteria = preferredMaxNumOfKilometers >= car.getNumOfKilometer();
              boolean metBudgetCriteria = maxBudget >= car.getPrice();
              boolean foundMatch;
              boolean foundIdealMatch;
              if( (insistBudget && !metBudgetCriteria) || (insistColor && !metColorCriteria) || (insistMaxNumOfKilometers && !metKilometerCriteria))
              {
                  foundMatch = false;
                  foundIdealMatch = false;
              }

              else
              {
                  foundMatch = true;

                  if(metColorCriteria && metKilometerCriteria && metBudgetCriteria)
                       foundIdealMatch = true;
                  else
                      foundIdealMatch = false;

              }
               resultList.add(new CarBuyingDecision(metColorCriteria, metKilometerCriteria,metBudgetCriteria,foundMatch,foundIdealMatch,car));
           }

        }
        carsDBAccess.saveCarBuyingDecisionToDB(resultList);
        return resultList;

    }
}

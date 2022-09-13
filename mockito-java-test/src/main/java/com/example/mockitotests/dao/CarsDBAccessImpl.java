package com.example.mockitotests.dao;




import com.example.mockitotests.model.Car;
import com.example.mockitotests.model.CarBuyingDecision;

import java.util.ArrayList;
import java.util.List;

public class CarsDBAccessImpl implements CarsDBAccess {

    public List<Car> getAllCarsAccordingToCriteria(String company, Integer yearModel)
    {
        return List.of();
    }

    @Override
    public void saveCarBuyingDecisionToDB(List<CarBuyingDecision> buyingDecisions) {
         //TODO save decision to some external system/DB.
         System.out.print("TBD");
    }

}

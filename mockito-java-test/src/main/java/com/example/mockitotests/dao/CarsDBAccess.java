package com.example.mockitotests.dao;

import com.example.mockitotests.model.Car;
import com.example.mockitotests.model.CarBuyingDecision;

import java.util.List;

public interface CarsDBAccess {

    List<Car> getAllCarsAccordingToCriteria(String company, Integer yearModel);
    void saveCarBuyingDecisionToDB(List<CarBuyingDecision> buyingDecisions);
}

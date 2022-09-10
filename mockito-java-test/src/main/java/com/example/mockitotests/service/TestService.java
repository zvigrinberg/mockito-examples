package com.example.mockitotests.service;

import com.example.mockitotests.model.Car;
import com.example.mockitotests.model.CarBuyingDecision;

public interface TestService {

    CarBuyingDecision getCarDetails(String company, int yearModel, String type, Integer maxBudget, boolean insistBudget ,  String preferredColor, boolean insistColor, Integer preferredMaxNumOfKilometers, boolean insistMaxNumOfKilometers);
}

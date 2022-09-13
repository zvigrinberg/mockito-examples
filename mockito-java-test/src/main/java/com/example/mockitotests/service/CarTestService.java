package com.example.mockitotests.service;

import com.example.mockitotests.model.CarBuyingDecision;

import java.util.List;

public interface CarTestService {

    List<CarBuyingDecision> getCarDetails(String company, int yearModel, Integer maxBudget, boolean insistBudget , String preferredColor, boolean insistColor, Integer preferredMaxNumOfKilometers, boolean insistMaxNumOfKilometers);
}

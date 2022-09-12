package com.example.mockitotests.dao;

import java.util.List;

public interface CarsDBAccess {

    List<com.example.mockitotests.model.Car> getAllCarsAccordingToCriteria(String company, Integer yearModel, String type);
}

package com.example.mockitotests.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private String company;
    private Integer yearModel;
    private String color;
    private String licenseNumber;
    private Integer price;
    private boolean privateOwnership;
    private Integer numOfKilometer;

}

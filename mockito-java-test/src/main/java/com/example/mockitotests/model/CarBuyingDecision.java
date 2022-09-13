package com.example.mockitotests.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBuyingDecision {
    private boolean metColorCriteria;
    private boolean metKilometerCriteria;
    private boolean metBudgetCriteria;
    private boolean foundMatch;
    private boolean foundIdealMatch;
    private Car chosenCar;
}

package com.revisen.age.average.interfaces;

import java.util.TreeMap;

public interface AgeAverageCalculation {
    double calculateAverage(TreeMap<Integer, String> data);
    double calculateMinimumRange(double range);
    double calculateMaximumRange(double range);

    default double calculation(double range, double addValue) {
        return range + addValue;
    }
}

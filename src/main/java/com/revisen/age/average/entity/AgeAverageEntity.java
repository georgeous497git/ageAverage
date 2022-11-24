package com.revisen.age.average.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.TreeMap;

@Data
@Setter
@Getter
@Builder
public class AgeAverageEntity {

    private double average;
    private TreeMap<Integer, String> data;
}

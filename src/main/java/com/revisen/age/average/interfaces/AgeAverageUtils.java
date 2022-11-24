package com.revisen.age.average.interfaces;

import com.revisen.age.average.exception.AgeAverageException;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;


public interface AgeAverageUtils {
    <T extends Collection<String>, V extends Collection<Integer>> boolean isValidInput(T names, V ages) throws AgeAverageException;
    <T extends Collection<String>, V extends Collection<Integer>> TreeMap<Integer, String> normalizeInputs(T names, V ages);

    default <T extends Collection<String>, V extends Collection<Integer>> TreeMap<Integer, String> toMap(T names, V ages) {

        TreeMap<Integer, String> data = new TreeMap<>();

        if(Objects.isNull(names) || Objects.isNull(ages)) {
            return data;
        }

        Iterator<String> namesIterator = names.iterator();
        Iterator<Integer> agesIterator = ages.iterator();

        while (namesIterator.hasNext() && agesIterator.hasNext()) {
            data.put(agesIterator.next(), namesIterator.next());
        }

        return data;
    }
}

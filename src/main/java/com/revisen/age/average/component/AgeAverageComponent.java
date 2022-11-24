package com.revisen.age.average.component;

import com.revisen.age.average.exception.AgeAverageException;
import com.revisen.age.average.interfaces.AgeAverageCalculation;
import com.revisen.age.average.interfaces.AgeAverageUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeMap;

@Component
public class AgeAverageComponent implements AgeAverageCalculation, AgeAverageUtils {

    @Override
    public <T extends Collection<String>, V extends Collection<Integer>> boolean isValidInput(T names, V ages)
            throws AgeAverageException {

        if(Objects.nonNull(names) && Objects.nonNull(ages)) {
            return names.size() == ages.size();
        } else {
            throw new AgeAverageException("The inputs must not be null.");
        }
    }

    @Override
    public <T extends Collection<String>, V extends Collection<Integer>>
        TreeMap<Integer, String> normalizeInputs(T names, V ages) {

        // method 'toMap' used as default behavior/method (Stated Implementation) within an Interface
        return toMap(names, ages);
    }

    @Override
    public double calculateAverage(TreeMap<Integer, String> data) {
        if(Objects.isNull(data) || data.isEmpty()) {
            return 0d;
        }

        // There is a simple solution using IntStream but I want to show 'intermediate' lambda operations like mapToInt()
        // & 'terminal' lambda operations like sum()
        double total = data.keySet().stream().mapToInt(age -> age).sum();
        return total / data.size();
    }

    @Override
    public double calculateMinimumRange(double range) {
        return calculation(range, -5d);
    }

    @Override
    public double calculateMaximumRange(double range) {
        return calculation(range, 5d);
    }
}

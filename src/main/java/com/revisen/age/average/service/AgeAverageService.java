package com.revisen.age.average.service;

import com.revisen.age.average.component.AgeAverageComponent;
import com.revisen.age.average.configuration.AgeAverageConfiguration;
import com.revisen.age.average.entity.AgeAverageEntity;
import com.revisen.age.average.exception.AgeAverageException;
import com.revisen.age.average.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
@Service
public class AgeAverageService {

    @Autowired
    private AgeAverageComponent ageAverageComponent;
    @Autowired
    private AgeAverageConfiguration ageAverageConfiguration;

    public void initApp() throws AgeAverageException {

        List<SortedMap<Integer, String>> masterList = new ArrayList<>();
        for (AgeAverageConfiguration.Data inputData : ageAverageConfiguration.getData()) {
            List<String> names = inputData.getNames();
            List<Integer> ages = inputData.getAges();

            TreeMap<Integer, String> data = getData(names, ages);
            double average = executeCalculation(data);

            AgeAverageEntity ageAverageEntity = AgeAverageEntity.builder().data(data).average(average).build();
            SortedMap<Integer, String> outputData =
                    getRangeData(
                            getMinimumRange(ageAverageEntity.getAverage()),
                            getMaximumRange(ageAverageEntity.getAverage()),
                            ageAverageEntity
                    );
            masterList.add(outputData);
        }
        printResult(masterList);
    }

    private TreeMap<Integer, String> getData(List<String> names, List<Integer> ages) {
        log.info("Normalizing input data.");
        return ageAverageComponent.normalizeInputs(names, ages);
    }

    private double executeCalculation(TreeMap<Integer, String> data) {
        log.info("Calculating Average.");
        double avg = ageAverageComponent.calculateAverage(data);
        log.debug("The calculated average is: {}", avg);
        return avg;
    }

    private int getMinimumRange(double average) {
        double minimumRange = ageAverageComponent.calculateMinimumRange(average);
        log.debug("The calculated minimum range is: {}", minimumRange);
        return (int)minimumRange;
    }

    private int getMaximumRange(double average) {
        double maximumRange = ageAverageComponent.calculateMaximumRange(average);
        log.debug("The calculated maximum range is: {}", maximumRange);
        return (int)maximumRange;
    }

    private SortedMap<Integer, String> getRangeData(int startRange, int endRange, AgeAverageEntity entity) {
        log.debug("Subtracting data from {} to {}", startRange, endRange);
        return entity.getData().subMap(startRange, endRange);
    }

    private void printResult(List<SortedMap<Integer, String>> outputData) throws AgeAverageException {
        try {
            log.info("Writing processed data.");
            Utils.writeResultFiles("./outputData.txt", outputData);
        } catch (IOException io) {
            throw new AgeAverageException("Error found during fileWriting.");
        }
    }
}

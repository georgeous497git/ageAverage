package com.revisen.age.average.component;

import com.revisen.age.average.exception.AgeAverageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AgeAverageComponentTest {

    AgeAverageComponent ageAverageComponent;

    @BeforeEach
    public void setUp() {
        ageAverageComponent = new AgeAverageComponent();
    }

    @Test
    void isValidInputShouldReturnTrue() throws AgeAverageException {

        //Arrange
        List<String> names = Collections.singletonList("Jorge");
        List<Integer> ages = Collections.singletonList(35);

        //Act
        boolean isValid = ageAverageComponent.isValidInput(names, ages);

        //Assert
        assertTrue(isValid);
    }

    @Test
    void isValidInputShouldReturnFalse() throws AgeAverageException {

        //Arrange
        List<String> names = Arrays.asList("Jorge", "René");
        List<Integer> ages = Collections.singletonList(35);

        //Act
        boolean isValid = ageAverageComponent.isValidInput(names, ages);

        //Assert
        assertFalse(isValid);
    }

    @Test
    void isValidInputShouldThrownAgeAverageException() {

        //Arrange
        List<String> names = null;
        List<Integer> ages = null;

        //Act
        Exception exception = assertThrows(AgeAverageException.class, () -> {
            ageAverageComponent.isValidInput(names, ages);
        });

        String expectedMessage = "The inputs must not be null.";
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void calculateAverageShouldReturnZero() {
        //Arrange
        TreeMap<Integer, String> data = new TreeMap<>();

        //Act
        double avg = ageAverageComponent.calculateAverage(data);

        //Assert
        assertEquals(0d, avg);
    }

    @Test
    void calculateAverageShouldReturnAverage() {
        //Arrange
        TreeMap<Integer, String> data = new TreeMap<>();
        data.put(20, "Jorge");
        data.put(30, "Rene");
        data.put(40, "Alberto");

        //Act
        double avg = ageAverageComponent.calculateAverage(data);

        //Assert
        assertEquals(30d, avg);
    }

    @Test
    void normalizeInputsShouldReturnSortedData() {
        //Arrange
        List<String> names = Arrays.asList("Jorge", "Alberto", "Rene");
        List<Integer> ages = Arrays.asList(35, 75, 25);

        //Act
        TreeMap<Integer, String> data = ageAverageComponent.normalizeInputs(names, ages);

        //Assert
        assertEquals(25, data.firstEntry().getKey());
        assertEquals(75, data.lastEntry().getKey());

        assertEquals("Rene", data.firstEntry().getValue());
        assertEquals("Alberto", data.lastEntry().getValue());
    }

    @Test
    void normalizeInputsShouldReturnEmptyData() {
        //Arrange
        List<String> names = Collections.emptyList();
        List<Integer> ages = Collections.emptyList();

        //Act
        TreeMap<Integer, String> data = ageAverageComponent.normalizeInputs(names, ages);

        //Assert
        assertTrue(data.isEmpty());
    }
}
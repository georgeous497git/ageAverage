package com.revisen.age.average.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "input-data")
public class AgeAverageConfiguration {

    private List<Data> data;

    public static class Data {
        List<String> names;
        List<Integer> ages;

        public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }

        public List<Integer> getAges() {
            return ages;
        }

        public void setAges(List<Integer> ages) {
            this.ages = ages;
        }
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}

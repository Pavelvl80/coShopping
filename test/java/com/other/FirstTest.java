package com.other;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edvard Piri on 15.01.2017.
 */
public class FirstTest {

    public int sum(int a, int b) {
        return a + b;
    }

    public Country init(int delta) throws Exception {
        Country country = new Country("Ukraine", 45333);
        country.changePopulation(delta);
        return country;
    }

    @Test
    public void test_when_positive_number() {
        int actual = sum(10, 42);
        int expected = 52;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_change_population_when_error() {
        Integer n = 45000;
        try {
            Country country = init(333);
            Assert.assertEquals(country.getName(), "Ukraine");
            Assert.assertEquals(country.getPopulation(), n);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "error");
        }
    }

}

package com.testJunitCoreRunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Created by Edvard Piri on 05.04.2017.
 */
public class JUnitCoreRunnerExample {

    //call test url
    //http://localhost:8080/test/junit

    public Boolean jUnitTest() {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(AdControllerIntegrationTest.class);
        result.getFailures();
        return result.wasSuccessful();
    }
}

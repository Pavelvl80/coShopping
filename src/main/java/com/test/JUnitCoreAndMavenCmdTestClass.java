package com.test;

import com.testJunitCoreRunner.AdControllerIntegrationTest;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Edvard Piri on 01.04.2017.
 */
public class JUnitCoreAndMavenCmdTestClass {

    public void mavenCmdTest() throws Exception {

        ArrayList list = new ArrayList();
        Process proc = Runtime.getRuntime().exec("cmd.exe cd C:\\users\\edvard piri\\ideaprojects\\project mvn test");
        InputStream istr = proc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(istr));
        String str;
        while ((str = br.readLine()) != null)
            list.add(str);
        proc.waitFor();
        br.close();
    }

    public void mavenInvokerTest() throws Exception {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("C:\\Users\\Edvard Piri\\IdeaProjects\\enterpriseChecker\\pom.xml"));
        request.setGoals(Arrays.asList("integration-test"));

        Invoker invoker = new DefaultInvoker();
        invoker.execute(request);
    }
}

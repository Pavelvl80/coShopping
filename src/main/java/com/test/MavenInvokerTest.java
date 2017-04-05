package com.test;

import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Edvard Piri on 03.04.2017.
 */
public class MavenInvokerTest {
    // we will always call the same goals...
    private static final List<String> PUBLISH_GOALS = Arrays.asList("test");

    // define a field for the Invoker instance.
    private final Invoker invoker;

    // now, instantiate the invoker in the class constructor...
    public MavenInvokerTest() {
        Invoker newInvoker = new DefaultInvoker();
//        newInvoker.setLocalRepositoryDirectory(localRepositoryDir);

        this.invoker = newInvoker;
    }

    public static void main(String[] args) throws Exception {
        MavenInvokerTest mit = new MavenInvokerTest();
        mit.publishSite();
    }

    // this method will be called repeatedly, and fire off new builds...
    public void publishSite() throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
//       request.setBaseDirectory(siteDirectory);
//        request.setInteractive( false );
        request.setGoals(PUBLISH_GOALS);
        request.setPomFile(new File("C:\\Users\\Edvard Piri\\IdeaProjects\\enterpriseChecker\\pom.xml"));
        String str = request.getBuilder();

        InvocationResult result = invoker.execute(request);
        String str2 = request.getBuilder();

        if (result.getExitCode() != 0) {
            if (result.getExecutionException() != null) {
                throw new MavenInvocationException("Failed to publish site.",
                        result.getExecutionException());
            } else {
                throw new MavenInvocationException("Failed to publish site. Exit code: " +
                        result.getExitCode());
            }
        }
    }
}

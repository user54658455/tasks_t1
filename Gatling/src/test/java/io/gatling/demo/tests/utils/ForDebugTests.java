package io.gatling.demo.tests.utils;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.commonelements.Scenarios.scnLoginLogout;
import static io.gatling.demo.commonelements.HttpProtocol.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class ForDebugTests extends Simulation {

    {
        setUp(
                scnLoginLogout.injectClosed(
                        //rampConcurrentUsers(1).to(10).during(60),
                        constantConcurrentUsers(1).during(120)
                )
        ).protocols(httpProtocol).maxDuration(120);
    }

}
package io.gatling.demo.tests.utils;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.commonelements.HttpProtocol.httpProtocol;
import static io.gatling.demo.commonelements.Scenarios.*;
import static io.gatling.javaapi.core.OpenInjectionStep.*;

public class NewUsersCreation extends Simulation {

    //register amount of users, save to .csv, buy 5 tickets for each user
    int amount = 1; //value is 1 because inside scenario repeats itself 50 times
    {
        setUp(
                scnNewUserRegistrationAndSaveToCsv.injectOpen(atOnceUsers(amount))
                        .protocols(httpProtocol)
                        .disablePauses()
        );
    }

}
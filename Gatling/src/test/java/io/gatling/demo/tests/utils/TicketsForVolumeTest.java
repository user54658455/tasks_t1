package io.gatling.demo.tests.utils;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.commonelements.HttpProtocol.httpProtocol;
import static io.gatling.demo.commonelements.Scenarios.*;
import static io.gatling.javaapi.core.OpenInjectionStep.*;

public class TicketsForVolumeTest extends Simulation {

  //buy 45 tickets for volume test for last 15 existing users from .csv
  //TO DO need to rework feeder to automate row number, now it has to be set manually for every user (15 times)
  {
    setUp(
            scnNewUserRegistrationAndSaveToCsvForVolumeTest.injectOpen(atOnceUsers(1))
                    .protocols(httpProtocol).disablePauses()
    );
  }

}
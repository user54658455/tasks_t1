package io.gatling.demo.tests;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.commonelements.Scenarios.*;
import static io.gatling.demo.commonelements.HttpProtocol.httpProtocol;
import static io.gatling.demo.tests.utils.CommonConstants.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class ProfileVerifyTest extends Simulation {

    // profile verify test 20+2 minutes
    //int platoTime = 180;    //single step duration, uncomment if local value needed
    //int rampUpTime = 80;    //ramp up duration, uncomment if local value needed
    //int usersInitialCount = 0;  //initial amount of users, uncomment if local value needed
    {
        setUp(
                scnFlightPayment.injectClosed(
                        //rampConcurrentUsers(usersCountScnFlightPayment).to(10).during(60),   //start from 1 user, 1 minute
                        constantConcurrentUsers(usersCountScnFlightPayment).during(platoTime)
                ),
                scnItineraryDeleteOne.injectClosed(
                        //rampConcurrentUsers(usersCountScnItineraryDeleteOne).to(10).during(60),   //start from 1 user, 1 minute
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne).during(platoTime)
                ),
                scnNewUserRegistration.injectClosed(
                        //rampConcurrentUsers(usersCountScnNewUserRegistration).to(10).during(60),   //start from 1 user, 1 minute
                        constantConcurrentUsers(usersCountScnNewUserRegistration).during(platoTime)
                ),
                scnFlightsFindNoPayment.injectClosed(
                        //rampConcurrentUsers(usersCountScnFlightsFindNoPayment).to(10).during(60),   //start from 1 user, 1 minute
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment).during(platoTime)
                ),
                scnItinerary.injectClosed(
                        //rampConcurrentUsers(usersCountScnItinerary).to(10).during(60),   //start from 1 user, 1 minute
                        constantConcurrentUsers(usersCountScnItinerary).during(platoTime)
                ),
                scnLoginLogout.injectClosed(
                        //rampConcurrentUsers(usersCountScnLoginLogout).to(10).during(60),   //start from 1 user, 1 minute
                        constantConcurrentUsers(usersCountScnLoginLogout).during(platoTime)
                )
        ).protocols(httpProtocol).maxDuration(platoTime);
    }

}
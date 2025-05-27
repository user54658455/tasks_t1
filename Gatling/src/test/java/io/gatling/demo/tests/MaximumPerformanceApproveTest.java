package io.gatling.demo.tests;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.commonelements.Scenarios.*;
import static io.gatling.demo.commonelements.HttpProtocol.httpProtocol;
import static io.gatling.demo.tests.utils.CommonConstants.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class MaximumPerformanceApproveTest extends Simulation {

    //test to approve maximum performance
    int platoTime = 180;    //single step duration, uncomment if local value needed
    //int rampUpTime = 80;    //ramp up duration, uncomment if local value needed
    //int usersInitialCount = 0;  //initial amount of users, uncomment if local value needed

    {
        setUp(
                scnFlightPayment.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnFlightPayment).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnFlightPayment).to(usersCountScnFlightPayment*2).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*2).during(platoTime),
                        //step 3
                        rampConcurrentUsers(usersCountScnFlightPayment*2).to(usersCountScnFlightPayment*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*3).during(platoTime),
                        //step 4
                        rampConcurrentUsers(usersCountScnFlightPayment*3).to(usersCountScnFlightPayment*4).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*4).during(platoTime),
                        //step 5
                        rampConcurrentUsers(usersCountScnFlightPayment*4).to(usersCountScnFlightPayment*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*5).during(platoTime),
                        //step 6
                        rampConcurrentUsers(usersCountScnFlightPayment*5).to(usersCountScnFlightPayment*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*6).during(3720)
                ),
                scnItineraryDeleteOne.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnItineraryDeleteOne).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne).to(usersCountScnItineraryDeleteOne*2).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*2).during(platoTime),
                        //step 3
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*2).to(usersCountScnItineraryDeleteOne*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*3).during(platoTime),
                        //step 4
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*3).to(usersCountScnItineraryDeleteOne*4).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*4).during(platoTime),
                        //step 5
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*4).to(usersCountScnItineraryDeleteOne*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*5).during(platoTime),
                        //step 6
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*5).to(usersCountScnItineraryDeleteOne*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*6).during(3720)
                ),
                scnNewUserRegistration.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnNewUserRegistration).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnNewUserRegistration).to(usersCountScnNewUserRegistration*2).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*2).during(platoTime),
                        //step 3
                        rampConcurrentUsers(usersCountScnNewUserRegistration*2).to(usersCountScnNewUserRegistration*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*3).during(platoTime),
                        //step 4
                        rampConcurrentUsers(usersCountScnNewUserRegistration*3).to(usersCountScnNewUserRegistration*4).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*4).during(platoTime),
                        //step 5
                        rampConcurrentUsers(usersCountScnNewUserRegistration*4).to(usersCountScnNewUserRegistration*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*5).during(platoTime),
                        //step 6
                        rampConcurrentUsers(usersCountScnNewUserRegistration*5).to(usersCountScnNewUserRegistration*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*6).during(3720)
                ),
                scnFlightsFindNoPayment.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnFlightsFindNoPayment).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment).to(usersCountScnFlightsFindNoPayment*2).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*2).during(platoTime),
                        //step 3
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*2).to(usersCountScnFlightsFindNoPayment*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*3).during(platoTime),
                        //step 4
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*3).to(usersCountScnFlightsFindNoPayment*4).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*4).during(platoTime),
                        //step 5
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*4).to(usersCountScnFlightsFindNoPayment*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*5).during(platoTime),
                        //step 6
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*5).to(usersCountScnFlightsFindNoPayment*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*6).during(3720)
                ),
                scnItinerary.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnItinerary).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnItinerary).to(usersCountScnItinerary*2).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*2).during(platoTime),
                        //step 3
                        rampConcurrentUsers(usersCountScnItinerary*2).to(usersCountScnItinerary*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*3).during(platoTime),
                        //step 4
                        rampConcurrentUsers(usersCountScnItinerary*3).to(usersCountScnItinerary*4).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*4).during(platoTime),
                        //step 5
                        rampConcurrentUsers(usersCountScnItinerary*4).to(usersCountScnItinerary*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*5).during(platoTime),
                        //step 6
                        rampConcurrentUsers(usersCountScnItinerary*5).to(usersCountScnItinerary*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*6).during(3720)
                ),
                scnLoginLogout.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnLoginLogout).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnLoginLogout).to(usersCountScnLoginLogout*2).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*2).during(platoTime),
                        //step 3
                        rampConcurrentUsers(usersCountScnLoginLogout*2).to(usersCountScnLoginLogout*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*3).during(platoTime),
                        //step 4
                        rampConcurrentUsers(usersCountScnLoginLogout*3).to(usersCountScnLoginLogout*4).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*4).during(platoTime),
                        //step 5
                        rampConcurrentUsers(usersCountScnLoginLogout*4).to(usersCountScnLoginLogout*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*5).during(platoTime),
                        //step 6
                        rampConcurrentUsers(usersCountScnLoginLogout*5).to(usersCountScnLoginLogout*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*6).during(3720)
                )
        ).protocols(httpProtocol).maxDuration((rampUpTime+platoTime)* 5L + 3800);
    }

}
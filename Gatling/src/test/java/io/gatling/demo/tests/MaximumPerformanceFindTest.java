package io.gatling.demo.tests;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.commonelements.Scenarios.*;
import static io.gatling.demo.commonelements.HttpProtocol.httpProtocol;
import static io.gatling.demo.tests.utils.CommonConstants.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class MaximumPerformanceFindTest extends Simulation {

    //test to find maximum performance
    //int platoTime = 180;    //single step duration, uncomment if local value needed
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
                        constantConcurrentUsers(usersCountScnFlightPayment*6).during(platoTime),
                        //step 7
                        rampConcurrentUsers(usersCountScnFlightPayment*6).to(usersCountScnFlightPayment*7).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*7).during(platoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnFlightPayment*7).to(usersCountScnFlightPayment*8).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*8).during(platoTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnFlightPayment*8).to(usersCountScnFlightPayment*9).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*9).during(platoTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnFlightPayment*9).to(usersCountScnFlightPayment*10).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*10).during(platoTime)
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
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*6).during(platoTime),
                        //step 7
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*6).to(usersCountScnItineraryDeleteOne*7).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*7).during(platoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*7).to(usersCountScnItineraryDeleteOne*8).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*8).during(platoTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*8).to(usersCountScnItineraryDeleteOne*9).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*9).during(platoTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*9).to(usersCountScnItineraryDeleteOne*10).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*10).during(platoTime)
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
                        constantConcurrentUsers(usersCountScnNewUserRegistration*6).during(platoTime),
                        //step 7
                        rampConcurrentUsers(usersCountScnNewUserRegistration*6).to(usersCountScnNewUserRegistration*7).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*7).during(platoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnNewUserRegistration*7).to(usersCountScnNewUserRegistration*8).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*8).during(platoTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnNewUserRegistration*8).to(usersCountScnNewUserRegistration*9).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*9).during(platoTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnNewUserRegistration*9).to(usersCountScnNewUserRegistration*10).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*10).during(platoTime)
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
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*6).during(platoTime),
                        //step 7
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*6).to(usersCountScnFlightsFindNoPayment*7).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*7).during(platoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*7).to(usersCountScnFlightsFindNoPayment*8).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*8).during(platoTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*8).to(usersCountScnFlightsFindNoPayment*9).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*9).during(platoTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*9).to(usersCountScnFlightsFindNoPayment*10).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*10).during(platoTime)
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
                        constantConcurrentUsers(usersCountScnItinerary*6).during(platoTime),
                        //step 7
                        rampConcurrentUsers(usersCountScnItinerary*6).to(usersCountScnItinerary*7).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*7).during(platoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnItinerary*7).to(usersCountScnItinerary*8).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*8).during(platoTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnItinerary*8).to(usersCountScnItinerary*9).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*9).during(platoTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnItinerary*9).to(usersCountScnItinerary*10).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*10).during(platoTime)
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
                        constantConcurrentUsers(usersCountScnLoginLogout*6).during(platoTime),
                        //step 7
                        rampConcurrentUsers(usersCountScnLoginLogout*6).to(usersCountScnLoginLogout*7).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*7).during(platoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnLoginLogout*7).to(usersCountScnLoginLogout*8).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*8).during(platoTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnLoginLogout*8).to(usersCountScnLoginLogout*9).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*9).during(platoTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnLoginLogout*9).to(usersCountScnLoginLogout*10).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*10).during(platoTime)
                )
        ).protocols(httpProtocol).maxDuration((rampUpTime+platoTime)* 10L);
    }

}
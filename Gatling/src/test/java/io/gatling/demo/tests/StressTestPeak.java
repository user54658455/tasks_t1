package io.gatling.demo.tests;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.commonelements.ChainBuilders.*;
import static io.gatling.demo.commonelements.Feeders.*;
import static io.gatling.demo.commonelements.HttpProtocol.httpProtocol;
import static io.gatling.demo.tests.utils.CommonConstants.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class StressTestPeak extends Simulation {

    int stressPlatoTime = 420;    //stress step duration 7 minutes (1 + 5 + 1)
    int stressDownTime = 120;    //users decrease step duration
    int stressAfterTime = 900;    //after stress fades step duration

    int platoTime = 180;    //single step duration, uncomment if local value needed
    //int rampUpTime = 80;    //ramp up duration, uncomment if local value needed
    //int usersInitialCount = 0;  //initial amount of users, uncomment if local value needed


  //----------------------------------scenario builders specifically for StressTestPeak----------------------------------

  private ScenarioBuilder scnLoginLogoutSTP = scenario("scnLoginLogout")
          .exec(during(platoTime).on(pace(76).exec(
                  home_page,
                  feed(feederUsers),
                  login,
                  logout
          )));

  private ScenarioBuilder scnFlightPaymentSTP = scenario("scnFlightPayment")
          .exec(during(platoTime).on(pace(83).exec(
                  home_page,
                  feed(feederUsers),
                  login,
                  flights,
                  feed(feederDateDepartArrive),
                  flights_find,
                  choose_available_flight,
                  feed(feederCreditCard),
                  payment_details,
                  itinerary,
                  logout
          )));

  private ScenarioBuilder scnFlightsFindNoPaymentSTP = scenario("scnFlightsFindNoPayment")
          .exec(during(platoTime).on(pace(71).exec(
                  home_page,
                  feed(feederUsers),
                  login,
                  flights,
                  feed(feederDateDepartArrive),
                  flights_find,
                  choose_available_flight
          )));

  private ScenarioBuilder scnItinerarySTP = scenario("scnItinerary")
          .exec(during(platoTime).on(pace(121).exec(
                  home_page,
                  feed(feederUsers),
                  login,
                  flights,
                  itinerary,
                  logout
          )));

  private ScenarioBuilder scnItineraryDeleteOneSTP = scenario("scnItineraryDeleteOne")
          .exec(during(platoTime).on(pace(49).exec(
                  home_page,
                  feed(feederUsers),
                  login,
                  itinerary,
                  itinerary_delete,
                  logout
          )));

  private ScenarioBuilder scnNewUserRegistrationSTP = scenario("scnNewUserRegistration")
          .exec(during(platoTime).on(pace(36).exec(
                  home_page,
                  open_register_page,
                  feed(feederNewUserData),
                  fill_register_page,
                  next_page_after_register_completion
          )));


  //----------------------------------test----------------------------------

    {
        setUp(
                scnLoginLogoutSTP.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnLoginLogout*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*1).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnLoginLogout*1).to(usersCountScnLoginLogout*2).during(rampUpTime),
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
                        constantConcurrentUsers(usersCountScnLoginLogout*7).during(stressPlatoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnLoginLogout*7).to(usersCountScnLoginLogout*7).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*7).during(stressDownTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnLoginLogout*7).to(usersCountScnLoginLogout*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*6).during(stressDownTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnLoginLogout*6).to(usersCountScnLoginLogout*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*5).during(stressDownTime),
                        //step 11
                        rampConcurrentUsers(usersCountScnLoginLogout*5).to(usersCountScnLoginLogout*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnLoginLogout*1).during(stressAfterTime)
                ),

                scnFlightPaymentSTP.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnFlightPayment*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*1).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnFlightPayment*1).to(usersCountScnFlightPayment*2).during(rampUpTime),
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
                        constantConcurrentUsers(usersCountScnFlightPayment*7).during(stressPlatoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnFlightPayment*7).to(usersCountScnFlightPayment*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*5).during(stressDownTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnFlightPayment*5).to(usersCountScnFlightPayment*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*3).during(stressDownTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnFlightPayment*3).to(usersCountScnFlightPayment*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*1).during(stressDownTime),
                        //step 11
                        rampConcurrentUsers(usersCountScnFlightPayment*1).to(usersCountScnFlightPayment*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightPayment*1).during(stressAfterTime)
                ),

                scnFlightsFindNoPaymentSTP.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnFlightsFindNoPayment*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*1).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*1).to(usersCountScnFlightsFindNoPayment*2).during(rampUpTime),
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
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*7).during(stressPlatoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*7).to(usersCountScnFlightsFindNoPayment*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*5).during(stressDownTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*5).to(usersCountScnFlightsFindNoPayment*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*3).during(stressDownTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*3).to(usersCountScnFlightsFindNoPayment*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*3).during(stressDownTime),
                        //step 11
                        rampConcurrentUsers(usersCountScnFlightsFindNoPayment*3).to(usersCountScnFlightsFindNoPayment*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnFlightsFindNoPayment*1).during(stressAfterTime)
                ),

                scnItinerarySTP.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnItinerary*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*1).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnItinerary*1).to(usersCountScnItinerary*2).during(rampUpTime),
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
                        constantConcurrentUsers(usersCountScnItinerary*7).during(stressPlatoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnItinerary*7).to(usersCountScnItinerary*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*6).during(stressDownTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnItinerary*6).to(usersCountScnItinerary*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*6).during(stressDownTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnItinerary*6).to(usersCountScnItinerary*4).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*4).during(stressDownTime),
                        //step 11
                        rampConcurrentUsers(usersCountScnItinerary*4).to(usersCountScnItinerary*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItinerary*1).during(stressAfterTime)
                ),

                scnItineraryDeleteOneSTP.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnItineraryDeleteOne*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*1).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*1).to(usersCountScnItineraryDeleteOne*2).during(rampUpTime),
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
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*7).during(stressPlatoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*7).to(usersCountScnItineraryDeleteOne*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*6).during(stressDownTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*6).to(usersCountScnItineraryDeleteOne*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*5).during(stressDownTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*5).to(usersCountScnItineraryDeleteOne*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*3).during(stressDownTime),
                        //step 11
                        rampConcurrentUsers(usersCountScnItineraryDeleteOne*3).to(usersCountScnItineraryDeleteOne*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnItineraryDeleteOne*1).during(stressAfterTime)
                ),

                scnNewUserRegistrationSTP.injectClosed(
                        //step 1
                        rampConcurrentUsers(usersInitialCount).to(usersCountScnNewUserRegistration*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*1).during(platoTime),
                        //step 2
                        rampConcurrentUsers(usersCountScnNewUserRegistration*1).to(usersCountScnNewUserRegistration*2).during(rampUpTime),
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
                        constantConcurrentUsers(usersCountScnNewUserRegistration*7).during(stressPlatoTime),
                        //step 8
                        rampConcurrentUsers(usersCountScnNewUserRegistration*7).to(usersCountScnNewUserRegistration*6).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*6).during(stressDownTime),
                        //step 9
                        rampConcurrentUsers(usersCountScnNewUserRegistration*6).to(usersCountScnNewUserRegistration*5).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*5).during(stressDownTime),
                        //step 10
                        rampConcurrentUsers(usersCountScnNewUserRegistration*5).to(usersCountScnNewUserRegistration*3).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*3).during(stressDownTime),
                        //step 11
                        rampConcurrentUsers(usersCountScnNewUserRegistration*3).to(usersCountScnNewUserRegistration*1).during(rampUpTime),
                        constantConcurrentUsers(usersCountScnNewUserRegistration*1).during(stressAfterTime)
                )
        ).protocols(httpProtocol);
    }

}
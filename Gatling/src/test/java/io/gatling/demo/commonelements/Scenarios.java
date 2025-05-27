package io.gatling.demo.commonelements;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.demo.commonelements.ChainBuilders.*;
import static io.gatling.demo.commonelements.ChainBuilders.logout;
import static io.gatling.demo.commonelements.Feeders.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.feed;

public class Scenarios {

    public static ScenarioBuilder scnLoginLogout = scenario("scnLoginLogout")
            .exec(
                    forever().on(
                            pace(76)
                                    .exec(
                                            home_page,
                                            feed(feederUsers),
                                            login,
                                            logout
                                    )
                    )
            );

    public static ScenarioBuilder scnFlightPayment = scenario("scnFlightPayment")
            .exec(
                    forever().on(
                            pace(83)
                                    .exec(
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
                                    )
                    )
            );

    public static ScenarioBuilder scnFlightsFindNoPayment = scenario("scnFlightsFindNoPayment")
            .exec(
                    forever().on(
                            pace(71)
                                    .exec(
                                            home_page,
                                            feed(feederUsers),
                                            login,
                                            flights,
                                            feed(feederDateDepartArrive),
                                            flights_find,
                                            choose_available_flight
                                    )
                    )
            );

    public static ScenarioBuilder scnItinerary = scenario("scnItinerary")
            .exec(
                    forever().on(
                            pace(121)
                                    .exec(
                                            home_page,
                                            feed(feederUsers),
                                            login,
                                            flights,
                                            itinerary,
                                            logout
                                    )
                    )
            );

    public static ScenarioBuilder scnItineraryDeleteOne = scenario("scnItineraryDeleteOne")
            .exec(
                    forever().on(
                            pace(49)
                                    .exec(
                                            home_page,
                                            feed(feederUsers),
                                            login,
                                            itinerary,
                                            itinerary_delete,
                                            logout
                                    )
                    )
            );

    public static ScenarioBuilder scnNewUserRegistration = scenario("scnNewUserRegistration")
            .exec(
                    forever().on(
                            pace(36)
                                    .exec(
                                            home_page,
                                            open_register_page,
                                            feed(feederNewUserData),
                                            fill_register_page,
                                            next_page_after_register_completion
                                    )
                    )
            );

    public static ScenarioBuilder scnNewUserRegistrationAndSaveToCsv = scenario("scnNewUserRegistrationAndSaveToCsv")
            .exec(repeat(50).on(
                    home_page,
                    open_register_page,
                    feed(feederNewUserDataSaveToCsv),
                    fill_register_page,
                    next_page_after_register_completion,
                    repeat(5).on(
                            flights,
                            feed(feederDateDepartArrive),
                            flights_find,
                            choose_available_flight,
                            feed(feederCreditCard),
                            payment_details
                    ),
                    logout
                    )
            );

    //buy 45 tickets for volume test for last 15 existing users from .csv
    public static ScenarioBuilder scnNewUserRegistrationAndSaveToCsvForVolumeTest = scenario("scnNewUserRegistrationAndSaveToCsv")
            .exec(
                    home_page,
                    feed(feederUsersForVolumeTest),
                    login,
                    repeat(45).on(  //buy 45 tickets for same user to match volume test requirements
                            flights,
                            feed(feederDateDepartArrive),
                            flights_find,
                            choose_available_flight,
                            feed(feederCreditCard),
                            payment_details
                            //pause(1)    //pause for 1 sec after each ticket
                    ),
                    logout
            );

}
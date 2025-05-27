package io.gatling.demo.commonelements;

import io.gatling.javaapi.core.ChainBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.gatling.demo.commonelements.Headers.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class ChainBuilders {

    public final static ChainBuilder home_page = group("home_page")
            .on(
                    // home_page,
                    http("/WebTours/")
                            .get("/WebTours/")
                            .headers(headers_0)
                            .resources(
                                    http("/WebTours/header.html")
                                            .get("/WebTours/header.html")
                                            .headers(headers_1)
                            ),
                    http("/cgi-bin/welcome.pl")
                            .get("/cgi-bin/welcome.pl?signOff=true")
                            //.get("/cgi-bin/welcome.pl?signOff=1")
                            .headers(headers_2),
                    //.check(header("Set-Cookie").saveAs("sessionId"))
                    //.check(header("Set-Cookie").findAll().saveAs("all-Set-Cookie")),
                    http("/cgi-bin/nav.pl")
                            .get("/cgi-bin/nav.pl?in=home")
                            .headers(headers_3)
                            .check(regex("name=\"userSession\" value=\"(.+?)\"").saveAs("userSession")),
                    //pause(10)
                    //pause(3)
                    pause(3,4)
            );

    public final static ChainBuilder login = group("login")
            .on(
                    // login,
                    //addCookie(Cookie("Cookie", "MSO=SID&1745368669")),
                    http("/cgi-bin/login.pl")
                            .post("/cgi-bin/login.pl")
                            .headers(headers_4)
                            .formParam("userSession", "#{userSession}")
                            //.formParam("username", "jojo")
                            .formParam("username", "#{username}")
                            //.formParam("password", "bean")
                            .formParam("password", "#{password}")
                            .formParam("login.x", "56")
                            .formParam("login.y", "8")
                            .formParam("JSFormSubmit", "off")
                            .check(substring("User password was correct")),
                    http("/cgi-bin/nav.pl")
                            .get("/cgi-bin/nav.pl?page=menu&in=home")
                            .headers(headers_5),
                    http("/cgi-bin/login.pl")
                            .get("/cgi-bin/login.pl?intro=true")
                            .headers(headers_5)
                            .resources(
                                    http("/WebTours/images/flights.gif")
                                            .get("/WebTours/images/flights.gif")
                                            .headers(headers_6),
                                    http("/WebTours/images/signoff.gif")
                                            .get("/WebTours/images/signoff.gif")
                                            .headers(headers_8),
                                    http("/WebTours/images/in_home.gif")
                                            .get("/WebTours/images/in_home.gif")
                                            .headers(headers_6),
                                    http("/WebTours/images/itinerary.gif")
                                            .get("/WebTours/images/itinerary.gif")
                                            .headers(headers_6)
                            ),
                    //pause(7)
                    pause(3,4)
            );

    public final static ChainBuilder logout = group("logout")
            .on(
                    // logout,
                    http("/cgi-bin/welcome.pl")
                            .get("/cgi-bin/welcome.pl?signOff=1")
                            .headers(headers_11)
                            .check(substring("A Session ID has been created and loaded into a cookie called MSO.")),
                    http("/cgi-bin/nav.pl")
                            .get("/cgi-bin/nav.pl?in=home")
                            .headers(headers_12)
                    //pause(3,4)
            );

    public final static ChainBuilder flights = group("flights")
            .on(
                    exec(
                            // flights,
                            http("/cgi-bin/welcome.pl")
                                    .get("/cgi-bin/welcome.pl?page=search")
                                    .headers(headers_13),
                            http("/cgi-bin/nav.pl")
                                    .get("/cgi-bin/nav.pl?page=menu&in=flights")
                                    .headers(headers_14),
                            http("/cgi-bin/reservations.pl")
                                    .get("/cgi-bin/reservations.pl?page=welcome")
                                    .headers(headers_14)
                                    //value=\"(.+?)\">.+?</option>
                                    .check(regex("(name=\"depart\" >(\n|.)*?</select>)").saveAs("departCitiesString"))
                                    .check(regex("(name=\"arrive\" >(\n|.)*?</select>)").saveAs("arriveCitiesString"))
                                    .check(regex("name=\"seatPref\" value=\"(.+?)\"").findRandom().saveAs("seatPref"))
                                    .check(regex("name=\"seatType\" value=\"(.+?)\"").findRandom().saveAs("seatType")),
                            //pause(22)
                            pause(3,4)
                    )
                            .exec(session -> {
                                String cityRegex = "value=\"(.+?)\">.+?</option>";

                                String departCitiesStringLocal = session.getString("departCitiesString").toString();
                                Pattern cityPatternDepart = Pattern.compile(cityRegex);
                                Matcher cityMatcherDepart = cityPatternDepart.matcher(departCitiesStringLocal);
                                List<String> citiesDepart = new ArrayList<>();
                                while (cityMatcherDepart.find()) {
                                    citiesDepart.add(cityMatcherDepart.group(1));
                                }
/*                              for (String citiD : citiesDepart) {
                                  System.out.println("citiesDepart: " + citiD);
                              }*/

                                String arriveCitiesStringLocal = session.getString("arriveCitiesString").toString();
                                Pattern cityPatternArrive = Pattern.compile(cityRegex);
                                Matcher cityMatcherArrive = cityPatternArrive.matcher(arriveCitiesStringLocal);
                                List<String> citiesArrive = new ArrayList<>();
                                while (cityMatcherArrive.find()) {
                                    citiesArrive.add(cityMatcherArrive.group(1));
                                }
/*                              for (String citiA : citiesArrive) {
                                  System.out.println("citiesArrive: " + citiA);
                              }*/

                                String depart = citiesDepart.get(new Random().nextInt(citiesDepart.size()));
                                String arrive = citiesArrive.get(new Random().nextInt(citiesArrive.size()));

                                //could be an infinite cycle if "depart" and "arrive" are only one and equals value
                                //better to rewrite with checks
                                while (depart.equals(arrive)) {
                                    arrive = citiesArrive.get(new Random().nextInt(citiesArrive.size()));
                                };

                                return session.set("depart", depart).set("arrive", arrive);
                            })
            );

    public final static ChainBuilder flights_find = group("flights_find")
            .on(
                    // flights_find,
                    http("/cgi-bin/reservations.pl")
                            .post("/cgi-bin/reservations.pl")
                            .headers(headers_15)
                            .body(ElFileBody("data/0010_request.html"))
                            .check(regex("name=\"outboundFlight\" value=\"(.+?)\"").findRandom().saveAs("outboundFlight")),
                    //pause(14)
                    pause(3,4)
            );

    public final static ChainBuilder choose_available_flight = group("choose_available_flight")
            .on(
                    // choose_available_flight,
                    http("/cgi-bin/reservations.pl")
                            .post("/cgi-bin/reservations.pl")
                            .headers(headers_16)
                            .body(ElFileBody("data/0011_request.html"))
                            .check(substring("name=\"outboundFlight\" value=\"#{outboundFlight}")),
                    //pause(57)
                    pause(3,4)
            );

    public final static ChainBuilder payment_details = group("payment_details")
            .on(
                    // payment_details,
                    http("/cgi-bin/reservations.pl")
                            .post("/cgi-bin/reservations.pl")
                            .headers(headers_17)
                            .body(ElFileBody("data/0012_request.html"))
                            .check(substring("<b>#{name}#{lastname}'s Flight Invoice</b>"))
                            .check(substring("Total Charged to Credit Card # #{cardNum}"))
                            .check(substring("A #{seatType} Class ticket"))
                            .check(substring("leaves #{depart}  for #{arrive}"))
                            .resources(
                                    http("/WebTours/images/bookanother.gif")
                                            .get("/WebTours/images/bookanother.gif")
                                            .headers(headers_18)
                            ),
                    //pause(13)
                    pause(3,4)
            );

    public final static ChainBuilder itinerary = group("itinerary")
            .on(
                    // itinerary,
                    http("/cgi-bin/welcome.pl")
                            .get("/cgi-bin/welcome.pl?page=itinerary")
                            .headers(headers_19),
                    http("/cgi-bin/itinerary.pl")
                            .get("/cgi-bin/itinerary.pl")
                            .headers(headers_20)
                            //.check(regex("name=\"flightID\" value=\"(.+?)\"").saveAs("flightID"))
                            .check(regex("name=\"flightID\" value=\"(.+?)\"").findAll().saveAs("flightIDs")),
                    http("/cgi-bin/nav.pl")
                            .get("/cgi-bin/nav.pl?page=menu&in=itinerary")
                            .headers(headers_20)
                            .resources(
                                    http("/WebTours/images/in_itinerary.gif")
                                            .get("/WebTours/images/in_itinerary.gif")
                                            .headers(headers_21),
                                    http("/WebTours/images/cancelreservation.gif")
                                            .get("/WebTours/images/cancelreservation.gif")
                                            .headers(headers_22),
                                    http("/WebTours/images/cancelallreservations.gif")
                                            .get("/WebTours/images/cancelallreservations.gif")
                                            .headers(headers_22)
                            ),
                    //pause(27)
                    pause(3,4)
            );

    public final static ChainBuilder itinerary_delete = group("itinerary_delete")
            .on(
                    // itinerary_delete,
                    exec(session -> {
                        List<String> flightIDs = session.getList("flightIDs");

                        if (/*flightIDs == null || */flightIDs.isEmpty()) {
                            System.out.println("No flights found to cancel");
                            return session;
                        }

                        // add constant parameters to body
                        StringBuilder formBody = new StringBuilder();
                        formBody.append("------geckoformboundaryb16b90ce75a72f82b47476819fb85059\r\n");
                        formBody.append("Content-Disposition: form-data; name=\"1\"\r\n\r\non\r\n");

                        // add each flightID to body
                        for (String flightID : flightIDs) {
                            formBody.append("------geckoformboundaryb16b90ce75a72f82b47476819fb85059\r\n");
                            formBody.append("Content-Disposition: form-data; name=\"flightID\"\r\n\r\n").append(flightID).append("\r\n");
                        }

                        // add some more constant parameters to body
                        formBody.append("------geckoformboundaryb16b90ce75a72f82b47476819fb85059\r\n");
                        formBody.append("Content-Disposition: form-data; name=\"removeFlights.x\"\r\n\r\n1\r\n");
                        formBody.append("------geckoformboundaryb16b90ce75a72f82b47476819fb85059\r\n");
                        formBody.append("Content-Disposition: form-data; name=\"removeFlights.y\"\r\n\r\n1\r\n");

                        // add cgifields to body to mach real requests
                        int i = 1;
                        for (String flightID : flightIDs) {
                            formBody.append("------geckoformboundaryb16b90ce75a72f82b47476819fb85059\r\n");
                            formBody.append("Content-Disposition: form-data; name=\".cgifields\"\r\n\r\n" + i + "\r\n");
                            i++;
                        }
                        // add final constant parameter to body
                        formBody.append("------geckoformboundaryb16b90ce75a72f82b47476819fb85059--\r\n");

                        return session.set("itineraryDeleteBody", formBody.toString());
                    })
                            .exec(http("/cgi-bin/itinerary.pl")
                                            .post("/cgi-bin/itinerary.pl")
                                            .headers(headers_23)
                                            .body(StringBody("#{itineraryDeleteBody}"))
                                    //.check(substring("Itinerary Updated"))
                            ),
                    //pause(9)
                    pause(3,4)
            );

    public final static ChainBuilder open_register_page = group("open_register_page")
            .on(
                    // open_register_page,
                    http("/cgi-bin/login.pl")
                            .get("/cgi-bin/login.pl?username=&password=&getInfo=true")
                            .headers(headers_24),
                    //pause(39)
                    pause(3,4)
            );

    public final static ChainBuilder fill_register_page = group("fill_register_page")
            .on(
                    exec(session -> {
                        //some code (what i want to return from session i pass to variable #{})
                        return session;
                    })
                            .exec(
                                    // fill_register_page,
                                    http("/cgi-bin/login.pl")
                                            .post("/cgi-bin/login.pl")
                                            .headers(headers_25)
                                            .body(ElFileBody("data/0005_request.html"))
                                            .check(substring("Thank you, <b>#{newUsername}</b>, for registering and welcome to the Web Tours family.")),
                                    //pause(13)
                                    pause(3,4)
                            )
            );

    public final static ChainBuilder next_page_after_register_completion = group("next_page_after_register_completion")
            .on(
                    // next_page_after_register_completion,
                    http("/cgi-bin/welcome.pl")
                            .get("/cgi-bin/welcome.pl?page=menus")
                            .headers(headers_26),
                    http("/cgi-bin/nav.pl")
                            .get("/cgi-bin/nav.pl?page=menu&in=home")
                            .headers(headers_27),
                    http("/cgi-bin/login.pl")
                            .get("/cgi-bin/login.pl?intro=true")
                            .headers(headers_27)
                            .resources(
                                    http("/WebTours/images/flights.gif")
                                            .get("/WebTours/images/flights.gif")
                                            .headers(headers_29),
                                    http("/WebTours/images/in_home.gif")
                                            .get("/WebTours/images/in_home.gif")
                                            .headers(headers_29)
                            ),
                    pause(3,4)
            );

}
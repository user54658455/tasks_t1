package io.gatling.demo.tests.utils;

public class CommonConstants {

    //amount of users for a single step scenario wise
    public static int usersCountScnFlightPayment = 4;
    public static int usersCountScnItineraryDeleteOne = 1;
    public static int usersCountScnNewUserRegistration = 1;
    public static int usersCountScnFlightsFindNoPayment = 2;
    public static int usersCountScnItinerary = 1;
    public static int usersCountScnLoginLogout = 1;
    public static int usersInitialCount = 0;  //initial amount of users

    //timings for steps and ramp ups
    public static int platoTime = 1320;    //single step duration
    public static int rampUpTime = 80;    //ramp up duration

}
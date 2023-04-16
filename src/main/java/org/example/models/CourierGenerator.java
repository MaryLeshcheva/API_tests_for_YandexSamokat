package org.example.models;

import static org.example.RandomGenerator.randomString;

public class CourierGenerator {
    public static Courier generateRandomCourier() {
        Courier courier = new Courier();
        courier.setLogin(randomString(8));
        courier.setPassword(randomString(10));
        courier.setFirstName(randomString(11));
        return courier;
    }
}

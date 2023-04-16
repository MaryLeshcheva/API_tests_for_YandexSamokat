package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Config;
import org.example.models.Courier;
import org.example.models.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierSteps extends Config {

    @Step("Создание нового курьера")
    public static ValidatableResponse create(Courier courier) {
        return given()
                .log().all()
                .spec(getRequestSpec())
                .body(courier)
                .when()
                .post(COURIER_URL).then();
    }

    @Step("Авторизация курьера")
    public static ValidatableResponse login(Courier courier) {
        CourierCredentials courierCredentials = new CourierCredentials(courier.getLogin(), courier.getPassword());

        return given()
                .log().all()
                .spec(getRequestSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_URL + "login").then();
    }

    @Step("Удаление курьера")
    public static ValidatableResponse delete(int courierId) {
        return given()
                .log().all()
                .spec(getRequestSpec())
                .when()
                .delete(COURIER_URL + courierId).then();
    }
}

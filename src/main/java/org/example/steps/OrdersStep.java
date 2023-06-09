package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Config;
import org.example.models.Order;

import static io.restassured.RestAssured.given;

public class OrdersStep extends Config {
    @Step("Создание заказа")
    public static ValidatableResponse createOrder(Order order) {
        return given()
                .log().all()
                .spec(getRequestSpec())
                .body(order)
                .when()
                .post(ORDERS_URL).then();
    }

    @Step("Получение списка заказов")
    public static ValidatableResponse getOrders() {
        return given()
                .log().all()
                .spec(getRequestSpec())
                .when()
                .get(ORDERS_URL).then();
    }
}

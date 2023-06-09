import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.example.models.Courier;
import org.example.models.CourierGenerator;
import org.example.steps.CourierSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest {
    Courier courier;
    Integer id;

    @Before
    public void setUp() {
        courier = CourierGenerator.generateRandomCourier();
        CourierSteps.create(courier);
        id = CourierSteps.login(courier).extract().path("id");
    }

    @After
    public void cleanUp() {
        if (id != null) {
            CourierSteps.delete(id);
        }
    }

    @Test
    @DisplayName("Авторизация курьера в системе")
    public void successfullyLoginTest() {
        ValidatableResponse response = CourierSteps.login(courier);
        response
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("id", is(id));
    }

    @Test
    @DisplayName("Авторизация c несуществующей парой логин-пароль")
    public void loginWithNonExistentCourierTest() {
        courier.setLogin("Anna12");
        courier.setPassword("annushka");
        ValidatableResponse response = CourierSteps.login(courier);
        response
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }
    @Test
    @DisplayName("Авторизация c неверным логином")
    public void loginWithInvalidLoginTest() {
        courier.setLogin("Lalala");
        ValidatableResponse response = CourierSteps.login(courier);
        response.statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Авторизация c неверным паролем")
    public void loginWithInvalidPasswordTest() {
        courier.setPassword("Lololo");
        ValidatableResponse response = CourierSteps.login(courier);
        response.statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Авторизация без пароля")
    public void loginWithoutPasswordTest() {
        courier.setPassword(null);
        ValidatableResponse response = CourierSteps.login(courier);
        response.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Авторизация без логина")
    public void loginWithoutLoginTest() {
        courier.setLogin(null);
        ValidatableResponse response = CourierSteps.login(courier);
        response.statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}

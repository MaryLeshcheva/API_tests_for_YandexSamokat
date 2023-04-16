import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.example.models.Courier;
import org.example.models.CourierGenerator;
import org.example.steps.CourierSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class CreateCourierTest {
    Courier courier;

    Integer id;

    @Before
    public void setUp() {
        courier = CourierGenerator.generateRandomCourier();
    }

    @After
    public void cleanUp() {
        if (id != null) {
            CourierSteps.delete(id);
        }
    }

    @Test
    @DisplayName("Создание нового курьера")
    public void successfullyCourierCreationTest() {
        ValidatableResponse response = CourierSteps.create(courier);
        response
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat().body("ok", is(true));
        id = CourierSteps.login(courier).extract().path("id");
    }

    @Test
    @DisplayName("Создание курьера с дублирующимся логином")
    public void duplicateCourierCreationTest() {
        CourierSteps.create(courier);
        id = CourierSteps.login(courier).extract().path("id");
        courier.setPassword("marina");
        ValidatableResponse response = CourierSteps.create(courier);
        response.statusCode(HttpStatus.SC_CONFLICT);
    }

    @Test
    @DisplayName("Создание нового курьера без логина")
    public void courierCreationWithoutLoginTest() {
        courier.setLogin(null);
        ValidatableResponse response = CourierSteps.create(courier);
        response.statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void courierCreationWithoutPasswordTest() {
        courier.setPassword(null);
        ValidatableResponse response = CourierSteps.create(courier);
        response.statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}

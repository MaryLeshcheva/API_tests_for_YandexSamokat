import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.example.steps.OrdersStep;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest {
    @Test
    @DisplayName("Получение списка всех заказов")
    public void getAllOrdersTest() {
        ValidatableResponse response = OrdersStep.getOrders();
        response.statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("orders", notNullValue());
    }
}

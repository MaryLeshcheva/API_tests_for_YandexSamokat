import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.example.models.Order;
import org.example.steps.OrdersStep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
@RunWith(Parameterized.class)
public class CreateOrderTest {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime,
                                        String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters()
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Илюшка", "Илюшин", "ул.Ленина дом 15", "Сокол", "8 888 888 88 88", 1, "02-04-2023",
                        "Все добра!", List.of("Gray")},
                {"Анютка", "Анина", "улица Чехова дом 1", "Аэропорт", "+7 777 777 77 77", 6, "10-04-2023",
                        "", List.of("Black")},
                {"Lin", "Pin", "Sun 15", "Тверская", "81234568911", 2, "11-04-2023",
                        "null", List.of("Black", "Gray")},
                {"мари", "мишель", "Ёлкино 99", "Фили", "5555555555", 6, "14-04-2023",
                        "вы супер!", null},
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void successfullyCreateOrderWitchValidDataTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = OrdersStep.createOrder(order);
        response.statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("track", notNullValue());
    }
}

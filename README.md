API тесты для Яндекс Самокат. Учебный проект

API тесты для [Яндекс Самокат](http://qa-scooter.praktikum-services.ru/). [Документация](https://qa-scooter.praktikum-services.ru/docs/) на API приложения.

- Тесты написаны для ручек: создание курьера, логин курьера, создание заказа, возвращение списка заказов.
- В maven подключены библиотеки: JUnit 4, Allure, rest-assured, gson. Настроен на работу с Java 11.

Команды: 
- для запуска тестовых сценариев: mvn clean test
- для формирования Allure отчёта: mvn allure:serve
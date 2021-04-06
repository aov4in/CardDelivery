package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.*;
import static java.time.format.DateTimeFormatter.ofPattern;


public class CardDeliveryTest {

    @Test
    void shouldTestCardDelivery(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        Configuration.timeout = 11000;
        $(Selectors.byClassName("notification__title")).shouldBe(Condition.visible);
    }

    @Test
    void shouldTestCardDeliveryNoCity(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestCardDeliveryCityNameForEngland(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ekaterinburg");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldBe(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldTestCardDeliveryCityUnavailableDate(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(1).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id='date'] .input__sub").shouldBe(Condition.text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldTestCardDeliveryNoDate(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id='date'] .input__sub").shouldBe(Condition.text("Неверно введена дата"));
    }

    @Test
    void shouldTestCardDeliveryNoName(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestCardDeliveryBadName(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Andreev Andrei");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldBe(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestCardDeliveryBadNameOrSymbol(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("123123");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldBe(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestCardDeliveryCityNameOrOtherSymbol(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("123123");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldBe(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldTestCardDeliveryNoPlusPhone(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("12345678911");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub").shouldBe(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestCardDeliveryNoPhone(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub").shouldBe(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestCardDeliveryNotEnoughSymbolPhone(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+12345");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub").shouldBe(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestCardDeliveryManySymbolPhone(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+1234567891234567");
        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub").shouldBe(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestCardDeliveryNoCheckBox(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        SelenideElement dateForm = $("[placeholder='Дата встречи']");
        dateForm.click();
        dateForm.sendKeys(Keys.CONTROL, "a");
        dateForm.sendKeys(Keys.BACK_SPACE);
        String dateOfMeeting = LocalDate.now().plusDays(3).format(ofPattern("dd.MM.yyyy"));
        dateForm.setValue(dateOfMeeting);
        $("[data-test-id=name] input").setValue("Андреев Андрей");
        $("[data-test-id=phone] input").setValue("+1234567891234567");
//        $(Selectors.byClassName("checkbox__box")).click();
        $(Selectors.withText("Забронировать")).click();
        $(Selectors.byClassName("checkbox__text")).shouldBe(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

}

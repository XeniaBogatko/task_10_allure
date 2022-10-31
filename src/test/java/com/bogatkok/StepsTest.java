package com.bogatkok;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @BeforeAll
    static void loggerStart(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Feature("Issue tab")
    @Story("Issue search with Lambda method")
    @Owner("xenia bogatko")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Issue search by the number")
    @Link(value = "Testing site", url = "https://github.com/eroshenkoam/allure-example/issues")
    public void testLambdaStep() {

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @Feature("Issue tab")
    @Story("Issue search with annotated steps method")
    @Owner("xenia bogatko")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Issue search by the number")
    @Link(value = "Testing site", url = "https://github.com/eroshenkoam/allure-example/issues")
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);

    }

}
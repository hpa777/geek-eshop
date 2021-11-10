package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class Steps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenWebBrowser() {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login\\.html page$")
    public void iNavigateToLoginHtmlPage() {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }

    @When("I navigate to product page")
    public void iNavigateToProductPage() throws InterruptedException {
        webDriver.get(DriverInitializer.getProperty("front.url"));
        Thread.sleep(2000);
    }

    @When("I click on element with id {string}")
    public void iClickOnElementWithId(String id) {
        WebElement webElement = webDriver.findElement(By.id(id));
        webElement.click();
    }

    @When("I provide textField with id {string} as {string}")
    public void iProvideTextField(String id, String value) {
        WebElement webElement = webDriver.findElement(By.id(id));
        webElement.sendKeys(value);
    }

    @Then("name should be {string}")
    public void nameShouldBe(String name) {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText()).isEqualTo(name);
    }

    @Then("^user logged out$")
    public void userLoggedOut() {
        webDriver.findElement(By.id("username"));
        webDriver.findElement(By.id("password"));
    }

    @Given("^I navigtate to category page$")
    public void iNavigateToCategoryPage() {
        WebElement webElement = webDriver.findElement(By.cssSelector("#navbarSupportedContent > ul > li:nth-child(2) > a"));
        webElement.click();
    }

    @Then("check category {string}")
    public void checkAddCategory(String categoryname) {
        webDriver.findElement(By.cssSelector("body > div > div > div.col-4.page-size > ul > li.page-item:last-child > a")).click();
        boolean flag = false;
        for (WebElement row:
                webDriver.findElements(By.cssSelector("body > div > div > div:nth-child(2) > table > tbody > tr"))) {
            if (row.getText().contains(categoryname)) {
                flag = true;
                row.findElement(By.cssSelector(".btn-danger")).click();
                break;
            }
        }
        assertEquals(true, flag);
    }

    private String productName;

    @When("I add the product to the cart")
    public void iAddProductToCart() throws InterruptedException {
        WebElement card = webDriver.findElement(By.cssSelector(".card"));
        productName = card.findElement(By.cssSelector(".card-title")).getText();
        System.out.println(productName);
        WebElement button = card.findElement(By.cssSelector(".btn-primary:last-child"));
        button.click();
        Thread.sleep(2000);
    }

    @Then("check product in the cart")
    public void checkProduct() {
        webDriver.findElement(By.xpath("/html/body/app-root/app-nav-bar/nav/div/div/ul/li[2]/a")).click();
        boolean flag = false;
        for (WebElement row:
                webDriver.findElements(By.cssSelector("body > app-root > div > app-cart-page > div > div.col-md-9.col-xs-12.mb-3 > table > tbody > tr"))) {
            if (row.getText().contains(productName)) {
                flag = true;
                row.findElement(By.cssSelector(".btn-danger")).click();
                break;
            }
        }
        assertEquals(true, flag);
    }


    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}

package stepDefinitions;


import DriverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class HomePage {

    private final WebDriver driver = DriverFactory.getDriver();

    @Given("user navigates to the {string} url")
    public void user_navigates_to_the_url(String url) {
        driver.get(url);
    }

    @Then("user validates the Demoblaze product store Homepage is displayed")
    public void user_validates_the_demoblaze_product_store_homepage_is_displayed() {
        Assert.assertEquals(driver.getTitle(), "STORE");
        Assert.assertTrue(driver.findElement(By.cssSelector(".list-group")).isDisplayed());
    }

    @When("user clicks on the Laptops category")
    public void userClicksOnTheLaptopsCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Laptops")));
        driver.findElement(By.linkText("Laptops")).click();
    }

    @Then("user validates the list of products displayed under the Laptops category")
    public void userValidatesTheListOfProductsDisplayedUnderTheLaptopsCategory() {
        try {
            Thread.sleep(5000); // Wait for the products to load
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> products = driver.findElements(By.xpath("//div[@id='tbodyid']/div//h4[@class='card-title']"));
        List<String> prod = Arrays.asList("Sony vaio i5", "Sony vaio i7", "MacBook air", "Dell i7 8gb", "2017 Dell 15.6 Inch", "MacBook Pro");
        for (WebElement product : products) {
            Assert.assertTrue(prod.contains(product.getText()));
        }
    }
}

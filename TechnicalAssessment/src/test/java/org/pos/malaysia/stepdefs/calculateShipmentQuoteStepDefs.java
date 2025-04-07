package org.pos.malaysia.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pos.malaysia.pages.calculateShipmentQuotePage;
import org.testng.Assert;

public class calculateShipmentQuoteStepDefs {

    private WebDriver driver;
    private calculateShipmentQuotePage calculateShipmentQuotePage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I navigate to POS malaysia website")
    public void i_navigate_to_pos_malaysia_website() {
       driver.get("https://www.pos.com.my/send/ratecalculator");
       calculateShipmentQuotePage = new calculateShipmentQuotePage(driver);
    }


    @Given("I am within rate calculator page")
    public void i_am_within_rate_calculator_page() {
        Assert.assertTrue(calculateShipmentQuotePage.rateCalculatorTitleIsDisplayed());
    }


    @Then("I enter {string}  and empty postcode for the TO dropdown")
    public void i_enter_and_empty_postcode_for_the_to_dropdown(String country) {
        calculateShipmentQuotePage.enterCountry(country);
    }

    @Then("I enter {string} weight and press on calculate button")
    public void i_enter_weight_and_press_on_calculate_button(String weight) {
        calculateShipmentQuotePage.enterWeightValue(weight);
        calculateShipmentQuotePage.clickCalculateBTN();
    }

    @Then("I verify that multiple quotes and shipments options are available")
    public void i_verify_that_multiple_quotes_and_shipments_options_are_available() {
        Assert.assertTrue(calculateShipmentQuotePage.hasMultipleServiceTypes());
    }

    @Then("I enter “{}” and “{}” from the dropdown")
    public void iEnterAndFromTheDropdown(String country, String postalCode) {
        //capturing as a negative scenario  - method create to verify that it is false
        Assert.assertFalse(calculateShipmentQuotePage.countryOneDropdownIsNotClickable(country));
        calculateShipmentQuotePage.enterPostCode(postalCode);
    }
}

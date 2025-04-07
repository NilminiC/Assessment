package org.pos.malaysia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class calculateShipmentQuotePage {

    private WebDriver driver;
    private WebDriverWait wait;

    //By Locators - Encapsulation
    private By rateCalculatorTitleLocator = By.xpath("//h1[text()='Shipping Rate Calculator']");
    private By postCodeLocator = By.xpath("//input[@formcontrolname='postcodeFrom']");
    private By countryOneLocator = By.xpath("//span[@class='font-bold']");
    private By countryTwoLocator = By.xpath("//input[@name='country']");
    private By weightValueLocator = By.xpath("//input[@formcontrolname='itemWeight']");
    private By calcuateBTNLocator = By.linkText("Calculate");
    private By multipleServiceTypesLocator = By.xpath("//dt[text()='Service Type']");

    //Constructor
    public calculateShipmentQuotePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Methods

    public boolean rateCalculatorTitleIsDisplayed() {
        try {
            WebElement rateCalculatorTitle = driver.findElement(rateCalculatorTitleLocator);
            return rateCalculatorTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Rate Calculator Title element not found: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    public void enterPostCode(String postCode) {
        WebElement postCodeInput = driver.findElement(postCodeLocator);
        postCodeInput.sendKeys(postCode);
    }

    public void enterCountry(String country) {
        WebElement countryInput = driver.findElement(countryTwoLocator);
        countryInput.click();
        countryInput.clear();
        countryInput.sendKeys(country);
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//mat-option/span/div/small[contains(text(),'"+country+"')]")
        ));
        suggestion.click();
    }

    public void enterWeightValue(String weightValue) {
        WebElement weightInput = driver.findElement(weightValueLocator);
        weightInput.sendKeys(weightValue);
    }

    public void clickCalculateBTN() {
        driver.findElement(calcuateBTNLocator).click();
    }

    public boolean hasMultipleServiceTypes() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(multipleServiceTypesLocator));
            List<WebElement> elements = driver.findElements(multipleServiceTypesLocator);
            System.out.println("Found " + elements.size() + " 'Service Type' elements.");
            return elements.size() > 1;
        } catch (NoSuchElementException e) {
            System.out.println("No 'Service Type' elements found.");
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    public boolean countryOneDropdownIsNotClickable(String country) {
        try {
            WebElement countryInput = driver.findElement(countryOneLocator);
            countryInput.clear();
            countryInput.sendKeys(country);
            return countryInput.isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("Country input field not found.");
            return false;
        } catch (StaleElementReferenceException e) {
            System.out.println("The country input field is no longer attached to the DOM.");
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

}

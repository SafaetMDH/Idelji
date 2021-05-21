package command_providers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ElementAssertions {
    private By locator;
    private WebDriver driver;

    public ElementAssertions(By locator, WebDriver driver) {
        this.locator = locator;
        this.driver = driver;
    }

    public ElementAssertions elementExist() {
       Boolean element  = driver.findElements(locator).size() > 0 ;
        Assert.assertTrue(element, "The expected element does not exist");
        return this;
    }
}

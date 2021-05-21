package command_providers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitFor {
    private By locator;
    private WebDriver driver;

    public WaitFor(WebDriver driver, By locator) {
        this.locator = locator;
        this.driver = driver;
    }

    public WaitFor waitForElementToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this;
    }
}

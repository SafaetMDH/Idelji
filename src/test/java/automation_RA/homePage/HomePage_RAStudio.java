package homePage;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReadConfigFiles;

public class HomePage_RAStudio {

    WebDriver driver;
    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By RAStudio = By.xpath("//*//ul/li//img[@title='RA Studio']");
    private final By RALandPage = By.id("ra");


    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void homePageRAStudioValidation() throws InterruptedException {

        //Verify Sign in page Landing
        Thread.sleep(2000);
        boolean SignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("SignInPage appeared :" + SignPage);

        //Enter Email ID
        driver.findElement(Email).clear();
        driver.findElement(Email).sendKeys("Safaethosan@Idelji.com");

        //Enter Password
        driver.findElement(Password).clear();
        driver.findElement(Password).sendKeys("Safaet121!");

        //Select Remember me
        driver.findElement(RememberMe).click();

        //Verify Versions
        Thread.sleep(2000);
        boolean verifyVersions = driver.findElement(Versions).isDisplayed();
        System.out.println("Current Version '2020.8.5' :" + verifyVersions);

        //Click on Logon
        Thread.sleep(2000);
        driver.findElement(LogonButton).click();

        // Current window handle
        String winHandleBefore = driver.getWindowHandle();

        //Navigate to RA Studio
        Thread.sleep(5000);
        driver.findElement(RAStudio).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        //Verify RA Studio Page
        Thread.sleep(5000);
        boolean HomePage = driver.findElement(RALandPage).isDisplayed();
        System.out.println("RA HomePage Appeared :" +HomePage);

        // Close the new window,
        driver.close();

        // Switch back to original browser
        driver.switchTo().window(winHandleBefore);
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}

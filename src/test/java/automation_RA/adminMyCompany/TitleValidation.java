package adminMyCompany;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReadConfigFiles;

public class TitleValidation {

    WebDriver driver;

    //Locators for SignInPage
    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By SignOut = By.xpath("//*[@class='fa fa-sign-out' and @ng-click='logoffClick()']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");

    @BeforeMethod
    public void browserInitialization() {

        //Reading the URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);

    }

    @Test
    public void myCompanyTitleValidation() throws InterruptedException {

        //Verify Sign in page Landing
        boolean verifyThatUserIsInSignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("User is in the SignInPage :" + verifyThatUserIsInSignPage);

        //Enter Email ID
        driver.findElement(Email).clear();
        driver.findElement(Email).sendKeys("Safaethosan@Idelji.com");

        //Enter Password
        driver.findElement(Password).clear();
        driver.findElement(Password).sendKeys("Safaet121!");

        //Select Remember me
        driver.findElement(RememberMe).click();

        //Verify Versions
        boolean verifyVersions = driver.findElement(Versions).isDisplayed();
        System.out.println("Version 2020.8.5 presence :" + verifyVersions);

        //Click on Logon
        Thread.sleep(3000);
        driver.findElement(LogonButton).click();

        //Verify Landing Page
        Thread.sleep(5000);
        boolean verifyLandingPage = driver.findElement(SignOut).isDisplayed();
        System.out.println("User is in the HomePage :" + verifyLandingPage);

    }

    @AfterMethod
    public void closeBrowser() {

        driver.quit();
    }

}

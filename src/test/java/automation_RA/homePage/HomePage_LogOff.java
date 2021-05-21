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

public class HomePage_LogOff {

    WebDriver driver;
    private final By SignOut = By.xpath("//*[@class='fa fa-sign-out']");
    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void validateLogOff() throws InterruptedException {

        //Verify SignIn Page
        Thread.sleep(2000);
        boolean SignInPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("SignInPage appeared :" + SignInPage);

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
        boolean versions = driver.findElement(Versions).isDisplayed();
        System.out.println("Current version '2020.8.5' :" + versions);

        //Click on Logon
        Thread.sleep(2000);
        driver.findElement(LogonButton).click();

        //Verify Home Page
        Thread.sleep(2000);
        boolean HomePage = driver.findElement(SignOut).isDisplayed();
        System.out.println("HomePage appeared :" + HomePage);

        //ClickOn LoggOff
        Thread.sleep(2000);
        driver.findElement(SignOut).click();

        //Verify LoggOff Page
        Thread.sleep(2000);
        boolean VerifyLogOff = driver.findElement(LogonButton).isDisplayed();
        System.out.println("Login button appeared :" +VerifyLogOff);
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}

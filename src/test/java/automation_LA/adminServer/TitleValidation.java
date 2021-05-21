package automation_LA.adminServer;

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

    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='L01AAA']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By AdminLink = By.linkText("Admin");
    private final By ServerLink = By.linkText("Servers");
    private final By ServerTitle = By.xpath("//*[@id='users']//label[text()='Servers ']");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("urlLA");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void adminServerTitleValidation() throws InterruptedException {

        //Verify Sign in page Landing
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
        boolean verifyVersions = driver.findElement(Versions).isDisplayed();
        System.out.println("Current version 'L01AAA' :" + verifyVersions);

        //Click on Logon
        Thread.sleep(3000);
        driver.findElement(LogonButton).click();

        //Navigate to Admin
        Thread.sleep(3000);
        driver.findElement(AdminLink).click();

        //Navigate to Server
        driver.findElement(ServerLink).click();
        Thread.sleep(2000);

        //Title Validation
        boolean actual = driver.findElement(ServerTitle).isDisplayed();
        System.out.println("Server title appeared :" +actual);
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}

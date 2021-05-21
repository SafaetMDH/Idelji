package automation_RA.analyzeArchives;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReadConfigFiles;

public class Archive_TitleValidation {

    WebDriver driver;

    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By Analyze = By.linkText("Analyze");
    private final By Archive = By.linkText("Archives");
    private final By ArchiveTitle = By.xpath("//*[@id='archives']/div[1]/label[text()='Archives']");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void navigatingToArchive() throws InterruptedException {

        //Verify Sign in page Landing
        boolean verifyThatUserIsInSignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("SignInPage appeared :" + verifyThatUserIsInSignPage);

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
        System.out.println("Current version '2020.8.5' :" + verifyVersions);

        //Click on Logon
        Thread.sleep(3000);
        driver.findElement(LogonButton).click();

        //Navigate to Archive
        Thread.sleep(5000);
        driver.findElement(Analyze).click();

        driver.findElement(Archive).click();
        Thread.sleep(2000);

        //Title Validation
        boolean actual = driver.findElement(ArchiveTitle).isDisplayed();
        System.out.println("Archive title appeared :" +actual);
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}
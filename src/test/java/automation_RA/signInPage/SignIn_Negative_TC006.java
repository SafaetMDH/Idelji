package signInPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignIn_Negative_TC006 {


    WebDriver driver;

    //Locators for SignInPage
    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By SignOut = By.xpath("//*[@class='fa fa-sign-out' and @ng-click='logoffClick()']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By ErrorMsg = By.xpath("//*[@id='polina']/form/p[text()='Invalid user name or password']");

    @BeforeMethod
    public void browserInitialization() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://www.remoteanalyst.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void signInWithInvalidCredentials() throws InterruptedException {

        //Verify Sign in page Landing
        boolean verifyThatUserIsInSignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("User is in the SignInPage :" + verifyThatUserIsInSignPage);

        //Enter UpperCase ValidEmail ID
        driver.findElement(Email).clear();
        driver.findElement(Email).sendKeys("SAFAETHOSN@IDELJI.COM");

        //Enter SomePassword
        driver.findElement(Password).clear();
        driver.findElement(Password).sendKeys("SAFAET121!");

        //Select Remember me
        driver.findElement(RememberMe).click();

        //Verify Versions
        boolean verifyVersions = driver.findElement(Versions).isDisplayed();
        System.out.println("Version 2020.8.5 presence :" + verifyVersions);

        //Click on Logon
        Thread.sleep(1000);
        driver.findElement(LogonButton).click();

        //VerifyErrorMsg
        Thread.sleep(1000);
        boolean VerifyErrorMsg = driver.findElement(ErrorMsg).isDisplayed();
        System.out.println("User unable to SignIn :" + VerifyErrorMsg);

    }

    @AfterMethod
    public void closeBrowser() {

        driver.quit();
    }
}

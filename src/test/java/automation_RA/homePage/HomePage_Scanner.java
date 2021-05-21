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

//NEEDS to WORK ON THAT for Select FILE/PROCESS

public class HomePage_Scanner {

    WebDriver driver;

    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By SignOut = By.xpath("//*[@class='fa fa-sign-out']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");

    private final By Scanner = By.xpath("/html/body/div[3]/div[1]/div/div/ul/li[2]/span");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void validateScanner() throws InterruptedException {

        //Verify Sign in page Landing
        Thread.sleep(2000);
        boolean verifyThatUserIsInSignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("User is in the SignInPage :" + verifyThatUserIsInSignPage);

        //Enter Email ID
        Thread.sleep(1000);
        driver.findElement(Email).clear();
        driver.findElement(Email).sendKeys("Safaethosan@Idelji.com");

        //Enter Password
        Thread.sleep(1000);
        driver.findElement(Password).clear();
        driver.findElement(Password).sendKeys("Safaet121!");

        //Select Remember me
        driver.findElement(RememberMe).click();

        //Verify Versions
        Thread.sleep(1000);
        boolean verifyVersions = driver.findElement(Versions).isDisplayed();
        System.out.println("Version 2020.8.5 presence :" + verifyVersions);

        //Click on Logon
        Thread.sleep(5000);
        driver.findElement(LogonButton).click();

        //Verify Landing Page
        boolean verifyLandingPage = driver.findElement(SignOut).isDisplayed();
        System.out.println("User is in the HomePage :" + verifyLandingPage);

        //Select file from Scanner
        Thread.sleep(15000);

        driver.findElement(Scanner).click();
        System.out.println("Clicked on Scanner");

    }

    @AfterMethod
    public void closeBrowser() {

        driver.quit();
    }
}

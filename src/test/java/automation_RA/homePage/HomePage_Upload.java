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

public class HomePage_Upload {

    WebDriver driver;
    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By SignOut = By.xpath("//*[@class='fa fa-sign-out']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By Upload = By.xpath("/html/body//ul/li//img[@title='Upload collected data']");
    private final By SubmitButton = By.xpath("//*[text()=' Choose files to Upload' and @class='btn btn-success']");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void validateUploadCollectedData() throws InterruptedException {

        //Verify SignInPage
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
        System.out.println("Current version '2020.8.5' :" + verifyVersions);

        //Click on Logon
        Thread.sleep(7000);
        driver.findElement(LogonButton).click();

        //Verify Landing Page
        boolean verifyLandingPage = driver.findElement(SignOut).isDisplayed();
        System.out.println("HomePage appeared :" + verifyLandingPage);

        // Current window handle
        String winHandleBefore = driver.getWindowHandle();

        //Navigate to RA Studio
        Thread.sleep(2000);
        driver.findElement(Upload).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(5000);
        boolean FileToUpload = driver.findElement(SubmitButton).isDisplayed();
        System.out.println("Upload page appeared :" +FileToUpload);

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

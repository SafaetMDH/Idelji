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

public class HomePage_Help {
    WebDriver driver;

    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By SignOut = By.xpath("//*[@class='fa fa-sign-out']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By HelpIcon = By.xpath("//*[@ng-click='goToHelp()' and @title='Help']");
    private final By NavigatorTab = By.xpath("//*[@class='documentor-menu' and text()='General Navigation']");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void validateHelp() throws InterruptedException {

        //Verify SignIn Page
        Thread.sleep(2000);
        boolean SignInPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("SignInPage Appeared :" + SignInPage);

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
        System.out.println("Current version '2020.8.5' :" + verifyVersions);

        //Click on Logon
        Thread.sleep(2000);
        driver.findElement(LogonButton).click();

        //Verify Landing Page showed up
        Thread.sleep(2000);
        boolean HomePage = driver.findElement(SignOut).isDisplayed();
        System.out.println("HomePage appeared :" + HomePage);

        // Current window handle
        String winHandleBefore = driver.getWindowHandle();

        //Click on Help
        Thread.sleep(2000);
        driver.findElement(HelpIcon).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        //Verify Help Page
        Thread.sleep(2000);
        boolean HelpPage = driver.findElement(NavigatorTab).isDisplayed();
        System.out.println("HelpPage appeared :" +HelpPage);

        // Close the new window,
        Thread.sleep(1000);
        driver.close();

        // Switch back to original browser
        driver.switchTo().window(winHandleBefore);
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}
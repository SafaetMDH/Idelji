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

public class HomePage_Profile {

        WebDriver driver;
        private final By Email = By.id("username");
        private final By Password = By.id("password");
        private final By RememberMe = By.id("remember-me");
        private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
        private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
        private final By SignOut = By.xpath("//*[@class='fa fa-sign-out']");
        private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
        private final By Profile = By.xpath("//*[@title='My Profile' and @class='pull-right']");
        private final By ResetButton = By.xpath("//*[@type='button' and text()='Reset']");

        @BeforeMethod
        public void browserInitialization() {

            //Reading URL from Config File
            String URL = ReadConfigFiles.getPropertyValues("url");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            ActOn.browser(driver).openBrowser(URL);
        }

        @Test
        public void validateProfile() throws InterruptedException {

            //Verify SignInPage
            Thread.sleep(2000);
            boolean SignInPage = driver.findElement(ForgetPass).isDisplayed();
            System.out.println("SignInPage appeared :" + SignInPage);

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
            Thread.sleep(2000);
            boolean verifyVersions = driver.findElement(Versions).isDisplayed();
            System.out.println("Current Version '2020.8.5' appeared :" + verifyVersions);

            //Click on Logon
            Thread.sleep(2000);
            driver.findElement(LogonButton).click();

            //Verify Landing Page
            Thread.sleep(2000);
            boolean VerifyHomePage = driver.findElement(SignOut).isDisplayed();
            System.out.println("HomePage appeared :" + VerifyHomePage);

            //Click on Profile
            Thread.sleep(2000);
            driver.findElement(Profile).click();

            //Verify Profile
            Thread.sleep(2000);
            boolean verifyReset = driver.findElement(ResetButton).isDisplayed();
            System.out.println("Profile setting page appeared :" + verifyReset);
        }

        @AfterMethod
        public void closeBrowser() {
            ActOn.browser(driver).closeBrowser();
        }
}

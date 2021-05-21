package signInPage;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReadConfigFiles;

public class SignIn_ContactUs {

    WebDriver driver;

    private static final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private static final By ContactUsLink = By.xpath("//*[@id='polina']/form//label/a[@ui-sref='contact']");
    private static final By FirstName = By.id("firstName");
    private static final By MassageBox = By.id("message");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void validateContactUsPage() throws InterruptedException {

        //Verify Sign in page Landing
        Thread.sleep(1000);
        boolean SignInPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("SignInPage appeared :" + SignInPage);

        //Click on ContactUS
        Thread.sleep(1000);
        driver.findElement(ContactUsLink).click();
        System.out.println("Contact us page appeared :true");

        //Verify contact page
        Thread.sleep(2000);
        boolean VerifyFirstName = driver.findElement(FirstName).isDisplayed();
        System.out.println("First name field appeared :" +VerifyFirstName);
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}

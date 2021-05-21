package signInPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignIn_ForgetPassword {

    WebDriver driver;
    private static final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private static final By Email = By.id("username");
    private static final By Password = By.id("password");
    private static final By MessageBox = By.xpath("/html/body//div[@class='display: table-cell']");

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://www.remoteanalyst.com/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void forgetPasswordLink() throws InterruptedException {

        //Verify Sign in page Landing
        Thread.sleep(1000);
        boolean verifyThatUserIsInSignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("User is in the SignInPage :" + verifyThatUserIsInSignPage);

        //Enter InValid Email
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(Email).sendKeys("Safaethosa@idelji.com");

        //Enter Password
        Thread.sleep(1000);
        driver.findElement(Password).clear();

        //Click on Forget Button
        Thread.sleep(1000);
        driver.findElement(ForgetPass).click();

        //Verify Massage Displayed
        Thread.sleep(2000);
        boolean Message = driver.findElement(MessageBox).isDisplayed();
        System.out.println("Message box displayed :" + Message);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
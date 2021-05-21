package signInPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignIn_Video {
    WebDriver driver;

    //Locators for Videos
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By VideosLink = By.xpath("//*[@id='polina']/form/div[1]/div/div/a");
    private final By VerifyPage = By.xpath("//*[@id='generate']/div/form//h3[text()='Remote Analyst Video in English']");

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
    public void verifyVideoLink() throws InterruptedException {

        //Verify Sign in page Landing
        boolean verifyThatUserIsInSignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("User is in the SignInPage :" + verifyThatUserIsInSignPage);

        //~~Switch Frame~~\\
        // Current window handle
        String winHandleBefore = driver.getWindowHandle();

        //Navigate to Video Link
        Thread.sleep(2000);
        driver.findElement(VideosLink).click();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        //Verify Video Page
        Thread.sleep(2000);
        boolean LandPage = driver.findElement(VerifyPage).isDisplayed();
        System.out.println("User is on the Video HomePage :" +LandPage);

        // Close the new window,
        driver.close();

        // Switch back to original browser
        driver.switchTo().window(winHandleBefore);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}

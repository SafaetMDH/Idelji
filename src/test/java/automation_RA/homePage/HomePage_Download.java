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

public class HomePage_Download {

    WebDriver driver;

    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By SignOut = By.xpath("//*[@class='fa fa-sign-out']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By DownloadLink = By.xpath("//*/ul//span/a/img[@src='assets/global/imgs/glacier.png']");
    private final By Calender = By.xpath("//*[@id='holidays']");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void validateDownload() throws InterruptedException {

        //Verify S=sign in page
        Thread.sleep(2000);
        boolean SignInPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("Sign in page appeared :" + SignInPage);

        //Enter Email ID
        Thread.sleep(1000);
        driver.findElement(Email).clear();
        driver.findElement(Email).sendKeys("Safaethosan@Idelji.com");

        //Enter password
        Thread.sleep(1000);
        driver.findElement(Password).clear();
        driver.findElement(Password).sendKeys("Safaet121!");

        //Select remember me
        driver.findElement(RememberMe).click();

        //Verify current versions
        Thread.sleep(1000);
        boolean verifyVersions = driver.findElement(Versions).isDisplayed();
        System.out.println("Current version '2020.8.5' :" + verifyVersions);

        //Click on Logon
        Thread.sleep(1000);
        driver.findElement(LogonButton).click();

        //Verify home page
        Thread.sleep(3000);
        boolean verifyLandingPage = driver.findElement(SignOut).isDisplayed();
        System.out.println("Home page appeared :" + verifyLandingPage);

        //Window handling starts here
        String winHandleBefore = driver.getWindowHandle();

        //Navigate to Download link
        Thread.sleep(2000);
        driver.findElement(DownloadLink).click();

        //Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        //Verify Download From Archive Page Displayed
        Thread.sleep(2500);
        boolean ArchivePage = driver.findElement(Calender).isDisplayed();
        System.out.println("Archive calender appeared :" +ArchivePage);

        // Close the new window,
        driver.close();

        // Switch back to original browser
        driver.switchTo().window(winHandleBefore);
        Thread.sleep(5000);
    }

    @AfterMethod
    public void closeBrowser() {

        driver.quit();
    }
}

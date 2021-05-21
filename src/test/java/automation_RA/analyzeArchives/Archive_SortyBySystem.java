package Analyze_Archives;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReadConfigFiles;

public class Archive_SortyBySystem {
    WebDriver driver;

    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By Analyze = By.linkText("Analyze");
    private final By Archive = By.linkText("Archives");
    private final By TypeBox = By.xpath("//*[@id='archives']//isteven-multi-select/span/button[@type='button']");
    private final By SelectNone = By.xpath("//*[@id=\"archives\"]//div/div/input[@type='text']");
    private final By DPA = By.linkText("DPA");
    private final By SortSystem = By.xpath("//*[@id=\"archives\"]/div[2]//thead/tr/th[text()='System']");
    private final By SortSystemVeri = By.xpath("//*[@id=\"archives\"]/div[2]/table[2]/tbody/tr[2]/td[text()='\\NSCLOUD(078578)']");


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
        Thread.sleep(4000);
        driver.findElement(Analyze).click();
        Thread.sleep(3000);
        driver.findElement(Archive).click();

        //
        Thread.sleep(1000);
        driver.findElement(SortSystem).click();

        boolean SortingByType = driver.findElement(SortSystemVeri).isDisplayed();
        System.out.println("Sorted by System Ascending :" +SortingByType);
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}

package automation_LA.adminServer;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReadConfigFiles;

public class AddNewServerNameValidation {
    WebDriver driver;

    private final By LicenceClear = By.xpath("/html/body//button[text()='OK']");
    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='L01AAA']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By AdminLink = By.linkText("Admin");
    private final By ServerLink = By.linkText("Servers");
    private final By AddNew = By.xpath("//*[@id='users']//button[@ng-click='addServer()']");
    private final By NameField = By.name("system");
    private final By SerialField = By.name("systemSerail");
    private final By LicenceKey = By.name("licenseKey");
    private final By MEASFH = By.name("mea");
    private final By SaveButton = By.xpath("//*[@id='system-config']/form//button[text()='Save']");
    private final By AdvisoryMessage = By.xpath("//*[text()='Invalid License Key.']");

    private boolean verifyName(String name)
    {
        name = name.trim();

        if(name == null || name.equals(""))
            return false;

        if(!name.matches("[a-zA-Z]*"))
            return false;

        return true;
    }

    @BeforeMethod
    public void addingNewServer() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("urlLA");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    @Test
    public void adminServerTitleValidation() throws InterruptedException {


        //Verify Sign in page Landing
        Thread.sleep(2000);
        driver.findElement(LicenceClear).click();
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
        System.out.println("Current version 'L01AAA' :" + verifyVersions);

        //Click on Logon
        Thread.sleep(3000);
        driver.findElement(LogonButton).click();

        //Navigate to Admin
        Thread.sleep(3000);
        driver.findElement(AdminLink).click();

        //Navigate to Server
        driver.findElement(ServerLink).click();
        Thread.sleep(2000);

        //Navigate to AddNew
        Thread.sleep(1000);
        driver.findElement(AddNew).click();

        //Enter Name
        Thread.sleep(2000);
        driver.findElement(NameField).sendKeys("TestWith10Character");

        //
        String length = driver.findElement(LicenceKey).getAttribute("Length");
        System.out.println("Name field total length is :" +length);

    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}

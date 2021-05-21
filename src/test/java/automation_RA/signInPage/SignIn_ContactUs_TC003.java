package signInPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignIn_ContactUs_TC003 {

    WebDriver driver;

    private static final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private static final By ContactUsLink = By.xpath("//*[@id='polina']/form//label/a[@ui-sref='contact']");
    private static final By FirstName = By.id("firstName");
    private static final By LastName = By.id("lastName");
    private static final By Email = By.id("email");
    private static final By Phone = By.id("phone");
    private static final By Company = By.id("company");
    private static final By Message = By.id("message");
    private static final By SuccessAdvisory = By.xpath("//*//div/span[@ng-bind-html='message' and text()='We have received your message, thank you for contacting us.']");

    private static final By FirstNameError = By.xpath("//*[@id='contact']//div[text()='Please enter first name.']");
    private static final By LastNameError = By.xpath("//*[@id='contact']//div[@ng-if='!lastName && lastNameValidate' and text()='Please enter first name.']");
    private static final By EmailError = By.xpath("//*[@id='contact']//div[text()='Please enter email.']");
    private static final By MessageBoxError = By.xpath("//*[@id='contact']//div[text()='Please enter message.']");
    private static final By SubmitButton = By.xpath("//*[@id='contact']//div/button[text()='Submit']");


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
    public void validateContactUsPage() throws InterruptedException {

        //Verify Sign in page Landing
        Thread.sleep(1000);
        boolean verifyThatUserIsInSignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("User is in the SignInPage :" + verifyThatUserIsInSignPage);

        //Click on ContactUS
        Thread.sleep(1000);
        driver.findElement(ContactUsLink).click();

        //Enter First Name
        driver.findElement(FirstName).clear();
        driver.findElement(FirstName).sendKeys("TestFirstName");
        Thread.sleep(2000);

        //Enter Last Name
        driver.findElement(LastName).clear();
        driver.findElement(LastName).sendKeys("TestLastName");
        Thread.sleep(2000);

        //Enter Email
        driver.findElement(Email).clear();
        driver.findElement(Email).sendKeys("TestEmail@Test.com");
        Thread.sleep(2000);

        //Enter Message
        driver.findElement(Message).clear();
        driver.findElement(Message).sendKeys("TestMessageTest");
        Thread.sleep(2000);

        //ClickSubmit
        driver.findElement(SubmitButton).click();
        Thread.sleep(2000);

        //Verify Advisory Message
        boolean AdvisoryMessage = driver.findElement(SuccessAdvisory).isDisplayed();
        System.out.println("Advisory Success Message shows :" +AdvisoryMessage);

    }

    @AfterMethod
    public void closeBrowser() {

        driver.quit();
    }
}

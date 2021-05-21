package Analyze_Archives;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReadConfigFiles;

import java.io.File;

public class Archive_Download {

    WebDriver driver;

    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By RememberMe = By.id("remember-me");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By Versions = By.xpath("//*[@id='polina']/form//label[text()='2020.8.5']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By Analyze = By.linkText("Analyze");
    private final By Archive = By.linkText("Archives");
    private final By Download = By.xpath("//*[@id='archives']/div[2]/table[2]/tbody/tr[2]/td/i[@title='Download']");

    @BeforeMethod
    public void browserInitialization() {

        //Reading URL from Config File
        String URL = ReadConfigFiles.getPropertyValues("url");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(URL);
    }

    private File getLatestFileFromDir(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null; }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
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

        //Click on Download
        Thread.sleep(2000);
        driver.findElement(Download).click();
        Thread.sleep(2000);

        String downloadPath = "C://Users//AdministratorDownloads";
        File getLatestFile = getLatestFileFromDir(downloadPath);
        String fileName = getLatestFile.getName();
        Assert.assertTrue(fileName.equals("NSCLOUD(078578) - QT for 2021-05-05 0000 to 2021-05-05 1100.zip"), "Downloaded file name is not matching with expected file name");

        //Delete The File
        File file = new File("C://Users//AdministratorDownloads/NSCLOUD(078578) - QT for 2021-05-05 0000 to 2021-05-05 1100.zip");

        if (file.delete() )
        {
            System.out.println("Downloaded file deleted");
        } else {
            System.out.println("Downloaded file not deleted");
        }
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}

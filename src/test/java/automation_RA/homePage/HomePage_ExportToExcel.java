package homePage;

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

public class HomePage_ExportToExcel {

    WebDriver driver;
    private final By Email = By.id("username");
    private final By Password = By.id("password");
    private final By LogonButton = By.xpath("//*[@id='polina']//button[text()='Logon']");
    private final By SignOut = By.xpath("//*[@class='fa fa-sign-out']");
    private final By ForgetPass = By.xpath("//*[@id='polina']/form//a[@ng-click='forgotPasswordClick()']");
    private final By ExportToExcel = By.xpath("//html//span/a/i[@title='Export to Excel']");

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
    public void validateUploadCollectedData() throws InterruptedException {

        //Verify SignIn page
        boolean SignPage = driver.findElement(ForgetPass).isDisplayed();
        System.out.println("Sign in page appeared :" + SignPage);

        //Enter Email ID
        driver.findElement(Email).clear();
        driver.findElement(Email).sendKeys("Safaethosan@Idelji.com");

        //Enter Password
        driver.findElement(Password).clear();
        driver.findElement(Password).sendKeys("Safaet121!");

        //Click on Logon
        Thread.sleep(2000);
        driver.findElement(LogonButton).click();

        //Verify Landing Page
        Thread.sleep(2000);
        boolean HomePage = driver.findElement(SignOut).isDisplayed();
        System.out.println("HomePage appeared :" + HomePage);

        //Click on Export
        Thread.sleep(5000);
        driver.findElement(ExportToExcel).click();
        Thread.sleep(2000);

        //Verify Download
        String downloadPath = "/Users/safaethosan/Downloads";
        File getLatestFile = getLatestFileFromDir(downloadPath);
        String fileName = getLatestFile.getName();
        Assert.assertTrue(fileName.equals("NSCLOUD_SYSTEM_May 12, 2021 00_00-May 13, 2021 00_00.xlsx"), "Downloaded file name is not matching with expected file name");

        //Delete The File
        File file = new File("/Users/safaethosan/Downloads/NSCLOUD_SYSTEM_May 12, 2021 00_00-May 13, 2021 00_00.xlsx");

        if (file.delete() )
        {
            System.out.println("file deleted");
        } else {
            System.out.println("file not deleted");
        }
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}

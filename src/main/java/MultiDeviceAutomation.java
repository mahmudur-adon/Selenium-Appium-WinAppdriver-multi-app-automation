import io.appium.java_client.MobileElement;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;




public class MultiDeviceAutomation {


    private static WebDriver browserDriver;
    private static AndroidDriver androidDriverline;
    private static WindowsDriver windows11Driver;




    @BeforeTest
    public static void setupAndroid() throws Exception {
        DesiredCapabilities cap1 = new DesiredCapabilities();
        cap1.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap1.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        cap1.setCapability(MobileCapabilityType.DEVICE_NAME, "CB512EEHF6"); //change according to adb device list command
        cap1.setCapability("appPackage", "jp.naver.line.android"); //change according to project
        cap1.setCapability("appActivity", "jp.naver.line.android.activity.SplashActivity"); //change according to project
        cap1.setCapability("autoGrantPermissions", true);
        cap1.setCapability(MobileCapabilityType.NO_RESET, true);
        androidDriverline = new AndroidDriver(new URL("http://127.1.1.2:4730/wd/hub"), cap1);
        androidDriverline.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeTest
    public static void setUpFireFox() {
        // Set up Firefox driver
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        browserDriver = new FirefoxDriver(firefoxOptions);
        browserDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @BeforeTest
    public static void setupDesktop() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\FileZilla FTP Client\\FileZilla.lnk");
        windows11Driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        windows11Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1, description = "Logging into a system from firefox browser")
    public void loginfromBrowser() throws Exception {
        // Interact with Firefox driver
        browserDriver.get("example.com"); //change according to project
        browserDriver.findElement(By.xpath("//button[@type='button']"))
                .click();
        WebElement operatorName = browserDriver.findElement(By.id("username"));
        operatorName.sendKeys(""); //change according to project
        WebElement operatorPass = browserDriver.findElement(By.id("password"));
        operatorPass.sendKeys(""); //change according to project
        browserDriver.findElement(By.id("kc-login")).click();

        // Verify that login was successful
        String actualTitle = browserDriver.getTitle();
        String expectedTitle = ""; //change according to project
        Assert.assertEquals(expectedTitle, actualTitle);
    }



    @Test(priority = 2, description = "Interact with an android application")
    public void LineChat() throws Exception {

        List<MobileElement> lineTabs = androidDriverline.findElements(By.id("jp.naver.line.android:id/bnb_button_clickable_area"));
        MobileElement chatTab = lineTabs.get(1);
        chatTab.click();
        MobileElement jsdeChatLine = (MobileElement) androidDriverline.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.view.ViewGroup/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]");
        jsdeChatLine.click();

        WebDriverWait waitformsg = new WebDriverWait(androidDriverline, 10);
        MobileElement enterMessage = (MobileElement) waitformsg.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.EditText")));
        enterMessage.sendKeys("Hi");

        MobileElement send = (MobileElement) androidDriverline.findElementByAccessibilityId("Send");
        send.click();

        WebDriverWait wait = new WebDriverWait(androidDriverline, 10);
        MobileElement reply = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[2]")));
        reply.click();
        MobileElement el8 = (MobileElement) androidDriverline.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[2]");
        el8.click();
        el8.clear();
    }



    @Test(priority = 3, description = "Traversing an website in firefox browser")
    public void BrowserTraverse() throws Exception {
        // Interact with Firefox driver
        new WebDriverWait(browserDriver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(""))).click();
        WebElement searchBox = null;
        try {
            searchBox = browserDriver.findElement(By.xpath("//input[@type='text']"));
        } catch (NoSuchElementException e) {
            System.out.println("Count not find the searchbar");
        }

        Assert.assertTrue(searchBox != null);

        browserDriver.findElement(By.cssSelector("")).click();
        WebElement operatorListText = null;
        try {
            operatorListText = browserDriver.findElement(By.xpath("]"));
        } catch (NoSuchElementException e) {
            System.out.println("Count not find");
        }
    }

    @Test(priority = 4, description = "Login Desktop Application using winappdriver")
    public void connectFtp() {
        WebElement host = windows11Driver.findElementByAccessibilityId("-31830");
        host.sendKeys("");

        WebElement username = windows11Driver.findElementByAccessibilityId("-31829");
        username.sendKeys("");

        WebElement pass=windows11Driver.findElement(By.xpath("//Edit[@ClassName='Edit'][@Name='Password:']"));

        //WebElement password = driver.findElementByName("Password:");
        pass.click();
        pass.sendKeys("");

        WebElement port = windows11Driver.findElement(By.xpath("//Edit[@ClassName='Edit'][@Name='Port:']"));
        port.sendKeys("");

        WebElement quickconnect = windows11Driver.findElementByAccessibilityId("");
        quickconnect.click();

        // assert that the Not connected element is not present
        try {
            WebElement status = windows11Driver.findElement(By.name("Not connected."));
            Assert.fail("Element is present");
        } catch (NoSuchElementException e) {
            // element is not present, test passes
        }

    }

    @Test(priority = 5, description = "Traverse FileZilla")
    public void traverseMenu() {
        WebElement file = windows11Driver.findElement(By.name("File"));
        file.click();

        WebElement edit = windows11Driver.findElement(By.name("Edit"));
        edit.click();

        WebElement view = windows11Driver.findElement(By.name("View"));
        view.click();

        WebElement transfer = windows11Driver.findElement(By.name("Transfer"));
        transfer.click();

        WebElement server = windows11Driver.findElement(By.name("Server"));
        server.click();

        WebElement bookmarks = windows11Driver.findElement(By.name("Bookmarks"));
        bookmarks.click();

        WebElement help = windows11Driver.findElement(By.name("Help"));
        help.click();

        WebElement about = windows11Driver.findElement(By.name("About..."));
        about.click();

        // assert that the Not connected element is not present
        try {
            WebElement platformvalue = windows11Driver.findElement(By.name("64-bit system"));
            platformvalue.click();
        } catch (NoSuchElementException e) {

            Assert.fail("Platform value is not present");
        }
    }


    @AfterClass
    public static void logoutBrowser() throws InterruptedException {
        // get the handles of all open windows/tabs
        List<String> handles = new ArrayList<String>(browserDriver.getWindowHandles());
        browserDriver.switchTo().window(handles.get(0));
        browserDriver.findElement(By.cssSelector(".navbar-button")).click();
    }


    @AfterClass
    public static void tearDownBrowser() throws InterruptedException {
        Thread.sleep(5000);
        browserDriver.quit();
    }


    @AfterClass
    public static void tearDownAndroid() throws InterruptedException {
        Thread.sleep(5000);
        androidDriverline.quit();
    }

    @AfterClass
    public static void tearDownWindows() throws InterruptedException {
        Thread.sleep(5000);
        windows11Driver.quit();
    }
}






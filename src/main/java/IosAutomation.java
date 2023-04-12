import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IosAutomation {

    private IOSDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "ios");
        desiredCapabilities.setCapability("appium:platformVersion", "14.0");
        desiredCapabilities.setCapability("appium:udid", "157FC1A6-1056-40F1-A39B-985A8DAA07AB");
        desiredCapabilities.setCapability("appium:bundleId", "com.apple.reminders");
        desiredCapabilities.setCapability("appium:deviceName", "iPhone 11 Pro Max");
        desiredCapabilities.setCapability("appium:includeSafariInWebviews", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new IOSDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void addNote() {
        MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Allow Once");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"New Reminder\"]");
        el2.click();
        MobileElement el4 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Reminders\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther");
        el4.sendKeys("hello again");
        MobileElement el5 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Reminders\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther");
        el5.click();
        MobileElement el6 = (MobileElement) driver.findElementByAccessibilityId("Add");
        el6.click();

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
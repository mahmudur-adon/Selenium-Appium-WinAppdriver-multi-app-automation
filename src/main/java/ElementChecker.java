import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;

public class ElementChecker {
    public static boolean isElementPresent(AndroidDriver<MobileElement> driver, String elementId) {
        try {
            driver.findElementById(elementId);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

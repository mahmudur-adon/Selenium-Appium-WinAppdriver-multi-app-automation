import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class Scroll {
    public static void scrollDown(AndroidDriver<MobileElement> driver) {
        int scrollStart = (int) (driver.manage().window().getSize().getHeight() * 0.8);
        int scrollEnd = (int) (driver.manage().window().getSize().getHeight() * 0.2);
        int scrollDuration = 2000;
        new TouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction()
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }
}

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseFrontEnd {

    public static WebDriver driver = null;
    public static String driverPath = "\\src\\test\\java\\drivers\\chromedriver.exe";

    @BeforeClass
    public static void BaseFrontEnd() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverPath);
        driver = new ChromeDriver();
//To maximize browser
        driver.manage().window().maximize();
//Implicit wait
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    //Test cleanup
    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}

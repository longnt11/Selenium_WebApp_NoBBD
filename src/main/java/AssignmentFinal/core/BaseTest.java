package AssignmentFinal.core;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public String getUsername() {
        return "admin@tecdiary.com";
    }

    public String getPassword() {
        return "12345678";
    }

    public String getLoginUrl() {
        return "https://sma.tec.sh/admin/login";
    }

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browserName) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        desiredCapabilities.setBrowserName(browserName);
        if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        }
        if (browserName.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        }
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void afterMethod(){
        driver.manage().deleteAllCookies();
    }
}
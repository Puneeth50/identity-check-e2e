package com.identity.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverHelper extends EventFiringWebDriver {


    private static final Logger LOGGER = LoggerFactory
            .getLogger(DriverHelper.class);
    private static String BROWSER;
    private static RemoteWebDriver DRIVER = null;
    private static final Thread CLOSE_THREAD = new Thread() {

        @Override
        public void run() {
            DRIVER.quit();
        }
    };
    private static String BASE_PATH;


    static {
        PropUtils.loadRunConfigProps("config.properties");
        BROWSER = PropUtils.getProp("browser");
        BASE_PATH = PropUtils.getProp("url");
        try {
            switch (BROWSER.toLowerCase()) {
                case ("chrome"):
                    startChromeDriver();
                    break;
                case ("firefox"):
                    startFireFoxDriver();
                    break;
                case ("iexplore"):
                    startIEDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser " + BROWSER + " type not supported");
            }
            LOGGER.info("Browser used:", BROWSER);
        } catch (IllegalStateException e) {
        }
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }


    public DriverHelper(WebDriver driver) {
        super(driver);
    }

    public static WebDriver getWebDriver() {
        return DRIVER;
    }


    private static WebDriver startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", getFilePath(PropUtils.getProp("chrome_driver_loc")));
        DRIVER = new ChromeDriver();
        return DRIVER;
    }

    private static WebDriver startFireFoxDriver() {
        System.setProperty("webdriver.gecko.driver", getFilePath(PropUtils.getProp("firefox_driver_loc")));
        FirefoxOptions options = new FirefoxOptions();
        options.setLegacy(true);
        DRIVER = new FirefoxDriver();
        return DRIVER;
    }

    private static WebDriver startIEDriver() {
        DRIVER = new InternetExplorerDriver();
        return DRIVER;
    }

    private static String getFilePath(String path) {
        ClassLoader classLoader = DriverHelper.class.getClassLoader();
        return classLoader.getResource(path).getFile();
    }

    public static void loadPage() throws InterruptedException {
        Thread.sleep(3000);
        DRIVER.navigate().to(BASE_PATH);
        DRIVER.manage().window().maximize();
    }
}

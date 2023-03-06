package com.envision.actitime.utility;

import com.envision.actitime.filereader.PropertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class BrowserFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    static String rm = PropertyFileReader.getPropertyValue("actitime.runmode");
    static String bn = PropertyFileReader.getPropertyValue("actitime.browsername");
    static String url = PropertyFileReader.getPropertyValue("actitime.url");


    //    private static WebDriver openFirefoxBrowser() {
//        System.setProperty("webdriver.geko.driver", "browser_exes/geckodriver.exe");
//        driver = new FirefoxDriver();
//        return driver;
//    }
    private static void openChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (rm.equalsIgnoreCase("headless")) {
            options.addArguments("--headless=new");
        }
        driver.set(new ChromeDriver(options));
    }

    private static void openFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (rm.equalsIgnoreCase("headless")) {
            options.addArguments("-headless");
        }
        driver.set(new FirefoxDriver(options));
    }

    private static void openEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (rm.equalsIgnoreCase("headless")) {
            options.addArguments("--headless=new");
        }
        driver.set(new EdgeDriver(options));
    }


    public static WebDriver openBrowserAsPerPropertiesFileBrowser() {
        if (bn.equalsIgnoreCase("edge")) {
            openEdge();
        } else if (bn.equalsIgnoreCase("firefox")) {
            openFirefox();
        } else {
            openChrome();
        }
        return driver.get();

    }

    public static WebDriver openBrowser(String browserInputName) {
        switch (browserInputName.toLowerCase().intern()) {
            case "firefox":
                openFirefox();
                break;
            case "edge":
                openEdge();
                break;
            default:
                openChrome();
                break;
        }
        return driver.get();
    }

    public static WebDriver openBrowser(String browserName, String runMode) {
        bn = browserName;
        rm = runMode;
        return openBrowser(bn);
    }

    public static WebDriver openBrowser() {
        return driver.get();
    }

    public static void LaunchWebsitefromPropertyFile() {
        driver.get().get(url);
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
        driver.get().manage().window().maximize();
    }

    public static void LaunchWebsitewithURLinput(String inputURL) {
        url = inputURL;
        LaunchWebsitefromPropertyFile();
    }

    public static void closeAllWindows() {
        driver.get().quit();
    }

    public static void closeCurrentWindow() {
        driver.get().close();
    }

    public static void testOnAll3Browsers() {
        String[] browsers = {"chrome", "edge", "firefox"};
        for (String each : browsers) {
            openBrowser(each);
            //closeAllWindows();
        }
    }

    public static void experiment() {
        driver.get().getCurrentUrl().endsWith("hj");
    }

    public static void elementExperiment() {
        Wait<WebDriver> w = new WebDriverWait(driver.get(), Duration.ofSeconds(2L));
        WebElement element = w.until(ExpectedConditions.presenceOfElementLocated(By.id("idName")));
    }

    public static void elementExperiment2() {
        Wait<WebDriver> w = new FluentWait<>(driver.get())
                .pollingEvery(Duration.ofSeconds(2L))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(10L));
        WebElement element = w.until(ExpectedConditions.presenceOfElementLocated(By.id("idName")));


//    public static void main(String[] args) {
////        BrowserFactory.openBrowser("firefox", "active");
////        BrowserFactory.closeAllWindows();
////        BrowserFactory.openBrowser("firefox", "headless");
////        BrowserFactory.closeAllWindows();
////        BrowserFactory.openBrowser("edge", "active");
////        BrowserFactory.closeAllWindows();
////        BrowserFactory.openBrowser("edge", "headless");
////        BrowserFactory.closeAllWindows();
////        BrowserFactory.openBrowser("chrome", "active");
////        BrowserFactory.closeAllWindows();
////        BrowserFactory.openBrowser("chrome", "headless");
////        BrowserFactory.closeAllWindows();
//        testOnAll3Browsers();
//    }
    }
}
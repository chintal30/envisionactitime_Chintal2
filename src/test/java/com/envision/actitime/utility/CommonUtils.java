package com.envision.actitime.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {
    public static void waitDefininte(int seconds){
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getCurrentDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_LLLL_yyyy_HH_mm_ss"));
    }
    public static void takePageVisibleScreenshot(){
        File screenshotFile = ((TakesScreenshot) BrowserFactory.openBrowser()).getScreenshotAs(OutputType.FILE);
        File toPNGFile=new File("actitime_screenshots/"+getCurrentDateTime()+".png");
        try {
            FileHandler.copy(screenshotFile,toPNGFile); // FileHandler from Selenium
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void takeFullScreenshot(){ // Using Ashot plugin
        Screenshot ss= new AShot().shootingStrategy(ShootingStrategies
                .viewportPasting(1000))
                .takeScreenshot(BrowserFactory.openBrowser());
        try {
            ImageIO.write(ss.getImage(),
                    "png",
                    new File("actitime_screenshots/"+getCurrentDateTime()+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void takeElementScreenshot(WebElement element){
        File screenshotFile = element.getScreenshotAs(OutputType.FILE);
        File toPNGFile=new File("actitime_screenshots/"+getCurrentDateTime()+".png");
        try {
            FileHandler.copy(screenshotFile,toPNGFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        WebDriver driver;
//        BrowserFactory.openBrowserAsPerPropertiesFileBrowser();
//        BrowserFactory.LaunchWebsite();
//        driver=BrowserFactory.openBrowser();
//        WebElement a1= driver.findElement(By.id("addpass-the-required-system-properties-while-running-the-client"));
//        takeElementScreenshot(a1);
//        BrowserFactory.closeAllWindows();
//    }
}

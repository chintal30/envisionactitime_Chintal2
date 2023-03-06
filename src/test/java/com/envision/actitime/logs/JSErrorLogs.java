package com.envision.actitime.logs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class JSErrorLogs {
    public static LogEntries getLogs(WebDriver driver) {
        return driver.manage().logs().get(LogType.BROWSER);
    }

    public static Boolean isLoginErrorLog(WebDriver driver) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        return logEntries.getAll().stream()
                .anyMatch(logEntry -> logEntry.getMessage().contains("An invalid email address was specified"));
    }

    public static Character xyz() {
        Character c = 'A';
        String s = String.valueOf(c), ss;
        ss = s.concat("Hi");
        int t = ss.length();
        int[] ii = {1, 2};
        System.out.println(ss + " " + t + " " + ii.length);
        return c;
    }

    public static int countWordsInGivenString(String string) {
        //String exp="one two three";
        String[] main = string.split(" ");
        return main.length;
    }

    public static void charType(char c) {
        if (c >= 'A' && c <= 'Z') {
            System.out.println("I am uppercase");
        } else if (c >= 'a' && c <= 'z') {
            System.out.println("I am lowercase");
        } else if (c >= '0' && c <= '9') {
            System.out.println("I am Number");
        } else {
            System.out.println("I am Special Character");
        }

    }

    public static void main(String[] args) {
        charType('o');

    }
}

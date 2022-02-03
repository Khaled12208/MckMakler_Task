package org.herokuapp.webuipages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PageBase {

    // Global variables for all pages
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions actions;

    // Constructor to init all web driver and js excutor
    protected PageBase(WebDriver webDriver) {
        this.driver = webDriver;
        this.js = ((JavascriptExecutor) driver);
        this.actions = new Actions(driver);

    }

    protected boolean isEnabled(WebElement element)
    {
        return element.isEnabled();
    }

    protected void clickElement (WebElement element)
    {
        element.click();
    }

    protected void writeTxtElement (WebElement element, String txt)
    {
        element.sendKeys(txt);
    }


}

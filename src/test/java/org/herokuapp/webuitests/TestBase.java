package org.herokuapp.webuitests;


import org.herokuapp.helpers.Helpers;
import org.herokuapp.webframework.testconfiguration.*;
import org.herokuapp.webframework.webdriverfactory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class TestBase
{

    protected WebDriver driver;
    Helpers help  = new Helpers();
    final TestConfiguration config = new TestConfigurationBuilder()
                .setBrowser(BrowserType.CHROME)
                .setBrowserCustomDimensions(BrowserSize.MAX)
                .setWindowType(ExecutionPrivacy.PUBLIC)
                .setExecutionMode(ExecutionMode.HEADFULL)
                .Build();
    @BeforeTest(alwaysRun = true)
    public void Browser_Init() throws Exception {
        driver = new DriverFactory(config).OpenBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://admin-advertisement.herokuapp.com/advertisements");

    }

    @AfterTest(alwaysRun = true)
    public void Browser_Termination() {
       driver.quit();
    }

}

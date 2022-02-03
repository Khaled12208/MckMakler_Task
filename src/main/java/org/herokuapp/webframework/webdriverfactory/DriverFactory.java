package org.herokuapp.webframework.webdriverfactory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.webframework.testconfiguration.TestConfiguration;
import org.openqa.selenium.WebDriver;


public class DriverFactory {

    // this class is the web driver factory where the browser driver is init

    private TestConfiguration configuration;
    private WebDriver driver;
    private String WebURL;
    protected static final Logger logger = LogManager.getLogger(DriverFactory.class);

    // constructor to take the config from config builder
    public DriverFactory(TestConfiguration configuration)
    {
        this.configuration=configuration;

    }

    // Function to return webdriver
    public WebDriver OpenBrowser() throws Exception {

        if(configuration.getBrowserType()!=null){
            switch (configuration.getBrowserType()) {
                case CHROME:
                    driver = new Chrome().GetBrowser(configuration);
                    driver =  SetupConfig(driver);
                    logger.debug("invoking Chrome web browser");
                    break;
                case EDGE:
                    driver = new Edge().GetBrowser(configuration);
                    driver =  SetupConfig(driver);
                    logger.debug("invoking Edge web browser");
                    break;

            }
            return driver;
        }else
        {
            logger.error("couldn't invoke web browser");
            throw new Exception(" Unable to create browser driver, make sure to set a browser type");

        }
    }

    // Function to return Setup the webDriver with the config
    private WebDriver SetupConfig(WebDriver driver) throws Exception {

        if (configuration.getBrowserSize()==null && configuration.getBrowseDimensions()!=null){
            logger.debug(" Custom browser size is : "+ configuration.getBrowseDimensions());
            driver.manage().window().setSize(configuration.getBrowseDimensions());
        }else if (configuration.getBrowserSize()!=null && configuration.getBrowseDimensions()==null) {

            if (configuration.getBrowserSize().toString().equalsIgnoreCase("MIN")) {
                logger.debug(" Custom size is : Min");
                driver.manage().window().minimize();

            } else {
                driver.manage().window().maximize();
                logger.debug(" Custom size is : Max");
            }
        }
        else if(configuration.getBrowserSize()==null && configuration.getBrowseDimensions()==null) {

            logger.error("couldn't Find valid browser size configuration ");
            throw new Exception(" Browser widows size conflict, Please Make sure to provide one way for windows size setting ");

        }else
        {
            throw new Exception(" Browser widows size conflict, Please Make sure to provide one way for windows size setting ");

        }
        return driver;
    }

}

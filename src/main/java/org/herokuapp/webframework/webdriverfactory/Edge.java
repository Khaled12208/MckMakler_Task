package org.herokuapp.webframework.webdriverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.webframework.testconfiguration.TestConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Edge implements Browser {

    // This Class is the Edge Browser initializer

    private WebDriver driver;
    private EdgeOptions options;
    private DesiredCapabilities capabilities;
    protected static final Logger logger = LogManager.getLogger(Edge.class);


    @Override
    public WebDriver GetBrowser(TestConfiguration config) throws Exception {
        try {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(SetBrowserOption(config));
            return driver;

        } catch (Exception e) {
            logger.error("Browser is not installed on the system");
            throw new Exception(" Please Make Sure that the Edge Browser is installed in your system : \n "+e.fillInStackTrace());

        }

    }

    private EdgeOptions SetBrowserOption(TestConfiguration config)
    {
        options = new EdgeOptions();
        capabilities= new DesiredCapabilities();
        if(config.getExecutionMode().toString().equalsIgnoreCase("HEADLESS"))
        {
            logger.debug("Browser mode set to be headless");
            options.addArguments("--headless");

        }
        if(config.getWindowType().toString().equalsIgnoreCase("PRIVATE"))
        {
            logger.debug("Browser mode set to be private");
            options.addArguments("inprivate");

        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return  options.merge(capabilities);

    }
}

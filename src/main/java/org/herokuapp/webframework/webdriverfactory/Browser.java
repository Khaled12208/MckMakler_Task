package org.herokuapp.webframework.webdriverfactory;

import org.herokuapp.helpers.Helpers;
import org.herokuapp.webframework.testconfiguration.TestConfiguration;
import org.openqa.selenium.WebDriver;

public interface Browser  {
    Helpers helper = new Helpers();

    WebDriver GetBrowser(TestConfiguration config) throws Exception ;

}

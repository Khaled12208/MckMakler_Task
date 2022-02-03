package org.herokuapp.webuitests;

import org.herokuapp.webuipages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomeTests extends TestBase{

    private HomePage home;

    @BeforeClass
    public void init() {
        home = new HomePage(driver);

    }

    @Test
    public void UC01_validateAdvertisementTableIsDisplayed()
    {
        Assert.assertTrue(home.getAddTable().isDisplayed());
    }

    @Test
    public void UC02_validateAddNewAdvertisementButtonIsEnabled()
    {
        Assert.assertTrue(home.getAddNewButton().isDisplayed());
    }
}

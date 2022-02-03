package org.herokuapp.webuitests;

import org.herokuapp.webuipages.HomePage;
import org.herokuapp.webuipages.NewAddPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class NewAddTests extends TestBase {

    private HomePage home;
    private NewAddPage advert;

    @BeforeClass
    public void init() {
        home = new HomePage(driver);
        home.addNewAdd();
        advert = new NewAddPage(driver);
    }

    @Test
    public void UC01_validatePageTitle() {
        Assert.assertEquals(advert.getPageTitle(), "Advertisement");

    }


    @Test
    public void UC02_validateNameLimitsCounter() {

        advert.enterAdvertTitle(help.getAlphaNumericString(60));
        Assert.assertEquals(advert.getNumberOfCharacters(), 60);

    }

    @Test
    public void UC03_validateNameLimitsWarning() {

        advert.enterAdvertTitle(help.getAlphaNumericString(60));
        Assert.assertTrue(advert.getNameAnimation().isEnabled());
        Assert.assertEquals(advert.getNameAnimation().getText().trim(), "Max length reached");

    }

    @Test
    public void UC04_validateNameIsMandatory() {

        advert.enterAdvertTitle();
        Assert.assertTrue(advert.getNameAnimation().isEnabled());
        Assert.assertEquals(advert.getNameAnimation().getText().trim(), "This is required");

    }


    @Test
    public void UC05_validatePriceSchema() {
        advert.enterAdvertPrice("100x");
        Assert.assertTrue(advert.getPriceAnimation().isEnabled());
        Assert.assertEquals(advert.getPriceAnimation().getText().trim(), "Invalid price(Valid currency in euros: 12,12)");
    }

    @Test
    public void UC06_validateSaveButtonIsDisabledWithInvalidParam() {
        advert.enterAdvertTitle(help.getAlphaNumericString(60));
        advert.enterAdvertPrice("100x");
        Assert.assertTrue(advert.getSaveButton().getAttribute("disabled") != null);


    }

    @Test
    public void UC07_validateSaveButtonIsEnabledWitValidParam() {
        advert.enterAdvertTitle(help.getAlphaNumericString(50));
        advert.enterAdvertPrice("100");
        Assert.assertTrue(advert.getSaveButton().getAttribute("disabled") == null);

    }



}

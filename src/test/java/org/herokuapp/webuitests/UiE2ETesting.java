package org.herokuapp.webuitests;

import org.herokuapp.webuipages.HomePage;
import org.herokuapp.webuipages.NewAddPage;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class UiE2ETesting extends TestBase {

    HomePage home;
    NewAddPage advert;
    Map<String, Object> testData;
    Map<String, Object> updateTestData;

    @BeforeClass()
    public void initTestData()
    {
        try {
            testData= help.readResourceFileAsMap("E2Etestdata.json", UiE2ETesting.class);
            updateTestData  = help.readResourceFileAsMap("E2EUpdatetestdata.json",UiE2ETesting.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void UC01_E2E_AddingNewAdd() throws Exception {

        // Create New Add and Assert is exist
        home = new HomePage(driver);
        home.addNewAdd();
        advert = new NewAddPage(driver);
        advert.enterAdvertTitle(testData.get("name").toString());
        advert.enterAdvertStreet(testData.get("street").toString());
        advert.enterAdvertRooms(testData.get("rooms").toString());
        advert.enterAdvertPrice(testData.get("price").toString());
        if (testData.get("status").toString().equalsIgnoreCase("true")) {
            advert.clickStatusCheckBox();
        }
        advert.clickSave();
        Assert.assertTrue(home.assertThatAddisAdded(testData));


    }

    @Test
    public void UC02_E2E_UpdatingAdd() throws Exception {

        //update Add Cart and assert
        home.visitAddCart(testData);
        advert.enterAdvertTitle(updateTestData.get("name").toString());
        advert.enterAdvertStreet(updateTestData.get("street").toString());
        advert.enterAdvertRooms(updateTestData.get("rooms").toString());
        advert.enterAdvertPrice(updateTestData.get("price").toString());
        if (updateTestData.get("status").toString().equalsIgnoreCase("true")) {
            advert.clickStatusCheckBox();
        }
        advert.clickSave();

        HomePage home2=new HomePage(driver);
        Assert.assertTrue(home2.assertThatAddisAdded(updateTestData));


    }




}

package org.herokuapp.webuipages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NewAddPage extends PageBase{

    @FindBy(xpath = "//div[@class='md-subhead']")
    WebElement addNewAdvertCardTitle;

    @FindBy(xpath = "//input[contains(@ng-model,'$ctrl.advertisement.name')]")
    WebElement advertNameLabel;

    @FindBy(xpath = "//input[contains(@ng-model,'$ctrl.advertisement.street')]")
    WebElement advertStreet;

    @FindBy(xpath = "//input[contains(@ng-model,'$ctrl.advertisement.rooms')]")
    WebElement advertRoom;

    @FindBy(xpath = "//input[contains(@ng-model,'$ctrl.advertisement.price')]")
    WebElement advertPrice;

    @FindBy(xpath = "//div[@class='md-container']")
    WebElement advertStatusCheckBox;

    @FindBy(xpath = "//button[contains(@ng-click,'$ctrl.cancel()')]")
    WebElement advertCancelButton;

    @FindBy(xpath = "//button[contains(@ng-click,'$ctrl.saveAdvertisementDetails()')]")
    WebElement advertSave;

    @FindBy(xpath = "//div[@class='md-char-counter']")
    WebElement titleCounter;

    @FindBy(xpath = "//div[contains(@ng-messages,'name.$error')]")
    WebElement nameAnimation;

    @FindBy(xpath = "//div[contains(@ng-messages,'price.$error')]")
    WebElement priceAnimation;



    public NewAddPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterAdvertTitle(String...title)
    {
        if(advertNameLabel.getText().isEmpty())
        {
            advertNameLabel.clear();
        }

        if(title.length == 0)
        {
            advertNameLabel.sendKeys(Keys.TAB);

        }else
        {
            writeTxtElement(advertNameLabel,title[0]);
            advertNameLabel.sendKeys(Keys.TAB);
        }

    }

    public void enterAdvertStreet(String street)
    {
        if(advertStreet.getText().isEmpty())
        {
            advertStreet.clear();
        }
        writeTxtElement(advertStreet,street);
        advertStreet.sendKeys(Keys.TAB);

    }
    public void enterAdvertRooms(String room)
    {
        if(advertRoom.getText().isEmpty())
        {
            advertRoom.clear();
        }
        writeTxtElement(advertRoom,room);
        advertRoom.sendKeys(Keys.TAB);

    }

    public void enterAdvertPrice(String price)
    {
        if(advertPrice.getText().isEmpty())
        {
            advertPrice.clear();
        }
        writeTxtElement(advertPrice,price);
        advertPrice.sendKeys(Keys.TAB);

    }

    public void clickStatusCheckBox()
    {

        clickElement(advertStatusCheckBox);
        advertPrice.sendKeys(Keys.TAB);

    }

    public String getPageTitle()
    {
        return addNewAdvertCardTitle.getText();
    }

    public void clickSave()
    {
        clickElement(advertSave);
    }

    public boolean checkSaveButtonIsEnabled()
    {
        return isEnabled(advertSave);
    }
    public int getNumberOfCharacters()
    {
        return Integer.parseInt(titleCounter.getText().split("/")[0].trim());
    }

    public WebElement getNameAnimation()
    {
        return nameAnimation;
    }

    public WebElement getPriceAnimation()
    {
        return priceAnimation;
    }

    public void clickCancel()
    {
        clickElement(advertCancelButton);
    }

    public WebElement getSaveButton()
    {
        return advertSave;
    }




}

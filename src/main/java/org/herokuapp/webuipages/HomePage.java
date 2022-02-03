package org.herokuapp.webuipages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HomePage extends PageBase {

    @FindBy(xpath = "//md-icon[@class='al-add__icon ng-scope material-icons']")
    private WebElement addAdvertButton;

    @FindBy(xpath = "//table[@class='md-table ng-isolate-scope']")
    private WebElement advertsTable;

    @FindBy(xpath = "//h2[@class='hbar__brand ng-binding']")
    private WebElement pageTitle;

    @FindAll(
            @FindBy(xpath = "//tbody//tr")
    )
    List<WebElement> rows; ;

    @FindBy(xpath = "//thead[contains(@class,'md-head')]")
    WebElement tableHead;


    public HomePage( WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public String getPageTitle()
    {
        return pageTitle.getText();
    }

    public void addNewAdd()
    {
        clickElement(addAdvertButton);
    }

    public WebElement getAddNewButton()
    {
        return addAdvertButton;
    }

    public WebElement getAddTable()
    {
        return tableHead;
    }

    public boolean assertThatAddisAdded(Map<String,Object> advertName)
    {
        int i =0 ,j = 0;
        int maxRows= rows.size();
        int maxCloumns=4;
        Boolean flag = false;

        List<List<String>> tablePaths = new LinkedList<>();
        for(i=1;i<=maxRows;i++)
        {
            List<String> row = new LinkedList<>();
            for(j=1;j<=maxCloumns;j++)
            {
                row.add("//tbody//tr["+i+"]/td["+j+"]");
            }
            tablePaths.add(row);
        }

        List<List<WebElement>> tableWebElemts = new LinkedList<>();
        for(i=0;i<maxRows;i++)
        {
            List<WebElement> rowElements = new LinkedList<>();
            for(j=0;j<maxCloumns;j++)
            {
                rowElements.add(driver.findElement(By.xpath(tablePaths.get(i).get(j))));
            }
            tableWebElemts.add(rowElements);
        }

        for(i=0;i<maxRows;i++)
        {

           if( tableWebElemts.get(i).get(0).getText().trim().equalsIgnoreCase(advertName.get("name").toString()) &&
         tableWebElemts.get(i).get(1).getText().trim().equalsIgnoreCase(advertName.get("street").toString()) &&
           tableWebElemts.get(i).get(2).getText().trim().equalsIgnoreCase(advertName.get("rooms").toString()) )
           {
               flag=true;
               break;

           }


        }
        return flag;
    }

    public void visitAddCart(Map<String,Object> advertName)
    {
        int i =0 ,j = 0;
        int maxRows= rows.size();
        int maxCloumns=4;
        Boolean flag = false;

        List<List<String>> tablePaths = new LinkedList<>();
        for(i=1;i<=maxRows;i++)
        {
            List<String> row = new LinkedList<>();
            for(j=1;j<=maxCloumns;j++)
            {
                row.add("//tbody//tr["+i+"]/td["+j+"]");
            }
            tablePaths.add(row);
        }

        List<List<WebElement>> tableWebElemts = new LinkedList<>();
        for(i=0;i<maxRows;i++)
        {
            List<WebElement> rowElements = new LinkedList<>();
            for(j=0;j<maxCloumns;j++)
            {
                rowElements.add(driver.findElement(By.xpath(tablePaths.get(i).get(j))));
            }
            tableWebElemts.add(rowElements);
        }

        for(i=0;i<maxRows;i++)
        {
            WebElement name=tableWebElemts.get(i).get(0);
            if( name.getText().trim().equalsIgnoreCase(advertName.get("name").toString()) &&
                    tableWebElemts.get(i).get(1).getText().trim().equalsIgnoreCase(advertName.get("street").toString()) &&
                    tableWebElemts.get(i).get(2).getText().trim().equalsIgnoreCase(advertName.get("rooms").toString()) )
            {
                js.executeScript("arguments[0].scrollIntoView(true);", name);
                name.click();
                break;
            }


        }
    }


}

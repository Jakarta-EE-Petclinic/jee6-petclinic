package org.woehlke.jee6.petclinic.web.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 27.01.14
 * Time: 09:23
 * To change this template use File | Settings | File Templates.
 */
public class ShowOwnerPage {

    @FindBy(id="showOwnerForm")
    private WebElement showOwnerForm;

    @FindBy(id="showOwnerForm:firstName")
    private WebElement firstName;

    @FindBy(id="showOwnerForm:lastName")
    private WebElement lastName;

    @FindBy(id="showOwnerForm:address")
    private WebElement address;

    @FindBy(id="showOwnerForm:city")
    private WebElement city;

    @FindBy(id="showOwnerForm:telephone")
    private WebElement telephone;

    @FindBy(id="showOwnerForm:edit")
    private WebElement edit;

    public void assertPageIsLoaded() {
        Assert.assertTrue(showOwnerForm.isDisplayed());
    }

    public void clickEditOwner() {
        edit.click();
    }

    public void assertContent(String firstName,
                              String lastName,
                              String address,
                              String city,
                              String telephone) {
        Assert.assertEquals(firstName,this.firstName.getText());
        Assert.assertEquals(lastName,this.lastName.getText());
        Assert.assertEquals(address,this.address.getText());
        Assert.assertEquals(city,this.city.getText());
        Assert.assertEquals(telephone,this.telephone.getText());
    }
}

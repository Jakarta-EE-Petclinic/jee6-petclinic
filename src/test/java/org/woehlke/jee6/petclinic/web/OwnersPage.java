package org.woehlke.jee6.petclinic.web;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 26.01.14
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class OwnersPage {

    @FindBy(id="owners")
    private WebElement owners;

    public void assertPageIsLoaded() {
        Assert.assertTrue(owners.isDisplayed());
    }
}

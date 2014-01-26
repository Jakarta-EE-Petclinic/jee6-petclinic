package org.woehlke.jee6.petclinic.web;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;
import java.util.logging.Logger;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 21.01.14
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
public class PetTypeTest {

    private static Logger log = Logger.getLogger(PetTypeTest.class.getName());

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return Deployments.createPetTypeDeployment();
    }

    @Drone
    WebDriver driver;

    @ArquillianResource
    URL deploymentUrl;

    @FindBy(id="petTypes")
    private WebElement petTypes;

    @FindBy(id="petTypesForm:getNewPetTypeForm")
    private WebElement getNewPetTypeForm;

    @FindBy(id="addNewPetType")
    private WebElement addNewPetType;

    @FindBy(id="addNewPetTypeForm:name")
    private WebElement name;

    @FindBy(id="addNewPetTypeForm:save")
    private WebElement save;

    @FindBy(id="petTypesForm:petTypesTable:0:name")
    private WebElement nameInTable;

    @FindBy(id="petTypesForm:petTypesTable:0:edit")
    private WebElement editInTable;

    @FindBy(id="editPetType")
    private WebElement editPetType;

    @FindBy(id="editPetTypeForm:name")
    private WebElement editName;

    @FindBy(id="editPetTypeForm:save")
    private WebElement editSave;

    @FindBy(id="petTypesForm:petTypesTable:0:delete")
    private WebElement deleteInTable;

    @Test
    @InSequence(1)
    @RunAsClient
    public void testOpeningHomePage() {
        String url = deploymentUrl.toExternalForm()+ "hello.jsf";
        log.info("url: "+url);
        driver.get(url);
        String pageTitle = driver.getTitle();
        log.info("pageTitle: " + pageTitle);
        Assert.assertEquals(pageTitle, "Petclinic");
    }

    @Test
    @InSequence(2)
    @RunAsClient
    public void testOpeningPetTypesPage() {
        String url = deploymentUrl.toExternalForm() + "petTypes.jsf";
        log.info("url: " + url);
        driver.get(url);
        Assert.assertTrue(petTypes.isDisplayed());
    }


    @Test
    @InSequence(3)
    @RunAsClient
    public void testNewPetTypePage() {
        String url = deploymentUrl.toExternalForm() + "petTypes.jsf";
        log.info("url: "+url);
        driver.get(url);
        Assert.assertTrue(petTypes.isDisplayed());
        getNewPetTypeForm.click();
        log.info("page: " + driver.getCurrentUrl());
        Assert.assertTrue(addNewPetType.isDisplayed());
        name.sendKeys("cat");
        save.click();
        Assert.assertTrue(petTypes.isDisplayed());
        Assert.assertEquals("cat", nameInTable.getText());
    }


    @Test
    @InSequence(4)
    @RunAsClient
    public void testEditPetTypePage() {
        String url = deploymentUrl.toExternalForm() + "petTypes.jsf";
        log.info("url: "+url);
        driver.get(url);
        Assert.assertTrue(petTypes.isDisplayed());
        editInTable.click();
        log.info("page: " + driver.getCurrentUrl());
        Assert.assertTrue(editPetType.isDisplayed());
        editName.clear();
        editName.sendKeys("dog");
        guardHttp(editSave).click();
        Assert.assertTrue(petTypes.isDisplayed());
        Assert.assertEquals("dog", nameInTable.getText());
    }

    @Test
    @InSequence(5)
    @RunAsClient
    public void testDeletePetTypePage() {
        String url = deploymentUrl.toExternalForm() + "petTypes.jsf";
        log.info("url: "+url);
        driver.get(url);
        Assert.assertTrue(petTypes.isDisplayed());
        guardHttp(deleteInTable).click();
        Assert.assertTrue(petTypes.isDisplayed());
        boolean isDeleted = false;
        try {
            Assert.assertEquals(null,nameInTable);
        } catch (NoSuchElementException elementException) {
            isDeleted = true;
        }
        Assert.assertTrue(isDeleted);
    }
}

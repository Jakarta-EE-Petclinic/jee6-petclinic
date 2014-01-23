package org.woehlke.jee6.petclinic.web;

import com.thoughtworks.selenium.DefaultSelenium;
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

import java.net.URL;
import java.util.logging.Logger;

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
    DefaultSelenium driver;

    @ArquillianResource
    URL deploymentUrl;

    @Test
    @InSequence(1)
    @RunAsClient
    public void testOpeningHomePage() {
        String url = deploymentUrl.toExternalForm();
        log.info("url: "+url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        String pageTitle = driver.getTitle();
        log.info("pageTitle: " + pageTitle);
        Assert.assertEquals(pageTitle, "Petclinic");
    }

    @Test
    @InSequence(2)
    @RunAsClient
    public void testOpeningPetTypesPage() {
        String url = deploymentUrl.toExternalForm() + "petTypes.xhtml";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        boolean isPresent = driver.isElementPresent("id=petTypes");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @InSequence(3)
    @RunAsClient
    public void testNewPetTypePage() {
        String url = deploymentUrl.toExternalForm() + "petTypes.xhtml";
        log.info("url: "+url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        boolean isPresent = driver.isElementPresent("id=petTypes");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.click("id=petTypesForm:getNewPetTypeForm");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        isPresent = driver.isElementPresent("id=addNewPetType");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.type("id=addNewPetTypeForm:name","cat");
        driver.click("id=addNewPetTypeForm:save");
        driver.waitForPageToLoad("15000");
        isPresent = driver.isElementPresent("id=petTypes");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'cat')]");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @InSequence(4)
    @RunAsClient
    public void testEditPetTypePage() {
        String url = deploymentUrl.toExternalForm() + "petTypes.xhtml";
        log.info("url: "+url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        boolean isPresent = driver.isElementPresent("id=petTypes");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.click("id=petTypesForm:petTypesTable:0:edit");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        isPresent = driver.isElementPresent("id=editPetType");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.type("id=editPetTypeForm:name","dog");
        driver.click("id=editPetTypeForm:save");
        driver.waitForPageToLoad("15000");
        isPresent = driver.isElementPresent("id=petTypes");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'dog')]");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }


    @Test
    @InSequence(5)
    @RunAsClient
    public void testDeletePetTypePage() {
        String url = deploymentUrl.toExternalForm() + "petTypes.xhtml";
        log.info("url: "+url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        boolean isPresent = driver.isElementPresent("id=petTypes");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.click("id=petTypesForm:petTypesTable:0:delete");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        isPresent = driver.isElementPresent("id=petTypes");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'dog')]");
        log.info("isPresent: " + isPresent);
        Assert.assertFalse(isPresent);
    }
}

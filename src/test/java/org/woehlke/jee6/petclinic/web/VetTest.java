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
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
public class VetTest {

    private static Logger log = Logger.getLogger(VetTest.class.getName());

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return Deployments.createVetDeployment();
    }

    @Drone
    DefaultSelenium driver;

    @ArquillianResource
    URL deploymentUrl;

    @Test
    @InSequence(1)
    @RunAsClient
    public void testOpeningHomePage() {
        String url = deploymentUrl.toExternalForm()+ "hello.jsf";
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
    public void testOpeningVetPage() {
        String url = deploymentUrl.toExternalForm() + "vets.jsf";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        boolean isPresent = driver.isElementPresent("id=veterinarians");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @InSequence(3)
    @RunAsClient
    public void testNewVetPage() {
        String url = deploymentUrl.toExternalForm() + "vets.jsf";
        log.info("url: "+url);
        driver.open(url);
        driver.click("id=veterinariansForm:getNewVetForm");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        boolean isPresent = driver.isElementPresent("id=addNewVeterinarian");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.type("id=addNewVeterinarianForm:firstName","Thomas");
        driver.type("id=addNewVeterinarianForm:lastName","Woehlke");
        driver.click("id=addNewVeterinarianForm:save");
        driver.waitForPageToLoad("15000");
        isPresent = driver.isElementPresent("id=veterinarians");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'Thomas')]");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @InSequence(4)
    @RunAsClient
    public void testEditVetPage() {
        String url = deploymentUrl.toExternalForm() + "vets.jsf";
        log.info("url: "+url);
        driver.open(url);
        driver.click("id=veterinariansForm:veterinariansTable:0:edit");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        boolean isPresent = driver.isElementPresent("id=editVeterinarian");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.type("id=editVeterinarianForm:firstName","Willy");
        driver.type("id=editVeterinarianForm:lastName","Wombel");
        driver.click("id=editVeterinarianForm:save");
        driver.waitForPageToLoad("15000");
        isPresent = driver.isElementPresent("id=veterinarians");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'Willy')]");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'Wombel')]");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'none')]");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @InSequence(5)
    @RunAsClient
    public void testDeleteVetPage() {
        String url = deploymentUrl.toExternalForm() + "vets.jsf";
        log.info("url: "+url);
        driver.open(url);
        driver.click("id=veterinariansForm:veterinariansTable:0:delete");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        boolean isPresent = driver.isElementPresent("id=veterinarians");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'Willy')]");
        log.info("isPresent: " + isPresent);
        Assert.assertFalse(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'Wombel')]");
        log.info("isPresent: " + isPresent);
        Assert.assertFalse(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'none')]");
        log.info("isPresent: " + isPresent);
        Assert.assertFalse(isPresent);
    }
}

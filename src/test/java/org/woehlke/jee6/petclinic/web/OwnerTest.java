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
 * Date: 22.01.14
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
public class OwnerTest {

    private static Logger log = Logger.getLogger(SpecialtiesTest.class.getName());

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return Deployments.createOwnerDeployment();
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
    public void testOpenFindOwnersPage() {
        String url = deploymentUrl.toExternalForm() + "findOwners.xhtml";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=findOwners"));
    }

    @Test
    @InSequence(3)
    @RunAsClient
    public void testOpenOwnersPage() {
        String url = deploymentUrl.toExternalForm() + "findOwners.xhtml";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=findOwners"));
        driver.click("id=findOwnersForm:search");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=owners"));
    }

    @Test
    @InSequence(4)
    @RunAsClient
    public void testOpenNewOwnerPage() {
        String url = deploymentUrl.toExternalForm() + "findOwners.xhtml";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=findOwners"));
        driver.click("id=findOwnersForm:getNewOwnerForm");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=addNewOwner"));
    }

    @Test
    @InSequence(5)
    @RunAsClient
    public void testOpenNewOwnerPageFromOwnersList() {
        String url = deploymentUrl.toExternalForm() + "findOwners.xhtml";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=findOwners"));
        driver.click("id=findOwnersForm:search");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=owners"));
        driver.click("id=ownersForm:getNewOwnerForm");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=addNewOwner"));
    }



    @Test
    @InSequence(6)
    @RunAsClient
    public void testAddNewOwner() {
        String url = deploymentUrl.toExternalForm() + "findOwners.xhtml";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=findOwners"));
        driver.click("id=findOwnersForm:search");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=owners"));
        driver.click("id=ownersForm:getNewOwnerForm");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=addNewOwner"));
        driver.type("addNewOwnerForm:firstName","Thomas");
        driver.type("addNewOwnerForm:lastName","Woehlke");
        driver.type("addNewOwnerForm:address","Schoenhauser Allee 42");
        driver.type("addNewOwnerForm:city","Berlin");
        driver.type("addNewOwnerForm:telephone","03012345678");
        driver.click("id=addNewOwnerForm:save");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=owners"));
        Assert.assertTrue(driver.isElementPresent("id=ownersForm:ownersTable:0:showOwner"));
        Assert.assertTrue(driver.isElementPresent("xpath=//td/a[contains(text(), 'Thomas')]"));
        Assert.assertTrue(driver.isElementPresent("xpath=//td/a[contains(text(), 'Woehlke')]"));
        Assert.assertTrue(driver.isElementPresent("xpath=//td[contains(text(), 'Schoenhauser Allee 42')]"));
        Assert.assertTrue(driver.isElementPresent("xpath=//td[contains(text(), 'Berlin')]"));
        Assert.assertTrue(driver.isElementPresent("xpath=//td[contains(text(), '03012345678')]"));
    }

    @Test
    @InSequence(7)
    @RunAsClient
    public void testEditOwner() {
        String url = deploymentUrl.toExternalForm() + "findOwners.xhtml";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=findOwners"));
        driver.click("id=findOwnersForm:search");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=owners"));
        driver.click("id=ownersForm:ownersTable:0:showOwner");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=showOwnerForm"));
        driver.click("id=showOwnerForm:edit");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=editOwnerForm"));
        driver.type("editOwnerForm:firstName","Willy");
        driver.type("editOwnerForm:lastName","Wombel");
        driver.type("editOwnerForm:address","Elbchaussee 242");
        driver.type("editOwnerForm:city","Hamburg");
        driver.type("editOwnerForm:telephone","04012345678");
        driver.click("id=editOwnerForm:save");
        driver.waitForPageToLoad("15000");
        Assert.assertTrue(driver.isElementPresent("id=showOwnerForm"));
        Assert.assertTrue(driver.isElementPresent("xpath=//span[contains(text(), 'Willy')]"));
        Assert.assertTrue(driver.isElementPresent("xpath=//span[contains(text(), 'Wombel')]"));
        Assert.assertTrue(driver.isElementPresent("xpath=//span[contains(text(), 'Elbchaussee 242')]"));
        Assert.assertTrue(driver.isElementPresent("xpath=//span[contains(text(), 'Hamburg')]"));
        Assert.assertTrue(driver.isElementPresent("xpath=//span[contains(text(), '04012345678')]"));

    }
}

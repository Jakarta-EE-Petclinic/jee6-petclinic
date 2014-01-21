package org.woehlke.jee6.petclinic.web;

import com.thoughtworks.selenium.DefaultSelenium;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
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
 * Date: 19.01.14
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
public class SpecialtiesTest {

    private static Logger log = Logger.getLogger(SpecialtiesTest.class.getName());

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return Deployments.createSpecialtiesDeployment();
    }

    @Drone
    DefaultSelenium driver;

    @ArquillianResource
    URL deploymentUrl;

    @Test
    @RunAsClient
    public void testOpeningHomePage() {
        String url = deploymentUrl.toExternalForm();
        log.info("url: "+url);
        driver.open(url);
        String pageTitle = driver.getTitle();
        log.info("pageTitle: " + pageTitle);
        Assert.assertEquals(pageTitle, "Petclinic");
    }

    @Test
    @RunAsClient
    public void testOpeningSpecialtiesPage() {
        String url = deploymentUrl.toExternalForm() + "specialties.xhtml";
        log.info("url: " + url);
        driver.open(url);
        boolean isPresent = driver.isElementPresent("id=specialties");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @RunAsClient
    public void testNewSpecialtyPage() {
        String url = deploymentUrl.toExternalForm() + "specialties.xhtml";
        log.info("url: "+url);
        driver.open(url);
        driver.click("id=specialtiesForm:addNewSpecialty");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        boolean isPresent = driver.isElementPresent("id=addNewSpecialty");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.type("id=addNewSpecialtyForm:name","dentist");
        driver.click("id=addNewSpecialtyForm:save");
        driver.waitForPageToLoad("15000");
        isPresent = driver.isElementPresent("id=specialties");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'dentist')]");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @RunAsClient
    public void testEditSpecialtyPage() {
        String url = deploymentUrl.toExternalForm() + "specialties.xhtml";
        log.info("url: "+url);
        driver.open(url);
        driver.click("id=specialtiesForm:specialtiesTable:0:edit");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        boolean isPresent = driver.isElementPresent("id=editSpecialty");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.type("id=editSpecialtyForm:name","specialist");
        driver.click("id=editSpecialtyForm:save");
        driver.waitForPageToLoad("15000");
        isPresent = driver.isElementPresent("id=specialties");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'specialist')]");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @RunAsClient
    public void testDeleteSpecialtyPage() {
        String url = deploymentUrl.toExternalForm() + "specialties.xhtml";
        log.info("url: "+url);
        driver.open(url);
        driver.click("id=specialtiesForm:specialtiesTable:0:delete");
        driver.waitForPageToLoad("15000");
        String page = driver.getLocation();
        log.info("page: "+page);
        boolean isPresent = driver.isElementPresent("id=specialties");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        isPresent = driver.isElementPresent("xpath=//td[contains(text(), 'specialist')]");
        log.info("isPresent: " + isPresent);
        Assert.assertFalse(isPresent);
    }

}

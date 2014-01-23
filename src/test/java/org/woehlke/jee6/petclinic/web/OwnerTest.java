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
        boolean isPresent = driver.isElementPresent("id=findOwners");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @InSequence(3)
    @RunAsClient
    public void testOpenOwnersPage() {
        String url = deploymentUrl.toExternalForm() + "findOwners.xhtml";
        log.info("url: " + url);
        driver.open(url);
        driver.waitForPageToLoad("15000");
        boolean isPresent = driver.isElementPresent("id=findOwners");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
        driver.click("id=findOwnersForm:search");
        driver.waitForPageToLoad("15000");
        isPresent = driver.isElementPresent("id=owners");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }
}

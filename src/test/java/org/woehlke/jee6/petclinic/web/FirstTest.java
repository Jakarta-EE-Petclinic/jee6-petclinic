package org.woehlke.jee6.petclinic.web;

import com.thoughtworks.selenium.DefaultSelenium;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

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
public class FirstTest {

    private static Logger log = Logger.getLogger(FirstTest.class.getName());

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return Deployments.createSpecialtiesDeployment();
    }

    @Drone
    DefaultSelenium selenium;

    @ArquillianResource
    URL deploymentUrl;

    @Test
    public void testOpeningHomePage() throws InterruptedException {
        String url = deploymentUrl.toExternalForm();
        log.info("url: "+url);
        selenium.open(url);
        String pageTitle = selenium.getTitle();
        log.info("pageTitle: " + pageTitle);
        Assert.assertEquals(pageTitle, "Petclinic");
    }

    @Test
    public void testOpeningSpecialtiesPage() throws InterruptedException {
        String url = deploymentUrl.toExternalForm() + "specialties.xhtml";
        log.info("url: "+url);
        selenium.open(url);
        selenium.waitForPageToLoad("15000");
        boolean isPresent = selenium.isElementPresent("id=specialties");
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    //@Test
    public void testOpeningNewSpecialtyPage() throws InterruptedException {
        String url = deploymentUrl.toExternalForm() + "specialties.xhtml";
        log.info("url: "+url);
        selenium.open(url);
        selenium.fireEvent("id=specialtiesForm:addNewSpecialty","click");
        selenium.waitForPageToLoad("15000");
        String page = selenium.getLocation();
        log.info("page: "+page);
        //Assert.assertEquals(page, "h2");
        /*
        String tagName = selenium.findElement(By.id("addNewSpecialty")).getTagName();
        log.info("tagName: " + tagName);
        Assert.assertEquals(tagName, "h2");
        */
    }
}

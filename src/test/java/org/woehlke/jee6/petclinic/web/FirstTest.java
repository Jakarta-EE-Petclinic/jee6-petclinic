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
public class FirstTest {

    private static Logger log = Logger.getLogger(FirstTest.class.getName());

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
    public void testOpeningHomePage() throws InterruptedException {
        String url = deploymentUrl.toExternalForm();
        log.info("url: "+url);
        driver.open(url);
        String pageTitle = driver.getTitle();
        log.info("pageTitle: " + pageTitle);
        Assert.assertEquals(pageTitle, "Petclinic");
    }

    @Test
    @RunAsClient
    public void testOpeningSpecialtiesPage() throws InterruptedException {
        String url = deploymentUrl.toExternalForm() + "specialties.xhtml";
        log.info("url: " + url);
        driver.open(url);
        boolean isPresent = driver.isElementPresent("id=specialties");// .findElement(By.id("specialties")).isDisplayed();
        log.info("isPresent: " + isPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    @RunAsClient
    public void testOpeningNewSpecialtyPage() throws InterruptedException {
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
    }

}

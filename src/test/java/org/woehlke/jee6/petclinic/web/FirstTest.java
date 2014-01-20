package org.woehlke.jee6.petclinic.web;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.woehlke.jee6.petclinic.dao.SpecialtyDao;
import org.woehlke.jee6.petclinic.dao.SpecialtyDaoImpl;
import org.woehlke.jee6.petclinic.entities.*;

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
    WebDriver driver;

    @ArquillianResource
    URL deploymentUrl;

    @Test
    public void testOpeningHomePage() throws InterruptedException {
        String url = deploymentUrl.toExternalForm();
        log.info("url: "+url);
        driver.get(url);
        String pageTitle = driver.getTitle();
        log.info("pageTitle: " + pageTitle);
        Assert.assertEquals(pageTitle, "Petclinic");
    }
}

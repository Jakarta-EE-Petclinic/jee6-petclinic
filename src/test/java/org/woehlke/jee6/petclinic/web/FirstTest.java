package org.woehlke.jee6.petclinic.web;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;


/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 19.01.14
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Arquillian.class)
public class FirstTest {

    @Drone
    WebDriver driver;

    @Test
    public void testOpeningHomePage() {

        driver.get("http://www.google.com");

        String pageTitle = driver.getTitle();

        Assert.assertEquals(pageTitle, "Google");
    }
}

package org.woehlke.jee6.petclinic.web;

import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.woehlke.jee6.petclinic.dao.PetTypeDao;
import org.woehlke.jee6.petclinic.dao.PetTypeDaoImpl;
import org.woehlke.jee6.petclinic.dao.SpecialtyDao;
import org.woehlke.jee6.petclinic.dao.SpecialtyDaoImpl;
import org.woehlke.jee6.petclinic.entities.*;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 20.01.14
 * Time: 08:45
 * To change this template use File | Settings | File Templates.
 */
public class Deployments {

    private static final String WEBAPP_SRC = "src/main/webapp";

    public static WebArchive createSpecialtiesDeployment() {
        File[] deps = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class, "specialties.war")
                .addClasses(SpecialtyController.class, LanguageBean.class,
                        SpecialtyDao.class, SpecialtyDaoImpl.class,
                        Owner.class, Pet.class, PetType.class,
                        Specialty.class, Vet.class, Visit.class)
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                        .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                        "/", Filters.include(".*\\.xhtml$"))
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("messages_de.properties")
                .addAsResource("messages_en.properties")
                .addAsLibraries(deps)
                .setWebXML("WEB-INF/web.xml");
    }

    public static WebArchive createPetTypeDeployment() {
        File[] deps = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        return ShrinkWrap.create(WebArchive.class, "pettypes.war")
                .addClasses(PetTypeController.class, LanguageBean.class,
                        PetTypeDao.class, PetTypeDaoImpl.class,
                        Owner.class, Pet.class, PetType.class,
                        Specialty.class, Vet.class, Visit.class)
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                        .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                        "/", Filters.include(".*\\.xhtml$"))
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("messages_de.properties")
                .addAsResource("messages_en.properties")
                .addAsLibraries(deps)
                .setWebXML("WEB-INF/web.xml");
    }
}

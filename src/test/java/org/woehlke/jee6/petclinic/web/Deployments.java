package org.woehlke.jee6.petclinic.web;

import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.woehlke.jee6.petclinic.dao.SpecialtyDao;
import org.woehlke.jee6.petclinic.dao.SpecialtyDaoImpl;
import org.woehlke.jee6.petclinic.entities.*;

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
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
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
                .addAsLibraries(resolver.artifacts(
                        "org.hibernate:hibernate-search",
                        "org.webjars:bootstrap",
                        "org.richfaces.ui:richfaces-components-ui",
                        "org.richfaces.core:richfaces-core-impl"
                ).resolveAsFiles())
                .setWebXML("WEB-INF/web.xml");
    }
}

package org.woehlke.jee6.petclinic.web;

import org.woehlke.jee6.petclinic.dao.SpecialtyDao;
import org.woehlke.jee6.petclinic.entities.Specialty;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.01.14
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class SpecialtyController implements Serializable {

    @EJB
    private SpecialtyDao specialtyDao;

    private Specialty specialty;

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Specialty> getSpecialties(){
        return specialtyDao.getAll();
    }

    public String getNewSpecialtyForm(){
        specialty = new Specialty();
        return "newSpecialty.xhtml";
    }

    public String saveNewSpecialty(){
        specialtyDao.addNew(this.specialty);
        return "specialties.xhtml";
    }
}

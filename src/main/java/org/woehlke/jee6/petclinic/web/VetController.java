package org.woehlke.jee6.petclinic.web;

import org.woehlke.jee6.petclinic.dao.VetDao;
import org.woehlke.jee6.petclinic.entities.Specialty;
import org.woehlke.jee6.petclinic.entities.Vet;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class VetController implements Serializable {

    @EJB
    private VetDao vetDao;

    private Vet vet;

    @ManagedProperty(value = "#{specialtyParser.specialtyList}")
    private List<Specialty> specialties;
    private List<Specialty> selectedSpecialties;

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public List<Specialty> getSelectedSpecialties() {
        return selectedSpecialties;
    }

    public void setSelectedSpecialties(List<Specialty> selectedSpecialties) {
        this.selectedSpecialties = selectedSpecialties;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public String getNewVetForm(){
        this.vet = new Vet();
        return "newVet.xhtml";
    }

    public String saveNewVet(){
        for(Specialty specialty:selectedSpecialties){
            this.vet.addSpecialty(specialty);
        }
        vetDao.addNew(this.vet);
        return "vets.xhtml";
    }

    public List<Vet> getVets(){
        return vetDao.getAll();
    }

    public String getEditForm(long id){
        this.vet = vetDao.findById(id);
        return "editVet.xhtml";
    }

    public String deleteVet(long id){
        this.vet = vetDao.findById(id);
        this.vet.removeSpecialties();
        vetDao.update(this.vet);
        vetDao.delete(id);
        return "vets.xhtml";
    }
}

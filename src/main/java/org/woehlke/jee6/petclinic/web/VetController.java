package org.woehlke.jee6.petclinic.web;

import org.woehlke.jee6.petclinic.dao.VetDao;
import org.woehlke.jee6.petclinic.entities.Specialty;
import org.woehlke.jee6.petclinic.entities.Vet;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        vetDao.addNew(this.vet);
        return "vets.xhtml";
    }

    public List<Vet> getVets(){
        /**
        Specialty s1 = new Specialty();
        s1.setName("dentistry");
        Specialty s2 = new Specialty();
        s2.setName("surgery");
        Set<Specialty> specialties = new HashSet<Specialty>();
        specialties.add(s1);
        specialties.add(s2);
        Specialty s3 = new Specialty();
        s3.setName("radiology");
        Set<Specialty> specialties2 = new HashSet<Specialty>();
        specialties2.add(s3);
        List<Vet> list = new ArrayList<Vet>();
        Vet v1 = new Vet();
        v1.setFirstName("Vorname 01");
        v1.setLastName("Nachname 01");
        v1.setSpecialties(specialties);
        Vet v2 = new Vet();
        v2.setFirstName("Vorname 02");
        v2.setLastName("Nachname 02");
        v2.setSpecialties(specialties2);
        Vet v3 = new Vet();
        v3.setFirstName("Vorname 03");
        v3.setLastName("Nachname 03");
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.addAll(vetDao.getAll());
        return list; */
        return vetDao.getAll();
    }
}

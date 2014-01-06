package org.woehlke.jee6.petclinic.web;

import org.woehlke.jee6.petclinic.dao.OwnerDao;
import org.woehlke.jee6.petclinic.entities.Owner;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 06.01.14
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class OwnerController implements Serializable {

    @EJB
    private OwnerDao ownerDao;

    private String searchterm;

    private List<Owner> ownerList;

    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public String search(){
        if(searchterm==null || searchterm.isEmpty()){
            this.ownerList = ownerDao.getAll();
        } else {
            try {
                this.ownerList = ownerDao.search(searchterm);
            } catch (Exception e){
                this.ownerList = ownerDao.getAll();
            }
        }
        return "owners.xhtml";
    }

    public String getNewOwnerForm(){
        this.owner = new Owner();
        return "newOwner.xhtml";
    }

    public String saveNewOwner(){
        ownerDao.addNew(this.owner);
        this.ownerList = ownerDao.getAll();
        return "owners.xhtml";
    }

    public String showOwner(long id){
        this.owner = ownerDao.findById(id);
        return "showOwner.xhtml";
    }

    public String getEditForm(){
        return "editOwner.xhtml";
    }

    public String saveEditedOwner(){
        ownerDao.update(this.owner);
        this.ownerList = ownerDao.getAll();
        return "showOwner.xhtml";
    }

    public String delete(long id){
        ownerDao.delete(id);
        this.ownerList = ownerDao.getAll();
        return "owners.xhtml";
    }

    public String getAddNewPetForm(){
        return "addNewPet.xhtml";
    }
}

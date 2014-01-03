package org.woehlke.jee6.petclinic.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private java.util.Set<Specialty> specialties;

    protected Set<Specialty> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<Specialty>();
        }
        return this.specialties;
    }

    @XmlElement
    public List<Specialty> getSpecialties() {
        List<Specialty> list = new ArrayList<Specialty>();
        for(Specialty s : getSpecialtiesInternal()){
            list.add(s);
        }
        Collections.sort(list);
        return list;
    }

    public String getSpecialtiesAsString(){
        StringBuilder stringBuilder = new StringBuilder();
        if(getNrOfSpecialties()==0){
            stringBuilder.append("none");
        } else {
            for(Specialty specialty : getSpecialties()){
                stringBuilder.append(specialty.getName());
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public void addSpecialty(Specialty specialty) {
        getSpecialtiesInternal().add(specialty);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}

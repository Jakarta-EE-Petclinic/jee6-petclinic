package org.woehlke.jee6.petclinic.entities;

import org.hibernate.search.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "owners")
@Indexed
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String lastName;

    @Column(name = "address")
    @NotEmpty
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String address;

    @Column(name = "city")
    @NotEmpty
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @IndexedEmbedded
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }


}

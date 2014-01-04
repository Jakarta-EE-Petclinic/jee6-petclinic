package org.woehlke.jee6.petclinic.web;

import org.woehlke.jee6.petclinic.entities.Specialty;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.01.14
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public class SpecialtyParser {

    private Iterable<? extends Specialty> specialtyList;

    public Iterable<? extends Specialty> getSpecialtyList() {
        return specialtyList;
    }

    public void setSpecialtyList(Iterable<? extends Specialty> specialtyList) {
        this.specialtyList = specialtyList;
    }
}

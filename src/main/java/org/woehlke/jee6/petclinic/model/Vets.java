package org.woehlke.jee6.petclinic.model;

import org.woehlke.jee6.petclinic.entities.Vet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 01.01.14
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class Vets {

    private List<Vet> vets;

    @XmlElement
    public List<Vet> getVetList() {
        if (vets == null) {
            vets = new ArrayList<Vet>();
        }
        return vets;
    }
}

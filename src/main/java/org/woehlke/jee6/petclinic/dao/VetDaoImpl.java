package org.woehlke.jee6.petclinic.dao;

import org.woehlke.jee6.petclinic.entities.Vet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 02.01.14
 * Time: 08:30
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class VetDaoImpl implements VetDao {

    private static Logger log = Logger.getLogger(VetDaoImpl.class.getName());

    @PersistenceContext(unitName="jee6petclinic")
    private EntityManager entityManager;

    @Override
    public List<Vet> getAll(){
        Query q = entityManager.createQuery("select v from Vet v");
        @SuppressWarnings("unchecked")
        List<Vet> list =  q.getResultList();
        return list;
    }

    @Override
    public void delete(long id) {
        Vet vet = entityManager.find(Vet.class, id);
        entityManager.remove(vet);
    }

    @Override
    public void addNew(Vet vet) {
        log.info("addNewVet: "+vet.toString());
        entityManager.persist(vet);
    }

    @Override
    public Vet findById(long id) {
        Vet address = entityManager.find(Vet.class, id);
        return address;
    }

    @Override
    public void update(Vet vet) {
        entityManager.merge(vet);
    }
}

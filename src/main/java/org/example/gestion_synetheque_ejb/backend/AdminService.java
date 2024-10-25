package org.example.gestion_synetheque_ejb.backend;


import jakarta.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class AdminService {
    @PersistenceContext
    private EntityManager em;

    public void ajouterCD(CD cd) {
        em.persist(cd);
    }

    public void modifierCD(CD cd) {
        em.merge(cd);
    }

    public void supprimerCD(Long id) {
        CD cd = em.find(CD.class, id);
        if (cd != null) em.remove(cd);
    }


}


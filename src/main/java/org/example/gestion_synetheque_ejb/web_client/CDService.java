package org.example.gestion_synetheque_ejb.web_client;

import jakarta.ejb.Stateless;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CDService {
    @PersistenceContext
    private EntityManager em;

    public List<CD> listerCD() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }

    public void emprunterCD(Long id) {
        CD cd = em.find(CD.class, id);
        if (cd != null && !cd.isEmprunte()) {
            cd.setEmprunte(true);
            em.merge(cd);
        }
    }

    public void retournerCD(Long id) {
        CD cd = em.find(CD.class, id);
        if (cd != null && cd.isEmprunte()) {
            cd.setEmprunte(false);
            em.merge(cd);
        }
    }
}


package org.example.gestion_synetheque_ejb.backend;

import javax.ejb.Stateless;
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
        // Logique pour marquer comme emprunté (par exemple avec un statut)
    }

    public void retournerCD(Long id) {
        CD cd = em.find(CD.class, id);
        // Logique pour marquer comme retourné
    }
}

package org.example.gestion_synetheque_ejb.web_client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class CD {
    @Id
    @GeneratedValue
    private Long id;
    private String titre;
    private String auteur;
    private boolean emprunte;  // Indique si le CD est emprunté

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public boolean isEmprunte() { return emprunte; }
    public void setEmprunte(boolean emprunte) { this.emprunte = emprunte; }
}

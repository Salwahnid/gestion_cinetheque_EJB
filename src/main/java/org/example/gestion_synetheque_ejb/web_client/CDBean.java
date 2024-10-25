package org.example.gestion_synetheque_ejb.web_client;

import jakarta.ejb.EJB;

import javax.faces.bean.ManagedBean;
import java.util.List;


@ManagedBean
public class CDBean {
    @EJB
    private CDService cdService;

    public List<CD> getCds() {
        return cdService.listerCD();
    }

    public String emprunter(Long id) {
        cdService.emprunterCD(id);
        return "listerCD.xhtml?faces-redirect=true";
    }
}


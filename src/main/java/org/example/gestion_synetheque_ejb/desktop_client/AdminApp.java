package org.example.gestion_synetheque_ejb.desktop_client;


import org.example.gestion_synetheque_ejb.backend.AdminService;
import org.example.gestion_synetheque_ejb.web_client.CD;

import javax.ejb.EJB;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminApp extends JFrame {

    @EJB
    private static AdminService adminService;

    private JTextField titreField = new JTextField(20);
    private JTextField auteurField = new JTextField(20);
    private JButton ajouterButton = new JButton("Ajouter CD");
    private JButton supprimerButton = new JButton("Supprimer CD");
    private JTable table;
    private List<CD> cds;

    public AdminApp() {
        setTitle("Gestion des CDs - Admin");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
        loadCDs();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Formulaire d'ajout de CD
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Titre:"));
        formPanel.add(titreField);
        formPanel.add(new JLabel("Auteur:"));
        formPanel.add(auteurField);
        formPanel.add(ajouterButton);
        panel.add(formPanel);

        // Table des CDs
        String[] columnNames = {"ID", "Titre", "Auteur"};
        table = new JTable(new Object[0][3], columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        // Bouton de suppression
        panel.add(supprimerButton);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterCD();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerCD();
            }
        });

        add(panel);
    }

    private void loadCDs() {

        Object[][] data = new Object[cds.size()][3];
        for (int i = 0; i < cds.size(); i++) {
            CD cd = cds.get(i);
            data[i][0] = cd.getId();
            data[i][1] = cd.getTitre();
            data[i][2] = cd.getAuteur();
        }
        table.setModel(new javax.swing.table.DefaultTableModel(
                data, new String[]{"ID", "Titre", "Auteur"}
        ));
    }

    private void ajouterCD() {
        String titre = titreField.getText();
        String auteur = auteurField.getText();
        if (!titre.isEmpty() && !auteur.isEmpty()) {
            org.example.gestion_synetheque_ejb.backend.CD cd = new org.example.gestion_synetheque_ejb.backend.CD();
            cd.setTitre(titre);
            cd.setAuteur(auteur);
            adminService.ajouterCD(cd);
            loadCDs();  // Recharger la liste des CDs
        }
    }

    private void supprimerCD() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Long cdId = (Long) table.getValueAt(selectedRow, 0);
            adminService.supprimerCD(cdId);
            loadCDs();  // Recharger la liste des CDs
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminApp().setVisible(true));
    }
}

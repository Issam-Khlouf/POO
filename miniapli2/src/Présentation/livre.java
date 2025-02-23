package Présentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class livre extends JFrame {
    private JTextField idField, titreField, auteurField, anneeField;
    private JButton ajouterButton, modifierButton, supprimerButton;
    private JTextArea resultArea;

    private Dao.databaseconnetio databaseconnetio;

    public livre(Dao.databaseconnetio databaseconnetio) {
        this.databaseconnetio =databaseconnetio;

        setTitle("Gestion des Livres");
        setSize(500, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        idField = new JTextField(10);
        titreField = new JTextField(20);
        auteurField = new JTextField(20);
        anneeField = new JTextField(10);

        ajouterButton = new JButton("Ajouter");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);

       
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10)); 
        JPanel panelGestion = new JPanel(new GridLayout(5, 2, 10, 10));

      
        panelGestion.add(new JLabel("ID Livre:"));
        panelGestion.add(idField);
        panelGestion.add(new JLabel("Titre:"));
        panelGestion.add(titreField);
        panelGestion.add(new JLabel("Auteur:"));
        panelGestion.add(auteurField);
        panelGestion.add(new JLabel("Année de Publication:"));
        panelGestion.add(anneeField);
        panelGestion.add(ajouterButton);
        panelGestion.add(modifierButton);
        panelGestion.add(supprimerButton);

        
        panelPrincipal.add(panelGestion, BorderLayout.NORTH);
        panelPrincipal.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        add(panelPrincipal);

      
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterLivre();
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierLivre();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerLivre();
            }
        });
    }

    private void ajouterLivre() {
        String titre = titreField.getText();
        String auteur = auteurField.getText();
        int annee = Integer.parseInt(anneeField.getText());

        String query = "INSERT INTO livre (titre, auteur, annee_publication) VALUES (?, ?, ?)";
        try (Connection connection = databaseconnetio.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, titre);
            ps.setString(2, auteur);
            ps.setInt(3, annee);
            ps.executeUpdate();
            resultArea.setText("Livre ajouté avec succès.");
        } catch (SQLException ex) {
            resultArea.setText("Erreur : " + ex.getMessage());
        }
    }

    private void modifierLivre() {
        int id = Integer.parseInt(idField.getText());
        String titre = titreField.getText();
        String auteur = auteurField.getText();
        int annee = Integer.parseInt(anneeField.getText());

        String query = "UPDATE livre SET titre = ?, auteur = ?, annee_publication = ? WHERE id_livre = ?";
        try (Connection connection = databaseconnetio.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, titre);
            ps.setString(2, auteur);
            ps.setInt(3, annee);
            ps.setInt(4, id);
            ps.executeUpdate();
            resultArea.setText("Livre modifié avec succès.");
        } catch (SQLException ex) {
            resultArea.setText("Erreur : " + ex.getMessage());
        }
    }

    private void supprimerLivre() {
        int id = Integer.parseInt(idField.getText());

        String query = "DELETE FROM livre WHERE id_livre = ?";
        try (Connection connection = databaseconnetio.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            resultArea.setText("Livre supprimé avec succès.");
        } catch (SQLException ex) {
            resultArea.setText("Erreur : " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
    	Dao.databaseconnetio databaseconnetio = new Dao.databaseconnetio();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new livre(databaseconnetio).setVisible(true);
            }
        });
    }
}
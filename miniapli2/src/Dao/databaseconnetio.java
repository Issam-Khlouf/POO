package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class databaseconnetio {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_bibliotheque";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

   
    public databaseconnetio() throws ClassNotFoundException {
        try {
          
            Class.forName("com.mysql.jdbc.Driver");

        
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie.");
        
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
    }

    
    public void ajouterLivre(String titre, String auteur, int anneePublication) {
        String query = "INSERT INTO livre (titre, auteur, annee_publication) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, titre);
            ps.setString(2, auteur);
            ps.setInt(3, anneePublication);
            ps.executeUpdate();
            System.out.println("Livre ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public void modifierLivre(int id, String titre, String auteur, int anneePublication) {
        String query = "UPDATE livre SET titre = ?, auteur = ?, annee_publication = ? WHERE id_livre = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, titre);
            ps.setString(2, auteur);
            ps.setInt(3, anneePublication);
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Livre modifié avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 
    public void supprimerLivre(int id) {
        String query = "DELETE FROM livre WHERE id_livre = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Livre supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public List<String> rechercherLivres(String titre) {
        List<String> livres = new ArrayList<>();
        String query = "SELECT * FROM livre WHERE titre LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + titre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    livres.add("ID: " + rs.getInt("id_livre") +
                            ", Titre: " + rs.getString("titre") +
                            ", Auteur: " + rs.getString("auteur") +
                            ", Année: " + rs.getInt("annee_publication"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

  
    public void fermerConnexion() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}
}
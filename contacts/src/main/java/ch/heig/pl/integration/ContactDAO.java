package ch.heig.pl.integration;

import ch.heig.pl.model.Contact;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class ContactDAO implements ContactDAOLocal, ContactDAORemote {

    @Resource(lookup = "java:global/ContactsInMem") // pour SGBD embarqué (H2)
//    @Resource(lookup = "jdbc/ContactsDS")  // pour SGBD externe
    private DataSource dataSource;

    /**
     * Création table contacts
     *   Le datasource doit être défini et le schéma doit exister
     *   (utilisé en phase développement seulement)
     */
    public void create() {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement
                        ("create table contacts (" +
                                "nom varchar(20) primary key," +
                                "telephone int," +
                                "conjoint varchar(20) references contacts)");){
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Liste de tous les contacts
     */
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM contacts c1 " +
                             "left outer join contacts c2 on c1.conjoint=c2.nom ");) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String nom1 = rs.getString(1);
                int telephone1 = rs.getInt(2);
                Contact contact1 = new Contact(nom1,telephone1);
                String conjoint1 = rs.getString(3);
                if (conjoint1 != null) {
                    String nom2 = rs.getString(4);
                    int telephone2 = rs.getInt(5);
                    Contact contact2 = new Contact(nom2,telephone2);
                    contact1.setConjoint(contact2);
                    contact2.setConjoint(contact1);
                }
                contacts.add(contact1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contacts;
    }

    /**
     * Ajoute un nouveau contact (sans conjoint)
     *   (doublons encore à gérer)
     */
    public void add(Contact contact) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement
                        ("insert into contacts (nom,telephone) values (?,?)");){
            pstmt.setString(1,contact.getNom());
            pstmt.setInt(2,contact.getTelephone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *  Met à jour un Contact
     *    (mise à jour du conjoint seulement)
     */
    public void updateConjoint(Contact contact) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement
                        ("update contacts set conjoint=? where nom=?");){
            pstmt.setString(1,contact.getConjoint().getNom());
            pstmt.setString(2,contact.getNom());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Obtention d'un contact
     *   obtention du contact conjoint s'il existe
     *   (contact inconnu encore à gérer)
     */
    public Contact getContact(String nom) {
        Contact contact1 = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "SELECT * FROM contacts c1 " +
                             "left outer join contacts c2 on c1.conjoint=c2.nom " +
                             "where c1.nom=?");) {
            pstmt.setString(1,nom);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String nom1 = rs.getString(1);
                int telephone1 = rs.getInt(2);
                contact1 = new Contact(nom1,telephone1);
                String conjoint1 = rs.getString(3);
                if (conjoint1 != null) {
                    String nom2 = rs.getString(4);
                    int telephone2 = rs.getInt(5);
                    Contact contact2 = new Contact(nom2,telephone2);
                    contact1.setConjoint(contact2);
                    contact2.setConjoint(contact1);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contact1;
    }
}

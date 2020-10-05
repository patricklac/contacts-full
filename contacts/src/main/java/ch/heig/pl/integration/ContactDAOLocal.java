package ch.heig.pl.integration;

import ch.heig.pl.model.Contact;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ContactDAOLocal {
    List<Contact> getContacts();
    void add(Contact contact);
    Contact getContact(String nom);
    void updateConjoint(Contact contact);
}

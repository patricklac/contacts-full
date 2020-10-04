package ch.heig.pl.integration;

import ch.heig.pl.model.Contact;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ContactDAORemote {
    List<Contact> getContacts();

    void add(Contact contact);

    void create();
}

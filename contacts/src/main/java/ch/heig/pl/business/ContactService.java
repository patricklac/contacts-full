package ch.heig.pl.business;

import ch.heig.pl.integration.ContactDAOLocal;
import ch.heig.pl.model.Contact;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class ContactService {

    @Inject
    ContactDAOLocal contactDAO;

    /**
     *  Unit 2 contacts s'ils existent et s'ils sont libres
     */
    public void unit(String nom1, String nom2) throws AlreadyCoupledException {
        Contact contact1 = contactDAO.getContact(nom1);
        Contact contact2 = contactDAO.getContact(nom2);
        if (contact1 != null && contact1.getConjoint() == null
                && contact2 != null && contact2.getConjoint() == null) {
            contact1.setConjoint(contact2);
            contactDAO.updateConjoint(contact1);
            contact2.setConjoint(contact1);
            contactDAO.updateConjoint(contact2);
        } else {
            throw new AlreadyCoupledException();
        }
    }

    /**
     * Liste des contacts en couple.
     *   seulement un contact par couple.
     */
    public Set<Contact> getContactsInCouple() {
        List<Contact> contacts = contactDAO.getContacts();
        Set<Contact> contactsInCouple = new HashSet<>();
        for (Contact contact : contacts) {
            Contact contact2 = contact.getConjoint();
            if (contact2 != null && !contactsInCouple.contains(contact2)) {
                contactsInCouple.add(contact);
            }
        }
        return contactsInCouple;
    }
}

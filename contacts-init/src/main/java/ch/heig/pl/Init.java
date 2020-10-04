package ch.heig.pl;


import ch.heig.pl.integration.ContactDAORemote;
import ch.heig.pl.model.Contact;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;

public class Init {

    public static void main(String... args) throws NamingException {

        Properties environment = new Properties();
        environment.put(INITIAL_CONTEXT_FACTORY, "fish.payara.ejb.rest.client.RemoteEJBContextFactory");
        environment.put(PROVIDER_URL, "http://localhost:8080/ejb-invoker");

        InitialContext ejbRemoteContext = new InitialContext(environment);

        ContactDAORemote contactDAO = (ContactDAORemote) ejbRemoteContext.lookup("java:global/contacts/ContactDAO!ch.heig.pl.integration.ContactDAORemote");
        contactDAO.create();
        contactDAO.add(new Contact("Pierre",1234));
        contactDAO.add(new Contact("Sylvie",1111));
        contactDAO.add(new Contact("Jean",2222));
        contactDAO.add(new Contact("Marie",3333));
        for (Contact contact : contactDAO.getContacts()) {
            System.out.println(contact);
        }
    }
}
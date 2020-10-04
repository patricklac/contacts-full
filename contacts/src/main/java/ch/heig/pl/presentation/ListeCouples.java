package ch.heig.pl.presentation;

import ch.heig.pl.business.ContactService;
import ch.heig.pl.model.Contact;
import ch.heig.pl.integration.ContactDAOLocal;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/listeCouples")
public class ListeCouples extends HttpServlet {

    @Inject
    private ContactDAOLocal contactDAO;

    @Inject
    private ContactService contactService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Contact> contactsInCouple = contactService.getContactsInCouple();
        request.setAttribute("contactsInCouple", contactsInCouple);
        request.getRequestDispatcher("/WEB-INF/pages/listeCouples.jsp").forward(request, response);
    }
}

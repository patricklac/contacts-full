package ch.heig.pl.presentation;

import ch.heig.pl.integration.ContactDAOLocal;
import ch.heig.pl.model.Contact;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ajoute")
public class Ajoute extends HttpServlet {

    @Inject
    private ContactDAOLocal contactDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/ajoute.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String telephone = request.getParameter("telephone");
        int tel = 0;

        List<String> errors = new ArrayList<>();
        if (nom == null || nom.trim().equals("")) {
            errors.add("Le nom doit être renseigné");
        }
        if (telephone == null || telephone.trim().equals("")) {
            errors.add("Le téléphone doit être renseigné");
        } else {
            try {
                tel = Integer.parseInt(telephone);

            } catch (NumberFormatException e ){
                errors.add("Le téléphone doit être numérique");
            }
        }

        request.setAttribute("nom", nom);
        request.setAttribute("telephone", telephone);

        if (errors.size() == 0) {
            contactDAO.add(new Contact(nom,tel));
            response.sendRedirect(request.getContextPath() + "/liste");
        } else {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/pages/ajoute.jsp").forward(request, response);
        }

    }

}

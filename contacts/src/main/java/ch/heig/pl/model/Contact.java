package ch.heig.pl.model;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private String nom;
    private int telephone;
    private Contact conjoint;

    public Contact() {
    }

    public Contact(String nom, int telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }

    public Contact getConjoint() {
        return conjoint;
    }

    public void setConjoint(Contact conjoint) {
        this.conjoint = conjoint;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public int getTelephone() {
        return telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return nom.equals(contact.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}

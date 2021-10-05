package ch.heig.pl.dto;

public class CoupleDTO {

    private String nom1;
    private String nom2;

    public CoupleDTO(String nom1, String nom2) {
        this.nom1 = nom1;
        this.nom2 = nom2;
    }

    public String getNom1() {
        return nom1;
    }

    public String getNom2() {
        return nom2;
    }
}

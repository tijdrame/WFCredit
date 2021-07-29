package com.boa.api.response;

public class Account {
    private String compte, agence, agenceLib, dateOuverture;

    public Account() {
    }

    public Account(String compte, String agence, String agenceLib, String dateOuverture) {
        this.compte = compte;
        this.agence = agence;
        this.agenceLib = agenceLib;
        this.dateOuverture = dateOuverture;
    }

    public String getCompte() {
        return this.compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getAgence() {
        return this.agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getAgenceLib() {
        return this.agenceLib;
    }

    public void setAgenceLib(String agenceLib) {
        this.agenceLib = agenceLib;
    }

    public String getDateOuverture() {
        return this.dateOuverture;
    }

    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Account compte(String compte) {
        setCompte(compte);
        return this;
    }

    public Account agence(String agence) {
        setAgence(agence);
        return this;
    }

    public Account agenceLib(String agenceLib) {
        setAgenceLib(agenceLib);
        return this;
    }

    public Account dateOuverture(String dateOuverture) {
        setDateOuverture(dateOuverture);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " compte='" + getCompte() + "'" +
            ", agence='" + getAgence() + "'" +
            ", agenceLib='" + getAgenceLib() + "'" +
            ", dateOuverture='" + getDateOuverture() + "'" +
            "}";
    }

}

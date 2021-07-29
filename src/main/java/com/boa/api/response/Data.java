package com.boa.api.response;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private String rcod, rmsg, client, civilite, nom, prenom, dateNaissance, situationFamiliale;
    private String identite, telephone, adresse, ville, profession, typeClient, naturePersonne, secteurActivite;
    private List<Account> accounts = new ArrayList<>();
    private String email, telGsm;


    public Data() {
    }

    public Data(String rcod, String rmsg, String client, String civilite, String nom, String prenom, String dateNaissance, String situationFamiliale, String identite, String telephone, String adresse, String ville, String profession, String typeClient, String naturePersonne, String secteurActivite, List<Account> accounts, String email, String telGsm) {
        this.rcod = rcod;
        this.rmsg = rmsg;
        this.client = client;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.situationFamiliale = situationFamiliale;
        this.identite = identite;
        this.telephone = telephone;
        this.adresse = adresse;
        this.ville = ville;
        this.profession = profession;
        this.typeClient = typeClient;
        this.naturePersonne = naturePersonne;
        this.secteurActivite = secteurActivite;
        this.accounts = accounts;
        this.email = email;
        this.telGsm = telGsm;
    }

    public String getRcod() {
        return this.rcod;
    }

    public void setRcod(String rcod) {
        this.rcod = rcod;
    }

    public String getRmsg() {
        return this.rmsg;
    }

    public void setRmsg(String rmsg) {
        this.rmsg = rmsg;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCivilite() {
        return this.civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSituationFamiliale() {
        return this.situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public String getIdentite() {
        return this.identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTypeClient() {
        return this.typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    public String getNaturePersonne() {
        return this.naturePersonne;
    }

    public void setNaturePersonne(String naturePersonne) {
        this.naturePersonne = naturePersonne;
    }

    public String getSecteurActivite() {
        return this.secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelGsm() {
        return this.telGsm;
    }

    public void setTelGsm(String telGsm) {
        this.telGsm = telGsm;
    }

    public Data rcod(String rcod) {
        setRcod(rcod);
        return this;
    }

    public Data rmsg(String rmsg) {
        setRmsg(rmsg);
        return this;
    }

    public Data client(String client) {
        setClient(client);
        return this;
    }

    public Data civilite(String civilite) {
        setCivilite(civilite);
        return this;
    }

    public Data nom(String nom) {
        setNom(nom);
        return this;
    }

    public Data prenom(String prenom) {
        setPrenom(prenom);
        return this;
    }

    public Data dateNaissance(String dateNaissance) {
        setDateNaissance(dateNaissance);
        return this;
    }

    public Data situationFamiliale(String situationFamiliale) {
        setSituationFamiliale(situationFamiliale);
        return this;
    }

    public Data identite(String identite) {
        setIdentite(identite);
        return this;
    }

    public Data telephone(String telephone) {
        setTelephone(telephone);
        return this;
    }

    public Data adresse(String adresse) {
        setAdresse(adresse);
        return this;
    }

    public Data ville(String ville) {
        setVille(ville);
        return this;
    }

    public Data profession(String profession) {
        setProfession(profession);
        return this;
    }

    public Data typeClient(String typeClient) {
        setTypeClient(typeClient);
        return this;
    }

    public Data naturePersonne(String naturePersonne) {
        setNaturePersonne(naturePersonne);
        return this;
    }

    public Data secteurActivite(String secteurActivite) {
        setSecteurActivite(secteurActivite);
        return this;
    }

    public Data accounts(List<Account> accounts) {
        setAccounts(accounts);
        return this;
    }

    public Data email(String email) {
        setEmail(email);
        return this;
    }

    public Data telGsm(String telGsm) {
        setTelGsm(telGsm);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " rcod='" + getRcod() + "'" +
            ", rmsg='" + getRmsg() + "'" +
            ", client='" + getClient() + "'" +
            ", civilite='" + getCivilite() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", situationFamiliale='" + getSituationFamiliale() + "'" +
            ", identite='" + getIdentite() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", ville='" + getVille() + "'" +
            ", profession='" + getProfession() + "'" +
            ", typeClient='" + getTypeClient() + "'" +
            ", naturePersonne='" + getNaturePersonne() + "'" +
            ", secteurActivite='" + getSecteurActivite() + "'" +
            ", accounts='" + getAccounts() + "'" +
            ", email='" + getEmail() + "'" +
            ", telGsm='" + getTelGsm() + "'" +
            "}";
    }
    

}


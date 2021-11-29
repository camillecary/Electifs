/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cary.moduleselectifs;

/**
 *
 * @author camille
 */
public class Personne {
    
    public String login; 
    public String mdp;
    public String nom;
    public String prenom;
    public String mail;

    public Personne(String login, String mdp, String nom, String prenom, String mail) {
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cary.moduleselectifs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author camille
 */
public class Admisnistrateur {

    public String fonction;

    public Admisnistrateur(String fonction) {
        this.fonction = fonction;
    }

    public static int createEtudiant(Connection con, int idpersonne, String prenom, String nom, String mail, String login, String mdp, int annee, String fonction, int role, int idclass)
            throws SQLException {
        // lors de la creation du PreparedStatement, il faut que je précise
        // que je veux qu'il conserve les clés générées
        try (PreparedStatement pst = con.prepareStatement(
                """
                insert into Etudiant (idpersonne,nom,prenom,mail,login,mdp,annee,fonction,role,idclasse) values (?,?,?,?,?,?,?,?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, idpersonne);
            pst.setString(2, nom);
            pst.setString(3, prenom);
            pst.setString(4, mail);
            pst.setString(5, login);
            pst.setString(6, mdp);
            pst.setInt(7, annee);
            pst.setString(8, fonction);
            pst.setInt(9, role);
            pst.setInt(10, idclass);

            pst.executeUpdate();

            // je peux alors récupérer les clés créées comme un result set :
            try (ResultSet rid = pst.getGeneratedKeys()) {
                // et comme ici je suis sur qu'il y a une et une seule clé, je
                // fait un simple next 
                rid.next();
                // puis je récupère la valeur de la clé créé qui est dans la
                // première colonne du ResultSet
                int id = rid.getInt(1);
                return id;
            }
        }
    }

    public static int createModule(Connection con, int idmodule, String intitule, String description, int nbrplaceM, int idgroupemodule)
            throws SQLException {
        // lors de la creation du PreparedStatement, il faut que je précise
        // que je veux qu'il conserve les clés générées
        try (PreparedStatement pst = con.prepareStatement(
                """
                insert into Etudiant (idmodule,intitule,description,nbrplaceM,idgroupemodule) values (?,?,?,?,?)
                """, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, idmodule);
            pst.setString(2, intitule);
            pst.setString(3, description);
            pst.setInt(4, nbrplaceM);
            pst.setInt(5, idgroupemodule);

            pst.executeUpdate();

            // je peux alors récupérer les clés créées comme un result set :
            try (ResultSet rid = pst.getGeneratedKeys()) {
                // et comme ici je suis sur qu'il y a une et une seule clé, je
                // fait un simple next 
                rid.next();
                // puis je récupère la valeur de la clé créé qui est dans la
                // première colonne du ResultSet
                int id = rid.getInt(1);
                return id;
            }
        }
    }

}

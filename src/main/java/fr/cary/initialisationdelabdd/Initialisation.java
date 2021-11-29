/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cary.initialisationdelabdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author camille
 */
public class Initialisation {

    public static Connection connectPostgresql(String host, int port,
            String database, String user, String pass)
            throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port + "/" + database, user, pass);
        // fixe le plus haut degré d'isolation entre transactions
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }

    public static void main1(String[] args) {
        try (Connection con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "pass")) {
            // testConnection(con);  // ici le programme
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Error(ex);
        }
    }

    public static void createSchema(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            // on veut que le schema soit entierement créé ou pas du tout
            // il nous faut plusieurs ordres pour créer le schema
            // on va donc explicitement gérer les connections
            con.setAutoCommit(false);
            st.executeUpdate(
                    """
            create table Personne(
             idpersonne integer primary key generated always as identity,
             nom varchar(50) not null,
             prenom varchar(50) not null,
             mail varchar(150) not null,
             login varchar(30) not null,
             mdp varchar(30) not null,
             annee integer not null,
             fonction integer,
             role integer not null,
             idclasse integer not null
            )
            """);

            st.executeUpdate(
                    """
            create table Module(
             idmodule integer primary key generated always as identity,
             intitule varchar(50) not null,
             description text,
             nbrplaceM integer not null,
             idgroupemodule integer not null


            )
            """);
            st.executeUpdate(
                    """
            create table Classe(
             idclasse integer primary key generated always as identity,
             classe varchar(30) not null,


            )
            """);
            st.executeUpdate(
                    """
            create table GroupeModule(
             idgroupemodule integer primary key generated always as identity,
             creneauhor integer not null

            )
            """);
            st.executeUpdate(
                    """
            create table Choix(
             idchoix integer primary key generated always as identity,
             ordre integer not null,
             idpersonne integer not null,
             idgroupemodule integer not null    
            )
            """);
            st.executeUpdate(
                    """
                    alter table Personne
                        add constraint fk_Personne_idclasse
                        foreign key (idclasse) references Personne(idclasse)
                    """);
            
            st.executeUpdate(
                    """
                    alter table Module
                        add constraint fk_Module_idgroupemodule
                        foreign key (idgroupemodule) references GroupeModule(idgroupemodule)
                    """);
            st.executeUpdate(
                    """
                    alter table Choix
                        add constraint fk_Choix_idpersonne
                        foreign key (idgroupemodule) references Choix(idpersonne)
                    """);
             st.executeUpdate(
                    """
                    alter table Choix
                        add constraint fk_Choix_idmodule
                        foreign key (idgroupemodule) references Choix(idmodule)
                    """);


            con.commit();
        } catch (SQLException ex) {
            // si quelque chose se passe mal, j'annule la transaction
            // avant de resignaler l'exception
            con.rollback();
            throw ex;
        } finally {
            // pour s'assurer que le autoCommit(true) reste le comportement
            // par défaut (utile dans la plupart des "select"
            con.setAutoCommit(true);
        }
    }

    public static void deleteSchema(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            con.setAutoCommit(false);
            st.executeUpdate(
                    """
               alter table Personne 
                 drop constraint fk_Personne_idclasse
               """);
             st.executeUpdate(
                    """
               alter table Module
                 drop constraint fk_Module_idgroupemodule
               """);
              st.executeUpdate(
                    """
               alter table Choix fk_Choix_idpersonne
                 drop constraint 
               """);
            st.executeUpdate(
                    """
               alter table Choix 
                 drop constraint fk_Choix_idmodule
               """);
            st.executeUpdate("drop table Personne");
            st.executeUpdate("drop table Module ");
            st.executeUpdate("drop table GroupeModule");
            st.executeUpdate("drop table  Choix");
            st.executeUpdate("drop table  Classe");

            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static void main(String[] args) {
        try (Connection con = connectPostgresql(
                "localhost", 5432,
                "postgres", "postgres", "pass")) {
            System.out.println("Connexion OK");
            try {
                deleteSchema(con);

            } catch (Exception ex) {
                System.out.println("Problème de suppression");
            }
//            createSchema(con);

        } catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }

    }
}

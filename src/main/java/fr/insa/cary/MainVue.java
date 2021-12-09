package fr.insa.cary;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("") 


public class MainVue extends VerticalLayout { 



  public MainVue() {

    H1 titre = new H1("Inscription aux modules d'enseignement électifs");
        titre.getElement().getStyle().set("font-size", "40px");
        titre.getElement().getStyle().set("text-align", "center");
        titre.getElement().getStyle().set("background-color", "DodgerBlue");
    
     H3 intro = new H3("Entrez votre identifiant et le mot de passe qui vous a été attribué.");   
        intro.getElement().getStyle().set("text-align", "left");
        
    TextField zoneidentifiant = new TextField();;
        zoneidentifiant.setLabel("Identifiant");
        zoneidentifiant.setRequiredIndicatorVisible(true);
        zoneidentifiant.setErrorMessage("Ce champ est obligatoire");
    
    TextField zonemdp = new TextField();;
        zonemdp.setLabel("Mot de passe");
        zonemdp.setRequiredIndicatorVisible(true);
        zonemdp.setErrorMessage("Ce champ est obligatoire");
        
    Button effacer = new Button("Effacer"); 
    
    Button connecter = new Button("Connecter"); 
    
    
    effacer.addClickListener(click -> { 
      zoneidentifiant.setValue("");
      zonemdp.setValue("");
    });
    
    connecter.addClickShortcut(Key.ENTER);  //permet de se connecter en tapant ENTER (et pas qu'en cliquant sur Connecter)

    HorizontalLayout boutons = new HorizontalLayout(effacer,connecter);
    boutons.getElement().getStyle().set("text-align", "center");
             
    add(titre);

    add(intro);
    
    add( 
      new HorizontalLayout(
        zoneidentifiant,
        zonemdp
      ));
    
    add(
    boutons
      );
    
  }
}


    

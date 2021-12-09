/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cary;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 *
 * @author camille
 */
public class VuePrincipale extends VerticalLayout{
private Button vbCoucou;
public VuePrincipale() {
this.vbCoucou = new Button("dis Coucou"); this.vbCoucou.addClickListener((t) -> {
            Notification.show("Coucou");
        });
this.add(this.vbCoucou); }


}
  
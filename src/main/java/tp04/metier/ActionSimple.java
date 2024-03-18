/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Représente une action simple.
 * Une action simple est une action avec un libellé et des cours enregistrés pour différents jours.
 * Cette classe hérite de la classe abstraite Action.
 *
 * @author Moli, Nguyen
 *
 * 18/03/2024
 *
 * Copyright
 */

public class ActionSimple extends Action {

    // attribut lien
    private ArrayList<Cours> listeCours;

    
    /**
     * Constructeur de la classe ActionSimple.Initialise une nouvelle instance d'une action simple avec le libellé spécifié.
     * 
     * @param libelle Le libellé de l'action simple.
     */    
    public ActionSimple(String libelle) throws IllegalStateException {
        // Action simple initialisée comme 1 action
        super(libelle);
        if ("".equals(libelle)){
          throw new IllegalStateException("ActionSimple ne peux pas avoir un libelle vide");
        }
        // init spécifique
        this.listeCours = new ArrayList<>();
    }

    /**
     * Enregistre un cours pour un jour spécifique.Si aucun cours n'est enregistré pour le jour donné, un nouveau cours est créé avec la valeur spécifiée.
     * 
     * @param cours La valeur du cours.
     */
    public void enrgCours(Cours cours) throws IllegalStateException {
        if (this.listeCours.contains(cours)) {
            throw new IllegalStateException("Ce cours existe déjà dans la liste pour cette action");
        }
        else {
            this.listeCours.add(cours);
        }
    }
    
    //Fonction permettant d'afficher les cours d'une action simple
    public void affichageCours () {
    	for(Cours c : this.listeCours) {
            System.out.println(c.getJour() + " - Valeur : " + c.getValeur());
    	}
    }
    
    @Override
    public float valeur(Jour j) {
        for(Cours c : this.listeCours) {
            if (c.getJour().equals(j)) {
                return c.getValeur();
            }
        }
    	return 0;
    }
}

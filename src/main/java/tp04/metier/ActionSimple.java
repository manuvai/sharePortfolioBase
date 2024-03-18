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
 * Démarche de développement agile
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
    
    // constructeur
    public ActionSimple(String libelle) throws Exception {
        // Action simple initialisée comme 1 action
        super(libelle);
        if (libelle == ""){
          throw new Exception("ActionSimple ne peux pas avoir un libelle vide");
        }
        // init spécifique
        this.listeCours = new ArrayList<Cours>();
    }

    // Enrg possible si pas de cours pour ce jour
    public void enrgCours(Cours cours) throws Exception {
    	        if (this.listeCours.contains(cours)) {
    	          throw new Exception("Ce cours existe déjà dans la liste pour cette action");
    	        }
    	        else {
    	        	 this.listeCours.add(cours);
    	        	 System.out.println("Le cours : " + cours.getJour() + " a bien été ajouté a la liste");
    	        }
    	    }
    
    //Fonction permettant d'afficher les cours d'une action simple
    public void affichageCours () {
    	System.out.println("Affichage de la liste des cours pour : " + this.getLibelle());
    	for(Cours c : this.listeCours) {
    		System.out.println(c.getJour() + " - Valeur : " + c.getValeur());
    	}
    }

    @Override
    public float valeur(Jour j) {
    	return 0;
    }
}
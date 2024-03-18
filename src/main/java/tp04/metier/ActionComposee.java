/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Moli Nguyen
 */
public class ActionComposee extends Action {

    // attribut lien
    Map<ActionSimple, Float> mapPanier;

    public ActionComposee(String libelle) throws Exception {
        super(libelle);
        // Vérification permettant de vérifier le constructeur
        if (libelle == ""){
          throw new Exception("ActionComposée ne peux pas avoir un libelle vide");
        }
        this.mapPanier = new HashMap();
    }

     // Fonction permettant d'enregistrer une action simple 
    public void enrgComposition(ActionSimple as, float pourcentage) throws Exception {
        if (pourcentage <= 0) {
            throw new Exception("Le pourcentage ne peux pas être inférieur ou égale a 0");
            }
        else {
            System.out.println("L'action simple  : " + as.getLibelle() + " a bien été enregistré");
            this.mapPanier.put(as, pourcentage);
        }
    }
    
    // Fonction permettant d'afficher la liste des cours d'une action composée
    public void affichageCours () {
    	System.out.println("Affichage de la liste des cours pour : " + this.getLibelle());
     
        //Parcours le Map
    	for(ActionSimple as : this.mapPanier.keySet()) {
    		System.out.println(as.getLibelle() + "-pourcentage : " + mapPanier.get(as));
    	}
    }

    @Override
    public float valeur(Jour j) {
        float valeur;

        valeur = 0;
        for (ActionSimple as : this.mapPanier.keySet()) {
            valeur = valeur + (as.valeur(j) * this.mapPanier.get(as));
        }

        return valeur;
    }

}

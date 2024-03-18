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
 * Représente une action composée.
 * Une action composée est une combinaison d'actions simples avec des pourcentages associés à chacune d'elles.
 * Cette classe hérite de la classe abstraite Action.
 * @author Moli Nguyen
 */
public class ActionComposee extends Action {

    // Map pour stocker les actions simples avec leur pourcentage dans le panier de l'action composée
    Map<ActionSimple, Float> mapPanier;


     /**
     * Constructeur de la classe ActionComposee.
     * Initialise une nouvelle instance d'une action composée avec le libellé spécifié.
     * 
     * @param libelle Le libellé de l'action composée.
     */
    public ActionComposee(String libelle) throws Exception {
        super(libelle);
        // Vérification permettant de vérifier le constructeur
        if ("".equals(libelle)){
          throw new Exception("ActionComposée ne peux pas avoir un libelle vide");
        }
        this.mapPanier = new HashMap();
    }
    
    /**
     * Enregistre une action simple avec son pourcentage dans le panier de l'action composée.
     * 
     * @param as L'action simple à ajouter au panier.
     * @param pourcentage Le pourcentage associé à l'action simple dans le panier.
     */

     // Fonction permettant d'enregistrer une action simple 
    public void enrgComposition(ActionSimple as, float pourcentage) throws Exception {
        if (pourcentage <= 0) {
            throw new Exception("Le pourcentage ne peux pas être inférieur ou égale a 0");
            }
        else {
            this.mapPanier.put(as, pourcentage);
        }
    }
    
    // Fonction permettant d'afficher la liste des cours d'une action composée
    public void affichageCours () {     
        //Parcours le Map
    	for(ActionSimple as : this.mapPanier.keySet()) {
    		System.out.println(as.getLibelle() + "-pourcentage : " + mapPanier.get(as));
    	}
    }

    @Override
    public float valeur(Jour j) {
        float valeur;

        valeur = 0;
        // Parcours toutes les actions simples dans le panier
        for (ActionSimple as : this.mapPanier.keySet()) {
        // Calcule la valeur de l'action simple pour le jour donné et multiplie par le pourcentage
            valeur = valeur + (as.valeur(j) * this.mapPanier.get(as));
        }

        return valeur;
    }
}

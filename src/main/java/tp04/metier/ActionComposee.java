/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Représente une action composée.
 * Une action composée est une combinaison d'actions simples avec des pourcentages associés à chacune d'elles.
 * Cette classe hérite de la classe abstraite Action.
 * 
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
    public ActionComposee(String libelle) throws IllegalStateException {
        super(libelle);
        // Vérification permettant de vérifier le constructeur
        if ("".equals(libelle)){
          throw new IllegalStateException("ActionComposée ne peux pas avoir un libelle vide");
        }
        this.mapPanier = new HashMap<>();
    }
    
    public void setListeActions(ArrayList<Action> actionsS) {
        
    }
    
    /**
     * Enregistre une action simple avec son pourcentage dans le panier de l'action composée.
     * 
     * @param as L'action simple à ajouter au panier.
     * @param pourcentage Le pourcentage associé à l'action simple dans le panier.
     */

     // Fonction permettant d'enregistrer une action simple 
    public void enrgComposition(ActionSimple as, float pourcentage) throws IllegalStateException {
        if (pourcentage <= 0) {
            throw new IllegalStateException("Le pourcentage ne peux pas être inférieur ou égale a 0");
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
    
    //Fonciton permettant d'afficher le pourcentage d'une action simple
    public float affichagePourcentageActionSimple(ActionSimple actionSimple) {     
        //Parcours le Map
    	for(Map.Entry<ActionSimple, Float> entry : this.mapPanier.entrySet()) {
            if (entry.getKey().equals(actionSimple)) {
                return entry.getValue();
            }
        }
        return 0;
    }
    
    
    // Fonction permettant de supprimer une action composée
    public void suppressionCours(ActionSimple as) throws Exception {
        //verification du cours dans la Map
        if (this.mapPanier.containsKey(as)) {
           this.mapPanier.remove(as);
        }
        else {
            throw new Exception("Ce cours n'existe pas dans la liste pour cette action");
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
    
    
    /**
     * Affiche les cours de l'action composée pour une période donnée.
     *
     * @param dateDebut La date de début de la période.
     * @param dateFin La date de fin de la période.
     * @return Une map contenant les cours de l'action composée pour chaque jour de la période,
     *         ou null si les dates sont invalides.
     *
     * @throws IllegalArgumentException si les dates ne sont pas dans la même année
     *                                  ou si la date de début est supérieure à la date de fin.
     */
    public Map<Jour, Double> afficherCoursPeriode(Jour dateDebut, Jour dateFin) {
        int anneeDebut = dateDebut.getAnnee();
        int anneeFin = dateFin.getAnnee();

        if (anneeDebut != anneeFin) {
            throw new IllegalArgumentException("Veuillez entrer la date de la même année");
        }
        int jourDebut = dateDebut.getNoJour();
        int jourFin = dateFin.getNoJour();
        if (jourDebut > jourFin) {
            throw new IllegalArgumentException("La date début doit être inférieure à la date fin !");
        }

        Map<Jour, Double> mapCours = new HashMap<>();
        for (int j = jourDebut; j <= jourFin; j++) {
            Jour currentJour = new Jour(anneeDebut, j);
            double coursTotal = 0;
            for (Map.Entry<ActionSimple, Float> entry : mapPanier.entrySet()) {
                double pourcentage = entry.getValue();
                double cours = entry.getKey().valeur(currentJour);
                coursTotal += pourcentage * cours;
            }
            mapCours.put(currentJour, coursTotal);
        }

        return mapCours;
    }


}

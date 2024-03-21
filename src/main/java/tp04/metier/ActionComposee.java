/*
 * Copyright 2024 Usuari.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Représente une action composée.
 * Une action composée est une combinaison d'actions simples.
 * avec des pourcentages associés à chacune d'elles.
 * Cette classe hérite de la classe abstraite Action.
 * 
 * @author Moli Nguyen
 */
public class ActionComposee extends Action {

     /**
     * Map pour stocker les actions simples avec.
     * leur pourcentage dans le panier de l'action composée.
     */
    private Map<ActionSimple, Float> mapPanier;

    /**
     * Constructeur de la classe ActionComposee.
     * Initialise une nouvelle instance d'une.
     * action composée avec le libellé spécifié.
     * @param libelle Le libellé de l'action composée.
     */
    public ActionComposee(final String libelle) throws IllegalStateException {
        super(libelle);
        // Vérification permettant de vérifier le constructeur
        if ("".equals(libelle)) {
          throw new IllegalStateException("Le libelle est vide");
        }
        this.mapPanier = new HashMap<>();
    }

    /**
     * Enregistre une action simple avec son pourcentage.
     * dans le panier de l'action composée.
     * @param as L'action simple à ajouter au panier.
     * @param p associé à l'action simple dans le panier.
     */
    public void enrgComposition(ActionSimple as, float p ) throws IllegalStateException {
        if (p <= 0) {
            throw new IllegalStateException("Le p inférieur ou égale a 0");
        } else {
            this.mapPanier.put(as, p);
        }
    }

     /**
     * Afficher la liste des cours d'une action composée
     * dans le panier de l'action composée.
     */
    public void affichageCours() {     
        //Parcours le Map
        for(ActionSimple as : this.mapPanier.keySet()) {
            System.out.println(as.getLibelle() + " : " + mapPanier.get(as) + " % ");
    	}
    }
 
     /**
     * Afficher le pourcentage d'une action simple
     * @param actionSimple ActionSimple
     */
    public float affichagePourcentageActionSimple(ActionSimple actionSimple) {     
        //Parcours le Map
    	for (Map.Entry<ActionSimple, Float> entry : this.mapPanier.entrySet()) {
            if (entry.getKey().equals(actionSimple)) {
                return entry.getValue();
            }
        }
        return 0f;
    }

    /**
     * Suppression d'un cours d'une action simple.
     * @param as ActionSimple.
     * @throws java.lang.Exception
     * @throws java.lang.Exception.
     */
    public final void suppressionCours(final ActionSimple as) throws Exception {
        //verification du cours dans la Map
        if (this.mapPanier.containsKey(as)) {
           this.mapPanier.remove(as);
        } else {
            throw new Exception("Ce cours n'existe pas");
        }
    }

     /**
     * Suppression d'un cours d'une action simple
     * @param j Jour
     * @return valeur
     */
    @Override
    public final float valeur(Jour j) {
        float valeur;
        valeur = 0;
        // Parcours toutes les actions simples dans le panier
        for (ActionSimple as : this.mapPanier.keySet()) {
        // Calcule la valeur de l'action simple pour le jour donné
            valeur = valeur + (as.valeur(j) * this.mapPanier.get(as));
        }
        return valeur;
    }

    /**
     * Affiche les cours de l'action composée pour une période donnée.
     * @param debut La date de début de la période.
     * @param fin La date de fin de la période.
     * @return Une map contenant les cours de l'action.
     * composée pour chaque jour de la période.
     * @throws IllegalArgumentException si les dates.
     * ne sont pas dans la même année.
     * ou si la date de début est supérieure à la date de fin.
     */
    public final Map<Jour, Double> afficherCoursPeriode(Jour debut, Jour fin) {
        int anneeDebut = debut.getAnnee();
        int anneeFin = fin.getAnnee();

        if (anneeDebut != anneeFin) {
            throw new IllegalArgumentException("Date différente");
        }
        int jourDebut = debut.getNoJour();
        int jourFin = fin.getNoJour();
        if (jourDebut > jourFin) {
            throw new IllegalArgumentException("Date supérieur date fin");
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

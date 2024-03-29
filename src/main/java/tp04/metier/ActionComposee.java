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
 * Une action composée est une combinaison
 * d'actions simples avec des pourcentages associés à chacune d'elles.
 * Cette classe hérite de la classe abstraite Action.
 *
 * @author Moli Nguyen
 */
public class ActionComposee extends AbstractAction {

    /**
     * Map pour stocker les actions simples.
     * avec leur pourcentage dans le panier de l'action composée.
     */
    private Map<ActionSimple, Float> mapPanier;

    /**
     * Constructeur de la classe ActionComposee.
     * Initialise une nouvelle instance d'une action composée avec le libellé spécifié.
     *
     * @param libelle Le libellé de l'action composée.
     */
    public ActionComposee(final String libelle) throws IllegalStateException {
        super(libelle);
        // Vérification permettant de vérifier le constructeur
        if ("".equals(libelle)) {
            throw new IllegalStateException(
            "ActionComposée ne peux pas avoir un libelle vide"
            );
        }
        this.mapPanier = new HashMap<>();
    }

    /**
     * Enregistre une action simple avec son pourcentage dans le panier de l'action composée.
     *
     * @param as L'action simple à ajouter au panier.
     * @param pourcentage Le pourcentage associé à l'action simple dans le panier.
     * @throws IllegalStateException Une erreur levée lorsque le pourcentage n'est pas positif
     */
    public void enrgComposition(final ActionSimple as, final float pourcentage)
        throws IllegalStateException {
        if (pourcentage <= 0) {
            throw new IllegalStateException(
            "Le pourcentage ne peux pas être inférieur ou égale a 0"
            );
        } else {
            this.mapPanier.put(as, pourcentage);
        }
    }

    /**
     * Fonction permettant d'afficher la liste des cours d'une action composée.
     */
    public void affichageCours() {
        //Parcours le Map
        for (Map.Entry<ActionSimple, Float> entry : mapPanier.entrySet()) {
            ActionSimple actionSimple = entry.getKey();
            Float pourcentage = entry.getValue();

            System.out.println(
                actionSimple.getLibelle() + "-pourcentage : " + pourcentage
            );
        }
    }

    /**
     * Fonction permettant d'afficher le pourcentage d'une action simple.
     *
     * @param actionSimple l'action simple à avoir le pourcentage
     * @return Le pourcentage
     */
    public float affichagePourcentageActionSimple(
        final ActionSimple actionSimple
    ) {
        //Parcours le Map
        for (Map.Entry<ActionSimple, Float> entry : this.mapPanier.entrySet()) {
            if (entry.getKey().equals(actionSimple)) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * Fonction permettant de supprimer une action composée.
     *
     * @param as l'action simple à supprimer
     */
    public void suppressionCours(final ActionSimple as) {
        //verification du cours dans la Map
        if (!mapPanier.containsKey(as)) {
            throw new IllegalArgumentException("Ce cours n'existe pas dans la liste pour cette action");
        }
        mapPanier.remove(as);
    }

    @Override
    public float valeur(final Jour j) {
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
     * Affiche la composition de l'action composée.
     * Pour chaque action simple dans le panier de l'action composée, affiche son libellé et son pourcentage.
     */
    public void visualiserComposition() {
        System.out.println(
            "Composition de l'action composée '" + getLibelle() + "':"
        );
        for (Map.Entry<ActionSimple, Float> entry : mapPanier.entrySet()) {
            ActionSimple actionSimple = entry.getKey();
            float pourcentage = entry.getValue();
            System.out.println(
                "- Action simple: " +
                actionSimple.getLibelle() +
                ", Pourcentage: " +
                pourcentage
            );
        }
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
    public final  Map<Jour, Double> afficherCoursPeriode(
        final Jour dateDebut,
        final Jour dateFin
    ) {
        int anneeDebut = dateDebut.getAnnee();
        int anneeFin = dateFin.getAnnee();

        if (anneeDebut != anneeFin) {
            throw new IllegalArgumentException("Veuillez "
                    + "entrer la date de la même année");
        }
        int jourDebut = dateDebut.getNoJour();
        int jourFin = dateFin.getNoJour();
        if (jourDebut > jourFin) {
            throw new IllegalArgumentException("La date "
                    + "début doit être inférieure à la date fin !");
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

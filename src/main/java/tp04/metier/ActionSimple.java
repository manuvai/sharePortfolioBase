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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant une action simple, héritant de
 * la classe abstraite Action.
 * Une action simple est une action qui ne contient qu'un seul cours.
 * Elle hérite des propriétés et méthodes de la classe Action.
 *
 * <p>
 * Exemple d'utilisation :
 * <pre>
 *     ActionSimple action = new ActionSimple("ActionXYZ");
 * </pre>
 * </p>
 *
 * @author fatima/julie/marc
 */

public class ActionSimple extends Action {

    /**
     * Libellé de l'action.
     */
    private ArrayList<Cours> listeCours;


    /**
     * Constructeur de la classe ActionSimple.
     * Initialise une nouvelle instance d'une action simple.
     * avec le libellé spécifié.
     *
     * @param libelle Le libellé de l'action simple.
     * @throws IllegalStateException si le libellé est vide.
     */

    public  ActionSimple(final String libelle) throws IllegalStateException {
        // Action simple initialisée comme 1 action
        super(libelle);
        if ("".equals(libelle)) {
          throw new IllegalStateException("ActionSimple ne peux pas"
                                         + " avoir un libelle vide");
        }
        // init spécifique
        this.listeCours = new ArrayList<>();
    }

    /**
     * Enregistre un cours pour un jour spécifique.
     * Si aucun cours n'est enregistré pour le jour donné.
     * un nouveau cours est créé avec la valeur spécifiée.
     *
     * @param cours La valeur du cours.
     * @throws IllegalStateException si le libellé est vide.
     */

    public final  void enrgCours(final Cours cours)
            throws IllegalStateException {
        if (this.listeCours.contains(cours)) {
            throw new IllegalStateException("Ce cours existe déjà dans "
                    + "la liste pour cette action");
        }
        else {
            this.listeCours.add(cours);
        }
    }

    /**
    * Fonction permettant d'afficher les cours d'une action simple.
    * Chaque cours est affiché avec sa date et sa valeur.
    */
    public final void affichageCours() {
    	for(Cours c : this.listeCours) {
            System.out.println(c.getJour() + " - Valeur : " + c.getValeur());
    	}
    }

    /**
    * Override de la méthode valeur() pour calculer la valeur
    * de l'action simple pour un jour donné.
    *
    * @param j Le jour pour lequel calculer la valeur de l'action simple.
    * @return La valeur de l'action simple pour le jour donné.
    */

    @Override
    public final float valeur(Jour j) {
        for (Cours c : this.listeCours) {
            if (c.getJour().equals(j)) {
                return c.getValeur();
            }
        }
    	return 0;
    }

    /**
     * Affiche les cours d'une action pour une période donnée.
     *
     * @param dateDebut la date de début de la période
     * @param dateFin la date de fin de la période
     * @return une map contenant les cours de l'action pour
     * chaque jour de la période,
     * ou null si la période est invalide
     * (par exemple, si les dates ne sont pas dans la même année
     *         ou si la date de début est postérieure à la date de fin)
     */
    public final  Map<Jour, Float> afficherCoursPeriode(
             final Jour dateDebut, final Jour dateFin) {
        int anneeDebut = dateDebut.getAnnee();
        int anneeFin = dateFin.getAnnee();

        if (anneeDebut != anneeFin) {
            throw new IllegalArgumentException("Veuillez entrer"
                    + " la date de la même année");
        }
        int jourDebut = dateDebut.getNoJour();
        int jourFin = dateFin.getNoJour();

        if (jourDebut >= jourFin) {
            throw new IllegalArgumentException("La date début doit "
                                        + "être inférieure à la date fin !");
        }

        Map<Jour, Float> mapCours = new HashMap<>();

        for (int j = jourDebut; j <= jourFin; j++) {
            Jour currentJour = new Jour(anneeDebut, j);
            mapCours.put(currentJour, valeur(currentJour));
        }
        return mapCours;
    }

}

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
    
    
    
    /**
     * Affiche les cours d'une action pour une période donnée.
     *
     * @param dateDebut la date de début de la période
     * @param dateFin la date de fin de la période
     * @return une map contenant les cours de l'action pour chaque jour de la période,
     *         ou null si la période est invalide (par exemple, si les dates ne sont pas dans la même année
     *         ou si la date de début est postérieure à la date de fin)
     */
    public Map<Jour, Float> afficherCoursPeriode(Jour dateDebut, Jour dateFin) {
        int anneeDebut = dateDebut.getAnnee();
        int anneeFin = dateFin.getAnnee();
        
        if (anneeDebut != anneeFin) {
            throw new IllegalArgumentException("Veuillez entrer la date de la même année");
        }
        int jourDebut = dateDebut.getNoJour();
        int jourFin = dateFin.getNoJour();

        if (jourDebut >= jourFin) {
            throw new IllegalArgumentException("La date début doit être inférieure à la date fin !");
        }

        Map<Jour, Float> mapCours = new HashMap<>();

        for (int j = jourDebut; j <= jourFin; j++) {
            Jour currentJour = new Jour(anneeDebut, j);
            mapCours.put(currentJour, valeur(currentJour));
        }
        return mapCours;
    }

}

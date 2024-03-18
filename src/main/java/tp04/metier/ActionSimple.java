/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 * Représente une action simple.
 * Une action simple est une action avec un libellé et des cours enregistrés pour différents jours.
 * Cette classe hérite de la classe abstraite Action.
 * 
 * @author somebody
 */
public class ActionSimple extends Action {

    // Map pour stocker les cours pour chaque jour
    private Map<Jour, Cours> mapCours;

    
    /**
     * Constructeur de la classe ActionSimple.
     * Initialise une nouvelle instance d'une action simple avec le libellé spécifié.
     * 
     * @param libelle Le libellé de l'action simple.
     */
    
    
    public ActionSimple(String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap();
    }

    
    /**
     * Enregistre un cours pour un jour spécifique.
     * Si aucun cours n'est enregistré pour le jour donné, un nouveau cours est créé avec la valeur spécifiée.
     * 
     * @param j Le jour pour lequel enregistrer le cours.
     * @param v La valeur du cours.
     */
    
    public void enrgCours(Jour j, float v) {
        if (this.mapCours.containsKey(j) == false) {
            this.mapCours.put(j, new Cours(j, v));
        }
    }

    @Override
    public float valeur(Jour j) {
       // Vérifie si un cours est enregistré pour le jour donné
        if (this.mapCours.containsKey(j) == true) {
            // Si un cours est trouvé, retourne sa valeur
            return this.mapCours.get(j).getValeur();
        } else {
            // Si aucun cours n'est trouvé, retourne 0
            return 0; // definition d'une constante possible
        }
    }
}

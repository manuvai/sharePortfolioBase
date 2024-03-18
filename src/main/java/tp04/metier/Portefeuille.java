/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 * Représente un portefeuille d'actions.
 * Un portefeuille contient un ensemble d'actions avec les quantités correspondantes.
 * Il permet d'acheter et de vendre des actions, ainsi que de calculer la valeur totale du portefeuille.
 * 
 * @author somebody
 */
public class Portefeuille {

    // Map pour stocker les actions avec les quantités correspondantes
    Map<Action, LignePortefeuille> mapLignes;

    
     /**
     * Représente une ligne du portefeuille contenant une action et sa quantité.
     */
    public class LignePortefeuille {

        private  Action action;
        private int qte;

        /**
         * Obtient la quantité d'action dans cette ligne du portefeuille.
         * 
         * @return La quantité d'action.
         */
        public int getQte() {
            return qte;
        }

        /**
         * Définit la quantité d'action dans cette ligne du portefeuille.
         * 
         * @param qte La nouvelle quantité d'action.
         */
        public void setQte(int qte) {
            this.qte = qte;
        }

         /**
         * Obtient l'action associée à cette ligne du portefeuille.
         * 
         * @return L'action associée.
         */
        
        public Action getAction() {
            return this.action;
        }

         /**
         * Constructeur de la classe LignePortefeuille.
         * Initialise une nouvelle instance de ligne de portefeuille avec l'action et la quantité spécifiées.
         * 
         * @param action L'action associée à cette ligne.
         * @param qte La quantité d'action dans cette ligne.
         */
        
        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }
        
        /**
         * Retourne une représentation de la quantité d'action sous forme de chaîne de caractères.
         * 
         * @return La quantité d'action sous forme de chaîne de caractères.
         */

        public String toString() {
            return Integer.toString(qte);
        }
    }

        /**
       * Constructeur de la classe Portefeuille.
       * Initialise un nouveau portefeuille avec une map vide pour stocker les lignes du portefeuille.
       */
        public Portefeuille() {
            this.mapLignes = new HashMap();
        }

    
    /** 
     * Achat une quantité spécifiée d'une certaine action.
     * Si l'action n'est pas présente dans le portefeuille, elle est ajoutée avec la quantité spécifiée.
     * Si l'action est déjà présente, la quantité existante est mise à jour en ajoutant la quantité spécifiée. 
     *
     * @param a l'action à acheter.
     * @param q la quantité à acheter
     */
    
    public void acheter(Action a, int q) {
    if (q <= 0) {
        throw new IllegalArgumentException("La quantité doit être supérieure à 0 pour acheter cette action");
    }

    // Si l'action n'est pas présente dans le portefeuille, l'ajouter avec la quantité spécifiée
    if (!mapLignes.containsKey(a)) {
        mapLignes.put(a, new LignePortefeuille(a, q));
    } else {
    // Si l'action est déjà présente, mettre à jour la quantité existante en ajoutant la quantité spécifiée
        mapLignes.get(a).setQte(mapLignes.get(a).getQte() + q);
    }
}
    
    
    /**
     * Vend une quantité spécifiée d'une certaine action.
     * Si la quantité disponible est supérieure à la quantité spécifiée,
     * la quantité disponible est diminuée de la quantité spécifiée.
     * Si la quantité disponible est égale à la quantité spécifiée, l'action est
     * supprimée de la map.
     *
     * @param a L'action à vendre.
     * @param q La quantité à vendre.
     */
    public final void vendre(final Action a, final int q) {

        if (q <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à 0 pour vendre cette action");
        }

        if (mapLignes.containsKey(a)) {
            int qte = mapLignes.get(a).getQte();

            if (q > qte) {
                throw new IllegalArgumentException("La quantité demandée est supérieure à ce que vous possédez");
            }

            if (qte > q) {
                mapLignes.get(a).setQte(qte - q);
            } else {
                mapLignes.remove(a);
            }
        }
    }
    
    public String toString() {
        return this.mapLignes.toString();
    }

    
    /**
     * Calcule la valeur totale du portefeuille pour une journée donnée.
     * La valeur totale est la somme des valeurs de chaque action multipliée par sa quantité.
     *
     * @param j Le jour pour lequel calculer la valeur du portefeuille.
     * @return La valeur totale du portefeuille pour le jour spécifié.
     */
    
    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
 /**
    * Modifie la quantité d'une action spécifiée dans le portefeuille.
    * Si l'action existe déjà, sa quantité est mise à jour avec la nouvelle quantité spécifiée.
    * Si l'action n'existe pas dans le portefeuille, elle est ajoutée avec la nouvelle quantité.
    * Une exception est lancée si la nouvelle quantité spécifiée est inférieure ou égale à 0, 
    * car cela ne représente pas une opération valide sur le portefeuille.
    *
    * @param a L'action à modifier.
    * @param nouvelleQte La nouvelle quantité pour l'action.
    * @throws IllegalArgumentException Si la nouvelle quantité est inférieure ou égale à 0.
 */
    public void modifierAction(Action a, int nouvelleQte){
         if (nouvelleQte <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à 0");
        }
         LignePortefeuille ligne = mapLignes.get(a);
        if (ligne != null) {
            // Si l'action existe déjà, on met à jour la quantité
            ligne.setQte(nouvelleQte);
        } else {
            // Si l'action n'existe pas, on l'ajoute avec la nouvelle quantité
            mapLignes.put(a, new LignePortefeuille(a, nouvelleQte));
        }
    }
    
 /**
    * Calcule l'évolution du portefeuille entre deux dates données.
    *
    * @param jourDebut la date de début de la période
    * @param jourFin la date de fin de la période
    * @return l'évolution du portefeuille entre les deux dates, sous forme de float
 */
    public float evolutionPortefeuille(Jour jourDebut, Jour jourFin){
        
        return valeur(jourFin) - valeur(jourDebut);
    }
}


    

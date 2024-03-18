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

    /*
     * a revoir une modification
     * 
     * @author Fatima @author yassine
     * 
     */

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

    


}

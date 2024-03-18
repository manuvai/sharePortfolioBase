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
 * @author somebody
 */
public class Portefeuille {

    Map<Action, LignePortefeuille> mapLignes;

    public class LignePortefeuille {

        private  Action action;
        private int qte;

        public int getQte() {
            return qte;
        }

        public void setQte(int qte) {
            this.qte = qte;
        }

        public Action getAction() {
            return this.action;
        }

        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        public String toString() {
            return Integer.toString(qte);
        }
    }

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
}

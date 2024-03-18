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

    private class LignePortefeuille {

        private Action action;

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

    public void acheter(Action a, int q) {
        if (this.mapLignes.containsKey(a) == false) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
    }

    /**
      * Vend une quantité spécifiée d'une certaine action.
      * Si la quantité disponible est supérieure à la quantité spécifiée, la quantité disponible est diminuée de la quantité spécifiée.
      * Si la quantité disponible est égale à la quantité spécifiée, l'action est supprimée de la map.
      *
      * @param a L'action à vendre.
      * @param q La quantité à vendre.
      */
    public final void vendre(Action a, int q) {
    if (mapLignes.containsKey(a)) {
        int qte = mapLignes.get(a).getQte();

        if (qte > q) {
            mapLignes.get(a).setQte(qte - q);
        } else if (qte == q) {
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
}

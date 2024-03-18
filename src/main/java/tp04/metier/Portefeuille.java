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

        public final Action action;
        public int qte;

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

    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }

    /*
     * Cette fonction permet d'acheter une action simple et d'enregistrer son cours
     * pour la journée donnée.
     * Si l'action simple existe déjà dans le portefeuille, la quantité est mise à
     * jour et le cours est enregistré pour la journée spécifiée.
     * Si l'action simple n'existe pas dans le portefeuille, une nouvelle action
     * simple est créée avec le cours enregistré pour la journée spécifiée.
     * 
     * @param libelle le libellé de l'action simple à acheter
     * 
     * @param quantite la quantité d'actions à acheter
     * 
     * @param jour le jour pour lequel enregistrer le cours
     * 
     * @param cours le cours de l'action pour le jour donné
     * 
     * @author Fatima @author yassine
     * 
     */

    public void acheterActionSimple(String libelle, int quantite, Jour jour, float cours) {

        // Parcours des actions dans le portefeuille
        Action actionTrouvee = null;
        for (Action a : this.mapLignes.keySet()) {
            if (a.getLibelle().equals(libelle) && a instanceof ActionSimple) {
                actionTrouvee = a;
                break;
            }
        }
        // Si l'action simple existe dans le portefeuille
        if (actionTrouvee != null) {
            LignePortefeuille lp = this.mapLignes.get(actionTrouvee);
            lp.setQte(lp.getQte() + quantite);
            ((ActionSimple) actionTrouvee).enrgCours(jour, cours);
        } else {
            ActionSimple nouvelleAction = new ActionSimple(libelle);
            nouvelleAction.enrgCours(jour, cours);
            this.mapLignes.put(nouvelleAction, new LignePortefeuille(nouvelleAction, quantite));
        }

    }

}
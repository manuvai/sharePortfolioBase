/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Représente un portefeuille d'actions.
 * Un portefeuille contenant un ensemble d'actions.
 * Permet d'acheter/vendre des actions.
 * ainsi que de calculer la valeur totale du portefeuille.
 * @author somebody
 */
public class Portefeuille {

     /**
     * Map pour stocker les actions avec les quantités correspondantes.
     */
    private Map<Action, LignePortefeuille> mapLignes;

     /**
     * Une ligne contenant une action et sa quantité.
     */
    public class LignePortefeuille {

        /**
         */
        private final Action action;
         /**
         */
        private int qte;

        /**
         * Obtient la quantité d'action dans cette ligne du portefeuille.
         * @return La quantité d'action.
         */
        public final int getQte() {
            return qte;
        }

        /**
         * Définit la quantité d'action dans cette ligne du portefeuille.
         * @param qte La nouvelle quantité d'action.
         */
        public void setQte(final int qte) {
            this.qte = qte;
        }

         /**
         * Obtient l'action associée à cette ligne du portefeuille.
         * @return L'action associée.
         */
        public final Action getAction() {
            return this.action;
        }

         /**
         * Constructeur de la classe LignePortefeuille.
         * Initialise une nouvelle instance de ligne.
         * de portefeuille avec l'action et la quantité spécifiées.
         * @param action L'action associée à cette ligne.
         * @param qte La quantité d'action dans cette ligne.
         */

        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        /**
         * Retourne une représentation de la quantité.
         * d'action sous forme de chaîne de caractères.
         * @return La quantité d'action sous forme de chaîne de caractères.
         */

        @Override
        public final String toString() {
            return Integer.toString(qte);
        }
    }

    /**
    * Constructeur de la classe Portefeuille.
    * Initialise un nouveau portefeuille avec une map vide.
    * pour stocker les lignes du portefeuille.
    */
    public Portefeuille() {
        this.mapLignes = new HashMap();
    }

    /**
     * Achat une quantité spécifiée d'une certaine action.
     * Si l'action ajoutée avec la quantité spécifiée.
     * Mise à jour en ajoutant la quantité spécifiée.
     *
     * @param a l'action à acheter.
     * @param q la quantité à acheter
     */
    public final void acheter(Action a, int q) {
        if (q <= 0) {
            throw new IllegalArgumentException("La quantité < 0");
        }

        // Ajout la quantité spécifiée
        if (!mapLignes.containsKey(a)) {
            mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
        // MAJ la quantité existante en ajoutant la quantité spécifiée
            mapLignes.get(a).setQte(mapLignes.get(a).getQte() + q);
        }
    }
    /**
     * Vend une quantité spécifiée d'une certaine action.
     * Si la quantité disponible est supérieure à la quantité spécifiée,
     * la quantité disponible est diminuée de la quantité spécifiée.
     * Si la qté dispo = qté spécifiée, l'action est
     * supprimée de la map.
     *
     * @param a L'action à vendre.
     * @param q La quantité à vendre.
     */
    public final void vendre(final Action a, final int q) {

        if (q <= 0) {
            throw new IllegalArgumentException("La quantité < 0");
        }

        if (mapLignes.containsKey(a)) {
            int qte = mapLignes.get(a).getQte();

            if (q > qte) {
                throw new IllegalArgumentException("Quantité supérieur");
            }

            if (qte > q) {
                mapLignes.get(a).setQte(qte - q);
            } else {
                mapLignes.remove(a);
            }
        }
    }

    /**
    * @return LLLLLLL
    */
    @Override
    public final String toString() {
        return this.mapLignes.toString();
    }

    /**
    * @param j La journée pour calculer la valeur totale du portefeuille.
    * @return La valeur totale du portefeuille pour la journée spécifiée.
    */
    public final float valeur(final Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
 /**
    * Modifie la quantité d'une action spécifiée dans le portefeuille.
    * Si l'action existe déjà, sa quantité est mise à jour.
    * avec la nouvelle quantité spécifiée.
    * Si l'action n'existe pas.
    * elle est ajoutée avec la nouvelle quantité.
    * Une exception est lancée si la nouvelle quantité.
    * spécifiée est inférieure ou égale à 0.
    * car cela ne représente pas une opération valide sur le portefeuille.
    *
    * @param a L'action à modifier.
    * @param nouvelleQte La nouvelle quantité pour l'action.
    * @throws IllegalArgumentException Si la nouvelle quantité.
    * est inférieure ou égale à 0.
 */
    public final void modifierAction(final Action a, int nouvelleQte){
         if (nouvelleQte <= 0) {
            throw new IllegalArgumentException("La quantité doit être > 0");
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
       * @param jDebut la date de début de la période
       * @param jFin la date de fin de la période
       * @return l'évolution du portefeuille entre 2 dates, sous forme de float
    */
    public final double evolutionPortefeuille(final Jour jDebut, final Jour jFin){
        return (double) valeur(jFin) - (double) valeur(jDebut);
    }
    /**
       * Calcule l'évolution du portefeuille entre deux dates données.
       * @param cours la date de début de la période
    */
    public final void analyseAction(Cours cours){
     ArrayList<Action> listeActions = new ArrayList<>();
        ArrayList<Action> listeActions_actionsComposees = new ArrayList<>();
        ActionComposee aComp = new ActionComposee("ActionCompProvisoire");
        
        //Parcours le Map
    	for(Map.Entry<Action, LignePortefeuille> entry : this.mapLignes.entrySet()) {
            if (entry.getKey() instanceof ActionSimple){
                ActionSimple as = (ActionSimple)entry.getKey();
                if(as.getCours().contains(cours)){
                    listeActions.add(as);
                }
            } else if (entry.getKey() instanceof ActionComposee){
                ActionComposee ac = (ActionComposee)entry.getKey();
                for(Map.Entry<ActionSimple, Float> entryAC : ac.mapPanier.entrySet()) {
                    if(entryAC.getKey().getCours().contains(cours)) {
                        aComp.setLibelle(ac.getLibelle());
                        listeActions_actionsComposees.add(entryAC.getKey());
                        ac.mapPanier.put(entryAC.getKey(), entryAC.getValue());
                    }
                }
                // On ajoute aComp à la liste listeActions
                listeActions.add(ac);
            }
        }
    }

    /**
     * Calcule et retourne le nombre maximum d'actions achetables d'une action
     * spécifique
     * pour un montant donné à un jour donné.
     *
     * @param a       L'action à acheter.
     * @param montant Le montant disponible pour l'achat.
     * @param jour    Le jour pour lequel l'achat est envisagé.
     * @return Le nombre maximum d'actions achetables avec le montant donné.
     */
    public int nombreActionsAchetables(Action a, float montant, Jour jour) {
        double valeurAction = a.valeur(jour);

        if (valeurAction <= 0) {
            throw new IllegalArgumentException(
                    "La valeur de l'action doit être supérieure à 0 pour effectuer un achat.");
        }

        // Calcule le nombre d'actions achetables
        return (int) (montant / valeurAction);

    }
    
    
}


    

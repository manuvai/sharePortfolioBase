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
 * Représente un portefeuille d'actions.
 * Un portefeuille contient un ensemble d'actions avec les quantités correspondantes.
 * Il permet d'acheter et de vendre des actions, ainsi que de calculer la valeur totale du portefeuille.
 * 
 * @author manuvai
 */
public class Portefeuille {

    // Map pour stocker les actions avec les quantités correspondantes
    Map<AbstractAction, LignePortefeuille> mapLignes;

    
     /**
     * Représente une ligne du portefeuille contenant une action et sa quantité.
     */
    public class LignePortefeuille {

        private AbstractAction action;
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
        
        public AbstractAction getAction() {
            return this.action;
        }

         /**
         * Constructeur de la classe LignePortefeuille.
         * Initialise une nouvelle instance de ligne de portefeuille avec l'action et la quantité spécifiées.
         * 
         * @param action L'action associée à cette ligne.
         * @param qte La quantité d'action dans cette ligne.
         */
        
        public LignePortefeuille(AbstractAction action, int qte) {
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
        this.mapLignes = new HashMap<>();
    }

    /** 
     * Achat une quantité spécifiée d'une certaine action.
     * Si l'action n'est pas présente dans le portefeuille, elle est ajoutée avec la quantité spécifiée.
     * Si l'action est déjà présente, la quantité existante est mise à jour en ajoutant la quantité spécifiée. 
     *
     * @param a l'action à acheter.
     * @param q la quantité à acheter
     */
    
    public void acheter(AbstractAction a, int q) {
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
    public final void vendre(final AbstractAction a, final int q) {

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
    * Calcule la valeur totale du portefeuille pour une journée spécifiée.
    * La valeur totale est obtenue en additionnant la valeur de chaque ligne d'action dans le portefeuille
    * pour la journée donnée, où la valeur de chaque ligne est le produit de la quantité d'actions détenue
    * et la valeur de l'action pour cette journée.
    * 
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
     * Si l'action existe déjà, sa quantité est mise à jour avec la nouvelle quantité spécifiée.
     * Si l'action n'existe pas dans le portefeuille, elle est ajoutée avec la nouvelle quantité.
     * Une exception est lancée si la nouvelle quantité spécifiée est inférieure ou égale à 0,
     * car cela ne représente pas une opération valide sur le portefeuille.
     *
     * @param a L'action à modifier.
     * @param nouvelleQte La nouvelle quantité pour l'action.
     * @throws IllegalArgumentException Si la nouvelle quantité est inférieure ou égale à 0.
     */
    public void modifierAction(AbstractAction a, int nouvelleQte){
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
    public double evolutionPortefeuille(Jour jourDebut, Jour jourFin){
        
        return (double)valeur(jourFin) - (double)valeur(jourDebut);
    }
    
 

    /**
     * Affiche la composition des actions composées présentes dans le portefeuille.
     * Pour chaque action composée dans le portefeuille, affiche son libellé et sa composition.
     */
    public void visualiserCompositionActionsComposees() {
        System.out.println("Composition des actions composées dans le portefeuille:");
        for (LignePortefeuille ligne : mapLignes.values()) {
            AbstractAction action = ligne.getAction();
            if (action instanceof ActionComposee actionComposee) {
                System.out.println("Action composée: " + actionComposee.getLibelle());
                actionComposee.visualiserComposition();
            }
        }
    }

    /**
     * TODO
     */
    public void visualiserComposition() {
        for (Map.Entry<AbstractAction, LignePortefeuille> entry : mapLignes.entrySet()) {
            AbstractAction action = entry.getKey();
            LignePortefeuille ligne = entry.getValue();

            System.out.println("- Action simple: " + action.getLibelle() + ", Pourcentage: " + ligne);
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
    public int nombreActionsAchetables(AbstractAction a, float montant, Jour jour) {
        double valeurAction = a.valeur(jour);

        if (valeurAction <= 0) {
            throw new IllegalArgumentException(
                    "La valeur de l'action doit être supérieure à 0 pour effectuer un achat.");
        }

        // Calcule le nombre d'actions achetables
        return (int) (montant / valeurAction);

    }
    
    
}


    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.Objects;

/**
 *
 * Classe abstraite représentant une action.
 * Une action peut être une action simple ou une action composée.
 * 
 * @author fatima
 */
public abstract class Action {

    /**
     * Libellé de l'action.
     */
    private final String libelle;

    /**
     * Valeur de la constante pour le calcul de hashCode.
     */
    private static final int MULTIPLIER = 53;
    /**
     * Valeur de la constante pour le calcul de hashCode.
     */
    private static final int INITIAL_HASH = 3;

    /**
     * Get the value of libelle.
     *
     * @return the value of libelle.
     */
    public final String getLibelle() {
        return libelle;
    }

    /**
     * Constructeur de la classe Action.
     *
     * @param inLibelle Le libellé de l'action.
     */
    public Action(final String inLibelle) {
        this.libelle = inLibelle;
    }

    /**
     * Méthode abstraite pour calculer la valeur de l'action pour un jour donné.
     *
     * @param j Le jour pour lequel calculer la valeur de l'action.
     * @return La valeur de l'action pour le jour donné.
     */

    public abstract float valeur(Jour j);

    /**
     * Override de la méthode hashCode() pour calculer .
     * le code de hachage de l'objet Action.
     * 
     * @return Le code de hachage calculé.
     */
    @Override
    public final int hashCode() {
        int hash = INITIAL_HASH;
        hash = MULTIPLIER * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    /**
     * Override de la méthode equals() pour comparer deux objets Action.
     *
     * @param obj L'objet à comparer avec l'objet actuel.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        return Objects.equals(this.libelle, other.libelle);
    }

    /**
     * Override de la méthode toString() pour retourner le libellé de l'action.
     *
     * @return Le libellé de l'action.
     */

    @Override
    public final String toString() {
        return this.getLibelle();
    }
}

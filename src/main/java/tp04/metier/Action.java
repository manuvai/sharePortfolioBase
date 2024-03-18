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
 * @author somebody
 */
public abstract class Action {

    private String libelle;

    /**
     * Get the value of libelle
     *
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

     /**
     * Constructeur de la classe Action.
     * 
     * @param libelle Le libellé de l'action.
     */
    public Action(String libelle) {
        this.libelle = libelle;
    }
    
    /**
     * Méthode abstraite pour calculer la valeur de l'action pour un jour donné.
     * 
     * @param j Le jour pour lequel calculer la valeur de l'action.
     * @return La valeur de l'action pour le jour donné.
     */

    public abstract float valeur(Jour j);

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.getLibelle();
    }
}

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

/**
 * Classe représentant une date dans une année.
 * Cette classe permet de représenter une date dans une année donnée,
 * avec un numéro de jour compris entre 1 et 365.
 * Elle fournit des méthodes pour accéder et modifier l'année et
 * le numéro de jour, ainsi que des méthodes pour le hashCode,
 * l'égalité, la comparaison et la représentation sous forme
 * de chaîne de caractères.
 *
 * @author fatima
 */
public class Jour implements Comparable<Jour> {

    /**
     * Valeur de la constante pour le calcul de hashCode.
     */
    private static final int MULTIPLIER = 61;
    /**
     * Valeur de la constante pour le calcul de hashCode.
     */
    private static final int INITIAL_HASH = 7;

     /**
     * Année.
     */
    private int annee;
    /**
     * Numéro de jour.
     */
    private int noJour;


    /**
     * Get the value of annee.
     *
     *
     * @return the value of annee
     */
    public final int getAnnee() {
        return annee;
    }

    /**
     * Set the value of annee.
     *
     * @param annee la nouvelle valeur de l'annee
     */
    public final void setAnnee(final int annee){
        this.annee = annee;
    }

    /**
     * Get the value of noJour.
     *
     * @return the value of noJour
     */
    public final int getNoJour() {
        return noJour;
    }

    /**
     * Constructor of the Jour object.
     *
     * @param annee Le numéro de l'année
     * @param noJour le numéro du jour 1-365
     * @throws IllegalArgumentException si le numéro
     * de jour est inférieur ou égal à 0
     */
    public Jour(final int annee, final int noJour) {
        if (noJour <= 0) {
            throw new IllegalArgumentException("0 must not"
                    + " be used as a valid Day");
        }
        this.annee = annee;
        this.noJour = noJour;
    }

    @Override
    public final int hashCode() {
        int hash = INITIAL_HASH;
        hash = MULTIPLIER * hash + this.annee;
        hash = MULTIPLIER * hash + this.noJour;
        return hash;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jour other = (Jour) obj;
        if (this.annee != other.annee) {
            return false;
        }
        if (this.noJour != other.noJour) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "Jour{" + "annee=" + annee + ", noJour=" + noJour + '}';
    }

    @Override
    public final int compareTo(final Jour other) {
        if (annee < other.annee) {
            return -1;
        }
        if (annee > other.annee) {
            return 1;
        }

        return Integer.compare(noJour, other.noJour);

    }
}

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
 *
 * @author somebody
 */
public class Jour implements Comparable<Jour>{

    private int annee;
    private int noJour;

    /**
     * Get the value of annee
     *
     * @return the value of annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Set the value of annee
     *
     * @param annee la nouvelle valeur de l'annee
     */
    public void setAnnee(int annee){
        this.annee = annee;
    }

    /**
     * Get the value of noJour
     *
     * @return the value of noJour
     */
    public int getNoJour() {
        return noJour;
    }

    /**
     * Constructor of the Jour object
     *
     * @param annee Le numéro de l'année
     * @param noJour le numéro du jour 1-365
     */
    public Jour(int annee, int noJour) {
    	if(noJour <= 0) {
            throw new IllegalArgumentException("0 must not be used as a valid Day");
    	}
        this.annee = annee;
        this.noJour = noJour;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.annee;
        hash = 61 * hash + this.noJour;
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
    public String toString() {
        return "Jour{" + "annee=" + annee + ", noJour=" + noJour + '}';
    }

    @Override
    public int compareTo(Jour other) {
        if (annee < other.annee) {
            return -1;
        }
        if (annee > other.annee) {
            return 1;
        }

        return Integer.compare(noJour, other.noJour);

    }
}

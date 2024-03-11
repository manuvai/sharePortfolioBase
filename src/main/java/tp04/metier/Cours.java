/*
 * Copyright 2024 David Navarre &lt;David.Navarre at irit.fr&gt;.
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
 * This class aims at storing a value (of an action) for a given day.
 * <p>
 * Example of usage: <code>Cours c = new Cours(j, 2.3F);</code></p>
 *
 * @author SomeFirstName SomeName &lt;SomeFirstName.SomeName at truc.fr&gt;
 * @see Action
 * @see Jour
 */
public final class Cours {

    /**
     * The given day for this Cours instance.
     */
    private final Jour jour;
    /**
     * The value of this cours for the given day.
     */
    private final float valeur;

    /**
     * Provides a read access to the value of the cours.
     *
     * @return the value of this Cours instance
     */
    public float getValeur() {
        return valeur;
    }

    /**
     * Provides a read access to the day of the cours.
     *
     * @return the day of this Cours instance
     */
    public Jour getJour() {
        return jour;
    }

    /**
     * Builds a Cours instance from a day and a value.
     *
     * @param aJour the day of the Cours
     * @param aValeur ths value of the Cours
     */
    public Cours(final Jour aJour, final float aValeur) {
        this.jour = aJour;
        this.valeur = aValeur;
    }

    @Override
    public String toString() {
        return "Cours{" + "jour=" + jour + ", valeur=" + valeur + '}';
    }

}

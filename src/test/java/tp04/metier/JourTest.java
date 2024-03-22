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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Classe de test pour la classe Jour.
 * Cette classe fournit des méthodes de test pour
 * vérifier le bon fonctionnement des différentes
 * fonctionnalités de la classe Jour.
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class JourTest {

    /**
    * Valeur par défaut pour le jour utilisée dans les tests.
    * Cette constante représente la valeur
    * par défaut pour le jour dans les tests.
    */
    private static final int DEFAULT_DAY = 1;

    /**
     * Valeur par défaut pour l'année utilisée dans les tests.
     * Cette constante représente la valeur par
     * défaut pour l'année dans les tests.
     */
    private static final int DEFAULT_YEAR = 1;

    /**
     * Jour incorrect utilisé dans les tests.
     * Cette constante représente un jour incorrect utilisé dans les tests.
     */
    private static final int INCORRECT_DAY = 0;

    /**
     * Année incorrecte utilisée dans les tests.
     * Cette constante représente une année incorrecte utilisée dans les tests.
     */
    private static final int INCORRECT_YEAR = 0;
        /**
         * Constructeur par défaut de la classe JourTest.
         * Ce constructeur n'effectue aucune opération spécifique.
         */
    public JourTest() {
    }
    /**
     * Teste le constructeur de la classe Jour avec des paramètres corrects.
     * Cette méthode teste la création d'une instance
     * de la classe Jour en passant des paramètres corrects,
     * puis vérifie que la méthode toString() renvoie la valeur attendue.
     */
    @Test
    protected final void testConstructorParametersAreCorrectSuccess() {
        //Arrange
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);

        //Action
        final String expectedToString = "Jour{" + "annee="
                + DEFAULT_YEAR + ", noJour=" + DEFAULT_DAY + '}';
        final String currentToString = jour.toString();

        //Assert
        Assertions.assertEquals(expectedToString,
                currentToString, "Basic construction");
    }
    /**
     * Teste le constructeur de la classe Jour avec un jour incorrect.
     * Cette méthode vérifie que le constructeur
     * de la classe Jour génère une exception IllegalArgumentException
     * lorsque le jour passé en paramètre est incorrect (0).
     */
    @Test
    protected final void testConstructorDayIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "0 must not be used as a valid Day";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.
                assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Jour(DEFAULT_YEAR, INCORRECT_DAY);
        }, "0 must not be used as a valid Day");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage,
                "Expected error message");
    }
}

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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoursTest {

    private static final float DEFAULT_VALUE_COURS = 112;
    private static final float INCORRECT_VALUE_COURS = -5;
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_YEAR = 1;

    @Test
    protected void testConstructorParametersAreCorrectSuccess() throws Exception {
        //Arrange
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);
        final Cours cours = new Cours(jour,DEFAULT_VALUE_COURS) ;
  

        //Action
        final String expectedToString = "Jour{" + "annee=" + DEFAULT_YEAR + ", noJour=" + DEFAULT_DAY + '}' + DEFAULT_VALUE_COURS;
        final String currentToString = jour.toString() + cours.getValeur();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }
	
    @Test
    protected void testConstructorValueIncorrectShouldFail() {
    	 final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);
        //Arrange 
        final String expectedMessage = "negatif number must not be used as a valid Value";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Cours(jour, INCORRECT_VALUE_COURS);
        }, "negatif number must not be used as a valid Value");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }
}

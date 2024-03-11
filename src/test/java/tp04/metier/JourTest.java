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
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class JourTest {

    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_YEAR = 1;
    private static final int INCORRECT_DAY = 0;
    private static final int INCORRECT_YEAR = 0;

    public JourTest() {
    }

    @Test
    protected void testConstructorParametersAreCorrectSuccess() {
        //Arrange
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);

        //Action
        final String expectedToString = "Jour{" + "annee=" + DEFAULT_YEAR + ", noJour=" + DEFAULT_DAY + '}';
        final String currentToString = jour.toString();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }

    //@Test
    protected void testConstructorDayIncorrectShouldFail() {
        //Arrange
        final String expectedMessage = "0 must not be used as a valid Day";
        //Action and asserts
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
            new Jour(DEFAULT_YEAR, INCORRECT_DAY);
        }, "0 must not be used as a valid Day");
        final String currentMessage = assertThrowsExactly.getMessage();
        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }
}

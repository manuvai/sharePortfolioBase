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

/**
 *
 * @author NGUYEN, MOLI
 */
public class ActionSimpleTest {
    
        /** Paramètre **/
        private static final String DEFAULT_LABEL = "France";
        @Test
        // Test Permettant de tester le constructeur
        protected void testConstructorParametersAreCorrectSuccess() throws Exception {
            //Arrange
            final ActionSimple actionSimple = new ActionSimple(DEFAULT_LABEL);

            //Action
            final String expectedToString = DEFAULT_LABEL;
            final String currentToString = actionSimple.toString();

            //Assert
            Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
        }
}

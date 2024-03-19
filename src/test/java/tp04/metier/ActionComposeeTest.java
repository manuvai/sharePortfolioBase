/*
 * Copyright 2024 21705637.
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
 * @author Moli et NGUYEN
 */
public class ActionComposeeTest {
    /** Paramètre **/
    private static final String DEFAULT_LABEL = "France";
    private static final Jour DEFAULT_JOUR = new Jour(2020, 10);
    private static final Cours DEFAULT_COURS1 = new Cours(DEFAULT_JOUR, 25);
    private static final Cours DEFAULT_COURS2 = new Cours(DEFAULT_JOUR, 55);
    private static final float DEFAULT_POURCENTAGE = 11;
  
    
    @Test
    // Test Permettant de tester le constructeur
    protected void testConstructorParametersAreCorrectSuccess() throws Exception {
        //Arrange
        final ActionComposee actionComposee= new ActionComposee(DEFAULT_LABEL);

        //Action
        final String expectedToString = DEFAULT_LABEL;
        final String currentToString = actionComposee.toString();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "Basic construction");
    }
    
    @Test
    protected void testValueIncorrectShouldFail() throws Exception {
        //Arrange
        final ActionSimple DEFAULT_AS1 = new ActionSimple("France 1");
        final ActionSimple DEFAULT_AS2 = new ActionSimple("M6");
        
        DEFAULT_AS1.enrgCours(DEFAULT_COURS1);
        DEFAULT_AS2.enrgCours(DEFAULT_COURS2);
        
        ActionComposee actionComposee = new ActionComposee("FranceTV");
        
        actionComposee.enrgComposition(DEFAULT_AS2, 11);
        actionComposee.enrgComposition(DEFAULT_AS1, 11);
        
        CrudAction listeActions = new CrudAction();
        listeActions.enregistrerAction(DEFAULT_AS2);
        listeActions.enregistrerAction(DEFAULT_AS1);
        
        //Action
        final String expectedToString = DEFAULT_AS1 + "-" + DEFAULT_POURCENTAGE + DEFAULT_AS2 + "-" + DEFAULT_POURCENTAGE; 
        final String currentToString = DEFAULT_AS1 + "-" + actionComposee.affichagePourcentageActionSimple(DEFAULT_AS1)+ DEFAULT_AS2 + "-" + actionComposee.affichagePourcentageActionSimple(DEFAULT_AS2); 
        
        Assertions.assertEquals(expectedToString, currentToString, "testValueIncorrectShouldFail KO");
    }
}

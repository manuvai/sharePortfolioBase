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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    // Test Permettant de tester le constructeur
    protected void testConstructorParametersAreIncorrectKO() throws Exception {
        // GIVEN
        String expectedMessage = "ActionComposée ne peux pas avoir un libelle vide";

        //Action
        IllegalStateException exception = Assertions.assertThrowsExactly(IllegalStateException.class
                , () -> new ActionComposee("")
                , "Le controleur ne devrait pas accepter une string vide");

        //Assert
        Assertions.assertEquals(expectedMessage, exception.getMessage(), "Les messages devraient correspondre");
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

    @Test
    protected void testEnrgCompositionPourcentageNegativeKO() {
        // GIVEN
        ActionComposee actionComposee = new ActionComposee("Test");
        ActionSimple actionSimple = new ActionSimple("Total");
        String expectedMessage = "Le pourcentage ne peux pas être inférieur ou égale a 0";

        // WHEN
        IllegalStateException exception = Assertions.assertThrowsExactly(IllegalStateException.class
            , () -> actionComposee.enrgComposition(actionSimple, -1)
            , "Cet appel devrait lancer une exception");

        // THEN
        Assertions.assertEquals(expectedMessage, exception.getMessage(), "Les messages devraient correspondre");
    }

    @Test
    protected void testEnrgCompositionPourcentageZeroKO() {
        // GIVEN
        ActionComposee actionComposee = new ActionComposee("Test");
        ActionSimple actionSimple = new ActionSimple("Total");
        String expectedMessage = "Le pourcentage ne peux pas être inférieur ou égale a 0";

        // WHEN
        IllegalStateException exception = Assertions.assertThrowsExactly(IllegalStateException.class
                , () -> actionComposee.enrgComposition(actionSimple, 0)
                , "Cet appel devrait lancer une exception");

        // THEN
        Assertions.assertEquals(expectedMessage, exception.getMessage(), "Les messages devraient correspondre");
    }

    @Test
    protected void testAffichagePourcentageActionSimpleMapEmptyReturnZeroOK() {
        // GIVEN
        ActionComposee actionComposee = new ActionComposee("Test");
        ActionSimple actionSimple = new ActionSimple("Test simple");

        // THEN
        Assertions.assertEquals(0
                , actionComposee.affichagePourcentageActionSimple(actionSimple)
                , "Le pourcentage devrait correspondre");

    }
    @Test
    protected void testAffichagePourcentageActionSimpleMapFilledReturnOK() {
        // GIVEN
        Float expectedPourcentage = DEFAULT_POURCENTAGE;
        ActionComposee actionComposee = new ActionComposee("Test");
        ActionSimple actionSimple = new ActionSimple("Test simple");

        // WHEN
        actionComposee.enrgComposition(actionSimple, expectedPourcentage);

        // THEN
        Assertions.assertEquals(expectedPourcentage
                , actionComposee.affichagePourcentageActionSimple(actionSimple)
                , "Le pourcentage devrait correspondre");
    }
    @Test
    protected void testSuppressionCoursActionNotIncludedKO() {
        // GIVEN
        ActionComposee actionComposee = new ActionComposee("Test");
        ActionSimple actionSimple = new ActionSimple("Test action Simple");
        String expectedExceptionMessage = "Ce cours n'existe pas dans la liste pour cette action";

        // WHEN
        IllegalArgumentException exception = Assertions.assertThrowsExactly(IllegalArgumentException.class
                , () -> actionComposee.suppressionCours(actionSimple)
                , "Ne devrait pas lever d'exception");

        // THEN
        Assertions.assertEquals(expectedExceptionMessage, exception.getMessage(), "Les messages devraient correspondre");
    }
    @Test
    protected void testSuppressionCoursActionOK() {
        // GIVEN
        ActionComposee actionComposee = new ActionComposee("Test");
        ActionSimple actionSimple = new ActionSimple("Test action Simple");

        // WHEN
        actionComposee.enrgComposition(actionSimple, DEFAULT_POURCENTAGE);

        // THEN
        Assertions.assertDoesNotThrow(() -> actionComposee.suppressionCours(actionSimple)
                , "Ne devrait pas lever d'exception");
    }
    @Test
    protected void testAffichageCoursOK() {
        // GIVEN
        String lineSeparator = System.getProperty("line.separator");
        String expectedOutput = "Total-pourcentage : 11.0" + lineSeparator;
        ActionComposee actionComposee = new ActionComposee("Test");
        ActionSimple actionSimple = new ActionSimple("Total");
        actionComposee.enrgComposition(actionSimple, DEFAULT_POURCENTAGE);

        // WHEN

        // Redirection de la sortie console vers un flux pour permettre la comparaison
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Exécution de la méthode à tester
        actionComposee.affichageCours();

        // Récupération de la sortie imprimée
        String actualOutput = outputStream.toString();

        // Rétablissement de la sortie console normale
        System.setOut(originalOut);

        // THEN
        Assertions.assertEquals(expectedOutput, actualOutput, "Les sorties devraient correspondre");
    }

    @Test
    protected void testAfficherCoursPeriodeAnneeDifferente() {
        String messageErreur = "Veuillez entrer la date de la même année";
        Jour jourDebut = new Jour(2024, 1);
        Jour jourFin = new Jour(2023, 1);

        ActionComposee action = new ActionComposee("ActionTest");
        IllegalArgumentException exception = Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> action.afficherCoursPeriode(jourDebut, jourFin),
                "Annee differente");

        Assertions.assertEquals(messageErreur, exception.getMessage(), "Message d'erreur devrait être le même");

    }

    @Test
    protected void testAfficherCoursPeriodeJourFutur() {
        String messageErreur = "La date début doit être inférieure à la date fin !";
        Jour jourDebut = new Jour(2024, 6);
        Jour jourFin = new Jour(2024, 1);

        ActionComposee action = new ActionComposee("ActionTest");
        IllegalArgumentException exception = Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> action.afficherCoursPeriode(jourDebut, jourFin),
                "Annee differente");

        Assertions.assertEquals(messageErreur, exception.getMessage(), "Message d'erreur devrait être le même");

    }
}

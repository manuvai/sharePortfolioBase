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

import java.util.Collections;
import java.util.Map;
//import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Moli et NGUYEN
 */
public class ActionComposeeTest {
      /**
     * libelle défaut.
     */
    private static final String DEFAULT_LABEL = "France";
     /**
     * défaut jour.
     */
    private static final Jour DEFAULT_JOUR = new Jour(2020, 10);
      /**
     * défaut cours 1.
     */
    private static final Cours DEFAULT_COURS1 = new Cours(DEFAULT_JOUR, 25);
     /**
     * défaut cours 2.
     */
    private static final Cours DEFAULT_COURS2 = new Cours(DEFAULT_JOUR, 55);
     /**
     * défaut pourcentage.
     */
    private static final float DEFAULT_POURCENTAGE = 11;
     /**
     * défaut année.
     */
    private static final int DEFAULT_YEAR = 2020;

     /**
     * défaut année.
     */
    private static final int DEFAULT_DATE_JOUR = 1;

    /**
     * Test of testConstructorParameter method, of class ActionComposee.
     * @throws java.lang.Exception
     */
     @Test
    protected final void testConstructorParameters() throws Exception {
        //Arrange
        final ActionComposee actionComposee = new ActionComposee(DEFAULT_LABEL);

        //Action
        final String expectedToString = DEFAULT_LABEL;
        final String currentToString = actionComposee.toString();

        //Assert
        Assertions.assertEquals(expectedToString, currentToString, "valider");
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
        final ActionSimple defaultAs1 = new ActionSimple("France 1");
        final ActionSimple defaultAs2 = new ActionSimple("M6");
        defaultAs1.enrgCours(DEFAULT_COURS1);
        defaultAs2.enrgCours(DEFAULT_COURS2);

        ActionComposee actionComposee = new ActionComposee("FranceTV");
        actionComposee.enrgComposition(defaultAs2, DEFAULT_POURCENTAGE);
        actionComposee.enrgComposition(defaultAs1, DEFAULT_POURCENTAGE);

        CrudAction listeActions = new CrudAction();
        listeActions.enregistrerAction(defaultAs2);
        listeActions.enregistrerAction(defaultAs1);

        //Action
        final String expectedToString = defaultAs1 + "-" + DEFAULT_POURCENTAGE
                + defaultAs2 + "-" + DEFAULT_POURCENTAGE;
        final String currentToString = defaultAs1 + "-"
                + actionComposee.affichagePourcentageActionSimple(defaultAs1)
                + defaultAs2 + "-"
                + actionComposee.affichagePourcentageActionSimple(defaultAs2);
        Assertions.assertEquals(expectedToString,
                currentToString, "testValueIncorrectShouldFail KO");
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
    protected final void testAfficherCoursPeriodeAnneeDifferente() {
        String messageErreur = "Date différente";
        Jour jourDebut = new Jour(DEFAULT_YEAR, 1);
        Jour jourFin = new Jour(DEFAULT_YEAR, 1);

        ActionComposee action = new ActionComposee("ActionTest");
        IllegalArgumentException exception = Assertions
                .assertThrowsExactly(IllegalArgumentException.class,
                () -> action.afficherCoursPeriode(jourDebut, jourFin),
                "Annee differente");

        Assertions.assertEquals(messageErreur, exception.getMessage(),
                "Message d'erreur devrait être le même");
    }

    /**
     * Test of testAfficherCoursPeriodeJourFutur.
     * method, of class ActionComposee.
     */
    @Test
    protected void testAfficherCoursPeriodeJourFutur() {
        String messageErreur = "Date supérieur date fin";
        Jour jourDebut = new Jour(DEFAULT_YEAR, 6);
        Jour jourFin = new Jour(DEFAULT_YEAR, 1);

        ActionComposee action = new ActionComposee("ActionTest");
        IllegalArgumentException exception = Assertions
                .assertThrowsExactly(IllegalArgumentException.class,
                () -> action.afficherCoursPeriode(jourDebut, jourFin),
                "Annee differente");

        Assertions.assertEquals(messageErreur, exception.getMessage(),
                "Message d'erreur devrait être le même");
    }

     /**
      * Test of enrgComposition method, of class ActionComposee.
      * @throws java.lang.IllegalStateException
      */
    @Test
    public final void testEnrgComposition() throws IllegalStateException {
        ActionSimple as = new ActionSimple("test1");
        ActionComposee actionComposee = new ActionComposee("test");
        actionComposee.enrgComposition(as, DEFAULT_POURCENTAGE);
    }

    /**
     * Test of testAffichageCours method, of class ActionComposee.
     */
    @Test
    public final void testAffichageCours() {
        ActionComposee actionComposee = new ActionComposee("test");
        actionComposee.affichageCours();
    }

    /**
     * Test of affichagePourcentageActionSimple method, of class ActionComposee.
     */
    @Test
    public final void testAffichagePourcentageActionSimple() {
        ActionSimple as = new ActionSimple("test1");
        ActionComposee actionComposee = new ActionComposee("test");
        float expResult = 0.0F;
        float result = actionComposee.affichagePourcentageActionSimple(as);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of suppressionCours method, of class ActionComposee.
     * @throws java.lang.Exception
     * @throws java.lang.Exception.
     */
    @Test
    public final void testSuppressionCours() throws Exception {
        ActionSimple as = new ActionSimple("test1");
        ActionComposee actionComposee = new ActionComposee("test");
        as.enrgCours(DEFAULT_COURS1);
        actionComposee.suppressionCours(as);
    }

     /**
     * Test of afficherCoursPeriode method, of class ActionComposee.
     */
    @Test
    public final void testAfficherCoursPeriode() {
        Jour fin = new Jour(DEFAULT_YEAR, 10);
        ActionComposee actionComposee = new ActionComposee("test");
        Map<Jour, Double> expResult = Collections.singletonMap(fin, 0d);
        Map<Jour, Double> result =
                actionComposee.afficherCoursPeriode(DEFAULT_JOUR, fin);
        assertEquals(expResult, result);
    }

    /**
     * Test of valeur method, of class ActionComposee.
     */
    @Test
    public final void testValeur() {
        ActionComposee actionComposee = new ActionComposee("test");
        float result = actionComposee.valeur(DEFAULT_JOUR);
        assertEquals(DEFAULT_POURCENTAGE, result, 11);
    }
}

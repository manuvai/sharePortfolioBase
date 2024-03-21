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
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

     /**
     * Test of testValueIncorrectShouldFail method, of class ActionComposee.
     * @throws java.lang.Exception
     */
     @Test
    protected final void testValueIncorrectShouldFail() throws Exception {
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

    /**
     * Test of testAfficherCoursPeriodeAnneeDifferente.
     * method, of class ActionComposee.
     */
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

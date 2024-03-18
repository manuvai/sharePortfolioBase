/*
 * Copyright 2024 theo.
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
 * @author theo
 */
public class PortefeuilleTest {

    @Test
    public void testVendreActionSimpleQteNegativeKO() {
        // GIVEN
        String expectedMessage = "La quantité doit être supérieure à 0 pour vendre cette action";
        ActionSimple action = new ActionSimple("Total");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);

        // THEN
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> portefeuille.vendre(action, -1),
                "You should be able to sell negative amounts of stocks");

        final String currentMessage = assertThrowsExactly.getMessage();

        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }

    @Test
    public void testVendreActionComposeeQteNulleKO() {
        // GIVEN
        String expectedMessage = "La quantité doit être supérieure à 0 pour vendre cette action";
        ActionComposee actionComposee = new ActionComposee("Energies");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(actionComposee, 1);

        // THEN
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> portefeuille.vendre(actionComposee, 0),
                "You should not be able to sell 0 stocks");

        final String currentMessage = assertThrowsExactly.getMessage();

        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }

    @Test
    public void testVendreActionComposeeQteSuperieureKO() {
        // GIVEN
        String expectedMessage = "La quantité demandée est supérieure à ce que vous possédez";
        ActionComposee actionComposee = new ActionComposee("Energies");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(actionComposee, 1);

        // THEN
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> portefeuille.vendre(actionComposee, 2),
                "You should not be able to sell more stocks than you have");

        final String currentMessage = assertThrowsExactly.getMessage();

        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }

    @Test
    public void testVendreActionSimpleQtePositiveOK() {
        // GIVEN
        ActionSimple action = new ActionSimple("Total");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 2);

        // THEN
        Assertions.assertDoesNotThrow(() -> portefeuille.vendre(action, 1),
                "You should be able to sell less stocks than you have");

        Assertions.assertEquals(1, portefeuille.mapLignes.get(action).getQte(), "Vente d'actions simples KO");

    }

    @Test
    public void testVendreActionComposeeQtePositiveOK() {
        // GIVEN
        ActionComposee action = new ActionComposee("Energies");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 2);

        // THEN
        Assertions.assertDoesNotThrow(() -> portefeuille.vendre(action, 1),
                "You should be able to sell less actions than you have");

        Assertions.assertEquals(1, portefeuille.mapLignes.get(action).getQte(), "Vente d'actions composées KO");

    }

    @Test
    public void testVendreActionComposeeQteTotaleOK() {
        // GIVEN
        ActionComposee action = new ActionComposee("Energies");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 2);

        // THEN
        Assertions.assertDoesNotThrow(() -> portefeuille.vendre(action, 2), "You should be able to sell all actions than you have");

        Assertions.assertNull(portefeuille.mapLignes.get(action), "Vente d'actions composées KO");

    }
    
    
    @Test
    protected void testVisualisationValeurTotalParJour() {
        // Arrange
        final int DEFAULT_YEAR = 2024;
        final int DEFAULT_DAY = 18;
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);

        // Action
        Portefeuille portefeuille = new Portefeuille();
        
        //actionS
        ActionSimple action1 = new ActionSimple("action1");
        ActionSimple action2 = new ActionSimple("action2");
        ActionSimple action3 = new ActionSimple("action3");
        
        action1.enrgCours(jour,15.0f);
        action2.enrgCours(jour,12.0f);
        action3.enrgCours(jour,11.0f);
       
        portefeuille.acheter(action1, 2);


        //actionC
        ActionComposee action4 = new ActionComposee("action4");
        action4.enrgComposition(action2, 0.5f);
        action4.enrgComposition(action3, 0.5f);

        portefeuille.acheter(action4, 100);

        //calculer
        final float expectedTotalValue = (float)(15.0 * 2 + 12.0 * 0.5 * 100 + 11.0 * 0.5 * 100 );
        final float actualTotalValue = portefeuille.valeur(jour);
       

        // Assert
        Assertions.assertEquals(expectedTotalValue, actualTotalValue, "VisualisationValeurTotalParJour KO");
        
    }
}

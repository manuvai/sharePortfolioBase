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
 * @author Yiyang/Manuvai/Yifan
 */
public class PortefeuilleTest {

    @Test
    public void testVendreActionSimpleQteNegativeKO() throws Exception {
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
    public void testVendreActionComposeeQteNulleKO() throws Exception {
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
    public void testVendreActionComposeeQteSuperieureKO() throws Exception {
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
    public void testVendreActionSimpleQtePositiveOK() throws Exception {
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
    public void testVendreActionComposeeQtePositiveOK() throws Exception {
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
    public void testVendreActionComposeeQteTotaleOK() throws Exception {
        // GIVEN
        ActionComposee action = new ActionComposee("Energies");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 2);

        // THEN
        Assertions.assertDoesNotThrow(() -> portefeuille.vendre(action, 2), "You should be able to sell all actions than you have");

        Assertions.assertNull(portefeuille.mapLignes.get(action), "Vente d'actions composées KO");

    }

    
    
   
 /**
 *
 * @author Fatima/Yassine
 */
    @Test
    public void testAcheterActionSimpleQuantiteTotaleOK() throws Exception {
        // GIVEN
        ActionSimple action = new ActionSimple("Total");
        Portefeuille portefeuille = new Portefeuille();

        // WHEN
        portefeuille.acheter(action, 10);

        // THEN
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(action), "L'action doit être présente dans le portefeuille");
        Assertions.assertEquals(10, portefeuille.mapLignes.get(action).getQte(), "La quantité d'action doit être 10");
    }
    
    
     @Test
    public void testAcheterActionComposeeeQuantiteTotaleOK() throws Exception {
        // GIVEN
        ActionComposee action = new ActionComposee("Airbus");
        Portefeuille portefeuille = new Portefeuille();

        // WHEN
        portefeuille.acheter(action, 25);

        // THEN
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(action), "L'action doit être présente dans le portefeuille");
        Assertions.assertEquals(25, portefeuille.mapLignes.get(action).getQte(), "La quantité d'action doit être 25");
    }
    
     @Test
    public void testAcheterActionComposeeeQuantiteNOK() throws Exception {
        
    String expectedMessage = "La quantité doit être supérieure à 0 pour acheter cette action";
        // GIVEN
        ActionComposee action = new ActionComposee("Airbus");
        Portefeuille portefeuille = new Portefeuille();

      
        // THEN
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> portefeuille.acheter(action, 0),
                "0");

        final String currentMessage = assertThrowsExactly.getMessage();

        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
        
    }

    
     @Test
    public void testAcheterActionSimpleNOK() throws Exception {
        
       String expectedMessage = "La quantité doit être supérieure à 0 pour acheter cette action";
         // GIVEN
        ActionSimple action = new ActionSimple("Total");
        Portefeuille portefeuille = new Portefeuille();

        // THEN
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> portefeuille.acheter(action, 0),
                "0");

        final String currentMessage = assertThrowsExactly.getMessage();

        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");
       
    }
    @Test
    public void testModifierActionSimpleQuantiteMiseAJourOK() throws Exception {
        // GIVEN
        ActionSimple actionExistante = new ActionSimple("Total");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(actionExistante, 10);

        // WHEN
        portefeuille.modifierAction(actionExistante, 20);

        // THEN
        Assertions.assertEquals(20, portefeuille.mapLignes.get(actionExistante).getQte(), "La quantité de l'action existante doit être mise à jour à 20");

        // GIVEN
        ActionSimple nouvelleActionSimple = new ActionSimple("NouvelleActionSimple");

        // WHEN
        portefeuille.modifierAction(nouvelleActionSimple, 15);

        // THEN
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(nouvelleActionSimple), "La nouvelle action simple doit être ajoutée au portefeuille");
        Assertions.assertEquals(15, portefeuille.mapLignes.get(nouvelleActionSimple).getQte(), "La quantité de la nouvelle action simple doit être de 15");
    }
    
    @Test
    public void testModifierActionComposeeQuantiteMiseAJourOK() throws Exception {
        // GIVEN
        ActionComposee actionComposeeExistante = new ActionComposee("Energies");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(actionComposeeExistante, 5);

        // WHEN
        portefeuille.modifierAction(actionComposeeExistante, 10);

        // THEN
        Assertions.assertEquals(10, portefeuille.mapLignes.get(actionComposeeExistante).getQte(), "La quantité de l'action composée existante doit être mise à jour à 10");

        // GIVEN
        ActionComposee nouvelleActionComposee = new ActionComposee("NouveauSecteur");

        // WHEN
        portefeuille.modifierAction(nouvelleActionComposee, 20);

        // THEN
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(nouvelleActionComposee), "La nouvelle action composée doit être ajoutée au portefeuille");
        Assertions.assertEquals(20, portefeuille.mapLignes.get(nouvelleActionComposee).getQte(), "La quantité de la nouvelle action composée doit être de 20");
    }
    
    @Test
    public void testEvolutionValeurTotalPortefeuille(){
        // Arrange
        final int DEFAULT_YEAR1 = 2024;
        final int DEFAULT_DAY1 = 18;
        final Jour jour1 = new Jour(DEFAULT_YEAR1, DEFAULT_DAY1);
        
        final int DEFAULT_YEAR2 = 2024;
        final int DEFAULT_DAY2 = 24;
        final Jour jour2 = new Jour(DEFAULT_YEAR2, DEFAULT_DAY2);

        // Action
        Portefeuille portefeuille = new Portefeuille();
        
        //actionSimple
        ActionSimple action1 = new ActionSimple("action1");
        ActionSimple action2 = new ActionSimple("action2");
        ActionSimple action3 = new ActionSimple("action3");
        action1.enrgCours(new Cours(jour1, 15.0f));
        action2.enrgCours(new Cours(jour1, 12.0f));
        action3.enrgCours(new Cours(jour1, 11.0f));
        
        action1.enrgCours(new Cours(jour2, 30.0f));
        action2.enrgCours(new Cours(jour2, 20.0f));
        action3.enrgCours(new Cours(jour2, 50.0f));
       
        portefeuille.acheter(action1, 2);
        //actionComposée
        ActionComposee action4 = new ActionComposee("action4");
        action4.enrgComposition(action2, 0.5f);
        action4.enrgComposition(action3, 0.5f);

        portefeuille.acheter(action4, 100);

        //calculer
        final double expectedTotalValue = (30.0 * 2 + 20.0 * 0.5 * 100 + 50.0 * 0.5 * 100 )-(15.0 * 2 + 12.0 * 0.5 * 100 + 11.0 * 0.5 * 100 );
        final double actualTotalValue = portefeuille.evolutionPortefeuille(jour1, jour2);
       

        // Assert
        Assertions.assertEquals(expectedTotalValue, actualTotalValue, "VisualisationEvolutionValeurTotalPortefeuille KO");
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
        
        action1.enrgCours(new Cours(jour, 15.0f));
        action2.enrgCours(new Cours(jour, 12.0f));
        action3.enrgCours(new Cours(jour, 11.0f));
       
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

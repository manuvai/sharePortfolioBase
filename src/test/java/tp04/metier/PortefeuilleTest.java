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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
        Assertions.assertEquals(20, portefeuille.mapLignes.get(actionExistante).getQte(),
                "La quantité de l'action existante doit être mise à jour à 20");

        // GIVEN
        ActionSimple nouvelleActionSimple = new ActionSimple("NouvelleActionSimple");

        // WHEN
        portefeuille.modifierAction(nouvelleActionSimple, 15);

        // THEN
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(nouvelleActionSimple),
                "La nouvelle action simple doit être ajoutée au portefeuille");
        Assertions.assertEquals(15, portefeuille.mapLignes.get(nouvelleActionSimple).getQte(),
                "La quantité de la nouvelle action simple doit être de 15");
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
        Assertions.assertEquals(10, portefeuille.mapLignes.get(actionComposeeExistante).getQte(),
                "La quantité de l'action composée existante doit être mise à jour à 10");

        // GIVEN
        ActionComposee nouvelleActionComposee = new ActionComposee("NouveauSecteur");

        // WHEN
        portefeuille.modifierAction(nouvelleActionComposee, 20);

        // THEN
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(nouvelleActionComposee),
                "La nouvelle action composée doit être ajoutée au portefeuille");
        Assertions.assertEquals(20, portefeuille.mapLignes.get(nouvelleActionComposee).getQte(),
                "La quantité de la nouvelle action composée doit être de 20");
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

        Cours cours1 = new Cours(jour, 15.0f);
        Cours cours2 = new Cours(jour, 12.0f);
        Cours cours3 = new Cours(jour, 45.0f);

        action1.enrgCours(cours1);
        action2.enrgCours(cours2);
        action3.enrgCours(cours3);

        portefeuille.acheter(action1, 2);


        //actionC
        ActionComposee action4 = new ActionComposee("action4");
        action4.enrgComposition(action2, 0.5f);
        action4.enrgComposition(action3, 0.5f);

        portefeuille.acheter(action4, 100);

        // calculer
        final float expectedTotalValue = (float) (15.0 * 2 + 12.0 * 0.5 * 100 + 45.0 * 0.5 * 100);
        final float actualTotalValue = portefeuille.valeur(jour);
       

        // Assert
        Assertions.assertEquals(expectedTotalValue, actualTotalValue, "VisualisationValeurTotalParJour KO");
        
    }
    
        /**
        *
        * @author Fatima/Yassine
        */
    
    @Test
    public void testVisualiserCompositionActionsComposees() {
        // Création d'une action composée
        ActionComposee actionComposee = new ActionComposee("Action composée 1");
        // Ajout d'actions simples à l'action composée
        ActionSimple actionSimple1 = new ActionSimple("Action simple 1");
        ActionSimple actionSimple2 = new ActionSimple("Action simple 2");
        actionComposee.enrgComposition(actionSimple1, 50.0f);
        actionComposee.enrgComposition(actionSimple2, 50.0f);

        // Création d'un portefeuille
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(actionComposee, 1);

        // Redirection de la sortie console vers un flux pour permettre la comparaison
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Exécution de la méthode à tester
        portefeuille.visualiserCompositionActionsComposees();

        // Récupération de la sortie imprimée
        String output = outputStream.toString();

        // Rétablissement de la sortie console normale
        System.setOut(originalOut);

        // Assertion
        String lineSeparator = System.getProperty("line.separator");
        String expectedOutput = "Composition des actions composées dans le portefeuille:" + lineSeparator
                                + "Action composée: Action composée 1" + lineSeparator
                                + "Composition de l'action composée 'Action composée 1':" + lineSeparator
                                + "- Action simple: Action simple 2, Pourcentage: 50.0" + lineSeparator
                                + "- Action simple: Action simple 1, Pourcentage: 50.0" + lineSeparator;

        assertEquals(expectedOutput, output);
    }



    
    
    
    

    @Test
    public void testNombreActionsAchetablesActionSimple() throws Exception {
        // GIVEN
        Jour jour = new Jour(2024, 100); // Exemple de jour
        float montant = 1000.0f; // Montant disponible pour l'achat
        ActionSimple actionSimple = new ActionSimple("ActionSimple");

         Cours cours2 = new Cours(jour,12.0f);

        actionSimple.enrgCours(cours2);// Enregistrement d'un cours de 50.0 pour l'action simple à ce jour

        Portefeuille portefeuille = new Portefeuille();

        // WHEN
        int nombreAchetables = portefeuille.nombreActionsAchetables(actionSimple, montant, jour);

        // THEN
        Assertions.assertEquals(83, nombreAchetables, "Le nombre d'actions achetables pour l'action simple est incorrect.");
    }

    @Test
    public void testNombreActionsAchetablesActionComposee() throws Exception {
        // GIVEN
        Jour jour = new Jour(2024, 100); // Exemple de jour
        float montant = 1000.0f; // Montant disponible pour l'achat
        ActionComposee actionComposee = new ActionComposee("ActionComposee");
        ActionSimple partSimple1 = new ActionSimple("PartSimple1");
        ActionSimple partSimple2 = new ActionSimple("PartSimple2");

        Cours cours1 = new Cours(jour,15.0f);
        Cours cours2 = new Cours(jour,12.0f);

        partSimple1.enrgCours(cours2);
        partSimple2.enrgCours(cours1);

        actionComposee.enrgComposition(partSimple1, 0.5f); // 50% de partSimple1
        actionComposee.enrgComposition(partSimple2, 0.5f); // 50% de partSimple2

        Portefeuille portefeuille = new Portefeuille();

        // WHEN
        int nombreAchetables = portefeuille.nombreActionsAchetables(actionComposee, montant, jour);

        // THEN
        Assertions.assertEquals(74, nombreAchetables, "Le nombre d'actions achetables pour l'action composée est incorrect.");
    }
    @Test
    public void testNombreActionsAchetablesActionSimpleValeurNegativeKo() throws Exception {
        // GIVEN
        String expectedMessage = "La valeur de l'action doit être supérieure à 0 pour effectuer un achat.";
        ActionSimple actionSimple = new ActionSimple("ActionSimple");
        Jour jour = new Jour(2024, 14);

        Portefeuille portefeuille = new Portefeuille();

        // THEN
        IllegalArgumentException assertThrowsExactly = Assertions.assertThrowsExactly(
                IllegalArgumentException.class
                , () -> portefeuille.nombreActionsAchetables(actionSimple, 10, jour)
                , "Vous ne devriez pas pouvoir faire d'achat pour un jour n'ayant pas de cours enregistré");

        final String currentMessage = assertThrowsExactly.getMessage();

        Assertions.assertEquals(expectedMessage, currentMessage, "Expected error message");

    }
}

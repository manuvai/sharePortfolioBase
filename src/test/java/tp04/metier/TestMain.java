package tp04.metier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author fatim
 */
public class TestMain {

  
    @Test
     protected void testAchatActionSimple() {
        //Arrange
       Portefeuille portefeuille = new Portefeuille();
    
        Jour jour2023 = new Jour(2023,12);
        Jour jour2024 = new Jour(2024,122);
        Jour jour2025 = new Jour(2025,50);

        //Action
       portefeuille.acheterActionSimple("France 2", 10, jour2023, 50);
       portefeuille.acheterActionSimple("Tisseo", 5, jour2024, 30);
       portefeuille.acheterActionSimple("Total", 15, jour2025, 70);

    
       // Assert
        // Vérification que les actions ont été correctement ajoutées au portefeuille
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(new ActionSimple("France 2")), "L'action France 2 doit être présente dans le portefeuille");
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(new ActionSimple("Tisseo")), "L'action Tisseo doit être présente dans le portefeuille");
        Assertions.assertTrue(portefeuille.mapLignes.containsKey(new ActionSimple("Total")), "L'action Total doit être présente dans le portefeuille");

        // Vérification que les quantités d'actions sont correctes
        Assertions.assertEquals(10, portefeuille.mapLignes.get(new ActionSimple("France 2")).getQte(), "La quantité d'action France 2 doit être 10");
        Assertions.assertEquals(5, portefeuille.mapLignes.get(new ActionSimple("Tisseo")).getQte(), "La quantité d'action Tisseo doit être 5");
        Assertions.assertEquals(15, portefeuille.mapLignes.get(new ActionSimple("Total")).getQte(), "La quantité d'action Total doit être 15");
    }
}

/*
 * Copyright 2024 yifan.
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

import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yifan
 */
public class ActionTest {
    
    @Test
    public void testEnregistrerEtRecupererCoursHistorique() {
        // Arrange
        final int DEFAULT_YEAR = 2024;
        final int DEFAULT_DAY = 20;
        final Jour jour = new Jour(DEFAULT_YEAR, DEFAULT_DAY);

        // Action
        ActionSimple actionS1 = new ActionSimple("action1");
        ActionSimple actionS2 = new ActionSimple("action2");
        ActionSimple actionS3 = new ActionSimple("action3");
        
        Cours cours1 = new Cours(jour, 15.0f);
        Cours cours2 = new Cours(jour, 12.0f);
        Cours cours3 = new Cours(jour, 12.0f);
        
        actionS1.enrgCours(cours1);
        actionS2.enrgCours(cours2);
        actionS3.enrgCours(cours3);

        ActionComposee actionC4 = new ActionComposee("action4");
        actionC4.enrgComposition(actionS2, 0.5f);
        actionC4.enrgComposition(actionS3, 0.5f);

        //calculer
        final float expectedValueS = (float)(15);
        final float actualValueS = actionS1.valeur(jour) ;
        
        final float expectedValueC = (float)(12);
        final float actualValueC = actionC4.valeur(jour) ;
       
        // Assert
        Assertions.assertEquals(expectedValueS , actualValueS, "testEnregistrerEtRecupererCoursHistoriqueS KO");
        Assertions.assertEquals(expectedValueC , actualValueC, "testEnregistrerEtRecupererCoursHistoriqueC KO");

    }
    
    
    @Test
    public void testHistoriqueUneActionSimpleSurUnePeriode() {
        // Arrange
        final int DEFAULT_YEAR = 2024;
        final int DEFAULT_DAY1 = 20;
        final Jour jour1 = new Jour(DEFAULT_YEAR, DEFAULT_DAY1);
        final int DEFAULT_DAY2 = 21;
        final Jour jour2 = new Jour(DEFAULT_YEAR, DEFAULT_DAY2);
        final int DEFAULT_DAY3 = 22;
        final Jour jour3 = new Jour(DEFAULT_YEAR, DEFAULT_DAY3);
        final int DEFAULT_DAY4 = 23;
        final Jour jour4 = new Jour(DEFAULT_YEAR, DEFAULT_DAY4);
        
        // Action
        ActionSimple actionS1 = new ActionSimple("action1");
        
        
        Cours cours1 = new Cours(jour1, 15.0f);
        Cours cours2 = new Cours(jour2, 12.0f);
        Cours cours3 = new Cours(jour3, 20.0f);
        Cours cours4 = new Cours(jour4, 30.0f);
        
        actionS1.enrgCours(cours1);
        actionS1.enrgCours(cours2);
        actionS1.enrgCours(cours3);
        actionS1.enrgCours(cours4);     

        //calculer
        Map<Jour, Float> expectedValue = new HashMap<>();
        expectedValue.put(new Jour(2024, 20), 15.0f);
        expectedValue.put(new Jour(2024, 23), 30.0f);
        expectedValue.put(new Jour(2024, 21), 12.0f);
        expectedValue.put(new Jour(2024, 22), 20.0f);
        Map<Jour, Float> actualValue = actionS1.afficherCoursPeriode(jour1, jour4);
        
       
        // Assert
        Assertions.assertEquals(expectedValue , actualValue, "testHistoriqueUneActionSimpleSurUnePeriode KO");

    }
    
    @Test
    public void testAfficherCoursPeriodeActionComposee() {
        //Arrange
        ActionSimple action1 = new ActionSimple("Action 1");
        ActionSimple action2 = new ActionSimple("Action 2");
        ActionComposee actionComposee = new ActionComposee("Action Composee");
        actionComposee.enrgComposition(action1, 0.5f);
        actionComposee.enrgComposition(action2, 0.5f);

        Jour jour1 = new Jour(2022, 1);
        Jour jour2 = new Jour(2022, 2);
        Jour jour3 = new Jour(2022, 3);

        
        Cours cours1 = new Cours(jour1, 100.0f);
        Cours cours2 = new Cours(jour2, 110.0f);
        Cours cours3 = new Cours(jour3, 120.0f);
        Cours cours4 = new Cours(jour1, 200.0f);
        Cours cours5 = new Cours(jour2, 220.0f);
        Cours cours6 = new Cours(jour3, 240.0f);
        
        action1.enrgCours(cours1);
        action1.enrgCours(cours2);
        action1.enrgCours(cours3);

        action2.enrgCours(cours4);
        action2.enrgCours(cours5);
        action2.enrgCours(cours6);

       //Calculer
        Map<Jour, Double> expectedValue = new HashMap<>();
        expectedValue.put(jour1, 150.0);
        expectedValue.put(jour2, 165.0);
        expectedValue.put(jour3, 180.0);
        Map<Jour, Double> actualValue = actionComposee.afficherCoursPeriode(jour1, jour3);
        
        // Assert
        Assertions.assertEquals(expectedValue, actualValue, "testAfficherCoursPeriodeActionComposee KO");

}

}

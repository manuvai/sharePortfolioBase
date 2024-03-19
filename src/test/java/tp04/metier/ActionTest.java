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
        //actionS
        ActionSimple actionS1 = new ActionSimple("action1");
        ActionSimple actionS2 = new ActionSimple("action2");
        ActionSimple actionS3 = new ActionSimple("action3");
        
        Cours cours1 = new Cours(jour, 15.0f);
        Cours cours2 = new Cours(jour, 12.0f);
        Cours cours3 = new Cours(jour, 12.0f);
        
        actionS1.enrgCours(cours1);
        actionS2.enrgCours(cours2);
        actionS3.enrgCours(cours3);

        
        //actionC
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
}

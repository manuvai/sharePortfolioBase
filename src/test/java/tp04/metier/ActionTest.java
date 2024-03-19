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
        Portefeuille portefeuille = new Portefeuille();
        
        //actionS
        ActionSimple action1 = new ActionSimple("action1");
        ActionSimple action2 = new ActionSimple("action2");
        ActionSimple action3 = new ActionSimple("action3");
        
        action1.enrgCours(jour,20.0f);
        action2.enrgCours(jour,12.0f);
        action3.enrgCours(jour,11.0f);

        //actionC
        ActionComposee action4 = new ActionComposee("action4");
        action4.enrgComposition(action2, 0.5f);
        action4.enrgComposition(action3, 0.5f);

        //calculer
        final float expectedValueS = (float)(20);
        final float actualValueS = ;
       
        // Assert
        Assertions.assertEquals(expectedValueS , actualValueS, "testEnregistrerEtRecupererCoursHistoriqueS KO");

    }
}

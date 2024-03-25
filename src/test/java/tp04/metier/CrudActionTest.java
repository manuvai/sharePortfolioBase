/*
 * Copyright 2024 manuvai.
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

import java.util.Collections;
import java.util.List;

public class CrudActionTest {

    @Test
    protected void testGetListeActionsListeVideOK() {
        CrudAction crudAction = new CrudAction();
        List<AbstractAction> actionList = crudAction.getListeActions();

        Assertions.assertNotNull(actionList, "Le retour de la méthode ne devrait pas être nul");
        Assertions.assertTrue(actionList.isEmpty(), "La liste d'actions devrait être vide");
    }
    @Test
    protected void testSetListeActionsOK() {
        CrudAction crudAction = new CrudAction();
        List<AbstractAction> expectedList = Collections.singletonList(new ActionSimple("Action Test"));
        crudAction.setListeActions(expectedList);

        List<AbstractAction> actualList = crudAction.getListeActions();

        Assertions.assertNotNull(actualList, "Le retour de la méthode ne devrait pas être nul");
        Assertions.assertFalse(actualList.isEmpty(), "La liste d'actions ne devrait pas être vide");

        boolean isListEqual = expectedList.containsAll(actualList)
                && actualList.containsAll(expectedList);
        Assertions.assertTrue(isListEqual, "Les listes devraient correspondre");
    }
    @Test
    protected void testEnleverActionOK() {
        AbstractAction action = new ActionSimple("Action Test");

        CrudAction crudAction = new CrudAction();
        crudAction.enregistrerAction(action);

        crudAction.enleverAction(action);

        List<AbstractAction> actualList = crudAction.getListeActions();

        Assertions.assertNotNull(actualList, "Le retour de la méthode ne devrait pas être nul");
        Assertions.assertFalse(actualList.contains(action), "La liste ne devrait plus contenir l'action retirée");
    }
}

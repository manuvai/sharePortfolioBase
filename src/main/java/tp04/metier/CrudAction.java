/*
 * Copyright 2024 Usuari.
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

import java.util.ArrayList;

/*
 * Démarche de développement agile
 *
 * @author Moli, Nguyen
 *
 * 18/03/2024
 *
 * Copyright
 */

public class CrudAction {

	// Paramètres 
	private ArrayList<Action> ListeActions = new ArrayList<>();

	public CrudAction() {
		super();
	}

	public ArrayList<Action> getListeActions() {
		return ListeActions;
	}

	public void setListeActions(ArrayList<Action> listeActions) {
		ListeActions = listeActions;
	}
	
	// Fonction permettant d'enregistrer une action dans la liste d'action simple
	public void enregistrerAction ( Action action ) {
		// Ajout a la liste
		this.ListeActions.add(action);
	}
	
        // Fonction permettant d'afficher une liste d'actions
	public void affichageListeAction () {
		System.out.println("Affichage Liste d'actions");
		for (Action a : this.ListeActions) {
			System.out.println(a.getLibelle());
		}
	}
        
        // Fonction permettant d'enlever une action de la liste d'actions
	public void enleverAction ( Action action ) {
		// Ajout a la liste
		this.ListeActions.remove(action);
	}
}

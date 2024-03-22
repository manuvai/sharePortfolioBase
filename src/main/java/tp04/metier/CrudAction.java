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
import java.util.List;

/**
 * Démarche de développement agile.
 *
 * @author Moli, Nguyen
 * 18/03/2024
 *
 * Copyright
 */
public class CrudAction {

/**
 * Liste des actions.
 * Cette liste contient les actions
 * associées à l'objet en cours.
 */
	private List<Action> listeActions = new ArrayList<>();

	/**
	 * Retourne la liste des actions contenues.
	 *
	 * @return La liste des actions
	 */
	public final List<Action> getListeActions() {
		return listeActions;
	}

	/**
	 * mets à jour la liste des actions.
	 *
	 * @param inListeActions la liste des actions
	 */
	public final void setListeActions(final List<Action>
                inListeActions) {
		listeActions = inListeActions;
	}

	/**
	 * Fonction permettant d'enregistrer.
         * une action dans la liste d'action simple
	 *
	 * @param action L'action à ajouter
	 */
	public final void enregistrerAction(final Action action) {
		// Ajout a la liste
		listeActions.add(action);
	}

	/**
	 * Fonction permettant d'afficher une liste d'actions.
	 */
	public final void affichageListeAction() {
		System.out.println("Affichage Liste d'actions");
		for (Action a : listeActions) {
			System.out.println(a.getLibelle());
		}
	}

	/**
	 * Fonction permettant d'enlever.
         * une action de la liste d'actions
	 *
	 * @param action l'action à enlever
	 */
	public final void enleverAction(final Action action) {
		// Ajout a la liste
		listeActions.remove(action);
	}
}

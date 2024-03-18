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

public class Main {

	public static void main(String[] args) throws Exception {
	
		// Test Marc et Julie
		
		// Création des jours
		Jour jour202010 = new Jour(2020, 10);
		Jour jour202050 = new Jour(2020, 50);
		Jour jour202160 = new Jour(2021, 60);
		Jour jour202240 = new Jour(2022, 40);
		Jour jour202320 = new Jour(2023, 20);
		
		// Création de la valeur de l'action a une date donnée
		Cours cours0 = new Cours(jour202010, 30);
		Cours cours1 = new Cours(jour202050, 25);
		Cours cours2 = new Cours(jour202160, 26);
		Cours cours3 = new Cours(jour202240, 27);
		Cours cours4 = new Cours(jour202320, 28);
		
		// Création action Simple
		ActionSimple France1 = new ActionSimple("France 1");
		ActionSimple M6 = new ActionSimple("M6");
		ActionSimple Facebook = new ActionSimple("Facebook");
		ActionSimple Tesla = new ActionSimple("Tesla");
		
		// Enregistrement cours pour une action simple
		System.out.println("Phase enregistrement cours : action simple");
		France1.enrgCours(cours0);
		France1.enrgCours(cours1);
		M6.enrgCours(cours4);
		
		France1.affichageCours();
		
		CrudAction listeActions = new CrudAction();
		listeActions.enregistrerAction(France1);
		listeActions.enregistrerAction(Facebook);
		
		listeActions.affichageListeAction();
                
                //Ajouter action composée
                ActionComposee FranceTv = new ActionComposee("FranceTV");
                ActionComposee CanalPlus = new ActionComposee("6play");
                ActionComposee Meta = new ActionComposee("Meta");
                
                FranceTv.enrgComposition(France1, 10);
                FranceTv.enrgComposition(M6, 25);
                
                Meta.enrgComposition(Facebook,14);
                
                FranceTv.affichageCours();
                
	}
}

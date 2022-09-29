package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.controller.NullConfrontationException;
import com.statistant.ligue1.controller.StatisticController;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConfrontationOverviewController {
	
	@FXML private Button nouveau;
	@FXML private Button modifier;
	@FXML private Button menuPrincipal;

	public Button getNouveau() {
		return nouveau;
	}

	public void setNouveau(Button nouveau) {
		this.nouveau = nouveau;
	}

	public Button getModifier() {
		return modifier;
	}

	public void setModifier(Button modifier) {
		this.modifier = modifier;
	}

	public Button getMenuPrincipal() {
		return menuPrincipal;
	}

	public void setMenuPrincipal(Button menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}

	@FXML
	private void initialize() {
		Button btnMenu = getMenuPrincipal();
		Image imgMenu = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/maison.png").toExternalForm());
		ImageView viewMenu = new ImageView(imgMenu);
		btnMenu.setGraphic(viewMenu);
		setMenuPrincipal(btnMenu);

		Button btnNouveau = getNouveau();
		Image imgNouveau = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/plus.png").toExternalForm());
		ImageView viewNouveau = new ImageView(imgNouveau);
		btnNouveau.setGraphic(viewNouveau);
		if (!AuthentificationOverviewController.getUSER_LOGIN().equals("sysadmin")) {
			btnNouveau.setVisible(false);
		}
		setNouveau(btnNouveau);

		Button btnModifier = getModifier();
		Image imgModifier = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/modifier.png").toExternalForm());
		ImageView viewModifier = new ImageView(imgModifier);
		btnModifier.setGraphic(viewModifier);
		if (!AuthentificationOverviewController.getUSER_LOGIN().equals("sysadmin")) {
			btnModifier.setVisible(false);
		}
		setModifier(btnModifier);
	}

	@FXML
	private void handleMainMenu() {
		InitializeWindow.showMenuOverview();
	}

	@FXML
	private void handleNew() {
		InitializeWindow.showNewConfrontationWindow();
	}

	@FXML
	private void handleModify() {
		TableView<Confrontation> tableConfrontation = InitializeWindow.getTableConfrontations();
		if (tableConfrontation != null) {
			Confrontation confrontation = tableConfrontation.getSelectionModel().getSelectedItem();
			if (confrontation == null) {
				Ligue1Utils.reportError("Merci de saisir une confrontation à modifier");
				return;
			}
			InitializeWindow.showModifyConfrontationWindow(confrontation);
		} else {
			Ligue1Utils.reportError("Erreur à la récupération du tableau des confrontations");
		}
	}

	@FXML
	private void handleUpdate() {
		StatisticController.execute();
		Ligue1Utils.reportInfo("Mise à jour des statistiques des confrontations OK après clic sur le bouton !");
		InitializeWindow.alertInfo("Mise à jour des statistiques des confrontations OK après clic sur le bouton !");
	}

	@FXML
	private void handleSecondLeg() {
		try {
			Ligue1Utils.createSecondLeg();
		} catch (NullConfrontationException e) {
			Ligue1Utils.reportError(e.getMessage());
			e.printStackTrace();
		}
		InitializeWindow.alertInfo("Création des confrontations retour OK après clic sur le bouton !");
		InitializeWindow.showConfrontationOverview();
	}

}

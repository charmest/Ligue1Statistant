package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.view.InitializeWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StandingOverviewController {

	@FXML
	private Button general;
	@FXML
	private Button domicile;
	@FXML
	private Button exterieur;
	@FXML
	private Button menuPrincipal;

	public Button getGeneral() {
		return general;
	}

	public void setGeneral(Button general) {
		this.general = general;
	}

	public Button getDomicile() {
		return domicile;
	}

	public void setDomicile(Button domicile) {
		this.domicile = domicile;
	}

	public Button getExterieur() {
		return exterieur;
	}

	public void setExterieur(Button exterieur) {
		this.exterieur = exterieur;
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

		Button btnGeneral = getGeneral();
		Image imgGeneral = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/ballon.png").toExternalForm());
		ImageView viewGeneral = new ImageView(imgGeneral);
		btnGeneral.setGraphic(viewGeneral);
		setGeneral(btnGeneral);

		Button btnDomicile = getDomicile();
		Image imgDomicile = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/maison.png").toExternalForm());
		ImageView viewDomicile = new ImageView(imgDomicile);
		btnDomicile.setGraphic(viewDomicile);
		setDomicile(btnDomicile);
		
		Button btnExterieur = getExterieur();
		Image imgExterieur = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/avion.png").toExternalForm());
		ImageView viewExterieur = new ImageView(imgExterieur);
		btnExterieur.setGraphic(viewExterieur);
		setExterieur(btnExterieur);
	}

	@FXML
	private void handleMainMenu() {
		InitializeWindow.showMenuOverview();
	}

	@FXML
	private void handleGeneral() {
		InitializeWindow.showStandingOverview();
	}

	@FXML
	private void handleHome() {
		InitializeWindow.showHomeStandingOverview();
	}

	@FXML
	private void handleAway() {
		InitializeWindow.showAwayStandingOverview();
	}

}

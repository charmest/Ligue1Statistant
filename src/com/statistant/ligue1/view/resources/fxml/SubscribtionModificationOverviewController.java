package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.controller.IncompatibleEmailException;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullUserException;
import com.statistant.ligue1.pojo.User;
import com.statistant.ligue1.utils.AuthentificationUtils;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SubscribtionModificationOverviewController {

	@FXML
	private TextField login;
	@FXML
	private TextField email;
	private final StringProperty typeAbo = new SimpleStringProperty();
	@FXML
	private ChoiceBox<String> subscribtionType;
	ObservableList<String> abonnements = FXCollections.observableArrayList("EQUIPES", "JOURNEES");
	@FXML
	private TextField teams;
	@FXML
	private TextField nbReportsPerTeam;
	@FXML
	private TextField nbReportsLeft;
	@FXML
	private TextField journeys;
	@FXML
	private Text textNbReportsPerTeam;
	@FXML
	private Text textNbReportsLeft;
	@FXML
	private Text textTeams;
	@FXML
	private Text textJourneys;
	@FXML
	private Button menu;

	public Button getMenu() {
		return menu;
	}

	public void setMenu(Button menu) {
		this.menu = menu;
	}

	public String getLogin() {
		return login.getText();
	}

	public void setLogin(String login) {
		this.login.setText(login);
	}

	public String getEmail() {
		return email.getText();
	}

	public void setEmail(String email) {
		this.email.setText(email);
	}

	public String getSubscribtionType() {
		return subscribtionType.getValue().toString();
	}

	public void setSubscribtionType(String subscribtionType) {
		this.typeAbo.set(subscribtionType);
		this.subscribtionType.setValue(subscribtionType);
	}

	public String getTeams() {
		return teams.getText();
	}

	public void setTeams(String teams) {
		this.teams.setText(teams);
	}

	public Integer getNbReportsPerTeam() {
		return Integer.parseInt(nbReportsPerTeam.getText());
	}

	public void setNbReportsPerTeam(String nbReportsPerTeam) {
		this.nbReportsPerTeam.setText(nbReportsPerTeam);
	}

	public Integer getNbReportsLeft() {
		return Integer.parseInt(nbReportsLeft.getText());
	}

	public void setNbReportsLeft(String nbReportsLeft) {
		this.nbReportsLeft.setText(nbReportsLeft);
	}

	public String getJourneys() {
		return journeys.getText();
	}

	public void setJourneys(String journeys) {
		this.journeys.setText(journeys);
	}

	@FXML
	public void handleBackToMainMenu() {
		InitializeWindow.showMenuOverview();
	}
	
	@FXML
	public void handleValidate() {
		String login = getLogin();
		String email = getEmail();
		try {
			User user = DatabaseConnection.getUserByLogin(login);
			AuthentificationUtils.emailMatchesLogin(email, login);
			String typeAbo = getSubscribtionType();
			if (Ligue1Utils.isEmpty(typeAbo)) {
				Ligue1Utils.reportError("Merci de sélectionner un type d'abonnement à mettre en place.");
				return;
			}
			if (typeAbo.equals("JOURNEES")) {
				user.setSubscribtionType("JOURNEES");
				user.setJourneesSubscribed(getJourneys());
			}
			if (typeAbo.equals("EQUIPES")) {
				user.setSubscribtionType("EQUIPES");
				user.setMyTeams(getTeams());
				user.setNbReportsPerTeam(getNbReportsPerTeam());
				user.setNbReportsLeft(getNbReportsLeft());
			}
			DatabaseConnection.createOrUpdateUser(user);
			InitializeWindow.alertInfo("L'abonnement de l'utilisateur " + user.getLogin()
					+ " a été modifié avec succès.");
			InitializeWindow.showMenuOverview();
		} catch (NullUserException | IncompatibleEmailException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
	}

	@FXML
	private void initialize() {
		subscribtionType.setItems(abonnements);
		Button btnMenu = getMenu();
		Image imgMenu = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/maison.png").toExternalForm());
		ImageView viewMenu = new ImageView(imgMenu);
		btnMenu.setGraphic(viewMenu);
		setMenu(btnMenu);

		Button btnValidate = getMenu();
		Image imgValidate = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/modifier.png").toExternalForm());
		ImageView viewValidate = new ImageView(imgValidate);
		btnValidate.setGraphic(viewValidate);
		setMenu(btnValidate);
	}
}

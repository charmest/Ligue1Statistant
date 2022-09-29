package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.controller.IncoherentArgumentException;
import com.statistant.ligue1.controller.UnhandledPasswordException;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullUserException;
import com.statistant.ligue1.pojo.User;
import com.statistant.ligue1.utils.AuthentificationUtils;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PasswordModificationWithBackButtonOverviewController {
	
	private final StringProperty identifiant = new SimpleStringProperty();
	@FXML private TextField login;
	@FXML private PasswordField newPassword;
	@FXML private PasswordField confirmationPassword;
	@FXML
	private Button validate;
	@FXML
	private Button back;
	
	public Button getValidate() {
		return validate;
	}

	public void setValidate(Button validate) {
		this.validate = validate;
	}

	public Button getBack() {
		return back;
	}

	public void setBack(Button back) {
		this.back = back;
	}

	public String getLogin() {
		return login.getText();
	}

	public void setLogin(String login) {
		this.identifiant.set(login);
		this.login.setText(login);
	}

	public PasswordField getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(PasswordField newPassword) {
		this.newPassword = newPassword;
	}

	public PasswordField getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(PasswordField confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}
	
	@FXML
	private void initialize() {
		Button btnValidate = getValidate();
		Image imgValidate = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/connection.png").toExternalForm());
		ImageView viewValidate = new ImageView(imgValidate);
		btnValidate.setGraphic(viewValidate);
		setValidate(btnValidate);
		
		Button btnBack = getBack();
		Image imgBack = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/retour.png").toExternalForm());
		ImageView viewBack = new ImageView(imgBack);
		btnBack.setGraphic(viewBack);
		setBack(btnBack);
	}

	@FXML
	public void handleValidate() {
		String login = getLogin();
		String newPassword = getNewPassword().getText();
		String confirmationPassword = getConfirmationPassword().getText();
		try {
			AuthentificationUtils.checkAreNotEmpty(newPassword, confirmationPassword);
			User user = DatabaseConnection.getUserByLogin(login);
			AuthentificationUtils.checkPasswordMatchRequiredOptions(newPassword);
			AuthentificationUtils.checkPasswordEqualsConfirmation(newPassword, confirmationPassword);
			user.setPassword(AuthentificationUtils.crypt(newPassword));
			user.setPasswordModified(1);
			DatabaseConnection.createOrUpdateUser(user);
			InitializeWindow.alertInfo("Votre mot de passe a été modifié avec succès.");
			AuthentificationOverviewController.setUSER_LOGIN(user.getLogin());
			AuthentificationOverviewController.setUSER_CONNECTED(DatabaseConnection.getUserByLogin(login));
			AuthentificationOverviewController.setREPORT_PATH(user.getReportPath());
			AuthentificationOverviewController.setJOURNEES_SUBSCRIBED(user.getJourneesSubscribed());
			AuthentificationOverviewController.setMY_TEAMS(user.getMyTeams());
			AuthentificationOverviewController.setSUBSCRIPTION_TYPE(user.getSubscribtionType());
			AuthentificationOverviewController.setNB_REPORTS_LEFT(user.getNbReportsLeft());
			AuthentificationOverviewController.setNB_REPORTS_PER_TEAM(user.getNbReportsPerTeam());
			InitializeWindow.showMenuOverview();
		}
		catch (IncoherentArgumentException | NullUserException | UnhandledPasswordException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
	}
	
	@FXML
	public void handleBack() {
		InitializeWindow.showAccountOverview(getLogin());
	}

	public void setUser(User user) {
		identifiant.set(user.getLogin());
		setLogin(user.getLogin());
		login.setText(getLogin());
	}

}

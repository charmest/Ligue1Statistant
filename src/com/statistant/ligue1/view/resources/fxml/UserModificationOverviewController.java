package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.controller.IncompatibleEmailException;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullUserException;
import com.statistant.ligue1.pojo.User;
import com.statistant.ligue1.utils.AuthentificationUtils;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserModificationOverviewController {

	@FXML
	private TextField login;
	@FXML
	private TextField email;
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
		this.login.setText(login);
	}

	public String getEmail() {
		return email.getText();
	}

	public void setEmail(TextField email) {
		this.email = email;
	}

	public void setLogin(TextField login) {
		this.login = login;
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
		String email = getEmail();
		try {
			User user = DatabaseConnection.getUserByLogin(login);
			AuthentificationUtils.emailMatchesLogin(email, login);
			user.setPassword(AuthentificationUtils.crypt("1234"));
			user.setPasswordModified(0);
			DatabaseConnection.createOrUpdateUser(user);
			InitializeWindow.alertInfo("Le mot de passe de l'utilisateur " + user.getLogin()
					+ " a été modifié avec succès. C'est 1234 mais il faudra le modifier à la prochaine connexion.");
			InitializeWindow.showMenuOverview();
		} catch (NullUserException | IncompatibleEmailException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
	}

	@FXML
	public void handleBack() {
		InitializeWindow.showMenuOverview();
	}

}

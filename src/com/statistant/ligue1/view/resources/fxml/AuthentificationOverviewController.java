package com.statistant.ligue1.view.resources.fxml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.statistant.ligue1.controller.ExpiredMembershipException;
import com.statistant.ligue1.controller.IncoherentArgumentException;
import com.statistant.ligue1.controller.NullConfrontationException;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullMatchException;
import com.statistant.ligue1.dao.NullUserException;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.pojo.Match;
import com.statistant.ligue1.pojo.User;
import com.statistant.ligue1.utils.AuthentificationUtils;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AuthentificationOverviewController {

	private static String USER_LOGIN;
	private static User USER_CONNECTED;
	private static String REPORT_PATH;
	private static String JOURNEES_SUBSCRIBED;
	private static String MY_TEAMS;
	private static String SUBSCRIPTION_TYPE;
	private static int NB_REPORTS_LEFT;
	private static int NB_REPORTS_PER_TEAM;

	public static String getUSER_LOGIN() {
		return USER_LOGIN;
	}

	public static void setUSER_LOGIN(String uSER_LOGIN) {
		USER_LOGIN = uSER_LOGIN;
	}

	public static User getUSER_CONNECTED() {
		return USER_CONNECTED;
	}

	public static void setUSER_CONNECTED(User uSER_CONNECTED) {
		USER_CONNECTED = uSER_CONNECTED;
	}

	public static String getREPORT_PATH() {
		return REPORT_PATH;
	}

	public static void setREPORT_PATH(String rEPORT_PATH) {
		REPORT_PATH = rEPORT_PATH;
	}

	public static String getJOURNEES_SUBSCRIBED() {
		return JOURNEES_SUBSCRIBED;
	}

	public static void setJOURNEES_SUBSCRIBED(String jOURNEES_SUBSCRIBED) {
		JOURNEES_SUBSCRIBED = jOURNEES_SUBSCRIBED;
	}

	public static String getMY_TEAMS() {
		return MY_TEAMS;
	}

	public static void setMY_TEAMS(String mY_TEAMS) {
		MY_TEAMS = mY_TEAMS;
	}

	public static String getSUBSCRIPTION_TYPE() {
		return SUBSCRIPTION_TYPE;
	}

	public static void setSUBSCRIPTION_TYPE(String sUBSCRIPTION_TYPE) {
		SUBSCRIPTION_TYPE = sUBSCRIPTION_TYPE;
	}

	public static int getNB_REPORTS_LEFT() {
		return NB_REPORTS_LEFT;
	}

	public static void setNB_REPORTS_LEFT(int nB_REPORTS_LEFT) {
		NB_REPORTS_LEFT = nB_REPORTS_LEFT;
	}

	public static int getNB_REPORTS_PER_TEAM() {
		return NB_REPORTS_PER_TEAM;
	}

	public static void setNB_REPORTS_PER_TEAM(int nB_REPORTS_PER_TEAM) {
		NB_REPORTS_PER_TEAM = nB_REPORTS_PER_TEAM;
	}

	@FXML
	private TextField login;
	@FXML
	private PasswordField password;
	@FXML
	private Button connection;
	@FXML
	private Button forgottenPassword;
	
	public Button getConnection() {
		return connection;
	}

	public void setConnection(Button connection) {
		this.connection = connection;
	}

	public Button getForgottenPassword() {
		return forgottenPassword;
	}

	public void setForgottenPassword(Button forgottenPassword) {
		this.forgottenPassword = forgottenPassword;
	}

	public TextField getLogin() {
		return login;
	}

	public void setLogin(TextField login) {
		this.login = login;
	}

	public PasswordField getPassword() {
		return password;
	}

	public void setPassword(PasswordField password) {
		this.password = password;
	}
	
	@FXML
	private void initialize() {
		Button btnConnection = getConnection();
		Image imgConnect = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/connection.png").toExternalForm());
		ImageView viewConnect = new ImageView(imgConnect);
		btnConnection.setGraphic(viewConnect);
		setConnection(btnConnection);
		
		Button btnForgottenPassword = getForgottenPassword();
		Image imgForgottenPassword = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/question.png").toExternalForm());
		ImageView viewForgottenPassword = new ImageView(imgForgottenPassword);
		btnForgottenPassword.setGraphic(viewForgottenPassword);
		setForgottenPassword(btnForgottenPassword);
	}

	@FXML
	public void handleConnect() {
		String inputLogin = getLogin().getText();
		String inputPassword = getPassword().getText();
		try {
			AuthentificationUtils.checkAreNotEmpty(inputLogin, inputPassword);
			DatabaseConnection.getUserByLogin(inputLogin);
			User user = DatabaseConnection.getUserByLoginAndPassword(inputLogin,
					AuthentificationUtils.crypt(inputPassword));
			AuthentificationUtils.checkSubscribtionIsActive(user);
			if (AuthentificationUtils.passwordIsModified(user)) {
				setUSER_LOGIN(user.getLogin());
				setUSER_CONNECTED(DatabaseConnection.getUserByLogin(getUSER_LOGIN()));
				setREPORT_PATH(user.getReportPath());
				setJOURNEES_SUBSCRIBED(user.getJourneesSubscribed());
				setMY_TEAMS(user.getMyTeams());
				setSUBSCRIPTION_TYPE(user.getSubscribtionType());
				setNB_REPORTS_LEFT(user.getNbReportsLeft());
				setNB_REPORTS_PER_TEAM(user.getNbReportsPerTeam());
				if (getSUBSCRIPTION_TYPE().equals("EQUIPES") && getNB_REPORTS_LEFT() == 0) {
					InitializeWindow.alertError("Vous n'avez plus de crédit de génération de rapport... Vous pouvez tout de même accéder à l'application. Merci de contacter l'administrateur à l'adresse mail \"support@statistant.fr\" pour modifier l'abonnement et pouvoir à nouveau générer des rapports.");
				}
				InitializeWindow.showMenuOverview();
			} else {
				InitializeWindow.showPasswordModificationOverview(user);
			}
		} catch (IncoherentArgumentException | NullUserException | ExpiredMembershipException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}

	}

	@FXML
	public void handleForgottenPassword() {
		InitializeWindow
				.alertInfo("Merci de contacter l'administrateur à l'adresse suivante \"support@statistant.fr\".");
	}

	public static ObservableList<Match> getMatchesOfMyTeams() {
		String teams = AuthentificationOverviewController.MY_TEAMS;
		String[] split = teams.split(";");
		ObservableList<Match> myTeamsMatches = FXCollections.observableArrayList();
		for (String team : split) {
			myTeamsMatches.addAll(DatabaseConnection.getAllMatchesForTeam(team));
		}
		List<String> listIds = new ArrayList<String>();
		for (Match match : myTeamsMatches) {
			listIds.add(match.getId());
		}
		Set<String> set = new HashSet<String>(listIds);
		ObservableList<Match> myTeamsMatchesWithoutDoublons = FXCollections.observableArrayList();
		for (String matchId : set) {
			try {
				Match match = DatabaseConnection.getMatch(matchId);
				myTeamsMatchesWithoutDoublons.add(match);
			} catch (NullMatchException e) {
				Ligue1Utils.reportError(e.getMessage());
			}
		}
		return myTeamsMatchesWithoutDoublons;
	}

	public static ObservableList<Confrontation> getConfrontationsOfMyTeams() {
		String teams = AuthentificationOverviewController.MY_TEAMS;
		String[] split = teams.split(";");
		ObservableList<Confrontation> myTeamsConfrontations = FXCollections.observableArrayList();
		ObservableList<Confrontation> myTeamsConfrontationsWithoutDoublons = FXCollections.observableArrayList();
		try {
			for (String team : split) {
				myTeamsConfrontations.addAll(DatabaseConnection.getAllConfrontationsForTeam(team));
			}
			List<String> listIds = new ArrayList<String>();
			for (Confrontation confrontation : myTeamsConfrontations) {
				listIds.add(confrontation.getMatch());
			}
			Set<String> set = new HashSet<String>(listIds);
			for (String confrontationId : set) {
				Confrontation confrontation = DatabaseConnection.getConfrontation(confrontationId);
				myTeamsConfrontationsWithoutDoublons.add(confrontation);
			}
		} catch (NullConfrontationException e) {
			Ligue1Utils.reportError(e.getMessage());
		}
		return myTeamsConfrontationsWithoutDoublons;
	}

	public static ObservableList<Match> getMatchesOfMyJourneys() {
		String journeys = AuthentificationOverviewController.JOURNEES_SUBSCRIBED;
		String[] split = journeys.split(";");
		ObservableList<Match> myJourneysMatches = FXCollections.observableArrayList();
		for (String journey : split) {
			myJourneysMatches.addAll(DatabaseConnection.getAllMatchesForJourney(journey));
		}
		return myJourneysMatches;
	}

	public static ObservableList<Confrontation> getConfrontationsOfMyJourneys() {
		String journeys = AuthentificationOverviewController.JOURNEES_SUBSCRIBED;
		String[] split = journeys.split(";");
		ObservableList<Confrontation> myJourneysConfrontations = FXCollections.observableArrayList();
		for (String journey : split) {
			myJourneysConfrontations.addAll(DatabaseConnection.getAllConfrontationsForJourney(journey));
		}
		return myJourneysConfrontations;
	}

	public static void main(String[] args) {

	}

}

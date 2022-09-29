package com.statistant.ligue1.view.resources.fxml;

import java.io.IOException;

import com.statistant.ligue1.controller.CountMatch;
import com.statistant.ligue1.controller.GenerateStatisticsReport;
import com.statistant.ligue1.controller.InvalidNumberToConvertFromBooleanException;
import com.statistant.ligue1.controller.MatchController;
import com.statistant.ligue1.controller.NullConfrontationException;
import com.statistant.ligue1.controller.NullResourceSelectedException;
import com.statistant.ligue1.controller.NullStatisticException;
import com.statistant.ligue1.controller.SameTeamsException;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullMatchException;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.dao.NullUserException;
import com.statistant.ligue1.pojo.Match;
import com.statistant.ligue1.pojo.Statistic;
import com.statistant.ligue1.pojo.User;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MatchOverviewController {

	@FXML private Button nouveau;
	@FXML private Button modifier;
	@FXML private Button menuPrincipal;
	@FXML private Button generateReport;
	@FXML private Button comptabiliser;

	public Button getGenerateReport() {
		return generateReport;
	}

	public void setGenerateReport(Button generateReport) {
		this.generateReport = generateReport;
	}

	public Button getComptabiliser() {
		return comptabiliser;
	}

	public void setComptabiliser(Button comptabiliser) {
		this.comptabiliser = comptabiliser;
	}

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
		
		Button btnGenerate = getGenerateReport();
		Image imgGenerate = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/rapport.png").toExternalForm());
		ImageView viewGenerate = new ImageView(imgGenerate);
		btnGenerate.setGraphic(viewGenerate);
		setGenerateReport(btnGenerate);

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
		
		Button btnCount = getComptabiliser();
		Image imgCount = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/ballon.png").toExternalForm());
		ImageView viewCount = new ImageView(imgCount);
		btnCount.setGraphic(viewCount);
		if (!AuthentificationOverviewController.getUSER_LOGIN().equals("sysadmin")) {
			btnCount.setVisible(false);
		}
		setComptabiliser(btnCount);
	}
	
	@FXML
	private void handleMainMenu() {
		InitializeWindow.showMenuOverview();
	}

	@FXML
	private void handleModify() {
		TableView<Match> tableMatch = InitializeWindow.getTableMatchs();
		if (tableMatch != null) {
			Match match = tableMatch.getSelectionModel().getSelectedItem();
			if (match == null) {
				Ligue1Utils.reportError("Merci de saisir un match à modifier");
				return;
			}
			InitializeWindow.showModifyMatchWindow(match);
		} else {
			Ligue1Utils.reportError("Erreur à la récupération du tableau des matchs");
		}
	}

	@FXML
	private void handleNew() {
		InitializeWindow.showNewMatchWindow();
	}

	@FXML
	private void handleCount() {
		int nbMatchsCounted = 0;
		try {
			nbMatchsCounted = CountMatch.execute();
		} catch (NullConfrontationException | NullTeamException e) {
			Ligue1Utils.reportError("Erreur à la comptabilisation des matchs de Ligue 1 : " + e.getMessage());
			e.printStackTrace();
			return;
		}
		if (nbMatchsCounted > 0) {
			DatabaseConnection.saveAllSeason();
			InitializeWindow.alertInfo(
					"La saison a été enregistrée dans le dossier \"C:\\perso\\Ligue1\\sauvegardes\" avec succès.");
		}
		InitializeWindow.showMatchOverview();
	}

	private static Match getSelectedMatch() throws NullResourceSelectedException {
		TableView<Match> tableMatch = InitializeWindow.getTableMatchs();
		Match match = tableMatch.getSelectionModel().getSelectedItem();
		if (match == null) {
			throw new NullResourceSelectedException(
					"Erreur à la récupération du match depuis la table des matchs. Saisir un match.");
		}
		return match;
	}

	private static void generateReportMatch(Match match, String selectedDirectoryPath) {
		String homeTeam = match.getHomeTeamNickname();
		String awayTeam = match.getAwayTeamNickname();

		try {
			MatchController.checkTeamsAreOK(homeTeam, awayTeam);
		} catch (NullTeamException | SameTeamsException e) {
			Ligue1Utils.reportError("Incohérence dans les données du formulaire !");
			e.printStackTrace();
		}

		try {
			String matchName = homeTeam + "-" + awayTeam;
			Statistic stat = null;
			try {
				stat = DatabaseConnection.getStatistic(matchName);
			} catch (NullStatisticException e) {

			}
			if (stat != null) {
				Ligue1Utils.reportInfo("Les statistiques du match " + matchName + " ont récupérées avec succès.");
				GenerateStatisticsReport.generateReport(stat, selectedDirectoryPath);
				Ligue1Utils.reportInfo("Le rapport du match " + matchName + " a été généré avec succès.");
			}
		} catch (IOException | NullTeamException | NullConfrontationException | NullMatchException
				| InvalidNumberToConvertFromBooleanException | NullStatisticException ex) {
			Ligue1Utils.reportError("Erreur à la génération du rapport de statistiques !" + ex.getMessage());
			ex.printStackTrace();
			return;
		}
	}

	@FXML
	private void handleGenerateReport() {
		Match match = null;
		try {
			match = getSelectedMatch();
			int comptabilise = match.getCountMatch();
			if (comptabilise == 1) {
				InitializeWindow.alertError(
						"Ce match est terminé. Il est impossible de générer un rapport sur un match déjà terminé");
			} else { // Match en cours
				if (AuthentificationOverviewController.getSUBSCRIPTION_TYPE().equals("EQUIPES")) {
					if (AuthentificationOverviewController.getNB_REPORTS_LEFT() > 0 && !reportAlreadyGenerated(match)) {
						InitializeWindow.showAlertGenerateReport(match,
								AuthentificationOverviewController.getNB_REPORTS_LEFT());
					} else {// EQUIPES && 0 crédit
						if (!reportAlreadyGenerated(match)) {
							InitializeWindow.alertError(
									"Votre abonnement est malheureusement arrivé à expiration... Merci de contacter l'administrateur à l'adresse \"support@statistant.fr\" pour modifier votre abonnement.");
						} else {
							generateReport(match);
						}
					}
				} else { // JOURNEE
					generateReport(match);
				}
			}
		} catch (NullResourceSelectedException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
//		InitializeWindow.showMatchOverview();
	}

	public static void generateReport(Match match) {
		String path = AuthentificationOverviewController.getREPORT_PATH();
		if (Ligue1Utils.isEmpty(path)) {
			path = "C:\\";
		}
		try {
			if (AuthentificationOverviewController.getSUBSCRIPTION_TYPE().equals("EQUIPES")) {
				generateReportMatch(match, path);
				if (!reportAlreadyGenerated(match)) {
					manageNbReportsLeft();
					User user = AuthentificationOverviewController.getUSER_CONNECTED();
					if (Ligue1Utils.isEmpty(user.getReportsAlreadyGenerated())) {
						user.setReportsAlreadyGenerated(match.getId());
					} else {
						user.setReportsAlreadyGenerated(user.getReportsAlreadyGenerated() + ";" + match.getId());
					}
					DatabaseConnection.createOrUpdateUser(user);
				}
				if (AuthentificationOverviewController.getNB_REPORTS_LEFT() > 0) {
					InitializeWindow
							.alertInfo("Il vous reste encore " + AuthentificationOverviewController.getNB_REPORTS_LEFT()
									+ " rapport(s) de matchs à générer avant expiration de l'abonnement.");
				} else {
					InitializeWindow.alertError(
							"C'était votre dernier crédit de rapport ! Votre abonnement est malheureusement arrivé à expiration... Merci de contacter l'administrateur à l'adresse \"support@statistant.fr\" pour modifier votre abonnement.");
				}
			} else {
				generateReportMatch(match, path);
			}
		} catch (NullUserException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
	}

	private static void manageNbReportsLeft() throws NullUserException {
		User user = AuthentificationOverviewController.getUSER_CONNECTED();
		int newCredit = user.getNbReportsLeft() - 1;
		user.setNbReportsLeft(newCredit);
		DatabaseConnection.createOrUpdateUser(user);
		AuthentificationOverviewController
				.setNB_REPORTS_LEFT(AuthentificationOverviewController.getNB_REPORTS_LEFT() - 1);
	}

	private static boolean reportAlreadyGenerated(Match match) {
		User user = AuthentificationOverviewController.getUSER_CONNECTED();
		String reports = user.getReportsAlreadyGenerated();
		if (!Ligue1Utils.isEmpty(reports)) {
			String[] split = reports.split(";");
			for (String report : split) {
				if (report.equals(match.getId())) {
					return true;
				}
			}
		}
		return false;
	}

}

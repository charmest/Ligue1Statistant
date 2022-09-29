package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.controller.NullConfrontationException;
import com.statistant.ligue1.controller.ScoreFormatException;
import com.statistant.ligue1.controller.StatisticController;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ModifyConfrontationOverviewController {

	private final StringProperty rencontre = new SimpleStringProperty();

	private final StringProperty rencontre1 = new SimpleStringProperty();
	@FXML
	private TextField fxRecent1;

	private final StringProperty rencontre2 = new SimpleStringProperty();
	@FXML
	private TextField fxRecent2;

	private final StringProperty rencontre3 = new SimpleStringProperty();
	@FXML
	private TextField fxRecent3;

	private final StringProperty rencontre4 = new SimpleStringProperty();
	@FXML
	private TextField fxRecent4;

	private final StringProperty rencontre5 = new SimpleStringProperty();
	@FXML
	private TextField fxRecent5;
	@FXML
	private MenuButton homeTeamPossibilities;
	@FXML
	private MenuButton awayTeamPossibilities;

	public String getHomeTeamNickName(Confrontation confrontation) {
		return confrontation.getHomeTeamNickName();
	}

	public String getAwayTeamNickName(Confrontation confrontation) {
		return confrontation.getAwayTeamNickname();
	}
	
	public String getHomeTeamFullName(Confrontation confrontation) {
		String homeTeamFullName = "";
		try {
			homeTeamFullName = DatabaseConnection.getTeamByNickname(getHomeTeamNickName(confrontation)).getName();
		} catch (NullTeamException e) {
			Ligue1Utils.reportError(e.getMessage());
		}
		return homeTeamFullName;
	}
	
	public String getAwayTeamFullName(Confrontation confrontation) {
		String awayTeamFullName = "";
		try {
			awayTeamFullName = DatabaseConnection.getTeamByNickname(getAwayTeamNickName(confrontation)).getName();
		} catch (NullTeamException e) {
			Ligue1Utils.reportError(e.getMessage());
		}
		return awayTeamFullName;
	}

	public MenuButton getHomeTeamPossibilities() {
		return homeTeamPossibilities;
	}

	public void setHomeTeamPossibilities(MenuButton homeTeamPossibilities) {
		this.homeTeamPossibilities = homeTeamPossibilities;
	}

	public MenuButton getAwayTeamPossibilities() {
		return awayTeamPossibilities;
	}

	public void setAwayTeamPossibilities(MenuButton awayTeamPossibilities) {
		this.awayTeamPossibilities = awayTeamPossibilities;
	}

	public void setConfrontation(Confrontation confrontation) {
		homeTeamPossibilities.setText(getHomeTeamFullName(confrontation));
		awayTeamPossibilities.setText(getAwayTeamFullName(confrontation));
		rencontre.set(getFormMatch());
		setFxRecent1(confrontation.getRecent1());
		fxRecent1.setText(getFxRecent1());
		setFxRecent2(confrontation.getRecent2());
		fxRecent2.setText(getFxRecent2());
		setFxRecent3(confrontation.getRecent3());
		fxRecent3.setText(getFxRecent3());
		setFxRecent4(confrontation.getRecent4());
		fxRecent4.setText(getFxRecent4());
		setFxRecent5(confrontation.getRecent5());
		fxRecent5.setText(getFxRecent5());
	}

	public String getFormMatch() {
		String homeTeamNickName = getTeamNickName(getHomeTeamPossibilities().getText());
		String awayTeamNickName = getTeamNickName(getAwayTeamPossibilities().getText());
		return homeTeamNickName + "-" + awayTeamNickName;
	}

	private String getTeamNickName(String fullName) {
		String teamNickName = "";
		try {
			teamNickName = DatabaseConnection.getTeamByFullName(fullName).getNickName();
		}
		catch (NullTeamException e) {
			Ligue1Utils.reportError(e.getMessage());
		}
		return teamNickName;
	}

	public String getFormRecent1() {
		return this.fxRecent1.getText();
	}

	public String getFxRecent1() {
		return this.rencontre1.get();
	}

	public void setFxRecent1(String recent) {
		this.rencontre1.set(recent);
		fxRecent1.setText(recent);
	}

	public String getFormRecent2() {
		return this.fxRecent2.getText();
	}

	public String getFxRecent2() {
		return this.rencontre2.get();
	}

	public void setFxRecent2(String recent) {
		this.rencontre2.set(recent);
		fxRecent2.setText(recent);
	}

	public String getFormRecent3() {
		return this.fxRecent3.getText();
	}

	public String getFxRecent3() {
		return this.rencontre3.get();
	}

	public void setFxRecent3(String recent) {
		this.rencontre3.set(recent);
		fxRecent3.setText(recent);
	}

	public String getFormRecent4() {
		return this.fxRecent4.getText();
	}

	public String getFxRecent4() {
		return this.rencontre4.get();
	}

	public void setFxRecent4(String recent) {
		this.rencontre4.set(recent);
		fxRecent4.setText(recent);
	}

	public String getFormRecent5() {
		return this.fxRecent5.getText();
	}

	public String getFxRecent5() {
		return this.rencontre5.get();
	}

	public void setFxRecent5(String recent) {
		this.rencontre5.set(recent);
		fxRecent5.setText(recent);
	}

	@FXML
	private void handleValidate() {
		String match = getFormMatch();
		String recent1 = getFormRecent1();
		String recent2 = getFormRecent2();
		String recent3 = getFormRecent3();
		String recent4 = getFormRecent4();
		String recent5 = getFormRecent5();
		try {
			checkAllFieldsAreOK();
		} catch (ScoreFormatException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
		Confrontation confrontation = new Confrontation(match, recent1, recent2, recent3, recent4, recent5);
		DatabaseConnection.createOrUpdateConfrontation(confrontation);
		try {
			StatisticController.setStatistiques(confrontation);
		} catch (NullConfrontationException e) {
			Ligue1Utils.reportError("Erreur à la mise à jour des statistiques du match : "+confrontation.getMatch());
			e.printStackTrace();
		}
		Confrontation reversedConfrontation = Ligue1Utils.getReversedConfrontation(confrontation);
		DatabaseConnection.createOrUpdateConfrontation(reversedConfrontation);
		try {
			StatisticController.setStatistiques(reversedConfrontation);
		} catch (NullConfrontationException e) {
			Ligue1Utils.reportError("Erreur à la mise à jour des statistiques du match : "+reversedConfrontation.getMatch());
			e.printStackTrace();
		}
		InitializeWindow.alertInfo("Confrontations " + confrontation.getMatch() + " et "+ reversedConfrontation.getMatch() +" créées avec succès.");
//		InitializeWindow.showConfrontationOverview();
	}

	private void checkAllFieldsAreOK() throws ScoreFormatException {
		if (!Ligue1Utils.isEmpty(fxRecent1.getText()) && !Ligue1Utils.isScoreWellFormed(fxRecent1.getText())) {
			throw new ScoreFormatException(
					"Le format du score du match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
		if (!Ligue1Utils.isEmpty(fxRecent2.getText()) && !Ligue1Utils.isScoreWellFormed(fxRecent2.getText())) {
			throw new ScoreFormatException(
					"Le format du score du deuxième match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
		if (!Ligue1Utils.isEmpty(fxRecent3.getText()) && !Ligue1Utils.isScoreWellFormed(fxRecent3.getText())) {
			throw new ScoreFormatException(
					"Le format du score du troisième match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
		if (!Ligue1Utils.isEmpty(fxRecent4.getText()) && !Ligue1Utils.isScoreWellFormed(fxRecent4.getText())) {
			throw new ScoreFormatException(
					"Le format du score du quatrième match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
		if (!Ligue1Utils.isEmpty(fxRecent5.getText()) && !Ligue1Utils.isScoreWellFormed(fxRecent5.getText())) {
			throw new ScoreFormatException(
					"Le format du score du cinquième match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
	}
}

package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.controller.MatchController;
import com.statistant.ligue1.controller.NullMatchParametersException;
import com.statistant.ligue1.controller.NullResultatException;
import com.statistant.ligue1.controller.OutOfBounceJourneyNumberException;
import com.statistant.ligue1.controller.SameTeamsException;
import com.statistant.ligue1.controller.ScoreFormatException;
import com.statistant.ligue1.controller.UnhandledResultatException;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.pojo.Match;
import com.statistant.ligue1.pojo.Team;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifyMatchOverviewController {
	
	@FXML
	private MenuButton journey;
	@FXML
	private MenuItem un;
	@FXML
	private MenuItem deux;
	@FXML
	private MenuItem trois;
	@FXML
	private MenuItem quatre;
	@FXML
	private MenuItem cinq;
	@FXML
	private MenuItem six;
	@FXML
	private MenuItem sept;
	@FXML
	private MenuItem huit;
	@FXML
	private MenuItem neuf;
	@FXML
	private MenuItem dix;
	@FXML
	private MenuItem onze;
	@FXML
	private MenuItem douze;
	@FXML
	private MenuItem treize;
	@FXML
	private MenuItem quatorze;
	@FXML
	private MenuItem quinze;
	@FXML
	private MenuItem seize;
	@FXML
	private MenuItem dixSept;
	@FXML
	private MenuItem dixHuit;
	@FXML
	private MenuItem dixNeuf;
	@FXML
	private MenuItem vingt;
	@FXML
	private MenuItem vingtEtUn;
	@FXML
	private MenuItem vingtDeux;
	@FXML
	private MenuItem vingtTrois;
	@FXML
	private MenuItem vingtQuatre;
	@FXML
	private MenuItem vingtCinq;
	@FXML
	private MenuItem vingtSix;
	@FXML
	private MenuItem vingtSept;
	@FXML
	private MenuItem vingtHuit;
	@FXML
	private MenuItem vingtNeuf;
	@FXML
	private MenuItem trente;
	@FXML
	private MenuItem trenteEtUn;
	@FXML
	private MenuItem trenteDeux;
	@FXML
	private MenuItem trenteTrois;
	@FXML
	private MenuItem trenteQuatre;
	@FXML
	private MenuItem trenteCinq;
	@FXML
	private MenuItem trenteSix;
	@FXML
	private MenuItem trenteSept;
	@FXML
	private MenuItem trenteHuit;

	private final IntegerProperty journee = new SimpleIntegerProperty();
	@FXML
	private MenuButton homeTeamPossibilities;

	private final StringProperty equipeDomicile = new SimpleStringProperty();

	private final StringProperty resultat = new SimpleStringProperty();
	@FXML
	private TextField score;
	
	private final StringProperty commentaire = new SimpleStringProperty();
	@FXML
	private TextArea comment;
	
	@FXML private Button validate;
	
	@FXML
	private MenuButton awayTeamPossibilities;

	private final StringProperty equipeExterieur = new SimpleStringProperty();

	private final IntegerProperty victoireE1 = new SimpleIntegerProperty();
	@FXML
	private TextField homeTeamWin;

	private final IntegerProperty nul = new SimpleIntegerProperty();
	@FXML
	private TextField draw;

	private final IntegerProperty victoireE2 = new SimpleIntegerProperty();
	@FXML
	private TextField awayTeamWin;

	private final IntegerProperty e1MieuxClassee = new SimpleIntegerProperty();
	@FXML
	private TextField homeTeamHasABetterStanding;

	private final IntegerProperty e1Important = new SimpleIntegerProperty();
	@FXML
	private TextField isAnImportantGameForHomeTeam;

	private final IntegerProperty e2Important = new SimpleIntegerProperty();
	@FXML
	private TextField isAnImportantGameForAwayTeam;

	public Button getValidate() {
		return validate;
	}

	public void setValidate(Button validate) {
		this.validate = validate;
	}

	public void setMatch(Match match) {
		setJourney(match.getJourney());
		journey.setText(String.valueOf(getJourneyNumber()));
		String homeTeamNickname = match.getHomeTeamNickname();
		Team homeTeam;
		try {
			homeTeam = DatabaseConnection.getTeamByNickname(homeTeamNickname);
		} catch (NullTeamException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
		setHomeTeamPossibilities(homeTeam.getName());
		homeTeamPossibilities.setText(homeTeam.getName());
		
		String awayTeamNickname = match.getAwayTeamNickname();
		Team awayTeam;
		try {
			awayTeam = DatabaseConnection.getTeamByNickname(awayTeamNickname);
		} catch (NullTeamException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
		setAwayTeamPossibilities(awayTeam.getName());
		awayTeamPossibilities.setText(awayTeam.getName());
		
		setScore(match.getScore());
		score.setText(String.valueOf(getScore()));
		setHomeTeamWin(Ligue1Utils.convert01ToString(match.getHomeTeamWin()));
		homeTeamWin.setText(String.valueOf(getHomeTeamWin()));
		setDraw(Ligue1Utils.convert01ToString(match.getDraw()));
		draw.setText(String.valueOf(getDraw()));
		setAwayTeamWin(Ligue1Utils.convert01ToString(match.getAwayTeamWin()));
		awayTeamWin.setText(String.valueOf(getAwayTeamWin()));
		setHomeTeamHasABetterStanding(Ligue1Utils.convert01ToString(match.getHomeTeamHasABetterStanding()));
		homeTeamHasABetterStanding.setText(String.valueOf(getHomeTeamHasABetterStanding()));
		setIsAnImportantGameForHomeTeam(Ligue1Utils.convert01ToString(match.getIsAnImportantGameForHomeTeam()));
		isAnImportantGameForHomeTeam.setText(String.valueOf(getIsAnImportantGameForHomeTeam()));
		setIsAnImportantGameForAwayTeam(Ligue1Utils.convert01ToString(match.getIsAnImportantGameForAwayTeam()));
		isAnImportantGameForAwayTeam.setText(String.valueOf(getIsAnImportantGameForAwayTeam()));
		setComment(match.getComment());
		comment.setText(getCommentaire());
	}

	public MenuItem getUn() {
		return un;
	}

	public void setUn(MenuItem un) {
		this.un = un;
	}

	public MenuItem getDeux() {
		return deux;
	}

	public void setDeux(MenuItem deux) {
		this.deux = deux;
	}

	public MenuItem getTrois() {
		return trois;
	}

	public void setTrois(MenuItem trois) {
		this.trois = trois;
	}

	public MenuItem getQuatre() {
		return quatre;
	}

	public void setQuatre(MenuItem quatre) {
		this.quatre = quatre;
	}

	public MenuItem getCinq() {
		return cinq;
	}

	public void setCinq(MenuItem cinq) {
		this.cinq = cinq;
	}

	public MenuItem getSix() {
		return six;
	}

	public void setSix(MenuItem six) {
		this.six = six;
	}

	public MenuItem getSept() {
		return sept;
	}

	public void setSept(MenuItem sept) {
		this.sept = sept;
	}

	public MenuItem getHuit() {
		return huit;
	}

	public void setHuit(MenuItem huit) {
		this.huit = huit;
	}

	public MenuItem getNeuf() {
		return neuf;
	}

	public void setNeuf(MenuItem neuf) {
		this.neuf = neuf;
	}

	public MenuItem getDix() {
		return dix;
	}

	public void setDix(MenuItem dix) {
		this.dix = dix;
	}

	public MenuItem getOnze() {
		return onze;
	}

	public void setOnze(MenuItem onze) {
		this.onze = onze;
	}

	public MenuItem getDouze() {
		return douze;
	}

	public void setDouze(MenuItem douze) {
		this.douze = douze;
	}

	public MenuItem getTreize() {
		return treize;
	}

	public void setTreize(MenuItem treize) {
		this.treize = treize;
	}

	public MenuItem getQuatorze() {
		return quatorze;
	}

	public void setQuatorze(MenuItem quatorze) {
		this.quatorze = quatorze;
	}

	public MenuItem getQuinze() {
		return quinze;
	}

	public void setQuinze(MenuItem quinze) {
		this.quinze = quinze;
	}

	public MenuItem getSeize() {
		return seize;
	}

	public void setSeize(MenuItem seize) {
		this.seize = seize;
	}

	public MenuItem getDixSept() {
		return dixSept;
	}

	public void setDixSept(MenuItem dixSept) {
		this.dixSept = dixSept;
	}

	public MenuItem getDixHuit() {
		return dixHuit;
	}

	public void setDixHuit(MenuItem dixHuit) {
		this.dixHuit = dixHuit;
	}

	public MenuItem getDixNeuf() {
		return dixNeuf;
	}

	public void setDixNeuf(MenuItem dixNeuf) {
		this.dixNeuf = dixNeuf;
	}

	public MenuItem getVingt() {
		return vingt;
	}

	public void setVingt(MenuItem vingt) {
		this.vingt = vingt;
	}

	public MenuItem getVingtEtUn() {
		return vingtEtUn;
	}

	public void setVingtEtUn(MenuItem vingtEtUn) {
		this.vingtEtUn = vingtEtUn;
	}

	public MenuItem getVingtDeux() {
		return vingtDeux;
	}

	public void setVingtDeux(MenuItem vingtDeux) {
		this.vingtDeux = vingtDeux;
	}

	public MenuItem getVingtTrois() {
		return vingtTrois;
	}

	public void setVingtTrois(MenuItem vingtTrois) {
		this.vingtTrois = vingtTrois;
	}

	public MenuItem getVingtQuatre() {
		return vingtQuatre;
	}

	public void setVingtQuatre(MenuItem vingtQuatre) {
		this.vingtQuatre = vingtQuatre;
	}

	public MenuItem getVingtCinq() {
		return vingtCinq;
	}

	public void setVingtCinq(MenuItem vingtCinq) {
		this.vingtCinq = vingtCinq;
	}

	public MenuItem getVingtSix() {
		return vingtSix;
	}

	public void setVingtSix(MenuItem vingtSix) {
		this.vingtSix = vingtSix;
	}

	public MenuItem getVingtSept() {
		return vingtSept;
	}

	public void setVingtSept(MenuItem vingtSept) {
		this.vingtSept = vingtSept;
	}

	public MenuItem getVingtHuit() {
		return vingtHuit;
	}

	public void setVingtHuit(MenuItem vingtHuit) {
		this.vingtHuit = vingtHuit;
	}

	public MenuItem getVingtNeuf() {
		return vingtNeuf;
	}

	public void setVingtNeuf(MenuItem vingtNeuf) {
		this.vingtNeuf = vingtNeuf;
	}

	public MenuItem getTrente() {
		return trente;
	}

	public void setTrente(MenuItem trente) {
		this.trente = trente;
	}

	public MenuItem getTrenteEtUn() {
		return trenteEtUn;
	}

	public void setTrenteEtUn(MenuItem trenteEtUn) {
		this.trenteEtUn = trenteEtUn;
	}

	public MenuItem getTrenteDeux() {
		return trenteDeux;
	}

	public void setTrenteDeux(MenuItem trenteDeux) {
		this.trenteDeux = trenteDeux;
	}

	public MenuItem getTrenteTrois() {
		return trenteTrois;
	}

	public void setTrenteTrois(MenuItem trenteTrois) {
		this.trenteTrois = trenteTrois;
	}

	public MenuItem getTrenteQuatre() {
		return trenteQuatre;
	}

	public void setTrenteQuatre(MenuItem trenteQuatre) {
		this.trenteQuatre = trenteQuatre;
	}

	public MenuItem getTrenteCinq() {
		return trenteCinq;
	}

	public void setTrenteCinq(MenuItem trenteCinq) {
		this.trenteCinq = trenteCinq;
	}

	public MenuItem getTrenteSix() {
		return trenteSix;
	}

	public void setTrenteSix(MenuItem trenteSix) {
		this.trenteSix = trenteSix;
	}

	public MenuItem getTrenteSept() {
		return trenteSept;
	}

	public void setTrenteSept(MenuItem trenteSept) {
		this.trenteSept = trenteSept;
	}

	public MenuItem getTrenteHuit() {
		return trenteHuit;
	}

	public void setTrenteHuit(MenuItem trenteHuit) {
		this.trenteHuit = trenteHuit;
	}
	
	@FXML
	private void handleJourneyUn() {
		String selectedJourney = getUn().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyDeux() {
		String selectedJourney = getDeux().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrois() {
		String selectedJourney = getTrois().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyQuatre() {
		String selectedJourney = getQuatre().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyCinq() {
		String selectedJourney = getCinq().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneySix() {
		String selectedJourney = getSix().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneySept() {
		String selectedJourney = getSept().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyHuit() {
		String selectedJourney = getHuit().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyNeuf() {
		String selectedJourney = getNeuf().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyDix() {
		String selectedJourney = getDix().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyOnze() {
		String selectedJourney = getOnze().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyDouze() {
		String selectedJourney = getDouze().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTreize() {
		String selectedJourney = getTreize().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyQuatorze() {
		String selectedJourney = getQuatorze().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyQuinze() {
		String selectedJourney = getQuinze().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneySeize() {
		String selectedJourney = getSeize().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyDixSept() {
		String selectedJourney = getDixSept().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyDixHuit() {
		String selectedJourney = getDixHuit().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyDixNeuf() {
		String selectedJourney = getDixNeuf().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingt() {
		String selectedJourney = getVingt().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtEtUn() {
		String selectedJourney = getVingtEtUn().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtDeux() {
		String selectedJourney = getVingtDeux().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtTrois() {
		String selectedJourney = getVingtTrois().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtQuatre() {
		String selectedJourney = getVingtQuatre().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtCinq() {
		String selectedJourney = getVingtCinq().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtSix() {
		String selectedJourney = getVingtSix().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtSept() {
		String selectedJourney = getVingtSept().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtHuit() {
		String selectedJourney = getVingtHuit().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyVingtNeuf() {
		String selectedJourney = getVingtNeuf().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrente() {
		String selectedJourney = getTrente().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrenteEtUn() {
		String selectedJourney = getTrenteEtUn().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrenteDeux() {
		String selectedJourney = getTrenteDeux().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrenteTrois() {
		String selectedJourney = getTrenteTrois().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrenteQuatre() {
		String selectedJourney = getTrenteQuatre().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrenteCinq() {
		String selectedJourney = getTrenteCinq().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrenteSix() {
		String selectedJourney = getTrenteSix().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrenteSept() {
		String selectedJourney = getTrenteSept().getText();
		this.journey.setText(selectedJourney);
	}
	
	@FXML
	private void handleJourneyTrenteHuit() {
		String selectedJourney = getTrenteHuit().getText();
		this.journey.setText(selectedJourney);
	}

	public MenuButton getJourney() {
		return journey;
	}

	public void setJourney(MenuButton journey) {
		this.journey = journey;
	}

	public Integer getJourneyNumber() {
		return this.journee.get();
	}

	public void setJourney(Integer journee) {
		this.journee.set(journee);
		journey.setText(String.valueOf(journee));
	}

	public String getHomeTeam() {
		return this.equipeDomicile.get();
	}

	public void setHomeTeamPossibilities(String string) {
		this.equipeDomicile.set(string);
		homeTeamPossibilities.setText(String.valueOf(string));
	}

	public String getFormScore() {
		return this.score.getText();
	}

	public String getScore() {
		return this.resultat.get();
	}

	public void setScore(String string) {
		this.resultat.set(string);
		score.setText(String.valueOf(string));
	}
	
	public String getFormComment() {
		return this.comment.getText();
	}

	public String getCommentaire() {
		return this.commentaire.get();
	}

	public void setComment(String string) {
		this.commentaire.set(string);
		comment.setText(String.valueOf(string));
	}

	public String getFormHomeTeamPossibilities() {
		return this.homeTeamPossibilities.getText();
	}
	
	public String getFormAwayTeamPossibilities() {
		return this.awayTeamPossibilities.getText();
	}

	public String getAwayTeam() {
		return this.equipeExterieur.get();
	}

	public void setAwayTeamPossibilities(String string) {
		this.equipeExterieur.set(string);
		awayTeamPossibilities.setText(String.valueOf(string));
	}

	public Integer getFormHomeTeamWin() {
		Integer returnInt = 0;
		try {
			returnInt = Ligue1Utils.convertStringTo01(this.homeTeamWin.getText());
		} catch (NumberFormatException e) {
		}
		return returnInt;
	}

	public String getHomeTeamWin() {
		return Ligue1Utils.convert01ToString(this.victoireE1.get());
	}

	public void setHomeTeamWin(String string) {
		this.victoireE1.set(Ligue1Utils.convertStringTo01(string));
		homeTeamWin.setText(string);
	}

	public Integer getFormDraw() {
		Integer returnInt = 0;
		try {
			returnInt = Ligue1Utils.convertStringTo01(this.draw.getText());
		} catch (NumberFormatException e) {
		}
		return returnInt;
	}

	public String getDraw() {
		return Ligue1Utils.convert01ToString(this.nul.get());
	}

	public void setDraw(String text) {
		this.nul.set(Ligue1Utils.convertStringTo01(text));
		draw.setText(text);
	}

	public Integer getFormAwayTeamWin() {
		Integer returnInt = 0;
		try {
			returnInt = Ligue1Utils.convertStringTo01(this.awayTeamWin.getText());
		} catch (NumberFormatException e) {
		}
		return returnInt;
	}

	public String getAwayTeamWin() {
		return Ligue1Utils.convert01ToString(this.victoireE2.get());
	}

	public void setAwayTeamWin(String text) {
		this.victoireE2.set(Ligue1Utils.convertStringTo01(text));
		awayTeamWin.setText(text);
	}

	public Integer getFormHomeTeamHasABetterStanding() {
		Integer returnInt = 0;
		try {
			returnInt = Ligue1Utils.convertStringTo01(this.homeTeamHasABetterStanding.getText());
		} catch (NumberFormatException e) {
		}
		return returnInt;
	}

	public String getHomeTeamHasABetterStanding() {
		return Ligue1Utils.convert01ToString(this.e1MieuxClassee.get());
	}

	public void setHomeTeamHasABetterStanding(String text) {
		this.e1MieuxClassee.set(Ligue1Utils.convertStringTo01(text));
		homeTeamHasABetterStanding.setText(text);
	}

	public Integer getFormIsAnImportantGameForHomeTeam() {
		Integer returnInt = 0;
		try {
			returnInt = Ligue1Utils.convertStringTo01(this.isAnImportantGameForHomeTeam.getText());
		} catch (NumberFormatException e) {
		}
		return returnInt;
	}

	public String getIsAnImportantGameForHomeTeam() {
		return Ligue1Utils.convert01ToString(this.e1Important.get());
	}

	public void setIsAnImportantGameForHomeTeam(String text) {
		this.e1Important.set(Ligue1Utils.convertStringTo01(text));
		isAnImportantGameForHomeTeam.setText(text);
	}

	public Integer getFormIsAnImportantGameForAwayTeam() {
		Integer returnInt = 0;
		try {
			returnInt = Ligue1Utils.convertStringTo01(this.isAnImportantGameForAwayTeam.getText());
		} catch (NumberFormatException e) {
		}
		return returnInt;
	}

	public String getIsAnImportantGameForAwayTeam() {
		return Ligue1Utils.convert01ToString(this.e2Important.get());
	}

	public void setIsAnImportantGameForAwayTeam(String text) {
		this.e2Important.set(Ligue1Utils.convertStringTo01(text));
		isAnImportantGameForAwayTeam.setText(text);
	}

	@FXML
	private void handleValidate() {
		String resultat = getFormScore();
		if (!Ligue1Utils.isEmpty(resultat)) {
			try {
				MatchController.checkScore(resultat);
				updateResultOnFormWithScore(resultat);
			} catch (ScoreFormatException e) {
				homeTeamWin.setText("");
				draw.setText("");
				awayTeamWin.setText("");
				Ligue1Utils.reportError(e.getMessage());
				return;
			}
		}
		else {
			homeTeamWin.setText("");
			draw.setText("");
			awayTeamWin.setText("");
		}
		boolean matchTermine = matchTermine();
		if (matchTermine) {
			boolean allFieldsOkAfterMatch = checkAllFormFieldsAfterMatch();
			if (allFieldsOkAfterMatch) {
				String team1 = "";
				String team2 = "";
				try {
					team1 = DatabaseConnection.getTeamByFullName(homeTeamPossibilities.getText()).getNickName();
					team2 = DatabaseConnection.getTeamByFullName(awayTeamPossibilities.getText()).getNickName();
				} catch (NullTeamException e) {
					Ligue1Utils.reportError(e.getMessage());
					return;
				}
				Integer victoireEquipe1 = getFormHomeTeamWin();
				Integer victoireEquipe2 = getFormAwayTeamWin();
				Integer nul = getFormDraw();
				Integer isImportantGameForHomeTeam = getFormIsAnImportantGameForHomeTeam();
				Integer isImportantGameForAwayTeam = getFormIsAnImportantGameForAwayTeam();
				Integer homeTeamHasBetterStanding = getFormHomeTeamHasABetterStanding();
				Integer journee = getJourneyNumber();
				String comment = getCommentaire();
				Match match = new Match(team1, team2, resultat, victoireEquipe1, nul, victoireEquipe2,
						isImportantGameForHomeTeam, isImportantGameForAwayTeam, homeTeamHasBetterStanding, journee, comment);
				DatabaseConnection.createOrUpdateMatch(match);
				Ligue1Utils.reportInfo("Match " + match.getId() + " modifié avec succès.");
				InitializeWindow.alertInfo("Match " + match.getId() + " modifié avec succès.");
				Stage stage = (Stage) getValidate().getScene().getWindow();
				stage.close();
//				InitializeWindow.showMatchOverview();
			}
		} else {
			boolean allFieldsOkBeforeMatch = checkAllFormFieldsBeforeMatch();
			if (allFieldsOkBeforeMatch) {
				String team1 = "";
				String team2 = "";
				try {
					team1 = DatabaseConnection.getTeamByFullName(homeTeamPossibilities.getText()).getNickName();
					team2 = DatabaseConnection.getTeamByFullName(awayTeamPossibilities.getText()).getNickName();
				} catch (NullTeamException e) {
					Ligue1Utils.reportError(e.getMessage());
					return;
				}
				Integer isImportantGameForHomeTeam = getFormIsAnImportantGameForHomeTeam();
				Integer isImportantGameForAwayTeam = getFormIsAnImportantGameForAwayTeam();
				Integer homeTeamHasBetterStanding = getFormHomeTeamHasABetterStanding();
				Integer journee = Integer.parseInt(getJourney().getText());
				String comment = getFormComment();
				Match match = new Match(team1, team2, isImportantGameForHomeTeam, isImportantGameForAwayTeam,
						homeTeamHasBetterStanding, journee, comment);
				DatabaseConnection.createOrUpdateMatch(match);
				Ligue1Utils.reportInfo("Match " + match.getId() + " modifié avec succès.");
				InitializeWindow.alertInfo("Match " + match.getId() + " modifié avec succès.");
				Stage stage = (Stage) getValidate().getScene().getWindow();
				stage.close();
//				InitializeWindow.showMatchOverview();
			}
		}
	}

	private boolean checkAllFormFieldsBeforeMatch() {
		String team1 = "";
		String team2 = "";
		try {
			team1 = DatabaseConnection.getTeamByFullName(homeTeamPossibilities.getText()).getNickName();
			team2 = DatabaseConnection.getTeamByFullName(awayTeamPossibilities.getText()).getNickName();
			MatchController.checkTeamsAreOK(team1, team2);
		} catch (NullTeamException | SameTeamsException e) {
			Ligue1Utils.reportError(e.getMessage());
			return false;
		}
		String journee = journey.getText();
		try {
			MatchController.checkJourneyNumber(journee);
		} catch (OutOfBounceJourneyNumberException | NumberFormatException e) {
			Ligue1Utils.reportError(e.getMessage());
			return false;
		}
		String isImportantGameForHomeTeam = isAnImportantGameForHomeTeam.getText();
		String isImportantGameForAwayTeam = isAnImportantGameForAwayTeam.getText();
		String homeTeamHasBetterStanding = homeTeamHasABetterStanding.getText();
		try {
			MatchController.checkMatchParameters(isImportantGameForHomeTeam, isImportantGameForAwayTeam,
					homeTeamHasBetterStanding);
		} catch (NullMatchParametersException e) {
			Ligue1Utils.reportError(e.getMessage());
			return false;
		}
		return true;
	}

	private boolean matchTermine() {
		try {
			MatchController.checkOnlyOneResult(getFormHomeTeamWin(), getFormAwayTeamWin(), getFormDraw());
		} catch (NullResultatException | UnhandledResultatException e) {
			return false;
		}
		return Ligue1Utils.isScoreWellFormed(getFormScore());
	}

	private boolean checkAllFormFieldsAfterMatch() {
		String team1 = "";
		String team2 = "";
		try {
			team1 = DatabaseConnection.getTeamByFullName(homeTeamPossibilities.getText()).getNickName();
			team2 = DatabaseConnection.getTeamByFullName(awayTeamPossibilities.getText()).getNickName();
			MatchController.checkTeamsAreOK(team1, team2);
		} catch (NullTeamException | SameTeamsException e) {
			Ligue1Utils.reportError(e.getMessage());
			return false;
		}
		String resultat = score.getText();
		try {
			MatchController.checkScore(resultat);
		} catch (ScoreFormatException e) {
			Ligue1Utils.reportError(e.getMessage());
			return false;
		}
		String journee = journey.getText();
		try {
			MatchController.checkJourneyNumber(journee);
		} catch (OutOfBounceJourneyNumberException | NumberFormatException e) {
			Ligue1Utils.reportError(e.getMessage());
			return false;
		}
		Integer victoireEquipe1 = Ligue1Utils.convertStringTo01(homeTeamWin.getText());
		Integer victoireEquipe2 = Ligue1Utils.convertStringTo01(awayTeamWin.getText());
		Integer nul = Ligue1Utils.convertStringTo01(draw.getText());
		try {
			MatchController.checkOnlyOneResult(victoireEquipe1, victoireEquipe2, nul);
		} catch (NullResultatException | UnhandledResultatException e) {
			Ligue1Utils.reportError(e.getMessage());
			return false;
		}

		String isImportantGameForHomeTeam = isAnImportantGameForHomeTeam.getText();
		String isImportantGameForAwayTeam = isAnImportantGameForAwayTeam.getText();
		String homeTeamHasBetterStanding = homeTeamHasABetterStanding.getText();
		try {
			MatchController.checkMatchParameters(isImportantGameForHomeTeam, isImportantGameForAwayTeam,
					homeTeamHasBetterStanding);
		} catch (NullMatchParametersException e) {
			Ligue1Utils.reportError(e.getMessage());
			return false;
		}
		return true;
	}

	public void updateResultOnFormWithScore(String score) {
		String[] split = score.split("-");
		if (Integer.parseInt(split[0]) > Integer.parseInt(split[1])) {
			setHomeTeamWin("Oui");
			setDraw("Non");
			setAwayTeamWin("Non");
		}
		if (Integer.parseInt(split[0]) == Integer.parseInt(split[1])) {
			setHomeTeamWin("Non");
			setDraw("Oui");
			setAwayTeamWin("Non");
		}
		if (Integer.parseInt(split[0]) < Integer.parseInt(split[1])) {
			setHomeTeamWin("Non");
			setDraw("Non");
			setAwayTeamWin("Oui");
		}
	}

}

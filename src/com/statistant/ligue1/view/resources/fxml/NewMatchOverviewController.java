package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.controller.MatchController;
import com.statistant.ligue1.controller.NullMatchParametersException;
import com.statistant.ligue1.controller.NullResultatException;
import com.statistant.ligue1.controller.OutOfBounceJourneyNumberException;
import com.statistant.ligue1.controller.SameTeamsException;
import com.statistant.ligue1.controller.ScoreFormatException;
import com.statistant.ligue1.controller.UnhandledResultatException;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullMatchException;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.pojo.Match;
import com.statistant.ligue1.pojo.Team;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewMatchOverviewController {

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
	@FXML
	private MenuItem asm1;
	@FXML
	private MenuItem asse1;
	@FXML
	private MenuItem dfco1;
	@FXML
	private MenuItem fcgb1;
	@FXML
	private MenuItem fcl1;
	@FXML
	private MenuItem fcm1;
	@FXML
	private MenuItem fcn1;
	@FXML
	private MenuItem losc1;
	@FXML
	private MenuItem mhsc1;
	@FXML
	private MenuItem no1;
	@FXML
	private MenuItem ogcn1;
	@FXML
	private MenuItem om1;
	@FXML
	private MenuItem ol1;
	@FXML
	private MenuItem psg1;
	@FXML
	private MenuItem rcl1;
	@FXML
	private MenuItem rcs1;
	@FXML
	private MenuItem sco1;
	@FXML
	private MenuItem sb291;
	@FXML
	private MenuItem sdr1;
	@FXML
	private MenuItem srfc1;
	@FXML
	private MenuButton homeTeamPossibilities;
	@FXML
	private TextField score;
	@FXML
	private MenuItem asm2;
	@FXML
	private MenuItem asse2;
	@FXML
	private MenuItem dfco2;
	@FXML
	private MenuItem fcgb2;
	@FXML
	private MenuItem fcl2;
	@FXML
	private MenuItem fcm2;
	@FXML
	private MenuItem fcn2;
	@FXML
	private MenuItem losc2;
	@FXML
	private MenuItem mhsc2;
	@FXML
	private MenuItem no2;
	@FXML
	private MenuItem ogcn2;
	@FXML
	private MenuItem om2;
	@FXML
	private MenuItem ol2;
	@FXML
	private MenuItem psg2;
	@FXML
	private MenuItem rcl2;
	@FXML
	private MenuItem rcs2;
	@FXML
	private MenuItem sco2;
	@FXML
	private MenuItem sb292;
	@FXML
	private MenuItem sdr2;
	@FXML
	private MenuItem srfc2;
	@FXML
	private MenuButton awayTeamPossibilities;
	@FXML
	private TextField homeTeamWin;
	@FXML
	private TextField draw;
	@FXML
	private TextField awayTeamWin;
	@FXML
	private TextField homeTeamHasABetterStanding;
	@FXML
	private TextField isAnImportantGameForHomeTeam;
	@FXML
	private TextField isAnImportantGameForAwayTeam;
	@FXML
	private TextArea comment;

	public MenuItem getAsm1() {
		return asm1;
	}

	public void setAsm1(MenuItem asm1) {
		this.asm1 = asm1;
	}

	public MenuItem getAsse1() {
		return asse1;
	}

	public void setAsse1(MenuItem asse1) {
		this.asse1 = asse1;
	}

	public MenuItem getDfco1() {
		return dfco1;
	}

	public void setDfco1(MenuItem dfco1) {
		this.dfco1 = dfco1;
	}

	public MenuItem getFcgb1() {
		return fcgb1;
	}

	public void setFcgb1(MenuItem fcgb1) {
		this.fcgb1 = fcgb1;
	}

	public MenuItem getFcl1() {
		return fcl1;
	}

	public void setFcl1(MenuItem fcl1) {
		this.fcl1 = fcl1;
	}

	public MenuItem getFcm1() {
		return fcm1;
	}

	public void setFcm1(MenuItem fcm1) {
		this.fcm1 = fcm1;
	}

	public MenuItem getFcn1() {
		return fcn1;
	}

	public void setFcn1(MenuItem fcn1) {
		this.fcn1 = fcn1;
	}

	public MenuItem getLosc1() {
		return losc1;
	}

	public void setLosc1(MenuItem losc1) {
		this.losc1 = losc1;
	}

	public MenuItem getMhsc1() {
		return mhsc1;
	}

	public void setMhsc1(MenuItem mhsc1) {
		this.mhsc1 = mhsc1;
	}

	public MenuItem getNo1() {
		return no1;
	}

	public void setNo1(MenuItem no1) {
		this.no1 = no1;
	}

	public MenuItem getOgcn1() {
		return ogcn1;
	}

	public void setOgcn1(MenuItem ogcn1) {
		this.ogcn1 = ogcn1;
	}

	public MenuItem getOm1() {
		return om1;
	}

	public void setOm1(MenuItem om1) {
		this.om1 = om1;
	}

	public MenuItem getOl1() {
		return ol1;
	}

	public void setOl1(MenuItem ol1) {
		this.ol1 = ol1;
	}

	public MenuItem getPsg1() {
		return psg1;
	}

	public void setPsg1(MenuItem psg1) {
		this.psg1 = psg1;
	}

	public MenuItem getRcl1() {
		return rcl1;
	}

	public void setRcl1(MenuItem rcl1) {
		this.rcl1 = rcl1;
	}

	public MenuItem getRcs1() {
		return rcs1;
	}

	public void setRcs1(MenuItem rcs1) {
		this.rcs1 = rcs1;
	}

	public MenuItem getSco1() {
		return sco1;
	}

	public void setSco1(MenuItem sco1) {
		this.sco1 = sco1;
	}

	public MenuItem getSb291() {
		return sb291;
	}

	public void setSb291(MenuItem sb291) {
		this.sb291 = sb291;
	}

	public MenuItem getSdr1() {
		return sdr1;
	}

	public void setSdr1(MenuItem sdr1) {
		this.sdr1 = sdr1;
	}

	public MenuItem getSrfc1() {
		return srfc1;
	}

	public void setSrfc1(MenuItem srfc1) {
		this.srfc1 = srfc1;
	}

	public MenuItem getAsm2() {
		return asm2;
	}

	public void setAsm2(MenuItem asm2) {
		this.asm2 = asm2;
	}

	public MenuItem getAsse2() {
		return asse2;
	}

	public void setAsse2(MenuItem asse2) {
		this.asse2 = asse2;
	}

	public MenuItem getDfco2() {
		return dfco2;
	}

	public void setDfco2(MenuItem dfco2) {
		this.dfco2 = dfco2;
	}

	public MenuItem getFcgb2() {
		return fcgb2;
	}

	public void setFcgb2(MenuItem fcgb2) {
		this.fcgb2 = fcgb2;
	}

	public MenuItem getFcl2() {
		return fcl2;
	}

	public void setFcl2(MenuItem fcl2) {
		this.fcl2 = fcl2;
	}

	public MenuItem getFcm2() {
		return fcm2;
	}

	public void setFcm2(MenuItem fcm2) {
		this.fcm2 = fcm2;
	}

	public MenuItem getFcn2() {
		return fcn2;
	}

	public void setFcn2(MenuItem fcn2) {
		this.fcn2 = fcn2;
	}

	public MenuItem getLosc2() {
		return losc2;
	}

	public void setLosc2(MenuItem losc2) {
		this.losc2 = losc2;
	}

	public MenuItem getMhsc2() {
		return mhsc2;
	}

	public void setMhsc2(MenuItem mhsc2) {
		this.mhsc2 = mhsc2;
	}

	public MenuItem getNo2() {
		return no2;
	}

	public void setNo2(MenuItem no2) {
		this.no2 = no2;
	}

	public MenuItem getOgcn2() {
		return ogcn2;
	}

	public void setOgcn2(MenuItem ogcn2) {
		this.ogcn2 = ogcn2;
	}

	public MenuItem getOm2() {
		return om2;
	}

	public void setOm2(MenuItem om2) {
		this.om2 = om2;
	}

	public MenuItem getOl2() {
		return ol2;
	}

	public void setOl2(MenuItem ol2) {
		this.ol2 = ol2;
	}

	public MenuItem getPsg2() {
		return psg2;
	}

	public void setPsg2(MenuItem psg2) {
		this.psg2 = psg2;
	}

	public MenuItem getRcl2() {
		return rcl2;
	}

	public void setRcl2(MenuItem rcl2) {
		this.rcl2 = rcl2;
	}

	public MenuItem getRcs2() {
		return rcs2;
	}

	public void setRcs2(MenuItem rcs2) {
		this.rcs2 = rcs2;
	}

	public MenuItem getSco2() {
		return sco2;
	}

	public void setSco2(MenuItem sco2) {
		this.sco2 = sco2;
	}

	public MenuItem getSb292() {
		return sb292;
	}

	public void setSb292(MenuItem sb292) {
		this.sb292 = sb292;
	}

	public MenuItem getSdr2() {
		return sdr2;
	}

	public void setSdr2(MenuItem sdr2) {
		this.sdr2 = sdr2;
	}

	public MenuItem getSrfc2() {
		return srfc2;
	}

	public void setSrfc2(MenuItem srfc2) {
		this.srfc2 = srfc2;
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

	public MenuButton getJourney() {
		return journey;
	}

	public void setJourney(MenuButton journey) {
		this.journey = journey;
	}

	public TextField getScore() {
		return score;
	}

	public void setScore(TextField score) {
		this.score = score;
	}

	public String getHomeTeamWin() {
		return Ligue1Utils.convert01ToString(homeTeamWin.getText());
	}

	public void setHomeTeamWin(String string) {
		this.homeTeamWin.setText(string);
	}

	public String getDraw() {
		return Ligue1Utils.convert01ToString(draw.getText());
	}

	public void setDraw(String string) {
		this.draw.setText(string);
	}

	public String getAwayTeamWin() {
		return Ligue1Utils.convert01ToString(awayTeamWin.getText());
	}

	public void setAwayTeamWin(String string) {
		this.awayTeamWin.setText(string);
	}

	public Integer getHomeTeamHasABetterStanding() {
		return Ligue1Utils.convertStringTo01(homeTeamHasABetterStanding.getText());
	}

	public void setHomeTeamHasABetterStanding(String string) {
		this.homeTeamHasABetterStanding.setText(string);
	}

	public Integer getIsAnImportantGameForHomeTeam() {
		return Ligue1Utils.convertStringTo01(isAnImportantGameForHomeTeam.getText());
	}

	public void setIsAnImportantGameForHomeTeam(String string) {
		this.isAnImportantGameForHomeTeam.setText(string);
	}

	public Integer getIsAnImportantGameForAwayTeam() {
		return Ligue1Utils.convertStringTo01(isAnImportantGameForAwayTeam.getText());
	}

	public void setIsAnImportantGameForAwayTeam(String string) {
		this.isAnImportantGameForAwayTeam.setText(string);
	}

	public TextArea getComment() {
		return comment;
	}

	public void setComment(TextArea comment) {
		this.comment = comment;
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

	@FXML
	private void handleHomeTeamChoiceASM() {
		String choosenHomeTeam = getAsm1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceASSE() {
		String choosenHomeTeam = getAsse1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceDFCO() {
		String choosenHomeTeam = getDfco1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceFCGB() {
		String choosenHomeTeam = getFcgb1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceFCL() {
		String choosenHomeTeam = getFcl1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceFCM() {
		String choosenHomeTeam = getFcm1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceFCN() {
		String choosenHomeTeam = getFcn1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceLOSC() {
		String choosenHomeTeam = getLosc1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceMHSC() {
		String choosenHomeTeam = getMhsc1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceNO() {
		String choosenHomeTeam = getNo1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceOGCN() {
		String choosenHomeTeam = getOgcn1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceOM() {
		String choosenHomeTeam = getOm1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceOL() {
		String choosenHomeTeam = getOl1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoicePSG() {
		String choosenHomeTeam = getPsg1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceRCL() {
		String choosenHomeTeam = getRcl1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceRCS() {
		String choosenHomeTeam = getRcs1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceSCO() {
		String choosenHomeTeam = getSco1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceSB29() {
		String choosenHomeTeam = getSb291().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceSDR() {
		String choosenHomeTeam = getSdr1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}
	@FXML
	private void handleHomeTeamChoiceSRFC() {
		String choosenHomeTeam = getSrfc1().getText();
		this.homeTeamPossibilities.setText(choosenHomeTeam);
	}

	@FXML
	private void handleAwayTeamChoiceASM() {
		String choosenAwayTeam = getAsm2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceASSE() {
		String choosenAwayTeam = getAsse2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceDFCO() {
		String choosenAwayTeam = getDfco2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceFCGB() {
		String choosenAwayTeam = getFcgb2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceFCL() {
		String choosenAwayTeam = getFcl2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceFCM() {
		String choosenAwayTeam = getFcm2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceFCN() {
		String choosenAwayTeam = getFcn2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceLOSC() {
		String choosenAwayTeam = getLosc2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceMHSC() {
		String choosenAwayTeam = getMhsc2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceNO() {
		String choosenAwayTeam = getNo2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceOGCN() {
		String choosenAwayTeam = getOgcn2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceOM() {
		String choosenAwayTeam = getOm2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceOL() {
		String choosenAwayTeam = getOl2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoicePSG() {
		String choosenAwayTeam = getPsg2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceRCL() {
		String choosenAwayTeam = getRcl2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceRCS() {
		String choosenAwayTeam = getRcs2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceSCO() {
		String choosenAwayTeam = getSco2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceSB29() {
		String choosenAwayTeam = getSb292().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceSDR() {
		String choosenAwayTeam = getSdr2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	@FXML
	private void handleAwayTeamChoiceSRFC() {
		String choosenAwayTeam = getSrfc2().getText();
		this.awayTeamPossibilities.setText(choosenAwayTeam);
	}
	
	@FXML
	private void handleValidate() {
		String team1 = "";
		String team2 = "";
		try {
			team1 = DatabaseConnection.getTeamByFullName(homeTeamPossibilities.getText()).getNickName();
			team2 = DatabaseConnection.getTeamByFullName(awayTeamPossibilities.getText()).getNickName();
			MatchController.checkTeamsAreOK(team1, team2);
			String isImportantGameForHomeTeam = isAnImportantGameForHomeTeam.getText();
			String isImportantGameForAwayTeam = isAnImportantGameForAwayTeam.getText();
			String homeTeamHasBetterStanding = homeTeamHasABetterStanding.getText();
			MatchController.checkMatchParameters(isImportantGameForHomeTeam, isImportantGameForAwayTeam,
					homeTeamHasBetterStanding);
		} catch (NullTeamException | SameTeamsException e) {
			setHomeTeamHasABetterStanding("");
			setIsAnImportantGameForHomeTeam("");
			setIsAnImportantGameForAwayTeam("");
			Ligue1Utils.reportError(e.getMessage());
			return;
		} catch (NullMatchParametersException e1) {
			try {
				updateImportantAndStanding(team1, team2);
			} catch (NullTeamException e) {
				Ligue1Utils.reportError(e.getMessage());
				return;
			}
		}
		if (matchTermine()) {
			boolean allFieldsOkAfterMatch = checkAllFormFieldsAfterMatch();
			if (allFieldsOkAfterMatch) {
				String resultat = getScore().getText();
				try {
					MatchController.checkScore(resultat);
					updateResultOnFormWithScore(resultat);
				} catch (ScoreFormatException e) {
					setHomeTeamWin("");
					setDraw("");
					setAwayTeamWin("");
					Ligue1Utils.reportError(e.getMessage());
					return;
				}
				Integer victoireEquipe1 = Ligue1Utils.convertStringTo01(getHomeTeamWin());
				Integer victoireEquipe2 = Ligue1Utils.convertStringTo01(getAwayTeamWin());
				Integer nul = Ligue1Utils.convertStringTo01(getDraw());
				try {
					updateImportantAndStanding(team1, team2);
				} catch (NullTeamException e) {
					Ligue1Utils.reportError(e.getMessage());
					return;
				}
				Integer isImportantGameForHomeTeam = getIsAnImportantGameForHomeTeam();
				Integer isImportantGameForAwayTeam = getIsAnImportantGameForAwayTeam();
				Integer homeTeamHasBetterStanding = getHomeTeamHasABetterStanding();
				Integer journee = Integer.parseInt(getJourney().getText());
				String comment = getComment().getText();
				Match match = new Match(team1, team2, resultat, victoireEquipe1, nul, victoireEquipe2,
						isImportantGameForHomeTeam, isImportantGameForAwayTeam, homeTeamHasBetterStanding, journee, comment);
				try {
					DatabaseConnection.getMatch(match.getId());
					Ligue1Utils.reportError(
							"Le match que vous tentez de créer existe déjà. Merci de modifier le match existant.");
				} catch (NullMatchException e) {
					DatabaseConnection.createOrUpdateMatch(match);
					Ligue1Utils.reportInfo("Match " + match.getId() + " créé avec succès.");
					InitializeWindow.alertInfo("Match " + match.getId() + " créé avec succès.");
//					InitializeWindow.showMatchOverview();
				}
			}
		} else {
			boolean allFieldsOkBeforeMatch = checkAllFormFieldsBeforeMatch();
			if (allFieldsOkBeforeMatch) {
				try {
					updateImportantAndStanding(team1, team2);
				} catch (NullTeamException e) {
					Ligue1Utils.reportError(e.getMessage());
					return;
				}
				Integer isImportantGameForHomeTeam = getIsAnImportantGameForHomeTeam();
				Integer isImportantGameForAwayTeam = getIsAnImportantGameForAwayTeam();
				Integer homeTeamHasBetterStanding = getHomeTeamHasABetterStanding();
				Integer journee = Integer.parseInt(getJourney().getText());
				String comment = getComment().getText();
				Match match = new Match(team1, team2, isImportantGameForHomeTeam, isImportantGameForAwayTeam,
						homeTeamHasBetterStanding, journee, comment);
				try {
					DatabaseConnection.getMatch(match.getId());
					Ligue1Utils.reportError(
							"Le match que vous tentez de créer existe déjà. Merci de modifier le match existant.");
				} catch (NullMatchException e) {
					DatabaseConnection.createOrUpdateMatch(match);
					Ligue1Utils.reportInfo("Match " + match.getId() + " créé avec succès.");
					InitializeWindow.alertInfo("Match " + match.getId() + " créé avec succès.");
//					InitializeWindow.showMatchOverview();
				}
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
			MatchController.checkOnlyOneResult(Integer.parseInt(getHomeTeamWin()),
					Integer.parseInt(getAwayTeamWin()), Integer.parseInt(getDraw()));
		} catch (NullResultatException | NumberFormatException | UnhandledResultatException e) {
			return false;
		}
		return true;
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
		String victoireEquipe1 = homeTeamWin.getText();
		String victoireEquipe2 = awayTeamWin.getText();
		String nul = draw.getText();
		try {
			MatchController.checkOnlyOneResult(Integer.parseInt(victoireEquipe1), Integer.parseInt(victoireEquipe2),
					Integer.parseInt(nul));
		} catch (NullResultatException | NumberFormatException | UnhandledResultatException e) {
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

	/**
	 * Coche les cases sur le classement, l'importance, et le résultat en fonction
	 * des informations renseignées dans le formulaire (surnoms des équipes, score)
	 * 
	 * @param awayTeam
	 * @param homeTeam
	 * 
	 * @throws NullTeamException
	 */
	public void updateImportantAndStanding(String e1, String e2) throws NullTeamException {

		Team team1 = DatabaseConnection.getTeamByNickname(e1);
		Team team2 = DatabaseConnection.getTeamByNickname(e2);
		String equipeMieuxClassee = Ligue1Utils.getEquipeMieuxClassee(team1, team2);
		if (!Ligue1Utils.isEmpty(equipeMieuxClassee)) {
			if (equipeMieuxClassee.equals(e1)) {
				setHomeTeamHasABetterStanding("Oui");
			} else {
				setHomeTeamHasABetterStanding("Non");
			}
		}
		boolean matchImportantE1 = Ligue1Utils.getMatchImportant(e1, e2);
		if (matchImportantE1) {
			setIsAnImportantGameForHomeTeam("Oui");
		} else {
			setIsAnImportantGameForHomeTeam("Non");
		}
		boolean matchImportantE2 = Ligue1Utils.getMatchImportant(e2, e1);
		if (matchImportantE2) {
			setIsAnImportantGameForAwayTeam("Oui");
		} else {
			setIsAnImportantGameForAwayTeam("Non");
		}
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

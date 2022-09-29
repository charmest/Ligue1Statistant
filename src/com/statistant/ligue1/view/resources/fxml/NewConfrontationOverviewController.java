package com.statistant.ligue1.view.resources.fxml;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.statistant.ligue1.controller.AlreadyExistingConfrontationException;
import com.statistant.ligue1.controller.ConfrontationMatchFormatException;
import com.statistant.ligue1.controller.NullConfrontationException;
import com.statistant.ligue1.controller.ScoreFormatException;
import com.statistant.ligue1.controller.StatisticController;
import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.pojo.Team;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class NewConfrontationOverviewController {

	@FXML
	private MenuButton fxHomeTeam;
	@FXML
	private MenuButton fxAwayTeam;
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
	private TextField fxRecent1;
	@FXML
	private TextField fxRecent2;
	@FXML
	private TextField fxRecent3;
	@FXML
	private TextField fxRecent4;
	@FXML
	private TextField fxRecent5;

	public MenuButton getHomeTeamPossibilities() {
		return fxHomeTeam;
	}

	public void setHomeTeamPossibilities(MenuButton homeTeamPossibilities) {
		this.fxHomeTeam = homeTeamPossibilities;
	}

	public MenuButton getAwayTeamPossibilities() {
		return fxAwayTeam;
	}

	public void setAwayTeamPossibilities(MenuButton awayTeamPossibilities) {
		this.fxAwayTeam = awayTeamPossibilities;
	}

	public TextField getFxRecent1() {
		return fxRecent1;
	}

	public void setFxRecent1(TextField fxRecent1) {
		this.fxRecent1 = fxRecent1;
	}

	public TextField getFxRecent2() {
		return fxRecent2;
	}

	public void setFxRecent2(TextField fxRecent2) {
		this.fxRecent2 = fxRecent2;
	}

	public TextField getFxRecent3() {
		return fxRecent3;
	}

	public void setFxRecent3(TextField fxRecent3) {
		this.fxRecent3 = fxRecent3;
	}

	public TextField getFxRecent4() {
		return fxRecent4;
	}

	public void setFxRecent4(TextField fxRecent4) {
		this.fxRecent4 = fxRecent4;
	}

	public TextField getFxRecent5() {
		return fxRecent5;
	}

	public void setFxRecent5(TextField fxRecent5) {
		this.fxRecent5 = fxRecent5;
	}

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

	public TextField getScore() {
		return score;
	}

	public void setScore(TextField score) {
		this.score = score;
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

	@FXML
	private void handleHomeTeamChoiceASM() {
		String choosenHomeTeam = getAsm1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceASSE() {
		String choosenHomeTeam = getAsse1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceDFCO() {
		String choosenHomeTeam = getDfco1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceFCGB() {
		String choosenHomeTeam = getFcgb1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceFCL() {
		String choosenHomeTeam = getFcl1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceFCM() {
		String choosenHomeTeam = getFcm1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceFCN() {
		String choosenHomeTeam = getFcn1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceLOSC() {
		String choosenHomeTeam = getLosc1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceMHSC() {
		String choosenHomeTeam = getMhsc1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceNO() {
		String choosenHomeTeam = getNo1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceOGCN() {
		String choosenHomeTeam = getOgcn1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceOM() {
		String choosenHomeTeam = getOm1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceOL() {
		String choosenHomeTeam = getOl1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoicePSG() {
		String choosenHomeTeam = getPsg1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceRCL() {
		String choosenHomeTeam = getRcl1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceRCS() {
		String choosenHomeTeam = getRcs1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceSCO() {
		String choosenHomeTeam = getSco1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceSB29() {
		String choosenHomeTeam = getSb291().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceSDR() {
		String choosenHomeTeam = getSdr1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleHomeTeamChoiceSRFC() {
		String choosenHomeTeam = getSrfc1().getText();
		this.fxHomeTeam.setText(choosenHomeTeam);
	}

	@FXML
	private void handleAwayTeamChoiceASM() {
		String choosenAwayTeam = getAsm2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceASSE() {
		String choosenAwayTeam = getAsse2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceDFCO() {
		String choosenAwayTeam = getDfco2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceFCGB() {
		String choosenAwayTeam = getFcgb2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceFCL() {
		String choosenAwayTeam = getFcl2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceFCM() {
		String choosenAwayTeam = getFcm2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceFCN() {
		String choosenAwayTeam = getFcn2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceLOSC() {
		String choosenAwayTeam = getLosc2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceMHSC() {
		String choosenAwayTeam = getMhsc2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceNO() {
		String choosenAwayTeam = getNo2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceOGCN() {
		String choosenAwayTeam = getOgcn2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceOM() {
		String choosenAwayTeam = getOm2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceOL() {
		String choosenAwayTeam = getOl2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoicePSG() {
		String choosenAwayTeam = getPsg2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceRCL() {
		String choosenAwayTeam = getRcl2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceRCS() {
		String choosenAwayTeam = getRcs2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceSCO() {
		String choosenAwayTeam = getSco2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceSB29() {
		String choosenAwayTeam = getSb292().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceSDR() {
		String choosenAwayTeam = getSdr2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleAwayTeamChoiceSRFC() {
		String choosenAwayTeam = getSrfc2().getText();
		this.fxAwayTeam.setText(choosenAwayTeam);
	}

	@FXML
	private void handleValidate() {
		String matchName = "";
		try {
			String homeTeamFullName = getHomeTeamPossibilities().getText();
			String awayTeamFullName = getAwayTeamPossibilities().getText();
			String homeTeamNickName = DatabaseConnection.getTeamByFullName(homeTeamFullName).getNickName();
			String awayTeamNickName = DatabaseConnection.getTeamByFullName(awayTeamFullName).getNickName();
			matchName = homeTeamNickName + "-" + awayTeamNickName;
			checkMatchIsAllowed(matchName);
			checkConfrontationDoesNotExist(matchName);
		} catch (ConfrontationMatchFormatException | NullTeamException | AlreadyExistingConfrontationException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
		String recent1 = getFxRecent1().getText();
		String recent2 = getFxRecent2().getText();
		String recent3 = getFxRecent3().getText();
		String recent4 = getFxRecent4().getText();
		String recent5 = getFxRecent5().getText();
		try {
			checkAllFieldsAreOK(recent1, recent2, recent3, recent4, recent5);
		} catch (ScoreFormatException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		} 
		Confrontation confrontation = new Confrontation(matchName, recent1, recent2, recent3, recent4, recent5);
		DatabaseConnection.createOrUpdateConfrontation(confrontation);
		try {
			StatisticController.setStatistiques(confrontation);
		} catch (NullConfrontationException e) {
			Ligue1Utils.reportError("Erreur à la mise à jour des statistiques du match : " + confrontation.getMatch());
			e.printStackTrace();
		}
		Confrontation reversedConfrontation = Ligue1Utils.getReversedConfrontation(confrontation);
		DatabaseConnection.createOrUpdateConfrontation(reversedConfrontation);
		try {
			StatisticController.setStatistiques(reversedConfrontation);
		} catch (NullConfrontationException e) {
			Ligue1Utils.reportError(
					"Erreur à la mise à jour des statistiques du match : " + reversedConfrontation.getMatch());
			e.printStackTrace();
		}
		InitializeWindow.alertInfo("Confrontations " + confrontation.getMatch() + " et "
				+ reversedConfrontation.getMatch() + " créées avec succès.");
//		InitializeWindow.showConfrontationOverview();
	}

	private void checkConfrontationDoesNotExist(String matchName) throws AlreadyExistingConfrontationException {
		try {
			DatabaseConnection.getConfrontation(matchName);
			throw new AlreadyExistingConfrontationException(
					"La confrontation " + matchName + " existe déjà. Merci de modifier la confrontation existante.");
		} catch (NullConfrontationException e) {
		}
	}

	private void checkAllFieldsAreOK(String recent1, String recent2, String recent3, String recent4, String recent5)
			throws ScoreFormatException {
//		if (Ligue1Utils.isEmpty(recent1) && Ligue1Utils.isEmpty(recent2) && Ligue1Utils.isEmpty(recent3)
//				&& Ligue1Utils.isEmpty(recent4) && Ligue1Utils.isEmpty(recent5)) {
//			throw new EmptyConfrontationScoresException(
//					"Merci de saisir au moins un score (au format \"x-x\") pour créer une confrontation.");
//		}
		if (!Ligue1Utils.isEmpty(recent1) && !Ligue1Utils.isScoreWellFormed(recent1)) {
			throw new ScoreFormatException(
					"Le format du score du match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
		if (!Ligue1Utils.isEmpty(recent2) && !Ligue1Utils.isScoreWellFormed(recent2)) {
			throw new ScoreFormatException(
					"Le format du score du deuxième match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
		if (!Ligue1Utils.isEmpty(recent3) && !Ligue1Utils.isScoreWellFormed(recent3)) {
			throw new ScoreFormatException(
					"Le format du score du troisième match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
		if (!Ligue1Utils.isEmpty(recent4) && !Ligue1Utils.isScoreWellFormed(recent4)) {
			throw new ScoreFormatException(
					"Le format du score du quatrième match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
		if (!Ligue1Utils.isEmpty(recent5) && !Ligue1Utils.isScoreWellFormed(recent5)) {
			throw new ScoreFormatException(
					"Le format du score du cinquième match le plus récent n'est pas autorisé. Merci de saisir un score au format x-x.");
		}
	}

	private static void checkMatchIsAllowed(String matchName) throws ConfrontationMatchFormatException {
		String regex = "^[A-Z0-9]+-[A-Z0-9]+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(matchName);
		if (!m.matches()) {
			throw new ConfrontationMatchFormatException("Erreur dans le format du match. Merci de réitérer la saisie.");
		}
		String[] split = matchName.split("-");
		String homeTeam = split[0];
		String awayTeam = split[1];
		Team t1 = null;
		Team t2 = null;
		try {
			t1 = DatabaseConnection.getTeamByNickname(homeTeam);
			t2 = DatabaseConnection.getTeamByNickname(awayTeam);
		} catch (NullTeamException e) {
			throw new ConfrontationMatchFormatException("L'une des équipes est nulle");
		}
		if (t1 == null) {
			throw new ConfrontationMatchFormatException(
					"L'équipe 1 n'évolue pas en ligue 1. Merci de réitérer la saisie");
		}
		if (t2 == null) {
			throw new ConfrontationMatchFormatException(
					"L'équipe 2 n'évolue pas en ligue 1. Merci de réitérer la saisie");
		}
		if (t2.getName().equals(t1.getName())) {
			throw new ConfrontationMatchFormatException(
					"Une équipe ne peut pas s'auto-affronter. Merci de réitérer la saisie");
		}
	}
}

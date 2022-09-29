package com.statistant.ligue1.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullMatchException;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.pojo.Configuration;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.pojo.Match;
import com.statistant.ligue1.pojo.Statistic;
import com.statistant.ligue1.pojo.Team;
import com.statistant.ligue1.pojo.comparator.AverageScoredGoalsComparator;
import com.statistant.ligue1.pojo.comparator.PercentageComparator;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

/**
 * Generate the report of the specific match in the folder
 * "C:/perso/Ligue1/{nbJournee}/".
 * 
 * @author Thomas CHARMES
 */
public class GenerateStatisticsReport {

	/**
	 * generate the match report, in Word format.
	 * 
	 * @param statistics
	 * @param wi
	 * @throws IOException
	 * @throws NullTeamException
	 * @throws NullConfrontationException
	 * @throws NullMatchException
	 * @throws InvalidNumberToConvertFromBooleanException
	 * @throws NullStatisticException
	 */
	public static void generateReport(Statistic statistics, String selectedDirectoryPath)
			throws IOException, NullTeamException, NullConfrontationException, NullMatchException,
			InvalidNumberToConvertFromBooleanException, NullStatisticException {

		Configuration config = new Configuration(5, 6F, 6F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 6F);
		String match = statistics.getMatch();
		String[] teams = match.split("-");
		String homeTeam = teams[0];
		String awayTeam = teams[1];
		Match matchEntity = DatabaseConnection.getMatch(match);
		;
		int journey = matchEntity.getJourney();

		XWPFDocument document = new XWPFDocument();
		File dossier = new File(selectedDirectoryPath + "\\Journée " + journey);
		dossier.mkdir();
		File rapport_match = new File(
				selectedDirectoryPath + "\\Journée " + journey + "\\Rapport " + matchEntity.getId() + ".docx");
		FileOutputStream out = new FileOutputStream(rapport_match);
		TreeMap<String, String> mapTreeStatsGenerales = new TreeMap<>();
		mapTreeStatsGenerales = getTreeMapStatsVictoires(statistics, match, homeTeam, awayTeam);
		String generalStandingOfHomeTeam;
		String generalStandingOfAwayTeam;
		writeTitleInWordDocument("RAPPORT STATISTIQUES DU MATCH " + match + " :", document);
		String comment = matchEntity.getComment();
		if (!Ligue1Utils.isEmpty(comment)) {
			Map<String, String> mapComment = new HashMap<>();
			mapComment.put("A savoir", comment);
			writeMapInWordDocument("Informations importantes", mapComment, document);
		}
		try {
			generalStandingOfHomeTeam = getGeneralStandingAndPointsNumber(homeTeam);
			generalStandingOfAwayTeam = getGeneralStandingAndPointsNumber(awayTeam);

			writeMapInWordDocument(
					"Stats des 5 dernières confrontations entre " + homeTeam + " (" + generalStandingOfHomeTeam
							+ ") et " + awayTeam + " (" + generalStandingOfAwayTeam + ")",
					mapTreeStatsGenerales, document);
		} catch (NullStatisticException e) {
			InitializeWindow.alertError(
					"Erreur à la récupération du classement et du nombre de points d'une équipe : " + e.getMessage());
			e.printStackTrace();
		}

		Confrontation confrontation = DatabaseConnection.getConfrontation(match);

		document.createParagraph().createRun()
				.setText("Match le plus récent " + statistics.getMatch() + "--> " + confrontation.getRecent1() + "\n"
						+ "----- " + confrontation.getRecent2() + "\n" + "----- " + confrontation.getRecent3() + "\n"
						+ "----- " + confrontation.getRecent4() + "\n" + "----- " + confrontation.getRecent5()
						+ " <-- Match le plus ancien");

		Map<String, String> mapStatsLDEM = getTreeMapStatsLDEM(statistics, match, homeTeam, awayTeam);
		writeMapInWordDocument("" + match, mapStatsLDEM, document);

		TreeMap<String, String> mapTreeStatsMoyennes = getTreeMapStatsMoyennes(statistics, match, homeTeam, awayTeam);
		writeMapInWordDocument("Statistiques des moyennes de buts du match lors des dernières confrontations " + match,
				mapTreeStatsMoyennes, document);

		TreeMap<String, String> mapTreeStatsNombreDeButsMatch = getTreeMapStatsNombreButsMatch(statistics, match);
		writeMapInWordDocument("Statistiques sur le nombre de buts du match lors des dernières confrontations " + match,
				mapTreeStatsNombreDeButsMatch, document);

		TreeMap<String, String> mapTreeStatsNombreDeButsE1 = getTreeMapStatsNombreButsE1(statistics, match, homeTeam);
		writeMapInWordDocument(
				"Statistiques sur le nombre de buts de l'équipe à domicile lors des dernières confrontations ",
				mapTreeStatsNombreDeButsE1, document);

		TreeMap<String, String> mapTreeStatsNombreDeButsE2 = getTreeMapStatsNombreButsE2(statistics, match, awayTeam);
		writeMapInWordDocument(
				"Statistiques sur le nombre de buts de l'équipe à l'extérieur lors des dernières confrontations ",
				mapTreeStatsNombreDeButsE2, document);

		TreeMap<String, String> mapTreeStatsNombreExactDeButsE1 = getTreeMapStatsHomeTeamExactGoalsNumber(statistics,
				match, homeTeam);
		writeMapInWordDocument(
				"Statistiques sur le nombre exact de buts marqués lors des dernières confrontations par l'équipe à domicile ",
				mapTreeStatsNombreExactDeButsE1, document);

		TreeMap<String, String> mapTreeStatsNombreExactDeButsE2 = getTreeMapStatsAwayTeamExactGoalsNumber(statistics,
				match, awayTeam);
		writeMapInWordDocument(
				"Statistiques sur le nombre exact de buts marqués lors des dernières confrontations par l'équipe à l'extérieur ",
				mapTreeStatsNombreExactDeButsE2, document);

		TreeMap<String, String> mapTreeStatsEcartButE1 = getTreeMapStatsEcartButsE1(statistics, match, homeTeam);
		writeMapInWordDocument(
				"Statistiques sur l'écart de score en faveur de l'équipe à domicile lors des dernières confrontations ",
				mapTreeStatsEcartButE1, document);

		TreeMap<String, String> mapTreeStatsEcartButE2 = getTreeMapStatsEcartButsE2(statistics, match, awayTeam);
		writeMapInWordDocument(
				"Statistiques sur l'écart de score en faveur de l'équipe à l'extérieur lors des dernières confrontations ",
				mapTreeStatsEcartButE2, document);

		Boolean e1MieuxClassee = Ligue1Utils.convertToBoolean(matchEntity.getHomeTeamHasABetterStanding());
		Boolean e1MatchImportant = Ligue1Utils.convertToBoolean(matchEntity.getIsAnImportantGameForHomeTeam());
		Boolean e2MatchImportant = Ligue1Utils.convertToBoolean(matchEntity.getIsAnImportantGameForAwayTeam());
		String formE1 = matchEntity.getHomeTeamNickname();
		String formE2 = matchEntity.getAwayTeamNickname();

		if (Ligue1Utils.isEmpty(formE1) || Ligue1Utils.isEmpty(formE2)) {
			InitializeWindow.alertError("Au moins l'une des deux équipes n'est pas renseignée");
		}
		writeHomeTeamStatsFromTableEquipes(document, formE1, e1MieuxClassee, e1MatchImportant);

		writeAwayTeamStatsFromTableEquipes(document, formE2, e1MieuxClassee, e2MatchImportant);

		TreeMap<String, String> mapTreeStatsPronostics = new TreeMap<>();
		mapTreeStatsPronostics = getTreeMapStatsPronostics(statistics, match, homeTeam, awayTeam, config);
		writeMapInWordDocument("Probabilités du résultat : ", mapTreeStatsPronostics, document);

		document.write(out);
		out.close();

		matchEntity.setActiveStatisticsReportGeneration(0);
		DatabaseConnection.createOrUpdateMatch(matchEntity);

		InitializeWindow.alertInfo("Le rapport du match " + match + " a bien été enregistré dans le dossier "
				+ rapport_match.getAbsolutePath());

	}

	private static String getGeneralStandingAndPointsNumber(String teamNickname)
			throws NullStatisticException, NullTeamException {

		String resultat = "";
		Integer classement = -1;
		Integer nbPoints = -1;
		Team team = DatabaseConnection.getTeamByNickname(teamNickname);
		classement = team.getStanding();
		nbPoints = team.getNbPoints();

		if (classement == -1 || nbPoints == -1) {
			throw new NullStatisticException(
					"Erreur lors de la récupération du classement ou du nombre de points de l'équipe " + teamNickname);
		}

		if (classement == 1) {
			resultat = classement + "er : " + nbPoints + " pts";
		} else {
			resultat = classement + "eme : " + nbPoints + " pts";
		}

		return resultat;
	}

	private static void writeHomeTeamStatsFromTableEquipes(XWPFDocument document, String team1,
			Boolean homeTeamHasBestStanding, Boolean isImportantForTeam1) throws NullTeamException {

		Team team = DatabaseConnection.getTeamByNickname(team1);
		if (team == null) {
			InitializeWindow.alertError(
					"Erreur à la récupération de la resource équipe de la méthode writeHomeTeamStatsFromTableEquipes()");
			return;
		}

		// Stats globales

		Integer nbMatchsGagnes = team.getNbWins();
		Integer nbMatchsJoues = team.getNbMatchesPlayed();
		Integer nbMatchsNuls = team.getNbDraws();
		Integer nbMatchsPerdus = team.getNbLosses();
		Integer serieD = team.getLoosingSerie();
		Integer serieN = team.getDrawSerie();
		Integer serieV = team.getWinningSerie();

		Float pourcentageVictoires = null;
		Float pourcentageNuls = null;
		Float pourcentageDefaites = null;

		XWPFRun run = document.createParagraph().createRun();
		run.setBold(true);
		run.setFontSize(14);
		run.setText("Statistiques générales de l'équipe " + team1);

		if (nbMatchsJoues != 0L) {
			pourcentageVictoires = (nbMatchsGagnes.floatValue() / nbMatchsJoues.floatValue()) * 100;
			pourcentageNuls = (nbMatchsNuls.floatValue() / nbMatchsJoues.floatValue()) * 100;
			pourcentageDefaites = (nbMatchsPerdus.floatValue() / nbMatchsJoues.floatValue()) * 100;

			document.createParagraph().createRun().setText("Pourcentage de victoires : " + pourcentageVictoires + "%");
			document.createParagraph().createRun().setText("Pourcentage de matchs nuls : " + pourcentageNuls + "%");
			document.createParagraph().createRun().setText("Pourcentage de défaites : " + pourcentageDefaites + "%");
		} else {
			document.createParagraph().createRun().setText("L'équipe " + team1
					+ "  va jouer son premier match de la saison, aucune donnée n'est disponible pour le moment.");
		}

		if (serieV > 0L)
			document.createParagraph().createRun().setText(
					"Nombre de victoires sur les 5 derniers matchs de l'équipe " + team1 + " : " + serieV + " V");
		if (serieN > 0L)
			document.createParagraph().createRun().setText(
					"Nombre de matchs nuls sur les 5 derniers matchs de l'équipe " + team1 + " : " + serieN + " N");
		if (serieD > 0L)
			document.createParagraph().createRun().setText(
					"Nombre de défaites sur les 5 derniers matchs de l'équipe " + team1 + " : " + serieD + " D");

		// Stats domicile

		Float pourcentageVictoiresDomicile = null;
		Float pourcentageNulsDomicile = null;
		Float pourcentageDefaitesDomicile = null;

		Integer nbMatchsGagnesDomicile = team.getNbHomeWins();
		Integer nbMatchsJouesDomicile = team.getNbHomeMatchesPlayed();
		Integer nbMatchsNulsDomicile = team.getNbHomeDraws();
		Integer nbMatchsPerdusDomicile = team.getNbHomeLosses();
		Integer serieDDomicile = team.getHomeLoosingSerie();
		Integer serieNDomicile = team.getHomeDrawSerie();
		Integer serieVDomicile = team.getHomeWinningSerie();

		XWPFRun runDom = document.createParagraph().createRun();
		runDom.setBold(true);
		runDom.setFontSize(14);
		runDom.setText("Statistiques à domicile de l'équipe " + team1);

		if (nbMatchsJouesDomicile != 0L) {
			pourcentageVictoiresDomicile = (nbMatchsGagnesDomicile.floatValue() / nbMatchsJouesDomicile.floatValue())
					* 100;
			pourcentageNulsDomicile = (nbMatchsNulsDomicile.floatValue() / nbMatchsJouesDomicile.floatValue()) * 100;
			pourcentageDefaitesDomicile = (nbMatchsPerdusDomicile.floatValue() / nbMatchsJouesDomicile.floatValue())
					* 100;

			document.createParagraph().createRun()
					.setText("Pourcentage de victoires à domicile : " + pourcentageVictoiresDomicile + "%");
			document.createParagraph().createRun()
					.setText("Pourcentage de matchs nuls à domicile : " + pourcentageNulsDomicile + "%");
			document.createParagraph().createRun()
					.setText("Pourcentage de défaites à domicile : " + pourcentageDefaitesDomicile + "%");
		} else {
			document.createParagraph().createRun().setText("L'équipe " + team1
					+ " va jouer son premier match de la saison à domicile, aucune donnée n'est disponible pour le moment.");
		}

		if (serieVDomicile > 0L)
			document.createParagraph().createRun()
					.setText("Nombre de victoires à domicile sur les 5 derniers matchs de l'équipe " + team1 + " : "
							+ serieVDomicile + " V");
		if (serieNDomicile > 0L)
			document.createParagraph().createRun()
					.setText("Nombre de matchs nuls à domicile sur les 5 derniers matchs de l'équipe " + team1 + " : "
							+ serieNDomicile + " N");
		if (serieDDomicile > 0L)
			document.createParagraph().createRun()
					.setText("Nombre de défaites à domicile sur les 5 derniers matchs de l'équipe " + team1 + " : "
							+ serieDDomicile + " D");

		Integer nbDefaitesContreClassementInf;
		Integer nbDefaitesContreClassementInfDomicile;
		Integer nbMatchsJouesContreClassementInf;
		Integer nbMatchsJouesContreClassementInfDomicile;
		Integer nbMatchsNulsContreClassementInf;
		Integer nbMatchsNulsContreClassementInfDomicile;
		Integer nbVictoiresContreClassementInf;
		Integer nbVictoiresContreClassementInfDomicile;

		Float pourcentageDefaitesContreClassementInf = null;
		Float pourcentageDefaitesContreClassementInfDomicile = null;
		Float pourcentageNulsContreClassementInf = null;
		Float pourcentageNulsContreClassementInfDomicile = null;
		Float pourcentageVictoiresContreClassementInf = null;
		Float pourcentageVictoiresContreClassementInfDomicile = null;

		Integer nbDefaitesContreClassementSup;
		Integer nbDefaitesContreClassementSupDomicile;
		Integer nbMatchsJouesContreClassementSup;
		Integer nbMatchsJouesContreClassementSupDomicile;
		Integer nbMatchsNulsContreClassementSup;
		Integer nbMatchsNulsContreClassementSupDomicile;
		Integer nbVictoiresContreClassementSup;
		Integer nbVictoiresContreClassementSupDomicile;

		Float pourcentageDefaitesContreClassementSup = null;
		Float pourcentageDefaitesContreClassementSupDomicile = null;
		Float pourcentageNulsContreClassementSup = null;
		Float pourcentageNulsContreClassementSupDomicile = null;
		Float pourcentageVictoiresContreClassementSup = null;
		Float pourcentageVictoiresContreClassementSupDomicile = null;

		Integer nbDefaitesContreImportant;
		Integer nbDefaitesContreImportantDomicile;
		Integer nbMatchsJouesContreImportant;
		Integer nbMatchsJouesContreImportantDomicile;
		Integer nbMatchsNulsContreImportant;
		Integer nbMatchsNulsContreImportantDomicile;
		Integer nbVictoiresContreImportant;
		Integer nbVictoiresContreImportantDomicile;

		Float pourcentageDefaitesContreImportant = null;
		Float pourcentageDefaitesContreImportantDomicile = null;
		Float pourcentageNulsContreImportant = null;
		Float pourcentageNulsContreImportantDomicile = null;
		Float pourcentageVictoiresContreImportant = null;
		Float pourcentageVictoiresContreImportantDomicile = null;

		Integer nbDefaitesContreBanal;
		Integer nbDefaitesContreBanalDomicile;
		Integer nbMatchsJouesContreBanal;
		Integer nbMatchsJouesContreBanalDomicile;
		Integer nbMatchsNulsContreBanal;
		Integer nbMatchsNulsContreBanalDomicile;
		Integer nbVictoiresContreBanal;
		Integer nbVictoiresContreBanalDomicile;

		Float pourcentageDefaitesContreBanal = null;
		Float pourcentageDefaitesContreBanalDomicile = null;
		Float pourcentageNulsContreBanal = null;
		Float pourcentageNulsContreBanalDomicile = null;
		Float pourcentageVictoiresContreBanal = null;
		Float pourcentageVictoiresContreBanalDomicile = null;

		if (homeTeamHasBestStanding) {

			nbDefaitesContreClassementInf = team.getNbLossesAgainstStandingInferior();
			nbDefaitesContreClassementInfDomicile = team.getNbLossesAgainstStandingInferiorAtHome();
			nbMatchsJouesContreClassementInf = team.getNbMatchesPlayedAgainstStandingInferior();
			nbMatchsJouesContreClassementInfDomicile = team.getNbMatchesPlayedAgainstStandingInferiorAtHome();
			nbMatchsNulsContreClassementInf = team.getNbDrawsAgainstStandingInferior();
			nbMatchsNulsContreClassementInfDomicile = team.getNbDrawsAgainstStandingInferiorAtHome();
			nbVictoiresContreClassementInf = team.getNbWinsAgainstStandingInferior();
			nbVictoiresContreClassementInfDomicile = team.getNbWinsAgainstStandingInferiorAtHome();

			XWPFRun runClassementInf = document.createParagraph().createRun();
			runClassementInf.setBold(true);
			runClassementInf.setFontSize(14);
			runClassementInf.setText("Statistiques de l'équipe " + team1 + " contre une équipe moins bien classée");

			if (nbMatchsJouesContreClassementInf != 0L) {
				pourcentageVictoiresContreClassementInf = (nbVictoiresContreClassementInf.floatValue()
						/ nbMatchsJouesContreClassementInf.floatValue()) * 100;
				pourcentageNulsContreClassementInf = (nbMatchsNulsContreClassementInf.floatValue()
						/ nbMatchsJouesContreClassementInf.floatValue()) * 100;
				pourcentageDefaitesContreClassementInf = (nbDefaitesContreClassementInf.floatValue()
						/ nbMatchsJouesContreClassementInf.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires contre une équipe moins bien classée : "
								+ pourcentageVictoiresContreClassementInf + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls contre une équipe moins bien classée : "
								+ pourcentageNulsContreClassementInf + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites contre une équipe moins bien classée : "
								+ pourcentageDefaitesContreClassementInf + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team1
						+ " va jouer son premier match de la saison contre une équipe moins bien classée qu'elle, aucune donnée n'est disponible pour le moment.");
			}

			XWPFRun runClassementInfDom = document.createParagraph().createRun();
			runClassementInfDom.setBold(true);
			runClassementInfDom.setFontSize(14);
			runClassementInfDom
					.setText("Statistiques de l'équipe " + team1 + " à domicile contre une équipe moins bien classée");

			if (nbMatchsJouesContreClassementInfDomicile != 0L) {
				pourcentageVictoiresContreClassementInfDomicile = (nbVictoiresContreClassementInfDomicile.floatValue()
						/ nbMatchsJouesContreClassementInfDomicile.floatValue()) * 100;
				pourcentageNulsContreClassementInfDomicile = (nbMatchsNulsContreClassementInfDomicile.floatValue()
						/ nbMatchsJouesContreClassementInfDomicile.floatValue()) * 100;
				pourcentageDefaitesContreClassementInfDomicile = (nbDefaitesContreClassementInfDomicile.floatValue()
						/ nbMatchsJouesContreClassementInfDomicile.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires à domicile contre une équipe moins bien classée : "
								+ pourcentageVictoiresContreClassementInfDomicile + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls à domicile contre une équipe moins bien classée : "
								+ pourcentageNulsContreClassementInfDomicile + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites à domicile contre une équipe moins bien classée : "
								+ pourcentageDefaitesContreClassementInfDomicile + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team1
						+ " va jouer son premier match de la saison à domicile contre une équipe moins bien classée qu'elle, aucune donnée n'est disponible pour le moment.");
			}

		} else {
			nbDefaitesContreClassementSup = team.getNbLossesAgainstStandingSuperior();
			nbDefaitesContreClassementSupDomicile = team.getNbLossesAgainstStandingSuperiorAtHome();
			nbMatchsJouesContreClassementSup = team.getNbMatchesPlayedAgainstStandingSuperior();
			nbMatchsJouesContreClassementSupDomicile = team.getNbMatchesPlayedAgainstStandingSuperiorAtHome();
			nbMatchsNulsContreClassementSup = team.getNbDrawsAgainstStandingSuperior();
			nbMatchsNulsContreClassementSupDomicile = team.getNbDrawsAgainstStandingSuperiorAtHome();
			nbVictoiresContreClassementSup = team.getNbWinsAgainstStandingSuperior();
			nbVictoiresContreClassementSupDomicile = team.getNbWinsAgainstStandingSuperiorAtHome();

			XWPFRun runClassementSup = document.createParagraph().createRun();
			runClassementSup.setBold(true);
			runClassementSup.setFontSize(14);
			runClassementSup.setText("Statistiques de l'équipe " + team1 + " contre une équipe mieux classée");

			if (nbMatchsJouesContreClassementSup != 0L) {
				pourcentageVictoiresContreClassementSup = (nbVictoiresContreClassementSup.floatValue()
						/ nbMatchsJouesContreClassementSup.floatValue()) * 100;
				pourcentageNulsContreClassementSup = (nbMatchsNulsContreClassementSup.floatValue()
						/ nbMatchsJouesContreClassementSup.floatValue()) * 100;
				pourcentageDefaitesContreClassementSup = (nbDefaitesContreClassementSup.floatValue()
						/ nbMatchsJouesContreClassementSup.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires contre une équipe mieux classée : "
								+ pourcentageVictoiresContreClassementSup + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls contre une équipe mieux classée : "
								+ pourcentageNulsContreClassementSup + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites contre une équipe mieux classée : "
								+ pourcentageDefaitesContreClassementSup + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team1
						+ " va jouer son premier match de la saison contre une équipe mieux classée qu'elle, aucune donnée n'est disponible pour le moment.");
			}

			XWPFRun runClassementSupDomicile = document.createParagraph().createRun();
			runClassementSupDomicile.setBold(true);
			runClassementSupDomicile.setFontSize(14);
			runClassementSupDomicile
					.setText("Statistiques de l'équipe " + team1 + " à domicile contre une équipe mieux classée");

			if (nbMatchsJouesContreClassementSupDomicile != 0L) {
				pourcentageVictoiresContreClassementSupDomicile = (nbVictoiresContreClassementSupDomicile.floatValue()
						/ nbMatchsJouesContreClassementSupDomicile.floatValue()) * 100;
				pourcentageNulsContreClassementSupDomicile = (nbMatchsNulsContreClassementSupDomicile.floatValue()
						/ nbMatchsJouesContreClassementSupDomicile.floatValue()) * 100;
				pourcentageDefaitesContreClassementSupDomicile = (nbDefaitesContreClassementSupDomicile.floatValue()
						/ nbMatchsJouesContreClassementSupDomicile.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires à domicile contre une équipe mieux classée : "
								+ pourcentageVictoiresContreClassementSupDomicile + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls à domicile contre une équipe mieux classée : "
								+ pourcentageNulsContreClassementSupDomicile + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites à domicile contre une équipe mieux classée : "
								+ pourcentageDefaitesContreClassementSupDomicile + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team1
						+ " va jouer son premier match de la saison à domicile contre une équipe mieux classée qu'elle, aucune donnée n'est disponible pour le moment.");
			}
		}

		if (isImportantForTeam1) {

			nbDefaitesContreImportant = team.getNbLossesAgainstImportantOpponent();
			nbDefaitesContreImportantDomicile = team.getNbLossesAgainstImportantOpponentAtHome();
			nbMatchsJouesContreImportant = team.getNbMatchesPlayedAgainstImportantOpponent();
			nbMatchsJouesContreImportantDomicile = team.getNbMatchesPlayedAgainstImportantOpponentAtHome();
			nbMatchsNulsContreImportant = team.getNbDrawsAgainstImportantOpponent();
			nbMatchsNulsContreImportantDomicile = team.getNbDrawsAgainstImportantOpponentAtHome();
			nbVictoiresContreImportant = team.getNbWinsAgainstImportantOpponent();
			nbVictoiresContreImportantDomicile = team.getNbWinsAgainstImportantOpponentAtHome();

			XWPFRun runImportant = document.createParagraph().createRun();
			runImportant.setBold(true);
			runImportant.setFontSize(14);
			runImportant.setText("Statistiques de l'équipe " + team1 + " dans un match important");

			if (nbMatchsJouesContreImportant != 0L) {
				pourcentageVictoiresContreImportant = (nbVictoiresContreImportant.floatValue()
						/ nbMatchsJouesContreImportant.floatValue()) * 100;
				pourcentageNulsContreImportant = (nbMatchsNulsContreImportant.floatValue()
						/ nbMatchsJouesContreImportant.floatValue()) * 100;
				pourcentageDefaitesContreImportant = (nbDefaitesContreImportant.floatValue()
						/ nbMatchsJouesContreImportant.floatValue()) * 100;

				document.createParagraph().createRun().setText("Pourcentage de victoires dans un match important : "
						+ pourcentageVictoiresContreImportant + "%");
				document.createParagraph().createRun().setText(
						"Pourcentage de matchs nuls dans un match important : " + pourcentageNulsContreImportant + "%");
				document.createParagraph().createRun().setText("Pourcentage de défaites dans un match important : "
						+ pourcentageDefaitesContreImportant + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team1
						+ "  va jouer son premier match important de la saison, aucune donnée n'est disponible pour le moment.");
			}

			XWPFRun runImportantDomicile = document.createParagraph().createRun();
			runImportantDomicile.setBold(true);
			runImportantDomicile.setFontSize(14);
			runImportantDomicile.setText("Statistiques de l'équipe " + team1 + " à domicile dans un match important");

			if (nbMatchsJouesContreImportantDomicile != 0L) {
				pourcentageVictoiresContreImportantDomicile = (nbVictoiresContreImportantDomicile.floatValue()
						/ nbMatchsJouesContreImportantDomicile.floatValue()) * 100;
				pourcentageNulsContreImportantDomicile = (nbMatchsNulsContreImportantDomicile.floatValue()
						/ nbMatchsJouesContreImportantDomicile.floatValue()) * 100;
				pourcentageDefaitesContreImportantDomicile = (nbDefaitesContreImportantDomicile.floatValue()
						/ nbMatchsJouesContreImportantDomicile.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires à domicile dans un match important : "
								+ pourcentageVictoiresContreImportantDomicile + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls à domicile dans un match important : "
								+ pourcentageNulsContreImportantDomicile + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites à domicile dans un match important : "
								+ pourcentageDefaitesContreImportantDomicile + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team1
						+ " va jouer son premier match important de la saison à domicile, aucune donnée n'est disponible pour le moment.");
			}

		} else {

			nbDefaitesContreBanal = team.getNbLossesAgainstNormalOpponent();
			nbDefaitesContreBanalDomicile = team.getNbLossesAgainstNormalOpponentAtHome();
			nbMatchsJouesContreBanal = team.getNbMatchesPlayedAgainstNormalOpponent();
			nbMatchsJouesContreBanalDomicile = team.getNbMatchesPlayedAgainstNormalOpponentAtHome();
			nbMatchsNulsContreBanal = team.getNbDrawsAgainstNormalOpponent();
			nbMatchsNulsContreBanalDomicile = team.getNbDrawsAgainstNormalOpponentAtHome();
			nbVictoiresContreBanal = team.getNbWinsAgainstNormalOpponent();
			nbVictoiresContreBanalDomicile = team.getNbWinsAgainstNormalOpponentAtHome();

			XWPFRun runBanal = document.createParagraph().createRun();
			runBanal.setBold(true);
			runBanal.setFontSize(14);
			runBanal.setText(
					"Statistiques de l'équipe " + team1 + " dans un match qui n'est pas considéré comme important");

			if (nbMatchsJouesContreBanal != 0L) {
				pourcentageVictoiresContreBanal = (nbVictoiresContreBanal.floatValue()
						/ nbMatchsJouesContreBanal.floatValue()) * 100;
				pourcentageNulsContreBanal = (nbMatchsNulsContreBanal.floatValue()
						/ nbMatchsJouesContreBanal.floatValue()) * 100;
				pourcentageDefaitesContreBanal = (nbDefaitesContreBanal.floatValue()
						/ nbMatchsJouesContreBanal.floatValue()) * 100;

				document.createParagraph().createRun().setText(
						"Pourcentage de victoires dans un match classique : " + pourcentageVictoiresContreBanal + "%");
				document.createParagraph().createRun().setText(
						"Pourcentage de matchs nuls dans un match classique : " + pourcentageNulsContreBanal + "%");
				document.createParagraph().createRun().setText(
						"Pourcentage de défaites dans un match classique : " + pourcentageDefaitesContreBanal + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team1
						+ " va jouer son premier match classique de la saison, aucune donnée n'est disponible pour le moment.");
			}

			XWPFRun runBanalDomicile = document.createParagraph().createRun();
			runBanalDomicile.setBold(true);
			runBanalDomicile.setFontSize(14);
			runBanalDomicile.setText("Statistiques de l'équipe " + team1
					+ " à domicile dans un match qui n'est pas considéré comme important");

			if (nbMatchsJouesContreBanalDomicile != 0L) {
				pourcentageVictoiresContreBanalDomicile = (nbVictoiresContreBanalDomicile.floatValue()
						/ nbMatchsJouesContreBanalDomicile.floatValue()) * 100;
				pourcentageNulsContreBanalDomicile = (nbMatchsNulsContreBanalDomicile.floatValue()
						/ nbMatchsJouesContreBanalDomicile.floatValue()) * 100;
				pourcentageDefaitesContreBanalDomicile = (nbDefaitesContreBanalDomicile.floatValue()
						/ nbMatchsJouesContreBanalDomicile.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires à domicile dans un match classique : "
								+ pourcentageVictoiresContreBanalDomicile + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls à domicile dans un match classique : "
								+ pourcentageNulsContreBanalDomicile + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites à domicile dans un match classique : "
								+ pourcentageDefaitesContreBanalDomicile + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team1
						+ " va jouer son premier match classique de la saison à domicile, aucune donnée n'est disponible pour le moment.");
			}

		}

	}

	private static void writeAwayTeamStatsFromTableEquipes(XWPFDocument document, String team2,
			Boolean homeTeamHasBestStanding, Boolean isImportantForTeam2) throws NullTeamException {

		Team team = DatabaseConnection.getTeamByNickname(team2);
		if (team == null) {
			InitializeWindow.alertError(
					"Erreur à la récupération de la resource équipe de la méthode writeAwayTeamStatsFromTableEquipes()");
			return;
		}

		// Stats globales
		Integer nbMatchsGagnes = team.getNbWins();
		Integer nbMatchsJoues = team.getNbMatchesPlayed();
		Integer nbMatchsNuls = team.getNbDraws();
		Integer nbMatchsPerdus = team.getNbLosses();
		Integer serieD = team.getLoosingSerie();
		Integer serieN = team.getDrawSerie();
		Integer serieV = team.getWinningSerie();

		Float pourcentageVictoires = null;
		Float pourcentageNuls = null;
		Float pourcentageDefaites = null;

		XWPFRun run = document.createParagraph().createRun();
		run.setBold(true);
		run.setFontSize(14);
		run.setText("Statistiques générales de l'équipe " + team2);

		if (nbMatchsJoues != 0L) {
			pourcentageVictoires = (nbMatchsGagnes.floatValue() / nbMatchsJoues.floatValue()) * 100;
			pourcentageNuls = (nbMatchsNuls.floatValue() / nbMatchsJoues.floatValue()) * 100;
			pourcentageDefaites = (nbMatchsPerdus.floatValue() / nbMatchsJoues.floatValue()) * 100;

			document.createParagraph().createRun().setText("Pourcentage de victoires : " + pourcentageVictoires + "%");
			document.createParagraph().createRun().setText("Pourcentage de matchs nuls : " + pourcentageNuls + "%");
			document.createParagraph().createRun().setText("Pourcentage de défaites : " + pourcentageDefaites + "%");
		} else {
			document.createParagraph().createRun().setText("L'équipe " + team2
					+ " va jouer son premier match de la saison, aucune donnée n'est disponible pour le moment.");
		}

		if (serieV > 0L)
			document.createParagraph().createRun().setText(
					"Nombre de victoires de l'équipe " + team2 + " sur les 5 derniers matchs : " + serieV + " V");
		if (serieN > 0L)
			document.createParagraph().createRun().setText(
					"Nombre de matchs nuls de l'équipe " + team2 + " sur les 5 derniers matchs : " + serieN + " N");
		if (serieD > 0L)
			document.createParagraph().createRun().setText(
					"Nombre de défaites de l'équipe " + team2 + " sur les 5 derniers matchs : " + serieD + " D");

		// Stats extérieur

		Integer nbMatchsGagnesExterieur = team.getNbAwayWins();
		Integer nbMatchsJouesExterieur = team.getNbAwayMatchesPlayed();
		Integer nbMatchsNulsExterieur = team.getNbAwayDraws();
		Integer nbMatchsPerdusExterieur = team.getNbAwayLosses();
		Integer serieDExterieur = team.getAwayLoosingSerie();
		Integer serieNExterieur = team.getAwayDrawSerie();
		Integer serieVExterieur = team.getAwayWinningSerie();

		Float pourcentageVictoiresExterieur = null;
		Float pourcentageNulsExterieur = null;
		Float pourcentageDefaitesExterieur = null;

		XWPFRun runDom = document.createParagraph().createRun();
		runDom.setBold(true);
		runDom.setFontSize(14);
		runDom.setText("Statistiques à l'extérieur de l'équipe " + team2);

		if (nbMatchsJouesExterieur != 0L) {
			pourcentageVictoiresExterieur = (nbMatchsGagnesExterieur.floatValue() / nbMatchsJouesExterieur.floatValue())
					* 100;
			pourcentageNulsExterieur = (nbMatchsNulsExterieur.floatValue() / nbMatchsJouesExterieur.floatValue()) * 100;
			pourcentageDefaitesExterieur = (nbMatchsPerdusExterieur.floatValue() / nbMatchsJouesExterieur.floatValue())
					* 100;

			document.createParagraph().createRun()
					.setText("Pourcentage de victoires à l'extérieur : " + pourcentageVictoiresExterieur + "%");
			document.createParagraph().createRun()
					.setText("Pourcentage de matchs nuls à l'extérieur : " + pourcentageNulsExterieur + "%");
			document.createParagraph().createRun()
					.setText("Pourcentage de défaites à l'extérieur : " + pourcentageDefaitesExterieur + "%");
		} else {
			document.createParagraph().createRun().setText("L'équipe " + team2
					+ " va jouer son premier match de la saison à l'extérieur, aucune donnée n'est disponible pour le moment.");
		}

		if (serieVExterieur > 0L)
			document.createParagraph().createRun().setText("Série de victoires de l'équipe " + team2
					+ " sur les 5 derniers matchs à l'extérieur : " + serieVExterieur + " V");
		if (serieNExterieur > 0L)
			document.createParagraph().createRun().setText("Série de matchs nuls de l'équipe " + team2
					+ " sur les 5 derniers matchs à l'extérieur : " + serieNExterieur + " N");
		if (serieDExterieur > 0L)
			document.createParagraph().createRun().setText("Série de défaites de l'équipe " + team2
					+ " sur les 5 derniers matchs à l'extérieur : " + serieDExterieur + " D");

		Integer nbDefaitesContreClassementInf;
		Integer nbDefaitesContreClassementInfExterieur;
		Integer nbMatchsJouesContreClassementInf;
		Integer nbMatchsJouesContreClassementInfExterieur;
		Integer nbMatchsNulsContreClassementInf;
		Integer nbMatchsNulsContreClassementInfExterieur;
		Integer nbVictoiresContreClassementInf;
		Integer nbVictoiresContreClassementInfExterieur;

		Float pourcentageDefaitesContreClassementInf = null;
		Float pourcentageDefaitesContreClassementInfExterieur = null;
		Float pourcentageNulsContreClassementInf = null;
		Float pourcentageNulsContreClassementInfExterieur = null;
		Float pourcentageVictoiresContreClassementInf = null;
		Float pourcentageVictoiresContreClassementInfExterieur = null;

		Integer nbDefaitesContreClassementSup;
		Integer nbDefaitesContreClassementSupExterieur;
		Integer nbMatchsJouesContreClassementSup;
		Integer nbMatchsJouesContreClassementSupExterieur;
		Integer nbMatchsNulsContreClassementSup;
		Integer nbMatchsNulsContreClassementSupExterieur;
		Integer nbVictoiresContreClassementSup;
		Integer nbVictoiresContreClassementSupExterieur;

		Float pourcentageDefaitesContreClassementSup = null;
		Float pourcentageDefaitesContreClassementSupExterieur = null;
		Float pourcentageNulsContreClassementSup = null;
		Float pourcentageNulsContreClassementSupExterieur = null;
		Float pourcentageVictoiresContreClassementSup = null;
		Float pourcentageVictoiresContreClassementSupExterieur = null;

		Integer nbDefaitesContreImportant;
		Integer nbDefaitesContreImportantExterieur;
		Integer nbMatchsJouesContreImportant;
		Integer nbMatchsJouesContreImportantExterieur;
		Integer nbMatchsNulsContreImportant;
		Integer nbMatchsNulsContreImportantExterieur;
		Integer nbVictoiresContreImportant;
		Integer nbVictoiresContreImportantExterieur;

		Float pourcentageDefaitesContreImportant = null;
		Float pourcentageDefaitesContreImportantExterieur = null;
		Float pourcentageNulsContreImportant = null;
		Float pourcentageNulsContreImportantExterieur = null;
		Float pourcentageVictoiresContreImportant = null;
		Float pourcentageVictoiresContreImportantExterieur = null;

		Integer nbDefaitesContreBanal;
		Integer nbDefaitesContreBanalExterieur;
		Integer nbMatchsJouesContreBanal;
		Integer nbMatchsJouesContreBanalExterieur;
		Integer nbMatchsNulsContreBanal;
		Integer nbMatchsNulsContreBanalExterieur;
		Integer nbVictoiresContreBanal;
		Integer nbVictoiresContreBanalExterieur;

		Float pourcentageDefaitesContreBanal = null;
		Float pourcentageDefaitesContreBanalExterieur = null;
		Float pourcentageNulsContreBanal = null;
		Float pourcentageNulsContreBanalExterieur = null;
		Float pourcentageVictoiresContreBanal = null;
		Float pourcentageVictoiresContreBanalExterieur = null;

		if (homeTeamHasBestStanding) {

			nbDefaitesContreClassementSup = team.getNbLossesAgainstStandingSuperior();
			nbDefaitesContreClassementSupExterieur = team.getNbLossesAgainstStandingSuperiorAway();
			nbMatchsJouesContreClassementSup = team.getNbMatchesPlayedAgainstStandingSuperior();
			nbMatchsJouesContreClassementSupExterieur = team.getNbMatchesPlayedAgainstStandingSuperiorAway();
			nbMatchsNulsContreClassementSup = team.getNbDrawsAgainstStandingSuperior();
			nbMatchsNulsContreClassementSupExterieur = team.getNbDrawsAgainstStandingSuperiorAway();
			nbVictoiresContreClassementSup = team.getNbWinsAgainstStandingSuperior();
			nbVictoiresContreClassementSupExterieur = team.getNbWinsAgainstStandingSuperiorAway();

			XWPFRun runClassementSup = document.createParagraph().createRun();
			runClassementSup.setBold(true);
			runClassementSup.setFontSize(14);
			runClassementSup.setText("Statistiques de l'équipe " + team2 + " contre une équipe mieux classée");

			if (nbMatchsJouesContreClassementSup != 0L) {
				pourcentageVictoiresContreClassementSup = (nbVictoiresContreClassementSup.floatValue()
						/ nbMatchsJouesContreClassementSup.floatValue()) * 100;
				pourcentageNulsContreClassementSup = (nbMatchsNulsContreClassementSup.floatValue()
						/ nbMatchsJouesContreClassementSup.floatValue()) * 100;
				pourcentageDefaitesContreClassementSup = (nbDefaitesContreClassementSup.floatValue()
						/ nbMatchsJouesContreClassementSup.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires contre une équipe mieux classée : "
								+ pourcentageVictoiresContreClassementSup + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls contre une équipe mieux classée : "
								+ pourcentageNulsContreClassementSup + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites contre une équipe mieux classée : "
								+ pourcentageDefaitesContreClassementSup + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team2
						+ " va jouer son premier match de la saison contre une équipe mieux classée qu'elle, aucune donnée n'est disponible pour le moment.");
			}

			XWPFRun runClassementSupExterieur = document.createParagraph().createRun();
			runClassementSupExterieur.setBold(true);
			runClassementSupExterieur.setFontSize(14);
			runClassementSupExterieur
					.setText("Statistiques de l'équipe " + team2 + " à l'extérieur contre une équipe mieux classée");

			if (nbMatchsJouesContreClassementSupExterieur != 0L) {
				pourcentageVictoiresContreClassementSupExterieur = (nbVictoiresContreClassementSupExterieur.floatValue()
						/ nbMatchsJouesContreClassementSupExterieur.floatValue()) * 100;
				pourcentageNulsContreClassementSupExterieur = (nbMatchsNulsContreClassementSupExterieur.floatValue()
						/ nbMatchsJouesContreClassementSupExterieur.floatValue()) * 100;
				pourcentageDefaitesContreClassementSupExterieur = (nbDefaitesContreClassementSupExterieur.floatValue()
						/ nbMatchsJouesContreClassementSupExterieur.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires à l'extérieur contre une équipe mieux classée : "
								+ pourcentageVictoiresContreClassementSupExterieur + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls à l'extérieur contre une équipe mieux classée : "
								+ pourcentageNulsContreClassementSupExterieur + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites à l'extérieur contre une équipe mieux classée : "
								+ pourcentageDefaitesContreClassementSupExterieur + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team2
						+ " va jouer son premier match de la saison à l'extérieur contre une équipe mieux classée qu'elle, aucune donnée n'est disponible pour le moment.");
			}

		} else {

			nbDefaitesContreClassementInf = team.getNbLossesAgainstStandingInferior();
			nbDefaitesContreClassementInfExterieur = team.getNbLossesAgainstStandingInferiorAway();
			nbMatchsJouesContreClassementInf = team.getNbMatchesPlayedAgainstStandingInferior();
			nbMatchsJouesContreClassementInfExterieur = team.getNbMatchesPlayedAgainstStandingInferiorAway();
			nbMatchsNulsContreClassementInf = team.getNbDrawsAgainstStandingInferior();
			nbMatchsNulsContreClassementInfExterieur = team.getNbDrawsAgainstStandingInferiorAway();
			nbVictoiresContreClassementInf = team.getNbWinsAgainstStandingInferior();
			nbVictoiresContreClassementInfExterieur = team.getNbWinsAgainstStandingInferiorAway();

			XWPFRun runClassementInf = document.createParagraph().createRun();
			runClassementInf.setBold(true);
			runClassementInf.setFontSize(14);
			runClassementInf.setText("Statistiques de l'équipe " + team2 + " contre une équipe moins bien classée");

			if (nbMatchsJouesContreClassementInf != 0L) {
				pourcentageVictoiresContreClassementInf = (nbVictoiresContreClassementInf.floatValue()
						/ nbMatchsJouesContreClassementInf.floatValue()) * 100;
				pourcentageNulsContreClassementInf = (nbMatchsNulsContreClassementInf.floatValue()
						/ nbMatchsJouesContreClassementInf.floatValue()) * 100;
				pourcentageDefaitesContreClassementInf = (nbDefaitesContreClassementInf.floatValue()
						/ nbMatchsJouesContreClassementInf.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires contre une équipe moins bien classée : "
								+ pourcentageVictoiresContreClassementInf + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls contre une équipe moins bien classée : "
								+ pourcentageNulsContreClassementInf + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites contre une équipe moins bien classée : "
								+ pourcentageDefaitesContreClassementInf + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team2
						+ " va jouer son premier match de la saison contre une équipe moins bien classée qu'elle, aucune donnée n'est disponible pour le moment.");
			}

			XWPFRun runClassementInfExt = document.createParagraph().createRun();
			runClassementInfExt.setBold(true);
			runClassementInfExt.setFontSize(14);
			runClassementInfExt.setText(
					"Statistiques de l'équipe " + team2 + " à l'extérieur contre une équipe moins bien classée");

			if (nbMatchsJouesContreClassementInfExterieur != 0L) {
				pourcentageVictoiresContreClassementInfExterieur = (nbVictoiresContreClassementInfExterieur.floatValue()
						/ nbMatchsJouesContreClassementInfExterieur.floatValue()) * 100;
				pourcentageNulsContreClassementInfExterieur = (nbMatchsNulsContreClassementInfExterieur.floatValue()
						/ nbMatchsJouesContreClassementInfExterieur.floatValue()) * 100;
				pourcentageDefaitesContreClassementInfExterieur = (nbDefaitesContreClassementInfExterieur.floatValue()
						/ nbMatchsJouesContreClassementInfExterieur.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires à l'extérieur contre une équipe moins bien classée : "
								+ pourcentageVictoiresContreClassementInfExterieur + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls à l'extérieur contre une équipe moins bien classée : "
								+ pourcentageNulsContreClassementInfExterieur + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites à l'extérieur contre une équipe moins bien classée : "
								+ pourcentageDefaitesContreClassementInfExterieur + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team2
						+ " va jouer son premier match de la saison à l'extérieur contre une équipe moins bien classée qu'elle, aucune donnée n'est disponible pour le moment.");
			}

		}

		if (isImportantForTeam2) {

			nbDefaitesContreImportant = team.getNbLossesAgainstImportantOpponent();
			nbDefaitesContreImportantExterieur = team.getNbLossesAgainstImportantOpponentAway();
			nbMatchsJouesContreImportant = team.getNbMatchesPlayedAgainstImportantOpponent();
			nbMatchsJouesContreImportantExterieur = team.getNbMatchesPlayedAgainstImportantOpponentAway();
			nbMatchsNulsContreImportant = team.getNbDrawsAgainstImportantOpponent();
			nbMatchsNulsContreImportantExterieur = team.getNbDrawsAgainstImportantOpponentAway();
			nbVictoiresContreImportant = team.getNbWinsAgainstImportantOpponent();
			nbVictoiresContreImportantExterieur = team.getNbWinsAgainstImportantOpponentAway();

			XWPFRun runImportant = document.createParagraph().createRun();
			runImportant.setBold(true);
			runImportant.setFontSize(14);
			runImportant.setText("Statistiques de l'équipe " + team2 + " dans un match important");

			if (nbMatchsJouesContreImportant != 0L) {
				pourcentageVictoiresContreImportant = (nbVictoiresContreImportant.floatValue()
						/ nbMatchsJouesContreImportant.floatValue()) * 100;
				pourcentageNulsContreImportant = (nbMatchsNulsContreImportant.floatValue()
						/ nbMatchsJouesContreImportant.floatValue()) * 100;
				pourcentageDefaitesContreImportant = (nbDefaitesContreImportant.floatValue()
						/ nbMatchsJouesContreImportant.floatValue()) * 100;

				document.createParagraph().createRun().setText("Pourcentage de victoires dans un match important : "
						+ pourcentageVictoiresContreImportant + "%");
				document.createParagraph().createRun().setText(
						"Pourcentage de matchs nuls dans un match important : " + pourcentageNulsContreImportant + "%");
				document.createParagraph().createRun().setText("Pourcentage de défaites dans un match important : "
						+ pourcentageDefaitesContreImportant + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team2
						+ " va jouer son premier match important de la saison, aucune donnée n'est disponible pour le moment.");
			}

			XWPFRun runImportantExterieur = document.createParagraph().createRun();
			runImportantExterieur.setBold(true);
			runImportantExterieur.setFontSize(14);
			runImportantExterieur
					.setText("Statistiques de l'équipe " + team2 + " à l'extérieur dans un match important");

			if (nbMatchsJouesContreImportantExterieur != 0L) {
				pourcentageVictoiresContreImportantExterieur = (nbVictoiresContreImportantExterieur.floatValue()
						/ nbMatchsJouesContreImportantExterieur.floatValue()) * 100;
				pourcentageNulsContreImportantExterieur = (nbMatchsNulsContreImportantExterieur.floatValue()
						/ nbMatchsJouesContreImportantExterieur.floatValue()) * 100;
				pourcentageDefaitesContreImportantExterieur = (nbDefaitesContreImportantExterieur.floatValue()
						/ nbMatchsJouesContreImportantExterieur.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires à l'extérieur dans un match important : "
								+ pourcentageVictoiresContreImportantExterieur + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls à l'extérieur dans un match important : "
								+ pourcentageNulsContreImportantExterieur + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites à l'extérieur dans un match important : "
								+ pourcentageDefaitesContreImportantExterieur + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team2
						+ " va jouer son premier match important de la saison à l'extérieur, aucune donnée n'est disponible pour le moment.");
			}

		} else {

			nbDefaitesContreBanal = team.getNbLossesAgainstNormalOpponent();
			nbDefaitesContreBanalExterieur = team.getNbLossesAgainstNormalOpponentAway();
			nbMatchsJouesContreBanal = team.getNbMatchesPlayedAgainstNormalOpponent();
			nbMatchsJouesContreBanalExterieur = team.getNbMatchesPlayedAgainstNormalOpponentAway();
			nbMatchsNulsContreBanal = team.getNbDrawsAgainstNormalOpponent();
			nbMatchsNulsContreBanalExterieur = team.getNbDrawsAgainstNormalOpponentAway();
			nbVictoiresContreBanal = team.getNbWinsAgainstNormalOpponent();
			nbVictoiresContreBanalExterieur = team.getNbWinsAgainstNormalOpponentAway();

			XWPFRun runBanal = document.createParagraph().createRun();
			runBanal.setBold(true);
			runBanal.setFontSize(14);
			runBanal.setText(
					"Statistiques de l'équipe " + team2 + " dans un match qui n'est pas considéré comme important");

			if (nbMatchsJouesContreBanal != 0L) {
				pourcentageVictoiresContreBanal = (nbVictoiresContreBanal.floatValue()
						/ nbMatchsJouesContreBanal.floatValue()) * 100;
				pourcentageNulsContreBanal = (nbMatchsNulsContreBanal.floatValue()
						/ nbMatchsJouesContreBanal.floatValue()) * 100;
				pourcentageDefaitesContreBanal = (nbDefaitesContreBanal.floatValue()
						/ nbMatchsJouesContreBanal.floatValue()) * 100;

				document.createParagraph().createRun().setText(
						"Pourcentage de victoires dans un match classique : " + pourcentageVictoiresContreBanal + "%");
				document.createParagraph().createRun().setText(
						"Pourcentage de matchs nuls dans un match classique : " + pourcentageNulsContreBanal + "%");
				document.createParagraph().createRun().setText(
						"Pourcentage de défaites dans un match classique : " + pourcentageDefaitesContreBanal + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team2
						+ " va jouer son premier match classique de la saison, aucune donnée n'est disponible pour le moment.");
			}

			XWPFRun runBanalExterieur = document.createParagraph().createRun();
			runBanalExterieur.setBold(true);
			runBanalExterieur.setFontSize(14);
			runBanalExterieur.setText("Statistiques de l'équipe " + team2
					+ " à l'extérieur dans un match qui n'est pas considéré comme important");

			if (nbMatchsJouesContreBanalExterieur != 0L) {
				pourcentageVictoiresContreBanalExterieur = (nbVictoiresContreBanalExterieur.floatValue()
						/ nbMatchsJouesContreBanalExterieur.floatValue()) * 100;
				pourcentageNulsContreBanalExterieur = (nbMatchsNulsContreBanalExterieur.floatValue()
						/ nbMatchsJouesContreBanalExterieur.floatValue()) * 100;
				pourcentageDefaitesContreBanalExterieur = (nbDefaitesContreBanalExterieur.floatValue()
						/ nbMatchsJouesContreBanalExterieur.floatValue()) * 100;

				document.createParagraph().createRun()
						.setText("Pourcentage de victoires à l'extérieur dans un match classique : "
								+ pourcentageVictoiresContreBanalExterieur + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de matchs nuls à l'extérieur dans un match classique : "
								+ pourcentageNulsContreBanalExterieur + "%");
				document.createParagraph().createRun()
						.setText("Pourcentage de défaites à l'extérieur dans un match classique : "
								+ pourcentageDefaitesContreBanalExterieur + "%");
			} else {
				document.createParagraph().createRun().setText("L'équipe " + team2
						+ " va jouer son premier match classique de la saison à l'extérieur, aucune donnée n'est disponible pour le moment.");
			}

		}

	}

	private static TreeMap<String, String> getTreeMapStatsAwayTeamExactGoalsNumber(Statistic statistiques, String match,
			String e2) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] score = match.split("-");
//		String e2 = score[1];

		Float E2marque0 = statistiques.getAwayTeamScoredExactly0GoalPercentage();
		Float E2marque1 = statistiques.getAwayTeamScoredExactly1GoalPercentage();
		Float E2marque2 = statistiques.getAwayTeamScoredExactly2GoalsPercentage();
		Float E2marque3 = statistiques.getAwayTeamScoredExactly3GoalsPercentage();
		Float E2marque4 = statistiques.getAwayTeamScoredExactly4GoalsPercentage();
		Float E2marque5 = statistiques.getAwayTeamScoredExactly5GoalsPercentage();

		if (E2marque0 != 0F)
			stats.put("Pourcentage de matchs avec exactement 0 but marqué pour l'équipe " + e2, E2marque0 + "%");
		if (E2marque1 != 0F)
			stats.put("Pourcentage de matchs avec exactement 1 but marqué pour l'équipe " + e2, E2marque1 + "%");
		if (E2marque2 != 0F)
			stats.put("Pourcentage de matchs avec exactement 2 buts marqués pour l'équipe " + e2, E2marque2 + "%");
		if (E2marque3 != 0F)
			stats.put("Pourcentage de matchs avec exactement 3 buts marqués pour l'équipe " + e2, E2marque3 + "%");
		if (E2marque4 != 0F)
			stats.put("Pourcentage de matchs avec exactement 4 buts marqués pour l'équipe " + e2, E2marque4 + "%");
		if (E2marque5 != 0F)
			stats.put("Pourcentage de matchs avec exactement 5 buts marqués pour l'équipe " + e2, E2marque5 + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;
	}

	private static TreeMap<String, String> getTreeMapStatsHomeTeamExactGoalsNumber(Statistic statistiques, String match,
			String e1) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] score = match.split("-");
//		String e1 = score[0];

		Float E1marque0 = statistiques.getHomeTeamScoredExactly0GoalPercentage();
		Float E1marque1 = statistiques.getHomeTeamScoredExactly1GoalPercentage();
		Float E1marque2 = statistiques.getHomeTeamScoredExactly2GoalsPercentage();
		Float E1marque3 = statistiques.getHomeTeamScoredExactly3GoalsPercentage();
		Float E1marque4 = statistiques.getHomeTeamScoredExactly4GoalsPercentage();
		Float E1marque5 = statistiques.getHomeTeamScoredExactly5GoalsPercentage();

		if (E1marque0 != 0F)
			stats.put("Pourcentage de matchs avec exactement 0 but marqué pour l'équipe " + e1, E1marque0 + "%");
		if (E1marque1 != 0F)
			stats.put("Pourcentage de matchs avec exactement 1 but marqué pour l'équipe " + e1, E1marque1 + "%");
		if (E1marque2 != 0F)
			stats.put("Pourcentage de matchs avec exactement 2 buts marqués pour l'équipe " + e1, E1marque2 + "%");
		if (E1marque3 != 0F)
			stats.put("Pourcentage de matchs avec exactement 3 buts marqués pour l'équipe " + e1, E1marque3 + "%");
		if (E1marque4 != 0F)
			stats.put("Pourcentage de matchs avec exactement 4 buts marqués pour l'équipe " + e1, E1marque4 + "%");
		if (E1marque5 != 0F)
			stats.put("Pourcentage de matchs avec exactement 5 buts marqués pour l'équipe " + e1, E1marque5 + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;
	}

	private static TreeMap<String, String> getTreeMapStatsEcartButsE2(Statistic statistiques, String match, String e2) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] score = match.split("-");
//		String e2 = score[1];

		Float E2par1 = statistiques.getAwayTeamWinByExactly1GoalPercentage();
		Float E2par2 = statistiques.getAwayTeamWinByExactly2GoalsPercentage();
		Float E2par3 = statistiques.getAwayTeamWinByExactly3GoalsPercentage();
		Float E2par4 = statistiques.getAwayTeamWinByExactly4GoalsPercentage();
		Float E2par5 = statistiques.getAwayTeamWinByExactly5GoalsPercentage();
		Float E2par6 = statistiques.getAwayTeamWinByExactly6GoalsPercentage();

		if (E2par1 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 1 but d'écart par l'équipe " + e2, E2par1 + "%");
		if (E2par2 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 2 buts d'écart par l'équipe " + e2, E2par2 + "%");
		if (E2par3 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 3 buts d'écart par l'équipe " + e2, E2par3 + "%");
		if (E2par4 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 4 buts d'écart par l'équipe " + e2, E2par4 + "%");
		if (E2par5 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 5 buts d'écart par l'équipe " + e2, E2par5 + "%");
		if (E2par6 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 6 buts d'écart par l'équipe " + e2, E2par6 + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;
	}

	private static TreeMap<String, String> getTreeMapStatsEcartButsE1(Statistic statistiques, String match, String e1) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] score = match.split("-");
//		String e1 = score[0];

		Float E1par1 = statistiques.getHomeTeamWinByExactly1GoalPercentage();
		Float E1par2 = statistiques.getHomeTeamWinByExactly2GoalsPercentage();
		Float E1par3 = statistiques.getHomeTeamWinByExactly3GoalsPercentage();
		Float E1par4 = statistiques.getHomeTeamWinByExactly4GoalsPercentage();
		Float E1par5 = statistiques.getHomeTeamWinByExactly5GoalsPercentage();
		Float E1par6 = statistiques.getHomeTeamWinByExactly6GoalsPercentage();

		if (E1par1 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 1 but d'écart par l'équipe " + e1, E1par1 + "%");
		if (E1par2 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 2 buts d'écart par l'équipe " + e1, E1par2 + "%");
		if (E1par3 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 3 buts d'écart par l'équipe " + e1, E1par3 + "%");
		if (E1par4 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 4 buts d'écart par l'équipe " + e1, E1par4 + "%");
		if (E1par5 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 5 buts d'écart par l'équipe " + e1, E1par5 + "%");
		if (E1par6 != 0F)
			stats.put("Pourcentage de matchs remporté par exactement 6 buts d'écart par l'équipe " + e1, E1par6 + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;

	}

	private static TreeMap<String, String> getTreeMapStatsNombreButsE2(Statistic statistiques, String match,
			String e2) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] score = match.split("-");
//		String e2 = score[1];

		Float E2plus05 = statistiques.getAwayTeamScoredMoreThan05GoalPercentage();
		Float E2plus15 = statistiques.getAwayTeamScoredMoreThan15GoalPercentage();
		Float E2plus25 = statistiques.getAwayTeamScoredMoreThan25GoalsPercentage();
		Float E2plus35 = statistiques.getAwayTeamScoredMoreThan35GoalsPercentage();
		Float E2plus45 = statistiques.getAwayTeamScoredMoreThan45GoalsPercentage();

		Float E2moins05 = statistiques.getAwayTeamScoredLessThan05GoalPercentage();
		Float E2moins15 = statistiques.getAwayTeamScoredLessThan15GoalPercentage();
		Float E2moins25 = statistiques.getAwayTeamScoredLessThan25GoalsPercentage();
		Float E2moins35 = statistiques.getAwayTeamScoredLessThan35GoalsPercentage();
		Float E2moins45 = statistiques.getAwayTeamScoredLessThan45GoalsPercentage();

		if (E2plus05 != 0F)
			stats.put("Pourcentage de matchs avec plus de 0,5 buts pour l'équipe " + e2, E2plus05 + "%");
		if (E2plus15 != 0F)
			stats.put("Pourcentage de matchs avec plus de 1,5 buts pour l'équipe " + e2, E2plus15 + "%");
		if (E2plus25 != 0F)
			stats.put("Pourcentage de matchs avec plus de 2,5 buts pour l'équipe " + e2, E2plus25 + "%");
		if (E2plus35 != 0F)
			stats.put("Pourcentage de matchs avec plus de 3,5 buts pour l'équipe " + e2, E2plus35 + "%");
		if (E2plus45 != 0F)
			stats.put("Pourcentage de matchs avec plus de 4,5 buts pour l'équipe " + e2, E2plus45 + "%");
		if (E2moins05 != 0F)
			stats.put("Pourcentage de matchs avec moins de 0,5 buts pour l'équipe " + e2, E2moins05 + "%");
		if (E2moins15 != 0F)
			stats.put("Pourcentage de matchs avec moins de 1,5 buts pour l'équipe " + e2, E2moins15 + "%");
		if (E2moins25 != 0F)
			stats.put("Pourcentage de matchs avec moins de 2,5 buts pour l'équipe " + e2, E2moins25 + "%");
		if (E2moins35 != 0F)
			stats.put("Pourcentage de matchs avec moins de 3,5 buts pour l'équipe " + e2, E2moins35 + "%");
		if (E2moins45 != 0F)
			stats.put("Pourcentage de matchs avec moins de 4,5 buts pour l'équipe " + e2, E2moins45 + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;
	}

	private static TreeMap<String, String> getTreeMapStatsNombreButsE1(Statistic statistiques, String match,
			String e1) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] score = match.split("-");
//		String e1 = score[0];

		Float E1plus05 = statistiques.getHomeTeamScoredMoreThan05GoalPercentage();
		Float E1plus15 = statistiques.getHomeTeamScoredMoreThan15GoalPercentage();
		Float E1plus25 = statistiques.getHomeTeamScoredMoreThan25GoalsPercentage();
		Float E1plus35 = statistiques.getHomeTeamScoredMoreThan35GoalsPercentage();
		Float E1plus45 = statistiques.getHomeTeamScoredMoreThan45GoalsPercentage();

		Float E1moins05 = statistiques.getHomeTeamScoredLessThan05GoalPercentage();
		Float E1moins15 = statistiques.getHomeTeamScoredLessThan15GoalPercentage();
		Float E1moins25 = statistiques.getHomeTeamScoredLessThan25GoalsPercentage();
		Float E1moins35 = statistiques.getHomeTeamScoredLessThan35GoalsPercentage();
		Float E1moins45 = statistiques.getHomeTeamScoredLessThan45GoalsPercentage();

		if (E1plus05 != 0F)
			stats.put("Pourcentage de matchs avec plus de 0,5 buts pour l'équipe " + e1, E1plus05 + "%");
		if (E1plus15 != 0F)
			stats.put("Pourcentage de matchs avec plus de 1,5 buts pour l'équipe " + e1, E1plus15 + "%");
		if (E1plus25 != 0F)
			stats.put("Pourcentage de matchs avec plus de 2,5 buts pour l'équipe " + e1, E1plus25 + "%");
		if (E1plus35 != 0F)
			stats.put("Pourcentage de matchs avec plus de 3,5 buts pour l'équipe " + e1, E1plus35 + "%");
		if (E1plus45 != 0F)
			stats.put("Pourcentage de matchs avec plus de 4,5 buts pour l'équipe " + e1, E1plus45 + "%");
		if (E1moins05 != 0F)
			stats.put("Pourcentage de matchs avec moins de 0,5 buts pour l'équipe " + e1, E1moins05 + "%");
		if (E1moins15 != 0F)
			stats.put("Pourcentage de matchs avec moins de 1,5 buts pour l'équipe " + e1, E1moins15 + "%");
		if (E1moins25 != 0F)
			stats.put("Pourcentage de matchs avec moins de 2,5 buts pour l'équipe " + e1, E1moins25 + "%");
		if (E1moins35 != 0F)
			stats.put("Pourcentage de matchs avec moins de 3,5 buts pour l'équipe " + e1, E1moins35 + "%");
		if (E1moins45 != 0F)
			stats.put("Pourcentage de matchs avec moins de 4,5 buts pour l'équipe " + e1, E1moins45 + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;

	}

	private static TreeMap<String, String> getTreeMapStatsNombreButsMatch(Statistic statistiques, String match) {

		Map<String, String> stats = new HashMap<String, String>();

		Float plus05 = statistiques.getMoreThan05GoalPercentage();
		Float plus15 = statistiques.getMoreThan15GoalPercentage();
		Float plus25 = statistiques.getMoreThan25GoalsPercentage();
		Float plus35 = statistiques.getMoreThan35GoalsPercentage();
		Float plus45 = statistiques.getMoreThan45GoalsPercentage();

		Float moins05 = statistiques.getLessThan05GoalPercentage();
		Float moins15 = statistiques.getLessThan15GoalPercentage();
		Float moins25 = statistiques.getLessThan25GoalsPercentage();
		Float moins35 = statistiques.getLessThan35GoalsPercentage();
		Float moins45 = statistiques.getLessThan45GoalsPercentage();

		if (plus05 != 0F)
			stats.put("Pourcentage de matchs avec plus de 0,5 buts", plus05 + "%");
		if (plus15 != 0F)
			stats.put("Pourcentage de matchs avec plus de 1,5 buts", plus15 + "%");
		if (plus25 != 0F)
			stats.put("Pourcentage de matchs avec plus de 2,5 buts", plus25 + "%");
		if (plus35 != 0F)
			stats.put("Pourcentage de matchs avec plus de 3,5 buts", plus35 + "%");
		if (plus45 != 0F)
			stats.put("Pourcentage de matchs avec plus de 4,5 buts", plus45 + "%");
		if (moins05 != 0F)
			stats.put("Pourcentage de matchs avec moins de 0,5 buts", moins05 + "%");
		if (moins15 != 0F)
			stats.put("Pourcentage de matchs avec moins de 1,5 buts", moins15 + "%");
		if (moins25 != 0F)
			stats.put("Pourcentage de matchs avec moins de 2,5 buts", moins25 + "%");
		if (moins35 != 0F)
			stats.put("Pourcentage de matchs avec moins de 3,5 buts", moins35 + "%");
		if (moins45 != 0F)
			stats.put("Pourcentage de matchs avec moins de 4,5 buts", moins45 + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;
	}

	private static Map<String, String> getTreeMapStatsLDEM(Statistic statistiques, String match, String homeTeam,
			String awayTeam) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] teams = match.split("-");
//		String homeTeam = teams[0];
//		String awayTeam = teams[1];
		Float pourcentageLDEM = statistiques.getBothTeamsToScorePercentage();
		int classementDomicile = 0;
		int classementExterieur = 0;
		try {
			classementDomicile = DatabaseConnection.getTeamByNickname(homeTeam).getHomeStanding();
			classementExterieur = DatabaseConnection.getTeamByNickname(awayTeam).getAwayStanding();
		} catch (NullTeamException e) {
			Ligue1Utils.reportError("Erreur à la récupération du classement d'une équipe : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		stats.put("Pourcentage de matchs où les deux équipes marquent", pourcentageLDEM + "%");
		stats.put("Classement à domicile de l'équipe " + homeTeam, String.valueOf(classementDomicile));
		stats.put("Classement à l'extérieur de l'équipe " + awayTeam, String.valueOf(classementExterieur));

		return stats;
	}

	private static TreeMap<String, String> getTreeMapStatsVictoires(Statistic statistiques, String match, String e1,
			String e2) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] score = match.split("-");
//		String e1 = score[0];
//		String e2 = score[1];

		Float pourcentageVDOM = 0F;
		Float pourcentageNul = 0F;
		Float pourcentageVEXT = 0F;

		pourcentageVDOM = statistiques.getHomeTeamWinPercentage();
		pourcentageNul = statistiques.getDrawPercentage();
		pourcentageVEXT = statistiques.getAwayTeamWinPercentage();

		stats.put("Pourcentage de victoires de l'équipe " + e2, pourcentageVEXT + "%");
		stats.put("Pourcentage de matchs nuls", pourcentageNul + "%");
		stats.put("Pourcentage de victoires de l'équipe " + e1, pourcentageVDOM + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;
	}

	private static TreeMap<String, String> getTreeMapStatsPronostics(Statistic statistiques, String match, String e1,
			String e2, Configuration config)
			throws NullTeamException, NullMatchException, InvalidNumberToConvertFromBooleanException {

//		String[] score = match.split("-");
//		String e1 = score[0];
//		String e2 = score[1];

		Map<String, String> stats = new HashMap<String, String>();

		Float totalPointsE1 = getProbabilityPointsForHomeTeam(statistiques, match, e1, e2, config);
		Float totalPointsNul = getDrawProbabilityPoints(statistiques, match, e1, e2, config);
		Float totalPointsE2 = getProbabilityPointsForAwayTeam(statistiques, match, e1, e2, config);

		Float total = totalPointsE1 + totalPointsE2 + totalPointsNul;

		Float probabiliteVictoireEquipeDomicile = 0F;
		Float probabiliteMatchNul = 0F;
		Float probabiliteVictoireEquipeExterieur = 0F;

		if (total != 0F) {
			probabiliteVictoireEquipeDomicile = (totalPointsE1 / total) * 100;
			probabiliteMatchNul = (totalPointsNul / total) * 100;
			probabiliteVictoireEquipeExterieur = (totalPointsE2 / total) * 100;
		}

		stats.put("Probabilité de victoire de l'équipe " + e1, probabiliteVictoireEquipeDomicile + "%");
		stats.put("Pourcentage de matchs nuls", probabiliteMatchNul + "%");
		stats.put("Pourcentage de victoires de l'équipe " + e2, probabiliteVictoireEquipeExterieur + "%");

		PercentageComparator comparator = new PercentageComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;
	}

	private static Float getProbabilityPointsForAwayTeam(Statistic statistiques, String match, String e1, String e2,
			Configuration config)
			throws NullTeamException, NullMatchException, InvalidNumberToConvertFromBooleanException {

		Float totalPoints = 0F;

//		String[] score = match.split("-");
//		String e1 = score[0];
//		String e2 = score[1];
		Team homeTeam = DatabaseConnection.getTeamByNickname(e1);
		Team awayTeam = DatabaseConnection.getTeamByNickname(e2);

		Float pourcentageVEXTConfrontations = statistiques.getAwayTeamWinPercentage();

		int classementE1 = homeTeam.getStanding();
		int classementE2 = awayTeam.getStanding();
		int nbPointsRapportesParClassement = 0;
		if (Ligue1Utils.isAllowed(classementE2) && Ligue1Utils.isAllowed(classementE1)) {
			// e2 mieux classée que e1
			if (classementE2 < classementE1) {
				nbPointsRapportesParClassement = (classementE1 - classementE2) * 10;
			}
		}

		int classementE1Domicile = homeTeam.getHomeStanding();
		int classementE2Exterieur = awayTeam.getAwayStanding();
		int nbPointsRapportesParClassementsDomEtExt = 0;
		if (Ligue1Utils.isAllowed(classementE2Exterieur) && Ligue1Utils.isAllowed(classementE1Domicile)) {
			// e2 mieux classée que e1
			if (classementE2Exterieur < classementE1Domicile) {
				nbPointsRapportesParClassementsDomEtExt = (classementE1Domicile - classementE2Exterieur) * 10;
			}
		}

		Float nbPointsForme = 0F;
		String formeE1 = getFormeActuelle(e1);
		String formeE2 = getFormeActuelle(e2);
		if (!Ligue1Utils.isEmpty(formeE1) && !Ligue1Utils.isEmpty(formeE2)) {
			if (formeE2.equals("V")) {
				Integer serieVe2 = awayTeam.getWinningSerie();
				if (formeE1.equals("V")) {
					Integer serieVe1 = homeTeam.getWinningSerie();
					if (serieVe2 > serieVe1) {
						nbPointsForme = (float) (serieVe2 - serieVe1) * 5;
					}
				} else {
					if (formeE1.equals("N")) {
						nbPointsForme = (float) serieVe2 * 10;
					} else {
						nbPointsForme = (float) serieVe2 * 15;
					}
				}
			}
		}

		Float nbPointsFormeDomExt = 0F;
		String formeE1Dom = getFormeActuelleDomicile(e1);
		String formeE2Ext = getFormeActuelleExterieur(e2);
		if (!Ligue1Utils.isEmpty(formeE1Dom) && !Ligue1Utils.isEmpty(formeE2Ext)) {
			if (formeE2Ext.equals("V")) {
				Integer serieVe2 = awayTeam.getAwayWinningSerie();
				if (formeE1Dom.equals("V")) {
					Integer serieVe1 = homeTeam.getHomeWinningSerie();
					if (serieVe2 > serieVe1) {
						nbPointsFormeDomExt = (float) (serieVe2 - serieVe1) * 5;
					}
				} else {
					if (formeE1.equals("N")) {
						nbPointsFormeDomExt = (float) serieVe2 * 10;
					} else {
						nbPointsFormeDomExt = (float) serieVe2 * 15;
					}
				}
			}
		}

		Float forme5DerniersMatchsE2 = getFormePointsForTeam(e2);
		Float forme5DerniersMatchsE1 = getFormePointsForTeam(e1);
		Float forme5DerniersMatchs = 0F;
		if (forme5DerniersMatchsE2 > forme5DerniersMatchsE1) {
			forme5DerniersMatchs = forme5DerniersMatchsE2 - forme5DerniersMatchsE1;
		}

		Float forme5DerniersMatchsE2Exterieur = getFormePointsExterieur(e2);
		Float forme5DerniersMatchsE1Domicile = getFormePointsDomicile(e1);
		Float forme5DerniersMatchsDomExt = 0F;
		if (forme5DerniersMatchsE2Exterieur > forme5DerniersMatchsE1Domicile) {
			forme5DerniersMatchsDomExt = forme5DerniersMatchsE2Exterieur - forme5DerniersMatchsE1Domicile;
		}

		Float pointsResultats = 0F;
		Float pourcentageDefaitesE1 = 0F;
		Float pourcentageVictoiresE2 = 0F;

		Integer jouesE1 = homeTeam.getNbMatchesPlayed();
		Integer perdusE1 = homeTeam.getNbLosses();
		if (jouesE1 > 0) {
			pourcentageDefaitesE1 = (float) ((perdusE1 / jouesE1)) * 100;
		}
		Integer jouesE2 = awayTeam.getNbMatchesPlayed();
		Integer gagnesE2 = awayTeam.getNbWins();
		if (jouesE2 > 0) {
			pourcentageVictoiresE2 = (float) ((gagnesE2 / jouesE2)) * 100;
		}
		pointsResultats = (pourcentageDefaitesE1 + pourcentageVictoiresE2) / 2;

		Float pointsResultatsDomExt = 0F;
		Float pourcentageDefaitesE1Dom = 0F;
		Float pourcentageVictoiresE2Ext = 0F;

		Integer jouesE1Dom = homeTeam.getNbHomeMatchesPlayed();
		Integer perdusE1Dom = homeTeam.getNbHomeLosses();
		if (jouesE1Dom > 0) {
			pourcentageDefaitesE1Dom = (float) ((perdusE1Dom / jouesE1Dom)) * 100;
		}
		Integer jouesE2Ext = awayTeam.getNbAwayMatchesPlayed();
		Integer gagnesE2Ext = awayTeam.getNbAwayWins();
		if (jouesE2Ext > 0) {
			pourcentageVictoiresE2Ext = (float) ((gagnesE2Ext / jouesE2Ext)) * 100;
		}
		pointsResultatsDomExt = (pourcentageDefaitesE1Dom + pourcentageVictoiresE2Ext) / 2;

		Match matchEntity = DatabaseConnection.getMatch(match);

		Boolean e1MieuxClassee = Ligue1Utils.convertToBoolean(matchEntity.getHomeTeamHasABetterStanding());
		Boolean e1MatchImportant = Ligue1Utils.convertToBoolean(matchEntity.getIsAnImportantGameForHomeTeam());
		Boolean e2MatchImportant = Ligue1Utils.convertToBoolean(matchEntity.getIsAnImportantGameForAwayTeam());

		Float pointsResultatsImportance = 0F;
		Float pointsResultatsImportanceDomExt = 0F;

		if (e2MatchImportant) {
			if (e1MatchImportant) {
				Float pourcentageDefaitesE1Importance = 0F;
				Float pourcentageVictoiresE2Importance = 0F;

				Integer jouesE1Importance = homeTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer perdusE1Importance = homeTeam.getNbLossesAgainstImportantOpponent();
				if (jouesE1Importance > 0) {
					pourcentageDefaitesE1Importance = (float) ((perdusE1Importance / jouesE1Importance)) * 100;
				}
				Integer jouesE2Importance = awayTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer gagnesE2Importance = awayTeam.getNbWinsAgainstImportantOpponent();
				if (jouesE2Importance > 0) {
					pourcentageVictoiresE2Importance = (float) ((gagnesE2Importance / jouesE2Importance)) * 100;
				}
				pointsResultatsImportance = (pourcentageDefaitesE1Importance + pourcentageVictoiresE2Importance) / 2;

				Float pourcentageDefaitesE1DomImportance = 0F;
				Float pourcentageVictoiresE2ExtImportance = 0F;

				Integer jouesE1DomImportance = homeTeam.getNbMatchesPlayedAgainstImportantOpponentAtHome();
				Integer perdusE1DomImportance = homeTeam.getNbLossesAgainstImportantOpponentAtHome();
				if (jouesE1DomImportance > 0) {
					pourcentageDefaitesE1DomImportance = (float) ((perdusE1DomImportance / jouesE1DomImportance)) * 100;
				}
				Integer jouesE2ExtImportance = awayTeam.getNbMatchesPlayedAgainstImportantOpponentAway();
				Integer gagnesE2ExtImportance = awayTeam.getNbWinsAgainstImportantOpponentAway();
				if (jouesE2ExtImportance > 0) {
					pourcentageVictoiresE2ExtImportance = (float) ((gagnesE2ExtImportance / jouesE2ExtImportance))
							* 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageDefaitesE1DomImportance
						+ pourcentageVictoiresE2ExtImportance) / 2;
			} else {
				Float pourcentageDefaitesE1ImportanceNon = 0F;
				Float pourcentageVictoiresE2Importance = 0F;

				Integer jouesE1ImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer perdusE1ImportanceNon = homeTeam.getNbLossesAgainstNormalOpponent();
				if (jouesE1ImportanceNon > 0) {
					pourcentageDefaitesE1ImportanceNon = (float) ((perdusE1ImportanceNon / jouesE1ImportanceNon)) * 100;
				}
				Integer jouesE2Importance = awayTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer gagnesE2Importance = awayTeam.getNbWinsAgainstImportantOpponent();
				if (jouesE2Importance > 0) {
					pourcentageVictoiresE2Importance = (float) ((gagnesE2Importance / jouesE2Importance)) * 100;
				}
				pointsResultatsImportance = (pourcentageDefaitesE1ImportanceNon + pourcentageVictoiresE2Importance) / 2;

				Float pourcentageDefaitesE1DomImportanceNon = 0F;
				Float pourcentageVictoiresE2ExtImportance = 0F;

				Integer jouesE1DomImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponentAtHome();
				Integer perdusE1DomImportanceNon = homeTeam.getNbLossesAgainstNormalOpponentAtHome();
				if (jouesE1DomImportanceNon > 0) {
					pourcentageDefaitesE1DomImportanceNon = (float) ((perdusE1DomImportanceNon
							/ jouesE1DomImportanceNon)) * 100;
				}
				Integer jouesE2ExtImportance = awayTeam.getNbMatchesPlayedAgainstImportantOpponentAway();
				Integer gagnesE2ExtImportance = awayTeam.getNbWinsAgainstImportantOpponentAway();
				if (jouesE2ExtImportance > 0) {
					pourcentageVictoiresE2ExtImportance = (float) ((gagnesE2ExtImportance / jouesE2ExtImportance))
							* 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageDefaitesE1DomImportanceNon
						+ pourcentageVictoiresE2ExtImportance) / 2;
			}
		} else {
			if (e1MatchImportant) {
				Float pourcentageDefaitesE1Importance = 0F;
				Float pourcentageVictoiresE2ImportanceNon = 0F;

				Integer jouesE1Importance = homeTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer perdusE1Importance = homeTeam.getNbLossesAgainstImportantOpponent();
				if (jouesE1Importance > 0) {
					pourcentageDefaitesE1Importance = (float) ((perdusE1Importance / jouesE1Importance)) * 100;
				}
				Integer jouesE2ImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer gagnesE2ImportanceNon = awayTeam.getNbWinsAgainstNormalOpponent();
				if (jouesE2ImportanceNon > 0) {
					pourcentageVictoiresE2ImportanceNon = (float) ((gagnesE2ImportanceNon / jouesE2ImportanceNon))
							* 100;
				}
				pointsResultatsImportance = (pourcentageDefaitesE1Importance + pourcentageVictoiresE2ImportanceNon) / 2;

				Float pourcentageDefaitesE1DomImportance = 0F;
				Float pourcentageVictoiresE2ExtImportanceNon = 0F;

				Integer jouesE1DomImportance = homeTeam.getNbMatchesPlayedAgainstImportantOpponentAtHome();
				Integer perdusE1DomImportance = homeTeam.getNbLossesAgainstImportantOpponentAtHome();
				if (jouesE1DomImportance > 0) {
					pourcentageDefaitesE1DomImportance = (float) ((perdusE1DomImportance / jouesE1DomImportance)) * 100;
				}
				Integer jouesE2ExtImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponentAway();
				Integer gagnesE2ExtImportanceNon = awayTeam.getNbWinsAgainstNormalOpponentAway();
				if (jouesE2ExtImportanceNon > 0) {
					pourcentageVictoiresE2ExtImportanceNon = (float) ((gagnesE2ExtImportanceNon
							/ jouesE2ExtImportanceNon)) * 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageDefaitesE1DomImportance
						+ pourcentageVictoiresE2ExtImportanceNon) / 2;
			} else {
				Float pourcentageDefaitesE1ImportanceNon = 0F;
				Float pourcentageVictoiresE2ImportanceNon = 0F;

				Integer jouesE1ImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer perdusE1ImportanceNon = homeTeam.getNbLossesAgainstNormalOpponent();
				if (jouesE1ImportanceNon > 0) {
					pourcentageDefaitesE1ImportanceNon = (float) ((perdusE1ImportanceNon / jouesE1ImportanceNon)) * 100;
				}
				Integer jouesE2ImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer gagnesE2ImportanceNon = awayTeam.getNbWinsAgainstNormalOpponent();
				if (jouesE2ImportanceNon > 0) {
					pourcentageVictoiresE2ImportanceNon = (float) ((gagnesE2ImportanceNon / jouesE2ImportanceNon))
							* 100;
				}
				pointsResultatsImportance = (pourcentageDefaitesE1ImportanceNon + pourcentageVictoiresE2ImportanceNon)
						/ 2;

				Float pourcentageDefaitesE1DomImportanceNon = 0F;
				Float pourcentageVictoiresE2ExtImportanceNon = 0F;

				Integer jouesE1DomImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponentAtHome();
				Integer perdusE1DomImportanceNon = homeTeam.getNbLossesAgainstNormalOpponentAtHome();
				if (jouesE1DomImportanceNon > 0) {
					pourcentageDefaitesE1DomImportanceNon = (float) ((perdusE1DomImportanceNon
							/ jouesE1DomImportanceNon)) * 100;
				}
				Integer jouesE2ExtImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponentAway();
				Integer gagnesE2ExtImportanceNon = awayTeam.getNbWinsAgainstNormalOpponentAway();
				if (jouesE2ExtImportanceNon > 0) {
					pourcentageVictoiresE2ExtImportanceNon = (float) ((gagnesE2ExtImportanceNon
							/ jouesE2ExtImportanceNon)) * 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageDefaitesE1DomImportanceNon
						+ pourcentageVictoiresE2ExtImportanceNon) / 2;

			}
		}

		Float pointsResultatsClassement = 0F;
		Float pointsResultatsClassementDomExt = 0F;

		if (e1MieuxClassee) {

			Float pourcentageDefaitesE1ContreClassementInferieur = 0F;
			Float pourcentageVictoiresE2ContreClassementSuperieur = 0F;

			Integer jouesE1ContreClassementInferieur = homeTeam.getNbMatchesPlayedAgainstStandingInferior();
			Integer perdusE1ContreClassementInferieur = homeTeam.getNbLossesAgainstStandingInferior();
			if (jouesE1ContreClassementInferieur > 0) {
				pourcentageDefaitesE1ContreClassementInferieur = (float) ((perdusE1ContreClassementInferieur
						/ jouesE1ContreClassementInferieur)) * 100;
			}
			Integer jouesE2ContreClassementSuperieur = awayTeam.getNbMatchesPlayedAgainstStandingSuperior();
			Integer gagnesE2ContreClassementSuperieur = awayTeam.getNbWinsAgainstStandingSuperior();
			if (jouesE2ContreClassementSuperieur > 0) {
				pourcentageVictoiresE2ContreClassementSuperieur = (float) ((gagnesE2ContreClassementSuperieur
						/ jouesE2ContreClassementSuperieur)) * 100;
			}
			pointsResultatsClassement = (pourcentageDefaitesE1ContreClassementInferieur
					+ pourcentageVictoiresE2ContreClassementSuperieur) / 2;

			Float pourcentageDefaitesE1ContreClassementInferieurDomicile = 0F;
			Float pourcentageVictoiresE2ContreClassementSuperieurExterieur = 0F;

			Integer jouesE1ContreClassementInferieurDomicile = homeTeam
					.getNbMatchesPlayedAgainstStandingInferiorAtHome();
			Integer perdusE1ContreClassementInferieurDomicile = homeTeam.getNbLossesAgainstStandingInferiorAtHome();
			if (jouesE1ContreClassementInferieurDomicile > 0) {
				pourcentageDefaitesE1ContreClassementInferieurDomicile = (float) ((perdusE1ContreClassementInferieurDomicile
						/ jouesE1ContreClassementInferieurDomicile)) * 100;
			}
			Integer jouesE2ContreClassementSuperieurExterieur = awayTeam
					.getNbMatchesPlayedAgainstStandingSuperiorAway();
			Integer gagnesE2ContreClassementSuperieurExterieur = awayTeam.getNbWinsAgainstStandingSuperiorAway();
			if (jouesE2ContreClassementSuperieurExterieur > 0) {
				pourcentageVictoiresE2ContreClassementSuperieurExterieur = (float) ((gagnesE2ContreClassementSuperieurExterieur
						/ jouesE2ContreClassementSuperieurExterieur)) * 100;
			}
			pointsResultatsClassementDomExt = (pourcentageDefaitesE1ContreClassementInferieurDomicile
					+ pourcentageVictoiresE2ContreClassementSuperieurExterieur) / 2;

		} else {
			Float pourcentageDefaitesE1ContreClassementSuperieur = 0F;
			Float pourcentageVictoiresE2ContreClassementInferieur = 0F;

			Integer jouesE1ContreClassementSuperieur = homeTeam.getNbMatchesPlayedAgainstStandingSuperior();
			Integer perdusE1ContreClassementSuperieur = homeTeam.getNbLossesAgainstStandingSuperior();
			if (jouesE1ContreClassementSuperieur > 0) {
				pourcentageDefaitesE1ContreClassementSuperieur = (float) ((perdusE1ContreClassementSuperieur
						/ jouesE1ContreClassementSuperieur)) * 100;
			}
			Integer jouesE2ContreClassementInferieur = awayTeam.getNbMatchesPlayedAgainstStandingInferior();
			Integer gagnesE2ContreClassementInferieur = awayTeam.getNbWinsAgainstStandingInferior();
			if (jouesE2ContreClassementInferieur > 0) {
				pourcentageVictoiresE2ContreClassementInferieur = (float) ((gagnesE2ContreClassementInferieur
						/ jouesE2ContreClassementInferieur)) * 100;
			}
			pointsResultatsClassement = (pourcentageDefaitesE1ContreClassementSuperieur
					+ pourcentageVictoiresE2ContreClassementInferieur) / 2;

			Float pourcentageDefaitesE1ContreClassementSuperieurDomicile = 0F;
			Float pourcentageVictoiresE2ContreClassementInferieurExterieur = 0F;

			Integer jouesE1ContreClassementSuperieurDomicile = homeTeam
					.getNbMatchesPlayedAgainstStandingSuperiorAtHome();
			Integer perdusE1ContreClassementSuperieurDomicile = homeTeam.getNbLossesAgainstStandingSuperiorAtHome();
			if (jouesE1ContreClassementSuperieurDomicile > 0) {
				pourcentageDefaitesE1ContreClassementSuperieurDomicile = (float) ((perdusE1ContreClassementSuperieurDomicile
						/ jouesE1ContreClassementSuperieurDomicile)) * 100;
			}
			Integer jouesE2ContreClassementInferieurExterieur = awayTeam
					.getNbMatchesPlayedAgainstStandingInferiorAway();
			Integer gagnesE2ContreClassementInferieurExterieur = awayTeam.getNbWinsAgainstStandingInferiorAway();
			if (jouesE2ContreClassementInferieurExterieur > 0) {
				pourcentageVictoiresE2ContreClassementInferieurExterieur = (float) ((gagnesE2ContreClassementInferieurExterieur
						/ jouesE2ContreClassementInferieurExterieur)) * 100;
			}
			pointsResultatsClassementDomExt = (pourcentageDefaitesE1ContreClassementSuperieurDomicile
					+ pourcentageVictoiresE2ContreClassementInferieurExterieur) / 2;
		}

		Float pourcentageGlobalVExt = DatabaseConnection.getGlobalPercentage("VictoireEquipeExterieur");

		totalPoints = (config.getCoefficientConfrontationsDirectes() * pourcentageVEXTConfrontations)
				+ (config.getCoefficientClassement() * nbPointsRapportesParClassement)
				+ (config.getCoefficientClassementDomExt() * nbPointsRapportesParClassementsDomEtExt)
				+ (config.getCoefficientForme() * nbPointsForme) + (config.getCoefficientForme() * forme5DerniersMatchs)
				+ (config.getCoefficientFormeDomExt() * forme5DerniersMatchsDomExt)
				+ (config.getCoefficientResultats() * pointsResultats)
				+ (config.getCoefficientResultatsDomExt() * pointsResultatsDomExt)
				+ (config.getCoefficientFormeDomExt() * nbPointsFormeDomExt)
				+ (config.getCoefficientResultatsImp() * pointsResultatsImportance)
				+ (config.getCoefficientResultatsImp() * pointsResultatsImportanceDomExt)
				+ (config.getCoefficientClassement() * pointsResultatsClassement)
				+ (config.getCoefficientClassementDomExt() * pointsResultatsClassementDomExt)
				+ (config.getCoefficientGlobal() * pourcentageGlobalVExt);

		return totalPoints;
	}

	private static Float getFormePointsDomicile(String e) throws NullTeamException {

		Float forme = 0F;
		Team team = DatabaseConnection.getTeamByNickname(e);
		String precedent1 = team.getHomeRecent1();
		String precedent2 = team.getHomeRecent2();
		String precedent3 = team.getHomeRecent3();
		String precedent4 = team.getHomeRecent4();
		String precedent5 = team.getHomeRecent5();

		forme = getNombreForme("V", precedent1, precedent2, precedent3, precedent4, precedent5) * 10F
				+ getNombreForme("N", precedent1, precedent2, precedent3, precedent4, precedent5) * 5F;

		return forme;
	}

	private static Float getFormePointsExterieur(String e) throws NullTeamException {

		Float forme = 0F;
		Team team = DatabaseConnection.getTeamByNickname(e);
		String precedent1 = team.getAwayRecent1();
		String precedent2 = team.getAwayRecent2();
		String precedent3 = team.getAwayRecent3();
		String precedent4 = team.getAwayRecent4();
		String precedent5 = team.getAwayRecent5();

		forme = getNombreForme("V", precedent1, precedent2, precedent3, precedent4, precedent5) * 10F
				+ getNombreForme("N", precedent1, precedent2, precedent3, precedent4, precedent5) * 5F;

		return forme;
	}

	private static Float getFormePointsForTeam(String e) throws NullTeamException {

		Float forme = 0F;
		Team team = DatabaseConnection.getTeamByNickname(e);
		String precedent1 = team.getRecent1();
		String precedent2 = team.getRecent2();
		String precedent3 = team.getRecent3();
		String precedent4 = team.getRecent4();
		String precedent5 = team.getRecent5();

		forme = getNombreForme("V", precedent1, precedent2, precedent3, precedent4, precedent5) * 10F
				+ getNombreForme("N", precedent1, precedent2, precedent3, precedent4, precedent5) * 5F;

		return forme;
	}

	private static Float getFormePointsForDraw(String e1, String e2) throws NullTeamException {

		Float forme = 0F;
		Team homeTeam = DatabaseConnection.getTeamByNickname(e1);
		Team awayTeam = DatabaseConnection.getTeamByNickname(e2);
		String e1precedent1 = homeTeam.getRecent1();
		String e1precedent2 = homeTeam.getRecent2();
		String e1precedent3 = homeTeam.getRecent3();
		String e1precedent4 = homeTeam.getRecent4();
		String e1precedent5 = homeTeam.getRecent5();

		String e2precedent1 = awayTeam.getRecent1();
		String e2precedent2 = awayTeam.getRecent2();
		String e2precedent3 = awayTeam.getRecent3();
		String e2precedent4 = awayTeam.getRecent4();
		String e2precedent5 = awayTeam.getRecent5();

		forme = getNombreForme("N", e1precedent1, e1precedent2, e1precedent3, e1precedent4, e1precedent5) * 10F
				+ getNombreForme("N", e2precedent1, e2precedent2, e2precedent3, e2precedent4, e2precedent5) * 10F;

		return forme;

	}

	private static float getFormePointsForDrawDomExt(String e1, String e2) throws NullTeamException {

		Float forme = 0F;
		Team homeTeam = DatabaseConnection.getTeamByNickname(e1);
		Team awayTeam = DatabaseConnection.getTeamByNickname(e2);
		String e1precedent1 = homeTeam.getHomeRecent1();
		String e1precedent2 = homeTeam.getHomeRecent2();
		String e1precedent3 = homeTeam.getHomeRecent3();
		String e1precedent4 = homeTeam.getHomeRecent4();
		String e1precedent5 = homeTeam.getHomeRecent5();

		String e2precedent1 = awayTeam.getAwayRecent1();
		String e2precedent2 = awayTeam.getAwayRecent2();
		String e2precedent3 = awayTeam.getAwayRecent3();
		String e2precedent4 = awayTeam.getAwayRecent4();
		String e2precedent5 = awayTeam.getAwayRecent5();

		forme = getNombreForme("N", e1precedent1, e1precedent2, e1precedent3, e1precedent4, e1precedent5) * 10F
				+ getNombreForme("N", e2precedent1, e2precedent2, e2precedent3, e2precedent4, e2precedent5) * 10F;

		return forme;

	}

	private static Integer getNombreForme(String forme, String precedent1, String precedent2, String precedent3,
			String precedent4, String precedent5) {

		Integer total = 0;
		if (precedent1.equals(forme))
			total++;
		if (precedent2.equals(forme))
			total++;
		if (precedent3.equals(forme))
			total++;
		if (precedent4.equals(forme))
			total++;
		if (precedent5.equals(forme))
			total++;
		return total;
	}

	private static String getFormeActuelleExterieur(String e) throws NullTeamException {
		Team team = DatabaseConnection.getTeamByNickname(e);
		Integer serieV = team.getAwayWinningSerie();
		if (serieV > 0)
			return "V";
		Integer serieN = team.getAwayDrawSerie();
		if (serieN > 0)
			return "N";
		Integer serieD = team.getAwayLoosingSerie();
		if (serieD > 0)
			return "D";

		return null;
	}

	private static String getFormeActuelleDomicile(String e) throws NullTeamException {
		Team team = DatabaseConnection.getTeamByNickname(e);
		Integer serieV = team.getHomeWinningSerie();
		if (serieV > 0)
			return "V";
		Integer serieN = team.getHomeDrawSerie();
		if (serieN > 0)
			return "N";
		Integer serieD = team.getHomeLoosingSerie();
		if (serieD > 0)
			return "D";

		return null;
	}

	private static String getFormeActuelle(String e) throws NullTeamException {
		Team team = DatabaseConnection.getTeamByNickname(e);
		Integer serieV = team.getWinningSerie();
		if (serieV > 0)
			return "V";
		Integer serieN = team.getDrawSerie();
		if (serieN > 0)
			return "N";
		Integer serieD = team.getLoosingSerie();
		if (serieD > 0)
			return "D";

		return null;
	}

	private static Float getDrawProbabilityPoints(Statistic statistiques, String match, String e1, String e2,
			Configuration config)
			throws InvalidNumberToConvertFromBooleanException, NullTeamException, NullMatchException {

		Float totalPoints = 0F;

//		String[] score = match.split("-");
//		String e1 = score[0];
//		String e2 = score[1];

		Match matchEntity = DatabaseConnection.getMatch(match);

		Team homeTeam = DatabaseConnection.getTeamByNickname(e1);
		Team awayTeam = DatabaseConnection.getTeamByNickname(e2);

		Float pourcentageNulsConfrontations = statistiques.getDrawPercentage();

		Integer classementE1 = homeTeam.getStanding();
		Integer classementE2 = awayTeam.getStanding();
		Integer nbPointsRapportesParClassement = 0;
		if (Ligue1Utils.isAllowed(classementE2) && Ligue1Utils.isAllowed(classementE1)) {
			// il n'y a pas plus de 3 places d'écart au classement
			if (Math.abs(classementE1) - (classementE2) <= 3) {
				if (Math.abs(classementE1) - (classementE2) == 3) {
					nbPointsRapportesParClassement = 10;
				}
				if (Math.abs(classementE1) - (classementE2) == 2) {
					nbPointsRapportesParClassement = 20;
				}
				if (Math.abs(classementE1) - (classementE2) == 1) {
					nbPointsRapportesParClassement = 30;
				}
			}
		}

		Integer classementE1Domicile = homeTeam.getHomeStanding();
		Integer classementE2Exterieur = awayTeam.getAwayStanding();
		Integer nbPointsRapportesParClassementsDomEtExt = 0;
		if (Ligue1Utils.isAllowed(classementE2Exterieur) && Ligue1Utils.isAllowed(classementE1Domicile)) {
			// il n'y a pas plus de 3 places d'écart au classement
			if (Math.abs(classementE1Domicile) - (classementE2Exterieur) <= 3) {
				if (Math.abs(classementE1Domicile) - (classementE2Exterieur) == 3) {
					nbPointsRapportesParClassementsDomEtExt = 10;
				}
				if (Math.abs(classementE1Domicile) - (classementE2Exterieur) == 2) {
					nbPointsRapportesParClassementsDomEtExt = 20;
				}
				if (Math.abs(classementE1Domicile) - (classementE2Exterieur) == 1) {
					nbPointsRapportesParClassementsDomEtExt = 30;
				}
			}
		}

		Float nbPointsForme = 0F;
		String formeE1 = getFormeActuelle(e1);
		String formeE2 = getFormeActuelle(e2);
		if (!Ligue1Utils.isEmpty(formeE1) && !Ligue1Utils.isEmpty(formeE2)) {
			if (formeE1.equals("N")) {
				Integer serieNe1 = homeTeam.getDrawSerie();
				if (formeE2.equals("N")) {
					Integer serieNe2 = awayTeam.getDrawSerie();
					nbPointsForme = (float) (serieNe1 + serieNe2) * 5;
				}
			}
		}

		Float nbPointsFormeDomExt = 0F;
		String formeE1Dom = getFormeActuelleDomicile(e1);
		String formeE2Ext = getFormeActuelleExterieur(e2);
		if (!Ligue1Utils.isEmpty(formeE1Dom) && !Ligue1Utils.isEmpty(formeE2Ext)) {
			if (formeE1Dom.equals("N")) {
				Integer serieNe1 = homeTeam.getHomeDrawSerie();
				if (formeE2Ext.equals("N")) {
					Integer serieNe2 = awayTeam.getAwayDrawSerie();
					nbPointsFormeDomExt = (float) (serieNe1 + serieNe2) * 5;
				}
			}
		}

		Float forme5DerniersMatchsDraw = getFormePointsForDraw(e1, e2);

		Float forme5DerniersMatchsDrawDomExt = getFormePointsForDrawDomExt(e1, e2);

		Float pointsResultats = 0F;
		Float pourcentageNulsE2 = 0F;
		Float pourcentageNulsE1 = 0F;

		Integer jouesE2 = awayTeam.getNbMatchesPlayed();
		Integer nulsE2 = awayTeam.getNbDraws();
		if (jouesE2 > 0) {
			pourcentageNulsE2 = (float) ((nulsE2 / jouesE2)) * 100;
		}
		Integer jouesE1 = homeTeam.getNbMatchesPlayed();
		Integer nulsE1 = homeTeam.getNbDraws();
		if (jouesE1 > 0) {
			pourcentageNulsE1 = (float) ((nulsE1 / jouesE1)) * 100;
		}
		pointsResultats = (pourcentageNulsE2 + pourcentageNulsE1) / 2;

		Float pointsResultatsDomExt = 0F;
		Float pourcentageNulsE2Ext = 0F;
		Float pourcentageNulsE1Dom = 0F;

		Integer jouesE2Ext = awayTeam.getNbAwayMatchesPlayed();
		Integer nulsE2Ext = awayTeam.getNbAwayDraws();
		if (jouesE2Ext > 0) {
			pourcentageNulsE2Ext = (float) ((nulsE2Ext / jouesE2Ext)) * 100;
		}
		Integer jouesE1Dom = homeTeam.getNbHomeMatchesPlayed();
		Integer nulsE1Dom = homeTeam.getNbHomeDraws();
		if (jouesE1Dom > 0) {
			pourcentageNulsE1Dom = (float) ((nulsE1Dom / jouesE1Dom)) * 100;
		}
		pointsResultatsDomExt = (pourcentageNulsE2Ext + pourcentageNulsE1Dom) / 2;

		Boolean e1MieuxClassee = Ligue1Utils.convertToBoolean(matchEntity.getHomeTeamHasABetterStanding());
		Boolean e1MatchImportant = Ligue1Utils.convertToBoolean(matchEntity.getIsAnImportantGameForHomeTeam());
		Boolean e2MatchImportant = Ligue1Utils.convertToBoolean(matchEntity.getIsAnImportantGameForAwayTeam());

		Float pointsResultatsImportance = 0F;
		Float pointsResultatsImportanceDomExt = 0F;

		if (e2MatchImportant) {
			if (e1MatchImportant) {
				Float pourcentageNulsE2Importance = 0F;
				Float pourcentageNulsE1Importance = 0F;

				Integer jouesE2Importance = awayTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer nulsE2Importance = awayTeam.getNbDrawsAgainstImportantOpponent();
				if (jouesE2Importance > 0) {
					pourcentageNulsE2Importance = (float) ((nulsE2Importance / jouesE2Importance)) * 100;
				}
				Integer jouesE1Importance = homeTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer nulsE1Importance = homeTeam.getNbDrawsAgainstImportantOpponent();
				if (jouesE1Importance > 0) {
					pourcentageNulsE1Importance = (float) ((nulsE1Importance / jouesE1Importance)) * 100;
				}
				pointsResultatsImportance = (pourcentageNulsE2Importance + pourcentageNulsE1Importance) / 2;

				Float pourcentageNulsE2ExtImportance = 0F;
				Float pourcentageNulsE1DomImportance = 0F;

				Integer jouesE2ExtImportance = awayTeam.getNbMatchesPlayedAgainstImportantOpponentAway();
				Integer nulsE2ExtImportance = awayTeam.getNbDrawsAgainstImportantOpponentAway();
				if (jouesE2ExtImportance > 0) {
					pourcentageNulsE2ExtImportance = (float) ((nulsE2ExtImportance / jouesE2ExtImportance)) * 100;
				}
				Integer jouesE1DomImportance = homeTeam.getNbMatchesPlayedAgainstImportantOpponentAtHome();
				Integer nulsE1DomImportance = homeTeam.getNbDrawsAgainstImportantOpponentAtHome();

				if (jouesE1DomImportance > 0) {
					pourcentageNulsE1DomImportance = (float) ((nulsE1DomImportance / jouesE1DomImportance)) * 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageNulsE2ExtImportance + pourcentageNulsE1DomImportance) / 2;
			} else {
				Float pourcentageNulsE2ImportanceNon = 0F;
				Float pourcentageNulsE1Importance = 0F;

				Integer jouesE2ImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer nulsE2ImportanceNon = awayTeam.getNbDrawsAgainstNormalOpponent();
				if (jouesE2ImportanceNon > 0) {
					pourcentageNulsE2ImportanceNon = (float) ((nulsE2ImportanceNon / jouesE2ImportanceNon)) * 100;
				}
				Integer jouesE1Importance = homeTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer nulsE1Importance = homeTeam.getNbDrawsAgainstImportantOpponent();
				if (jouesE1Importance > 0) {
					pourcentageNulsE1Importance = (float) ((nulsE1Importance / jouesE1Importance)) * 100;
				}
				pointsResultatsImportance = (pourcentageNulsE2ImportanceNon + pourcentageNulsE1Importance) / 2;

				Float pourcentageNulsE2ExtImportanceNon = 0F;
				Float pourcentageNulsE1DomImportance = 0F;

				Integer jouesE2ExtImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponentAway();
				Integer nulsE2ExtImportanceNon = awayTeam.getNbDrawsAgainstNormalOpponentAway();
				if (jouesE2ExtImportanceNon > 0) {
					pourcentageNulsE2ExtImportanceNon = (float) ((nulsE2ExtImportanceNon / jouesE2ExtImportanceNon))
							* 100;
				}
				Integer jouesE1DomImportance = homeTeam.getNbMatchesPlayedAgainstImportantOpponentAtHome();
				Integer nulsE1DomImportance = homeTeam.getNbDrawsAgainstImportantOpponentAtHome();
				if (jouesE1DomImportance > 0) {
					pourcentageNulsE1DomImportance = (float) ((nulsE1DomImportance / jouesE1DomImportance)) * 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageNulsE2ExtImportanceNon + pourcentageNulsE1DomImportance)
						/ 2;
			}
		} else {
			if (e1MatchImportant) {
				Float pourcentageNulsE2Importance = 0F;
				Float pourcentageNulsE1ImportanceNon = 0F;

				Integer jouesE2Importance = awayTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer nulsE2Importance = awayTeam.getNbDrawsAgainstImportantOpponent();
				if (jouesE2Importance > 0) {
					pourcentageNulsE2Importance = (float) ((nulsE2Importance / jouesE2Importance)) * 100;
				}
				Integer jouesE1ImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer nulsE1ImportanceNon = homeTeam.getNbDrawsAgainstNormalOpponent();
				if (jouesE1ImportanceNon > 0) {
					pourcentageNulsE1ImportanceNon = (float) ((nulsE1ImportanceNon / jouesE1ImportanceNon)) * 100;
				}
				pointsResultatsImportance = (pourcentageNulsE2Importance + pourcentageNulsE1ImportanceNon) / 2;

				Float pourcentageNulsE2ExtImportance = 0F;
				Float pourcentageNulsE1DomImportanceNon = 0F;

				Integer jouesE2ExtImportance = awayTeam.getNbMatchesPlayedAgainstImportantOpponentAway();
				Integer nulsE2ExtImportance = awayTeam.getNbDrawsAgainstImportantOpponentAway();
				if (jouesE2ExtImportance > 0) {
					pourcentageNulsE2ExtImportance = (float) ((nulsE2ExtImportance / jouesE2ExtImportance)) * 100;
				}
				Integer jouesE1DomImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponentAtHome();
				Integer nulsE1DomImportanceNon = homeTeam.getNbDrawsAgainstNormalOpponentAtHome();
				if (jouesE1DomImportanceNon > 0) {
					pourcentageNulsE1DomImportanceNon = (float) ((nulsE1DomImportanceNon / jouesE1DomImportanceNon))
							* 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageNulsE2ExtImportance + pourcentageNulsE1DomImportanceNon)
						/ 2;
			} else {
				Float pourcentageNulsE2ImportanceNon = 0F;
				Float pourcentageNulsE1ImportanceNon = 0F;

				Integer jouesE2ImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer nulsE2ImportanceNon = awayTeam.getNbDrawsAgainstNormalOpponent();
				if (jouesE2ImportanceNon > 0) {
					pourcentageNulsE2ImportanceNon = (float) ((nulsE2ImportanceNon / jouesE2ImportanceNon)) * 100;
				}
				Integer jouesE1ImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer nulsE1ImportanceNon = homeTeam.getNbDrawsAgainstNormalOpponent();
				if (jouesE1ImportanceNon > 0) {
					pourcentageNulsE1ImportanceNon = (float) ((nulsE1ImportanceNon / jouesE1ImportanceNon)) * 100;
				}
				pointsResultatsImportance = (pourcentageNulsE2ImportanceNon + pourcentageNulsE1ImportanceNon) / 2;

				Float pourcentageNulsE2ExtImportanceNon = 0F;
				Float pourcentageNulsE1DomImportanceNon = 0F;

				Integer jouesE2ExtImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponentAway();
				Integer nuls2ExtImportanceNon = awayTeam.getNbDrawsAgainstNormalOpponentAway();
				if (jouesE2ExtImportanceNon > 0) {
					pourcentageNulsE2ExtImportanceNon = (float) ((nuls2ExtImportanceNon / jouesE2ExtImportanceNon))
							* 100;
				}
				Integer jouesE1DomImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponentAtHome();
				Integer nulsE1DomImportanceNon = homeTeam.getNbDrawsAgainstNormalOpponentAtHome();
				if (jouesE1DomImportanceNon > 0) {
					pourcentageNulsE1DomImportanceNon = (float) ((nulsE1DomImportanceNon / jouesE1DomImportanceNon))
							* 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageNulsE2ExtImportanceNon
						+ pourcentageNulsE1DomImportanceNon) / 2;

			}
		}

		Float pointsResultatsClassement = 0F;
		Float pointsResultatsClassementDomExt = 0F;

		if (e1MieuxClassee) {

			Float pourcentageNulsE2ContreClassementSuperieur = 0F;
			Float pourcentageNulsE1ContreClassementInferieur = 0F;

			Integer jouesE2ContreClassementSuperieur = awayTeam.getNbMatchesPlayedAgainstStandingSuperior();
			Integer nulsE2ContreClassementSuperieur = awayTeam.getNbDrawsAgainstStandingSuperior();
			if (jouesE2ContreClassementSuperieur > 0) {
				pourcentageNulsE2ContreClassementSuperieur = (float) ((nulsE2ContreClassementSuperieur
						/ jouesE2ContreClassementSuperieur)) * 100;
			}
			Integer jouesE1ContreClassementInferieur = homeTeam.getNbMatchesPlayedAgainstStandingInferior();
			Integer nulsE1ContreClassementInferieur = homeTeam.getNbDrawsAgainstStandingInferior();
			if (jouesE1ContreClassementInferieur > 0) {
				pourcentageNulsE1ContreClassementInferieur = (float) ((nulsE1ContreClassementInferieur
						/ jouesE1ContreClassementInferieur)) * 100;
			}
			pointsResultatsClassement = (pourcentageNulsE2ContreClassementSuperieur
					+ pourcentageNulsE1ContreClassementInferieur) / 2;

			Float pourcentageNulsE2ContreClassementSuperieurExterieur = 0F;
			Float pourcentageNulsE1ContreClassementInferieurDomicile = 0F;

			Integer jouesE2ContreClassementSuperieurExterieur = awayTeam
					.getNbMatchesPlayedAgainstStandingSuperiorAway();
			Integer nulsE2ContreClassementSuperieurExterieur = awayTeam.getNbDrawsAgainstStandingSuperiorAway();
			if (jouesE2ContreClassementSuperieurExterieur > 0) {
				pourcentageNulsE2ContreClassementSuperieurExterieur = (float) ((nulsE2ContreClassementSuperieurExterieur
						/ jouesE2ContreClassementSuperieurExterieur)) * 100;
			}
			Integer jouesE1ContreClassementInferieurDomicile = homeTeam
					.getNbMatchesPlayedAgainstStandingInferiorAtHome();
			Integer nulsE1ContreClassementInferieurDomicile = homeTeam.getNbDrawsAgainstStandingInferiorAtHome();
			if (jouesE1ContreClassementInferieurDomicile > 0) {
				pourcentageNulsE1ContreClassementInferieurDomicile = (float) ((nulsE1ContreClassementInferieurDomicile
						/ jouesE1ContreClassementInferieurDomicile)) * 100;
			}
			pointsResultatsClassementDomExt = (pourcentageNulsE2ContreClassementSuperieurExterieur
					+ pourcentageNulsE1ContreClassementInferieurDomicile) / 2;

		} else {
			Float pourcentageNulsE2ContreClassementInferieur = 0F;
			Float pourcentageNulsE1ContreClassementSuperieur = 0F;

			Integer jouesE2ContreClassementInferieur = awayTeam.getNbMatchesPlayedAgainstStandingInferior();
			Integer nulsE2ContreClassementInferieur = awayTeam.getNbDrawsAgainstStandingInferior();
			if (jouesE2ContreClassementInferieur > 0) {
				pourcentageNulsE2ContreClassementInferieur = (float) ((nulsE2ContreClassementInferieur
						/ jouesE2ContreClassementInferieur)) * 100;
			}
			Integer jouesE1ContreClassementSuperieur = homeTeam.getNbMatchesPlayedAgainstStandingSuperior();
			Integer nulsE1ContreClassementSuperieur = homeTeam.getNbDrawsAgainstStandingSuperior();
			if (jouesE1ContreClassementSuperieur > 0) {
				pourcentageNulsE1ContreClassementSuperieur = (float) ((nulsE1ContreClassementSuperieur
						/ jouesE1ContreClassementSuperieur)) * 100;
			}
			pointsResultatsClassement = (pourcentageNulsE2ContreClassementInferieur
					+ pourcentageNulsE1ContreClassementSuperieur) / 2;

			Float pourcentageNulsE2ContreClassementInferieurExterieur = 0F;
			Float pourcentageNulsE1ContreClassementSuperieurDomicile = 0F;

			Integer jouesE2ContreClassementInferieurExterieur = awayTeam
					.getNbMatchesPlayedAgainstStandingInferiorAway();
			Integer nulsE2ContreClassementInferieurExterieur = awayTeam.getNbDrawsAgainstStandingInferiorAway();
			if (jouesE2ContreClassementInferieurExterieur > 0) {
				pourcentageNulsE2ContreClassementInferieurExterieur = (float) ((nulsE2ContreClassementInferieurExterieur
						/ jouesE2ContreClassementInferieurExterieur)) * 100;
			}
			Integer jouesE1ContreClassementSuperieurDomicile = homeTeam
					.getNbMatchesPlayedAgainstStandingSuperiorAtHome();
			Integer nulsE1ContreClassementSuperieurDomicile = homeTeam.getNbDrawsAgainstStandingSuperiorAtHome();
			if (jouesE1ContreClassementSuperieurDomicile > 0) {
				pourcentageNulsE1ContreClassementSuperieurDomicile = (float) ((nulsE1ContreClassementSuperieurDomicile
						/ jouesE1ContreClassementSuperieurDomicile)) * 100;
			}
			pointsResultatsClassementDomExt = (pourcentageNulsE2ContreClassementInferieurExterieur
					+ pourcentageNulsE1ContreClassementSuperieurDomicile) / 2;

		}

		Float pourcentageGlobalDraw = DatabaseConnection.getGlobalPercentage("nul");

		totalPoints = (config.getCoefficientConfrontationsDirectes() * pourcentageNulsConfrontations)
				+ (config.getCoefficientClassement() * nbPointsRapportesParClassement)
				+ (config.getCoefficientClassementDomExt() * nbPointsRapportesParClassementsDomEtExt)
				+ (config.getCoefficientForme() * nbPointsForme)
				+ (config.getCoefficientForme() * forme5DerniersMatchsDraw)
				+ (config.getCoefficientFormeDomExt() * forme5DerniersMatchsDrawDomExt)
				+ (config.getCoefficientResultats() * pointsResultats)
				+ (config.getCoefficientResultatsDomExt() * pointsResultatsDomExt)
				+ (config.getCoefficientFormeDomExt() * nbPointsFormeDomExt)
				+ (config.getCoefficientResultatsImp() * pointsResultatsImportance)
				+ (config.getCoefficientResultatsImp() * pointsResultatsImportanceDomExt)
				+ (config.getCoefficientClassement() * pointsResultatsClassement)
				+ (config.getCoefficientClassementDomExt() * pointsResultatsClassementDomExt)
				+ (config.getCoefficientGlobal() * pourcentageGlobalDraw);

		return totalPoints;
	}

	private static Float getProbabilityPointsForHomeTeam(Statistic statistiques, String match, String e1, String e2,
			Configuration config)
			throws InvalidNumberToConvertFromBooleanException, NullTeamException, NullMatchException {

		Float totalPoints = 0F;

//		String[] score = match.split("-");
//		String e1 = score[0];
//		String e2 = score[1];
		Team homeTeam = DatabaseConnection.getTeamByNickname(e1);
		Team awayTeam = DatabaseConnection.getTeamByNickname(e2);
		Match matchEntity = DatabaseConnection.getMatch(match);
		Float pourcentageVDOMConfrontations = statistiques.getHomeTeamWinPercentage();

		int classementE1 = homeTeam.getStanding();
		int classementE2 = awayTeam.getStanding();
		int nbPointsRapportesParClassement = 0;
		if (Ligue1Utils.isAllowed(classementE2) && Ligue1Utils.isAllowed(classementE1)) {
			// e1 mieux classée que e2
			if (classementE1 < classementE2) {
				nbPointsRapportesParClassement = (classementE2 - classementE1) * 10;
			}
		}

		int classementE1Domicile = homeTeam.getHomeStanding();
		int classementE2Exterieur = awayTeam.getAwayStanding();
		int nbPointsRapportesParClassementsDomEtExt = 0;
		if (Ligue1Utils.isAllowed(classementE2Exterieur) && Ligue1Utils.isAllowed(classementE1Domicile)) {
			// e1 mieux classée que e2
			if (classementE2Exterieur > classementE1Domicile) {
				nbPointsRapportesParClassementsDomEtExt = (classementE2Exterieur - classementE1Domicile) * 10;
			}
		}

		Float nbPointsForme = 0F;
		String formeE1 = getFormeActuelle(e1);
		String formeE2 = getFormeActuelle(e2);
		if (!Ligue1Utils.isEmpty(formeE1) && !Ligue1Utils.isEmpty(formeE2)) {
			if (formeE1.equals("V")) {
				Integer serieVe1 = homeTeam.getWinningSerie();
				if (formeE2.equals("V")) {
					Integer serieVe2 = awayTeam.getWinningSerie();
					if (serieVe1 > serieVe2) {
						nbPointsForme = (float) (serieVe1 - serieVe2) * 5;
					}
				} else {
					if (formeE2.equals("N")) {
						nbPointsForme = (float) serieVe1 * 10;
					} else {
						nbPointsForme = (float) serieVe1 * 15;
					}
				}
			}
		}

		Float nbPointsFormeDomExt = 0F;
		String formeE1Dom = getFormeActuelleDomicile(e1);
		String formeE2Ext = getFormeActuelleExterieur(e2);
		if (!Ligue1Utils.isEmpty(formeE1Dom) && !Ligue1Utils.isEmpty(formeE2Ext)) {
			if (formeE1Dom.equals("V")) {
				Integer serieVe1 = homeTeam.getHomeWinningSerie();
				if (formeE2Ext.equals("V")) {
					Integer serieVe2 = awayTeam.getAwayWinningSerie();
					if (serieVe1 > serieVe2) {
						nbPointsFormeDomExt = (float) (serieVe1 - serieVe2) * 5;
					}
				} else {
					if (formeE2Ext.equals("N")) {
						nbPointsFormeDomExt = (float) serieVe1 * 10;
					} else {
						nbPointsFormeDomExt = (float) serieVe1 * 15;
					}
				}
			}
		}

		Float forme5DerniersMatchsE2 = getFormePointsForTeam(e2);
		Float forme5DerniersMatchsE1 = getFormePointsForTeam(e1);
		Float forme5DerniersMatchs = 0F;
		if (forme5DerniersMatchsE2 < forme5DerniersMatchsE1) {
			forme5DerniersMatchs = forme5DerniersMatchsE1 - forme5DerniersMatchsE2;
		}

		Float forme5DerniersMatchsE2Exterieur = getFormePointsExterieur(e2);
		Float forme5DerniersMatchsE1Domicile = getFormePointsDomicile(e1);
		Float forme5DerniersMatchsDomExt = 0F;
		if (forme5DerniersMatchsE2Exterieur < forme5DerniersMatchsE1Domicile) {
			forme5DerniersMatchsDomExt = forme5DerniersMatchsE1Domicile - forme5DerniersMatchsE2Exterieur;
		}

		Float pointsResultats = 0F;
		Float pourcentageDefaitesE2 = 0F;
		Float pourcentageVictoiresE1 = 0F;

		Integer jouesE2 = awayTeam.getNbMatchesPlayed();
		Integer perdusE2 = awayTeam.getNbLosses();
		if (jouesE2 > 0) {
			pourcentageDefaitesE2 = (float) ((perdusE2 / jouesE2)) * 100;
		}
		Integer jouesE1 = homeTeam.getNbMatchesPlayed();
		Integer gagnesE1 = homeTeam.getNbWins();
		if (jouesE1 > 0) {
			pourcentageVictoiresE1 = (float) ((gagnesE1 / jouesE1)) * 100;
		}
		pointsResultats = (pourcentageDefaitesE2 + pourcentageVictoiresE1) / 2;

		Float pointsResultatsDomExt = 0F;
		Float pourcentageDefaitesE2Ext = 0F;
		Float pourcentageVictoiresE1Dom = 0F;

		Integer jouesE2Ext = awayTeam.getNbAwayMatchesPlayed();
		Integer perdusE2Ext = awayTeam.getNbAwayLosses();
		if (jouesE2Ext > 0) {
			pourcentageDefaitesE2Ext = (float) ((perdusE2Ext / jouesE2Ext)) * 100;
		}
		Integer jouesE1Dom = homeTeam.getNbHomeMatchesPlayed();
		Integer gagnesE1Dom = homeTeam.getNbHomeWins();
		if (jouesE1Dom > 0) {
			pourcentageVictoiresE1Dom = (float) ((gagnesE1Dom / jouesE1Dom)) * 100;
		}
		pointsResultatsDomExt = (pourcentageDefaitesE2Ext + pourcentageVictoiresE1Dom) / 2;

		Boolean e1MieuxClassee = Ligue1Utils.convertToBoolean(matchEntity.getHomeTeamHasABetterStanding());
		Boolean e1MatchImportant = Ligue1Utils.convertToBoolean(matchEntity.getIsAnImportantGameForHomeTeam());
		Boolean e2MatchImportant = Ligue1Utils.convertToBoolean(matchEntity.getIsAnImportantGameForAwayTeam());

		Float pointsResultatsImportance = 0F;
		Float pointsResultatsImportanceDomExt = 0F;

		if (e2MatchImportant) {
			if (e1MatchImportant) {
				Float pourcentageDefaitesE2Importance = 0F;
				Float pourcentageVictoiresE1Importance = 0F;

				Integer jouesE2Importance = awayTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer perdusE2Importance = awayTeam.getNbLossesAgainstImportantOpponent();
				if (jouesE2Importance > 0) {
					pourcentageDefaitesE2Importance = (float) ((perdusE2Importance / jouesE2Importance)) * 100;
				}
				Integer jouesE1Importance = homeTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer gagnesE1Importance = homeTeam.getNbWinsAgainstImportantOpponent();
				if (jouesE1Importance > 0) {
					pourcentageVictoiresE1Importance = (float) ((gagnesE1Importance / jouesE1Importance)) * 100;
				}
				pointsResultatsImportance = (pourcentageDefaitesE2Importance + pourcentageVictoiresE1Importance) / 2;

				Float pourcentageDefaitesE2ExtImportance = 0F;
				Float pourcentageVictoiresE1DomImportance = 0F;

				Integer jouesE2ExtImportance = awayTeam.getNbMatchesPlayedAgainstImportantOpponentAway();
				Integer perdusE2ExtImportance = awayTeam.getNbLossesAgainstImportantOpponentAway();
				if (jouesE2ExtImportance > 0) {
					pourcentageDefaitesE2ExtImportance = (float) ((perdusE2ExtImportance / jouesE2ExtImportance)) * 100;
				}
				Integer jouesE1DomImportance = homeTeam.getNbMatchesPlayedAgainstImportantOpponentAtHome();
				Integer gagnesE1DomImportance = homeTeam.getNbWinsAgainstImportantOpponentAtHome();
				if (jouesE1DomImportance > 0) {
					pourcentageVictoiresE1DomImportance = (float) ((gagnesE1DomImportance / jouesE1DomImportance))
							* 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageDefaitesE2ExtImportance
						+ pourcentageVictoiresE1DomImportance) / 2;
			} else {
				Float pourcentageDefaitesE2ImportanceNon = 0F;
				Float pourcentageVictoiresE1Importance = 0F;

				Integer jouesE2ImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer perdusE2ImportanceNon = awayTeam.getNbLossesAgainstNormalOpponent();
				if (jouesE2ImportanceNon > 0) {
					pourcentageDefaitesE2ImportanceNon = (float) ((perdusE2ImportanceNon / jouesE2ImportanceNon)) * 100;
				}
				Integer jouesE1Importance = homeTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer gagnesE1Importance = homeTeam.getNbWinsAgainstImportantOpponent();
				if (jouesE1Importance > 0) {
					pourcentageVictoiresE1Importance = (float) ((gagnesE1Importance / jouesE1Importance)) * 100;
				}
				pointsResultatsImportance = (pourcentageDefaitesE2ImportanceNon + pourcentageVictoiresE1Importance) / 2;

				Float pourcentageDefaitesE2ExtImportanceNon = 0F;
				Float pourcentageVictoiresE1DomImportance = 0F;

				Integer jouesE2ExtImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponentAway();
				Integer perdusE2ExtImportanceNon = awayTeam.getNbLossesAgainstNormalOpponentAway();
				if (jouesE2ExtImportanceNon > 0) {
					pourcentageDefaitesE2ExtImportanceNon = (float) ((perdusE2ExtImportanceNon
							/ jouesE2ExtImportanceNon)) * 100;
				}
				Integer jouesE1DomImportance = homeTeam.getNbMatchesPlayedAgainstImportantOpponentAtHome();
				Integer gagnesE1DomImportance = homeTeam.getNbWinsAgainstImportantOpponentAtHome();
				if (jouesE1DomImportance > 0) {
					pourcentageVictoiresE1DomImportance = (float) ((gagnesE1DomImportance / jouesE1DomImportance))
							* 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageDefaitesE2ExtImportanceNon
						+ pourcentageVictoiresE1DomImportance) / 2;
			}
		} else {
			if (e1MatchImportant) {
				Float pourcentageDefaitesE2Importance = 0F;
				Float pourcentageVictoiresE1ImportanceNon = 0F;

				Integer jouesE2Importance = awayTeam.getNbMatchesPlayedAgainstImportantOpponent();
				Integer perdusE2Importance = awayTeam.getNbLossesAgainstImportantOpponent();
				if (jouesE2Importance > 0) {
					pourcentageDefaitesE2Importance = (float) ((perdusE2Importance / jouesE2Importance)) * 100;
				}
				Integer jouesE1ImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer gagnesE1ImportanceNon = homeTeam.getNbWinsAgainstNormalOpponent();
				if (jouesE1ImportanceNon > 0) {
					pourcentageVictoiresE1ImportanceNon = (float) ((gagnesE1ImportanceNon / jouesE1ImportanceNon))
							* 100;
				}
				pointsResultatsImportance = (pourcentageDefaitesE2Importance + pourcentageVictoiresE1ImportanceNon) / 2;

				Float pourcentageDefaitesE2ExtImportance = 0F;
				Float pourcentageVictoiresE1DomImportanceNon = 0F;

				Integer jouesE2ExtImportance = awayTeam.getNbMatchesPlayedAgainstImportantOpponentAway();
				Integer perdusE2ExtImportance = awayTeam.getNbLossesAgainstImportantOpponentAway();
				if (jouesE2ExtImportance > 0) {
					pourcentageDefaitesE2ExtImportance = (float) ((perdusE2ExtImportance / jouesE2ExtImportance)) * 100;
				}
				Integer jouesE1DomImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponentAtHome();
				Integer gagnesE1DomImportanceNon = homeTeam.getNbWinsAgainstNormalOpponentAtHome();
				if (jouesE1DomImportanceNon > 0) {
					pourcentageVictoiresE1DomImportanceNon = (float) ((gagnesE1DomImportanceNon
							/ jouesE1DomImportanceNon)) * 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageDefaitesE2ExtImportance
						+ pourcentageVictoiresE1DomImportanceNon) / 2;
			} else {
				Float pourcentageDefaitesE2ImportanceNon = 0F;
				Float pourcentageVictoiresE1ImportanceNon = 0F;

				Integer jouesE2ImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer perdusE2ImportanceNon = awayTeam.getNbLossesAgainstNormalOpponent();
				if (jouesE2ImportanceNon > 0) {
					pourcentageDefaitesE2ImportanceNon = (float) ((perdusE2ImportanceNon / jouesE2ImportanceNon)) * 100;
				}
				Integer jouesE1ImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponent();
				Integer gagnesE1ImportanceNon = homeTeam.getNbWinsAgainstNormalOpponent();
				if (jouesE1ImportanceNon > 0) {
					pourcentageVictoiresE1ImportanceNon = (float) ((gagnesE1ImportanceNon / jouesE1ImportanceNon))
							* 100;
				}
				pointsResultatsImportance = (pourcentageDefaitesE2ImportanceNon + pourcentageVictoiresE1ImportanceNon)
						/ 2;

				Float pourcentageDefaitesE2ExtImportanceNon = 0F;
				Float pourcentageVictoiresE1DomImportanceNon = 0F;

				Integer jouesE2ExtImportanceNon = awayTeam.getNbMatchesPlayedAgainstNormalOpponentAway();
				Integer perdus2ExtImportanceNon = awayTeam.getNbLossesAgainstNormalOpponentAway();
				if (jouesE2ExtImportanceNon > 0) {
					pourcentageDefaitesE2ExtImportanceNon = (float) ((perdus2ExtImportanceNon
							/ jouesE2ExtImportanceNon)) * 100;
				}
				Integer jouesE1DomImportanceNon = homeTeam.getNbMatchesPlayedAgainstNormalOpponentAtHome();
				Integer gagnesE1DomImportanceNon = homeTeam.getNbWinsAgainstNormalOpponentAtHome();
				if (jouesE1DomImportanceNon > 0) {
					pourcentageVictoiresE1DomImportanceNon = (float) ((gagnesE1DomImportanceNon
							/ jouesE1DomImportanceNon)) * 100;
				}
				pointsResultatsImportanceDomExt = (pourcentageDefaitesE2ExtImportanceNon
						+ pourcentageVictoiresE1DomImportanceNon) / 2;

			}
		}

		Float pointsResultatsClassement = 0F;
		Float pointsResultatsClassementDomExt = 0F;

		if (e1MieuxClassee) {

			Float pourcentageDefaitesE2ContreClassementSuperieur = 0F;
			Float pourcentageVictoiresE1ContreClassementInferieur = 0F;

			Integer jouesE2ContreClassementSuperieur = awayTeam.getNbMatchesPlayedAgainstStandingSuperior();
			Integer perdusE2ContreClassementSuperieur = awayTeam.getNbLossesAgainstStandingSuperior();
			if (jouesE2ContreClassementSuperieur > 0) {
				pourcentageDefaitesE2ContreClassementSuperieur = (float) ((perdusE2ContreClassementSuperieur
						/ jouesE2ContreClassementSuperieur)) * 100;
			}
			Integer jouesE1ContreClassementInferieur = homeTeam.getNbMatchesPlayedAgainstStandingInferior();
			Integer gagnesE1ContreClassementInferieur = homeTeam.getNbWinsAgainstStandingInferior();
			if (jouesE1ContreClassementInferieur > 0) {
				pourcentageVictoiresE1ContreClassementInferieur = (float) ((gagnesE1ContreClassementInferieur
						/ jouesE1ContreClassementInferieur)) * 100;
			}
			pointsResultatsClassement = (pourcentageDefaitesE2ContreClassementSuperieur
					+ pourcentageVictoiresE1ContreClassementInferieur) / 2;

			Float pourcentageDefaitesE2ContreClassementSuperieurExterieur = 0F;
			Float pourcentageVictoiresE1ContreClassementInferieurDomicile = 0F;

			Integer jouesE2ContreClassementSuperieurExterieur = awayTeam
					.getNbMatchesPlayedAgainstStandingSuperiorAway();
			Integer perdusE2ContreClassementSuperieurExterieur = awayTeam.getNbLossesAgainstStandingSuperiorAway();
			if (jouesE2ContreClassementSuperieurExterieur > 0) {
				pourcentageDefaitesE2ContreClassementSuperieurExterieur = (float) ((perdusE2ContreClassementSuperieurExterieur
						/ jouesE2ContreClassementSuperieurExterieur)) * 100;
			}
			Integer jouesE1ContreClassementInferieurDomicile = homeTeam
					.getNbMatchesPlayedAgainstStandingInferiorAtHome();
			Integer gagnesE1ContreClassementInferieurDomicile = homeTeam.getNbWinsAgainstStandingInferiorAtHome();
			if (jouesE1ContreClassementInferieurDomicile > 0) {
				pourcentageVictoiresE1ContreClassementInferieurDomicile = (float) ((gagnesE1ContreClassementInferieurDomicile
						/ jouesE1ContreClassementInferieurDomicile)) * 100;
			}
			pointsResultatsClassementDomExt = (pourcentageDefaitesE2ContreClassementSuperieurExterieur
					+ pourcentageVictoiresE1ContreClassementInferieurDomicile) / 2;

		} else {
			Float pourcentageDefaitesE2ContreClassementInferieur = 0F;
			Float pourcentageVictoiresE1ContreClassementSuperieur = 0F;

			Integer jouesE2ContreClassementInferieur = awayTeam.getNbMatchesPlayedAgainstStandingInferior();
			Integer perdusE2ContreClassementInferieur = awayTeam.getNbLossesAgainstStandingInferior();
			if (jouesE2ContreClassementInferieur > 0) {
				pourcentageDefaitesE2ContreClassementInferieur = (float) ((perdusE2ContreClassementInferieur
						/ jouesE2ContreClassementInferieur)) * 100;
			}
			Integer jouesE1ContreClassementSuperieur = homeTeam.getNbMatchesPlayedAgainstStandingSuperior();
			Integer gagnesE1ContreClassementSuperieur = homeTeam.getNbWinsAgainstStandingSuperior();
			if (jouesE1ContreClassementSuperieur > 0) {
				pourcentageVictoiresE1ContreClassementSuperieur = (float) ((gagnesE1ContreClassementSuperieur
						/ jouesE1ContreClassementSuperieur)) * 100;
			}
			pointsResultatsClassement = (pourcentageDefaitesE2ContreClassementInferieur
					+ pourcentageVictoiresE1ContreClassementSuperieur) / 2;

			Float pourcentageDefaitesE2ContreClassementInferieurExterieur = 0F;
			Float pourcentageVictoiresE1ContreClassementSuperieurDomicile = 0F;

			Integer jouesE2ContreClassementInferieurExterieur = awayTeam
					.getNbMatchesPlayedAgainstStandingInferiorAway();
			Integer perdusE2ContreClassementInferieurExterieur = awayTeam.getNbLossesAgainstStandingInferiorAway();
			if (jouesE2ContreClassementInferieurExterieur > 0) {
				pourcentageDefaitesE2ContreClassementInferieurExterieur = (float) ((perdusE2ContreClassementInferieurExterieur
						/ jouesE2ContreClassementInferieurExterieur)) * 100;
			}
			Integer jouesE1ContreClassementSuperieurDomicile = homeTeam
					.getNbMatchesPlayedAgainstStandingSuperiorAtHome();
			Integer gagnesE1ContreClassementSuperieurDomicile = homeTeam.getNbWinsAgainstStandingSuperiorAtHome();
			if (jouesE1ContreClassementSuperieurDomicile > 0) {
				pourcentageVictoiresE1ContreClassementSuperieurDomicile = (float) ((gagnesE1ContreClassementSuperieurDomicile
						/ jouesE1ContreClassementSuperieurDomicile)) * 100;
			}
			pointsResultatsClassementDomExt = (pourcentageDefaitesE2ContreClassementInferieurExterieur
					+ pourcentageVictoiresE1ContreClassementSuperieurDomicile) / 2;

		}

		Float pourcentageGlobalVDom = DatabaseConnection.getGlobalPercentage("VictoireEquipeDomicile");

		totalPoints = (config.getCoefficientConfrontationsDirectes() * pourcentageVDOMConfrontations)
				+ (config.getCoefficientClassement() * nbPointsRapportesParClassement)
				+ (config.getCoefficientClassementDomExt() * nbPointsRapportesParClassementsDomEtExt)
				+ (config.getCoefficientForme() * nbPointsForme) + (config.getCoefficientForme() * forme5DerniersMatchs)
				+ (config.getCoefficientFormeDomExt() * forme5DerniersMatchsDomExt)
				+ (config.getCoefficientResultats() * pointsResultats)
				+ (config.getCoefficientResultatsDomExt() * pointsResultatsDomExt)
				+ (config.getCoefficientFormeDomExt() * nbPointsFormeDomExt)
				+ (config.getCoefficientResultatsImp() * pointsResultatsImportance)
				+ (config.getCoefficientResultatsImp() * pointsResultatsImportanceDomExt)
				+ (config.getCoefficientClassement() * pointsResultatsClassement)
				+ (config.getCoefficientClassementDomExt() * pointsResultatsClassementDomExt)
				+ (config.getCoefficientGlobal() * pourcentageGlobalVDom);

		return totalPoints;
	}

	private static TreeMap<String, String> getTreeMapStatsMoyennes(Statistic statistiques, String match, String e1,
			String e2) {

		Map<String, String> stats = new HashMap<String, String>();

//		String[] score = match.split("-");
//		String e1 = score[0];
//		String e2 = score[1];

		Float moyenneButsDomicile = statistiques.getHomeTeamGoalAverage();
		Float moyenneButsMatch = statistiques.getGoalAverage();
		Float moyenneButsExtérieur = statistiques.getAwayTeamGoalAverage();

		stats.put("Moyenne de buts par match de l'équipe " + e2, moyenneButsExtérieur + " buts");
		stats.put("Moyenne de buts par match", moyenneButsMatch + " buts");
		stats.put("Moyenne de buts par match de l'équipe " + e1, moyenneButsDomicile + " buts");

		AverageScoredGoalsComparator comparator = new AverageScoredGoalsComparator(stats);
		TreeMap<String, String> mapTree = new TreeMap<String, String>(comparator);
		mapTree.putAll(stats);

		return mapTree;
	}

	private static void writeMapInWordDocument(String titreParagraphe, Map<String, String> statsGenerales,
			XWPFDocument document) {
		XWPFParagraph paragraphServeurConfig = document.createParagraph();
		XWPFRun runServeurConfig = paragraphServeurConfig.createRun();
		runServeurConfig.setText(titreParagraphe);
		runServeurConfig.setBold(true);
		runServeurConfig.setFontSize(14);
		for (Entry<String, String> serveurConfig : statsGenerales.entrySet()) {
			document.createParagraph().createRun()
					.setText(serveurConfig.getKey() + " : " + serveurConfig.getValue() + "\n");
		}
		document.createParagraph().createRun().setText("");
	}

	private static void writeTitleInWordDocument(String title, XWPFDocument document) {
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setText(title);
		run.setBold(true);
		run.setFontSize(14);
		document.createParagraph().createRun().setText("");
	}

	public static void main(String[] args) {
//		convertToPdf("C:\\perso\\Ligue1", "PSG-MHSC", 21);
//		try {
//			List<Configuration> allConfigs = new ArrayList<Configuration>();
////			Configuration config1 = new Configuration(1, 0.1F, 0.1F, 0.1F, 3F, 3F, 3F, 3F, 6F, 6F);
////			Configuration config2 = new Configuration(2, 6F, 0.1F, 0.1F, 3F, 3F, 3F, 3F, 6F, 6F);
////			Configuration config3 = new Configuration(3, 1F, 1F, 3F, 6F, 3F, 6F, 6F, 6F, 1F);
////			Configuration config4 = new Configuration(4, 1.5F, 1F, 2F, 1F, 2F, 1F, 2F, 2F, 1.5F);
////			Configuration config5 = new Configuration(5, 6F, 6F,0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 6F);
//			Configuration config1 = new Configuration(1, 1F, 2F, 3F, 4F, 5F, 6F, 7F, 8F, 9F);
//			Configuration config2 = new Configuration(2, 9F, 8F, 7F, 6F, 5F, 4F, 3F, 2F, 1F);
//			Configuration config3 = new Configuration(3, 1F, 9F, 1F, 9F, 1F, 9F, 1F, 9F, 1F);
//			Configuration config4 = new Configuration(4, 9F, 1F, 9F, 1F, 9F, 1F, 9F, 1F, 9F);
//			Configuration config5 = new Configuration(5, 1F, 1F, 1F, 1F, 1F, 1F, 1F, 1F, 1F);
//			allConfigs.add(config1);
//			allConfigs.add(config2);
//			allConfigs.add(config3);
//			allConfigs.add(config4);
//			allConfigs.add(config5);
//			Iterator<Configuration> iteratorConfigs = allConfigs.iterator();
//			while (iteratorConfigs.hasNext()) {
//				Configuration config = iteratorConfigs.next();
//				List<Statistic> allStats = new ArrayList<Statistic>();
//				Statistic statASSESCO = DatabaseConnection.getStatistic("FCN-SRFC");
//				Statistic statFCLNO = DatabaseConnection.getStatistic("FCL-ASM");
//				Statistic statFCNDFCO = DatabaseConnection.getStatistic("FCM-FCGB");
//				Statistic statLOSCFCGB = DatabaseConnection.getStatistic("SB29-OGCN");
//				Statistic statOGCNSRFC = DatabaseConnection.getStatistic("RCS-NO");
//				Statistic statOMASM = DatabaseConnection.getStatistic("OL-RCL");
//				Statistic statPSGOL = DatabaseConnection.getStatistic("LOSC-SCO");
//				Statistic statRCLMHSC = DatabaseConnection.getStatistic("ASSE-PSG");
//				Statistic statRCSFCM = DatabaseConnection.getStatistic("OM-MHSC");
//				Statistic statSB29SDR = DatabaseConnection.getStatistic("SDR-DFCO");
//				allStats.add(statASSESCO);
//				allStats.add(statSB29SDR);
//				allStats.add(statFCLNO);
//				allStats.add(statFCNDFCO);
//				allStats.add(statLOSCFCGB);
//				allStats.add(statOGCNSRFC);
//				allStats.add(statOMASM);
//				allStats.add(statPSGOL);
//				allStats.add(statRCLMHSC);
//				allStats.add(statRCSFCM);
//				Iterator<Statistic> iteratorMatchs = allStats.iterator();
//				int nbReussite = 0;
//				Float pourcentageReussite = 0F;
//
//				while (iteratorMatchs.hasNext()) {
//					Statistic stat = iteratorMatchs.next();
//					String match = stat.getMatch();
//					String[] teams = match.split("-");
//					String homeTeam = teams[0];
//					String awayTeam = teams[1];
//					Float homeTeamProbability = getProbabilityPointsForHomeTeam(stat, match, homeTeam, awayTeam,
//							config);
//					Float drawProbability = getDrawProbabilityPoints(stat, match, homeTeam, awayTeam, config);
//					Float awayTeamProbability = getProbabilityPointsForAwayTeam(stat, match, homeTeam, awayTeam,
//							config);
//					float total = homeTeamProbability + drawProbability + awayTeamProbability;
//					System.out.println("CONFIG " + config.getId() + " : \n");
//					System.out.println(homeTeam + " : "
//							+ (getProbabilityPointsForHomeTeam(stat, match, homeTeam, awayTeam, config) / total) * 100);
//					System.out.println("NUL : "
//							+ (getDrawProbabilityPoints(stat, match, homeTeam, awayTeam, config) / total) * 100);
//					System.out.println(awayTeam + " : "
//							+ (getProbabilityPointsForAwayTeam(stat, match, homeTeam, awayTeam, config) / total) * 100
//							+ "\n");
//					switch (stat.getMatch()) {
//					case "FCN-SRFC":
//						if (drawProbability > homeTeamProbability && drawProbability > awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "FCL-ASM":
//						if (awayTeamProbability > homeTeamProbability && drawProbability < awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "FCM-FCGB":
//						if (drawProbability > homeTeamProbability && drawProbability > awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "SB29-OGCN":
//						if (drawProbability < homeTeamProbability && homeTeamProbability > awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "RCS-NO":
//						if (drawProbability < homeTeamProbability && homeTeamProbability > awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "OL-RCL":
//						if (drawProbability < homeTeamProbability && homeTeamProbability > awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "LOSC-SCO":
//						if (awayTeamProbability > homeTeamProbability && drawProbability < awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "ASSE-PSG":
//						if (drawProbability > homeTeamProbability && drawProbability > awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "OM-MHSC":
//						if (drawProbability < homeTeamProbability && homeTeamProbability > awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					case "SDR-DFCO":
//						if (drawProbability > homeTeamProbability && drawProbability > awayTeamProbability) {
//							nbReussite++;
//						}
//						break;
//					}
//				}
//				pourcentageReussite = (nbReussite / 10F * 100);
//				System.out.println("-------------------------------------------------");
//				System.out.println(
//						"TAUX DE REUSSITE CONFIGURATION NUMERO " + config.getId() + " : " + pourcentageReussite + "%");
//				System.out.println("-------------------------------------------------");
//			}
//		} catch (NullStatisticException | InvalidNumberToConvertFromBooleanException | NullTeamException
//				| NullMatchException e) {
//			System.out.println("Erreur à la récupération des statistiques du match " + e.getMessage());
//		}
	}
}

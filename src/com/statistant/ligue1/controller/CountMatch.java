package com.statistant.ligue1.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.pojo.Match;
import com.statistant.ligue1.pojo.Team;
import com.statistant.ligue1.pojo.comparator.StandingComparator;
import com.statistant.ligue1.utils.Ligue1Utils;
import com.statistant.ligue1.view.InitializeWindow;

/**
 * Count the last recorded matches in tables "teams" and "confrontations" from
 * database "ligue1"
 * 
 * @author Thomas CHARMES
 */
public class CountMatch {

	public static int execute() throws NullConfrontationException, NullTeamException {
		int nbMatchsComptabilises = countMatchInStandingsAndOppositions();
		if (nbMatchsComptabilises > 0) {
			updateAllStandings();
		} else {
			Ligue1Utils.reportInfo("Aucun match n'a été comptabilisé, les classements n'ont pas été modifiés.");
			InitializeWindow.alertInfo("Aucun match n'a été comptabilisé, les classements n'ont pas été modifiés.");
		}
		return nbMatchsComptabilises;
	}

	/**
	 * Count matches for which the case "Recorded ?" isn't checked and the score
	 * isn't empty. Update these scores in the "confrontations" table from database
	 * "Ligue1" "Configurations"
	 * 
	 * @param boutonAction
	 * @throws NullTeamException
	 * @throws NullConfrontationException
	 */
	private static int countMatchInStandingsAndOppositions() throws NullConfrontationException, NullTeamException {

		Collection<Match> matchesToCount = DatabaseConnection.getMatchesToCount();

		if (matchesToCount == null || matchesToCount.isEmpty()) {
			Ligue1Utils.reportInfo("Tous les matchs ont été comptabilisés, le classement est à jour.");
			InitializeWindow.alertInfo("Tous les matchs ont été comptabilisés, le classement est à jour.");
			return 0;
		}

		int nbMatchsComptabilises = 0;

		for (Match match : matchesToCount) {

			String score = match.getScore();
			String e1 = match.getHomeTeamNickname();
			String e2 = match.getAwayTeamNickname();

			if (!Ligue1Utils.isEmpty(score)) {

				boolean executeMatch;
				try {
					executeMatch = checkAllFieldsAreOKAndExecuteMatch(match);
					if (!executeMatch) {
						Ligue1Utils.reportError("Erreur à la comptabilisation du match : " + e1 + " - " + e2);
						return 0;
					}
				} catch (NullConfrontationException | NullTeamException
						| InvalidNumberToConvertFromBooleanException e) {
					Ligue1Utils.reportError(
							"Erreur lors de l'exécution du match " + match.getId() + " : " + e.getMessage());
					e.printStackTrace();
					return -1;
				}
				Ligue1Utils.reportInfo("Le match " + e1 + " - " + e2 + " a été comptabilisé et enregistré.");
				nbMatchsComptabilises++;
			} else {
				Ligue1Utils.reportInfo("Le match " + e1 + " - " + e2 + " n'a pas encore été disputé.");
			}
		}
		if (nbMatchsComptabilises > 0)
			Ligue1Utils.reportInfo("Les scores ont bien été mis à jour dans la table Confrontations");
		return nbMatchsComptabilises;
	}

	/**
	 * Met à jour les classements suivants : Général, Domicile, Extérieur
	 */
	private static void updateAllStandings() {

		Collection<Team> teams = DatabaseConnection.getAllTeams();

		if (teams == null || teams.isEmpty()) {
			Ligue1Utils.reportError("La table ne contient aucune équipe. Tu peux t'inquiéter...");
			return;
		}

		updateStanding(teams, "classement");
		Ligue1Utils.reportInfo("Le classement général a bien été mis à jour.");

		updateStanding(teams, "classementDomicile");
		Ligue1Utils.reportInfo("Le classement domicile a bien été mis à jour.");

		updateStanding(teams, "classementExterieur");
		Ligue1Utils.reportInfo("Le classement extérieur a bien été mis à jour.");

		InitializeWindow.alertInfo("Tous les classements ont été mis à jour.");
	}

	private static void updateStanding(Collection<Team> teams, String standingType) {

		Map<String, int[]> ratiosEquipes = new HashMap<String, int[]>();
		StandingComparator comparator = new StandingComparator(ratiosEquipes);
		TreeMap<String, int[]> maptree = new TreeMap<String, int[]>(comparator);

		for (Team team : teams) {

			String surnom = team.getNickName();
			
			int nbPoints = 0;
			if (standingType.equals("classementDomicile"))
				nbPoints = team.getNbHomePoints();
			if (standingType.equals("classementExterieur"))
				nbPoints = team.getNbAwayPoints();
			if (standingType.equals("classement"))
				nbPoints = team.getNbPoints();
			
			int differenceButs = 0;
			if (standingType.equals("classementDomicile"))
				differenceButs = team.getHomeGoalAverage();
			if (standingType.equals("classementExterieur"))
				differenceButs = team.getAwayGoalAverage();
			if (standingType.equals("classement"))
				differenceButs = team.getGoalAverage();

			int[] stats = { nbPoints, differenceButs };
			ratiosEquipes.put(surnom, stats);
		}
		maptree.putAll(ratiosEquipes);
		Map<Integer, String> classementDefinitif = new HashMap<Integer, String>();
		int i = 1;
		for (Entry<String, int[]> entry : maptree.entrySet()) {
			classementDefinitif.put(i, entry.getKey());
			i++;
		}

		for (Entry<Integer, String> classement : classementDefinitif.entrySet()) {

			Team team;
			try {
				team = DatabaseConnection.getTeamByNickname(classement.getValue());
				if (standingType.equals("classementDomicile"))
					team.setHomeStanding(classement.getKey());
				if (standingType.equals("classementExterieur"))
					team.setAwayStanding(classement.getKey());
				if (standingType.equals("classement"))
					team.setStanding(classement.getKey());
				DatabaseConnection.createOrUpdateTeam(team);
			} catch (NullTeamException e) {
				Ligue1Utils.reportError("Erreur à la récupération de l'équipe : " + e.getMessage());
				e.printStackTrace();
				return;
			}
		}
	}

	/**
	 * Vérifie que les données du formulaire sont cohérentes, met à jour les données
	 * des classements et des confrontations dans les tables correspondantes en
	 * fonction des cases cochées
	 * 
	 * @param wi
	 * @param controller
	 * @param wm
	 * @return
	 * @throws NullConfrontationException
	 * @throws NullTeamException
	 * @throws InvalidNumberToConvertFromBooleanException
	 */
	public static boolean checkAllFieldsAreOKAndExecuteMatch(Match match)
			throws NullConfrontationException, NullTeamException, InvalidNumberToConvertFromBooleanException {

		if (match != null) {
			String team1NickName = match.getHomeTeamNickname();
			String team2NickName = match.getAwayTeamNickname();
			Team team1 = null;
			Team team2 = null;

			if (!Ligue1Utils.isEmpty(team1NickName) && !Ligue1Utils.isEmpty(team2NickName)) {

				team1 = DatabaseConnection.getTeamByNickname(team1NickName);
				team2 = DatabaseConnection.getTeamByNickname(team2NickName);

				if (team1 == null || team2 == null) {
					InitializeWindow.alertError("Attention ! Au moins une des équipes n'évolue pas en Ligue 1 ! ");
					return false;
				}

				if (team1.getNickName().equals(team2.getNickName())) {
					InitializeWindow.alertError("Attention ! Une équipe ne peut pas s'auto-affronter ! ");
					return false;
				}

				boolean victoireEquipe1 = Ligue1Utils.convertToBoolean(match.getHomeTeamWin());
				boolean victoireEquipe2 = Ligue1Utils.convertToBoolean(match.getAwayTeamWin());
				boolean nul = Ligue1Utils.convertToBoolean(match.getDraw());

				boolean equipe1mieuxClassee = Ligue1Utils.convertToBoolean(match.getHomeTeamHasABetterStanding());
				boolean matchImportantEquipe1 = Ligue1Utils.convertToBoolean(match.getIsAnImportantGameForHomeTeam());
				boolean matchImportantEquipe2 = Ligue1Utils.convertToBoolean(match.getIsAnImportantGameForAwayTeam());

				String score = match.getScore();

				if (!score.isEmpty()) {

					if (!Ligue1Utils.isScoreWellFormed(score)) {
						InitializeWindow
								.alertError("Le format du score doit être x-x avec x un entier compris entre 0 et 9!");
					}

					if (TeamController.verifGenerale()) {
						Ligue1Utils.setResult(team1, team2, equipe1mieuxClassee, matchImportantEquipe1,
								matchImportantEquipe2, victoireEquipe1, nul, victoireEquipe2, score);
						match.setCountMatch(1);
						DatabaseConnection.createOrUpdateMatch(match);
					} else {
						return false;
					}

					if (!TeamController.verif(team1) || !TeamController.verif(team2)) {
						return false;
					}
				}

				Confrontation confrontation = DatabaseConnection.getConfrontation(team1NickName + "-" + team2NickName);
				Confrontation reversedConfrontation = DatabaseConnection
						.getConfrontation(team2NickName + "-" + team1NickName);

				if (confrontation != null && reversedConfrontation != null) {

					String[] split = score.split("-");

					String recent1 = confrontation.getRecent1();
					String recent2 = confrontation.getRecent2();
					String recent3 = confrontation.getRecent3();
					String recent4 = confrontation.getRecent4();

					confrontation.setRecent5(recent4);
					confrontation.setRecent4(recent3);
					confrontation.setRecent3(recent2);
					confrontation.setRecent2(recent1);
					confrontation.setRecent1(score);

					if (confrontation.getRecent1().equals("null") || Ligue1Utils.isEmpty(confrontation.getRecent1()))
						confrontation.setRecent1("");
					if (confrontation.getRecent2().equals("null") || Ligue1Utils.isEmpty(confrontation.getRecent2()))
						confrontation.setRecent2("");
					if (confrontation.getRecent3().equals("null") || Ligue1Utils.isEmpty(confrontation.getRecent3()))
						confrontation.setRecent3("");
					if (confrontation.getRecent4().equals("null") || Ligue1Utils.isEmpty(confrontation.getRecent4()))
						confrontation.setRecent4("");
					if (confrontation.getRecent5().equals("null") || Ligue1Utils.isEmpty(confrontation.getRecent5()))
						confrontation.setRecent5("");
					
					DatabaseConnection.createOrUpdateConfrontation(confrontation);
					StatisticController.setStatistiques(confrontation);
					
					String reversedScore = split[1] + "-" + split[0];
					String reversedRecent1 = reversedConfrontation.getRecent1();
					String reversedRecent2 = reversedConfrontation.getRecent2();
					String reversedRecent3 = reversedConfrontation.getRecent3();
					String reversedRecent4 = reversedConfrontation.getRecent4();

					reversedConfrontation.setRecent5(reversedRecent4);
					reversedConfrontation.setRecent4(reversedRecent3);
					reversedConfrontation.setRecent3(reversedRecent2);
					reversedConfrontation.setRecent2(reversedRecent1);
					reversedConfrontation.setRecent1(reversedScore);

					if (reversedConfrontation.getRecent1().equals("null") || Ligue1Utils.isEmpty(reversedConfrontation.getRecent1()))
						reversedConfrontation.setRecent1("");
					if (reversedConfrontation.getRecent2().equals("null") || Ligue1Utils.isEmpty(reversedConfrontation.getRecent2()))
						reversedConfrontation.setRecent2("");
					if (reversedConfrontation.getRecent3().equals("null") || Ligue1Utils.isEmpty(reversedConfrontation.getRecent3()))
						reversedConfrontation.setRecent3("");
					if (reversedConfrontation.getRecent4().equals("null") || Ligue1Utils.isEmpty(reversedConfrontation.getRecent4()))
						reversedConfrontation.setRecent4("");

					DatabaseConnection.createOrUpdateConfrontation(reversedConfrontation);
					StatisticController.setStatistiques(reversedConfrontation);
					
				}
			} else {
				InitializeWindow.alertError("Attention ! L'une des deux équipes n'est pas renseignée ! ");
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

}

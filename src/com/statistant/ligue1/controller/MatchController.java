package com.statistant.ligue1.controller;

import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.utils.Ligue1Utils;

/**
 * Check if the data written by the user are allowed.
 * 
 * @author techn
 *
 */
public class MatchController {

	public static void checkScore(String resultat) throws ScoreFormatException {
		if (Ligue1Utils.isEmpty(resultat)) {
			throw new ScoreFormatException("Le champ du formulaire \"score\" est vide");
		} else {
			if (!Ligue1Utils.isScoreWellFormed(resultat)) {
				throw new ScoreFormatException(
						"Le format du score n'est pas correct. Merci de saisir un score au format x-x avec x un entier compris entre 0 et 9.");
			}
		}
	}

	/**
	 * Vérifie la cohérence des données du formulaire (nombre de cases cochées,
	 * surnoms des équipes)
	 * 
	 * @return
	 * @throws NullResultatException 
	 * @throws UnhandledResultatException 
	 */
	public static void checkOnlyOneResult(Integer integer, Integer integer2,
			Integer integer3) throws NullResultatException, UnhandledResultatException {
		if (integer == null || integer2 == null || integer3 == null) {
			throw new NullResultatException("Merci de cliquer sur le bouton SCORE SAISI pour remplir les champs liés au résultat");
		}
		if (integer == integer2 && integer2 == integer3) {
			throw new UnhandledResultatException("Merci de cliquer sur le bouton SCORE SAISI pour remplir les champs liés au résultat");
		}
	}

	public static void checkTeamsAreOK(String team1, String team2) throws NullTeamException, SameTeamsException {
		if (Ligue1Utils.isEmpty(team1) && Ligue1Utils.isEmpty(team2)) {
			throw new NullTeamException("Merci de renseigner les noms des deux équipes");
		}
		if (Ligue1Utils.isEmpty(team1)) {
			throw new NullTeamException("Merci de renseigner le nom de l'équipe à domicile");
		}
		if (Ligue1Utils.isEmpty(team2)) {
			throw new NullTeamException("Merci de renseigner le nom de l'équipe à l'extérieur");
		}
		if (team1.equals(team2)) {
			throw new SameTeamsException(
					"Attention ! Une équipe ne peut pas s'auto-affronter ! Merci de réitérer la saisie");
		}
		try {
			DatabaseConnection.getTeamByNickname(team1);
		} catch (NullTeamException e) {
			throw new NullTeamException("L'équipe "+team1+" n'évolue pas en Ligue 1. Merci de réitérer la saisie");
		}
		try {
			DatabaseConnection.getTeamByNickname(team2);
		} catch (NullTeamException e) {
			throw new NullTeamException("L'équipe "+team2+" n'évolue pas en Ligue 1. Merci de réitérer la saisie");
		}
	}

	/**
	 * Comptabilise le match Ou réinitialise la saison Ou génère le rapport détaillé
	 * de la rencontre
	 */
	public boolean onBeforeSubmit(String action) {
		Ligue1Utils.resetAllSeason();
		return true;
	}

	public static void checkScoreFormat(String score) throws ScoreFormatException {
		if (!Ligue1Utils.isScoreWellFormed(score)) {
			throw new ScoreFormatException("Erreur de format du score dans le formulaire !");
		}
	}

	public static void checkJourneyNumber(String journey) throws OutOfBounceJourneyNumberException {
		if (! isAnInteger(journey)) {
			throw new NumberFormatException("Le champ journée doit être une valeur numérique. Merci de réitérer la saisie");
		}
		int journee = Integer.parseInt(journey);
		if (journee < 1 || journee > 38) {
			throw new OutOfBounceJourneyNumberException(
					"Le champ journée doit être compris entre 1 et 38 inclus. Merci de réitérer la saisie");
		}
	}

	private static boolean isAnInteger(String journey) {
		try {
			Integer.parseInt(journey);
		}
		catch (NumberFormatException e) {
			return false;	
		}
		return true;
	}

	public static void checkMatchParameters(String isImportantGameForHomeTeam, String isImportantGameForAwayTeam,
			String homeTeamHasBetterStanding) throws NullMatchParametersException {
		if (Ligue1Utils.isEmpty(isImportantGameForHomeTeam) || Ligue1Utils.isEmpty(isImportantGameForAwayTeam) || Ligue1Utils.isEmpty(homeTeamHasBetterStanding)) {
			throw new NullMatchParametersException("Merci de cliquer sur le bouton EQUIPES SAISIES pour remplir les champs liés aux paramètres du match");
		}
		
	}

}

package com.statistant.ligue1.controller;

import java.util.Collection;

import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.pojo.Team;

public class TeamController {
	
	public static boolean verifGenerale() throws NumberFormatException {
		Collection<Team> allTeams = DatabaseConnection.getAllTeams();
		for (Team team : allTeams) {
			if (!verif(team))
				return false;
		}
		return true;
	}
	
	public static boolean verif(Team team) throws NumberFormatException {
		if (team.getNbWins() * 3 + team.getNbDraws() != team.getNbPoints())
			return false;
		if (team.getNbWins() + team.getNbDraws() + team.getNbLosses() != team.getNbMatchesPlayed())
			return false;
		if (team.getNbHomeWins() + team.getNbAwayWins() != team.getNbWins())
			return false;
		if (team.getNbHomeWins() * 3 + team.getNbHomeDraws() != team.getNbHomePoints())
			return false;
		if (team.getNbAwayWins() * 3 + team.getNbAwayDraws() != team.getNbAwayPoints())
			return false;
		if (team.getNbHomeMatchesPlayed() + team.getNbAwayMatchesPlayed() != team.getNbMatchesPlayed())
			return false;
		if (team.getNbHomeDraws() + team.getNbAwayDraws() != team.getNbDraws())
			return false;
		if (team.getNbHomeLosses() + team.getNbAwayLosses() != team.getNbLosses())
			return false;
		if (team.getNbHomeWins() + team.getNbHomeDraws() + team.getNbHomeLosses() != team.getNbHomeMatchesPlayed())
			return false;
		if (team.getNbAwayWins() + team.getNbAwayDraws() + team.getNbAwayLosses() != team.getNbAwayMatchesPlayed())
			return false;
		if (team.getNbWinsAgainstStandingSuperior() + team.getNbDrawsAgainstStandingSuperior()
				+ team.getNbLossesAgainstStandingSuperior() != team.getNbMatchesPlayedAgainstStandingSuperior())
			return false;
		if (team.getNbWinsAgainstStandingInferior() + team.getNbDrawsAgainstStandingInferior()
				+ team.getNbLossesAgainstStandingInferior() != team.getNbMatchesPlayedAgainstStandingInferior())
			return false;
		if (team.getNbWinsAgainstStandingSuperiorAtHome() + team.getNbWinsAgainstStandingSuperiorAway() != team
				.getNbWinsAgainstStandingSuperior())
			return false;
		if (team.getNbDrawsAgainstStandingSuperiorAtHome() + team.getNbDrawsAgainstStandingSuperiorAway() != team
				.getNbDrawsAgainstStandingSuperior())
			return false;
		if (team.getNbLossesAgainstStandingSuperiorAtHome() + team.getNbLossesAgainstStandingSuperiorAway() != team
				.getNbLossesAgainstStandingSuperior())
			return false;
		if (team.getNbWinsAgainstStandingInferiorAtHome() + team.getNbWinsAgainstStandingInferiorAway() != team
				.getNbWinsAgainstStandingInferior())
			return false;
		if (team.getNbDrawsAgainstStandingInferiorAtHome() + team.getNbDrawsAgainstStandingInferiorAway() != team
				.getNbDrawsAgainstStandingInferior())
			return false;
		if (team.getNbLossesAgainstStandingInferiorAtHome() + team.getNbLossesAgainstStandingInferiorAway() != team
				.getNbLossesAgainstStandingInferior())
			return false;
		if (team.getNbWinsAgainstStandingSuperior() + team.getNbWinsAgainstStandingInferior() != team.getNbWins())
			return false;
		if (team.getNbDrawsAgainstStandingSuperior() + team.getNbDrawsAgainstStandingInferior() != team.getNbDraws())
			return false;
		if (team.getNbLossesAgainstStandingSuperior() + team.getNbLossesAgainstStandingInferior() != team.getNbLosses())
			return false;
		if (team.getNbWinsAgainstImportantOpponent() + team.getNbDrawsAgainstImportantOpponent()
				+ team.getNbLossesAgainstImportantOpponent() != team.getNbMatchesPlayedAgainstImportantOpponent())
			return false;
		if (team.getNbWinsAgainstNormalOpponent() + team.getNbDrawsAgainstNormalOpponent()
				+ team.getNbLossesAgainstNormalOpponent() != team.getNbMatchesPlayedAgainstNormalOpponent())
			return false;
		if (team.getNbWinsAgainstImportantOpponentAtHome() + team.getNbWinsAgainstImportantOpponentAway() != team
				.getNbWinsAgainstImportantOpponent())
			return false;
		if (team.getNbDrawsAgainstImportantOpponentAtHome() + team.getNbDrawsAgainstImportantOpponentAway() != team
				.getNbDrawsAgainstImportantOpponent())
			return false;
		if (team.getNbLossesAgainstImportantOpponentAtHome() + team.getNbLossesAgainstImportantOpponentAway() != team
				.getNbLossesAgainstImportantOpponent())
			return false;
		if (team.getNbWinsAgainstNormalOpponentAtHome() + team.getNbWinsAgainstNormalOpponentAway() != team
				.getNbWinsAgainstNormalOpponent())
			return false;
		if (team.getNbDrawsAgainstNormalOpponentAtHome() + team.getNbDrawsAgainstNormalOpponentAway() != team
				.getNbDrawsAgainstNormalOpponent())
			return false;
		if (team.getNbLossesAgainstNormalOpponentAtHome() + team.getNbLossesAgainstNormalOpponentAway() != team
				.getNbLossesAgainstNormalOpponent())
			return false;
		if (team.getNbWinsAgainstImportantOpponent() + team.getNbWinsAgainstNormalOpponent() != team.getNbWins())
			return false;
		if (team.getNbDrawsAgainstImportantOpponent() + team.getNbDrawsAgainstNormalOpponent() != team.getNbDraws())
			return false;
		if (team.getNbLossesAgainstImportantOpponent() + team.getNbLossesAgainstNormalOpponent() != team.getNbLosses())
			return false;
		return true;
	}

}

package com.statistant.ligue1.controller;

import java.util.Collection;

import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.pojo.Statistic;
import com.statistant.ligue1.utils.Ligue1Utils;

public class StatisticController {

	public static void execute() {
		Collection<Confrontation> allConfrontations = DatabaseConnection.getAllConfrontations();
		if (allConfrontations.isEmpty() || allConfrontations == null) {
			Ligue1Utils.reportInfo("Aucune confrontation n'est enregistrée dans la table");
			return;
		}
		Ligue1Utils.reportInfo("Les confrontations ont bien été récupérées");
		for (Confrontation confrontation : allConfrontations) {
			if (confrontation.getNbConfrontations() == 0) {
				Ligue1Utils.reportInfo(
						"La confrontation " + confrontation.getMatch() + " est inédite et ne comporte pas de stats !");
			} else {
				try {
					setStatistiques(confrontation);
				} catch (NullConfrontationException e) {
					Ligue1Utils.reportError("Erreur lors de la mise à jour des statistiques : " + e.getMessage());
					e.printStackTrace();
					return;
				}
				Ligue1Utils.reportInfo(
						"La table \"Statistiques\" a été mise à jour avec les informations de la confrontation "
								+ confrontation.getMatch());
			}
		}
	}

	public static void setStatistiques(Confrontation confrontation) throws NullConfrontationException {

		if (confrontation == null) {
			throw new NullConfrontationException("Confrontation nulle");
		}
		Statistic statistique;
		statistique = new Statistic(confrontation.getMatch());

		if (Ligue1Utils.isEmpty(confrontation.getRecent1()) && Ligue1Utils.isEmpty(confrontation.getRecent2())
				&& Ligue1Utils.isEmpty(confrontation.getRecent3()) && Ligue1Utils.isEmpty(confrontation.getRecent4())
				&& Ligue1Utils.isEmpty(confrontation.getRecent5())) {
			statistique.setHomeTeamGoalAverage(0);
			statistique.setAwayTeamGoalAverage(0);
			statistique.setGoalAverage(0);
			statistique.setHomeTeamScoredLessThan05GoalPercentage(0);
			statistique.setHomeTeamScoredLessThan15GoalPercentage(0);
			statistique.setHomeTeamScoredLessThan25GoalsPercentage(0);
			statistique.setHomeTeamScoredLessThan35GoalsPercentage(0);
			statistique.setHomeTeamScoredLessThan45GoalsPercentage(0);
			statistique.setHomeTeamScoredMoreThan05GoalPercentage(0);
			statistique.setHomeTeamScoredMoreThan15GoalPercentage(0);
			statistique.setHomeTeamScoredMoreThan25GoalsPercentage(0);
			statistique.setHomeTeamScoredMoreThan35GoalsPercentage(0);
			statistique.setHomeTeamScoredMoreThan45GoalsPercentage(0);
			statistique.setAwayTeamScoredLessThan05GoalPercentage(0);
			statistique.setAwayTeamScoredLessThan15GoalPercentage(0);
			statistique.setAwayTeamScoredLessThan25GoalsPercentage(0);
			statistique.setAwayTeamScoredLessThan35GoalsPercentage(0);
			statistique.setAwayTeamScoredLessThan45GoalsPercentage(0);
			statistique.setAwayTeamScoredMoreThan05GoalPercentage(0);
			statistique.setAwayTeamScoredMoreThan15GoalPercentage(0);
			statistique.setAwayTeamScoredMoreThan25GoalsPercentage(0);
			statistique.setAwayTeamScoredMoreThan35GoalsPercentage(0);
			statistique.setAwayTeamScoredMoreThan45GoalsPercentage(0);
			statistique.setBothTeamsToScorePercentage(0);
			statistique.setLessThan05GoalPercentage(0);
			statistique.setLessThan15GoalPercentage(0);
			statistique.setLessThan25GoalsPercentage(0);
			statistique.setLessThan35GoalsPercentage(0);
			statistique.setLessThan45GoalsPercentage(0);
			statistique.setMoreThan05GoalPercentage(0);
			statistique.setMoreThan15GoalPercentage(0);
			statistique.setMoreThan25GoalsPercentage(0);
			statistique.setMoreThan35GoalsPercentage(0);
			statistique.setMoreThan45GoalsPercentage(0);
			statistique.setHomeTeamConcededExactly0GoalPercentage(0);
			statistique.setHomeTeamConcededExactly1GoalPercentage(0);
			statistique.setHomeTeamConcededExactly2GoalsPercentage(0);
			statistique.setHomeTeamConcededExactly3GoalsPercentage(0);
			statistique.setHomeTeamConcededExactly4GoalsPercentage(0);
			statistique.setHomeTeamConcededExactly5GoalsPercentage(0);
			statistique.setHomeTeamScoredExactly0GoalPercentage(0);
			statistique.setHomeTeamScoredExactly1GoalPercentage(0);
			statistique.setHomeTeamScoredExactly2GoalsPercentage(0);
			statistique.setHomeTeamScoredExactly3GoalsPercentage(0);
			statistique.setHomeTeamScoredExactly4GoalsPercentage(0);
			statistique.setHomeTeamScoredExactly5GoalsPercentage(0);
			statistique.setAwayTeamConcededExactly0GoalPercentage(0);
			statistique.setAwayTeamConcededExactly1GoalPercentage(0);
			statistique.setAwayTeamConcededExactly2GoalsPercentage(0);
			statistique.setAwayTeamConcededExactly3GoalsPercentage(0);
			statistique.setAwayTeamConcededExactly4GoalsPercentage(0);
			statistique.setAwayTeamConcededExactly5GoalsPercentage(0);
			statistique.setAwayTeamScoredExactly0GoalPercentage(0);
			statistique.setAwayTeamScoredExactly1GoalPercentage(0);
			statistique.setAwayTeamScoredExactly2GoalsPercentage(0);
			statistique.setAwayTeamScoredExactly3GoalsPercentage(0);
			statistique.setAwayTeamScoredExactly4GoalsPercentage(0);
			statistique.setAwayTeamScoredExactly5GoalsPercentage(0);
			statistique.setDrawPercentage(0);
			statistique.setHomeTeamWinPercentage(0);
			statistique.setAwayTeamWinPercentage(0);
			statistique.setHomeTeamWinByExactly1GoalPercentage(0);
			statistique.setHomeTeamWinByExactly2GoalsPercentage(0);
			statistique.setHomeTeamWinByExactly3GoalsPercentage(0);
			statistique.setHomeTeamWinByExactly4GoalsPercentage(0);
			statistique.setHomeTeamWinByExactly5GoalsPercentage(0);
			statistique.setHomeTeamWinByExactly6GoalsPercentage(0);
			statistique.setHomeTeamWinByExactly7GoalsPercentage(0);
			statistique.setHomeTeamWinByExactly8GoalsPercentage(0);
			statistique.setHomeTeamWinByExactly9GoalsPercentage(0);
			statistique.setAwayTeamWinByExactly1GoalPercentage(0);
			statistique.setAwayTeamWinByExactly2GoalsPercentage(0);
			statistique.setAwayTeamWinByExactly3GoalsPercentage(0);
			statistique.setAwayTeamWinByExactly4GoalsPercentage(0);
			statistique.setAwayTeamWinByExactly5GoalsPercentage(0);
			statistique.setAwayTeamWinByExactly6GoalsPercentage(0);
			statistique.setAwayTeamWinByExactly7GoalsPercentage(0);
			statistique.setAwayTeamWinByExactly8GoalsPercentage(0);
			statistique.setAwayTeamWinByExactly9GoalsPercentage(0);
		} else {
			statistique.setHomeTeamGoalAverage(confrontation.getHomeTeamAverageGoals());
			statistique.setAwayTeamGoalAverage(confrontation.getAwayTeamAverageGoals());
			statistique.setGoalAverage(confrontation.getAverageGoals());
			statistique.setHomeTeamScoredLessThan05GoalPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithLessThanXGoals(0.5F));
			statistique.setHomeTeamScoredLessThan15GoalPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithLessThanXGoals(1.5F));
			statistique.setHomeTeamScoredLessThan25GoalsPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithLessThanXGoals(2.5F));
			statistique.setHomeTeamScoredLessThan35GoalsPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithLessThanXGoals(3.5F));
			statistique.setHomeTeamScoredLessThan45GoalsPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithLessThanXGoals(4.5F));
			statistique.setHomeTeamScoredMoreThan05GoalPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithMoreThanXGoals(0.5F));
			statistique.setHomeTeamScoredMoreThan15GoalPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithMoreThanXGoals(1.5F));
			statistique.setHomeTeamScoredMoreThan25GoalsPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithMoreThanXGoals(2.5F));
			statistique.setHomeTeamScoredMoreThan35GoalsPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithMoreThanXGoals(3.5F));
			statistique.setHomeTeamScoredMoreThan45GoalsPercentage(
					confrontation.getHomeTeamPercentageOfMatchesWithMoreThanXGoals(4.5F));
			statistique.setAwayTeamScoredLessThan05GoalPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithLessThanXGoals(0.5F));
			statistique.setAwayTeamScoredLessThan15GoalPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithLessThanXGoals(1.5F));
			statistique.setAwayTeamScoredLessThan25GoalsPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithLessThanXGoals(2.5F));
			statistique.setAwayTeamScoredLessThan35GoalsPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithLessThanXGoals(3.5F));
			statistique.setAwayTeamScoredLessThan45GoalsPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithLessThanXGoals(4.5F));
			statistique.setAwayTeamScoredMoreThan05GoalPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithMoreThanXGoals(0.5F));
			statistique.setAwayTeamScoredMoreThan15GoalPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithMoreThanXGoals(1.5F));
			statistique.setAwayTeamScoredMoreThan25GoalsPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithMoreThanXGoals(2.5F));
			statistique.setAwayTeamScoredMoreThan35GoalsPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithMoreThanXGoals(3.5F));
			statistique.setAwayTeamScoredMoreThan45GoalsPercentage(
					confrontation.getAwayTeamPercentageOfMatchesWithMoreThanXGoals(4.5F));
			statistique.setBothTeamsToScorePercentage(confrontation.getBothTeamScoredPercentage());
			statistique.setLessThan05GoalPercentage(confrontation.getPercentageOfMatchesWithLessThanXGoals(0.5F));
			statistique.setLessThan15GoalPercentage(confrontation.getPercentageOfMatchesWithLessThanXGoals(1.5F));
			statistique.setLessThan25GoalsPercentage(confrontation.getPercentageOfMatchesWithLessThanXGoals(2.5F));
			statistique.setLessThan35GoalsPercentage(confrontation.getPercentageOfMatchesWithLessThanXGoals(3.5F));
			statistique.setLessThan45GoalsPercentage(confrontation.getPercentageOfMatchesWithLessThanXGoals(4.5F));
			statistique.setMoreThan05GoalPercentage(confrontation.getPercentageOfMatchesWithMoreThanXGoals(0.5F));
			statistique.setMoreThan15GoalPercentage(confrontation.getPercentageOfMatchesWithMoreThanXGoals(1.5F));
			statistique.setMoreThan25GoalsPercentage(confrontation.getPercentageOfMatchesWithMoreThanXGoals(2.5F));
			statistique.setMoreThan35GoalsPercentage(confrontation.getPercentageOfMatchesWithMoreThanXGoals(3.5F));
			statistique.setMoreThan45GoalsPercentage(confrontation.getPercentageOfMatchesWithMoreThanXGoals(4.5F));
			statistique.setHomeTeamConcededExactly0GoalPercentage(
					confrontation.getHomeTeamConcedeExactlyXGoalsPercentage(0));
			statistique.setHomeTeamConcededExactly1GoalPercentage(
					confrontation.getHomeTeamConcedeExactlyXGoalsPercentage(1));
			statistique.setHomeTeamConcededExactly2GoalsPercentage(
					confrontation.getHomeTeamConcedeExactlyXGoalsPercentage(2));
			statistique.setHomeTeamConcededExactly3GoalsPercentage(
					confrontation.getHomeTeamConcedeExactlyXGoalsPercentage(3));
			statistique.setHomeTeamConcededExactly4GoalsPercentage(
					confrontation.getHomeTeamConcedeExactlyXGoalsPercentage(4));
			statistique.setHomeTeamConcededExactly5GoalsPercentage(
					confrontation.getHomeTeamConcedeExactlyXGoalsPercentage(5));
			statistique
					.setHomeTeamScoredExactly0GoalPercentage(confrontation.getHomeTeamScoredExactlyXGoalsPercentage(0));
			statistique
					.setHomeTeamScoredExactly1GoalPercentage(confrontation.getHomeTeamScoredExactlyXGoalsPercentage(1));
			statistique.setHomeTeamScoredExactly2GoalsPercentage(
					confrontation.getHomeTeamScoredExactlyXGoalsPercentage(2));
			statistique.setHomeTeamScoredExactly3GoalsPercentage(
					confrontation.getHomeTeamScoredExactlyXGoalsPercentage(3));
			statistique.setHomeTeamScoredExactly4GoalsPercentage(
					confrontation.getHomeTeamScoredExactlyXGoalsPercentage(4));
			statistique.setHomeTeamScoredExactly5GoalsPercentage(
					confrontation.getHomeTeamScoredExactlyXGoalsPercentage(5));
			statistique.setAwayTeamConcededExactly0GoalPercentage(
					confrontation.getAwayTeamConcedeExactlyXGoalsPercentage(0));
			statistique.setAwayTeamConcededExactly1GoalPercentage(
					confrontation.getAwayTeamConcedeExactlyXGoalsPercentage(1));
			statistique.setAwayTeamConcededExactly2GoalsPercentage(
					confrontation.getAwayTeamConcedeExactlyXGoalsPercentage(2));
			statistique.setAwayTeamConcededExactly3GoalsPercentage(
					confrontation.getAwayTeamConcedeExactlyXGoalsPercentage(3));
			statistique.setAwayTeamConcededExactly4GoalsPercentage(
					confrontation.getAwayTeamConcedeExactlyXGoalsPercentage(4));
			statistique.setAwayTeamConcededExactly5GoalsPercentage(
					confrontation.getAwayTeamConcedeExactlyXGoalsPercentage(5));
			statistique
					.setAwayTeamScoredExactly0GoalPercentage(confrontation.getAwayTeamScoredExactlyXGoalsPercentage(0));
			statistique
					.setAwayTeamScoredExactly1GoalPercentage(confrontation.getAwayTeamScoredExactlyXGoalsPercentage(1));
			statistique.setAwayTeamScoredExactly2GoalsPercentage(
					confrontation.getAwayTeamScoredExactlyXGoalsPercentage(2));
			statistique.setAwayTeamScoredExactly3GoalsPercentage(
					confrontation.getAwayTeamScoredExactlyXGoalsPercentage(3));
			statistique.setAwayTeamScoredExactly4GoalsPercentage(
					confrontation.getAwayTeamScoredExactlyXGoalsPercentage(4));
			statistique.setAwayTeamScoredExactly5GoalsPercentage(
					confrontation.getAwayTeamScoredExactlyXGoalsPercentage(5));
			statistique.setDrawPercentage(confrontation.getDrawPercentage());
			statistique.setHomeTeamWinPercentage(confrontation.getHomeTeamWinPercentage());
			statistique.setAwayTeamWinPercentage(confrontation.getAwayTeamWinPercentage());
			statistique
					.setHomeTeamWinByExactly1GoalPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(1));
			statistique
					.setHomeTeamWinByExactly2GoalsPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(2));
			statistique
					.setHomeTeamWinByExactly3GoalsPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(3));
			statistique
					.setHomeTeamWinByExactly4GoalsPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(4));
			statistique
					.setHomeTeamWinByExactly5GoalsPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(5));
			statistique
					.setHomeTeamWinByExactly6GoalsPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(6));
			statistique
					.setHomeTeamWinByExactly7GoalsPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(7));
			statistique
					.setHomeTeamWinByExactly8GoalsPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(8));
			statistique
					.setHomeTeamWinByExactly9GoalsPercentage(confrontation.getHomeTeamWonByExactlyXGoalsPercentage(9));
			statistique
					.setAwayTeamWinByExactly1GoalPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(1));
			statistique
					.setAwayTeamWinByExactly2GoalsPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(2));
			statistique
					.setAwayTeamWinByExactly3GoalsPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(3));
			statistique
					.setAwayTeamWinByExactly4GoalsPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(4));
			statistique
					.setAwayTeamWinByExactly5GoalsPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(5));
			statistique
					.setAwayTeamWinByExactly6GoalsPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(6));
			statistique
					.setAwayTeamWinByExactly7GoalsPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(7));
			statistique
					.setAwayTeamWinByExactly8GoalsPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(8));
			statistique
					.setAwayTeamWinByExactly9GoalsPercentage(confrontation.getAwayTeamWonByExactlyXGoalsPercentage(9));
		}

		DatabaseConnection.createOrUpdateStatistic(statistique);
	}

}

package com.statistant.ligue1.pojo;

import com.statistant.ligue1.utils.Ligue1Utils;

/**
 * Create a new instance of Confrontation from database in order to get all associated statistics.
 * @author Thomas CHARMES
 */
public class Confrontation {

	String match;
	String recent1;
	String recent2;
	String recent3;
	String recent4;
	String recent5;

	public Confrontation(String match, String recent1, String recent2, String recent3, String recent4, String recent5) {
		this.match = match;
		this.recent1 = recent1;
		this.recent2 = recent2;
		this.recent3 = recent3;
		this.recent4 = recent4;
		this.recent5 = recent5;
	}
	
	public void setRecent1(String recent1) {
		this.recent1 = recent1;
	}

	public void setRecent2(String recent2) {
		this.recent2 = recent2;
	}

	public void setRecent3(String recent3) {
		this.recent3 = recent3;
	}

	public void setRecent4(String recent4) {
		this.recent4 = recent4;
	}

	public void setRecent5(String recent5) {
		this.recent5 = recent5;
	}

	public String getMatch() {
		return match;
	}

	public String getRecent1() {
		return recent1;
	}

	public String getRecent2() {
		return recent2;
	}

	public String getRecent3() {
		return recent3;
	}

	public String getRecent4() {
		return recent4;
	}

	public String getRecent5() {
		return recent5;
	}
	
	public void setMatch(String match) {
		this.match = match;
	}

	/**
	 * The statistics are based on the 5 last confrontations of two teams.
	 * But some confrontations may be brand new or these teams may have met less than 5 times before.
	 * @return the exact number of confrontations between these two teams.
	 */
	public int getNbConfrontations() {
		int nbConfrontations = 5;
		if (Ligue1Utils.isEmpty(getRecent5())) {
			nbConfrontations--;
		}
		if (Ligue1Utils.isEmpty(getRecent4())) {
			nbConfrontations--;
		}
		if (Ligue1Utils.isEmpty(getRecent3())) {
			nbConfrontations--;
		}
		if (Ligue1Utils.isEmpty(getRecent2())) {
			nbConfrontations--;
		}
		if (Ligue1Utils.isEmpty(getRecent1())) {
			nbConfrontations--;
		}
		return nbConfrontations;
	}

	public String getHomeTeamNickName() {
		String[] teams = this.match.split("-");
		if (Ligue1Utils.isEmpty(teams[0])) {
			return null;
		}
		return teams[0];
	}

	public String getAwayTeamNickname() {
		String[] teams = this.match.split("-");
		if (Ligue1Utils.isEmpty(teams[1])) {
			return null;
		}
		return teams[1];
	}

	/**
	 * @return the number of goals scored by the away team during the most recent confrontation.
	 */
	public int getAwayTeamGoalsNumberOnRecent1() {
		if (Ligue1Utils.isEmpty(getRecent1())){
			return -1;
		}
		String[] score = this.recent1.split("-");
		for (String teamScore : score) {
			if (Ligue1Utils.isEmpty(teamScore)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent1)) {
				setRecent1(null);
				return -1;
			}
		}
		return Integer.parseInt(score[1]);
	}

	public int getAwayTeamGoalsNumberOnRecent2() {
		if (Ligue1Utils.isEmpty(getRecent2())){
			return -1;
		}
		String[] score = this.recent2.split("-");
		for (String teamScore : score) {
			if (Ligue1Utils.isEmpty(teamScore)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent2)) {
				setRecent2(null);
				return -1;
			}
		}
		return Integer.parseInt(score[1]);
	}

	public int getAwayTeamGoalsNumberOnRecent3() {
		if (Ligue1Utils.isEmpty(getRecent3())){
			return -1;
		}
		String[] score = this.recent3.split("-");
		for (String teamScore : score) {
			if (Ligue1Utils.isEmpty(teamScore)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent3)) {
				setRecent3(null);
				return -1;
			}
		}
		return Integer.parseInt(score[1]);
	}

	public int getAwayTeamGoalsNumberOnRecent4() {
		if (Ligue1Utils.isEmpty(getRecent4())){
			return -1;
		}
		String[] score = this.recent4.split("-");
		for (String teamScore : score) {
			if (Ligue1Utils.isEmpty(teamScore)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent4)) {
				setRecent4(null);
				return -1;
			}
		}
		return Integer.parseInt(score[1]);
	}

	public int getAwayTeamGoalsNumberOnRecent5() {
		if (Ligue1Utils.isEmpty(getRecent5())){
			return -1;
		}
		String[] score = this.recent5.split("-");
		for (String teamScore : score) {
			if (Ligue1Utils.isEmpty(teamScore)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent5)) {
				setRecent5(null);
				return -1;
			}
		}
		return Integer.parseInt(score[1]);
	}

	/**
	 * @return the number of goals scored by the home team during the most recent confrontation.
	 */
	public int getHomeTeamGoalsNumberOnRecent1() {
		if (Ligue1Utils.isEmpty(getRecent1())){
			return -1;
		}
		String[] score = this.recent1.split("-");
		for (String scoreEquipe : score) {
			if (Ligue1Utils.isEmpty(scoreEquipe)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent1)) {
				setRecent1(null);
				return -1;
			}
		}
		return Integer.parseInt(score[0]);
	}

	public int getHomeTeamGoalsNumberOnRecent2() {
		if (Ligue1Utils.isEmpty(getRecent2())){
			return -1;
		}
		String[] score = this.recent2.split("-");
		for (String scoreEquipe : score) {
			if (Ligue1Utils.isEmpty(scoreEquipe)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent2)) {
				setRecent2(null);
				return -1;
			}
		}
		return Integer.parseInt(score[0]);
	}

	public int getHomeTeamGoalsNumberOnRecent3() {
		if (Ligue1Utils.isEmpty(getRecent3())){
			return -1;
		}
		String[] score = this.recent3.split("-");
		for (String scoreEquipe : score) {
			if (Ligue1Utils.isEmpty(scoreEquipe)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent3)) {
				setRecent3(null);
				return -1;
			}
		}
		return Integer.parseInt(score[0]);
	}

	public int getHomeTeamGoalsNumberOnRecent4() {
		if (Ligue1Utils.isEmpty(getRecent4())){
			return -1;
		}
		String[] score = this.recent4.split("-");
		for (String scoreEquipe : score) {
			if (Ligue1Utils.isEmpty(scoreEquipe)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent4)) {
				setRecent4(null);
				return -1;
			}
		}
		return Integer.parseInt(score[0]);
	}

	public int getHomeTeamGoalsNumberOnRecent5() {
		if (Ligue1Utils.isEmpty(getRecent5())){
			return -1;
		}
		String[] score = this.recent5.split("-");
		for (String scoreEquipe : score) {
			if (Ligue1Utils.isEmpty(scoreEquipe)) {
				return -1;
			}
			if (! Ligue1Utils.isScoreWellFormed(this.recent5)) {
				setRecent5(null);
				return -1;
			}
		}
		return Integer.parseInt(score[0]);
	}

	/**
	 * @return the exact number of goals scored by the home team during the last 5 confrontations.
	 */
	public int getHomeTeamTotalGoals() {
		int total = 0;
		if (getHomeTeamGoalsNumberOnRecent1() != -1) {
			total = total + getHomeTeamGoalsNumberOnRecent1();
		}
		if (getHomeTeamGoalsNumberOnRecent2() != -1) {
			total = total + getHomeTeamGoalsNumberOnRecent2();
		}
		if (getHomeTeamGoalsNumberOnRecent3() != -1) {
			total = total + getHomeTeamGoalsNumberOnRecent3();
		}
		if (getHomeTeamGoalsNumberOnRecent4() != -1) {
			total = total + getHomeTeamGoalsNumberOnRecent4();
		}
		if (getHomeTeamGoalsNumberOnRecent5() != -1) {
			total = total + getHomeTeamGoalsNumberOnRecent5();
		}
		return total;
	}

	/**
	 * @return the exact number of goals scored by the away team during the last 5 confrontations.
	 */
	public int getAwayTeamTotalGoals() {
		int total = 0;
		if (getAwayTeamGoalsNumberOnRecent1() != -1) {
			total = total + getAwayTeamGoalsNumberOnRecent1();
		}
		if (getAwayTeamGoalsNumberOnRecent2() != -1) {
			total = total + getAwayTeamGoalsNumberOnRecent2();
		}
		if (getAwayTeamGoalsNumberOnRecent3() != -1) {
			total = total + getAwayTeamGoalsNumberOnRecent3();
		}
		if (getAwayTeamGoalsNumberOnRecent4() != -1) {
			total = total + getAwayTeamGoalsNumberOnRecent4();
		}
		if (getAwayTeamGoalsNumberOnRecent5() != -1) {
			total = total + getAwayTeamGoalsNumberOnRecent5();
		}
		return total;
	}

	/**
	 * @return the average goals scored per game by the home team during the 5 last confrontations.
	 */
	public float getHomeTeamAverageGoals() {
		return ((float) getHomeTeamTotalGoals() / getNbConfrontations());
	}

	/**
	 * @return the average goals scored per game by the away team during the 5 last confrontations.
	 */
	public float getAwayTeamAverageGoals() {
		return ((float) getAwayTeamTotalGoals() / getNbConfrontations());
	}

	/**
	 * @return the average goals scored per game by both teams during the 5 last confrontations.
	 */
	public float getAverageGoals() {
		return (((float)getHomeTeamTotalGoals() + (float) getAwayTeamTotalGoals()) / getNbConfrontations());
	}

	/**
	 * @return the draw percentage among the 5 last confrontations.
	 */
	public float getDrawPercentage() {
		int nbDraws = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1()==(getAwayTeamGoalsNumberOnRecent1())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent2()==(getAwayTeamGoalsNumberOnRecent2())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent3()==(getAwayTeamGoalsNumberOnRecent3())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent4()==(getAwayTeamGoalsNumberOnRecent4())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent5()==(getAwayTeamGoalsNumberOnRecent5())) {
				nbDraws++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1()==(getAwayTeamGoalsNumberOnRecent1())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent2()==(getAwayTeamGoalsNumberOnRecent2())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent3()==(getAwayTeamGoalsNumberOnRecent3())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent4()==(getAwayTeamGoalsNumberOnRecent4())) {
				nbDraws++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1()==(getAwayTeamGoalsNumberOnRecent1())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent2()==(getAwayTeamGoalsNumberOnRecent2())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent3()==(getAwayTeamGoalsNumberOnRecent3())) {
				nbDraws++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1()==(getAwayTeamGoalsNumberOnRecent1())) {
				nbDraws++;
			}
			if (getHomeTeamGoalsNumberOnRecent2()==(getAwayTeamGoalsNumberOnRecent2())) {
				nbDraws++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1()==(getAwayTeamGoalsNumberOnRecent1())) {
				nbDraws++;
			}
		}
		return percentage(nbDraws);
	}

	/**
	 * @param nb, the value to evaluate
	 * @return a percentage representing nb/nbConfrontations * 100
	 */
	private float percentage(int nb) {
		return ((float) nb / getNbConfrontations()) * 100;

	}

	/**
	 * @return the home team win percentage among the 5 last confrontations.
	 */
	public float getHomeTeamWinPercentage() {
		int nbHomeTeamWins = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1() > getAwayTeamGoalsNumberOnRecent1()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > getAwayTeamGoalsNumberOnRecent2()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > getAwayTeamGoalsNumberOnRecent3()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() > getAwayTeamGoalsNumberOnRecent4()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent5() > getAwayTeamGoalsNumberOnRecent5()) {
				nbHomeTeamWins++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1() > getAwayTeamGoalsNumberOnRecent1()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > getAwayTeamGoalsNumberOnRecent2()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > getAwayTeamGoalsNumberOnRecent3()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() > getAwayTeamGoalsNumberOnRecent4()) {
				nbHomeTeamWins++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1() > getAwayTeamGoalsNumberOnRecent1()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > getAwayTeamGoalsNumberOnRecent2()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > getAwayTeamGoalsNumberOnRecent3()) {
				nbHomeTeamWins++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1() > getAwayTeamGoalsNumberOnRecent1()) {
				nbHomeTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > getAwayTeamGoalsNumberOnRecent2()) {
				nbHomeTeamWins++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1() > getAwayTeamGoalsNumberOnRecent1()) {
				nbHomeTeamWins++;
			}
		}
		return percentage(nbHomeTeamWins);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches for which the home team scored exactly nbButs goals.  
	 */
	public float getHomeTeamScoredExactlyXGoalsPercentage(int nbGoals) {
		int nbGamesWhereHomeTeamScoredExactlyXGoals = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent2()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent3()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent4()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent5()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent2()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent3()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent4()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent2()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent3()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent2()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereHomeTeamScoredExactlyXGoals++;
			}
		}
		return percentage(nbGamesWhereHomeTeamScoredExactlyXGoals);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches for which the away team scored exactly nbButs goals.  
	 */
	public float getAwayTeamScoredExactlyXGoalsPercentage(int nbGoals) {
		int nbGamesWhereAwayTeamScoredExactlyXGoals = 0;
		if (getNbConfrontations() == 5) {
			if (getAwayTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent2()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent3()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent4()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent5()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getAwayTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent2()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent3()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent4()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getAwayTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent2()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent3()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getAwayTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent2()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getAwayTeamGoalsNumberOnRecent1()==(nbGoals)) {
				nbGamesWhereAwayTeamScoredExactlyXGoals++;
			}
		}
		return percentage(nbGamesWhereAwayTeamScoredExactlyXGoals);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches for which the home team conceded exactly nbButs goals.  
	 */
	public float getHomeTeamConcedeExactlyXGoalsPercentage(int nbGoals) {
		return getAwayTeamScoredExactlyXGoalsPercentage(nbGoals);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches for which the away team conceded exactly nbButs goals.  
	 */
	public float getAwayTeamConcedeExactlyXGoalsPercentage(int nbGoals) {
		return getHomeTeamScoredExactlyXGoalsPercentage(nbGoals);
	}

	/**
	 * @param goalDifference
	 * @return the percentage of matches for which the home team won by exactly X goal(s). 
	 */
	public float getHomeTeamWonByExactlyXGoalsPercentage(int goalDifference) {
		int nbHomeTeamWinsByExactlyXGoals = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1() - getAwayTeamGoalsNumberOnRecent1() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() - getAwayTeamGoalsNumberOnRecent2() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() - getAwayTeamGoalsNumberOnRecent3() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() - getAwayTeamGoalsNumberOnRecent4() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent5() - getAwayTeamGoalsNumberOnRecent5() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1() - getAwayTeamGoalsNumberOnRecent1() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() - getAwayTeamGoalsNumberOnRecent2() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() - getAwayTeamGoalsNumberOnRecent3() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() - getAwayTeamGoalsNumberOnRecent4() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1() - getAwayTeamGoalsNumberOnRecent1() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() - getAwayTeamGoalsNumberOnRecent2() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() - getAwayTeamGoalsNumberOnRecent3() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1() - getAwayTeamGoalsNumberOnRecent1() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() - getAwayTeamGoalsNumberOnRecent2() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1() - getAwayTeamGoalsNumberOnRecent1() == goalDifference) {
				nbHomeTeamWinsByExactlyXGoals++;
			}
		}
		return percentage(nbHomeTeamWinsByExactlyXGoals);
	}

	/**
	 * @param goalDifference
	 * @return the percentage of matches for which the away team won by exactly X goal(s). 
	 */
	public float getAwayTeamWonByExactlyXGoalsPercentage(int goalDifference) {
		int nbAwayTeamWinsByExactlyXGoals = 0;
		if (getNbConfrontations() == 5) {
			if (getAwayTeamGoalsNumberOnRecent1() - getHomeTeamGoalsNumberOnRecent1() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() - getHomeTeamGoalsNumberOnRecent2() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() - getHomeTeamGoalsNumberOnRecent3() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent4() - getHomeTeamGoalsNumberOnRecent4() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent5() - getHomeTeamGoalsNumberOnRecent5() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getAwayTeamGoalsNumberOnRecent1() - getHomeTeamGoalsNumberOnRecent1() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() - getHomeTeamGoalsNumberOnRecent2() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() - getHomeTeamGoalsNumberOnRecent3() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent4() - getHomeTeamGoalsNumberOnRecent4() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getAwayTeamGoalsNumberOnRecent1() - getHomeTeamGoalsNumberOnRecent1() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() - getHomeTeamGoalsNumberOnRecent2() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() - getHomeTeamGoalsNumberOnRecent3() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getAwayTeamGoalsNumberOnRecent1() - getHomeTeamGoalsNumberOnRecent1() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() - getHomeTeamGoalsNumberOnRecent2() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getAwayTeamGoalsNumberOnRecent1() - getHomeTeamGoalsNumberOnRecent1() == goalDifference) {
				nbAwayTeamWinsByExactlyXGoals++;
			}
		}
		return percentage(nbAwayTeamWinsByExactlyXGoals);
	}

	/**
	 * @return the away team win percentage among the 5 last confrontations.
	 */
	public float getAwayTeamWinPercentage() {
		int nbAwayTeamWins = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1() < getAwayTeamGoalsNumberOnRecent1()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() < getAwayTeamGoalsNumberOnRecent2()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() < getAwayTeamGoalsNumberOnRecent3()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() < getAwayTeamGoalsNumberOnRecent4()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent5() < getAwayTeamGoalsNumberOnRecent5()) {
				nbAwayTeamWins++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1() < getAwayTeamGoalsNumberOnRecent1()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() < getAwayTeamGoalsNumberOnRecent2()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() < getAwayTeamGoalsNumberOnRecent3()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() < getAwayTeamGoalsNumberOnRecent4()) {
				nbAwayTeamWins++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1() < getAwayTeamGoalsNumberOnRecent1()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() < getAwayTeamGoalsNumberOnRecent2()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() < getAwayTeamGoalsNumberOnRecent3()) {
				nbAwayTeamWins++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1() < getAwayTeamGoalsNumberOnRecent1()) {
				nbAwayTeamWins++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() < getAwayTeamGoalsNumberOnRecent2()) {
				nbAwayTeamWins++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1() < getAwayTeamGoalsNumberOnRecent1()) {
				nbAwayTeamWins++;
			}
		}
		return percentage(nbAwayTeamWins);
	}

	/**
	 * @return the percentage of matches which both teams scored among the last 5 confrontations.
	 */
	public float getBothTeamScoredPercentage() {
		int nbMatchesWhereBothTeamsScoredtNbConfrontations = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1() > 0 && getAwayTeamGoalsNumberOnRecent1() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > 0 && getAwayTeamGoalsNumberOnRecent2() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > 0 && getAwayTeamGoalsNumberOnRecent3() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() > 0 && getAwayTeamGoalsNumberOnRecent4() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent5() > 0 && getAwayTeamGoalsNumberOnRecent5() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1() > 0 && getAwayTeamGoalsNumberOnRecent1() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > 0 && getAwayTeamGoalsNumberOnRecent2() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > 0 && getAwayTeamGoalsNumberOnRecent3() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() > 0 && getAwayTeamGoalsNumberOnRecent4() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1() > 0 && getAwayTeamGoalsNumberOnRecent1() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > 0 && getAwayTeamGoalsNumberOnRecent2() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > 0 && getAwayTeamGoalsNumberOnRecent3() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1() > 0 && getAwayTeamGoalsNumberOnRecent1() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > 0 && getAwayTeamGoalsNumberOnRecent2() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1() > 0 && getAwayTeamGoalsNumberOnRecent1() > 0) {
				nbMatchesWhereBothTeamsScoredtNbConfrontations++;
			}
		}
		return percentage(nbMatchesWhereBothTeamsScoredtNbConfrontations);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches with more than X goals.
	 */
	public float getPercentageOfMatchesWithMoreThanXGoals(float nbGoals) {
		int nbMatches = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() + getAwayTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() + getAwayTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() + getAwayTeamGoalsNumberOnRecent4() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent5() + getAwayTeamGoalsNumberOnRecent5() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() + getAwayTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() + getAwayTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() + getAwayTeamGoalsNumberOnRecent4() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() + getAwayTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() + getAwayTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() + getAwayTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
		}
		return percentage(nbMatches);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches with more than X goals for the home team.
	 */
	public float getHomeTeamPercentageOfMatchesWithMoreThanXGoals(float nbGoals) {
		int nbMatches = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent5() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
		}
		return percentage(nbMatches);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches with more than X goals for the away team.
	 */
	public float getAwayTeamPercentageOfMatchesWithMoreThanXGoals(float nbGoals) {
		int nbMatches = 0;
		if (getNbConfrontations() == 5) {
			if (getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent4() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent5() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent4() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() > nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getAwayTeamGoalsNumberOnRecent1() > nbGoals) {
				nbMatches++;
			}
		}
		return percentage(nbMatches);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches with less than X goals for the home team.
	 */
	public float getHomeTeamPercentageOfMatchesWithLessThanXGoals(float nbGoals) {
		int nbMatches = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent5() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 1) {

			if (getHomeTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
		}
		return percentage(nbMatches);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches with less than X goals for the away team.
	 */
	public float getAwayTeamPercentageOfMatchesWithLessThanXGoals(float nbGoals) {
		int nbMatches = 0;
		if (getNbConfrontations() == 5) {
			if (getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent4() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent5() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent4() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getAwayTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
		}
		return percentage(nbMatches);
	}

	/**
	 * @param nbGoals
	 * @return the percentage of matches with less than X goals.
	 */
	public float getPercentageOfMatchesWithLessThanXGoals(float nbGoals) {
		int nbMatches = 0;
		if (getNbConfrontations() == 5) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() + getAwayTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() + getAwayTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() + getAwayTeamGoalsNumberOnRecent4() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent5() + getAwayTeamGoalsNumberOnRecent5() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 4) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() + getAwayTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() + getAwayTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent4() + getAwayTeamGoalsNumberOnRecent4() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 3) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() + getAwayTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent3() + getAwayTeamGoalsNumberOnRecent3() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 2) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
			if (getHomeTeamGoalsNumberOnRecent2() + getAwayTeamGoalsNumberOnRecent2() < nbGoals) {
				nbMatches++;
			}
		}
		if (getNbConfrontations() == 1) {
			if (getHomeTeamGoalsNumberOnRecent1() + getAwayTeamGoalsNumberOnRecent1() < nbGoals) {
				nbMatches++;
			}
		}
		return percentage(nbMatches);
	}

}

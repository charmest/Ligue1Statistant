package com.statistant.ligue1.pojo;

/**
 * An instance of all statistics from a confrontation
 * 
 * @author Thomas CHARMES
 */
public class Statistic {

	private String match;
	private float homeTeamWinPercentage;
	private float awayTeamWinPercentage;
	private float drawPercentage;
	private float homeTeamGoalAverage;
	private float awayTeamGoalAverage;
	private float goalAverage;
	private float moreThan05GoalPercentage;
	private float moreThan15GoalPercentage;
	private float moreThan25GoalsPercentage;
	private float moreThan35GoalsPercentage;
	private float moreThan45GoalsPercentage;
	private float lessThan05GoalPercentage;
	private float lessThan15GoalPercentage;
	private float lessThan25GoalsPercentage;
	private float lessThan35GoalsPercentage;
	private float lessThan45GoalsPercentage;
	private float bothTeamsToScorePercentage;
	private float homeTeamScoredMoreThan05GoalPercentage;
	private float homeTeamScoredMoreThan15GoalPercentage;
	private float homeTeamScoredMoreThan25GoalsPercentage;
	private float homeTeamScoredMoreThan35GoalsPercentage;
	private float homeTeamScoredMoreThan45GoalsPercentage;
	private float homeTeamScoredLessThan05GoalPercentage;
	private float homeTeamScoredLessThan15GoalPercentage;
	private float homeTeamScoredLessThan25GoalsPercentage;
	private float homeTeamScoredLessThan35GoalsPercentage;
	private float homeTeamScoredLessThan45GoalsPercentage;
	private float awayTeamScoredMoreThan05GoalPercentage;
	private float awayTeamScoredMoreThan15GoalPercentage;
	private float awayTeamScoredMoreThan25GoalsPercentage;
	private float awayTeamScoredMoreThan35GoalsPercentage;
	private float awayTeamScoredMoreThan45GoalsPercentage;
	private float awayTeamScoredLessThan05GoalPercentage;
	private float awayTeamScoredLessThan15GoalPercentage;
	private float awayTeamScoredLessThan25GoalsPercentage;
	private float awayTeamScoredLessThan35GoalsPercentage;
	private float awayTeamScoredLessThan45GoalsPercentage;
	private float awayTeamWinByExactly1GoalPercentage;
	private float awayTeamWinByExactly2GoalsPercentage;
	private float awayTeamWinByExactly3GoalsPercentage;
	private float awayTeamWinByExactly4GoalsPercentage;
	private float awayTeamWinByExactly5GoalsPercentage;
	private float awayTeamWinByExactly6GoalsPercentage;
	private float awayTeamWinByExactly7GoalsPercentage;
	private float awayTeamWinByExactly8GoalsPercentage;
	private float awayTeamWinByExactly9GoalsPercentage;
	private float homeTeamWinByExactly1GoalPercentage;
	private float homeTeamWinByExactly2GoalsPercentage;
	private float homeTeamWinByExactly3GoalsPercentage;
	private float homeTeamWinByExactly4GoalsPercentage;
	private float homeTeamWinByExactly5GoalsPercentage;
	private float homeTeamWinByExactly6GoalsPercentage;
	private float homeTeamWinByExactly7GoalsPercentage;
	private float homeTeamWinByExactly8GoalsPercentage;
	private float homeTeamWinByExactly9GoalsPercentage;
	private float homeTeamScoredExactly0GoalPercentage;
	private float homeTeamScoredExactly1GoalPercentage;
	private float homeTeamScoredExactly2GoalsPercentage;
	private float homeTeamScoredExactly3GoalsPercentage;
	private float homeTeamScoredExactly4GoalsPercentage;
	private float homeTeamScoredExactly5GoalsPercentage;
	private float homeTeamConcededExactly0GoalPercentage;
	private float homeTeamConcededExactly1GoalPercentage;
	private float homeTeamConcededExactly2GoalsPercentage;
	private float homeTeamConcededExactly3GoalsPercentage;
	private float homeTeamConcededExactly4GoalsPercentage;
	private float homeTeamConcededExactly5GoalsPercentage;
	private float awayTeamScoredExactly0GoalPercentage;
	private float awayTeamScoredExactly1GoalPercentage;
	private float awayTeamScoredExactly2GoalsPercentage;
	private float awayTeamScoredExactly3GoalsPercentage;
	private float awayTeamScoredExactly4GoalsPercentage;
	private float awayTeamScoredExactly5GoalsPercentage;
	private float awayTeamConcededExactly0GoalPercentage;
	private float awayTeamConcededExactly1GoalPercentage;
	private float awayTeamConcededExactly2GoalsPercentage;
	private float awayTeamConcededExactly3GoalsPercentage;
	private float awayTeamConcededExactly4GoalsPercentage;
	private float awayTeamConcededExactly5GoalsPercentage;

	public Statistic(String match, float homeTeamWinPercentage, float awayTeamWinPercentage, float drawPercentage,
			float homeTeamGoalAverage, float awayTeamGoalAverage, float goalAverage, float moreThan05GoalPercentage,
			float moreThan15GoalPercentage, float moreThan25GoalsPercentage, float moreThan35GoalsPercentage,
			float moreThan45GoalsPercentage, float lessThan05GoalPercentage, float lessThan15GoalPercentage,
			float lessThan25GoalsPercentage, float lessThan35GoalsPercentage, float lessThan45GoalsPercentage,
			float bothTeamsToScorePercentage, float homeTeamScoredMoreThan05GoalPercentage,
			float homeTeamScoredMoreThan15GoalPercentage, float homeTeamScoredMoreThan25GoalsPercentage,
			float homeTeamScoredMoreThan35GoalsPercentage, float homeTeamScoredMoreThan45GoalsPercentage,
			float homeTeamScoredLessThan05GoalPercentage, float homeTeamScoredLessThan15GoalPercentage,
			float homeTeamScoredLessThan25GoalsPercentage, float homeTeamScoredLessThan35GoalsPercentage,
			float homeTeamScoredLessThan45GoalsPercentage, float awayTeamScoredMoreThan05GoalPercentage,
			float awayTeamScoredMoreThan15GoalPercentage, float awayTeamScoredMoreThan25GoalsPercentage,
			float awayTeamScoredMoreThan35GoalsPercentage, float awayTeamScoredMoreThan45GoalsPercentage,
			float awayTeamScoredLessThan05GoalPercentage, float awayTeamScoredLessThan15GoalPercentage,
			float awayTeamScoredLessThan25GoalsPercentage, float awayTeamScoredLessThan35GoalsPercentage,
			float awayTeamScoredLessThan45GoalsPercentage, float awayTeamWinByExactly1GoalPercentage,
			float awayTeamWinByExactly2GoalsPercentage, float awayTeamWinByExactly3GoalsPercentage,
			float awayTeamWinByExactly4GoalsPercentage, float awayTeamWinByExactly5GoalsPercentage,
			float awayTeamWinByExactly6GoalsPercentage, float awayTeamWinByExactly7GoalsPercentage,
			float awayTeamWinByExactly8GoalsPercentage, float awayTeamWinByExactly9GoalsPercentage,
			float homeTeamWinByExactly1GoalPercentage, float homeTeamWinByExactly2GoalsPercentage,
			float homeTeamWinByExactly3GoalsPercentage, float homeTeamWinByExactly4GoalsPercentage,
			float homeTeamWinByExactly5GoalsPercentage, float homeTeamWinByExactly6GoalsPercentage,
			float homeTeamWinByExactly7GoalsPercentage, float homeTeamWinByExactly8GoalsPercentage,
			float homeTeamWinByExactly9GoalsPercentage, float homeTeamScoredExactly0GoalPercentage,
			float homeTeamScoredExactly1GoalPercentage, float homeTeamScoredExactly2GoalsPercentage,
			float homeTeamScoredExactly3GoalsPercentage, float homeTeamScoredExactly4GoalsPercentage,
			float homeTeamScoredExactly5GoalsPercentage, float homeTeamConcededExactly0GoalPercentage,
			float homeTeamConcededExactly1GoalPercentage, float homeTeamConcededExactly2GoalsPercentage,
			float homeTeamConcededExactly3GoalsPercentage, float homeTeamConcededExactly4GoalsPercentage,
			float homeTeamConcededExactly5GoalsPercentage, float awayTeamScoredExactly0GoalPercentage,
			float awayTeamScoredExactly1GoalPercentage, float awayTeamScoredExactly2GoalsPercentage,
			float awayTeamScoredExactly3GoalsPercentage, float awayTeamScoredExactly4GoalsPercentage,
			float awayTeamScoredExactly5GoalsPercentage, float awayTeamConcededExactly0GoalPercentage,
			float awayTeamConcededExactly1GoalPercentage, float awayTeamConcededExactly2GoalsPercentage,
			float awayTeamConcededExactly3GoalsPercentage, float awayTeamConcededExactly4GoalsPercentage,
			float awayTeamConcededExactly5GoalsPercentage) {
		this.match = match;
		this.homeTeamWinPercentage = homeTeamWinPercentage;
		this.awayTeamWinPercentage = awayTeamWinPercentage;
		this.drawPercentage = drawPercentage;
		this.homeTeamGoalAverage = homeTeamGoalAverage;
		this.awayTeamGoalAverage = awayTeamGoalAverage;
		this.goalAverage = goalAverage;
		this.moreThan05GoalPercentage = moreThan05GoalPercentage;
		this.moreThan15GoalPercentage = moreThan15GoalPercentage;
		this.moreThan25GoalsPercentage = moreThan25GoalsPercentage;
		this.moreThan35GoalsPercentage = moreThan35GoalsPercentage;
		this.moreThan45GoalsPercentage = moreThan45GoalsPercentage;
		this.lessThan05GoalPercentage = lessThan05GoalPercentage;
		this.lessThan15GoalPercentage = lessThan15GoalPercentage;
		this.lessThan25GoalsPercentage = lessThan25GoalsPercentage;
		this.lessThan35GoalsPercentage = lessThan35GoalsPercentage;
		this.lessThan45GoalsPercentage = lessThan45GoalsPercentage;
		this.bothTeamsToScorePercentage = bothTeamsToScorePercentage;
		this.homeTeamScoredMoreThan05GoalPercentage = homeTeamScoredMoreThan05GoalPercentage;
		this.homeTeamScoredMoreThan15GoalPercentage = homeTeamScoredMoreThan15GoalPercentage;
		this.homeTeamScoredMoreThan25GoalsPercentage = homeTeamScoredMoreThan25GoalsPercentage;
		this.homeTeamScoredMoreThan35GoalsPercentage = homeTeamScoredMoreThan35GoalsPercentage;
		this.homeTeamScoredMoreThan45GoalsPercentage = homeTeamScoredMoreThan45GoalsPercentage;
		this.homeTeamScoredLessThan05GoalPercentage = homeTeamScoredLessThan05GoalPercentage;
		this.homeTeamScoredLessThan15GoalPercentage = homeTeamScoredLessThan15GoalPercentage;
		this.homeTeamScoredLessThan25GoalsPercentage = homeTeamScoredLessThan25GoalsPercentage;
		this.homeTeamScoredLessThan35GoalsPercentage = homeTeamScoredLessThan35GoalsPercentage;
		this.homeTeamScoredLessThan45GoalsPercentage = homeTeamScoredLessThan45GoalsPercentage;
		this.awayTeamScoredMoreThan05GoalPercentage = awayTeamScoredMoreThan05GoalPercentage;
		this.awayTeamScoredMoreThan15GoalPercentage = awayTeamScoredMoreThan15GoalPercentage;
		this.awayTeamScoredMoreThan25GoalsPercentage = awayTeamScoredMoreThan25GoalsPercentage;
		this.awayTeamScoredMoreThan35GoalsPercentage = awayTeamScoredMoreThan35GoalsPercentage;
		this.awayTeamScoredMoreThan45GoalsPercentage = awayTeamScoredMoreThan45GoalsPercentage;
		this.awayTeamScoredLessThan05GoalPercentage = awayTeamScoredLessThan05GoalPercentage;
		this.awayTeamScoredLessThan15GoalPercentage = awayTeamScoredLessThan15GoalPercentage;
		this.awayTeamScoredLessThan25GoalsPercentage = awayTeamScoredLessThan25GoalsPercentage;
		this.awayTeamScoredLessThan35GoalsPercentage = awayTeamScoredLessThan35GoalsPercentage;
		this.awayTeamScoredLessThan45GoalsPercentage = awayTeamScoredLessThan45GoalsPercentage;
		this.awayTeamWinByExactly1GoalPercentage = awayTeamWinByExactly1GoalPercentage;
		this.awayTeamWinByExactly2GoalsPercentage = awayTeamWinByExactly2GoalsPercentage;
		this.awayTeamWinByExactly3GoalsPercentage = awayTeamWinByExactly3GoalsPercentage;
		this.awayTeamWinByExactly4GoalsPercentage = awayTeamWinByExactly4GoalsPercentage;
		this.awayTeamWinByExactly5GoalsPercentage = awayTeamWinByExactly5GoalsPercentage;
		this.awayTeamWinByExactly6GoalsPercentage = awayTeamWinByExactly6GoalsPercentage;
		this.awayTeamWinByExactly7GoalsPercentage = awayTeamWinByExactly7GoalsPercentage;
		this.awayTeamWinByExactly8GoalsPercentage = awayTeamWinByExactly8GoalsPercentage;
		this.awayTeamWinByExactly9GoalsPercentage = awayTeamWinByExactly9GoalsPercentage;
		this.homeTeamWinByExactly1GoalPercentage = homeTeamWinByExactly1GoalPercentage;
		this.homeTeamWinByExactly2GoalsPercentage = homeTeamWinByExactly2GoalsPercentage;
		this.homeTeamWinByExactly3GoalsPercentage = homeTeamWinByExactly3GoalsPercentage;
		this.homeTeamWinByExactly4GoalsPercentage = homeTeamWinByExactly4GoalsPercentage;
		this.homeTeamWinByExactly5GoalsPercentage = homeTeamWinByExactly5GoalsPercentage;
		this.homeTeamWinByExactly6GoalsPercentage = homeTeamWinByExactly6GoalsPercentage;
		this.homeTeamWinByExactly7GoalsPercentage = homeTeamWinByExactly7GoalsPercentage;
		this.homeTeamWinByExactly8GoalsPercentage = homeTeamWinByExactly8GoalsPercentage;
		this.homeTeamWinByExactly9GoalsPercentage = homeTeamWinByExactly9GoalsPercentage;
		this.homeTeamScoredExactly0GoalPercentage = homeTeamScoredExactly0GoalPercentage;
		this.homeTeamScoredExactly1GoalPercentage = homeTeamScoredExactly1GoalPercentage;
		this.homeTeamScoredExactly2GoalsPercentage = homeTeamScoredExactly2GoalsPercentage;
		this.homeTeamScoredExactly3GoalsPercentage = homeTeamScoredExactly3GoalsPercentage;
		this.homeTeamScoredExactly4GoalsPercentage = homeTeamScoredExactly4GoalsPercentage;
		this.homeTeamScoredExactly5GoalsPercentage = homeTeamScoredExactly5GoalsPercentage;
		this.homeTeamConcededExactly0GoalPercentage = homeTeamConcededExactly0GoalPercentage;
		this.homeTeamConcededExactly1GoalPercentage = homeTeamConcededExactly1GoalPercentage;
		this.homeTeamConcededExactly2GoalsPercentage = homeTeamConcededExactly2GoalsPercentage;
		this.homeTeamConcededExactly3GoalsPercentage = homeTeamConcededExactly3GoalsPercentage;
		this.homeTeamConcededExactly4GoalsPercentage = homeTeamConcededExactly4GoalsPercentage;
		this.homeTeamConcededExactly5GoalsPercentage = homeTeamConcededExactly5GoalsPercentage;
		this.awayTeamScoredExactly0GoalPercentage = awayTeamScoredExactly0GoalPercentage;
		this.awayTeamScoredExactly1GoalPercentage = awayTeamScoredExactly1GoalPercentage;
		this.awayTeamScoredExactly2GoalsPercentage = awayTeamScoredExactly2GoalsPercentage;
		this.awayTeamScoredExactly3GoalsPercentage = awayTeamScoredExactly3GoalsPercentage;
		this.awayTeamScoredExactly4GoalsPercentage = awayTeamScoredExactly4GoalsPercentage;
		this.awayTeamScoredExactly5GoalsPercentage = awayTeamScoredExactly5GoalsPercentage;
		this.awayTeamConcededExactly0GoalPercentage = awayTeamConcededExactly0GoalPercentage;
		this.awayTeamConcededExactly1GoalPercentage = awayTeamConcededExactly1GoalPercentage;
		this.awayTeamConcededExactly2GoalsPercentage = awayTeamConcededExactly2GoalsPercentage;
		this.awayTeamConcededExactly3GoalsPercentage = awayTeamConcededExactly3GoalsPercentage;
		this.awayTeamConcededExactly4GoalsPercentage = awayTeamConcededExactly4GoalsPercentage;
		this.awayTeamConcededExactly5GoalsPercentage = awayTeamConcededExactly5GoalsPercentage;
	}

	public Statistic(String match) {
		this.match = match;
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public float getHomeTeamWinPercentage() {
		return homeTeamWinPercentage;
	}

	public void setHomeTeamWinPercentage(float homeTeamWinPercentage) {
		this.homeTeamWinPercentage = homeTeamWinPercentage;
	}

	public float getAwayTeamWinPercentage() {
		return awayTeamWinPercentage;
	}

	public void setAwayTeamWinPercentage(float awayTeamWinPercentage) {
		this.awayTeamWinPercentage = awayTeamWinPercentage;
	}

	public float getDrawPercentage() {
		return drawPercentage;
	}

	public void setDrawPercentage(float drawPercentage) {
		this.drawPercentage = drawPercentage;
	}

	public float getHomeTeamGoalAverage() {
		return homeTeamGoalAverage;
	}

	public void setHomeTeamGoalAverage(float homeTeamGoalAverage) {
		this.homeTeamGoalAverage = homeTeamGoalAverage;
	}

	public float getAwayTeamGoalAverage() {
		return awayTeamGoalAverage;
	}

	public void setAwayTeamGoalAverage(float awayTeamGoalAverage) {
		this.awayTeamGoalAverage = awayTeamGoalAverage;
	}

	public float getGoalAverage() {
		return goalAverage;
	}

	public void setGoalAverage(float goalAverage) {
		this.goalAverage = goalAverage;
	}

	public float getMoreThan05GoalPercentage() {
		return moreThan05GoalPercentage;
	}

	public void setMoreThan05GoalPercentage(float moreThan05GoalPercentage) {
		this.moreThan05GoalPercentage = moreThan05GoalPercentage;
	}

	public float getMoreThan15GoalPercentage() {
		return moreThan15GoalPercentage;
	}

	public void setMoreThan15GoalPercentage(float moreThan15GoalPercentage) {
		this.moreThan15GoalPercentage = moreThan15GoalPercentage;
	}

	public float getMoreThan25GoalsPercentage() {
		return moreThan25GoalsPercentage;
	}

	public void setMoreThan25GoalsPercentage(float moreThan25GoalsPercentage) {
		this.moreThan25GoalsPercentage = moreThan25GoalsPercentage;
	}

	public float getMoreThan35GoalsPercentage() {
		return moreThan35GoalsPercentage;
	}

	public void setMoreThan35GoalsPercentage(float moreThan35GoalsPercentage) {
		this.moreThan35GoalsPercentage = moreThan35GoalsPercentage;
	}

	public float getMoreThan45GoalsPercentage() {
		return moreThan45GoalsPercentage;
	}

	public void setMoreThan45GoalsPercentage(float moreThan45GoalsPercentage) {
		this.moreThan45GoalsPercentage = moreThan45GoalsPercentage;
	}

	public float getLessThan05GoalPercentage() {
		return lessThan05GoalPercentage;
	}

	public void setLessThan05GoalPercentage(float lessThan05GoalPercentage) {
		this.lessThan05GoalPercentage = lessThan05GoalPercentage;
	}

	public float getLessThan15GoalPercentage() {
		return lessThan15GoalPercentage;
	}

	public void setLessThan15GoalPercentage(float lessThan15GoalPercentage) {
		this.lessThan15GoalPercentage = lessThan15GoalPercentage;
	}

	public float getLessThan25GoalsPercentage() {
		return lessThan25GoalsPercentage;
	}

	public void setLessThan25GoalsPercentage(float lessThan25GoalsPercentage) {
		this.lessThan25GoalsPercentage = lessThan25GoalsPercentage;
	}

	public float getLessThan35GoalsPercentage() {
		return lessThan35GoalsPercentage;
	}

	public void setLessThan35GoalsPercentage(float lessThan35GoalsPercentage) {
		this.lessThan35GoalsPercentage = lessThan35GoalsPercentage;
	}

	public float getLessThan45GoalsPercentage() {
		return lessThan45GoalsPercentage;
	}

	public void setLessThan45GoalsPercentage(float lessThan45GoalsPercentage) {
		this.lessThan45GoalsPercentage = lessThan45GoalsPercentage;
	}

	public float getBothTeamsToScorePercentage() {
		return bothTeamsToScorePercentage;
	}

	public void setBothTeamsToScorePercentage(float bothTeamsToScorePercentage) {
		this.bothTeamsToScorePercentage = bothTeamsToScorePercentage;
	}

	public float getHomeTeamScoredMoreThan05GoalPercentage() {
		return homeTeamScoredMoreThan05GoalPercentage;
	}

	public void setHomeTeamScoredMoreThan05GoalPercentage(float homeTeamScoredMoreThan05GoalPercentage) {
		this.homeTeamScoredMoreThan05GoalPercentage = homeTeamScoredMoreThan05GoalPercentage;
	}

	public float getHomeTeamScoredMoreThan15GoalPercentage() {
		return homeTeamScoredMoreThan15GoalPercentage;
	}

	public void setHomeTeamScoredMoreThan15GoalPercentage(float homeTeamScoredMoreThan15GoalPercentage) {
		this.homeTeamScoredMoreThan15GoalPercentage = homeTeamScoredMoreThan15GoalPercentage;
	}

	public float getHomeTeamScoredMoreThan25GoalsPercentage() {
		return homeTeamScoredMoreThan25GoalsPercentage;
	}

	public void setHomeTeamScoredMoreThan25GoalsPercentage(float homeTeamScoredMoreThan25GoalsPercentage) {
		this.homeTeamScoredMoreThan25GoalsPercentage = homeTeamScoredMoreThan25GoalsPercentage;
	}

	public float getHomeTeamScoredMoreThan35GoalsPercentage() {
		return homeTeamScoredMoreThan35GoalsPercentage;
	}

	public void setHomeTeamScoredMoreThan35GoalsPercentage(float homeTeamScoredMoreThan35GoalsPercentage) {
		this.homeTeamScoredMoreThan35GoalsPercentage = homeTeamScoredMoreThan35GoalsPercentage;
	}

	public float getHomeTeamScoredMoreThan45GoalsPercentage() {
		return homeTeamScoredMoreThan45GoalsPercentage;
	}

	public void setHomeTeamScoredMoreThan45GoalsPercentage(float homeTeamScoredMoreThan45GoalsPercentage) {
		this.homeTeamScoredMoreThan45GoalsPercentage = homeTeamScoredMoreThan45GoalsPercentage;
	}

	public float getHomeTeamScoredLessThan05GoalPercentage() {
		return homeTeamScoredLessThan05GoalPercentage;
	}

	public void setHomeTeamScoredLessThan05GoalPercentage(float homeTeamScoredLessThan05GoalPercentage) {
		this.homeTeamScoredLessThan05GoalPercentage = homeTeamScoredLessThan05GoalPercentage;
	}

	public float getHomeTeamScoredLessThan15GoalPercentage() {
		return homeTeamScoredLessThan15GoalPercentage;
	}

	public void setHomeTeamScoredLessThan15GoalPercentage(float homeTeamScoredLessThan15GoalPercentage) {
		this.homeTeamScoredLessThan15GoalPercentage = homeTeamScoredLessThan15GoalPercentage;
	}

	public float getHomeTeamScoredLessThan25GoalsPercentage() {
		return homeTeamScoredLessThan25GoalsPercentage;
	}

	public void setHomeTeamScoredLessThan25GoalsPercentage(float homeTeamScoredLessThan25GoalsPercentage) {
		this.homeTeamScoredLessThan25GoalsPercentage = homeTeamScoredLessThan25GoalsPercentage;
	}

	public float getHomeTeamScoredLessThan35GoalsPercentage() {
		return homeTeamScoredLessThan35GoalsPercentage;
	}

	public void setHomeTeamScoredLessThan35GoalsPercentage(float homeTeamScoredLessThan35GoalsPercentage) {
		this.homeTeamScoredLessThan35GoalsPercentage = homeTeamScoredLessThan35GoalsPercentage;
	}

	public float getHomeTeamScoredLessThan45GoalsPercentage() {
		return homeTeamScoredLessThan45GoalsPercentage;
	}

	public void setHomeTeamScoredLessThan45GoalsPercentage(float homeTeamScoredLessThan45GoalsPercentage) {
		this.homeTeamScoredLessThan45GoalsPercentage = homeTeamScoredLessThan45GoalsPercentage;
	}

	public float getAwayTeamScoredMoreThan05GoalPercentage() {
		return awayTeamScoredMoreThan05GoalPercentage;
	}

	public void setAwayTeamScoredMoreThan05GoalPercentage(float awayTeamScoredMoreThan05GoalPercentage) {
		this.awayTeamScoredMoreThan05GoalPercentage = awayTeamScoredMoreThan05GoalPercentage;
	}

	public float getAwayTeamScoredMoreThan15GoalPercentage() {
		return awayTeamScoredMoreThan15GoalPercentage;
	}

	public void setAwayTeamScoredMoreThan15GoalPercentage(float awayTeamScoredMoreThan15GoalPercentage) {
		this.awayTeamScoredMoreThan15GoalPercentage = awayTeamScoredMoreThan15GoalPercentage;
	}

	public float getAwayTeamScoredMoreThan25GoalsPercentage() {
		return awayTeamScoredMoreThan25GoalsPercentage;
	}

	public void setAwayTeamScoredMoreThan25GoalsPercentage(float awayTeamScoredMoreThan25GoalsPercentage) {
		this.awayTeamScoredMoreThan25GoalsPercentage = awayTeamScoredMoreThan25GoalsPercentage;
	}

	public float getAwayTeamScoredMoreThan35GoalsPercentage() {
		return awayTeamScoredMoreThan35GoalsPercentage;
	}

	public void setAwayTeamScoredMoreThan35GoalsPercentage(float awayTeamScoredMoreThan35GoalsPercentage) {
		this.awayTeamScoredMoreThan35GoalsPercentage = awayTeamScoredMoreThan35GoalsPercentage;
	}

	public float getAwayTeamScoredMoreThan45GoalsPercentage() {
		return awayTeamScoredMoreThan45GoalsPercentage;
	}

	public void setAwayTeamScoredMoreThan45GoalsPercentage(float awayTeamScoredMoreThan45GoalsPercentage) {
		this.awayTeamScoredMoreThan45GoalsPercentage = awayTeamScoredMoreThan45GoalsPercentage;
	}

	public float getAwayTeamScoredLessThan05GoalPercentage() {
		return awayTeamScoredLessThan05GoalPercentage;
	}

	public void setAwayTeamScoredLessThan05GoalPercentage(float awayTeamScoredLessThan05GoalPercentage) {
		this.awayTeamScoredLessThan05GoalPercentage = awayTeamScoredLessThan05GoalPercentage;
	}

	public float getAwayTeamScoredLessThan15GoalPercentage() {
		return awayTeamScoredLessThan15GoalPercentage;
	}

	public void setAwayTeamScoredLessThan15GoalPercentage(float awayTeamScoredLessThan15GoalPercentage) {
		this.awayTeamScoredLessThan15GoalPercentage = awayTeamScoredLessThan15GoalPercentage;
	}

	public float getAwayTeamScoredLessThan25GoalsPercentage() {
		return awayTeamScoredLessThan25GoalsPercentage;
	}

	public void setAwayTeamScoredLessThan25GoalsPercentage(float awayTeamScoredLessThan25GoalsPercentage) {
		this.awayTeamScoredLessThan25GoalsPercentage = awayTeamScoredLessThan25GoalsPercentage;
	}

	public float getAwayTeamScoredLessThan35GoalsPercentage() {
		return awayTeamScoredLessThan35GoalsPercentage;
	}

	public void setAwayTeamScoredLessThan35GoalsPercentage(float awayTeamScoredLessThan35GoalsPercentage) {
		this.awayTeamScoredLessThan35GoalsPercentage = awayTeamScoredLessThan35GoalsPercentage;
	}

	public float getAwayTeamScoredLessThan45GoalsPercentage() {
		return awayTeamScoredLessThan45GoalsPercentage;
	}

	public void setAwayTeamScoredLessThan45GoalsPercentage(float awayTeamScoredLessThan45GoalsPercentage) {
		this.awayTeamScoredLessThan45GoalsPercentage = awayTeamScoredLessThan45GoalsPercentage;
	}

	public float getAwayTeamWinByExactly1GoalPercentage() {
		return awayTeamWinByExactly1GoalPercentage;
	}

	public void setAwayTeamWinByExactly1GoalPercentage(float awayTeamWinByExactly1GoalPercentage) {
		this.awayTeamWinByExactly1GoalPercentage = awayTeamWinByExactly1GoalPercentage;
	}

	public float getAwayTeamWinByExactly2GoalsPercentage() {
		return awayTeamWinByExactly2GoalsPercentage;
	}

	public void setAwayTeamWinByExactly2GoalsPercentage(float awayTeamWinByExactly2GoalsPercentage) {
		this.awayTeamWinByExactly2GoalsPercentage = awayTeamWinByExactly2GoalsPercentage;
	}

	public float getAwayTeamWinByExactly3GoalsPercentage() {
		return awayTeamWinByExactly3GoalsPercentage;
	}

	public void setAwayTeamWinByExactly3GoalsPercentage(float awayTeamWinByExactly3GoalsPercentage) {
		this.awayTeamWinByExactly3GoalsPercentage = awayTeamWinByExactly3GoalsPercentage;
	}

	public float getAwayTeamWinByExactly4GoalsPercentage() {
		return awayTeamWinByExactly4GoalsPercentage;
	}

	public void setAwayTeamWinByExactly4GoalsPercentage(float awayTeamWinByExactly4GoalsPercentage) {
		this.awayTeamWinByExactly4GoalsPercentage = awayTeamWinByExactly4GoalsPercentage;
	}

	public float getAwayTeamWinByExactly5GoalsPercentage() {
		return awayTeamWinByExactly5GoalsPercentage;
	}

	public void setAwayTeamWinByExactly5GoalsPercentage(float awayTeamWinByExactly5GoalsPercentage) {
		this.awayTeamWinByExactly5GoalsPercentage = awayTeamWinByExactly5GoalsPercentage;
	}

	public float getAwayTeamWinByExactly6GoalsPercentage() {
		return awayTeamWinByExactly6GoalsPercentage;
	}

	public void setAwayTeamWinByExactly6GoalsPercentage(float awayTeamWinByExactly6GoalsPercentage) {
		this.awayTeamWinByExactly6GoalsPercentage = awayTeamWinByExactly6GoalsPercentage;
	}

	public float getAwayTeamWinByExactly7GoalsPercentage() {
		return awayTeamWinByExactly7GoalsPercentage;
	}

	public void setAwayTeamWinByExactly7GoalsPercentage(float awayTeamWinByExactly7GoalsPercentage) {
		this.awayTeamWinByExactly7GoalsPercentage = awayTeamWinByExactly7GoalsPercentage;
	}

	public float getAwayTeamWinByExactly8GoalsPercentage() {
		return awayTeamWinByExactly8GoalsPercentage;
	}

	public void setAwayTeamWinByExactly8GoalsPercentage(float awayTeamWinByExactly8GoalsPercentage) {
		this.awayTeamWinByExactly8GoalsPercentage = awayTeamWinByExactly8GoalsPercentage;
	}

	public float getAwayTeamWinByExactly9GoalsPercentage() {
		return awayTeamWinByExactly9GoalsPercentage;
	}

	public void setAwayTeamWinByExactly9GoalsPercentage(float awayTeamWinByExactly9GoalsPercentage) {
		this.awayTeamWinByExactly9GoalsPercentage = awayTeamWinByExactly9GoalsPercentage;
	}

	public float getHomeTeamWinByExactly1GoalPercentage() {
		return homeTeamWinByExactly1GoalPercentage;
	}

	public void setHomeTeamWinByExactly1GoalPercentage(float homeTeamWinByExactly1GoalPercentage) {
		this.homeTeamWinByExactly1GoalPercentage = homeTeamWinByExactly1GoalPercentage;
	}

	public float getHomeTeamWinByExactly2GoalsPercentage() {
		return homeTeamWinByExactly2GoalsPercentage;
	}

	public void setHomeTeamWinByExactly2GoalsPercentage(float homeTeamWinByExactly2GoalsPercentage) {
		this.homeTeamWinByExactly2GoalsPercentage = homeTeamWinByExactly2GoalsPercentage;
	}

	public float getHomeTeamWinByExactly3GoalsPercentage() {
		return homeTeamWinByExactly3GoalsPercentage;
	}

	public void setHomeTeamWinByExactly3GoalsPercentage(float homeTeamWinByExactly3GoalsPercentage) {
		this.homeTeamWinByExactly3GoalsPercentage = homeTeamWinByExactly3GoalsPercentage;
	}

	public float getHomeTeamWinByExactly4GoalsPercentage() {
		return homeTeamWinByExactly4GoalsPercentage;
	}

	public void setHomeTeamWinByExactly4GoalsPercentage(float homeTeamWinByExactly4GoalsPercentage) {
		this.homeTeamWinByExactly4GoalsPercentage = homeTeamWinByExactly4GoalsPercentage;
	}

	public float getHomeTeamWinByExactly5GoalsPercentage() {
		return homeTeamWinByExactly5GoalsPercentage;
	}

	public void setHomeTeamWinByExactly5GoalsPercentage(float homeTeamWinByExactly5GoalsPercentage) {
		this.homeTeamWinByExactly5GoalsPercentage = homeTeamWinByExactly5GoalsPercentage;
	}

	public float getHomeTeamWinByExactly6GoalsPercentage() {
		return homeTeamWinByExactly6GoalsPercentage;
	}

	public void setHomeTeamWinByExactly6GoalsPercentage(float homeTeamWinByExactly6GoalsPercentage) {
		this.homeTeamWinByExactly6GoalsPercentage = homeTeamWinByExactly6GoalsPercentage;
	}

	public float getHomeTeamWinByExactly7GoalsPercentage() {
		return homeTeamWinByExactly7GoalsPercentage;
	}

	public void setHomeTeamWinByExactly7GoalsPercentage(float homeTeamWinByExactly7GoalsPercentage) {
		this.homeTeamWinByExactly7GoalsPercentage = homeTeamWinByExactly7GoalsPercentage;
	}

	public float getHomeTeamWinByExactly8GoalsPercentage() {
		return homeTeamWinByExactly8GoalsPercentage;
	}

	public void setHomeTeamWinByExactly8GoalsPercentage(float homeTeamWinByExactly8GoalsPercentage) {
		this.homeTeamWinByExactly8GoalsPercentage = homeTeamWinByExactly8GoalsPercentage;
	}

	public float getHomeTeamWinByExactly9GoalsPercentage() {
		return homeTeamWinByExactly9GoalsPercentage;
	}

	public void setHomeTeamWinByExactly9GoalsPercentage(float homeTeamWinByExactly9GoalsPercentage) {
		this.homeTeamWinByExactly9GoalsPercentage = homeTeamWinByExactly9GoalsPercentage;
	}

	public float getHomeTeamScoredExactly0GoalPercentage() {
		return homeTeamScoredExactly0GoalPercentage;
	}

	public void setHomeTeamScoredExactly0GoalPercentage(float homeTeamScoredExactly0GoalPercentage) {
		this.homeTeamScoredExactly0GoalPercentage = homeTeamScoredExactly0GoalPercentage;
	}

	public float getHomeTeamScoredExactly1GoalPercentage() {
		return homeTeamScoredExactly1GoalPercentage;
	}

	public void setHomeTeamScoredExactly1GoalPercentage(float homeTeamScoredExactly1GoalPercentage) {
		this.homeTeamScoredExactly1GoalPercentage = homeTeamScoredExactly1GoalPercentage;
	}

	public float getHomeTeamScoredExactly2GoalsPercentage() {
		return homeTeamScoredExactly2GoalsPercentage;
	}

	public void setHomeTeamScoredExactly2GoalsPercentage(float homeTeamScoredExactly2GoalsPercentage) {
		this.homeTeamScoredExactly2GoalsPercentage = homeTeamScoredExactly2GoalsPercentage;
	}

	public float getHomeTeamScoredExactly3GoalsPercentage() {
		return homeTeamScoredExactly3GoalsPercentage;
	}

	public void setHomeTeamScoredExactly3GoalsPercentage(float homeTeamScoredExactly3GoalsPercentage) {
		this.homeTeamScoredExactly3GoalsPercentage = homeTeamScoredExactly3GoalsPercentage;
	}

	public float getHomeTeamScoredExactly4GoalsPercentage() {
		return homeTeamScoredExactly4GoalsPercentage;
	}

	public void setHomeTeamScoredExactly4GoalsPercentage(float homeTeamScoredExactly4GoalsPercentage) {
		this.homeTeamScoredExactly4GoalsPercentage = homeTeamScoredExactly4GoalsPercentage;
	}

	public float getHomeTeamScoredExactly5GoalsPercentage() {
		return homeTeamScoredExactly5GoalsPercentage;
	}

	public void setHomeTeamScoredExactly5GoalsPercentage(float homeTeamScoredExactly5GoalsPercentage) {
		this.homeTeamScoredExactly5GoalsPercentage = homeTeamScoredExactly5GoalsPercentage;
	}

	public float getHomeTeamConcededExactly0GoalPercentage() {
		return homeTeamConcededExactly0GoalPercentage;
	}

	public void setHomeTeamConcededExactly0GoalPercentage(float homeTeamConcededExactly0GoalPercentage) {
		this.homeTeamConcededExactly0GoalPercentage = homeTeamConcededExactly0GoalPercentage;
	}

	public float getHomeTeamConcededExactly1GoalPercentage() {
		return homeTeamConcededExactly1GoalPercentage;
	}

	public void setHomeTeamConcededExactly1GoalPercentage(float homeTeamConcededExactly1GoalPercentage) {
		this.homeTeamConcededExactly1GoalPercentage = homeTeamConcededExactly1GoalPercentage;
	}

	public float getHomeTeamConcededExactly2GoalsPercentage() {
		return homeTeamConcededExactly2GoalsPercentage;
	}

	public void setHomeTeamConcededExactly2GoalsPercentage(float homeTeamConcededExactly2GoalsPercentage) {
		this.homeTeamConcededExactly2GoalsPercentage = homeTeamConcededExactly2GoalsPercentage;
	}

	public float getHomeTeamConcededExactly3GoalsPercentage() {
		return homeTeamConcededExactly3GoalsPercentage;
	}

	public void setHomeTeamConcededExactly3GoalsPercentage(float homeTeamConcededExactly3GoalsPercentage) {
		this.homeTeamConcededExactly3GoalsPercentage = homeTeamConcededExactly3GoalsPercentage;
	}

	public float getHomeTeamConcededExactly4GoalsPercentage() {
		return homeTeamConcededExactly4GoalsPercentage;
	}

	public void setHomeTeamConcededExactly4GoalsPercentage(float homeTeamConcededExactly4GoalsPercentage) {
		this.homeTeamConcededExactly4GoalsPercentage = homeTeamConcededExactly4GoalsPercentage;
	}

	public float getHomeTeamConcededExactly5GoalsPercentage() {
		return homeTeamConcededExactly5GoalsPercentage;
	}

	public void setHomeTeamConcededExactly5GoalsPercentage(float homeTeamConcededExactly5GoalsPercentage) {
		this.homeTeamConcededExactly5GoalsPercentage = homeTeamConcededExactly5GoalsPercentage;
	}

	public float getAwayTeamScoredExactly0GoalPercentage() {
		return awayTeamScoredExactly0GoalPercentage;
	}

	public void setAwayTeamScoredExactly0GoalPercentage(float awayTeamScoredExactly0GoalPercentage) {
		this.awayTeamScoredExactly0GoalPercentage = awayTeamScoredExactly0GoalPercentage;
	}

	public float getAwayTeamScoredExactly1GoalPercentage() {
		return awayTeamScoredExactly1GoalPercentage;
	}

	public void setAwayTeamScoredExactly1GoalPercentage(float awayTeamScoredExactly1GoalPercentage) {
		this.awayTeamScoredExactly1GoalPercentage = awayTeamScoredExactly1GoalPercentage;
	}

	public float getAwayTeamScoredExactly2GoalsPercentage() {
		return awayTeamScoredExactly2GoalsPercentage;
	}

	public void setAwayTeamScoredExactly2GoalsPercentage(float awayTeamScoredExactly2GoalsPercentage) {
		this.awayTeamScoredExactly2GoalsPercentage = awayTeamScoredExactly2GoalsPercentage;
	}

	public float getAwayTeamScoredExactly3GoalsPercentage() {
		return awayTeamScoredExactly3GoalsPercentage;
	}

	public void setAwayTeamScoredExactly3GoalsPercentage(float awayTeamScoredExactly3GoalsPercentage) {
		this.awayTeamScoredExactly3GoalsPercentage = awayTeamScoredExactly3GoalsPercentage;
	}

	public float getAwayTeamScoredExactly4GoalsPercentage() {
		return awayTeamScoredExactly4GoalsPercentage;
	}

	public void setAwayTeamScoredExactly4GoalsPercentage(float awayTeamScoredExactly4GoalsPercentage) {
		this.awayTeamScoredExactly4GoalsPercentage = awayTeamScoredExactly4GoalsPercentage;
	}

	public float getAwayTeamScoredExactly5GoalsPercentage() {
		return awayTeamScoredExactly5GoalsPercentage;
	}

	public void setAwayTeamScoredExactly5GoalsPercentage(float awayTeamScoredExactly5GoalsPercentage) {
		this.awayTeamScoredExactly5GoalsPercentage = awayTeamScoredExactly5GoalsPercentage;
	}

	public float getAwayTeamConcededExactly0GoalPercentage() {
		return awayTeamConcededExactly0GoalPercentage;
	}

	public void setAwayTeamConcededExactly0GoalPercentage(float awayTeamConcededExactly0GoalPercentage) {
		this.awayTeamConcededExactly0GoalPercentage = awayTeamConcededExactly0GoalPercentage;
	}

	public float getAwayTeamConcededExactly1GoalPercentage() {
		return awayTeamConcededExactly1GoalPercentage;
	}

	public void setAwayTeamConcededExactly1GoalPercentage(float awayTeamConcededExactly1GoalPercentage) {
		this.awayTeamConcededExactly1GoalPercentage = awayTeamConcededExactly1GoalPercentage;
	}

	public float getAwayTeamConcededExactly2GoalsPercentage() {
		return awayTeamConcededExactly2GoalsPercentage;
	}

	public void setAwayTeamConcededExactly2GoalsPercentage(float awayTeamConcededExactly2GoalsPercentage) {
		this.awayTeamConcededExactly2GoalsPercentage = awayTeamConcededExactly2GoalsPercentage;
	}

	public float getAwayTeamConcededExactly3GoalsPercentage() {
		return awayTeamConcededExactly3GoalsPercentage;
	}

	public void setAwayTeamConcededExactly3GoalsPercentage(float awayTeamConcededExactly3GoalsPercentage) {
		this.awayTeamConcededExactly3GoalsPercentage = awayTeamConcededExactly3GoalsPercentage;
	}

	public float getAwayTeamConcededExactly4GoalsPercentage() {
		return awayTeamConcededExactly4GoalsPercentage;
	}

	public void setAwayTeamConcededExactly4GoalsPercentage(float awayTeamConcededExactly4GoalsPercentage) {
		this.awayTeamConcededExactly4GoalsPercentage = awayTeamConcededExactly4GoalsPercentage;
	}

	public float getAwayTeamConcededExactly5GoalsPercentage() {
		return awayTeamConcededExactly5GoalsPercentage;
	}

	public void setAwayTeamConcededExactly5GoalsPercentage(float awayTeamConcededExactly5GoalsPercentage) {
		this.awayTeamConcededExactly5GoalsPercentage = awayTeamConcededExactly5GoalsPercentage;
	}

	
}

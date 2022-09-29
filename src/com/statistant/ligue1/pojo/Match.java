package com.statistant.ligue1.pojo;

import com.statistant.ligue1.dao.DatabaseConnection;
import com.statistant.ligue1.dao.NullTeamException;
import com.statistant.ligue1.utils.Ligue1Utils;

/**
 * An instance of a match.
 * 
 * @author Thomas CHARMES
 */
public class Match {

	String id;
	String homeTeamNickname;
	String awayTeamNickname;
	String homeTeamName;
	String awayTeamName;
	String score;
	String comment;
	Integer homeTeamWin;
	Integer draw;
	Integer awayTeamWin;
	Integer isAnImportantGameForHomeTeam;
	Integer isAnImportantGameForAwayTeam;
	Integer homeTeamHasABetterStanding;
	Integer resetAllSeason;
	Integer countMatch;
	Integer activeStatisticsReportGeneration;
	Integer journey;

	public Match(String homeTeamNickname, String awayTeamNickname, String score, int homeTeamWin, int draw,
			int awayTeamWin, int isAnImportantGameForHomeTeam, int isAnImportantGameForAwayTeam,
			int homeTeamHasABetterStanding, int resetAllSeason, int countMatch, int activeStatisticsReportGeneration,
			int journey, String comment) {
		this.id = homeTeamNickname + "-" + awayTeamNickname;
		this.homeTeamNickname = homeTeamNickname;
		this.awayTeamNickname = awayTeamNickname;
		try {
			this.homeTeamName = DatabaseConnection.getTeamByNickname(homeTeamNickname).getName();
			this.awayTeamName = DatabaseConnection.getTeamByNickname(awayTeamNickname).getName();
		} catch (NullTeamException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
		this.score = score;
		this.homeTeamWin = homeTeamWin;
		this.draw = draw;
		this.awayTeamWin = awayTeamWin;
		this.isAnImportantGameForHomeTeam = isAnImportantGameForHomeTeam;
		this.isAnImportantGameForAwayTeam = isAnImportantGameForAwayTeam;
		this.homeTeamHasABetterStanding = homeTeamHasABetterStanding;
		this.resetAllSeason = resetAllSeason;
		this.countMatch = countMatch;
		this.activeStatisticsReportGeneration = activeStatisticsReportGeneration;
		this.journey = journey;
		this.comment = comment;
	}
	
	public Match(String homeTeamNickname, String awayTeamNickname, String score, int homeTeamWin, int draw,
			int awayTeamWin, int isAnImportantGameForHomeTeam, int isAnImportantGameForAwayTeam,
			int homeTeamHasABetterStanding,
			int journey, String comment) {
		this.id = homeTeamNickname + "-" + awayTeamNickname;
		this.homeTeamNickname = homeTeamNickname;
		this.awayTeamNickname = awayTeamNickname;
		try {
			this.homeTeamName = DatabaseConnection.getTeamByNickname(homeTeamNickname).getName();
			this.awayTeamName = DatabaseConnection.getTeamByNickname(awayTeamNickname).getName();
		} catch (NullTeamException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
		this.score = score;
		this.homeTeamWin = homeTeamWin;
		this.draw = draw;
		this.awayTeamWin = awayTeamWin;
		this.isAnImportantGameForHomeTeam = isAnImportantGameForHomeTeam;
		this.isAnImportantGameForAwayTeam = isAnImportantGameForAwayTeam;
		this.homeTeamHasABetterStanding = homeTeamHasABetterStanding;
		this.resetAllSeason = 0;
		this.countMatch = 0;
		this.activeStatisticsReportGeneration = 0;
		this.journey = journey;
		this.comment = comment;
	}
	
	public Match(String homeTeamNickname, String awayTeamNickname, int isAnImportantGameForHomeTeam, int isAnImportantGameForAwayTeam,
			int homeTeamHasABetterStanding,
			int journey, String comment) {
		this.id = homeTeamNickname + "-" + awayTeamNickname;
		this.homeTeamNickname = homeTeamNickname;
		this.awayTeamNickname = awayTeamNickname;
		try {
			this.homeTeamName = DatabaseConnection.getTeamByNickname(homeTeamNickname).getName();
			this.awayTeamName = DatabaseConnection.getTeamByNickname(awayTeamNickname).getName();
		} catch (NullTeamException e) {
			Ligue1Utils.reportError(e.getMessage());
			return;
		}
		this.score = "";
		this.homeTeamWin = 0;
		this.draw = 0;
		this.awayTeamWin = 0;
		this.isAnImportantGameForHomeTeam = isAnImportantGameForHomeTeam;
		this.isAnImportantGameForAwayTeam = isAnImportantGameForAwayTeam;
		this.homeTeamHasABetterStanding = homeTeamHasABetterStanding;
		this.resetAllSeason = 0;
		this.countMatch = 0;
		this.activeStatisticsReportGeneration = 1;
		this.journey = journey;
		this.comment = comment;
	}

	public String getHomeTeamNickname() {
		return homeTeamNickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setHomeTeamNickname(String homeTeamNickname) {
		this.homeTeamNickname = homeTeamNickname;
	}

	public String getAwayTeamNickname() {
		return awayTeamNickname;
	}

	public void setAwayTeamNickname(String awayTeamNickname) {
		this.awayTeamNickname = awayTeamNickname;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getHomeTeamWin() {
		return homeTeamWin;
	}

	public void setHomeTeamWin(int homeTeamWin) {
		this.homeTeamWin = homeTeamWin;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getAwayTeamWin() {
		return awayTeamWin;
	}

	public void setAwayTeamWin(int awayTeamWin) {
		this.awayTeamWin = awayTeamWin;
	}

	public int getIsAnImportantGameForHomeTeam() {
		return isAnImportantGameForHomeTeam;
	}

	public void setIsAnImportantGameForHomeTeam(int isAnImportantGameForHomeTeam) {
		this.isAnImportantGameForHomeTeam = isAnImportantGameForHomeTeam;
	}

	public int getIsAnImportantGameForAwayTeam() {
		return isAnImportantGameForAwayTeam;
	}

	public void setIsAnImportantGameForAwayTeam(int isAnImportantGameForAwayTeam) {
		this.isAnImportantGameForAwayTeam = isAnImportantGameForAwayTeam;
	}

	public int getHomeTeamHasABetterStanding() {
		return homeTeamHasABetterStanding;
	}

	public void setHomeTeamHasABetterStanding(int homeTeamHasABetterStanding) {
		this.homeTeamHasABetterStanding = homeTeamHasABetterStanding;
	}

	public int getResetAllSeason() {
		return resetAllSeason;
	}

	public void setResetAllSeason(int resetAllSeason) {
		this.resetAllSeason = resetAllSeason;
	}

	public int getCountMatch() {
		return countMatch;
	}

	public void setCountMatch(int countMatch) {
		this.countMatch = countMatch;
	}

	public int getActiveStatisticsReportGeneration() {
		return activeStatisticsReportGeneration;
	}

	public void setActiveStatisticsReportGeneration(int activeStatisticsReportGeneration) {
		this.activeStatisticsReportGeneration = activeStatisticsReportGeneration;
	}

	public int getJourney() {
		return journey;
	}

	public void setJourney(int journey) {
		this.journey = journey;
	}

}

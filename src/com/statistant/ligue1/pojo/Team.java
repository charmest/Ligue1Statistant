package com.statistant.ligue1.pojo;

/**
 * An instance of team and all it's statistics from database.
 * @author Thomas CHARMES
 */
public class Team {

	private String name;
	private String nickName;
	private int standing;
	private int homeStanding;
	private int awayStanding;
	private int nbMatchesPlayed;
	private int nbWins;
	private int nbDraws;
	private int nbLosses;
	private int nbHomeMatchesPlayed;
	private int nbHomeWins;
	private int nbHomeDraws;
	private int nbHomeLosses;
	private int nbAwayMatchesPlayed;
	private int nbAwayWins;
	private int nbAwayDraws;
	private int nbAwayLosses;
	private String recent1;
	private String recent2;
	private String recent3;
	private String recent4;
	private String recent5;
	private int winningSerie;
	private int drawSerie;
	private int loosingSerie;
	private String homeRecent1;
	private String homeRecent2;
	private String homeRecent3;
	private String homeRecent4;
	private String homeRecent5;
	private int homeWinningSerie;
	private int homeDrawSerie;
	private int homeLoosingSerie;
	private String awayRecent1;
	private String awayRecent2;
	private String awayRecent3;
	private String awayRecent4;
	private String awayRecent5;
	private int awayWinningSerie;
	private int awayDrawSerie;
	private int awayLoosingSerie;
	private int nbMatchesPlayedAgainstStandingSuperior;
	private int nbWinsAgainstStandingSuperior;
	private int nbDrawsAgainstStandingSuperior;
	private int nbLossesAgainstStandingSuperior;
	private int nbMatchesPlayedAgainstStandingSuperiorAtHome;
	private int nbWinsAgainstStandingSuperiorAtHome;
	private int nbDrawsAgainstStandingSuperiorAtHome;
	private int nbLossesAgainstStandingSuperiorAtHome;
	private int nbMatchesPlayedAgainstStandingSuperiorAway;
	private int nbWinsAgainstStandingSuperiorAway;
	private int nbDrawsAgainstStandingSuperiorAway;
	private int nbLossesAgainstStandingSuperiorAway;
	private int nbMatchesPlayedAgainstStandingInferior;
	private int nbWinsAgainstStandingInferior;
	private int nbDrawsAgainstStandingInferior;
	private int nbLossesAgainstStandingInferior;
	private int nbMatchesPlayedAgainstStandingInferiorAtHome;
	private int nbWinsAgainstStandingInferiorAtHome;
	private int nbDrawsAgainstStandingInferiorAtHome;
	private int nbLossesAgainstStandingInferiorAtHome;
	private int nbMatchesPlayedAgainstStandingInferiorAway;
	private int nbWinsAgainstStandingInferiorAway;
	private int nbDrawsAgainstStandingInferiorAway;
	private int nbLossesAgainstStandingInferiorAway;
	private int nbMatchesPlayedAgainstImportantOpponent;
	private int nbWinsAgainstImportantOpponent;
	private int nbDrawsAgainstImportantOpponent;
	private int nbLossesAgainstImportantOpponent;
	private int nbMatchesPlayedAgainstImportantOpponentAtHome;
	private int nbWinsAgainstImportantOpponentAtHome;
	private int nbDrawsAgainstImportantOpponentAtHome;
	private int nbLossesAgainstImportantOpponentAtHome;
	private int nbMatchesPlayedAgainstImportantOpponentAway;
	private int nbWinsAgainstImportantOpponentAway;
	private int nbDrawsAgainstImportantOpponentAway;
	private int nbLossesAgainstImportantOpponentAway;
	private int nbMatchesPlayedAgainstNormalOpponent;
	private int nbWinsAgainstNormalOpponent;
	private int nbDrawsAgainstNormalOpponent;
	private int nbLossesAgainstNormalOpponent;
	private int nbMatchesPlayedAgainstNormalOpponentAtHome;
	private int nbWinsAgainstNormalOpponentAtHome;
	private int nbDrawsAgainstNormalOpponentAtHome;
	private int nbLossesAgainstNormalOpponentAtHome;
	private int nbMatchesPlayedAgainstNormalOpponentAway;
	private int nbWinsAgainstNormalOpponentAway;
	private int nbDrawsAgainstNormalOpponentAway;
	private int nbLossesAgainstNormalOpponentAway;
	private int nbPoints;
	private int nbHomePoints;
	private int nbAwayPoints;
	private int goalAverage;
	private int homeGoalAverage;
	private int awayGoalAverage;
	
	public Team(String name, String nickName, int standing, int homeStanding, int awayStanding, int nbMatchesPlayed,
			int nbWins, int nbDraws, int nbLosses, int nbHomeMatchesPlayed, int nbHomeWins, int nbHomeDraws,
			int nbHomeLosses, int nbAwayMatchesPlayed, int nbAwayWins, int nbAwayDraws, int nbAwayLosses,
			String recent1, String recent2, String recent3, String recent4, String recent5, int winningSerie,
			int drawSerie, int loosingSerie, String homeRecent1, String homeRecent2, String homeRecent3,
			String homeRecent4, String homeRecent5, int homeWinningSerie, int homeDrawSerie,
			int homeLoosingSerie, String awayRecent1, String awayRecent2, String awayRecent3, String awayRecent4,
			String awayRecent5, int awayWinningSerie, int awayDrawSerie, int awayLoosingSerie,
			int nbMatchesPlayedAgainstStandingSuperior, int nbWinsAgainstStandingSuperior,
			int nbDrawsAgainstStandingSuperior, int nbLossesAgainstStandingSuperior,
			int nbMatchesPlayedAgainstStandingSuperiorAtHome, int nbWinsAgainstStandingSuperiorAtHome,
			int nbDrawsAgainstStandingSuperiorAtHome, int nbLossesAgainstStandingSuperiorAtHome,
			int nbMatchesPlayedAgainstStandingSuperiorAway, int nbWinsAgainstStandingSuperiorAway,
			int nbDrawsAgainstStandingSuperiorAway, int nbLossesAgainstStandingSuperiorAway,
			int nbMatchesPlayedAgainstStandingInferior, int nbWinsAgainstStandingInferior,
			int nbDrawsAgainstStandingInferior, int nbLossesAgainstStandingInferior,
			int nbMatchesPlayedAgainstStandingInferiorAtHome, int nbWinsAgainstStandingInferiorAtHome,
			int nbDrawsAgainstStandingInferiorAtHome, int nbLossesAgainstStandingInferiorAtHome,
			int nbMatchesPlayedAgainstStandingInferiorAway, int nbWinsAgainstStandingInferiorAway,
			int nbDrawsAgainstStandingInferiorAway, int nbLossesAgainstStandingInferiorAway,
			int nbMatchesPlayedAgainstImportantOpponent, int nbWinsAgainstImportantOpponent,
			int nbDrawsAgainstImportantOpponent, int nbLossesAgainstImportantOpponent,
			int nbMatchesPlayedAgainstImportantOpponentAtHome, int nbWinsAgainstImportantOpponentAtHome,
			int nbDrawsAgainstImportantOpponentAtHome, int nbLossesAgainstImportantOpponentAtHome,
			int nbMatchesPlayedAgainstImportantOpponentAway, int nbWinsAgainstImportantOpponentAway,
			int nbDrawsAgainstImportantOpponentAway, int nbLossesAgainstImportantOpponentAway,
			int nbMatchesPlayedAgainstNormalOpponent, int nbWinsAgainstNormalOpponent, int nbDrawsAgainstNormalOpponent,
			int nbLossesAgainstNormalOpponent, int nbMatchesPlayedAgainstNormalOpponentAtHome,
			int nbWinsAgainstNormalOpponentAtHome, int nbDrawsAgainstNormalOpponentAtHome,
			int nbLossesAgainstNormalOpponentAtHome, int nbMatchesPlayedAgainstNormalOpponentAway,
			int nbWinsAgainstNormalOpponentAway, int nbDrawsAgainstNormalOpponentAway,
			int nbLossesAgainstNormalOpponentAway, int nbPoints, int nbHomePoints, int nbAwayPoints, int goalAverage,
			int homeGoalAverage, int awayGoalAverage) {
		this.name = name;
		this.nickName = nickName;
		this.standing = standing;
		this.homeStanding = homeStanding;
		this.awayStanding = awayStanding;
		this.nbMatchesPlayed = nbMatchesPlayed;
		this.nbWins = nbWins;
		this.nbDraws = nbDraws;
		this.nbLosses = nbLosses;
		this.nbHomeMatchesPlayed = nbHomeMatchesPlayed;
		this.nbHomeWins = nbHomeWins;
		this.nbHomeDraws = nbHomeDraws;
		this.nbHomeLosses = nbHomeLosses;
		this.nbAwayMatchesPlayed = nbAwayMatchesPlayed;
		this.nbAwayWins = nbAwayWins;
		this.nbAwayDraws = nbAwayDraws;
		this.nbAwayLosses = nbAwayLosses;
		this.recent1 = recent1;
		this.recent2 = recent2;
		this.recent3 = recent3;
		this.recent4 = recent4;
		this.recent5 = recent5;
		this.winningSerie = winningSerie;
		this.drawSerie = drawSerie;
		this.loosingSerie = loosingSerie;
		this.homeRecent1 = homeRecent1;
		this.homeRecent2 = homeRecent2;
		this.homeRecent3 = homeRecent3;
		this.homeRecent4 = homeRecent4;
		this.homeRecent5 = homeRecent5;
		this.homeWinningSerie = homeWinningSerie;
		this.homeDrawSerie = homeDrawSerie;
		this.homeLoosingSerie = homeLoosingSerie;
		this.awayRecent1 = awayRecent1;
		this.awayRecent2 = awayRecent2;
		this.awayRecent3 = awayRecent3;
		this.awayRecent4 = awayRecent4;
		this.awayRecent5 = awayRecent5;
		this.awayWinningSerie = awayWinningSerie;
		this.awayDrawSerie = awayDrawSerie;
		this.awayLoosingSerie = awayLoosingSerie;
		this.nbMatchesPlayedAgainstStandingSuperior = nbMatchesPlayedAgainstStandingSuperior;
		this.nbWinsAgainstStandingSuperior = nbWinsAgainstStandingSuperior;
		this.nbDrawsAgainstStandingSuperior = nbDrawsAgainstStandingSuperior;
		this.nbLossesAgainstStandingSuperior = nbLossesAgainstStandingSuperior;
		this.nbMatchesPlayedAgainstStandingSuperiorAtHome = nbMatchesPlayedAgainstStandingSuperiorAtHome;
		this.nbWinsAgainstStandingSuperiorAtHome = nbWinsAgainstStandingSuperiorAtHome;
		this.nbDrawsAgainstStandingSuperiorAtHome = nbDrawsAgainstStandingSuperiorAtHome;
		this.nbLossesAgainstStandingSuperiorAtHome = nbLossesAgainstStandingSuperiorAtHome;
		this.nbMatchesPlayedAgainstStandingSuperiorAway = nbMatchesPlayedAgainstStandingSuperiorAway;
		this.nbWinsAgainstStandingSuperiorAway = nbWinsAgainstStandingSuperiorAway;
		this.nbDrawsAgainstStandingSuperiorAway = nbDrawsAgainstStandingSuperiorAway;
		this.nbLossesAgainstStandingSuperiorAway = nbLossesAgainstStandingSuperiorAway;
		this.nbMatchesPlayedAgainstStandingInferior = nbMatchesPlayedAgainstStandingInferior;
		this.nbWinsAgainstStandingInferior = nbWinsAgainstStandingInferior;
		this.nbDrawsAgainstStandingInferior = nbDrawsAgainstStandingInferior;
		this.nbLossesAgainstStandingInferior = nbLossesAgainstStandingInferior;
		this.nbMatchesPlayedAgainstStandingInferiorAtHome = nbMatchesPlayedAgainstStandingInferiorAtHome;
		this.nbWinsAgainstStandingInferiorAtHome = nbWinsAgainstStandingInferiorAtHome;
		this.nbDrawsAgainstStandingInferiorAtHome = nbDrawsAgainstStandingInferiorAtHome;
		this.nbLossesAgainstStandingInferiorAtHome = nbLossesAgainstStandingInferiorAtHome;
		this.nbMatchesPlayedAgainstStandingInferiorAway = nbMatchesPlayedAgainstStandingInferiorAway;
		this.nbWinsAgainstStandingInferiorAway = nbWinsAgainstStandingInferiorAway;
		this.nbDrawsAgainstStandingInferiorAway = nbDrawsAgainstStandingInferiorAway;
		this.nbLossesAgainstStandingInferiorAway = nbLossesAgainstStandingInferiorAway;
		this.nbMatchesPlayedAgainstImportantOpponent = nbMatchesPlayedAgainstImportantOpponent;
		this.nbWinsAgainstImportantOpponent = nbWinsAgainstImportantOpponent;
		this.nbDrawsAgainstImportantOpponent = nbDrawsAgainstImportantOpponent;
		this.nbLossesAgainstImportantOpponent = nbLossesAgainstImportantOpponent;
		this.nbMatchesPlayedAgainstImportantOpponentAtHome = nbMatchesPlayedAgainstImportantOpponentAtHome;
		this.nbWinsAgainstImportantOpponentAtHome = nbWinsAgainstImportantOpponentAtHome;
		this.nbDrawsAgainstImportantOpponentAtHome = nbDrawsAgainstImportantOpponentAtHome;
		this.nbLossesAgainstImportantOpponentAtHome = nbLossesAgainstImportantOpponentAtHome;
		this.nbMatchesPlayedAgainstImportantOpponentAway = nbMatchesPlayedAgainstImportantOpponentAway;
		this.nbWinsAgainstImportantOpponentAway = nbWinsAgainstImportantOpponentAway;
		this.nbDrawsAgainstImportantOpponentAway = nbDrawsAgainstImportantOpponentAway;
		this.nbLossesAgainstImportantOpponentAway = nbLossesAgainstImportantOpponentAway;
		this.nbMatchesPlayedAgainstNormalOpponent = nbMatchesPlayedAgainstNormalOpponent;
		this.nbWinsAgainstNormalOpponent = nbWinsAgainstNormalOpponent;
		this.nbDrawsAgainstNormalOpponent = nbDrawsAgainstNormalOpponent;
		this.nbLossesAgainstNormalOpponent = nbLossesAgainstNormalOpponent;
		this.nbMatchesPlayedAgainstNormalOpponentAtHome = nbMatchesPlayedAgainstNormalOpponentAtHome;
		this.nbWinsAgainstNormalOpponentAtHome = nbWinsAgainstNormalOpponentAtHome;
		this.nbDrawsAgainstNormalOpponentAtHome = nbDrawsAgainstNormalOpponentAtHome;
		this.nbLossesAgainstNormalOpponentAtHome = nbLossesAgainstNormalOpponentAtHome;
		this.nbMatchesPlayedAgainstNormalOpponentAway = nbMatchesPlayedAgainstNormalOpponentAway;
		this.nbWinsAgainstNormalOpponentAway = nbWinsAgainstNormalOpponentAway;
		this.nbDrawsAgainstNormalOpponentAway = nbDrawsAgainstNormalOpponentAway;
		this.nbLossesAgainstNormalOpponentAway = nbLossesAgainstNormalOpponentAway;
		this.nbPoints = nbPoints;
		this.nbHomePoints = nbHomePoints;
		this.nbAwayPoints = nbAwayPoints;
		this.goalAverage = goalAverage;
		this.homeGoalAverage = homeGoalAverage;
		this.awayGoalAverage = awayGoalAverage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getStanding() {
		return standing;
	}

	public void setStanding(int standing) {
		this.standing = standing;
	}

	public int getHomeStanding() {
		return homeStanding;
	}

	public void setHomeStanding(int homeStanding) {
		this.homeStanding = homeStanding;
	}

	public int getAwayStanding() {
		return awayStanding;
	}

	public void setAwayStanding(int awayStanding) {
		this.awayStanding = awayStanding;
	}

	public int getNbMatchesPlayed() {
		return nbMatchesPlayed;
	}

	public void setNbMatchesPlayed(int nbMatchesPlayed) {
		this.nbMatchesPlayed = nbMatchesPlayed;
	}

	public int getNbWins() {
		return nbWins;
	}

	public void setNbWins(int nbWins) {
		this.nbWins = nbWins;
	}

	public int getNbDraws() {
		return nbDraws;
	}

	public void setNbDraws(int nbDraws) {
		this.nbDraws = nbDraws;
	}

	public int getNbLosses() {
		return nbLosses;
	}

	public void setNbLosses(int nbLosses) {
		this.nbLosses = nbLosses;
	}

	public int getNbHomeMatchesPlayed() {
		return nbHomeMatchesPlayed;
	}

	public void setNbHomeMatchesPlayed(int nbHomeMatchesPlayed) {
		this.nbHomeMatchesPlayed = nbHomeMatchesPlayed;
	}

	public int getNbHomeWins() {
		return nbHomeWins;
	}

	public void setNbHomeWins(int nbHomeWins) {
		this.nbHomeWins = nbHomeWins;
	}

	public int getNbHomeDraws() {
		return nbHomeDraws;
	}

	public void setNbHomeDraws(int nbHomeDraws) {
		this.nbHomeDraws = nbHomeDraws;
	}

	public int getNbHomeLosses() {
		return nbHomeLosses;
	}

	public void setNbHomeLosses(int nbHomeLosses) {
		this.nbHomeLosses = nbHomeLosses;
	}

	public int getNbAwayMatchesPlayed() {
		return nbAwayMatchesPlayed;
	}

	public void setNbAwayMatchesPlayed(int nbAwayMatchesPlayed) {
		this.nbAwayMatchesPlayed = nbAwayMatchesPlayed;
	}

	public int getNbAwayWins() {
		return nbAwayWins;
	}

	public void setNbAwayWins(int nbAwayWins) {
		this.nbAwayWins = nbAwayWins;
	}

	public int getNbAwayDraws() {
		return nbAwayDraws;
	}

	public void setNbAwayDraws(int nbAwayDraws) {
		this.nbAwayDraws = nbAwayDraws;
	}

	public int getNbAwayLosses() {
		return nbAwayLosses;
	}

	public void setNbAwayLosses(int nbAwayLosses) {
		this.nbAwayLosses = nbAwayLosses;
	}

	public String getRecent1() {
		return recent1;
	}

	public void setRecent1(String recent1) {
		this.recent1 = recent1;
	}

	public String getRecent2() {
		return recent2;
	}

	public void setRecent2(String recent2) {
		this.recent2 = recent2;
	}

	public String getRecent3() {
		return recent3;
	}

	public void setRecent3(String recent3) {
		this.recent3 = recent3;
	}

	public String getRecent4() {
		return recent4;
	}

	public void setRecent4(String recent4) {
		this.recent4 = recent4;
	}

	public String getRecent5() {
		return recent5;
	}

	public void setRecent5(String recent5) {
		this.recent5 = recent5;
	}

	public int getWinningSerie() {
		return winningSerie;
	}

	public void setWinningSerie(int winningSerie) {
		this.winningSerie = winningSerie;
	}

	public int getDrawSerie() {
		return drawSerie;
	}

	public void setDrawSerie(int drawSerie) {
		this.drawSerie = drawSerie;
	}

	public int getLoosingSerie() {
		return loosingSerie;
	}

	public void setLoosingSerie(int loosingSerie) {
		this.loosingSerie = loosingSerie;
	}

	public String getHomeRecent1() {
		return homeRecent1;
	}

	public void setHomeRecent1(String homeRecent1) {
		this.homeRecent1 = homeRecent1;
	}

	public String getHomeRecent2() {
		return homeRecent2;
	}

	public void setHomeRecent2(String homeRecent2) {
		this.homeRecent2 = homeRecent2;
	}

	public String getHomeRecent3() {
		return homeRecent3;
	}

	public void setHomeRecent3(String homeRecent3) {
		this.homeRecent3 = homeRecent3;
	}

	public String getHomeRecent4() {
		return homeRecent4;
	}

	public void setHomeRecent4(String homeRecent4) {
		this.homeRecent4 = homeRecent4;
	}

	public String getHomeRecent5() {
		return homeRecent5;
	}

	public void setHomeRecent5(String homeRecent5) {
		this.homeRecent5 = homeRecent5;
	}

	public int getHomeWinningSerie() {
		return homeWinningSerie;
	}

	public void setHomeWinningSerie(int homeWinningSerie) {
		this.homeWinningSerie = homeWinningSerie;
	}

	public int getHomeDrawSerie() {
		return homeDrawSerie;
	}

	public void setHomeDrawSerie(int homeDrawSerie) {
		this.homeDrawSerie = homeDrawSerie;
	}

	public int getHomeLoosingSerie() {
		return homeLoosingSerie;
	}

	public void setHomeLoosingSerie(int homeLoosingSerie) {
		this.homeLoosingSerie = homeLoosingSerie;
	}

	public String getAwayRecent1() {
		return awayRecent1;
	}

	public void setAwayRecent1(String awayRecent1) {
		this.awayRecent1 = awayRecent1;
	}

	public String getAwayRecent2() {
		return awayRecent2;
	}

	public void setAwayRecent2(String awayRecent2) {
		this.awayRecent2 = awayRecent2;
	}

	public String getAwayRecent3() {
		return awayRecent3;
	}

	public void setAwayRecent3(String awayRecent3) {
		this.awayRecent3 = awayRecent3;
	}

	public String getAwayRecent4() {
		return awayRecent4;
	}

	public void setAwayRecent4(String awayRecent4) {
		this.awayRecent4 = awayRecent4;
	}

	public String getAwayRecent5() {
		return awayRecent5;
	}

	public void setAwayRecent5(String awayRecent5) {
		this.awayRecent5 = awayRecent5;
	}

	public int getAwayWinningSerie() {
		return awayWinningSerie;
	}

	public void setAwayWinningSerie(int awayWinningSerie) {
		this.awayWinningSerie = awayWinningSerie;
	}

	public int getAwayDrawSerie() {
		return awayDrawSerie;
	}

	public void setAwayDrawSerie(int awayDrawSerie) {
		this.awayDrawSerie = awayDrawSerie;
	}

	public int getAwayLoosingSerie() {
		return awayLoosingSerie;
	}

	public void setAwayLoosingSerie(int awayLoosingSerie) {
		this.awayLoosingSerie = awayLoosingSerie;
	}

	public int getNbMatchesPlayedAgainstStandingSuperior() {
		return nbMatchesPlayedAgainstStandingSuperior;
	}

	public void setNbMatchesPlayedAgainstStandingSuperior(int nbMatchesPlayedAgainstStandingSuperior) {
		this.nbMatchesPlayedAgainstStandingSuperior = nbMatchesPlayedAgainstStandingSuperior;
	}

	public int getNbWinsAgainstStandingSuperior() {
		return nbWinsAgainstStandingSuperior;
	}

	public void setNbWinsAgainstStandingSuperior(int nbWinsAgainstStandingSuperior) {
		this.nbWinsAgainstStandingSuperior = nbWinsAgainstStandingSuperior;
	}

	public int getNbDrawsAgainstStandingSuperior() {
		return nbDrawsAgainstStandingSuperior;
	}

	public void setNbDrawsAgainstStandingSuperior(int nbDrawsAgainstStandingSuperior) {
		this.nbDrawsAgainstStandingSuperior = nbDrawsAgainstStandingSuperior;
	}

	public int getNbLossesAgainstStandingSuperior() {
		return nbLossesAgainstStandingSuperior;
	}

	public void setNbLossesAgainstStandingSuperior(int nbLossesAgainstStandingSuperior) {
		this.nbLossesAgainstStandingSuperior = nbLossesAgainstStandingSuperior;
	}

	public int getNbMatchesPlayedAgainstStandingSuperiorAtHome() {
		return nbMatchesPlayedAgainstStandingSuperiorAtHome;
	}

	public void setNbMatchesPlayedAgainstStandingSuperiorAtHome(int nbMatchesPlayedAgainstStandingSuperiorAtHome) {
		this.nbMatchesPlayedAgainstStandingSuperiorAtHome = nbMatchesPlayedAgainstStandingSuperiorAtHome;
	}

	public int getNbWinsAgainstStandingSuperiorAtHome() {
		return nbWinsAgainstStandingSuperiorAtHome;
	}

	public void setNbWinsAgainstStandingSuperiorAtHome(int nbWinsAgainstStandingSuperiorAtHome) {
		this.nbWinsAgainstStandingSuperiorAtHome = nbWinsAgainstStandingSuperiorAtHome;
	}

	public int getNbDrawsAgainstStandingSuperiorAtHome() {
		return nbDrawsAgainstStandingSuperiorAtHome;
	}

	public void setNbDrawsAgainstStandingSuperiorAtHome(int nbDrawsAgainstStandingSuperiorAtHome) {
		this.nbDrawsAgainstStandingSuperiorAtHome = nbDrawsAgainstStandingSuperiorAtHome;
	}

	public int getNbLossesAgainstStandingSuperiorAtHome() {
		return nbLossesAgainstStandingSuperiorAtHome;
	}

	public void setNbLossesAgainstStandingSuperiorAtHome(int nbLossesAgainstStandingSuperiorAtHome) {
		this.nbLossesAgainstStandingSuperiorAtHome = nbLossesAgainstStandingSuperiorAtHome;
	}

	public int getNbMatchesPlayedAgainstStandingSuperiorAway() {
		return nbMatchesPlayedAgainstStandingSuperiorAway;
	}

	public void setNbMatchesPlayedAgainstStandingSuperiorAway(int nbMatchesPlayedAgainstStandingSuperiorAway) {
		this.nbMatchesPlayedAgainstStandingSuperiorAway = nbMatchesPlayedAgainstStandingSuperiorAway;
	}

	public int getNbWinsAgainstStandingSuperiorAway() {
		return nbWinsAgainstStandingSuperiorAway;
	}

	public void setNbWinsAgainstStandingSuperiorAway(int nbWinsAgainstStandingSuperiorAway) {
		this.nbWinsAgainstStandingSuperiorAway = nbWinsAgainstStandingSuperiorAway;
	}

	public int getNbDrawsAgainstStandingSuperiorAway() {
		return nbDrawsAgainstStandingSuperiorAway;
	}

	public void setNbDrawsAgainstStandingSuperiorAway(int nbDrawsAgainstStandingSuperiorAway) {
		this.nbDrawsAgainstStandingSuperiorAway = nbDrawsAgainstStandingSuperiorAway;
	}

	public int getNbLossesAgainstStandingSuperiorAway() {
		return nbLossesAgainstStandingSuperiorAway;
	}

	public void setNbLossesAgainstStandingSuperiorAway(int nbLossesAgainstStandingSuperiorAway) {
		this.nbLossesAgainstStandingSuperiorAway = nbLossesAgainstStandingSuperiorAway;
	}

	public int getNbMatchesPlayedAgainstStandingInferior() {
		return nbMatchesPlayedAgainstStandingInferior;
	}

	public void setNbMatchesPlayedAgainstStandingInferior(int nbMatchesPlayedAgainstStandingInferior) {
		this.nbMatchesPlayedAgainstStandingInferior = nbMatchesPlayedAgainstStandingInferior;
	}

	public int getNbWinsAgainstStandingInferior() {
		return nbWinsAgainstStandingInferior;
	}

	public void setNbWinsAgainstStandingInferior(int nbWinsAgainstStandingInferior) {
		this.nbWinsAgainstStandingInferior = nbWinsAgainstStandingInferior;
	}

	public int getNbDrawsAgainstStandingInferior() {
		return nbDrawsAgainstStandingInferior;
	}

	public void setNbDrawsAgainstStandingInferior(int nbDrawsAgainstStandingInferior) {
		this.nbDrawsAgainstStandingInferior = nbDrawsAgainstStandingInferior;
	}

	public int getNbLossesAgainstStandingInferior() {
		return nbLossesAgainstStandingInferior;
	}

	public void setNbLossesAgainstStandingInferior(int nbLossesAgainstStandingInferior) {
		this.nbLossesAgainstStandingInferior = nbLossesAgainstStandingInferior;
	}

	public int getNbMatchesPlayedAgainstStandingInferiorAtHome() {
		return nbMatchesPlayedAgainstStandingInferiorAtHome;
	}

	public void setNbMatchesPlayedAgainstStandingInferiorAtHome(int nbMatchesPlayedAgainstStandingInferiorAtHome) {
		this.nbMatchesPlayedAgainstStandingInferiorAtHome = nbMatchesPlayedAgainstStandingInferiorAtHome;
	}

	public int getNbWinsAgainstStandingInferiorAtHome() {
		return nbWinsAgainstStandingInferiorAtHome;
	}

	public void setNbWinsAgainstStandingInferiorAtHome(int nbWinsAgainstStandingInferiorAtHome) {
		this.nbWinsAgainstStandingInferiorAtHome = nbWinsAgainstStandingInferiorAtHome;
	}

	public int getNbDrawsAgainstStandingInferiorAtHome() {
		return nbDrawsAgainstStandingInferiorAtHome;
	}

	public void setNbDrawsAgainstStandingInferiorAtHome(int nbDrawsAgainstStandingInferiorAtHome) {
		this.nbDrawsAgainstStandingInferiorAtHome = nbDrawsAgainstStandingInferiorAtHome;
	}

	public int getNbLossesAgainstStandingInferiorAtHome() {
		return nbLossesAgainstStandingInferiorAtHome;
	}

	public void setNbLossesAgainstStandingInferiorAtHome(int nbLossesAgainstStandingInferiorAtHome) {
		this.nbLossesAgainstStandingInferiorAtHome = nbLossesAgainstStandingInferiorAtHome;
	}

	public int getNbMatchesPlayedAgainstStandingInferiorAway() {
		return nbMatchesPlayedAgainstStandingInferiorAway;
	}

	public void setNbMatchesPlayedAgainstStandingInferiorAway(int nbMatchesPlayedAgainstStandingInferiorAway) {
		this.nbMatchesPlayedAgainstStandingInferiorAway = nbMatchesPlayedAgainstStandingInferiorAway;
	}

	public int getNbWinsAgainstStandingInferiorAway() {
		return nbWinsAgainstStandingInferiorAway;
	}

	public void setNbWinsAgainstStandingInferiorAway(int nbWinsAgainstStandingInferiorAway) {
		this.nbWinsAgainstStandingInferiorAway = nbWinsAgainstStandingInferiorAway;
	}

	public int getNbDrawsAgainstStandingInferiorAway() {
		return nbDrawsAgainstStandingInferiorAway;
	}

	public void setNbDrawsAgainstStandingInferiorAway(int nbDrawsAgainstStandingInferiorAway) {
		this.nbDrawsAgainstStandingInferiorAway = nbDrawsAgainstStandingInferiorAway;
	}

	public int getNbLossesAgainstStandingInferiorAway() {
		return nbLossesAgainstStandingInferiorAway;
	}

	public void setNbLossesAgainstStandingInferiorAway(int nbLossesAgainstStandingInferiorAway) {
		this.nbLossesAgainstStandingInferiorAway = nbLossesAgainstStandingInferiorAway;
	}

	public int getNbMatchesPlayedAgainstImportantOpponent() {
		return nbMatchesPlayedAgainstImportantOpponent;
	}

	public void setNbMatchesPlayedAgainstImportantOpponent(int nbMatchesPlayedAgainstImportantOpponent) {
		this.nbMatchesPlayedAgainstImportantOpponent = nbMatchesPlayedAgainstImportantOpponent;
	}

	public int getNbWinsAgainstImportantOpponent() {
		return nbWinsAgainstImportantOpponent;
	}

	public void setNbWinsAgainstImportantOpponent(int nbWinsAgainstImportantOpponent) {
		this.nbWinsAgainstImportantOpponent = nbWinsAgainstImportantOpponent;
	}

	public int getNbDrawsAgainstImportantOpponent() {
		return nbDrawsAgainstImportantOpponent;
	}

	public void setNbDrawsAgainstImportantOpponent(int nbDrawsAgainstImportantOpponent) {
		this.nbDrawsAgainstImportantOpponent = nbDrawsAgainstImportantOpponent;
	}

	public int getNbLossesAgainstImportantOpponent() {
		return nbLossesAgainstImportantOpponent;
	}

	public void setNbLossesAgainstImportantOpponent(int nbLossesAgainstImportantOpponent) {
		this.nbLossesAgainstImportantOpponent = nbLossesAgainstImportantOpponent;
	}

	public int getNbMatchesPlayedAgainstImportantOpponentAtHome() {
		return nbMatchesPlayedAgainstImportantOpponentAtHome;
	}

	public void setNbMatchesPlayedAgainstImportantOpponentAtHome(int nbMatchesPlayedAgainstImportantOpponentAtHome) {
		this.nbMatchesPlayedAgainstImportantOpponentAtHome = nbMatchesPlayedAgainstImportantOpponentAtHome;
	}

	public int getNbWinsAgainstImportantOpponentAtHome() {
		return nbWinsAgainstImportantOpponentAtHome;
	}

	public void setNbWinsAgainstImportantOpponentAtHome(int nbWinsAgainstImportantOpponentAtHome) {
		this.nbWinsAgainstImportantOpponentAtHome = nbWinsAgainstImportantOpponentAtHome;
	}

	public int getNbDrawsAgainstImportantOpponentAtHome() {
		return nbDrawsAgainstImportantOpponentAtHome;
	}

	public void setNbDrawsAgainstImportantOpponentAtHome(int nbDrawsAgainstImportantOpponentAtHome) {
		this.nbDrawsAgainstImportantOpponentAtHome = nbDrawsAgainstImportantOpponentAtHome;
	}

	public int getNbLossesAgainstImportantOpponentAtHome() {
		return nbLossesAgainstImportantOpponentAtHome;
	}

	public void setNbLossesAgainstImportantOpponentAtHome(int nbLossesAgainstImportantOpponentAtHome) {
		this.nbLossesAgainstImportantOpponentAtHome = nbLossesAgainstImportantOpponentAtHome;
	}

	public int getNbMatchesPlayedAgainstImportantOpponentAway() {
		return nbMatchesPlayedAgainstImportantOpponentAway;
	}

	public void setNbMatchesPlayedAgainstImportantOpponentAway(int nbMatchesPlayedAgainstImportantOpponentAway) {
		this.nbMatchesPlayedAgainstImportantOpponentAway = nbMatchesPlayedAgainstImportantOpponentAway;
	}

	public int getNbWinsAgainstImportantOpponentAway() {
		return nbWinsAgainstImportantOpponentAway;
	}

	public void setNbWinsAgainstImportantOpponentAway(int nbWinsAgainstImportantOpponentAway) {
		this.nbWinsAgainstImportantOpponentAway = nbWinsAgainstImportantOpponentAway;
	}

	public int getNbDrawsAgainstImportantOpponentAway() {
		return nbDrawsAgainstImportantOpponentAway;
	}

	public void setNbDrawsAgainstImportantOpponentAway(int nbDrawsAgainstImportantOpponentAway) {
		this.nbDrawsAgainstImportantOpponentAway = nbDrawsAgainstImportantOpponentAway;
	}

	public int getNbLossesAgainstImportantOpponentAway() {
		return nbLossesAgainstImportantOpponentAway;
	}

	public void setNbLossesAgainstImportantOpponentAway(int nbLossesAgainstImportantOpponentAway) {
		this.nbLossesAgainstImportantOpponentAway = nbLossesAgainstImportantOpponentAway;
	}

	public int getNbMatchesPlayedAgainstNormalOpponent() {
		return nbMatchesPlayedAgainstNormalOpponent;
	}

	public void setNbMatchesPlayedAgainstNormalOpponent(int nbMatchesPlayedAgainstNormalOpponent) {
		this.nbMatchesPlayedAgainstNormalOpponent = nbMatchesPlayedAgainstNormalOpponent;
	}

	public int getNbWinsAgainstNormalOpponent() {
		return nbWinsAgainstNormalOpponent;
	}

	public void setNbWinsAgainstNormalOpponent(int nbWinsAgainstNormalOpponent) {
		this.nbWinsAgainstNormalOpponent = nbWinsAgainstNormalOpponent;
	}

	public int getNbDrawsAgainstNormalOpponent() {
		return nbDrawsAgainstNormalOpponent;
	}

	public void setNbDrawsAgainstNormalOpponent(int nbDrawsAgainstNormalOpponent) {
		this.nbDrawsAgainstNormalOpponent = nbDrawsAgainstNormalOpponent;
	}

	public int getNbLossesAgainstNormalOpponent() {
		return nbLossesAgainstNormalOpponent;
	}

	public void setNbLossesAgainstNormalOpponent(int nbLossesAgainstNormalOpponent) {
		this.nbLossesAgainstNormalOpponent = nbLossesAgainstNormalOpponent;
	}

	public int getNbMatchesPlayedAgainstNormalOpponentAtHome() {
		return nbMatchesPlayedAgainstNormalOpponentAtHome;
	}

	public void setNbMatchesPlayedAgainstNormalOpponentAtHome(int nbMatchesPlayedAgainstNormalOpponentAtHome) {
		this.nbMatchesPlayedAgainstNormalOpponentAtHome = nbMatchesPlayedAgainstNormalOpponentAtHome;
	}

	public int getNbWinsAgainstNormalOpponentAtHome() {
		return nbWinsAgainstNormalOpponentAtHome;
	}

	public void setNbWinsAgainstNormalOpponentAtHome(int nbWinsAgainstNormalOpponentAtHome) {
		this.nbWinsAgainstNormalOpponentAtHome = nbWinsAgainstNormalOpponentAtHome;
	}

	public int getNbDrawsAgainstNormalOpponentAtHome() {
		return nbDrawsAgainstNormalOpponentAtHome;
	}

	public void setNbDrawsAgainstNormalOpponentAtHome(int nbDrawsAgainstNormalOpponentAtHome) {
		this.nbDrawsAgainstNormalOpponentAtHome = nbDrawsAgainstNormalOpponentAtHome;
	}

	public int getNbLossesAgainstNormalOpponentAtHome() {
		return nbLossesAgainstNormalOpponentAtHome;
	}

	public void setNbLossesAgainstNormalOpponentAtHome(int nbLossesAgainstNormalOpponentAtHome) {
		this.nbLossesAgainstNormalOpponentAtHome = nbLossesAgainstNormalOpponentAtHome;
	}

	public int getNbMatchesPlayedAgainstNormalOpponentAway() {
		return nbMatchesPlayedAgainstNormalOpponentAway;
	}

	public void setNbMatchesPlayedAgainstNormalOpponentAway(int nbMatchesPlayedAgainstNormalOpponentAway) {
		this.nbMatchesPlayedAgainstNormalOpponentAway = nbMatchesPlayedAgainstNormalOpponentAway;
	}

	public int getNbWinsAgainstNormalOpponentAway() {
		return nbWinsAgainstNormalOpponentAway;
	}

	public void setNbWinsAgainstNormalOpponentAway(int nbWinsAgainstNormalOpponentAway) {
		this.nbWinsAgainstNormalOpponentAway = nbWinsAgainstNormalOpponentAway;
	}

	public int getNbDrawsAgainstNormalOpponentAway() {
		return nbDrawsAgainstNormalOpponentAway;
	}

	public void setNbDrawsAgainstNormalOpponentAway(int nbDrawsAgainstNormalOpponentAway) {
		this.nbDrawsAgainstNormalOpponentAway = nbDrawsAgainstNormalOpponentAway;
	}

	public int getNbLossesAgainstNormalOpponentAway() {
		return nbLossesAgainstNormalOpponentAway;
	}

	public void setNbLossesAgainstNormalOpponentAway(int nbLossesAgainstNormalOpponentAway) {
		this.nbLossesAgainstNormalOpponentAway = nbLossesAgainstNormalOpponentAway;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	public int getNbHomePoints() {
		return nbHomePoints;
	}

	public void setNbHomePoints(int nbHomePoints) {
		this.nbHomePoints = nbHomePoints;
	}

	public int getNbAwayPoints() {
		return nbAwayPoints;
	}

	public void setNbAwayPoints(int nbAwayPoints) {
		this.nbAwayPoints = nbAwayPoints;
	}

	public int getGoalAverage() {
		return goalAverage;
	}

	public void setGoalAverage(int goalAverage) {
		this.goalAverage = goalAverage;
	}

	public int getHomeGoalAverage() {
		return homeGoalAverage;
	}

	public void setHomeGoalAverage(int homeGoalAverage) {
		this.homeGoalAverage = homeGoalAverage;
	}

	public int getAwayGoalAverage() {
		return awayGoalAverage;
	}

	public void setAwayGoalAverage(int awayGoalAverage) {
		this.awayGoalAverage = awayGoalAverage;
	}

}

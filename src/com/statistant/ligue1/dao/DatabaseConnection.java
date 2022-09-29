package com.statistant.ligue1.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.smattme.MysqlExportService;
import com.statistant.ligue1.controller.NullConfrontationException;
import com.statistant.ligue1.controller.NullStatisticException;
import com.statistant.ligue1.pojo.Confrontation;
import com.statistant.ligue1.pojo.Match;
import com.statistant.ligue1.pojo.Statistic;
import com.statistant.ligue1.pojo.Team;
import com.statistant.ligue1.pojo.User;
import com.statistant.ligue1.utils.Ligue1Utils;

public class DatabaseConnection {

	private static Connection connection;
	
	private static String CONNECTION_HOST = "";
	private static String CONNECTION_DB_NAME = "";
	private static String CONNECTION_USER_NAME = "";
	private static String CONNECTION_USER_PASSWORD = "";
	
	private static final String CONNECTION_PATH = "jdbc:mysql://"+CONNECTION_HOST+"/"+CONNECTION_DB_NAME+"?user="+CONNECTION_USER_NAME+"&password="+CONNECTION_USER_PASSWORD+"&useSSL=false&serverTimezone=UTC";
	
	public static Connection initializeOrGetConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				InputStream input = DatabaseConnection.class.getResourceAsStream("connection.properties");
				Properties connectionProperties = new Properties();
				connectionProperties.load(input);
				CONNECTION_HOST = connectionProperties.getProperty("CONNECTION_HOST");
				CONNECTION_DB_NAME = connectionProperties.getProperty("CONNECTION_DB_NAME");
				CONNECTION_USER_NAME = connectionProperties.getProperty("CONNECTION_USER_NAME");
				CONNECTION_USER_PASSWORD = connectionProperties.getProperty("CONNECTION_USER_PASSWORD");
				connection = DriverManager.getConnection(CONNECTION_PATH);
			} catch (SQLException | ClassNotFoundException | IOException e) {
				Ligue1Utils.reportError("Erreur à la connexion à la base de données ! Merci de vérifier votre connexion internet.");
				e.printStackTrace();
				return null;
			}
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				Ligue1Utils.reportError("Erreur à la fermeture de la connexion : " + e.getMessage());
				e.printStackTrace();
				return;
			}
		}
	}

	public static Team getTeamByNickname(String nickname) throws NullTeamException {
		if (Ligue1Utils.isEmpty(nickname)) {
			throw new NullTeamException("Merci de renseigner le nom de l'équipe");
		}
		Connection cn = initializeOrGetConnection();
		String query = "";
		Team team = null;
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM teams WHERE surnom = \"" + nickname + "\"";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString("nom");
				int standing = rs.getInt("classement");
				int homeStanding = rs.getInt("classementDomicile");
				int awayStanding = rs.getInt("classementExterieur");
				int nbMatchesPlayed = rs.getInt("nombreMatchsJoues");
				int nbWins = rs.getInt("nombreMatchsGagnes");
				int nbDraws = rs.getInt("nombreMatchsNuls");
				int nbLosses = rs.getInt("nombreMatchsPerdus");
				int nbHomeMatchesPlayed = rs.getInt("nombreMatchsJouesDomicile");
				int nbHomeWins = rs.getInt("nombreMatchsGagnesDomicile");
				int nbHomeDraws = rs.getInt("nombreMatchsNulsDomicile");
				int nbHomeLosses = rs.getInt("nombreMatchsPerdusDomicile");
				int nbAwayMatchesPlayed = rs.getInt("nombreMatchsJouesExterieur");
				int nbAwayWins = rs.getInt("nombreMatchsGagnesExterieur");
				int nbAwayDraws = rs.getInt("nombreMatchsNulsExterieur");
				int nbAwayLosses = rs.getInt("nombreMatchsPerdusExterieur");
				String recent1 = rs.getString("matchPrecedentUn");
				String recent2 = rs.getString("matchPrecedentDeux");
				String recent3 = rs.getString("matchPrecedentTrois");
				String recent4 = rs.getString("matchPrecedentQuatre");
				String recent5 = rs.getString("matchPrecedentCinq");
				int winningSerie = rs.getInt("SerieVEnCours");
				int drawSerie = rs.getInt("SerieNEnCours");
				int loosingSerie = rs.getInt("SerieDEnCours");
				String homeRecent1 = rs.getString("matchPrecedentUnDomicile");
				String homeRecent2 = rs.getString("matchPrecedentDeuxDomicile");
				String homeRecent3 = rs.getString("matchPrecedentTroisDomicile");
				String homeRecent4 = rs.getString("matchPrecedentQuatreDomicile");
				String homeRecent5 = rs.getString("matchPrecedentCinqDomicile");
				int homeWinningSerie = rs.getInt("SerieVEnCoursDomicile");
				int homeDrawSerie = rs.getInt("SerieNEnCoursDomicile");
				int homeLoosingSerie = rs.getInt("SerieDEnCoursDomicile");
				String awayRecent1 = rs.getString("matchPrecedentUnExterieur");
				String awayRecent2 = rs.getString("matchPrecedentDeuxExterieur");
				String awayRecent3 = rs.getString("matchPrecedentTroisExterieur");
				String awayRecent4 = rs.getString("matchPrecedentQuatreExterieur");
				String awayRecent5 = rs.getString("matchPrecedentCinqExterieur");
				int awayWinningSerie = rs.getInt("SerieVEnCoursExterieur");
				int awayDrawSerie = rs.getInt("SerieNEnCoursExterieur");
				int awayLoosingSerie = rs.getInt("SerieDEnCoursExterieur");
				int nbMatchesPlayedAgainstStandingSuperior = rs.getInt("NombreMatchsJouesClassementSup");
				int nbWinsAgainstStandingSuperior = rs.getInt("NombreVClassementSup");
				int nbDrawsAgainstStandingSuperior = rs.getInt("NombreNClassementSup");
				int nbLossesAgainstStandingSuperior = rs.getInt("NombreDClassementSup");
				int nbMatchesPlayedAgainstStandingSuperiorAtHome = rs.getInt("NombreMatchsJouesClassementSupDomicile");
				int nbWinsAgainstStandingSuperiorAtHome = rs.getInt("NombreVClassementSupDomicile");
				int nbDrawsAgainstStandingSuperiorAtHome = rs.getInt("NombreNClassementSupDomicile");
				int nbLossesAgainstStandingSuperiorAtHome = rs.getInt("NombreDClassementSupDomicile");
				int nbMatchesPlayedAgainstStandingSuperiorAway = rs.getInt("NombreMatchsJouesClassementSupExterieur");
				int nbWinsAgainstStandingSuperiorAway = rs.getInt("NombreVClassementSupExterieur");
				int nbDrawsAgainstStandingSuperiorAway = rs.getInt("NombreNClassementSupExterieur");
				int nbLossesAgainstStandingSuperiorAway = rs.getInt("NombreDClassementSupExterieur");
				int nbMatchesPlayedAgainstStandingInferior = rs.getInt("NombreMatchsJouesClassementInf");
				int nbWinsAgainstStandingInferior = rs.getInt("NombreVClassementInf");
				int nbDrawsAgainstStandingInferior = rs.getInt("NombreNClassementInf");
				int nbLossesAgainstStandingInferior = rs.getInt("NombreDClassementInf");
				int nbMatchesPlayedAgainstStandingInferiorAtHome = rs.getInt("NombreMatchsJouesClassementInfDomicile");
				int nbWinsAgainstStandingInferiorAtHome = rs.getInt("NombreVClassementInfDomicile");
				int nbDrawsAgainstStandingInferiorAtHome = rs.getInt("NombreNClassementInfDomicile");
				int nbLossesAgainstStandingInferiorAtHome = rs.getInt("NombreDClassementInfDomicile");
				int nbMatchesPlayedAgainstStandingInferiorAway = rs.getInt("NombreMatchsJouesClassementInfExterieur");
				int nbWinsAgainstStandingInferiorAway = rs.getInt("NombreVClassementInfExterieur");
				int nbDrawsAgainstStandingInferiorAway = rs.getInt("NombreNClassementInfExterieur");
				int nbLossesAgainstStandingInferiorAway = rs.getInt("NombreDClassementInfExterieur");
				int nbMatchesPlayedAgainstImportantOpponent = rs.getInt("NombreMatchsJouesImportants");
				int nbWinsAgainstImportantOpponent = rs.getInt("NombreVMatchsImportants");
				int nbDrawsAgainstImportantOpponent = rs.getInt("NombreNMatchsImportants");
				int nbLossesAgainstImportantOpponent = rs.getInt("NombreDMatchsImportants");
				int nbMatchesPlayedAgainstImportantOpponentAtHome = rs.getInt("NombreMatchsJouesImportantsDomicile");
				int nbWinsAgainstImportantOpponentAtHome = rs.getInt("NombreVImportantsDomicile");
				int nbDrawsAgainstImportantOpponentAtHome = rs.getInt("NombreNImportantsDomicile");
				int nbLossesAgainstImportantOpponentAtHome = rs.getInt("NombreDImportantsDomicile");
				int nbMatchesPlayedAgainstImportantOpponentAway = rs.getInt("NombreMatchsJouesImportantsExterieur");
				int nbWinsAgainstImportantOpponentAway = rs.getInt("NombreVImportantsExterieur");
				int nbDrawsAgainstImportantOpponentAway = rs.getInt("NombreNImportantsExterieur");
				int nbLossesAgainstImportantOpponentAway = rs.getInt("NombreDImportantsExterieur");
				int nbMatchesPlayedAgainstNormalOpponent = rs.getInt("NombreMatchsJouesBanal");
				int nbWinsAgainstNormalOpponent = rs.getInt("NombreVMatchBanal");
				int nbDrawsAgainstNormalOpponent = rs.getInt("NombreNMatchBanal");
				int nbLossesAgainstNormalOpponent = rs.getInt("NombreDMatchBanal");
				int nbMatchesPlayedAgainstNormalOpponentAtHome = rs.getInt("NombreMatchsJouesBanalDomicile");
				int nbWinsAgainstNormalOpponentAtHome = rs.getInt("NombreVMatchBanalDomicile");
				int nbDrawsAgainstNormalOpponentAtHome = rs.getInt("NombreNMatchBanalDomicile");
				int nbLossesAgainstNormalOpponentAtHome = rs.getInt("NombreDMatchBanalDomicile");
				int nbMatchesPlayedAgainstNormalOpponentAway = rs.getInt("NombreMatchsJouesBanalExterieur");
				int nbWinsAgainstNormalOpponentAway = rs.getInt("NombreVMatchBanalExterieur");
				int nbDrawsAgainstNormalOpponentAway = rs.getInt("NombreNMatchBanalExterieur");
				int nbLossesAgainstNormalOpponentAway = rs.getInt("NombreDMatchBanalExterieur");
				int nbPoints = rs.getInt("NombrePoints");
				int nbHomePoints = rs.getInt("NombrePointsDomicile");
				int nbAwayPoints = rs.getInt("NombrePointsExterieur");
				int goalAverage = rs.getInt("GoalAverage");
				int homeGoalAverage = rs.getInt("HomeGoalAverage");
				int awayGoalAverage = rs.getInt("AwayGoalAverage");

				team = new Team(name, nickname, standing, homeStanding, awayStanding, nbMatchesPlayed, nbWins, nbDraws,
						nbLosses, nbHomeMatchesPlayed, nbHomeWins, nbHomeDraws, nbHomeLosses, nbAwayMatchesPlayed,
						nbAwayWins, nbAwayDraws, nbAwayLosses, recent1, recent2, recent3, recent4, recent5,
						winningSerie, drawSerie, loosingSerie, homeRecent1, homeRecent2, homeRecent3, homeRecent4,
						homeRecent5, homeWinningSerie, homeDrawSerie, homeLoosingSerie, awayRecent1, awayRecent2,
						awayRecent3, awayRecent4, awayRecent5, awayWinningSerie, awayDrawSerie, awayLoosingSerie,
						nbMatchesPlayedAgainstStandingSuperior, nbWinsAgainstStandingSuperior,
						nbDrawsAgainstStandingSuperior, nbLossesAgainstStandingSuperior,
						nbMatchesPlayedAgainstStandingSuperiorAtHome, nbWinsAgainstStandingSuperiorAtHome,
						nbDrawsAgainstStandingSuperiorAtHome, nbLossesAgainstStandingSuperiorAtHome,
						nbMatchesPlayedAgainstStandingSuperiorAway, nbWinsAgainstStandingSuperiorAway,
						nbDrawsAgainstStandingSuperiorAway, nbLossesAgainstStandingSuperiorAway,
						nbMatchesPlayedAgainstStandingInferior, nbWinsAgainstStandingInferior,
						nbDrawsAgainstStandingInferior, nbLossesAgainstStandingInferior,
						nbMatchesPlayedAgainstStandingInferiorAtHome, nbWinsAgainstStandingInferiorAtHome,
						nbDrawsAgainstStandingInferiorAtHome, nbLossesAgainstStandingInferiorAtHome,
						nbMatchesPlayedAgainstStandingInferiorAway, nbWinsAgainstStandingInferiorAway,
						nbDrawsAgainstStandingInferiorAway, nbLossesAgainstStandingInferiorAway,
						nbMatchesPlayedAgainstImportantOpponent, nbWinsAgainstImportantOpponent,
						nbDrawsAgainstImportantOpponent, nbLossesAgainstImportantOpponent,
						nbMatchesPlayedAgainstImportantOpponentAtHome, nbWinsAgainstImportantOpponentAtHome,
						nbDrawsAgainstImportantOpponentAtHome, nbLossesAgainstImportantOpponentAtHome,
						nbMatchesPlayedAgainstImportantOpponentAway, nbWinsAgainstImportantOpponentAway,
						nbDrawsAgainstImportantOpponentAway, nbLossesAgainstImportantOpponentAway,
						nbMatchesPlayedAgainstNormalOpponent, nbWinsAgainstNormalOpponent, nbDrawsAgainstNormalOpponent,
						nbLossesAgainstNormalOpponent, nbMatchesPlayedAgainstNormalOpponentAtHome,
						nbWinsAgainstNormalOpponentAtHome, nbDrawsAgainstNormalOpponentAtHome,
						nbLossesAgainstNormalOpponentAtHome, nbMatchesPlayedAgainstNormalOpponentAway,
						nbWinsAgainstNormalOpponentAway, nbDrawsAgainstNormalOpponentAway,
						nbLossesAgainstNormalOpponentAway, nbPoints, nbHomePoints, nbAwayPoints, goalAverage,
						homeGoalAverage, awayGoalAverage);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError(
					"Erreur à la récupération de l'équipe " + nickname + " dans la table avec la requête : " + query);
			e.printStackTrace();
			return null;
		}
		if (team == null) {
			throw new NullTeamException(
					"L'équipe " + nickname + " n'évolue pas en Ligue 1. Merci de réitérer la saisie.");
		}
		return team;
	}

	public static Team getTeamByFullName(String fullName) throws NullTeamException {
		if (Ligue1Utils.isEmpty(fullName)) {
			throw new NullTeamException("Merci de renseigner le nom de l'équipe");
		}
		Connection cn = initializeOrGetConnection();
		String query = "";
		Team team = null;
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM teams WHERE nom = \"" + fullName + "\"";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String nickName = rs.getString("surnom");
				int standing = rs.getInt("classement");
				int homeStanding = rs.getInt("classementDomicile");
				int awayStanding = rs.getInt("classementExterieur");
				int nbMatchesPlayed = rs.getInt("nombreMatchsJoues");
				int nbWins = rs.getInt("nombreMatchsGagnes");
				int nbDraws = rs.getInt("nombreMatchsNuls");
				int nbLosses = rs.getInt("nombreMatchsPerdus");
				int nbHomeMatchesPlayed = rs.getInt("nombreMatchsJouesDomicile");
				int nbHomeWins = rs.getInt("nombreMatchsGagnesDomicile");
				int nbHomeDraws = rs.getInt("nombreMatchsNulsDomicile");
				int nbHomeLosses = rs.getInt("nombreMatchsPerdusDomicile");
				int nbAwayMatchesPlayed = rs.getInt("nombreMatchsJouesExterieur");
				int nbAwayWins = rs.getInt("nombreMatchsGagnesExterieur");
				int nbAwayDraws = rs.getInt("nombreMatchsNulsExterieur");
				int nbAwayLosses = rs.getInt("nombreMatchsPerdusExterieur");
				String recent1 = rs.getString("matchPrecedentUn");
				String recent2 = rs.getString("matchPrecedentDeux");
				String recent3 = rs.getString("matchPrecedentTrois");
				String recent4 = rs.getString("matchPrecedentQuatre");
				String recent5 = rs.getString("matchPrecedentCinq");
				int winningSerie = rs.getInt("SerieVEnCours");
				int drawSerie = rs.getInt("SerieNEnCours");
				int loosingSerie = rs.getInt("SerieDEnCours");
				String homeRecent1 = rs.getString("matchPrecedentUnDomicile");
				String homeRecent2 = rs.getString("matchPrecedentDeuxDomicile");
				String homeRecent3 = rs.getString("matchPrecedentTroisDomicile");
				String homeRecent4 = rs.getString("matchPrecedentQuatreDomicile");
				String homeRecent5 = rs.getString("matchPrecedentCinqDomicile");
				int homeWinningSerie = rs.getInt("SerieVEnCoursDomicile");
				int homeDrawSerie = rs.getInt("SerieNEnCoursDomicile");
				int homeLoosingSerie = rs.getInt("SerieDEnCoursDomicile");
				String awayRecent1 = rs.getString("matchPrecedentUnExterieur");
				String awayRecent2 = rs.getString("matchPrecedentDeuxExterieur");
				String awayRecent3 = rs.getString("matchPrecedentTroisExterieur");
				String awayRecent4 = rs.getString("matchPrecedentQuatreExterieur");
				String awayRecent5 = rs.getString("matchPrecedentCinqExterieur");
				int awayWinningSerie = rs.getInt("SerieVEnCoursExterieur");
				int awayDrawSerie = rs.getInt("SerieNEnCoursExterieur");
				int awayLoosingSerie = rs.getInt("SerieDEnCoursExterieur");
				int nbMatchesPlayedAgainstStandingSuperior = rs.getInt("NombreMatchsJouesClassementSup");
				int nbWinsAgainstStandingSuperior = rs.getInt("NombreVClassementSup");
				int nbDrawsAgainstStandingSuperior = rs.getInt("NombreNClassementSup");
				int nbLossesAgainstStandingSuperior = rs.getInt("NombreDClassementSup");
				int nbMatchesPlayedAgainstStandingSuperiorAtHome = rs.getInt("NombreMatchsJouesClassementSupDomicile");
				int nbWinsAgainstStandingSuperiorAtHome = rs.getInt("NombreVClassementSupDomicile");
				int nbDrawsAgainstStandingSuperiorAtHome = rs.getInt("NombreNClassementSupDomicile");
				int nbLossesAgainstStandingSuperiorAtHome = rs.getInt("NombreDClassementSupDomicile");
				int nbMatchesPlayedAgainstStandingSuperiorAway = rs.getInt("NombreMatchsJouesClassementSupExterieur");
				int nbWinsAgainstStandingSuperiorAway = rs.getInt("NombreVClassementSupExterieur");
				int nbDrawsAgainstStandingSuperiorAway = rs.getInt("NombreNClassementSupExterieur");
				int nbLossesAgainstStandingSuperiorAway = rs.getInt("NombreDClassementSupExterieur");
				int nbMatchesPlayedAgainstStandingInferior = rs.getInt("NombreMatchsJouesClassementInf");
				int nbWinsAgainstStandingInferior = rs.getInt("NombreVClassementInf");
				int nbDrawsAgainstStandingInferior = rs.getInt("NombreNClassementInf");
				int nbLossesAgainstStandingInferior = rs.getInt("NombreDClassementInf");
				int nbMatchesPlayedAgainstStandingInferiorAtHome = rs.getInt("NombreMatchsJouesClassementInfDomicile");
				int nbWinsAgainstStandingInferiorAtHome = rs.getInt("NombreVClassementInfDomicile");
				int nbDrawsAgainstStandingInferiorAtHome = rs.getInt("NombreNClassementInfDomicile");
				int nbLossesAgainstStandingInferiorAtHome = rs.getInt("NombreDClassementInfDomicile");
				int nbMatchesPlayedAgainstStandingInferiorAway = rs.getInt("NombreMatchsJouesClassementInfExterieur");
				int nbWinsAgainstStandingInferiorAway = rs.getInt("NombreVClassementInfExterieur");
				int nbDrawsAgainstStandingInferiorAway = rs.getInt("NombreNClassementInfExterieur");
				int nbLossesAgainstStandingInferiorAway = rs.getInt("NombreDClassementInfExterieur");
				int nbMatchesPlayedAgainstImportantOpponent = rs.getInt("NombreMatchsJouesImportants");
				int nbWinsAgainstImportantOpponent = rs.getInt("NombreVMatchsImportants");
				int nbDrawsAgainstImportantOpponent = rs.getInt("NombreNMatchsImportants");
				int nbLossesAgainstImportantOpponent = rs.getInt("NombreDMatchsImportants");
				int nbMatchesPlayedAgainstImportantOpponentAtHome = rs.getInt("NombreMatchsJouesImportantsDomicile");
				int nbWinsAgainstImportantOpponentAtHome = rs.getInt("NombreVImportantsDomicile");
				int nbDrawsAgainstImportantOpponentAtHome = rs.getInt("NombreNImportantsDomicile");
				int nbLossesAgainstImportantOpponentAtHome = rs.getInt("NombreDImportantsDomicile");
				int nbMatchesPlayedAgainstImportantOpponentAway = rs.getInt("NombreMatchsJouesImportantsExterieur");
				int nbWinsAgainstImportantOpponentAway = rs.getInt("NombreVImportantsExterieur");
				int nbDrawsAgainstImportantOpponentAway = rs.getInt("NombreNImportantsExterieur");
				int nbLossesAgainstImportantOpponentAway = rs.getInt("NombreDImportantsExterieur");
				int nbMatchesPlayedAgainstNormalOpponent = rs.getInt("NombreMatchsJouesBanal");
				int nbWinsAgainstNormalOpponent = rs.getInt("NombreVMatchBanal");
				int nbDrawsAgainstNormalOpponent = rs.getInt("NombreNMatchBanal");
				int nbLossesAgainstNormalOpponent = rs.getInt("NombreDMatchBanal");
				int nbMatchesPlayedAgainstNormalOpponentAtHome = rs.getInt("NombreMatchsJouesBanalDomicile");
				int nbWinsAgainstNormalOpponentAtHome = rs.getInt("NombreVMatchBanalDomicile");
				int nbDrawsAgainstNormalOpponentAtHome = rs.getInt("NombreNMatchBanalDomicile");
				int nbLossesAgainstNormalOpponentAtHome = rs.getInt("NombreDMatchBanalDomicile");
				int nbMatchesPlayedAgainstNormalOpponentAway = rs.getInt("NombreMatchsJouesBanalExterieur");
				int nbWinsAgainstNormalOpponentAway = rs.getInt("NombreVMatchBanalExterieur");
				int nbDrawsAgainstNormalOpponentAway = rs.getInt("NombreNMatchBanalExterieur");
				int nbLossesAgainstNormalOpponentAway = rs.getInt("NombreDMatchBanalExterieur");
				int nbPoints = rs.getInt("NombrePoints");
				int nbHomePoints = rs.getInt("NombrePointsDomicile");
				int nbAwayPoints = rs.getInt("NombrePointsExterieur");
				int goalAverage = rs.getInt("GoalAverage");
				int homeGoalAverage = rs.getInt("HomeGoalAverage");
				int awayGoalAverage = rs.getInt("AwayGoalAverage");

				team = new Team(fullName, nickName, standing, homeStanding, awayStanding, nbMatchesPlayed, nbWins,
						nbDraws, nbLosses, nbHomeMatchesPlayed, nbHomeWins, nbHomeDraws, nbHomeLosses,
						nbAwayMatchesPlayed, nbAwayWins, nbAwayDraws, nbAwayLosses, recent1, recent2, recent3, recent4,
						recent5, winningSerie, drawSerie, loosingSerie, homeRecent1, homeRecent2, homeRecent3,
						homeRecent4, homeRecent5, homeWinningSerie, homeDrawSerie, homeLoosingSerie, awayRecent1,
						awayRecent2, awayRecent3, awayRecent4, awayRecent5, awayWinningSerie, awayDrawSerie,
						awayLoosingSerie, nbMatchesPlayedAgainstStandingSuperior, nbWinsAgainstStandingSuperior,
						nbDrawsAgainstStandingSuperior, nbLossesAgainstStandingSuperior,
						nbMatchesPlayedAgainstStandingSuperiorAtHome, nbWinsAgainstStandingSuperiorAtHome,
						nbDrawsAgainstStandingSuperiorAtHome, nbLossesAgainstStandingSuperiorAtHome,
						nbMatchesPlayedAgainstStandingSuperiorAway, nbWinsAgainstStandingSuperiorAway,
						nbDrawsAgainstStandingSuperiorAway, nbLossesAgainstStandingSuperiorAway,
						nbMatchesPlayedAgainstStandingInferior, nbWinsAgainstStandingInferior,
						nbDrawsAgainstStandingInferior, nbLossesAgainstStandingInferior,
						nbMatchesPlayedAgainstStandingInferiorAtHome, nbWinsAgainstStandingInferiorAtHome,
						nbDrawsAgainstStandingInferiorAtHome, nbLossesAgainstStandingInferiorAtHome,
						nbMatchesPlayedAgainstStandingInferiorAway, nbWinsAgainstStandingInferiorAway,
						nbDrawsAgainstStandingInferiorAway, nbLossesAgainstStandingInferiorAway,
						nbMatchesPlayedAgainstImportantOpponent, nbWinsAgainstImportantOpponent,
						nbDrawsAgainstImportantOpponent, nbLossesAgainstImportantOpponent,
						nbMatchesPlayedAgainstImportantOpponentAtHome, nbWinsAgainstImportantOpponentAtHome,
						nbDrawsAgainstImportantOpponentAtHome, nbLossesAgainstImportantOpponentAtHome,
						nbMatchesPlayedAgainstImportantOpponentAway, nbWinsAgainstImportantOpponentAway,
						nbDrawsAgainstImportantOpponentAway, nbLossesAgainstImportantOpponentAway,
						nbMatchesPlayedAgainstNormalOpponent, nbWinsAgainstNormalOpponent, nbDrawsAgainstNormalOpponent,
						nbLossesAgainstNormalOpponent, nbMatchesPlayedAgainstNormalOpponentAtHome,
						nbWinsAgainstNormalOpponentAtHome, nbDrawsAgainstNormalOpponentAtHome,
						nbLossesAgainstNormalOpponentAtHome, nbMatchesPlayedAgainstNormalOpponentAway,
						nbWinsAgainstNormalOpponentAway, nbDrawsAgainstNormalOpponentAway,
						nbLossesAgainstNormalOpponentAway, nbPoints, nbHomePoints, nbAwayPoints, goalAverage,
						homeGoalAverage, awayGoalAverage);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError(
					"Erreur à la récupération de l'équipe " + fullName + " dans la table avec la requête : " + query);
			e.printStackTrace();
			return null;
		}
		if (team == null) {
			throw new NullTeamException(
					"L'équipe " + fullName + " n'évolue pas en Ligue 1. Merci de réitérer la saisie.");
		}
		return team;
	}

	public static Collection<Team> getAllTeams() {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<Team> allTeams = new ArrayList<Team>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM teams";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String nickname = rs.getString("surnom");
				Team team = getTeamByNickname(nickname);
				allTeams.add(team);
			}
		} catch (SQLException | NullTeamException e) {
			Ligue1Utils.reportError("Erreur à la récupération de toutes les équipes de Ligue 1 : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return allTeams;
	}

	public static void createOrUpdateTeam(Team team) {
		Connection cn = initializeOrGetConnection();
		try {
			Statement st = cn.createStatement();
			String select = "SELECT * FROM teams WHERE teams.nom = \"" + team.getName() + "\"";

			String update1 = getQueryToUpdateInTableTeams("surnom", team, team.getNickName());
			String update2 = getQueryToUpdateInTableTeams("classement", team, team.getStanding());
			String update3 = getQueryToUpdateInTableTeams("classementDomicile", team, team.getHomeStanding());
			String update4 = getQueryToUpdateInTableTeams("classementExterieur", team, team.getAwayStanding());
			String update5 = getQueryToUpdateInTableTeams("nombreMatchsJoues", team, team.getNbMatchesPlayed());
			String update6 = getQueryToUpdateInTableTeams("nombreMatchsGagnes", team, team.getNbWins());
			String update7 = getQueryToUpdateInTableTeams("nombreMatchsNuls", team, team.getNbDraws());
			String update8 = getQueryToUpdateInTableTeams("nombreMatchsPerdus", team, team.getNbLosses());
			String update9 = getQueryToUpdateInTableTeams("nombreMatchsJouesDomicile", team,
					team.getNbHomeMatchesPlayed());
			String update10 = getQueryToUpdateInTableTeams("nombreMatchsGagnesDomicile", team, team.getNbHomeWins());
			String update11 = getQueryToUpdateInTableTeams("nombreMatchsNulsDomicile", team, team.getNbHomeDraws());
			String update12 = getQueryToUpdateInTableTeams("nombreMatchsPerdusDomicile", team, team.getNbHomeLosses());
			String update13 = getQueryToUpdateInTableTeams("nombreMatchsJouesExterieur", team,
					team.getNbAwayMatchesPlayed());
			String update14 = getQueryToUpdateInTableTeams("nombreMatchsGagnesExterieur", team, team.getNbAwayWins());
			String update15 = getQueryToUpdateInTableTeams("nombreMatchsNulsExterieur", team, team.getNbAwayDraws());
			String update16 = getQueryToUpdateInTableTeams("nombreMatchsPerdusExterieur", team, team.getNbAwayLosses());
			String update17 = getQueryToUpdateInTableTeams("matchPrecedentUn", team, team.getRecent1());
			String update18 = getQueryToUpdateInTableTeams("matchPrecedentDeux", team, team.getRecent2());
			String update19 = getQueryToUpdateInTableTeams("matchPrecedentTrois", team, team.getRecent3());
			String update20 = getQueryToUpdateInTableTeams("matchPrecedentQuatre", team, team.getRecent4());
			String update21 = getQueryToUpdateInTableTeams("matchPrecedentCinq", team, team.getRecent5());
			String update22 = getQueryToUpdateInTableTeams("SerieVEnCours", team, team.getWinningSerie());
			String update23 = getQueryToUpdateInTableTeams("SerieNEnCours", team, team.getDrawSerie());
			String update24 = getQueryToUpdateInTableTeams("SerieDEnCours", team, team.getLoosingSerie());
			String update25 = getQueryToUpdateInTableTeams("MatchPrecedentUnDomicile", team, team.getHomeRecent1());
			String update26 = getQueryToUpdateInTableTeams("MatchPrecedentDeuxDomicile", team, team.getHomeRecent2());
			String update27 = getQueryToUpdateInTableTeams("MatchPrecedentTroisDomicile", team, team.getHomeRecent3());
			String update28 = getQueryToUpdateInTableTeams("MatchPrecedentQuatreDomicile", team, team.getHomeRecent4());
			String update29 = getQueryToUpdateInTableTeams("MatchPrecedentCinqDomicile", team, team.getHomeRecent5());
			String update30 = getQueryToUpdateInTableTeams("SerieVEnCoursDomicile", team, team.getHomeWinningSerie());
			String update31 = getQueryToUpdateInTableTeams("SerieNEnCoursDomicile", team, team.getHomeDrawSerie());
			String update32 = getQueryToUpdateInTableTeams("SerieDEnCoursDomicile", team, team.getHomeLoosingSerie());
			String update33 = getQueryToUpdateInTableTeams("MatchPrecedentUnExterieur", team, team.getAwayRecent1());
			String update34 = getQueryToUpdateInTableTeams("MatchPrecedentDeuxExterieur", team, team.getAwayRecent2());
			String update35 = getQueryToUpdateInTableTeams("MatchPrecedentTroisExterieur", team, team.getAwayRecent3());
			String update36 = getQueryToUpdateInTableTeams("MatchPrecedentQuatreExterieur", team,
					team.getAwayRecent4());
			String update37 = getQueryToUpdateInTableTeams("MatchPrecedentCinqExterieur", team, team.getAwayRecent5());
			String update38 = getQueryToUpdateInTableTeams("SerieVEnCoursExterieur", team, team.getAwayWinningSerie());
			String update39 = getQueryToUpdateInTableTeams("SerieNEnCoursExterieur", team, team.getAwayDrawSerie());
			String update40 = getQueryToUpdateInTableTeams("SerieDEnCoursExterieur", team, team.getAwayLoosingSerie());
			String update41 = getQueryToUpdateInTableTeams("NombreMatchsJouesClassementSup", team,
					team.getNbMatchesPlayedAgainstStandingSuperior());
			String update42 = getQueryToUpdateInTableTeams("NombreVClassementSup", team,
					team.getNbWinsAgainstStandingSuperior());
			String update43 = getQueryToUpdateInTableTeams("NombreNClassementSup", team,
					team.getNbDrawsAgainstStandingSuperior());
			String update44 = getQueryToUpdateInTableTeams("NombreDClassementSup", team,
					team.getNbLossesAgainstStandingSuperior());
			String update45 = getQueryToUpdateInTableTeams("NombreMatchsJouesClassementSupDomicile", team,
					team.getNbMatchesPlayedAgainstStandingSuperiorAtHome());
			String update46 = getQueryToUpdateInTableTeams("NombreVClassementSupDomicile", team,
					team.getNbWinsAgainstStandingSuperiorAtHome());
			String update47 = getQueryToUpdateInTableTeams("NombreNClassementSupDomicile", team,
					team.getNbDrawsAgainstStandingSuperiorAtHome());
			String update48 = getQueryToUpdateInTableTeams("NombreDClassementSupDomicile", team,
					team.getNbLossesAgainstStandingSuperiorAtHome());
			String update49 = getQueryToUpdateInTableTeams("NombreMatchsJouesClassementSupExterieur", team,
					team.getNbMatchesPlayedAgainstStandingSuperiorAway());
			String update50 = getQueryToUpdateInTableTeams("NombreVClassementSupExterieur", team,
					team.getNbWinsAgainstStandingSuperiorAway());
			String update51 = getQueryToUpdateInTableTeams("NombreNClassementSupExterieur", team,
					team.getNbDrawsAgainstStandingSuperiorAway());
			String update52 = getQueryToUpdateInTableTeams("NombreDClassementSupExterieur", team,
					team.getNbLossesAgainstStandingSuperiorAway());
			String update53 = getQueryToUpdateInTableTeams("NombreMatchsJouesClassementInf", team,
					team.getNbMatchesPlayedAgainstStandingInferior());
			String update54 = getQueryToUpdateInTableTeams("NombreVClassementInf", team,
					team.getNbWinsAgainstStandingInferior());
			String update55 = getQueryToUpdateInTableTeams("NombreNClassementInf", team,
					team.getNbDrawsAgainstStandingInferior());
			String update56 = getQueryToUpdateInTableTeams("NombreDClassementInf", team,
					team.getNbLossesAgainstStandingInferior());
			String update57 = getQueryToUpdateInTableTeams("NombreMatchsJouesClassementInfDomicile", team,
					team.getNbMatchesPlayedAgainstStandingInferiorAtHome());
			String update58 = getQueryToUpdateInTableTeams("NombreVClassementInfDomicile", team,
					team.getNbWinsAgainstStandingInferiorAtHome());
			String update59 = getQueryToUpdateInTableTeams("NombreNClassementInfDomicile", team,
					team.getNbDrawsAgainstStandingInferiorAtHome());
			String update60 = getQueryToUpdateInTableTeams("NombreDClassementInfDomicile", team,
					team.getNbLossesAgainstStandingInferiorAtHome());
			String update61 = getQueryToUpdateInTableTeams("NombreMatchsJouesClassementInfExterieur", team,
					team.getNbMatchesPlayedAgainstStandingInferiorAway());
			String update62 = getQueryToUpdateInTableTeams("NombreVClassementInfExterieur", team,
					team.getNbWinsAgainstStandingInferiorAway());
			String update63 = getQueryToUpdateInTableTeams("NombreNClassementInfExterieur", team,
					team.getNbDrawsAgainstStandingInferiorAway());
			String update64 = getQueryToUpdateInTableTeams("NombreDClassementInfExterieur", team,
					team.getNbLossesAgainstStandingInferiorAway());
			String update65 = getQueryToUpdateInTableTeams("NombreMatchsJouesImportants", team,
					team.getNbMatchesPlayedAgainstImportantOpponent());
			String update66 = getQueryToUpdateInTableTeams("NombreVMatchsImportants", team,
					team.getNbWinsAgainstImportantOpponent());
			String update67 = getQueryToUpdateInTableTeams("NombreNMatchsImportants", team,
					team.getNbDrawsAgainstImportantOpponent());
			String update68 = getQueryToUpdateInTableTeams("NombreDMatchsImportants", team,
					team.getNbLossesAgainstImportantOpponent());
			String update69 = getQueryToUpdateInTableTeams("NombreMatchsJouesImportantsDomicile", team,
					team.getNbMatchesPlayedAgainstImportantOpponentAtHome());
			String update70 = getQueryToUpdateInTableTeams("NombreVImportantsDomicile", team,
					team.getNbWinsAgainstImportantOpponentAtHome());
			String update71 = getQueryToUpdateInTableTeams("NombreNImportantsDomicile", team,
					team.getNbDrawsAgainstImportantOpponentAtHome());
			String update72 = getQueryToUpdateInTableTeams("NombreDImportantsDomicile", team,
					team.getNbLossesAgainstImportantOpponentAtHome());
			String update73 = getQueryToUpdateInTableTeams("NombreMatchsJouesImportantsExterieur", team,
					team.getNbMatchesPlayedAgainstImportantOpponentAway());
			String update74 = getQueryToUpdateInTableTeams("NombreVImportantsExterieur", team,
					team.getNbWinsAgainstImportantOpponentAway());
			String update75 = getQueryToUpdateInTableTeams("NombreNImportantsExterieur", team,
					team.getNbDrawsAgainstImportantOpponentAway());
			String update76 = getQueryToUpdateInTableTeams("NombreDImportantsExterieur", team,
					team.getNbLossesAgainstImportantOpponentAway());
			String update77 = getQueryToUpdateInTableTeams("NombreMatchsJouesBanal", team,
					team.getNbMatchesPlayedAgainstNormalOpponent());
			String update78 = getQueryToUpdateInTableTeams("NombreVMatchBanal", team,
					team.getNbWinsAgainstNormalOpponent());
			String update79 = getQueryToUpdateInTableTeams("NombreNMatchBanal", team,
					team.getNbDrawsAgainstNormalOpponent());
			String update80 = getQueryToUpdateInTableTeams("NombreDMatchBanal", team,
					team.getNbLossesAgainstNormalOpponent());
			String update81 = getQueryToUpdateInTableTeams("NombreMatchsJouesBanalDomicile", team,
					team.getNbMatchesPlayedAgainstNormalOpponentAtHome());
			String update82 = getQueryToUpdateInTableTeams("NombreVMatchBanalDomicile", team,
					team.getNbWinsAgainstNormalOpponentAtHome());
			String update83 = getQueryToUpdateInTableTeams("NombreNMatchBanalDomicile", team,
					team.getNbDrawsAgainstNormalOpponentAtHome());
			String update84 = getQueryToUpdateInTableTeams("NombreDMatchBanalDomicile", team,
					team.getNbLossesAgainstNormalOpponentAtHome());
			String update85 = getQueryToUpdateInTableTeams("NombreMatchsJouesBanalExterieur", team,
					team.getNbMatchesPlayedAgainstNormalOpponentAway());
			String update86 = getQueryToUpdateInTableTeams("NombreVMatchBanalExterieur", team,
					team.getNbWinsAgainstNormalOpponentAway());
			String update87 = getQueryToUpdateInTableTeams("NombreNMatchBanalExterieur", team,
					team.getNbDrawsAgainstNormalOpponentAway());
			String update88 = getQueryToUpdateInTableTeams("NombreDMatchBanalExterieur", team,
					team.getNbLossesAgainstNormalOpponentAway());
			String update89 = getQueryToUpdateInTableTeams("NombrePoints", team, team.getNbPoints());
			String update90 = getQueryToUpdateInTableTeams("NombrePointsDomicile", team, team.getNbHomePoints());
			String update91 = getQueryToUpdateInTableTeams("NombrePointsExterieur", team, team.getNbAwayPoints());
			String update92 = getQueryToUpdateInTableTeams("GoalAverage", team, team.getGoalAverage());
			String update93 = getQueryToUpdateInTableTeams("HomeGoalAverage", team, team.getHomeGoalAverage());
			String update94 = getQueryToUpdateInTableTeams("AwayGoalAverage", team, team.getAwayGoalAverage());
			String create = getQueryToInsertIntoTableTeams(team);
			ResultSet rs = st.executeQuery(select);
			if (rs.next()) {
				st.executeUpdate(update1);
				st.executeUpdate(update2);
				st.executeUpdate(update3);
				st.executeUpdate(update4);
				st.executeUpdate(update5);
				st.executeUpdate(update6);
				st.executeUpdate(update7);
				st.executeUpdate(update8);
				st.executeUpdate(update9);
				st.executeUpdate(update10);
				st.executeUpdate(update11);
				st.executeUpdate(update12);
				st.executeUpdate(update13);
				st.executeUpdate(update14);
				st.executeUpdate(update15);
				st.executeUpdate(update16);
				st.executeUpdate(update17);
				st.executeUpdate(update18);
				st.executeUpdate(update19);
				st.executeUpdate(update20);
				st.executeUpdate(update21);
				st.executeUpdate(update22);
				st.executeUpdate(update23);
				st.executeUpdate(update24);
				st.executeUpdate(update25);
				st.executeUpdate(update26);
				st.executeUpdate(update27);
				st.executeUpdate(update28);
				st.executeUpdate(update29);
				st.executeUpdate(update30);
				st.executeUpdate(update31);
				st.executeUpdate(update32);
				st.executeUpdate(update33);
				st.executeUpdate(update34);
				st.executeUpdate(update35);
				st.executeUpdate(update36);
				st.executeUpdate(update37);
				st.executeUpdate(update38);
				st.executeUpdate(update39);
				st.executeUpdate(update40);
				st.executeUpdate(update41);
				st.executeUpdate(update42);
				st.executeUpdate(update43);
				st.executeUpdate(update44);
				st.executeUpdate(update45);
				st.executeUpdate(update46);
				st.executeUpdate(update47);
				st.executeUpdate(update48);
				st.executeUpdate(update49);
				st.executeUpdate(update50);
				st.executeUpdate(update51);
				st.executeUpdate(update52);
				st.executeUpdate(update53);
				st.executeUpdate(update54);
				st.executeUpdate(update55);
				st.executeUpdate(update56);
				st.executeUpdate(update57);
				st.executeUpdate(update58);
				st.executeUpdate(update59);
				st.executeUpdate(update60);
				st.executeUpdate(update61);
				st.executeUpdate(update62);
				st.executeUpdate(update63);
				st.executeUpdate(update64);
				st.executeUpdate(update65);
				st.executeUpdate(update66);
				st.executeUpdate(update67);
				st.executeUpdate(update68);
				st.executeUpdate(update69);
				st.executeUpdate(update70);
				st.executeUpdate(update71);
				st.executeUpdate(update72);
				st.executeUpdate(update73);
				st.executeUpdate(update74);
				st.executeUpdate(update75);
				st.executeUpdate(update76);
				st.executeUpdate(update77);
				st.executeUpdate(update78);
				st.executeUpdate(update79);
				st.executeUpdate(update80);
				st.executeUpdate(update81);
				st.executeUpdate(update82);
				st.executeUpdate(update83);
				st.executeUpdate(update84);
				st.executeUpdate(update85);
				st.executeUpdate(update86);
				st.executeUpdate(update87);
				st.executeUpdate(update88);
				st.executeUpdate(update89);
				st.executeUpdate(update90);
				st.executeUpdate(update91);
				st.executeUpdate(update92);
				st.executeUpdate(update93);
				st.executeUpdate(update94);
			} else {
				st.executeUpdate(create);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la récupération de toutes les équipes de Ligue 1 : " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	public static Confrontation getConfrontation(String match) throws NullConfrontationException {
		if (Ligue1Utils.isEmpty(match)) {
			throw new NullConfrontationException("Erreur à la récupération de la confrontation " + match
					+ " dans la table Confrontations : le nom n'existe pas dans la table.");
		}
		Connection cn = initializeOrGetConnection();
		String query = "";
		Confrontation confrontation = null;
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM confrontations WHERE confrontations.match = \"" + match + "\"";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String recent1 = rs.getString("Recent1");
				String recent2 = rs.getString("Recent2");
				String recent3 = rs.getString("Recent3");
				String recent4 = rs.getString("Recent4");
				String recent5 = rs.getString("Recent5");

				confrontation = new Confrontation(match, recent1, recent2, recent3, recent4, recent5);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la récupération de la confrontation " + match
					+ " dans la table avec la requête : " + query);
			e.printStackTrace();
			return null;
		}
		if (confrontation == null) {
			throw new NullConfrontationException(
					"La confrontation " + confrontation + " n'existe pas. Merci de réitérer la saisie.");
		}
		return confrontation;
	}

	public static String getRivals(String team) throws NullRivalException {
		if (Ligue1Utils.isEmpty(team)) {
			throw new NullRivalException("Erreur à la récupération de la liste des rivaux de l'équipe " + team
					+ " dans la table rivals : le nom n'existe pas dans la table.");
		}
		Connection cn = initializeOrGetConnection();
		String query = "";
		String rivals = "";
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM rivals WHERE rivals.team = \"" + team.trim() + "\"";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				rivals = rs.getString(2);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la récupération de la liste des rivaux de l'équipe " + team
					+ " dans la table avec la requête : " + query);
			e.printStackTrace();
			return null;
		}
		if (Ligue1Utils.isEmpty(rivals)) {
			throw new NullRivalException("L'équipe " + team + " ne comporte aucun rival. C'est impossible.");
		}
		return rivals;
	}

	public static Collection<Confrontation> getAllConfrontations() {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<Confrontation> allConfrontations = new ArrayList<Confrontation>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM confrontations";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String match = rs.getString("match");
				Confrontation confrontation = getConfrontation(match);
				allConfrontations.add(confrontation);
			}
		} catch (SQLException | NullConfrontationException e) {
			Ligue1Utils.reportError(
					"Erreur à la récupération de toutes les confrontations de Ligue 1 : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return allConfrontations;
	}

	public static void createOrUpdateConfrontation(Confrontation confrontation) {
		Connection cn = initializeOrGetConnection();
		try {
			Statement st = cn.createStatement();
			String select = "SELECT * FROM confrontations WHERE confrontations.match = \"" + confrontation.getMatch()
					+ "\"";
			String update1 = getQueryToUpdateInTableConfrontations("Recent1", confrontation,
					confrontation.getRecent1());
			String update2 = getQueryToUpdateInTableConfrontations("Recent2", confrontation,
					confrontation.getRecent2());
			String update3 = getQueryToUpdateInTableConfrontations("Recent3", confrontation,
					confrontation.getRecent3());
			String update4 = getQueryToUpdateInTableConfrontations("Recent4", confrontation,
					confrontation.getRecent4());
			String update5 = getQueryToUpdateInTableConfrontations("Recent5", confrontation,
					confrontation.getRecent5());
			String create = getQueryToInsertIntoTableConfrontations(confrontation);
			ResultSet rs = st.executeQuery(select);
			if (rs.next()) {
				st.executeUpdate(update1);
				st.executeUpdate(update2);
				st.executeUpdate(update3);
				st.executeUpdate(update4);
				st.executeUpdate(update5);
			} else {
				st.executeUpdate(create);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError(
					"Erreur à la récupération de toutes les confrontations de Ligue 1 : " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	public static void createOrUpdateUser(User user) {
		Connection cn = initializeOrGetConnection();
		try {
			Statement st = cn.createStatement();
			String select = "SELECT * FROM users WHERE users.login = \"" + user.getLogin() + "\"";
			String update1 = getQueryToUpdateInTableUsers("password", user, user.getPassword());
			String update3 = getQueryToUpdateInTableUsers("reportPath", user,
					(Ligue1Utils.isEmpty(user.getReportPath()) ? ""
							: StringUtils.replace(user.getReportPath(), "\\", "\\\\")));
			String update2 = getQueryToUpdateInTableUsers("journeesSubscribed", user,
					(Ligue1Utils.isEmpty(user.getJourneesSubscribed()) ? "" : user.getJourneesSubscribed()));
			String update4 = getQueryToUpdateInTableUsers("passwordModified", user, user.getPasswordModified());
			String update5 = getQueryToUpdateInTableUsers("myTeams", user,
					(Ligue1Utils.isEmpty(user.getMyTeams()) ? "" : user.getMyTeams()));
			String update6 = getQueryToUpdateInTableUsers("nbReportsLeft", user, user.getNbReportsLeft());
			String update7 = getQueryToUpdateInTableUsers("subscribtionType", user,
					(Ligue1Utils.isEmpty(user.getSubscribtionType()) ? "" : user.getSubscribtionType()));
			String update8 = getQueryToUpdateInTableUsers("email", user,
					(Ligue1Utils.isEmpty(user.getEmail()) ? "" : user.getEmail()));
			String update9 = getQueryToUpdateInTableUsers("nbReportsPerTeam", user, user.getNbReportsPerTeam());
			String update10 = getQueryToUpdateInTableUsers("reportsAlreadyGenerated", user,
					(Ligue1Utils.isEmpty(user.getReportsAlreadyGenerated()) ? "" : user.getReportsAlreadyGenerated()));
			String create = getQueryToInsertIntoTableUsers(user);
			ResultSet rs = st.executeQuery(select);
			if (rs.next()) {
				st.executeUpdate(update1);
				st.executeUpdate(update2);
				st.executeUpdate(update3);
				st.executeUpdate(update4);
				st.executeUpdate(update5);
				st.executeUpdate(update6);
				st.executeUpdate(update7);
				st.executeUpdate(update8);
				st.executeUpdate(update9);
				st.executeUpdate(update10);
			} else {
				st.executeUpdate(create);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la récupération de l'utilisateur : " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	private static String getQueryToInsertIntoTableUsers(User user) {
		return "INSERT INTO users VALUES ('" + user.getLogin() + "','" + user.getPassword() + "','"
				+ user.getReportPath() + "','" + user.getJourneesSubscribed() + "','" + user.getPasswordModified()
				+ "','" + user.getMyTeams() + "','" + user.getNbReportsLeft() + "','" + user.getSubscribtionType()
				+ "','" + user.getEmail() + "','" + user.getNbReportsPerTeam() + "','"
				+ user.getReportsAlreadyGenerated() + "')";
	}

	private static String getQueryToUpdateInTableUsers(String field, User user, Object attribute) {
		return "UPDATE users SET " + field + " = '" + attribute + "'  WHERE users.login = \"" + user.getLogin() + "\"";
	}

	public static Statistic getStatistic(String match) throws NullStatisticException {
		if (Ligue1Utils.isEmpty(match)) {
			throw new NullStatisticException("Erreur à la récupération des stats du match " + match
					+ " dans la table Statistics : le nom n'existe pas dans la table.");
		}
		Connection cn = initializeOrGetConnection();
		String query = "";
		Statistic statistic = null;
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM statistics WHERE statistics.match = \"" + match + "\"";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				float homeTeamWinPercentage = rs.getFloat("VEquipeDom");
				float awayTeamWinPercentage = rs.getFloat("VEquipeExt");
				float drawPercentage = rs.getFloat("Nul");
				float homeTeamGoalAverage = rs.getFloat("MoyButsEquipeDom");
				float awayTeamGoalAverage = rs.getFloat("MoyButsEquipeExt");
				float goalAverage = rs.getFloat("MoyButsMatch");
				float moreThan05GoalPercentage = rs.getFloat("Plus05");
				float moreThan15GoalPercentage = rs.getFloat("Plus15");
				float moreThan25GoalsPercentage = rs.getFloat("Plus25");
				float moreThan35GoalsPercentage = rs.getFloat("Plus35");
				float moreThan45GoalsPercentage = rs.getFloat("Plus45");
				float lessThan05GoalPercentage = rs.getFloat("Moins05");
				float lessThan15GoalPercentage = rs.getFloat("Moins15");
				float lessThan25GoalsPercentage = rs.getFloat("Moins25");
				float lessThan35GoalsPercentage = rs.getFloat("Moins35");
				float lessThan45GoalsPercentage = rs.getFloat("Moins45");
				float bothTeamsToScorePercentage = rs.getFloat("LDEM");
				float homeTeamScoredMoreThan05GoalPercentage = rs.getFloat("E1Plus05");
				float homeTeamScoredMoreThan15GoalPercentage = rs.getFloat("E1Plus15");
				float homeTeamScoredMoreThan25GoalsPercentage = rs.getFloat("E1Plus25");
				float homeTeamScoredMoreThan35GoalsPercentage = rs.getFloat("E1Plus35");
				float homeTeamScoredMoreThan45GoalsPercentage = rs.getFloat("E1Plus45");
				float homeTeamScoredLessThan05GoalPercentage = rs.getFloat("E1Moins05");
				float homeTeamScoredLessThan15GoalPercentage = rs.getFloat("E1Moins15");
				float homeTeamScoredLessThan25GoalsPercentage = rs.getFloat("E1Moins25");
				float homeTeamScoredLessThan35GoalsPercentage = rs.getFloat("E1Moins35");
				float homeTeamScoredLessThan45GoalsPercentage = rs.getFloat("E1Moins45");
				float awayTeamScoredMoreThan05GoalPercentage = rs.getFloat("E2Plus05");
				float awayTeamScoredMoreThan15GoalPercentage = rs.getFloat("E2Plus15");
				float awayTeamScoredMoreThan25GoalsPercentage = rs.getFloat("E2Plus25");
				float awayTeamScoredMoreThan35GoalsPercentage = rs.getFloat("E2Plus35");
				float awayTeamScoredMoreThan45GoalsPercentage = rs.getFloat("E2Plus45");
				float awayTeamScoredLessThan05GoalPercentage = rs.getFloat("E2Moins05");
				float awayTeamScoredLessThan15GoalPercentage = rs.getFloat("E2Moins15");
				float awayTeamScoredLessThan25GoalsPercentage = rs.getFloat("E2Moins25");
				float awayTeamScoredLessThan35GoalsPercentage = rs.getFloat("E2Moins35");
				float awayTeamScoredLessThan45GoalsPercentage = rs.getFloat("E2Moins45");
				float awayTeamWinByExactly1GoalPercentage = rs.getFloat("EcartButMoins1");
				float awayTeamWinByExactly2GoalsPercentage = rs.getFloat("EcartButMoins2");
				float awayTeamWinByExactly3GoalsPercentage = rs.getFloat("EcartButMoins3");
				float awayTeamWinByExactly4GoalsPercentage = rs.getFloat("EcartButMoins4");
				float awayTeamWinByExactly5GoalsPercentage = rs.getFloat("EcartButMoins5");
				float awayTeamWinByExactly6GoalsPercentage = rs.getFloat("EcartButMoins6");
				float awayTeamWinByExactly7GoalsPercentage = rs.getFloat("EcartButMoins7");
				float awayTeamWinByExactly8GoalsPercentage = rs.getFloat("EcartButMoins8");
				float awayTeamWinByExactly9GoalsPercentage = rs.getFloat("EcartButMoins9");
				float homeTeamWinByExactly1GoalPercentage = rs.getFloat("EcartButPlus1");
				float homeTeamWinByExactly2GoalsPercentage = rs.getFloat("EcartButPlus2");
				float homeTeamWinByExactly3GoalsPercentage = rs.getFloat("EcartButPlus3");
				float homeTeamWinByExactly4GoalsPercentage = rs.getFloat("EcartButPlus4");
				float homeTeamWinByExactly5GoalsPercentage = rs.getFloat("EcartButPlus5");
				float homeTeamWinByExactly6GoalsPercentage = rs.getFloat("EcartButPlus6");
				float homeTeamWinByExactly7GoalsPercentage = rs.getFloat("EcartButPlus7");
				float homeTeamWinByExactly8GoalsPercentage = rs.getFloat("EcartButPlus8");
				float homeTeamWinByExactly9GoalsPercentage = rs.getFloat("EcartButPlus9");
				float homeTeamScoredExactly0GoalPercentage = rs.getFloat("E1Exactement0ButMarque");
				float homeTeamScoredExactly1GoalPercentage = rs.getFloat("E1Exactement1ButMarque");
				float homeTeamScoredExactly2GoalsPercentage = rs.getFloat("E1Exactement2ButsMarques");
				float homeTeamScoredExactly3GoalsPercentage = rs.getFloat("E1Exactement3ButsMarques");
				float homeTeamScoredExactly4GoalsPercentage = rs.getFloat("E1Exactement4ButsMarques");
				float homeTeamScoredExactly5GoalsPercentage = rs.getFloat("E1Exactement5ButsMarques");
				float homeTeamConcededExactly0GoalPercentage = rs.getFloat("E1Exactement0ButEncaisse");
				float homeTeamConcededExactly1GoalPercentage = rs.getFloat("E1Exactement1ButEncaisse");
				float homeTeamConcededExactly2GoalsPercentage = rs.getFloat("E1Exactement2ButsEncaisses");
				float homeTeamConcededExactly3GoalsPercentage = rs.getFloat("E1Exactement3ButsEncaisses");
				float homeTeamConcededExactly4GoalsPercentage = rs.getFloat("E1Exactement4ButsEncaisses");
				float homeTeamConcededExactly5GoalsPercentage = rs.getFloat("E1Exactement5ButsEncaisses");
				float awayTeamScoredExactly0GoalPercentage = rs.getFloat("E2Exactement0ButMarque");
				float awayTeamScoredExactly1GoalPercentage = rs.getFloat("E2Exactement1ButMarque");
				float awayTeamScoredExactly2GoalsPercentage = rs.getFloat("E2Exactement2ButsMarques");
				float awayTeamScoredExactly3GoalsPercentage = rs.getFloat("E2Exactement3ButsMarques");
				float awayTeamScoredExactly4GoalsPercentage = rs.getFloat("E2Exactement4ButsMarques");
				float awayTeamScoredExactly5GoalsPercentage = rs.getFloat("E2Exactement5ButsMarques");
				float awayTeamConcededExactly0GoalPercentage = rs.getFloat("E2Exactement0ButEncaisse");
				float awayTeamConcededExactly1GoalPercentage = rs.getFloat("E2Exactement1ButEncaisse");
				float awayTeamConcededExactly2GoalsPercentage = rs.getFloat("E2Exactement2ButsEncaisses");
				float awayTeamConcededExactly3GoalsPercentage = rs.getFloat("E2Exactement3ButsEncaisses");
				float awayTeamConcededExactly4GoalsPercentage = rs.getFloat("E2Exactement4ButsEncaisses");
				float awayTeamConcededExactly5GoalsPercentage = rs.getFloat("E2Exactement5ButsEncaisses");

				statistic = new Statistic(match, homeTeamWinPercentage, awayTeamWinPercentage, drawPercentage,
						homeTeamGoalAverage, awayTeamGoalAverage, goalAverage, moreThan05GoalPercentage,
						moreThan15GoalPercentage, moreThan25GoalsPercentage, moreThan35GoalsPercentage,
						moreThan45GoalsPercentage, lessThan05GoalPercentage, lessThan15GoalPercentage,
						lessThan25GoalsPercentage, lessThan35GoalsPercentage, lessThan45GoalsPercentage,
						bothTeamsToScorePercentage, homeTeamScoredMoreThan05GoalPercentage,
						homeTeamScoredMoreThan15GoalPercentage, homeTeamScoredMoreThan25GoalsPercentage,
						homeTeamScoredMoreThan35GoalsPercentage, homeTeamScoredMoreThan45GoalsPercentage,
						homeTeamScoredLessThan05GoalPercentage, homeTeamScoredLessThan15GoalPercentage,
						homeTeamScoredLessThan25GoalsPercentage, homeTeamScoredLessThan35GoalsPercentage,
						homeTeamScoredLessThan45GoalsPercentage, awayTeamScoredMoreThan05GoalPercentage,
						awayTeamScoredMoreThan15GoalPercentage, awayTeamScoredMoreThan25GoalsPercentage,
						awayTeamScoredMoreThan35GoalsPercentage, awayTeamScoredMoreThan45GoalsPercentage,
						awayTeamScoredLessThan05GoalPercentage, awayTeamScoredLessThan15GoalPercentage,
						awayTeamScoredLessThan25GoalsPercentage, awayTeamScoredLessThan35GoalsPercentage,
						awayTeamScoredLessThan45GoalsPercentage, awayTeamWinByExactly1GoalPercentage,
						awayTeamWinByExactly2GoalsPercentage, awayTeamWinByExactly3GoalsPercentage,
						awayTeamWinByExactly4GoalsPercentage, awayTeamWinByExactly5GoalsPercentage,
						awayTeamWinByExactly6GoalsPercentage, awayTeamWinByExactly7GoalsPercentage,
						awayTeamWinByExactly8GoalsPercentage, awayTeamWinByExactly9GoalsPercentage,
						homeTeamWinByExactly1GoalPercentage, homeTeamWinByExactly2GoalsPercentage,
						homeTeamWinByExactly3GoalsPercentage, homeTeamWinByExactly4GoalsPercentage,
						homeTeamWinByExactly5GoalsPercentage, homeTeamWinByExactly6GoalsPercentage,
						homeTeamWinByExactly7GoalsPercentage, homeTeamWinByExactly8GoalsPercentage,
						homeTeamWinByExactly9GoalsPercentage, homeTeamScoredExactly0GoalPercentage,
						homeTeamScoredExactly1GoalPercentage, homeTeamScoredExactly2GoalsPercentage,
						homeTeamScoredExactly3GoalsPercentage, homeTeamScoredExactly4GoalsPercentage,
						homeTeamScoredExactly5GoalsPercentage, homeTeamConcededExactly0GoalPercentage,
						homeTeamConcededExactly1GoalPercentage, homeTeamConcededExactly2GoalsPercentage,
						homeTeamConcededExactly3GoalsPercentage, homeTeamConcededExactly4GoalsPercentage,
						homeTeamConcededExactly5GoalsPercentage, awayTeamScoredExactly0GoalPercentage,
						awayTeamScoredExactly1GoalPercentage, awayTeamScoredExactly2GoalsPercentage,
						awayTeamScoredExactly3GoalsPercentage, awayTeamScoredExactly4GoalsPercentage,
						awayTeamScoredExactly5GoalsPercentage, awayTeamConcededExactly0GoalPercentage,
						awayTeamConcededExactly1GoalPercentage, awayTeamConcededExactly2GoalsPercentage,
						awayTeamConcededExactly3GoalsPercentage, awayTeamConcededExactly4GoalsPercentage,
						awayTeamConcededExactly5GoalsPercentage);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la récupération des statistiques du match " + match
					+ " dans la table avec la requête : " + query);
			e.printStackTrace();
			return null;
		}
		if (statistic == null) {
			throw new NullStatisticException(
					"La statistique " + statistic + " n'existe pas. Merci de réitérer la saisie.");
		}
		return statistic;
	}

	public static Collection<Statistic> getAllStatistics() {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<Statistic> allStatistics = new ArrayList<Statistic>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM statistics";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String match = rs.getString("match");
				Statistic statistic = getStatistic(match);
				allStatistics.add(statistic);
			}
		} catch (SQLException | NullStatisticException e) {
			Ligue1Utils
					.reportError("Erreur à la récupération de toutes les statistiques de Ligue 1 : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return allStatistics;
	}

	public static void createOrUpdateStatistic(Statistic statistic) {
		Connection cn = initializeOrGetConnection();
		try {
			Statement st = cn.createStatement();
			String select = "SELECT * FROM statistics WHERE statistics.match = \"" + statistic.getMatch() + "\"";
			String update1 = getQueryToUpdateInTableStatistics("VEquipeDom", statistic,
					statistic.getHomeTeamWinPercentage());
			String update2 = getQueryToUpdateInTableStatistics("VEquipeExt", statistic,
					statistic.getAwayTeamWinPercentage());
			String update3 = getQueryToUpdateInTableStatistics("Nul", statistic, statistic.getDrawPercentage());
			String update4 = getQueryToUpdateInTableStatistics("MoyButsMatch", statistic, statistic.getGoalAverage());
			String update5 = getQueryToUpdateInTableStatistics("MoyButsEquipeDom", statistic,
					statistic.getHomeTeamGoalAverage());
			String update6 = getQueryToUpdateInTableStatistics("MoyButsEquipeExt", statistic,
					statistic.getAwayTeamGoalAverage());
			String update7 = getQueryToUpdateInTableStatistics("Plus05", statistic,
					statistic.getMoreThan05GoalPercentage());
			String update8 = getQueryToUpdateInTableStatistics("Plus15", statistic,
					statistic.getMoreThan15GoalPercentage());
			String update9 = getQueryToUpdateInTableStatistics("Plus25", statistic,
					statistic.getMoreThan25GoalsPercentage());
			String update10 = getQueryToUpdateInTableStatistics("Plus35", statistic,
					statistic.getMoreThan35GoalsPercentage());
			String update11 = getQueryToUpdateInTableStatistics("Plus45", statistic,
					statistic.getMoreThan45GoalsPercentage());
			String update12 = getQueryToUpdateInTableStatistics("Moins05", statistic,
					statistic.getLessThan05GoalPercentage());
			String update13 = getQueryToUpdateInTableStatistics("Moins15", statistic,
					statistic.getLessThan15GoalPercentage());
			String update14 = getQueryToUpdateInTableStatistics("Moins25", statistic,
					statistic.getLessThan25GoalsPercentage());
			String update15 = getQueryToUpdateInTableStatistics("Moins35", statistic,
					statistic.getLessThan35GoalsPercentage());
			String update16 = getQueryToUpdateInTableStatistics("Moins45", statistic,
					statistic.getLessThan45GoalsPercentage());
			String update17 = getQueryToUpdateInTableStatistics("LDEM", statistic,
					statistic.getBothTeamsToScorePercentage());
			String update18 = getQueryToUpdateInTableStatistics("E1Plus05", statistic,
					statistic.getHomeTeamScoredMoreThan05GoalPercentage());
			String update19 = getQueryToUpdateInTableStatistics("E1Plus15", statistic,
					statistic.getHomeTeamScoredMoreThan15GoalPercentage());
			String update20 = getQueryToUpdateInTableStatistics("E1Plus25", statistic,
					statistic.getHomeTeamScoredMoreThan25GoalsPercentage());
			String update21 = getQueryToUpdateInTableStatistics("E1Plus35", statistic,
					statistic.getHomeTeamScoredMoreThan35GoalsPercentage());
			String update22 = getQueryToUpdateInTableStatistics("E1Plus45", statistic,
					statistic.getHomeTeamScoredMoreThan45GoalsPercentage());
			String update23 = getQueryToUpdateInTableStatistics("E1Moins05", statistic,
					statistic.getHomeTeamScoredLessThan05GoalPercentage());
			String update24 = getQueryToUpdateInTableStatistics("E1Moins15", statistic,
					statistic.getHomeTeamScoredLessThan15GoalPercentage());
			String update25 = getQueryToUpdateInTableStatistics("E1Moins25", statistic,
					statistic.getHomeTeamScoredLessThan25GoalsPercentage());
			String update26 = getQueryToUpdateInTableStatistics("E1Moins35", statistic,
					statistic.getHomeTeamScoredLessThan35GoalsPercentage());
			String update27 = getQueryToUpdateInTableStatistics("E1Moins45", statistic,
					statistic.getHomeTeamScoredLessThan45GoalsPercentage());
			String update28 = getQueryToUpdateInTableStatistics("E2Plus05", statistic,
					statistic.getAwayTeamScoredMoreThan05GoalPercentage());
			String update29 = getQueryToUpdateInTableStatistics("E2Plus15", statistic,
					statistic.getAwayTeamScoredMoreThan15GoalPercentage());
			String update30 = getQueryToUpdateInTableStatistics("E2Plus25", statistic,
					statistic.getAwayTeamScoredMoreThan25GoalsPercentage());
			String update31 = getQueryToUpdateInTableStatistics("E2Plus35", statistic,
					statistic.getAwayTeamScoredMoreThan35GoalsPercentage());
			String update32 = getQueryToUpdateInTableStatistics("E2Plus45", statistic,
					statistic.getAwayTeamScoredMoreThan45GoalsPercentage());
			String update33 = getQueryToUpdateInTableStatistics("E2Moins05", statistic,
					statistic.getAwayTeamScoredLessThan05GoalPercentage());
			String update34 = getQueryToUpdateInTableStatistics("E2Moins15", statistic,
					statistic.getAwayTeamScoredLessThan15GoalPercentage());
			String update35 = getQueryToUpdateInTableStatistics("E2Moins25", statistic,
					statistic.getAwayTeamScoredLessThan25GoalsPercentage());
			String update36 = getQueryToUpdateInTableStatistics("E2Moins35", statistic,
					statistic.getAwayTeamScoredLessThan35GoalsPercentage());
			String update37 = getQueryToUpdateInTableStatistics("E2Moins45", statistic,
					statistic.getAwayTeamScoredLessThan45GoalsPercentage());
			String update38 = getQueryToUpdateInTableStatistics("EcartButMoins1", statistic,
					statistic.getAwayTeamWinByExactly1GoalPercentage());
			String update39 = getQueryToUpdateInTableStatistics("EcartButMoins2", statistic,
					statistic.getAwayTeamWinByExactly2GoalsPercentage());
			String update40 = getQueryToUpdateInTableStatistics("EcartButMoins3", statistic,
					statistic.getAwayTeamWinByExactly3GoalsPercentage());
			String update41 = getQueryToUpdateInTableStatistics("EcartButMoins4", statistic,
					statistic.getAwayTeamWinByExactly4GoalsPercentage());
			String update42 = getQueryToUpdateInTableStatistics("EcartButMoins5", statistic,
					statistic.getAwayTeamWinByExactly5GoalsPercentage());
			String update43 = getQueryToUpdateInTableStatistics("EcartButMoins6", statistic,
					statistic.getAwayTeamWinByExactly6GoalsPercentage());
			String update44 = getQueryToUpdateInTableStatistics("EcartButMoins7", statistic,
					statistic.getAwayTeamWinByExactly7GoalsPercentage());
			String update45 = getQueryToUpdateInTableStatistics("EcartButMoins8", statistic,
					statistic.getAwayTeamWinByExactly8GoalsPercentage());
			String update46 = getQueryToUpdateInTableStatistics("EcartButMoins9", statistic,
					statistic.getAwayTeamWinByExactly9GoalsPercentage());
			String update47 = getQueryToUpdateInTableStatistics("EcartButPlus1", statistic,
					statistic.getHomeTeamWinByExactly1GoalPercentage());
			String update48 = getQueryToUpdateInTableStatistics("EcartButPlus2", statistic,
					statistic.getHomeTeamWinByExactly2GoalsPercentage());
			String update49 = getQueryToUpdateInTableStatistics("EcartButPlus3", statistic,
					statistic.getHomeTeamWinByExactly3GoalsPercentage());
			String update50 = getQueryToUpdateInTableStatistics("EcartButPlus4", statistic,
					statistic.getHomeTeamWinByExactly4GoalsPercentage());
			String update51 = getQueryToUpdateInTableStatistics("EcartButPlus5", statistic,
					statistic.getHomeTeamWinByExactly5GoalsPercentage());
			String update52 = getQueryToUpdateInTableStatistics("EcartButPlus6", statistic,
					statistic.getHomeTeamWinByExactly6GoalsPercentage());
			String update53 = getQueryToUpdateInTableStatistics("EcartButPlus7", statistic,
					statistic.getHomeTeamWinByExactly7GoalsPercentage());
			String update54 = getQueryToUpdateInTableStatistics("EcartButPlus8", statistic,
					statistic.getHomeTeamWinByExactly8GoalsPercentage());
			String update55 = getQueryToUpdateInTableStatistics("EcartButPlus9", statistic,
					statistic.getHomeTeamWinByExactly9GoalsPercentage());
			String update56 = getQueryToUpdateInTableStatistics("E1Exactement0ButMarque", statistic,
					statistic.getHomeTeamScoredExactly0GoalPercentage());
			String update57 = getQueryToUpdateInTableStatistics("E1Exactement1ButMarque", statistic,
					statistic.getHomeTeamScoredExactly1GoalPercentage());
			String update58 = getQueryToUpdateInTableStatistics("E1Exactement2ButsMarques", statistic,
					statistic.getHomeTeamScoredExactly2GoalsPercentage());
			String update59 = getQueryToUpdateInTableStatistics("E1Exactement3ButsMarques", statistic,
					statistic.getHomeTeamScoredExactly3GoalsPercentage());
			String update60 = getQueryToUpdateInTableStatistics("E1Exactement4ButsMarques", statistic,
					statistic.getHomeTeamScoredExactly4GoalsPercentage());
			String update61 = getQueryToUpdateInTableStatistics("E1Exactement5ButsMarques", statistic,
					statistic.getHomeTeamScoredExactly5GoalsPercentage());
			String update62 = getQueryToUpdateInTableStatistics("E2Exactement0ButMarque", statistic,
					statistic.getAwayTeamScoredExactly0GoalPercentage());
			String update63 = getQueryToUpdateInTableStatistics("E2Exactement1ButMarque", statistic,
					statistic.getAwayTeamScoredExactly1GoalPercentage());
			String update64 = getQueryToUpdateInTableStatistics("E2Exactement2ButsMarques", statistic,
					statistic.getAwayTeamScoredExactly2GoalsPercentage());
			String update65 = getQueryToUpdateInTableStatistics("E2Exactement3ButsMarques", statistic,
					statistic.getAwayTeamScoredExactly3GoalsPercentage());
			String update66 = getQueryToUpdateInTableStatistics("E2Exactement4ButsMarques", statistic,
					statistic.getAwayTeamScoredExactly4GoalsPercentage());
			String update67 = getQueryToUpdateInTableStatistics("E2Exactement5ButsMarques", statistic,
					statistic.getAwayTeamScoredExactly5GoalsPercentage());
			String update68 = getQueryToUpdateInTableStatistics("E1Exactement0ButEncaisse", statistic,
					statistic.getHomeTeamConcededExactly0GoalPercentage());
			String update69 = getQueryToUpdateInTableStatistics("E1Exactement1ButEncaisse", statistic,
					statistic.getHomeTeamConcededExactly1GoalPercentage());
			String update70 = getQueryToUpdateInTableStatistics("E1Exactement2ButsEncaisses", statistic,
					statistic.getHomeTeamConcededExactly2GoalsPercentage());
			String update71 = getQueryToUpdateInTableStatistics("E1Exactement3ButsEncaisses", statistic,
					statistic.getHomeTeamConcededExactly3GoalsPercentage());
			String update72 = getQueryToUpdateInTableStatistics("E1Exactement4ButsEncaisses", statistic,
					statistic.getHomeTeamConcededExactly4GoalsPercentage());
			String update73 = getQueryToUpdateInTableStatistics("E1Exactement5ButsEncaisses", statistic,
					statistic.getHomeTeamConcededExactly5GoalsPercentage());
			String update74 = getQueryToUpdateInTableStatistics("E2Exactement0ButEncaisse", statistic,
					statistic.getAwayTeamConcededExactly0GoalPercentage());
			String update75 = getQueryToUpdateInTableStatistics("E2Exactement1ButEncaisse", statistic,
					statistic.getAwayTeamConcededExactly1GoalPercentage());
			String update76 = getQueryToUpdateInTableStatistics("E2Exactement2ButsEncaisses", statistic,
					statistic.getAwayTeamConcededExactly2GoalsPercentage());
			String update77 = getQueryToUpdateInTableStatistics("E2Exactement3ButsEncaisses", statistic,
					statistic.getAwayTeamConcededExactly3GoalsPercentage());
			String update78 = getQueryToUpdateInTableStatistics("E2Exactement4ButsEncaisses", statistic,
					statistic.getAwayTeamConcededExactly4GoalsPercentage());
			String update79 = getQueryToUpdateInTableStatistics("E2Exactement5ButsEncaisses", statistic,
					statistic.getAwayTeamConcededExactly5GoalsPercentage());

			String create = getQueryToInsertIntoTableStatistics(statistic);
			ResultSet rs = st.executeQuery(select);
			if (rs.next()) {
				st.executeUpdate(update1);
				st.executeUpdate(update2);
				st.executeUpdate(update3);
				st.executeUpdate(update4);
				st.executeUpdate(update5);
				st.executeUpdate(update6);
				st.executeUpdate(update7);
				st.executeUpdate(update8);
				st.executeUpdate(update9);
				st.executeUpdate(update10);
				st.executeUpdate(update11);
				st.executeUpdate(update12);
				st.executeUpdate(update13);
				st.executeUpdate(update14);
				st.executeUpdate(update15);
				st.executeUpdate(update16);
				st.executeUpdate(update17);
				st.executeUpdate(update18);
				st.executeUpdate(update19);
				st.executeUpdate(update20);
				st.executeUpdate(update21);
				st.executeUpdate(update22);
				st.executeUpdate(update23);
				st.executeUpdate(update24);
				st.executeUpdate(update25);
				st.executeUpdate(update26);
				st.executeUpdate(update27);
				st.executeUpdate(update28);
				st.executeUpdate(update29);
				st.executeUpdate(update30);
				st.executeUpdate(update31);
				st.executeUpdate(update32);
				st.executeUpdate(update33);
				st.executeUpdate(update34);
				st.executeUpdate(update35);
				st.executeUpdate(update36);
				st.executeUpdate(update37);
				st.executeUpdate(update38);
				st.executeUpdate(update39);
				st.executeUpdate(update40);
				st.executeUpdate(update41);
				st.executeUpdate(update42);
				st.executeUpdate(update43);
				st.executeUpdate(update44);
				st.executeUpdate(update45);
				st.executeUpdate(update46);
				st.executeUpdate(update47);
				st.executeUpdate(update48);
				st.executeUpdate(update49);
				st.executeUpdate(update50);
				st.executeUpdate(update51);
				st.executeUpdate(update52);
				st.executeUpdate(update53);
				st.executeUpdate(update54);
				st.executeUpdate(update55);
				st.executeUpdate(update56);
				st.executeUpdate(update57);
				st.executeUpdate(update58);
				st.executeUpdate(update59);
				st.executeUpdate(update60);
				st.executeUpdate(update61);
				st.executeUpdate(update62);
				st.executeUpdate(update63);
				st.executeUpdate(update64);
				st.executeUpdate(update65);
				st.executeUpdate(update66);
				st.executeUpdate(update67);
				st.executeUpdate(update68);
				st.executeUpdate(update69);
				st.executeUpdate(update70);
				st.executeUpdate(update71);
				st.executeUpdate(update72);
				st.executeUpdate(update73);
				st.executeUpdate(update74);
				st.executeUpdate(update75);
				st.executeUpdate(update76);
				st.executeUpdate(update77);
				st.executeUpdate(update78);
				st.executeUpdate(update79);
			} else {
				st.executeUpdate(create);
			}
		} catch (SQLException e) {
//			Ligue1Utils
//					.reportError("Erreur à la récupération de toutes les statistiques de Ligue 1 : " + e.getMessage());
//			e.printStackTrace();
//			return;
		}
	}

	public static Match getMatch(String matchName) throws NullMatchException {
		if (Ligue1Utils.isEmpty(matchName)) {
			throw new NullMatchException("Erreur à la récupération du match " + matchName
					+ " dans la table Matches : le match n'existe pas dans la table.");
		}
		Connection cn = initializeOrGetConnection();
		String query = "";
		matchName.trim();
		Match match = null;
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM matches WHERE id = \"" + matchName + "\"";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String homeTeamNickname = rs.getString("SurnomE1");
				String awayTeamNickname = rs.getString("SurnomE2");
				String score = rs.getString("Score");
				int homeTeamWin = rs.getInt("VictoireEquipeDomicile");
				int awayTeamWin = rs.getInt("VictoireEquipeExterieur");
				int draw = rs.getInt("nul");
				int isAnImportantGameForHomeTeam = rs.getInt("ImportantEquipeDomicile");
				int isAnImportantGameForAwayTeam = rs.getInt("ImportantEquipeExterieur");
				int homeTeamHasABetterStanding = rs.getInt("EquipeDomicileMieuxClassee");
				int resetAllSeason = rs.getInt("ResetAllSeason");
				int countMatch = rs.getInt("Comptabilise");
				int activeStatisticsReportGeneration = rs.getInt("RapportStatistiques");
				int journey = rs.getInt("Journey");
				String comment = rs.getString("Comment");
				match = new Match(homeTeamNickname, awayTeamNickname, score, homeTeamWin, draw, awayTeamWin,
						isAnImportantGameForHomeTeam, isAnImportantGameForAwayTeam, homeTeamHasABetterStanding,
						resetAllSeason, countMatch, activeStatisticsReportGeneration, journey, comment);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la récupération du match " + matchName);
			e.printStackTrace();
			return null;
		}
		if (match == null) {
			throw new NullMatchException("Le match " + match + " n'existe pas. Merci de réitérer la saisie.");
		}
		return match;
	}

	public static Collection<Match> getAllMatches() {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<Match> allMatches = new ArrayList<Match>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM matches";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				Match match = getMatch(id);
				allMatches.add(match);
			}
		} catch (SQLException | NullMatchException e) {
			Ligue1Utils.reportError("Erreur à la récupération de tous les matchs de Ligue 1 : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return allMatches;
	}

	public static Collection<Match> getAllMatchesForTeam(String teamNickname) {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<Match> allMatches = new ArrayList<Match>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT DISTINCT * FROM matches WHERE matches.SurnomE1 = '" + teamNickname
					+ "' OR matches.SurnomE2 = '" + teamNickname + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				Match match = getMatch(id);
				allMatches.add(match);
			}
		} catch (SQLException | NullMatchException e) {
			Ligue1Utils.reportError("Erreur à la récupération de tous les matchs de Ligue 1 pour l'équipe "
					+ teamNickname + " : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return allMatches;
	}

	public static Collection<Confrontation> getAllConfrontationsForTeam(String team) throws NullConfrontationException {
		List<Confrontation> allConfrontationsForTeam = new ArrayList<Confrontation>();
		Collection<Confrontation> allConfrontations = getAllConfrontations();
		Iterator<Confrontation> it = allConfrontations.iterator();
		while (it.hasNext()) {
			Confrontation confrontation = it.next();
			if (confrontation.getMatch().startsWith(team)) {
				allConfrontationsForTeam.add(confrontation);
			}
		}
		if (allConfrontationsForTeam.isEmpty()) {
			throw new NullConfrontationException("Il n'existe aucune confrontation pour l'équipe " + team);
		}
		return allConfrontationsForTeam;
	}

	public static Collection<Match> getAllMatchesForJourney(String journey) {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<Match> allMatches = new ArrayList<Match>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT DISTINCT * FROM matches WHERE matches.Journey = " + Integer.parseInt(journey);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				Match match = getMatch(id);
				allMatches.add(match);
			}
		} catch (SQLException | NullMatchException e) {
			Ligue1Utils.reportError("Erreur à la récupération de tous les matchs de Ligue 1 pour la journée " + journey
					+ " : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return allMatches;
	}

	public static Collection<Confrontation> getAllConfrontationsForJourney(String journey) {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<Confrontation> allConfrontations = new ArrayList<Confrontation>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM confrontations JOIN matches ON confrontations.match = matches.id WHERE matches.Journey = "
					+ Integer.parseInt(journey);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				Confrontation confrontaation = getConfrontation(id);
				allConfrontations.add(confrontaation);
			}
		} catch (SQLException | NullConfrontationException e) {
			Ligue1Utils.reportError("Erreur à la récupération de toutes les confrontations de Ligue 1 pour la journée "
					+ journey + " : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return allConfrontations;
	}

	public static User getUserByLoginAndPassword(String login, String password) throws NullUserException {
		Connection cn = initializeOrGetConnection();
		String query = "";
		User user = null;
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM users WHERE login = \"" + login + "\" AND password = \"" + password + "\"";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String reportPath = rs.getString("reportPath");
				String journeesSubscribed = rs.getString("journeesSubscribed");
				int passwordModified = rs.getInt("passwordModified");
				String myTeams = rs.getString("myTeams");
				int nbReportsLeft = rs.getInt("nbReportsLeft");
				String subscribtionType = rs.getString("subscribtionType");
				String email = rs.getString("email");
				int nbReportsPerTeam = rs.getInt("nbReportsPerTeam");
				String reportsAlreadyGenerated = rs.getString("reportsAlreadyGenerated");
				user = new User(login, password, reportPath, journeesSubscribed, passwordModified, myTeams,
						nbReportsLeft, subscribtionType, email, nbReportsPerTeam, reportsAlreadyGenerated);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError(
					"Mot de passe incorect. Veuillez réitérer votre saisie ou contacter l'administrateur à support@statistant.fr");
			e.printStackTrace();
			return null;
		}
		if (user == null) {
			throw new NullUserException(
					"Mot de passe incorect. Veuillez réitérer votre saisie ou contacter l'administrateur à support@statistant.fr");
		}
		return user;
	}

	public static User getUserByLogin(String login) throws NullUserException {
		Connection cn = initializeOrGetConnection();
		String query = "";
		User user = null;
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM users WHERE login = \"" + login + "\"";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String password = rs.getString("password");
				String reportPath = rs.getString("reportPath");
				String journeesSubscribed = rs.getString("journeesSubscribed");
				int passwordModified = rs.getInt("passwordModified");
				String myTeams = rs.getString("myTeams");
				int nbReportsLeft = rs.getInt("nbReportsLeft");
				String subscribtionType = rs.getString("subscribtionType");
				String email = rs.getString("email");
				int nbReportsPerTeam = rs.getInt("nbReportsPerTeam");
				String reportsAlreadyGenerated = rs.getString("reportsAlreadyGenerated");
				user = new User(login, password, reportPath, journeesSubscribed, passwordModified, myTeams,
						nbReportsLeft, subscribtionType, email, nbReportsPerTeam, reportsAlreadyGenerated);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("L'utilisateur " + login
					+ " n'existe pas. Merci de contacter l'administrateur à \"support@statistant.fr\".");
			e.printStackTrace();
			return null;
		}
		if (user == null) {
			throw new NullUserException("L'utilisateur " + login
					+ " n'existe pas. Merci de réitérer la saisie ou de contacter l'administrateur à \"support@statistant.fr\".");
		}
		return user;
	}

	public static Collection<User> getAllUsers() {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<User> users = new ArrayList<User>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM users";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String login = rs.getString("login");
				String password = rs.getString("password");
				String reportPath = rs.getString("reportPath");
				String journeesSubscribed = rs.getString("journeesSubscribed");
				int passwordModified = rs.getInt("passwordModified");
				String myTeams = rs.getString("myTeams");
				int nbReportsLeft = rs.getInt("nbReportsLeft");
				String subscribtionType = rs.getString("subscribtionType");
				String email = rs.getString("email");
				int nbReportsPerTeam = rs.getInt("nbReportsPerTeam");
				String reportsAlreadyGenerated = rs.getString("reportsAlreadyGenerated");
				users.add(new User(login, password, reportPath, journeesSubscribed, passwordModified, myTeams,
						nbReportsLeft, subscribtionType, email, nbReportsPerTeam, reportsAlreadyGenerated));
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la récupération des utilisateurs : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return users;
	}

	public static Collection<Match> getMatchesToCount() {
		Connection cn = initializeOrGetConnection();
		String query = "";
		List<Match> matchesToCount = new ArrayList<Match>();
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM matches WHERE Comptabilise = 0";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				Match match = getMatch(id);
				matchesToCount.add(match);
			}
		} catch (SQLException | NullMatchException e) {
			Ligue1Utils
					.reportError("Erreur à la récupération des matchs de Ligue 1 à comptabiliser : " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return matchesToCount;
	}

	public static int getMatchesCounted() {
		Connection cn = initializeOrGetConnection();
		String query = "";
		int matchesCounted = 0;
		try {
			Statement st = cn.createStatement();
			query = "SELECT * FROM matches WHERE Comptabilise = 1";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				matchesCounted++;
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la récupération des matchs de Ligue 1 comptabilisés : " + e.getMessage());
			e.printStackTrace();
		}
		return matchesCounted;
	}

	/**
	 * @param result : VictoireEquipeDomicile, nul, VictoireEquipeExterieur
	 * @return le pourcentage global en ligue 1 cette saison pour ce résultat
	 */
	public static float getGlobalPercentage(String result) {
		Connection cn = initializeOrGetConnection();
		String query = "";
		int nb = 0;
		try {
			Statement st = cn.createStatement();
			query = "SELECT COUNT(*) FROM matches WHERE Comptabilise = 1 AND " + result + " = 1";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				nb = rs.getInt(1);
			}
		} catch (SQLException e) {
			Ligue1Utils
					.reportError("Erreur à la récupération du nombre de " + result + " en Ligue 1 : " + e.getMessage());
			e.printStackTrace();
		}
		return Ligue1Utils.percentage(nb);
	}

	public static void createOrUpdateMatch(Match match) {
		Connection cn = initializeOrGetConnection();
		try {
			Statement st = cn.createStatement();
			String select = "SELECT * FROM matches WHERE matches.id = \"" + match.getHomeTeamNickname() + "-"
					+ match.getAwayTeamNickname() + "\"";
			String update1 = getQueryToUpdateInTableMatches("SurnomE1", match, match.getHomeTeamNickname());
			String update2 = getQueryToUpdateInTableMatches("SurnomE2", match, match.getAwayTeamNickname());
			String update3 = getQueryToUpdateInTableMatches("VictoireEquipeDomicile", match, match.getHomeTeamWin());
			String update4 = getQueryToUpdateInTableMatches("nul", match, match.getDraw());
			String update5 = getQueryToUpdateInTableMatches("VictoireEquipeExterieur", match, match.getAwayTeamWin());
			String update6 = getQueryToUpdateInTableMatches("ImportantEquipeDomicile", match,
					match.getIsAnImportantGameForHomeTeam());
			String update7 = getQueryToUpdateInTableMatches("ImportantEquipeExterieur", match,
					match.getIsAnImportantGameForAwayTeam());
			String update8 = getQueryToUpdateInTableMatches("EquipeDomicileMieuxClassee", match,
					match.getHomeTeamHasABetterStanding());
			String update9 = getQueryToUpdateInTableMatches("ResetAllSeason", match, match.getResetAllSeason());
			String update10 = getQueryToUpdateInTableMatches("Comptabilise", match, match.getCountMatch());
			String update11 = getQueryToUpdateInTableMatches("Score", match, match.getScore());
			String update12 = getQueryToUpdateInTableMatches("RapportStatistiques", match,
					match.getActiveStatisticsReportGeneration());
			String update13 = getQueryToUpdateInTableMatches("Journey", match, match.getJourney());
			String update14 = getQueryToUpdateInTableMatches("Comment", match, (Ligue1Utils.isEmpty(match.getComment()) ? "" : match.getComment()));
			String create = getQueryToInsertIntoTableMatches(match);
			ResultSet rs = st.executeQuery(select);
			if (rs.next()) {
				st.executeUpdate(update1);
				st.executeUpdate(update2);
				st.executeUpdate(update3);
				st.executeUpdate(update4);
				st.executeUpdate(update5);
				st.executeUpdate(update6);
				st.executeUpdate(update7);
				st.executeUpdate(update8);
				st.executeUpdate(update9);
				st.executeUpdate(update10);
				st.executeUpdate(update11);
				st.executeUpdate(update12);
				st.executeUpdate(update13);
				st.executeUpdate(update14);
			} else {
				st.executeUpdate(create);
			}
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la création / mise à jour du match " + match.getId() + " : " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	public static String getQueryToUpdateInTableMatches(String field, Match match, Object attribute) {
		return "UPDATE matches SET " + field + " = \"" + attribute + "\"  WHERE matches.id = \""
				+ match.getHomeTeamNickname() + "-" + match.getAwayTeamNickname() + "\"";

	}

	public static String getQueryToUpdateInTableTeams(String field, Team team, Object attribute) {
		return "UPDATE teams SET " + field + " = '" + attribute + "'  WHERE teams.surnom = \"" + team.getNickName()
				+ "\"";

	}

	public static String getQueryToInsertIntoTableMatches(Match match) {
		return "INSERT INTO matches VALUES ('" + match.getId() + "','" + match.getHomeTeamNickname() + "','"
				+ match.getAwayTeamNickname() + "','" + match.getHomeTeamWin() + "','" + match.getDraw() + "','"
				+ match.getAwayTeamWin() + "','" + match.getIsAnImportantGameForHomeTeam() + "','"
				+ match.getIsAnImportantGameForAwayTeam() + "','" + match.getHomeTeamHasABetterStanding() + "','"
				+ match.getResetAllSeason() + "','" + match.getCountMatch() + "','" + match.getScore() + "','"
				+ match.getActiveStatisticsReportGeneration() + "','" + match.getJourney() + "','" + match.getComment()
				+ "')";
	}

	public static String getQueryToUpdateInTableConfrontations(String field, Confrontation confrontation,
			Object attribute) {
		return "UPDATE confrontations SET " + field + " = '" + attribute + "'  WHERE confrontations.match = \""
				+ confrontation.getHomeTeamNickName() + "-" + confrontation.getAwayTeamNickname() + "\"";
	}

	public static String getQueryToInsertIntoTableConfrontations(Confrontation confrontation) {
		return "INSERT INTO confrontations VALUES ('" + confrontation.getMatch() + "','" + confrontation.getRecent1()
				+ "','" + confrontation.getRecent2() + "','" + confrontation.getRecent3() + "','"
				+ confrontation.getRecent4() + "','" + confrontation.getRecent5() + "')";
	}

	public static String getQueryToUpdateInTableStatistics(String field, Statistic stat, Object attribute) {
		return "UPDATE statistics SET " + field + " = '" + attribute + "'  WHERE statistics.match = \""
				+ stat.getMatch() + "\"";
	}

	public static String getQueryToInsertIntoTableStatistics(Statistic stat) {
		return "INSERT INTO statistics VALUES ('" + stat.getMatch() + "','" + stat.getHomeTeamWinPercentage() + "','"
				+ stat.getAwayTeamWinPercentage() + "','" + stat.getDrawPercentage() + "','" + stat.getGoalAverage()
				+ "','" + stat.getHomeTeamGoalAverage() + "','" + stat.getAwayTeamGoalAverage() + "','"
				+ stat.getMoreThan05GoalPercentage() + "','" + stat.getMoreThan15GoalPercentage() + "','"
				+ stat.getMoreThan25GoalsPercentage() + "','" + stat.getMoreThan35GoalsPercentage() + "','"
				+ stat.getMoreThan45GoalsPercentage() + "','" + stat.getLessThan05GoalPercentage() + "','"
				+ stat.getLessThan15GoalPercentage() + "','" + stat.getLessThan25GoalsPercentage() + "','"
				+ stat.getLessThan35GoalsPercentage() + "','" + stat.getLessThan45GoalsPercentage() + "','"
				+ stat.getBothTeamsToScorePercentage() + "','" + stat.getHomeTeamScoredMoreThan05GoalPercentage()
				+ "','" + stat.getHomeTeamScoredMoreThan15GoalPercentage() + "','"
				+ stat.getHomeTeamScoredMoreThan25GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredMoreThan35GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredMoreThan45GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredLessThan05GoalPercentage() + "','"
				+ stat.getHomeTeamScoredLessThan15GoalPercentage() + "','"
				+ stat.getHomeTeamScoredLessThan25GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredLessThan35GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredLessThan45GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredMoreThan05GoalPercentage() + "','"
				+ stat.getAwayTeamScoredMoreThan15GoalPercentage() + "','"
				+ stat.getAwayTeamScoredMoreThan25GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredMoreThan35GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredMoreThan45GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredLessThan05GoalPercentage() + "','"
				+ stat.getAwayTeamScoredLessThan15GoalPercentage() + "','"
				+ stat.getAwayTeamScoredLessThan25GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredLessThan35GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredLessThan45GoalsPercentage() + "','"
				+ stat.getAwayTeamWinByExactly1GoalPercentage() + "','" + stat.getAwayTeamWinByExactly2GoalsPercentage()
				+ "','" + stat.getAwayTeamWinByExactly3GoalsPercentage() + "','"
				+ stat.getAwayTeamWinByExactly4GoalsPercentage() + "','"
				+ stat.getAwayTeamWinByExactly5GoalsPercentage() + "','"
				+ stat.getAwayTeamWinByExactly6GoalsPercentage() + "','"
				+ stat.getAwayTeamWinByExactly7GoalsPercentage() + "','"
				+ stat.getAwayTeamWinByExactly8GoalsPercentage() + "','"
				+ stat.getAwayTeamWinByExactly9GoalsPercentage() + "','" + stat.getHomeTeamWinByExactly1GoalPercentage()
				+ "','" + stat.getHomeTeamWinByExactly2GoalsPercentage() + "','"
				+ stat.getHomeTeamWinByExactly3GoalsPercentage() + "','"
				+ stat.getHomeTeamWinByExactly4GoalsPercentage() + "','"
				+ stat.getHomeTeamWinByExactly5GoalsPercentage() + "','"
				+ stat.getHomeTeamWinByExactly6GoalsPercentage() + "','"
				+ stat.getHomeTeamWinByExactly7GoalsPercentage() + "','"
				+ stat.getHomeTeamWinByExactly8GoalsPercentage() + "','"
				+ stat.getHomeTeamWinByExactly9GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredExactly0GoalPercentage() + "','"
				+ stat.getHomeTeamScoredExactly1GoalPercentage() + "','"
				+ stat.getHomeTeamScoredExactly2GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredExactly3GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredExactly4GoalsPercentage() + "','"
				+ stat.getHomeTeamScoredExactly5GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredExactly0GoalPercentage() + "','"
				+ stat.getAwayTeamScoredExactly1GoalPercentage() + "','"
				+ stat.getAwayTeamScoredExactly2GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredExactly3GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredExactly4GoalsPercentage() + "','"
				+ stat.getAwayTeamScoredExactly5GoalsPercentage() + "','"
				+ stat.getHomeTeamConcededExactly0GoalPercentage() + "','"
				+ stat.getHomeTeamConcededExactly1GoalPercentage() + "','"
				+ stat.getHomeTeamConcededExactly2GoalsPercentage() + "','"
				+ stat.getHomeTeamConcededExactly3GoalsPercentage() + "','"
				+ stat.getHomeTeamConcededExactly4GoalsPercentage() + "','"
				+ stat.getHomeTeamConcededExactly5GoalsPercentage() + "','"
				+ stat.getAwayTeamConcededExactly0GoalPercentage() + "','"
				+ stat.getAwayTeamConcededExactly1GoalPercentage() + "','"
				+ stat.getAwayTeamConcededExactly2GoalsPercentage() + "','"
				+ stat.getAwayTeamConcededExactly3GoalsPercentage() + "','"
				+ stat.getAwayTeamConcededExactly4GoalsPercentage() + "','"
				+ stat.getAwayTeamConcededExactly5GoalsPercentage() + "')";
	}

	public static String getQueryToInsertIntoTableTeams(Team team) {
		return "INSERT INTO teams VALUES ('" + team.getName() + "','" + team.getNickName() + "','" + team.getStanding()
				+ "','" + team.getHomeStanding() + "','" + team.getAwayStanding() + "','" + team.getNbMatchesPlayed()
				+ "','" + team.getNbWins() + "','" + team.getNbDraws() + "','" + team.getNbLosses() + "','"
				+ team.getNbHomeMatchesPlayed() + "','" + team.getNbHomeWins() + "','" + team.getNbHomeDraws() + "','"
				+ team.getNbHomeLosses() + "','" + team.getNbAwayMatchesPlayed() + "','" + team.getNbAwayWins() + "','"
				+ team.getNbAwayDraws() + "','" + team.getNbAwayLosses() + "','" + team.getRecent1() + "','"
				+ team.getRecent2() + "','" + team.getRecent3() + "','" + team.getRecent4() + "','" + team.getRecent5()
				+ "','" + team.getWinningSerie() + "','" + team.getDrawSerie() + "','" + team.getLoosingSerie() + "','"
				+ team.getHomeRecent1() + "','" + team.getHomeRecent2() + "','" + team.getHomeRecent3() + "','"
				+ team.getHomeRecent4() + "','" + team.getHomeRecent5() + "','" + team.getHomeWinningSerie() + "','"
				+ team.getHomeDrawSerie() + "','" + team.getHomeLoosingSerie() + "','" + team.getAwayRecent1() + "','"
				+ team.getAwayRecent2() + "','" + team.getAwayRecent3() + "','" + team.getAwayRecent4() + "','"
				+ team.getAwayRecent5() + "','" + team.getAwayWinningSerie() + "','" + team.getAwayDrawSerie() + "','"
				+ team.getAwayLoosingSerie() + "','" + team.getNbMatchesPlayedAgainstStandingSuperior() + "','"
				+ team.getNbWinsAgainstStandingSuperior() + "','" + team.getNbDrawsAgainstStandingSuperior() + "','"
				+ team.getNbLossesAgainstStandingSuperior() + "','"
				+ team.getNbMatchesPlayedAgainstStandingSuperiorAtHome() + "','"
				+ team.getNbWinsAgainstStandingSuperiorAtHome() + "','" + team.getNbDrawsAgainstStandingSuperiorAtHome()
				+ "','" + team.getNbLossesAgainstStandingSuperiorAtHome() + "','"
				+ team.getNbMatchesPlayedAgainstStandingSuperiorAway() + "','"
				+ team.getNbWinsAgainstStandingSuperiorAway() + "','" + team.getNbDrawsAgainstStandingSuperiorAway()
				+ "','" + team.getNbLossesAgainstStandingSuperiorAway() + "','"
				+ team.getNbMatchesPlayedAgainstStandingInferior() + "','" + team.getNbWinsAgainstStandingInferior()
				+ "','" + team.getNbDrawsAgainstStandingInferior() + "','" + team.getNbLossesAgainstStandingInferior()
				+ "','" + team.getNbMatchesPlayedAgainstStandingInferiorAtHome() + "','"
				+ team.getNbWinsAgainstStandingInferiorAtHome() + "','" + team.getNbDrawsAgainstStandingInferiorAtHome()
				+ "','" + team.getNbLossesAgainstStandingInferiorAtHome() + "','"
				+ team.getNbMatchesPlayedAgainstStandingInferiorAway() + "','"
				+ team.getNbWinsAgainstStandingInferiorAway() + "','" + team.getNbDrawsAgainstStandingInferiorAway()
				+ "','" + team.getNbLossesAgainstStandingInferiorAway() + "','"
				+ team.getNbMatchesPlayedAgainstImportantOpponent() + "','" + team.getNbWinsAgainstImportantOpponent()
				+ "','" + team.getNbDrawsAgainstImportantOpponent() + "','" + team.getNbLossesAgainstImportantOpponent()
				+ "','" + team.getNbMatchesPlayedAgainstImportantOpponentAtHome() + "','"
				+ team.getNbWinsAgainstImportantOpponentAtHome() + "','"
				+ team.getNbDrawsAgainstImportantOpponentAtHome() + "','"
				+ team.getNbLossesAgainstImportantOpponentAtHome() + "','"
				+ team.getNbMatchesPlayedAgainstImportantOpponentAway() + "','"
				+ team.getNbWinsAgainstImportantOpponentAway() + "','" + team.getNbDrawsAgainstImportantOpponentAway()
				+ "','" + team.getNbLossesAgainstImportantOpponentAway() + "','"
				+ team.getNbMatchesPlayedAgainstNormalOpponent() + "','" + team.getNbWinsAgainstNormalOpponent() + "','"
				+ team.getNbDrawsAgainstNormalOpponent() + "','" + team.getNbLossesAgainstNormalOpponent() + "','"
				+ team.getNbMatchesPlayedAgainstNormalOpponentAtHome() + "','"
				+ team.getNbWinsAgainstNormalOpponentAtHome() + "','" + team.getNbDrawsAgainstNormalOpponentAtHome()
				+ "','" + team.getNbLossesAgainstNormalOpponentAtHome() + "','"
				+ team.getNbMatchesPlayedAgainstNormalOpponentAway() + "','" + team.getNbWinsAgainstNormalOpponentAway()
				+ "','" + team.getNbDrawsAgainstNormalOpponentAway() + "','"
				+ team.getNbLossesAgainstNormalOpponentAway() + "','" + team.getNbPoints() + "','"
				+ team.getNbHomePoints() + "','" + team.getNbAwayPoints() + "','" + team.getGoalAverage() + "','"
				+ team.getHomeGoalAverage() + "','" + team.getAwayGoalAverage() + "')";
	}

	public static File saveAllSeason() {
		Properties properties = new Properties();
		properties.setProperty(MysqlExportService.JDBC_CONNECTION_STRING,
				CONNECTION_PATH);
		properties.setProperty(MysqlExportService.DB_NAME, CONNECTION_DB_NAME);
		properties.setProperty(MysqlExportService.DB_USERNAME, CONNECTION_USER_NAME);
		properties.setProperty(MysqlExportService.DB_PASSWORD, CONNECTION_USER_PASSWORD);
		properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");
		properties.setProperty(MysqlExportService.PRESERVE_GENERATED_SQL_FILE, "true");
		String file = new File("C:\\perso\\Ligue1\\sauvegardes").getPath();
		properties.setProperty(MysqlExportService.TEMP_DIR, file);

		MysqlExportService mysqlExportService = new MysqlExportService(properties);
		File sqlFile = null;
		try {
			mysqlExportService.export();
			sqlFile = new File(mysqlExportService.getGeneratedZipFile().getAbsolutePath());
		} catch (ClassNotFoundException | IOException | SQLException e) {
			Ligue1Utils.reportError("Erreur à la sauvegarde de la base de données." + e.getMessage());
			e.printStackTrace();
			return null;
		}
		if (sqlFile.exists()) {
			Ligue1Utils.reportInfo("La saison a bien été sauvegardée");
			return sqlFile;
		}
		return null;
	}

	public static void deleteAllMatches() {
		Connection cn = initializeOrGetConnection();
		try {
			Statement st = cn.createStatement();
			String delete = "DELETE FROM matches";
			st.executeUpdate(delete);
		} catch (SQLException e) {
			Ligue1Utils.reportError("Erreur à la suppression de tous les matchs de Ligue 1 : " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	public static void main(String[] args) {
		System.out.println(initializeOrGetConnection());
	}

}

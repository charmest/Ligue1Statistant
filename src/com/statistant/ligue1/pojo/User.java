package com.statistant.ligue1.pojo;

/**
 * POJO class used for user authentification.
 * 
 * @author Thomas CHARMES
 */
public class User {

	private String login;
	private String password;
	private String reportPath;
	private String journeesSubscribed;
	private int passwordModified;
	private String myTeams;
	private int nbReportsLeft;
	private String subscribtionType;
	private String email;
	private int nbReportsPerTeam;
	private String reportsAlreadyGenerated;

	public User(String login, String password, String reportPath, String journeesSubscribed, int passwordModified,
			String myTeams, int nbReportsLeft, String subscribtionType, String email, int nbReportsPerTeam, String reportsAlreadyGenerated) {
		this.login = login;
		this.password = password;
		this.reportPath = reportPath;
		this.journeesSubscribed = journeesSubscribed;
		this.passwordModified = passwordModified;
		this.myTeams = myTeams;
		this.nbReportsLeft = nbReportsLeft;
		this.subscribtionType = subscribtionType;
		this.email = email;
		this.nbReportsPerTeam = nbReportsPerTeam;
		this.reportsAlreadyGenerated = reportsAlreadyGenerated;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.reportPath = "";
		this.passwordModified = 0;
		this.myTeams = "";
		this.nbReportsLeft = 0;
		this.subscribtionType = "";
		this.email = "";
		this.nbReportsPerTeam = 0;
		this.reportsAlreadyGenerated = "";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public int getPasswordModified() {
		return passwordModified;
	}

	public void setPasswordModified(int passwordModified) {
		this.passwordModified = passwordModified;
	}

	public String getJourneesSubscribed() {
		return journeesSubscribed;
	}

	public void setJourneesSubscribed(String journeesSubscribed) {
		this.journeesSubscribed = journeesSubscribed;
	}

	public String getMyTeams() {
		return myTeams;
	}

	public void setMyTeams(String myTeams) {
		this.myTeams = myTeams;
	}

	public int getNbReportsLeft() {
		return nbReportsLeft;
	}

	public void setNbReportsLeft(int nbReportsLeft) {
		this.nbReportsLeft = nbReportsLeft;
	}

	public String getSubscribtionType() {
		return subscribtionType;
	}

	public void setSubscribtionType(String subscribtionType) {
		this.subscribtionType = subscribtionType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNbReportsPerTeam() {
		return nbReportsPerTeam;
	}

	public void setNbReportsPerTeam(int nbReportsPerTeam) {
		this.nbReportsPerTeam = nbReportsPerTeam;
	}

	public String getReportsAlreadyGenerated() {
		return reportsAlreadyGenerated;
	}

	public void setReportsAlreadyGenerated(String reportsAlreadyGenerated) {
		this.reportsAlreadyGenerated = reportsAlreadyGenerated;
	}

	public static void main(String[] args) {

	}

}

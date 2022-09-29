package com.statistant.ligue1.view.resources.fxml;

import com.statistant.ligue1.view.InitializeWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuOverviewController {
	
	@FXML
	private Button account;
	@FXML
	private Button matchs;
	@FXML
	private Button standings;
	@FXML
	private Button confrontations;
	@FXML
	private Button resetAllSeason;
	@FXML
	private MenuButton admin;
	@FXML
	private Button users;
	@FXML
	private Button subscribtions;
    
    public MenuButton getAdmin() {
		return admin;
	}

	public void setAdmin(MenuButton admin) {
		this.admin = admin;
	}

	public Button getUsers() {
		return users;
	}

	public void setUsers(Button users) {
		this.users = users;
	}

	public Button getSubscribtions() {
		return subscribtions;
	}

	public void setSubscribtions(Button subscribtions) {
		this.subscribtions = subscribtions;
	}

	public Button getAccount() {
		return account;
	}

	public void setAccount(Button account) {
		this.account = account;
	}

	public Button getMatchs() {
		return matchs;
	}

	public void setMatchs(Button matchs) {
		this.matchs = matchs;
	}

	public Button getStandings() {
		return standings;
	}

	public void setStandings(Button standings) {
		this.standings = standings;
	}

	public Button getConfrontations() {
		return confrontations;
	}

	public void setConfrontations(Button confrontations) {
		this.confrontations = confrontations;
	}

	public Button getResetAllSeason() {
		return resetAllSeason;
	}

	public void setResetAllSeason(Button resetAllSeason) {
		this.resetAllSeason = resetAllSeason;
	}
	
	@FXML
	private void initialize() {
		Button btnAccount = getAccount();
		Image imgAccount = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/profil.png").toExternalForm());
		ImageView viewAccount = new ImageView(imgAccount);
		btnAccount.setGraphic(viewAccount);
		setAccount(btnAccount);
		
		Button btnConfrontations = getConfrontations();
		Image imgConfrontations = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/eclair.png").toExternalForm());
		ImageView viewConfrontations = new ImageView(imgConfrontations);
		btnConfrontations.setGraphic(viewConfrontations);
		setConfrontations(btnConfrontations);
		
		Button btnMatchs = getMatchs();
		Image imgMatchs = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/score.png").toExternalForm());
		ImageView viewmatchs = new ImageView(imgMatchs);
		btnMatchs.setGraphic(viewmatchs);
		setMatchs(btnMatchs);
		
		Button btnReset = getResetAllSeason();
		Image imgReset = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/attention.png").toExternalForm());
		ImageView viewReset = new ImageView(imgReset);
		btnReset.setGraphic(viewReset);
		if (! AuthentificationOverviewController.getUSER_LOGIN().equals("sysadmin")) {
			btnReset.setVisible(false);
		}
		setResetAllSeason(btnReset);
		
		MenuButton admin = getAdmin();
		Image imgAdmin = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/parametres.png").toExternalForm());
		ImageView viewAdmin = new ImageView(imgAdmin);
		admin.setGraphic(viewAdmin);
		if (! AuthentificationOverviewController.getUSER_LOGIN().equals("sysadmin")) {
			admin.setVisible(false);
		}
		setAdmin(admin);
		
		Button btnStandings = getStandings();
		Image imgStandings = new Image(this.getClass().getResource("/com/statistant/ligue1/view/resources/images/classement.png").toExternalForm());
		ImageView viewStandings = new ImageView(imgStandings);
		btnStandings.setGraphic(viewStandings);
		setStandings(btnStandings);
	}
	
	@FXML
	private void handleUsers() {
		InitializeWindow.showUserModificationOverview();
	}
	
	@FXML
	private void handleSubscribtions() {
		InitializeWindow.showSubscribtionModificationOverview();
	}

	@FXML
    private void handleMatchs() {
    	InitializeWindow.showMatchOverview();
    }
    
    @FXML
    private void handleAccount() {
    	String userLogin = AuthentificationOverviewController.getUSER_LOGIN();
    	InitializeWindow.showAccountOverview(userLogin);
    }
    
    @FXML
    private void handleConfrontations() {
    	InitializeWindow.showConfrontationOverview();
    }
    
    @FXML
    private void handleStandings() {
    	InitializeWindow.showStandingOverview();
    }
    
    @FXML
    private void handleResetAllSeason() {
    	InitializeWindow.showAlertResetAllSeason();
    }

}

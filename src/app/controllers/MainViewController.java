package app.controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;

public class MainViewController extends Main{

	//private Main main;

	@FXML
	private void goHome() throws IOException{
		showMainItems();
	}
	@FXML
	void logout() throws IOException {
		showLogin();
	}

}

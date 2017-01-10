package app.controllers;

import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;

public class MainViewController {

	private Main main;

	@FXML
	private void goHome() throws IOException{
		main.showMainItems();
	}

}

package edu.westga.cs1302.password_generator;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/** Starting point for the project. Launches the main window.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Main extends Application {
	private static final String WINDOW_TITLE = "Password Generator";
	private static final String GUI_RESOURCE = "view/MainWindow.fxml";

	/**
	 * JavaFX entry point.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @throws IOException
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(Main.GUI_RESOURCE));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
	}
	
	/**
	 * Primary Java entry point.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param args command line arguments
	 */

	public static void main(String[] args) {
		Main.launch(args);
	}

}

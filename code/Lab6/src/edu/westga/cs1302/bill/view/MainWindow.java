package edu.westga.cs1302.bill.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;
import edu.westga.cs1302.bill.model.CSVBillPersistenceManager;
import edu.westga.cs1302.bill.model.TSVBillPersistenceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The codebehind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	public static final String DATA_FILE = "data.txt";
	private Bill bill;

	@FXML
	private TextField name;
	@FXML
	private TextField amount;
	@FXML
	private TextArea receiptArea;
	@FXML
	private ComboBox<String> serverName;
	@FXML
    private ComboBox<BillPersistenceManager> format;

	@FXML
	void addItem(ActionEvent event) {
		try {
			BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
			this.bill.addItem(item);
			this.name.clear();
			this.amount.clear();
			this.updateReceipt();
		} catch (NumberFormatException numError) {
			this.displayErrorPopup("Invalid amount provided, please correct and try again.");
		} catch (IllegalArgumentException argError) {
			this.displayErrorPopup("Unable to add new bill item");
		}
	}

	private void updateReceipt() {
		this.receiptArea.setText(BillTextifier.getText(this.bill));
	}

	@FXML
	void selectServer(ActionEvent event) {
		String name = this.serverName.getValue();
		if (name != null) {
			this.bill.setServerName(name);
			this.updateReceipt();
		}
	}

	@FXML
	void saveBillData(ActionEvent event) {
		try {
			this.format.getValue().saveBillData(this.bill);
		} catch (IOException writeError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Unable to save data to file!");
			alert.showAndWait();
		}
	}

	private void displayErrorPopup(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	void initialize() {
		this.serverName.getItems().add("Bob");
		this.serverName.getItems().add("Alice");
		this.serverName.getItems().add("Trudy");
		this.format.getItems().add(new CSVBillPersistenceManager());
		this.format.getItems().add(new TSVBillPersistenceManager());
		this.format.setValue(this.format.getItems().get(0));
		File inputFile = new File(DATA_FILE);
		this.bill = new Bill();
		try (Scanner reader = new Scanner(inputFile)) {
			String format = reader.nextLine();
			if (format.equals("CSV")) {
				CSVBillPersistenceManager loader = new CSVBillPersistenceManager();
				Bill loadedBill = loader.loadBillData();
				this.bill = loadedBill;
			}
			if (format.equals("TSV")) {
				TSVBillPersistenceManager loader = new TSVBillPersistenceManager();
				Bill loadedBill = loader.loadBillData();
				this.bill = loadedBill;
			}
			if (this.bill.getItems().length == 0 && this.bill.getServerName().equals("No Server Set")) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Save is not in a valid format. Loading without save data.");
				alert.showAndWait();
			}
		} catch (FileNotFoundException noFileError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Save file not found.");
			alert.setContentText(noFileError.getMessage() + ". Loading without data.");
			alert.showAndWait();
			this.bill = new Bill();
		} 

		this.updateReceipt();
	}
}

package edu.westga.cs1302.cms.view;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/** Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
    @FXML private TextField name;
    @FXML private ListView<Student> students;
    @FXML private TextField grade;
    @FXML private ListView<String> averageGrade;

    @FXML
    void addStudent(ActionEvent event) {
    	String studentName = this.name.getText();
    	try {
    		int grade = Integer.parseInt(this.grade.getText());
    		Student student = new Student(studentName, grade);
        	this.students.getItems().add(student);
    	} catch (NumberFormatException gradeError) {
    		if (studentName.equals("")) {
    			Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to add student: " + gradeError.getMessage() + ". Please add a grade and name to the grade and name boxes.");
        		errorPopup.showAndWait();
    		} else if (studentName.length() < 3) {
    			Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to add student: " + gradeError.getMessage() + ". Please add a grade and name at least 3 letters long to the grade and name boxes.");
        		errorPopup.showAndWait();
    		} else {
    			Alert errorPopup = new Alert(AlertType.ERROR);
    			errorPopup.setContentText("Unable to add student: " + gradeError.getMessage() + ". Please add a whole number grade to the grade box.");
    			errorPopup.showAndWait();
    		}
    	} catch (IllegalArgumentException badInputError) {
    		if (badInputError.getMessage().equals("Grade must be between 0 and 100.")) {
    			Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("Unable to add student: " + badInputError.getMessage() + " Please add a grade between 0 and 100 in the grade box.");
        		errorPopup.showAndWait();
    		} else {
    		Alert errorPopup = new Alert(AlertType.ERROR);
    		errorPopup.setContentText("Unable to add student: " + badInputError.getMessage() + " Please add a name thats 3 letters or longer to the name box.");
    		errorPopup.showAndWait();
    		}
    	}
    	this.displayAverageGrade();
    }

    @FXML
    void removeStudent(ActionEvent event) {
    	Student student = this.students.getSelectionModel().getSelectedItem();
    	if (student != null) {
    	this.students.getItems().remove(student);
    	} else {
    		Alert errorPopup = new Alert(AlertType.ERROR);
    		errorPopup.setContentText("No student selected. Please select a student to remove.");
    		errorPopup.showAndWait();
    	}
    	this.displayAverageGrade();
    }
    
    @FXML
    void showGrade(ActionEvent event) {
    	Student student = this.students.getSelectionModel().getSelectedItem();
    	if (student != null) {
        	int studentGrade = student.getGrade();
        	Alert infoPopup = new Alert(AlertType.INFORMATION);
        	infoPopup.setContentText(student.getName() + "'s current grade is: " + studentGrade);
        	infoPopup.showAndWait();
        	} else {
        		Alert errorPopup = new Alert(AlertType.ERROR);
        		errorPopup.setContentText("No student selected. Please select a student you want to see the grade of.");
        		errorPopup.showAndWait();
        	}
    }
    
    @FXML
    void displayAverageGrade() {
    	this.averageGrade.getItems().clear();
    	String currentAverageGrade = "N/a";
    	if (this.students.getItems().isEmpty()) {
    		this.averageGrade.getItems().add(currentAverageGrade);
    	} else {
    		int gradeSum = 0;
    		for (Student currentStudent: this.students.getItems()) {
    			int singleGrade = currentStudent.getGrade();
    			gradeSum += singleGrade;
    		}
    		double averageTotal = (gradeSum + 0.0) / this.students.getItems().size();
    		currentAverageGrade = averageTotal + "";
    		if (currentAverageGrade.length() > 4) {
    			currentAverageGrade = currentAverageGrade.substring(0, 5);
    		}
    		this.averageGrade.getItems().add(currentAverageGrade);
    	}
    }

    @FXML
    void initialize() {
        assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

}

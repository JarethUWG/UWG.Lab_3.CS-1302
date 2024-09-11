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
    	int grade = Integer.parseInt(this.grade.getText());
    	String studentName = this.name.getText();
    	
    	Student student = new Student(studentName, grade);
    	
    	this.students.getItems().add(student);
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

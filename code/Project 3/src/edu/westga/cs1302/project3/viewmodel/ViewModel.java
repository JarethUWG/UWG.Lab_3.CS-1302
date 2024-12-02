package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

/** Manages utilizing the model and makes properties available to bind the UI elements.
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class ViewModel {
	private TaskManager manager;
	private ListProperty<Task> tasks;
	
	/** Initialize the properties and TaskManager for the viewmodel 
	 */
	public ViewModel() {
		this.manager = new TaskManager(null);
		Task task1 = new Task("Add Tasks", "Start adding tasks using the + button");
		Task task2 = new Task("Remove Tasks", "Start removing tasks using the - button");
		this.manager.addTask(task2);
		this.manager.addTask(task1);
		this.tasks = new SimpleListProperty<Task>(FXCollections.observableArrayList());
		this.tasks.addAll(this.manager.getTasks());
	}
	
	/** Return the tasks property
	 * 
	 * @return the tasks property
	 */
	public ListProperty<Task> getTasks() {
		return this.tasks;
	}
}

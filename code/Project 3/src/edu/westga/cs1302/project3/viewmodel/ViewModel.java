package edu.westga.cs1302.project3.viewmodel;

import java.io.File;
import java.io.FileNotFoundException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskPersistenceManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

/** Manages utilizing the model and makes properties available to bind the UI elements.
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class ViewModel {
	private TaskManager manager;
	private ListProperty<Task> tasks;
	private ObjectProperty<File> loadFile;

	/** Initialize the properties and TaskManager for the viewmodel 
	 */
	public ViewModel() {
		this.manager = new TaskManager(null);
		Task task1 = new Task("Add Tasks", "Start adding tasks using the + button");
		Task task2 = new Task("Remove Tasks", "Start removing tasks using the - button");
		this.manager.addTask(task2);
		this.manager.addTask(task1);
		this.tasks = new SimpleListProperty<Task>(FXCollections.observableArrayList());
		this.loadFile = new SimpleObjectProperty<File>();
		this.tasks.addAll(this.manager.getTasks());
	}
	
	/** Return the tasks property
	 * 
	 * @return the tasks property
	 */
	public ListProperty<Task> getTasks() {
		return this.tasks;
	}
	
	/** Return the loadFile property
	 * 
	 * @return the loadFile property
	 */
	public ObjectProperty<File> getLoadFile() {
		return this.loadFile;
	}
	
	/** Manages loading from a selected file
	 * 
	 * @return true if the file successfully loaded and false if an exception was thrown
	 */
	public boolean loadFileSelector() {
		String selectedFilePath = this.loadFile.get().getPath();
		TaskPersistenceManager loader = new TaskPersistenceManager(selectedFilePath);
		try {
			this.manager = loader.loadTaskData();
			this.tasks.clear();
			this.tasks.addAll(this.manager.getTasks());
			return true;
		} catch (ArrayIndexOutOfBoundsException badFormat) {
			return false;
		} catch (FileNotFoundException doNothing) {	
		}
		return false;
	}
}

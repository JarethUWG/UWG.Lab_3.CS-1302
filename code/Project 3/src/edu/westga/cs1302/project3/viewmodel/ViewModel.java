package edu.westga.cs1302.project3.viewmodel;

import java.io.File;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskPersistenceManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Manages utilizing the model and makes properties available to bind the UI
 * elements.
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class ViewModel {
	private TaskManager manager;
	private ListProperty<Task> tasks;
	private ObjectProperty<Task> selectedTask;
	private ObjectProperty<File> loadFile;
	private ObjectProperty<File> saveFile;
	private StringProperty taskTitle;
	private StringProperty taskDescription;

	/**
	 * Initialize the properties and TaskManager for the viewmodel
	 */
	public ViewModel() {
		this.manager = new TaskManager(null);
		Task task1 = new Task("Add Tasks", "Start adding tasks using the + button");
		Task task2 = new Task("Remove Tasks", "Start removing tasks using the - button");
		this.manager.addTask(task2);
		this.manager.addTask(task1);
		this.tasks = new SimpleListProperty<Task>(FXCollections.observableArrayList());
		this.selectedTask = new SimpleObjectProperty<Task>();
		this.loadFile = new SimpleObjectProperty<File>();
		this.saveFile = new SimpleObjectProperty<File>();
		this.taskTitle = new SimpleStringProperty();
		this.taskDescription = new SimpleStringProperty();
		this.tasks.addAll(this.manager.getTasks());
	}

	/**
	 * Return the tasks property
	 * 
	 * @return the tasks property
	 */
	public ListProperty<Task> getTasks() {
		return this.tasks;
	}

	/**
	 * Return the loadFile property
	 * 
	 * @return the loadFile property
	 */
	public ObjectProperty<File> getLoadFile() {
		return this.loadFile;
	}

	/**
	 * Return the saveFile property
	 * 
	 * @return the saveFile property
	 */
	public ObjectProperty<File> getSaveFile() {
		return this.saveFile;
	}

	/**
	 * Return the taskTitle property
	 * 
	 * @return the taskTitle property
	 */
	public StringProperty getTaskTitle() {
		return this.taskTitle;
	}

	/**
	 * Return the selectedTask property
	 * 
	 * @return the selectedTask property
	 */
	public ObjectProperty<Task> getSelectedTask() {
		return this.selectedTask;
	}

	/**
	 * Return the taskDescription property
	 * 
	 * @return the taskDescription property
	 */
	public StringProperty getTaskDescription() {
		return this.taskDescription;
	}

	/**
	 * Manages loading from a selected file
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
		} catch (Exception notLoaded) {
			return false;
		}
	}

	/**
	 * Manages saving to a selected file
	 * 
	 * @return true if the file successfully saves and false if an exception was thrown
	 */
	public boolean saveFileSelector() {
		String selectedFilePath = this.saveFile.get().getPath();
		TaskPersistenceManager saver = new TaskPersistenceManager(selectedFilePath);
		try {
			saver.saveTaskData(this.manager);
			this.tasks.clear();
			this.tasks.addAll(this.manager.getTasks());
			return true;
		} catch (Exception cannotWrite) {
			return false;
		}
	}

	/**
	 * Adds a new task to the task list
	 * 
	 * @throws IllegalArgumentException if either title or description is missing
	 * @throws IllegalStateException if a task with the same title already exists
	 */
	public void addNextTask() throws IllegalArgumentException, IllegalStateException {
		Task nextTask = new Task(this.taskTitle.get(), this.taskDescription.get());
		this.manager.addTask(nextTask);
		this.tasks.add(0, nextTask);
	}

	/**
	 * Removes a selected task from the task list
	 * 
	 * @throws IllegalStateException if object wasn't removed
	 */
	public void removeTask() throws IllegalStateException {
		if (this.selectedTask.getValue() == null) {
			throw new IllegalStateException();
		} else {
			this.manager.removeTask(this.selectedTask.getValue());
			this.tasks.remove(this.selectedTask.getValue());
		}
	}
}

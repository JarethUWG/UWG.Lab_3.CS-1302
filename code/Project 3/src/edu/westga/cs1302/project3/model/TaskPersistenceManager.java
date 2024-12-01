package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Supports saving of task data
 * 
 * @author Jareth Batty
 * @version Fall 2024
 */
public class TaskPersistenceManager {
	private final String dataFile;
	
	/** Creates a new taskPersistenceManager object.​
	 * 
	 * @precondition filePath != null​
	 * @postcondition this.dataFile = filePath
	 * 
	 * @param filePath a file path for the file the user wishes to save and load task data from​
	 */
	public TaskPersistenceManager(String filePath) {
		if (filePath == null) {
			throw new IllegalArgumentException("Must provide a valid file path");
		}
		this.dataFile = filePath;
	}
	
	/** Save tasks as a TSV
	 * 
	 * Writes all task data to DATA_FILE
	 * 
	 * @precondition tasks != null
	 * @postcondition none
	 * 
	 * @param manager the task manager containing the tasks to be saved
	 * 
	 * @throws IOException if any issues occur in writing
	 * @throws IllegalArgumentException if precondition is violated
	 */
	public void saveTaskData(TaskManager manager) throws IOException, IllegalArgumentException {
		if (manager == null) {
			throw new IllegalArgumentException("Must provide a valid manager");
		}
		List<Task> tasks = manager.getTasks();
		try (FileWriter writer = new FileWriter(this.dataFile)) {
			for (Task currentTask : tasks) {
				writer.write(currentTask.getTitle() + "\t" + currentTask.getDescription() + System.lineSeparator());
			}
		}
	}
	
	/** Loads tasks from a TSV file
	 * 
	 * Reads all task data from DATA_FILE
	 * 
	 * @return A task manager containing the loaded tasks from the file
	 * 
	 * @throws FileNotFoundException if there is no file associated with dataFile
	 * @throws IllegalArgumentException if the formatting of the file is incorrect
	 */
	public TaskManager loadTaskData() throws FileNotFoundException, IllegalArgumentException {
		List<Task> tasks = new ArrayList<Task>();
		File inputFile = new File(this.dataFile);
		try (Scanner reader = new Scanner(inputFile)) {
			while (reader.hasNextLine()) {
				String[] taskData = reader.nextLine().strip().split("\t");
				tasks.add(new Task(taskData[0], taskData[1]));
			}
		} 
		TaskManager manager = new TaskManager(tasks);
		return manager;
    }
		
}


package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores and manages multiple tasks​ ​
 * 
 * @author Jareth Batty​
 * @version Fall 2024​
 */
public class TaskManager {
	private List<Task> tasks;
	private Map<String, Task> lookUpTasks;

	/**
	 * Creates a new taskManager object.​
	 * 
	 * @precondition none​
	 * @postcondition this.tasks == tasks || this.tasks == new ArrayList< Task>()
	 * 
	 * @param tasks an optional preexisting list of tasks to add.​
	 */
	public TaskManager(List<Task> tasks) {
		if (tasks == null) {
			this.tasks = new ArrayList<Task>();
			this.lookUpTasks = new HashMap<String, Task>();
		} else {
			this.tasks = tasks;
			this.lookUpTasks = new HashMap<String, Task>();
			for (Task task : this.tasks) {
				this.lookUpTasks.put(task.getTitle(), task);
			}
		}
	}

	/**
	 * Returns all tasks in the task manager
	 * 
	 * @return all tasks in the task manager
	 */
	public List<Task> getTasks() {
		return this.tasks;
	}

	/**
	 * Returns the lookUpTable in the task manager
	 * 
	 * @return the lookUpTable in the task manager
	 */
	public Map<String, Task> getLookUpTasks() {
		return this.lookUpTasks;
	}

	/**
	 * Adds a task to the task list
	 * 
	 * @precondition task != null &&
	 *               !(this.lookUpTasks.containsKey(task.getTitle()))​
	 * @postcondition this.tasks.get(0) == task &&
	 *                this.lookUpTasks.containsKey(task.getTitle())
	 * 
	 * @param task the task to be added
	 * @throws IllegalArgumentException if null precondition is violated
	 * @throws IllegalStateException    if lookUp precondition is violated
	 */
	public void addTask(Task task) throws IllegalArgumentException, IllegalStateException {
		if (task == null) {
			throw new IllegalArgumentException("Cannot add: task invalid.");
		}
		if (this.lookUpTasks.containsKey(task.getTitle())) {
			throw new IllegalStateException("Task with given title already exists");
		}
		this.tasks.add(0, task);
		this.lookUpTasks.put(task.getTitle(), task);
	}

	/**
	 * Removes all tasks with the same name as given task from the task list
	 * 
	 * @precondition task != null​
	 * @postcondition !this.tasks.contains(task)
	 * 
	 * @param task the task to be removed
	 * @throws IllegalArgumentException if precondition is violated
	 */
	public void removeTask(Task task) throws IllegalArgumentException {
		if (task == null) {
			throw new IllegalArgumentException("Cannot remove: no task selected.");
		}
		this.tasks.remove(task);
		this.lookUpTasks.remove(task.getTitle());
	}
}

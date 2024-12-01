package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.List;

/** Stores and manages multiple tasks​
 *​
 * @author Jareth Batty​
 * @version Fall 2024​
 */
public class TaskManager {
	private List<Task> tasks;
	
	/** Creates a new taskManager object.​
	 * 
	 * @precondition none​
	 * @postcondition this.tasks == tasks || this.tasks == new ArrayList< Task>()
	 * 
	 * @param tasks an optional preexisting list of tasks to add.​
	 */
	public TaskManager(List<Task> tasks) {
		if (tasks == null) {
			this.tasks = new ArrayList<Task>();
		} else {
			this.tasks = tasks;
		}
	}

	/** Returns all tasks in the task manager
	 * 
	 * @return all tasks in the task manager
	 */
	public List<Task> getTasks() {
		return this.tasks;
	}
	
	/** Adds a task to the task List
	 * 
	 * @precondition task != null​
	 * @postcondition this.tasks.get(0) == task
	 * 
	 * @param task the task to be added 
	 * @throws IllegalArgumentException if precondition is violated
	 */
	public void addTask(Task task) throws IllegalArgumentException {
		if (task == null) {
			throw new IllegalArgumentException("Cannot add: task invalid.");
		}
		this.tasks.add(0, task);
	}
	
	/** Removes all tasks with the same name and description as given task
	 *  from the task list
	 * 
	 * @precondition task != null​
	 * @postcondition !this.tasks.contains(task)
	 * 
	 * @param task the task to be removed
	 * @throws IllegalArgumentException if precondition is violated
	 */
	public void removeTask(Task task) throws IllegalArgumentException {
		ArrayList<Integer> removedIndexes = new ArrayList<Integer>();
		int tasksSize = this.tasks.size();
		if (task == null) {
			throw new IllegalArgumentException("Cannot remove: no task selected.");
		}
		for (int index = 0; index < tasksSize; index++) {
			if (this.tasks.get(index).getTitle().equals(task.getTitle()) 
			&& this.tasks.get(index).getDescription().equals(task.getDescription())) {
				removedIndexes.add(index);
			}
		}
		for (int removedIndex: removedIndexes) {
			this.tasks.remove(removedIndex);
		}
	}
		
}

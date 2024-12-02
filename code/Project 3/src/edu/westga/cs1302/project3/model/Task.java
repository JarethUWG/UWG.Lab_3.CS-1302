package edu.westga.cs1302.project3.model;

/** Stores information about one task​
 *​
 * @author Jareth Batty​
 * @version Fall 2024​
 */
public class Task {
	private final String title;
	private final String description;
	
	/** Creates a new task object.​
	  *​
	  * @precondition title != null, !title.isBlank(), 
	  * description != null, !description.isBlank()​
	  * @postcondition this.name == name, this.description == description
	  * 
	  * @param title the name of the task
	  * @param description a description of the task
	  *​
	  * @throws IllegalArgumentException if precondition is violated.​
	  */
	public Task(String title, String description) throws IllegalArgumentException {
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("Please input a valid title.");
		}
		if (description == null || description.isBlank()) {
			throw new IllegalArgumentException("Please input a valid description.");
		}
		this.title = title;
		this.description = description;
	}

	/** Returns the title of the task
	 * 
	 * @return the title of the task
	 */
	public String getTitle() {
		return this.title;
	}
	
	/** Returns the description of the task
	 * 
	 * @return the description of the task
	 */
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return this.title;
	}
	
}
